<!-- 专辑管理 -->
<template>
  <div class="album-manage">
    <content-header>
        <Row slot="contentHeaderLeft">
          <Col span="6">
            <Input v-model="id" placeholder="按专辑ID查找..." clearable />
          </Col>
          <Col span="6">
            <Input v-model="albumName" placeholder="按专辑名称查找..." clearable />
          </Col>
          <Col span="6">
            <Input v-model="anchor" placeholder="按播主查找..." clearable />
          </Col>
          <Col span="2">
            <Button type="primary" icon="ios-search" :loading="isSearching" @click="search"></Button>
          </Col>
        </Row>
      <div slot="contentHeaderRight">
        <Button type="primary" @click="albumAdd">新增</Button>
        <Button type="warning"  @click="handleReset">重置</Button>
      </div>
    </content-header>
    <content-container>
      <Card :bordered="false">
        <Table height="360" border ref="selection" :columns="columns" :data="albumList"></Table>
        <AlbumAdd :modalShow="showModelAdd"
                  @reloadData="queryAlbumByPagination"
                  @editClose="modelCancel"/>
        <AlbumEdit :modalShow="showModelEdit"
                   :albumId="albumId"
                   @reloadData="queryAlbumByPagination"
                   @editClose="modelCancel"/>
      </Card>
    </content-container>
    <content-footer :total="total" :pageNum="pageNum" :pageSize="pageSize"></content-footer>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import AlbumExpand from './AlbumExpand'
import AlbumEdit from './AlbumEdit'
import AlbumAdd from './AlbumAdd'
export default {
  name: 'Album',
  components: {
    AlbumExpand,
    AlbumEdit,
    AlbumAdd
  },
  data () {
    return {
      formSelect: {},
      id: '', // id
      albumName: '', // 专辑名称
      anchor: '', // 播主
      isSearching: false, // 查找中
      total: 0,
      pageNum: 1,
      pageSize: 50,
      showModelAdd: false, // 是否展示弹窗新增
      showModelEdit: false, // 是否展示弹窗编辑
      loading: false, // 数据加载中
      columns: [
        {
          type: 'expand',
          width: 50,
          render: (h, params) => {
            return h(AlbumExpand, {
              props: {
                row: params.row
              }
            })
          }
        },
        {
          type: 'selection',
          width: 50,
          align: 'center'
        },
        {
          title: 'ID',
          align: 'center',
          key: 'id'
        },
        {
          title: '专辑标题',
          align: 'center',
          width: 100,
          key: 'title'
        },
        {
          title: '分类',
          align: 'center',
          key: 'categoryName'
        },
        {
          title: '播主',
          align: 'center',
          key: 'anchor'
        },
        {
          title: '国家',
          align: 'center',
          key: 'nation'
        },
        {
          title: '订阅数',
          align: 'center',
          key: 'subscriptionCount'
        },
        {
          title: '播放数',
          align: 'center',
          key: 'playedCount'
        },
        {
          title: '热度',
          align: 'center',
          key: 'hot'
        },
        {
          title: '操作',
          key: 'action',
          width: 220,
          align: 'center',
          // fixed: 'right',
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
                    this.albumEdit(params.row)
                  }
                }
              }, '编辑'),
              h('Button', {
                // value: params.row.removed === 1 ? '上架' : '下架',
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.changeAlbumStatus(params.row.id, params.row.removed)
                  }
                }
              }, params.row.removed === 0 ? '上架' : '下架'),
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                on: {
                  click: () => {
                    this.$router.push({
                      path: 'albumTrackManage',
                      query: {
                        id: params.row.id
                      }
                    })
                  }
                }
              }, '管理单集')
            ])
          }
        }
      ],
      albumList: [],
      // 当前用户
      albumId: null,
      album: {}
    }
  },
  methods: {
    ...mapActions([
      'albumPage',
      'albumStatus'
    ]),
    handleReset () {
      // 各个查询条件置空
      this.id = ''
      this.albumName = ''
      this.anchor = ''
    },
    handlePage (value) {
      this.pageNum = value
      this.search()
    },
    search () {
      this.isSearching = true
      this.queryAlbumByPagination()
    },
    // 获取用户列表
    queryAlbumByPagination () {
      this.albumPage({
        current: this.pageNum,
        pageSize: this.pageSize,
        id: this.id,
        albumTitle: this.albumName,
        anchor: this.anchor
      }).then((res) => {
        console.log(res)
        if (res.status === 1) {
          this.albumList = res.fields.list
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
    // 专辑新增
    albumAdd () {
      this.showModelAdd = true
    },
    // 专辑编辑
    albumEdit (album) {
      this.albumId = album.id
      this.showModelEdit = true
    },
    changeAlbumStatus (id, removed) {
      const msg = removed === 1 ? '您是否要继续上架' : '您是否要继续下架'
      this.$confirm({
        title: '上下架确认',
        tips: msg,
        ok: () => {
          this.albumStatus({
            id: id,
            status: 1 - removed
          }).then((res) => {
            if (res.status === 1) {
              this.$Modal.remove()
              this.queryAlbumByPagination()
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
    // 弹窗关闭
    modelCancel () {
      this.showModelAdd = false
      this.showModelEdit = false
    }
  },
  mounted () {
    this.search()
  }
}
</script>

<style lang="less">
  .album-manage {
    width: 100%;
    height: auto;
  }
</style>
