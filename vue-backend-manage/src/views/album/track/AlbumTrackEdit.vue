<!-- 专辑编辑 -->
<template>
  <div class="album-track-edit">
    <custom-modal :title="modalTips"
              :modalShow="showModelEdit"
              @modalRemove="modalRemove"
              @cancel="modalCancel"
              @confirm="modalConfirm">
      <Button shape="circle" type="primary" icon="ios-create-outline" class="float-right edit-top" size="small" ></Button>
      <Form ref="albumTrackEdit" :model="dataForm" :rules="dataRules" label-position="top" >
        <FormItem label="单集标题:" prop="title">
          <Input v-model="dataForm.title" clearable/>
        </FormItem>
        <FormItem label="标题图片:" prop="titleImageUrl">
          <Upload :before-upload="handleTitleImageUpload" action="">
            <Button icon="ios-cloud-upload-outline">图片上传</Button>
          </Upload>
          <div v-if="titleImage !== null">图片名: {{ titleImage.name }} <Button type="primary" @click="upload(titleImage, 'titleImageUrl')" :loading="loadingStatus">{{ loadingStatus ? '上传中...' : '请点击上传' }}</Button></div>
          <Input v-model="dataForm.titleImageUrl"  clearable/>
        </FormItem>
        <FormItem label="列表小图:" prop="imageUrl">
          <Upload :before-upload="handleImageUpload" action="">
            <Button icon="ios-cloud-upload-outline">图片上传</Button>
          </Upload>
          <div v-if="image !== null">图片名: {{ image.name }} <Button type="primary" @click="upload(image, 'imageUrl')" :loading="loadingStatus">{{ loadingStatus ? '上传中...' : '请点击上传' }}</Button></div>
          <Input v-model="dataForm.imageUrl" clearable />
        </FormItem>
        <FormItem label="音频文件:" prop="mediaUrl">
          <Upload :before-upload="handleMediaUpload" action="">
            <Button icon="ios-cloud-upload-outline">音频上传</Button>
          </Upload>
          <div v-if="video !== null">音频名: {{ video.name }} <Button type="primary" @click="uploadAudio" :loading="loadingStatus">{{ loadingStatus ? '上传中...' : '请点击上传' }}</Button></div>
          <Input v-model="dataForm.mediaUrl" clearable />
        </FormItem>
        <FormItem label="状态:" prop="removed">
          <input type="radio" name="removedRadios" value="1" v-model="dataForm.removed"><label>下架</label>
          <input type="radio" name="removedRadios" value="0" v-model="dataForm.removed"><label>上架</label>
        </FormItem>
      </Form>
    </custom-modal>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'AlbumTrackEdit',
  data () {
    return {
      showModelEdit: false,
      loading: false,
      loadingStatus: false, // 上传文件状态
      id: '',
      dataForm: {
        title: '',
        titleImageUrl: '',
        imageUrl: '',
        mediaUrl: '',
        removed: '',
      },
      image: null,
      titleImage: null,
      video: null,
      // 表单验证
      dataRules: {
        title: [
          { required: true, message: '专辑标题不能为空', trigger: 'blur' }
        ],
        titleImageUrl: [
          { required: true, message: '标题图片不能为空', trigger: 'blur' }
        ],
        mediaUrl: [
          { required: true, message: '音频文件不能为空', trigger: 'blur' }
        ],
        removed: [
          { required: true, validator: this.$validate.checkNumber(), trigger: 'blur' }
        ]
      },
      modalTips: '单集编辑'
    }
  },
  methods: {
    ...mapActions([
      'albumTrackEdit',
      'uploadImage',
      'uploadMedia'
    ]),
    // 弹窗取消
    modalCancel () {
      console.log('点击了取消')
      this.showModelEdit = false
    },
    // 弹窗确定
    modalConfirm () {
      console.log('点击了确定')
      this.$refs.albumTrackEdit.validate((valid) => {
        if (valid) {
          this.albumTrackEdit({
            id: this.id,
            title: this.dataForm.title,
            imageUrl: this.dataForm.imageUrl,
            mediaUrl: this.dataForm.mediaUrl,
            titleImageUrl: this.dataForm.titleImageUrl,
            removed: this.dataForm.removed
          }).then((res) => {
            console.log(res)
            if (res.status === 1) {
              this.$Notice.success({
                title: '成功',
                desc: res.msg
              })
              this.$emit('reloadData')
              this.showModelEdit = false
            } else {
              this.$Notice.error({
                title: '错误',
                desc: res.msg
              })
            }
          }).catch(error => {
            this.$Notice.error({
              title: '错误',
              desc: '网络连接错误'
            })
            console.log(error)
          })
        } else {
          // 验证没通过的话
          this.$Notice.error({
            title: '错误',
            desc: '请通过数据验证'
          })
        }
      })
    },
    // 弹窗关闭
    modalRemove () {
      this.$refs.albumTrackEdit.resetFields()
      this.$emit('editClose')
    },
    handleImageUpload (file) {
      this.image = file
      return false
    },
    handleTitleImageUpload (file) {
      this.titleImage = file
      return false
    },
    handleMediaUpload (file) {
      this.media = file
      return false
    },
    // 上传函数，第一个字段为要上传的文件名称  第二个字段为回显的url赋值的字段
    upload (file, name) {
      this.loadingStatus = true
      const form = new FormData()
      form.append('file', file)
      this.uploadImage(form).then(res => {
        this.image = null
        this.titleImage = null
        this.loadingStatus = false
        if (res.status === 1) {
          this.$Notice.info({
            title: '成功',
            desc: '图片上传成功'
          })
          if (name === 'imageUrl') {
            this.dataForm.imageUrl = res.fields
          } else if (name === 'titleImageUrl') {
            this.dataForm.titleImageUrl = res.fields
          }
        } else {
          this.$Notice.error({
            title: '错误',
            desc: res.msg
          })
        }
      }).catch(error => {
        this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
        console.log(error)
      })
    },
    // 视频上传函数
    uploadAudio () {
      this.loadingStatus = true
      const form = new FormData()
      form.append('file', this.video)
      this.uploadMedia(form).then(res => {
        this.video = null
        this.loadingStatus = false
        if (res.status === 1) {
          this.$Notice.info({
            title: '成功',
            desc: '音频上传成功'
          })
          this.dataForm.mediaUrl = res.fields
        } else {
          this.$Notice.error({
            title: '错误',
            desc: res.msg
          })
        }
      }).catch(error => {
        this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
        console.log(error)
      })
    }
  },
  props: {
    modalShow: Boolean,
    albumTrack: Object
  },
  watch: {
    modalShow (val) {
      this.showModelEdit = val
      this.dataForm = this.albumTrack
      this.id = this.albumTrack.id
    }
  }
}
</script>

<style lang="less">

</style>
