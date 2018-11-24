<!-- 专辑添加 -->
<template>
  <div class="album-category-add">
    <custom-modal :title="modalTips"
              :modalShow="showModelAdd"
              @modalRemove="modalRemove"
              @cancel="modalCancel"
              @confirm="modalConfirm">
      <Form ref="albumCategoryAdd" :model="dataForm" :rules="dataRules" label-position="top" >
      <FormItem label="分类名称:" prop="categoryName">
        <Input v-model="dataForm.categoryName" :clearable="true"  />
      </FormItem>
      <FormItem label="上级分类:" prop="parentId">
        <album-category-tree-select ref="categoryTreeSelect" :parentId="parentId" :removed="-1" :clean="clean" v-on:getParentCategoryId="getParentCategoryId"></album-category-tree-select>
      </FormItem>
      <FormItem label="优先级:" prop="rank">
        <Input v-model="dataForm.rank"  />
      </FormItem>
      <FormItem label="分类图片:" prop="imageUrl">
        <Upload :before-upload="handleTitleImageUpload" action="">
          <Button icon="ios-cloud-upload-outline">图片上传</Button>
        </Upload>
        <div v-if="file !== null">图片名: {{ file.name }} <Button type="success" @click="upload" :loading="loadingStatus">{{ loadingStatus ? '上传中...' : '请点击上传' }}</Button></div>
        <Input v-model="dataForm.imageUrl"  />
      </FormItem>
      <FormItem label="分类描述:" prop="desc">
        <Input v-model="dataForm.desc" clearable />
      </FormItem>
    </Form>
    </custom-modal>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'AlbumCategoryAdd',
  data () {
    return {
      showModelAdd: false,
      loading: false,
      parentId: this.categoryParentId,
      dataForm: {
        categoryName: '',
        rank: '',
        imageUrl: '',
        desc: '',
      },
      file: null,  // 图片对象
      loadingStatus: false,
      clean: false,
      // 表单验证
      dataRules: {
        categoryName: [
          { required: true, message: '专辑名称不能为空', trigger: 'blur' }
        ],
        rank: [
          {validator: this.$validate.checkNumber('优先级不能为空'), trigger: 'blur' }
        ],
        imageUrl: [
          { required: true, message: '分类图片不能为空', trigger: 'blur' }
        ]
      },
      modalTips: '专辑分类添加'
    }
  },
  methods: {
    ...mapActions([
      'albumCategoryAdd',
      'uploadImage'
    ]),
    // 弹窗取消
    modalCancel () {
      console.log('点击了取消')
      // this.$emit('reloadData')
      this.showModelAdd = false
    },
    // 弹窗确定
    modalConfirm () {
      console.log('点击了确定')
      this.$refs.albumCategoryAdd.validate((valid) => {
        if (valid) {
          this.albumCategoryAdd({
            categoryName: this.dataForm.categoryName,
            parentId: this.parentId,
            rank: this.dataForm.rank,
            imageUrl: this.dataForm.imageUrl,
            desc: this.dataForm.desc
          }).then((res) => {
            console.log(res)
            if (res.status === 1) {
              this.$Notice.info({
                title: '成功',
                desc: res.msg
              })
              this.clean = true
              // this.$router.push('albumCategory')
              this.$emit('reloadData')
              // this.$refs.categoryTreeSelect.cleanOption()
              this.showModelAdd = false
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
      console.log('categoryEdit 执行 modalRemove')
      this.$refs.albumCategoryAdd.resetFields()
      this.parentId = 0
      this.$emit('editClose')
    },
    handleTitleImageUpload (file) {
      this.file = file
      return false
    },
    // 上传图片函数
    upload () {
      this.loadingStatus = true
      const form = new FormData()
      form.append('file', this.file)
      this.uploadImage(form).then(res => {
        this.file = null
        this.loadingStatus = false
        if (res.status === 1) {
          this.$Notice.info({
            title: '成功',
            desc: '图片上传成功'
          })
          this.dataForm.imageUrl = res.fields
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
    // 监听分类下拉树组件传回的值
    getParentCategoryId (value) {
      this.parentId = value
    }
  },
  props: {
    modalShow: Boolean,
    categoryParentId: Number
  },
  watch: {
    modalShow (val) {
      this.showModelAdd = val
    },
    categoryParentId(val) {
      this.parentId = val
    }
  }
}
</script>

<style lang="less">

</style>
