<!-- 专辑编辑 -->
<template>
  <div class="user-edit">
    <custom-modal :title="modalTips"
              :modalShow="showModelEdit"
              @modalRemove="modalRemove"
              @cancel="modalCancel"
              @confirm="modalConfirm">
      <Button shape="circle" type="primary" icon="ios-create-outline" class="float-right edit-top" size="small" ></Button>
      <Form ref="userEdit" :model="dataForm" :rules="dataRules" label-position="top" >
        <FormItem label="邮箱账号:">
          <Input v-model="dataForm.email" disabled />
        </FormItem>
        <FormItem label="昵称:" prop="name">
          <Input v-model="dataForm.name"  clearable/>
        </FormItem>
        <FormItem label="年龄:" prop="age">
          <Input v-model="dataForm.age"  clearable/>
        </FormItem>
        <FormItem label="性别:" prop="sex">
          <RadioGroup v-model="dataForm.sex">
            <Radio :label="1">男</Radio>
            <Radio :label="0">女</Radio>
          </RadioGroup>
        </FormItem>
      </Form>
    </custom-modal>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'
  export default {
    name: 'UserEdit',
    data () {
      return {
        id: '',
        showModelEdit: false,
        dataForm: {
          email: '',
          name: '',
          sex: '',
          age: '',
        },

        // 表单验证
        dataRules: {
          name: [
            { required: true, message: '用户昵称不能为空', trigger: 'blur' }
          ],
          age: [
            { required: true, validator: this.$validate.checkNumber('年龄不能为空', '年龄应为正整数'), trigger: 'blur' }
          ],
          sex: [
            { required: true,validator: this.$validate.checkNumber('请选择性别'), trigger: 'blur' }
          ]
        },
        modalTips: '用户编辑'
      }
    },
    methods: {
      ...mapActions([
        'editUser',
        'getUser',
      ]),
      handleGetUser () {
        this.getUser({
          id: this.id
        }).then((res) => {
          if (res.status === 1) {
            this.dataForm =  res.fields
            console.log('userEdit#dataForm: ', res.fields)
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
        this.showModelEdit = false
      },
      // 弹窗确定
      modalConfirm () {
        console.log('点击了确定')
        this.$refs.userEdit.validate((valid) => {
          if (valid) {
            this.editUser({
              id: this.id,
              name: this.dataForm.name,
              sex: this.dataForm.sex,
              age: this.dataForm.age,
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
        this.$refs.userEdit.resetFields()
        this.$emit('editClose')
      }
    },
    props: {
      modalShow: Boolean,
      userId: Number
    },
    watch: {
      modalShow (val) {
        this.showModelEdit = val
        this.id = this.userId
        this.handleGetUser()
      }
    }
  }
</script>

<style lang="less">

</style>
