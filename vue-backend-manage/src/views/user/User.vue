<!-- 用户管理 -->
<template>
  <div class="user-manage">
    <Tabs @on-click="queryUserByPagination">
      <TabPane label="用户管理" icon="ios-people" >
        <content-header>
          <Row slot="contentHeaderLeft">
            <Col span="5">
              <Input v-model="email" placeholder="按邮箱账号查找..." clearable />
            </Col>
            <Col span="6">
              <Input v-model="userIdArray" placeholder="按ID批量查询,逗号分隔..." clearable />
            </Col>
            <Col span="6">
              <datePicker v-model="registerDate" style="width: 180px" type="daterange" placement="bottom-start" placeholder="按注册时间查找..."></datePicker>
            </Col>
            <Col span="5">
              <Button type="primary" icon="ios-search" :loading="isSearching" @click="search"></Button>
              <Button type="warning" @click="handleReset">重置</Button>
            </Col>
          </Row>
          <div slot="contentHeaderRight">
            <Button type="primary" @click="handleUserAdd">添加</Button>
            <Button type="primary" @click="handleUserExport">导出</Button>
          </div>
        </content-header>
        <content-container>
          <Table height="360" border ref="selection" :columns="columns" :data="userList"></Table>
          <user-add :modalShow="showModelAdd"
                    @reloadData="queryUserByPagination"
                    @editClose="modelCancel"/>
          <user-edit :modalShow="showModelEdit"
                    :userId="userId"
                    @reloadData="queryUserByPagination"
                    @editClose="modelCancel"/>

        </content-container>
        <content-footer :total="total" :pageNum="pageNum" :pageSize="pageSize"></content-footer>
      </TabPane>
      <TabPane label="用户导入" icon="ios-document">
        <Row style="background:#eee;padding:20px">
          <Col span="11">
            <Card :bordered="false">
              <p slot="title">1. 请先下载并填写【导入模板】</p>
              <Button size="large" type="warning" @click="handleUserTemplate">导入模板</Button>
            </Card>
          </Col>
          <Col span="11" offset="2">
            <Card shadow>
              <p slot="title">2. 点击【选择文件】,确定无误后,再点击上传</p>
              <Upload :before-upload="handleUserExcel" action="">
                <Button icon="ios-cloud-upload-outline">选择文件</Button>
              </Upload>
              <div v-if="userExcel !== null">文件名: {{ userExcel.name }} <Button type="primary" @click="handleUserExcelUpload" :loading="loading">{{ loading ? '上传中...' : '请点击上传' }}</Button></div>
            </Card>
          </Col>
        </Row>
      </TabPane>
    </Tabs>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import UserAdd from './UserAdd'
import UserEdit from './UserEdit'
import axios from 'axios'
export default {
  name: 'User',
  components: {
    UserAdd,
    UserEdit
  },
  data () {
    return {
      showModelAdd: false,
      showModelEdit: false,
      userExcel: null, // 导入用户excel文件

      userId: 0, // 编辑所需的用户id
      email: '', // 账号,
      userIdArray: '', // 多个id，以逗号分隔,
      registerDate: '', // 注册时间
      registerStartTime: '', // 开始注册时间
      registerEndTime: '', // 结束注册时间
      isSearching: false, // 查找中
      total: 0,
      pageNum: 1,
      pageSize: 50,
      showUserDevice: false,
      deviceInfoList: [],
      modal_loading: false,
      loading: false, // 数据加载中
      columns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: 'ID',
          width: 60,
          align: 'center',
          key: 'id'
        },
        {
          title: '邮箱账号',
          width: 150,
          align: 'center',
          key: 'email'
        },
        {
          title: '昵称',
          width: 90,
          align: 'center',
          key: 'name'
        },
        {
          title: '年龄',
          width: 90,
          align: 'center',
          key: 'age'
        },
        {
          title: '性别',
          align: 'center',
          width: 90,
          key: 'name',
          render: (h, params) => {
            return h('div', [
              h('span', params.row.sex !== 0 ? '男' : '女')

            ])
          }
        },
        {
          title: '注册时间',
          align: 'center',
          key: 'registerDate'
        },
        {
          title: '操作',
          key: 'action',
          width: 220,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.handleUserEdit(params.row.id)
                  }
                }
              }, '编辑'),
              h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.changeUserRemoved(params.row.id, params.row.removed)
                  }
                }
              }, params.row.removed === 0 ? '启用' : '禁用'),
            ])
          }
        }
      ],
      userList: [],

    }
  },
  methods: {
    ...mapActions([
      'userPage',
      'addUser',
      'editUser',
      'userRemoved',
      'userExport',
      'userImportTemplate',
      'userImport'
    ]),
    // 重置查询参数
    handleReset () {
      // 各个查询条件置空
      this.email = ''
      this.userIdArray = ''
      this.registerDate = ''
    },
    // 处理翻页
    handlePage (value) {
      this.pageNum = value
      this.search()
    },
    // 初始查询函数
    search () {
      this.isSearching = true
      this.queryUserByPagination()
    },
    // 获取用户列表
    queryUserByPagination () {
      this.registerStartTime = ''
      this.registerEndTime = ''
      if (this.registerDate !== '' && this.registerDate[0] !== '') {
        this.registerStartTime = this.$dateFormat(this.registerDate[0], 'yyyy-MM-dd') + ' 00:00:00'
        this.registerEndTime = this.$dateFormat(this.registerDate[1], 'yyyy-MM-dd') + ' 00:00:00'
      }
      this.userPage({
        current: this.pageNum,
        pageSize: this.pageSize,
        email: this.email,
        userIdArray: this.userIdArray,
        startRegisterDate: this.registerStartTime,
        endRegisterDate: this.registerEndTime
      }).then((res) => {
        console.log(res)
        if (res.status === 1) {
          this.userList = res.fields.list
          this.total = res.fields.recordTotal
        } else {
          this.$Notice.error({
            title: '错误',
            desc: res.msg
          })
        }
        this.isSearching = false
      }).catch(error => {
        this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
        console.log(error)
      })
    },

    // 用户导出
    handleUserExport () {
      this.registerStartTime = ''
      this.registerEndTime = ''
      if (this.registerDate !== '' && this.registerDate[0] !== '') {
        this.registerStartTime = this.$dateFormat(this.registerDate[0], 'yyyy-MM-dd') + ' 00:00:00'
        this.registerEndTime = this.$dateFormat(this.registerDate[1], 'yyyy-MM-dd') + ' 00:00:00'
      }
      // axios({
      //   method: 'post',
      //   url: 'http://localhost:19090/exportUser',
      //   data: {
      //     email: this.email,
      //     userIdArray: this.userIdArray,
      //     startRegisterDate: this.registerStartTime,
      //     endRegisterDate: this.registerEndTime
      //   },
      //   responseType: 'blob'
      // }).then((res) => {
      //   console.log(res)
      //   const link = document.createElement('a')
      //   let blob = new Blob([res.data],{type: 'application/vnd.ms-excel'});
      //   link.style.display = 'none'
      //   link.href = URL.createObjectURL(blob);
      //   let num = ''
      //   for(let i=0;i < 10;i++){
      //     num += Math.ceil(Math.random() * 10)
      //   }
      //   link.setAttribute('download', '用户_' + num + '.xlsx')
      //   document.body.appendChild(link)
      //   link.click()
      //   document.body.removeChild(link)
      // }).catch(error => {
      //   this.$Notice.error({
      //     title: '错误',
      //     desc: '网络连接错误'
      //   })
      //   console.log(error)
      // })
      this.userExport({
        email: this.email,
        userIdArray: this.userIdArray,
        startRegisterDate: this.registerStartTime,
        endRegisterDate: this.registerEndTime
      }).then((res) => {
        const link = document.createElement('a')
        let blob = new Blob([res],{type: 'application/vnd.ms-excel'});
        link.style.display = 'none'
        link.href = URL.createObjectURL(blob);
        let num = ''
        for(let i=0;i < 10;i++){
            num += Math.ceil(Math.random() * 10)
        }
        link.setAttribute('download', '用户_' + num + '.xlsx')
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
      }).catch(error => {
        this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
        console.log(error)
      })
    },
    // 弹窗关闭
    modelCancel () {
      this.showModelAdd = false
      this.showModelEdit = false
    },
    // 添加用户
    handleUserAdd () {
      this.showModelAdd = true
    },
    // 编辑用户
    handleUserEdit (id) {
      this.showModelEdit = true
      this.userId = id
    },
    // 启用禁用用户
    changeUserRemoved (id, removed) {
      const msg = removed === 1 ? '您是否要继续启用' : '您是否要继续禁用'
      this.$confirm({
        title: '上下架确认',
        tips: msg,
        ok: () => {
          this.userRemoved({
            id: id,
            removed: 1 - removed
          }).then((res) => {
            if (res.status === 1) {
              this.$Modal.remove()
              this.queryUserByPagination()
              this.$Notice.success({
                title: '成功',
                desc: res.msg
              })
            } else {
              this.$Notice.error({
                title: '错误',
                desc: res.msg
              })
            }
            this.isSearching = false
          }).catch(error => {
            this.$Notice.error({
              title: '错误',
              desc: '网络连接错误'
            })
            console.log(error)
          })
        }
      })
    },
    // 下载用户导入模板
    handleUserTemplate () {
      this.userImportTemplate().then((res) => {
        const link = document.createElement('a')
        let blob = new Blob([res],{type: 'application/vnd.ms-excel'});
        link.style.display = 'none'
        link.href = URL.createObjectURL(blob);
        link.setAttribute('download', '用户导入模板.xlsx')
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
      }).catch(error => {
        this.$Notice.error({
          title: '错误',
          desc: '网络连接错误'
        })
        console.log(error)
      })
    },

    handleUserExcel (file) {
      this.userExcel = file
      return false
    },
    handleUserExcelUpload () {
      this.loading = true
      const form = new FormData()
      form.append('file', this.userExcel)
      this.userImport(form).then(res => {
        this.userExcel = null
        this.loading = false
        if (res.status === 1) {
          this.$Notice.success({
            title: '成功',
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
  mounted () {
    this.search()
  }
}
</script>

<style lang="less">
  .user-manage {
    width: 100%;
    height: auto;
  }
</style>
