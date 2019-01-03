<!-- 专辑添加 -->
<template>
  <div class="AlbumAdd">
    <custom-modal :title="modalTips"
              :width="1024"
              :modalShow="showModelAdd"
              @modalRemove="modalRemove"
              @cancel="modalCancel"
              @confirm="modalConfirm">
      <Form ref="albumAdd" :model="dataForm" :rules="dataRules" label-position="top" >
        <FormItem label="专辑标题:" prop="title">
          <Input v-model="dataForm.title" :clearable="true"  />
        </FormItem>
        <FormItem label="分类:" prop="categoryId">
          <album-category-tree-select ref="categoryTreeSelect" :parentId="0"  :removed="0"  v-on:getParentCategoryId="getCategoryId"></album-category-tree-select>
        </FormItem>
        <FormItem label="播主:" prop="anchor">
          <Input v-model="dataForm.anchor"  />
        </FormItem>
        <FormItem label="作者:" prop="author">
          <Input v-model="dataForm.author"  />
        </FormItem>
        <FormItem label="国家:" prop="nation">
          <Input v-model="dataForm.nation" clearable />
        </FormItem>
        <FormItem label="订阅数:" prop="subscriptionCount">
          <Input v-model="dataForm.subscriptionCount" clearable />
        </FormItem>
        <FormItem label="播放数:" prop="playedCount">
          <Input v-model="dataForm.playedCount" clearable />
        </FormItem>
        <FormItem label="热度:" prop="hot">
          <Input v-model="dataForm.hot" clearable />
        </FormItem>
        <FormItem label="rss地址:" prop="rssAddress">
          <Input v-model="dataForm.rssAddress" clearable />
        </FormItem>
        <FormItem label="专辑封面:" prop="coverImageUrl">
          <Upload :before-upload="handleCoverImageUpload" action="">
            <Button icon="ios-cloud-upload-outline">图片上传</Button>
          </Upload>
          <div v-if="coverImage !== null">图片名: {{ coverImage.name }} <Button type="primary" @click="upload(coverImage, 'coverImageUrl')" :loading="loadingStatus">{{ loadingStatus ? '上传中...' : '请点击上传' }}</Button></div>
          <Input v-model="dataForm.coverImageUrl" clearable />
        </FormItem>
        <FormItem label="标题图片:" prop="titleImageUrl">
          <Upload :before-upload="handleTitleImageUpload" action="">
            <Button icon="ios-cloud-upload-outline">图片上传</Button>
          </Upload>
          <div v-if="titleImage !== null">图片名: {{ titleImage.name }} <Button type="primary" @click="upload(titleImage, 'titleImageUrl')" :loading="loadingStatus">{{ loadingStatus ? '上传中...' : '请点击上传' }}</Button></div>
          <Input v-model="dataForm.titleImageUrl" clearable />
        </FormItem>
        <FormItem label="状态:" prop="removed">
          <input type="radio" name="removedRadios" value="1" v-model="dataForm.removed"><label>下架</label>
          <input type="radio" name="removedRadios" value="0" v-model="dataForm.removed"><label>上架</label>
        </FormItem>
        <FormItem label="简介:">
          <vue-ueditor-wrap v-model="dataForm.summary" :config="myConfig"></vue-ueditor-wrap>
        </FormItem>
        <FormItem label="描述:">
          <vue-ueditor-wrap v-model="dataForm.description" :config="myConfig"></vue-ueditor-wrap>
        </FormItem>
      </Form>
    </custom-modal>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'
  import VueUeditorWrap from 'vue-ueditor-wrap'
  export default {
    name: 'AlbumAdd',
    components: {VueUeditorWrap},
    data () {
      return {
        myConfig: {
          // 如果需要上传功能,找后端小伙伴要服务器接口地址
          serverUrl: this.$config.baseUrl + 'ueditor/ueditorConfig',
          // serverUrl: 'http://localhost:8090/ueditor/ueditorConfig',
          // 你的UEditor资源存放的路径,相对于打包后的index.html
          UEDITOR_HOME_URL: '/ueditor/',
          // 编辑器不自动被内容撑高
          autoHeightEnabled: false,
          // 工具栏是否可以浮动
          autoFloatEnabled: false,
          // 初始容器高度
          initialFrameHeight: 340,
          // 初始容器宽度
          initialFrameWidth: '100%',
          // 关闭自动保存
          enableAutoSave: true
        },
        coverImage: null,
        titleImage: null,
        loadingStatus: false,
        showModelAdd: false,
        loading: false,
        dataForm: {
          title: '',
          categoryId: '',
          anchor: '',
          author: '',
          nation: '',
          subscriptionCount: '',
          playedCount: '',
          hot: '',
          rssAddress: '',
          coverImageUrl: '',
          titleImageUrl: '',
          removed: '',
          summary: '',
          description: '',
        },

        // 表单验证
        dataRules: {
          title: [
            { required: true, message: '专辑标题不能为空', trigger: 'blur' }
          ],
          categoryId: [
            { required: true, validator: this.$validate.checkNumber('分类不能为空'), trigger: 'blur' }
          ],
          anchor: [
            { required: true, message: '播主不能为空', trigger: 'blur' }
          ],
          nation: [
            { required: true, message: '国家不能为空', trigger: 'blur' }
          ],
          subscriptionCount: [
            { required: true, validator: this.$validate.checkNumber('订阅数不能为空'), trigger: 'blur' }
          ],
          playedCount: [
            { required: true, validator: this.$validate.checkNumber('播放数不能为空'), trigger: 'blur' }
          ],
          hot: [
            { required: true, validator: this.$validate.checkNumber('热度不能为空'), trigger: 'blur' }
          ],
          coverImageUrl: [
            { required: true, message: '专辑封面不能为空', trigger: 'blur' }
          ],
          removed: [
            { required: true, validator: this.$validate.checkNumber('状态不能为空'), trigger: 'blur' }
          ]

        },
        modalTips: '专辑添加'
      }
    },
    methods: {
      ...mapActions([
        'albumAdd',
        'uploadImage',
        'getRegion'
      ]),
      handleCoverImageUpload (file) {
        this.coverImage = file
        return false
      },
      handleTitleImageUpload (file) {
        this.titleImage = file
        return false
      },
      // 监听分类下拉树组件传回的值
      getCategoryId (value) {
        this.dataForm.categoryId = value
      },
      // 上传函数，第一个字段为要上传的文件名称  第二个字段为回显的url赋值的字段
      upload (file, name) {
        this.loadingStatus = true
        const form = new FormData()
        form.append('file', file)
        this.uploadImage(form).then(res => {
          this.coverImage = null
          this.titleImage = null
          this.loadingStatus = false
          if (res.status === 1) {
            this.$Notice.info({
              title: '成功',
              desc: '图片上传成功'
            })
            if (name === 'coverImageUrl') {
              this.dataForm.coverImageUrl = res.fields
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
      // 弹窗取消
      modalCancel () {
        console.log('点击了取消')
        this.showModelAdd = false
      },
      // 弹窗确定
      modalConfirm () {
        console.log('点击了确定')
        console.log('summary: ',this.summary)
        console.log('description: ',this.description)
        this.$refs.albumAdd.validate((valid) => {
          if (valid) {
            this.albumAdd({
              title: this.dataForm.title,
              categoryId: this.dataForm.categoryId,
              anchor: this.dataForm.anchor,
              author: this.dataForm.author,
              nation: this.dataForm.nation,
              subscriptionCount: this.dataForm.subscriptionCount,
              playedCount: this.dataForm.playedCount,
              hot: this.dataForm.hot,
              rssAddress: this.dataForm.rssAddress,
              coverImageUrl: this.dataForm.coverImageUrl,
              titleImageUrl: this.dataForm.titleImageUrl,
              removed: this.dataForm.removed,
              summary: this.dataForm.summary,
              description: this.dataForm.description
            }).then((res) => {
              console.log(res)
              if (res.status === 1) {
                this.$Notice.info({
                  title: '成功',
                  desc: res.msg
                })
                this.$emit('reloadData')
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
        this.$refs.albumAdd.resetFields()
        this.$emit('editClose')
      }
    },
    props: {
      modalShow: Boolean,
      albumId: Number
    },
    watch: {
      modalShow (val) {
        this.showModelAdd = val
      }
    }
  }
</script>

<style lang="less">

</style>
