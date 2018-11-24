<!-- 单集添加 -->
<template>
  <div class="user-add">
    <custom-modal :title="modalTips"
              :modalShow="showModelAdd"
              @modalRemove="modalRemove"
              @cancel="modalCancel"
              @confirm="modalConfirm">
      <Form ref="userAdd" :model="dataForm" :rules="dataRules" label-position="top" >
        <FormItem label="邮箱账号:" prop="email">
          <Input v-model="dataForm.email" :clearable="true" />
        </FormItem>
        <FormItem label="昵称:" prop="name">
          <Input v-model="dataForm.name"  clearable/>
        </FormItem>
        <FormItem label="密码:" prop="password">
          <Input type="password" v-model="dataForm.password"  clearable/>
        </FormItem>
        <FormItem label="确认密码:" prop="checkPassword">
          <Input type="password" v-model="dataForm.checkPassword"  clearable/>
        </FormItem>
        <FormItem label="年龄:" prop="age">
          <Input v-model="dataForm.age"  clearable/>
        </FormItem>
        <FormItem label="性别:" prop="sex">
          <RadioGroup v-model="dataForm.sex">
            <Radio label="1">男</Radio>
            <Radio label="0">女</Radio>
          </RadioGroup>
        </FormItem>
        <FormItem label="状态:" prop="removed">
          <RadioGroup v-model="dataForm.removed">
            <Radio label="1">禁用</Radio>
            <Radio label="0">启用</Radio>
          </RadioGroup>
        </FormItem>
      </Form>
    </custom-modal>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'UserAdd',
  data () {
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.dataForm.checkPassword !== '') {
          // 对第二个密码框单独验证
          this.$refs.userAdd.validateField('checkPassword');
        }
        callback();
      }
    };
    const validateCheckPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.dataForm.password) {
        callback(new Error('两次输入密码不匹配!'));
      } else {
        callback();
      }
    };
    return {
      showModelAdd: false,
      loading: false,
      albumId: '',
      dataForm: {
        email: '',
        name: '',
        password: '',
        checkPassword: '',
        age: '',
        sex: '',
        removed: ''
      },

      // 表单验证
      dataRules: {
        email: [
          { required: true, validator: this.$validate.checkEmail(), trigger: 'blur' }
        ],
        name: [
          { required: true, message: '用户昵称不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, validator: validatePassword, trigger: 'blur' }
        ],
        checkPassword: [
          { required: true, trigger: 'blur' , validator: validateCheckPassword}
        ],
        age: [
          { required: true, validator: this.$validate.checkNumber('年龄不能为空', '年龄应为正整数'), trigger: 'blur' }
        ],
        sex: [
          { required: true,validator: this.$validate.checkNumber('请选择性别'), trigger: 'blur' }
        ],
        removed: [
          { required: true, validator: this.$validate.checkNumber('请选择用户状态'), trigger: 'blur' }
        ]
      },
      modalTips: '添加用户',
    }
  },
  methods: {
    ...mapActions([
      'addUser',
    ]),
    // 弹窗取消
    modalCancel () {
      console.log('点击了取消')
      this.showModelAdd = false
    },
    // 弹窗确定
    modalConfirm () {
      console.log('点击了确定')
      this.$refs.userAdd.validate((valid) => {
        if (valid) {
          this.addUser({
            email: this.dataForm.email,
            name: this.dataForm.name,
            password: this.dataForm.password,
            sex: this.dataForm.sex,
            age: this.dataForm.age,
            removed: this.dataForm.removed,
          }).then((res) => {
            console.log(res)
            if (res.status === 1) {
              this.$Notice.success({
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
      this.$refs.userAdd.resetFields()
      this.$emit('editClose')
    },
  },
  props: {
    modalShow: Boolean,
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
