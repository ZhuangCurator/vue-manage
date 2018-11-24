<!-- 专辑管理 -->
<template>
  <div class="album-track-manage">
    <content-header>
      <Row slot="contentHeaderLeft">
        <Col span="6">
          <Input v-model="id" placeholder="按单集ID查找..." clearable />
        </Col>
        <Col span="6">
          <Input v-model="title" placeholder="按单集标题查找..." clearable />
        </Col>
        <Col span="5">
            <Button type="primary" icon="ios-search" :loading="isSearching" @click="search"></Button>
            <Button type="warning"  @click="handleReset()">重置</Button>
        </Col>
      </Row>
      <div slot="contentHeaderRight">
        <Button type="primary" @click="albumTrackAdd">新建单集</Button>
        <Button type="warning"  :disabled="disabled"  @click="handleBatchChangeStatus(0)">批量上架</Button>
        <Button type="warning"  :disabled="disabled"  @click="handleBatchChangeStatus(1)">批量下架</Button>
      </div>
    </content-header>
    <content-container>
      <Card :bordered="false">
        <Table height="360" border ref="selection" :columns="columns" :data="albumTrackList" :empty-text="emptyText"
               @on-selection-change="handleSelectChange" ></Table>
        <AlbumTrackAdd :modalShow="showModelAdd"
                       :album="albumId"
                  @reloadData="queryAlbumTrackByPagination"
                  @editClose="modelCancel"/>
        <AlbumTrackEdit :modalShow="showModelEdit"
                   :albumTrack="albumTrack"
                   @reloadData="queryAlbumTrackByPagination"
                   @editClose="modelCancel"/>
      </Card>
    </content-container>
    <content-footer :total="total" :pageNum="pageNum" :pageSize="pageSize"></content-footer>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import TrackExpand from './AlbumTrackExpand'
import AlbumTrackAdd from './AlbumTrackAdd'
import AlbumTrackEdit from './AlbumTrackEdit'
export default {
  name: 'AlbumTrack',
  components: {
    TrackExpand,
    AlbumTrackAdd,
    AlbumTrackEdit
  },
  data () {
    return {
      disabled: true, // 控制批量上下架的两个按钮是否可以使用
      formSelection: [], // 全选或者单选返回的selection
      albumId: 0,
      id: '', // id
      title: '', // 播主
      isSearching: false, // 查找中
      total: 0,
      pageNum: 1,
      pageSize: 50,
      showModelAdd: false, // 是否展示弹窗(新增或编辑)
      showModelEdit: false, // 是否展示弹窗(新增或编辑)
      loading: false, // 数据加载中
      columns: [
        {
          type: 'expand',
          width: 50,
          render: (h, params) => {
            return h(TrackExpand, {
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
          width: 100,
          align: 'center',
          key: 'id'
        },
        {
          title: '单集标题',
          align: 'center',
          width: 250,
          key: 'title'
        },
        {
          title: '播放次数',
          align: 'center',
          width: 100,
          key: 'playedCount'
        },
        {
          title: '更新时间',
          align: 'center',
          key: 'updateTime'
        },
        {
          title: '操作',
          key: 'action',
          width: 240,
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
                    this.albumTrackEdit(params.row)
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
                    this.changeAlbumTrackStatus(params.row.id, params.row.removed)
                  }
                }
              }, params.row.removed === 0 ? '上架' : '下架')
            ])
          }
        }
      ],
      albumTrackList: [],
      albumTrack: {},
      emptyText: '您查询的数据为空'
    }
  },
  methods: {
    ...mapActions([
      'albumTrackPage',
      'albumTrackStatus',
      'albumTrackBatchStatus'
    ]),
    // 各个查询条件置空
    handleReset () {
      this.id = ''
      this.title = ''
    },
    // 分页组件跳转查询
    handlePage (value) {
      this.pageNum = value
      this.search()
    },
    search () {
      this.isSearching = true
      this.queryAlbumTrackByPagination()
    },
    // 获取用户列表
    queryAlbumTrackByPagination () {
      this.disabled = true
      this.albumTrackPage({
        current: this.pageNum,
        pageSize: this.PageSize,
        id: this.id,
        albumId: this.albumId,
        title: this.title
      }).then((res) => {
        console.log(res)
        if (res.status === 1) {
          this.albumTrackList = res.fields.list
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
    // 单集新增
    albumTrackAdd () {
      this.showModelAdd = true
    },
    // 单集编辑
    albumTrackEdit (albumTrack) {
      this.albumTrack = albumTrack
      this.showModelEdit = true
    },
    // 上下架操作
    changeAlbumTrackStatus (id, removed) {
      const msg = removed === 1 ? '您是否要继续上架' : '您是否要继续下架'
      this.$confirm({
        title: '上下架确认',
        tips: msg,
        ok: () => {
          this.albumTrackStatus({
            id: id,
            status: 1 - removed
          }).then((res) => {
            if (res.status === 1) {
              this.$Modal.remove()
              this.queryAlbumTrackByPagination()
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
    },
    // 批量上下架操作
    handleBatchChangeStatus (status) {
      const msg = status === 0 ? '您是否要继续批量上架' : '您是否要继续批量下架'
      const idList = []
      this.selection.forEach(data => {
        idList.push(data.id)
      })
      console.log(idList)
      this.$confirm({
        title: '批量上下架确认',
        tips: msg,
        ok: () => {
          this.albumTrackBatchStatus({
            idList: idList,
            status: status
          }).then((res) => {
            if (res.status === 1) {
              this.$Notice.error({
                title: '成功',
                desc: res.msg
              })
              this.$Modal.remove()
              this.queryAlbumTrackByPagination()
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
    // 全选或者单选触发事件
    handleSelectChange (selection) {
      this.selection = selection
      if (selection.length > 0) {
        this.disabled = false
      } else {
        this.disabled = true
      }
      console.log('选项改变selection:', selection)
    }
  },
  mounted () {
    this.albumId = this.$route.query.id
    this.search()
  }
}
</script>

<style lang="less">

</style>
