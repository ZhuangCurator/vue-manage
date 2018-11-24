<template>
  <Card class="album-category">
    <div >
      <div class="album-category-title">
        <div class="category-name">
          <span >分类名称</span>
        </div>
        <div class="category-rank">
          <span>优先级</span>
        </div>
        <div class="category-image">
          <span>分类图片</span>
        </div>
        <div class="category-operate">
          <span>操作</span>
        </div>
      </div>
      <div class="album-category-content">
        <Tree :data="treeData" :load-data="loadData" :render="renderContent"></Tree>
      </div>
      <AlbumCategoryEdit :modalShow="showModelEdit"
                         :albumCategory="albumCategory"
                         @reloadData="queryCategoryList"
                         @editClose="modelCancel"/>
      <AlbumCategoryAdd :modalShow="showModelAdd"
                        :categoryParentId="parentId"
                        @reloadData="queryCategoryList"
                        @editClose="modelCancel"/>
    </div>
  </Card>
</template>
<script>
import { mapActions } from 'vuex'
import AlbumCategoryEdit from './AlbumCategoryEdit'
import AlbumCategoryAdd from './AlbumCategoryAdd'
export default {
  name: 'AlbumCategory',
  components: {AlbumCategoryEdit, AlbumCategoryAdd},
  data () {
    return {
      treeData: [
      ],
      categoryList: {
        type: Array
      },
      showModelEdit: false, // 展示编辑弹窗
      showModelAdd: false, // 展示添加弹窗
      albumCategory: {}, // 编辑的数据
      parentId: 0 // 给当前分类添加子分类
    }
  },
  methods: {
    ...mapActions([
      'albumCategoryList',
      'albumCategoryStatus'
    ]),
    loadData (item, callback) {
      console.log('item: ', item)
      let parentId = item.id || 0
      let data = []
      this.albumCategoryList({
        parentId: parentId
      }).then((res) => {
        console.log(res.fields)
        if (res.status === 1) {
          data = this.getTree(res.fields)
          console.log('loadData#data: ', data)
          callback(data)
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
    getTree (tree = []) {
      console.log('getTree:#tree:', tree)
      let arr = [];
      if (tree.length !== 0) {
        tree.forEach(item => {
          let obj = {};
          obj.categoryName = item.categoryName;
          obj.id = item.id; // 其他你想要添加的属性
          obj.removed = item.removed; // 其他你想要添加的属性
          obj.rank = item.rank; // 其他你想要添加的属性
          obj.imageUrl = item.imageUrl; // 其他你想要添加的属性
          obj.desc = item.desc; // 其他你想要添加的属性
          obj.parentId = item.parentId; // 其他你想要添加的属性
          if(item.child === 1) {
            obj.children = [];
            obj.loading = false;
          }
          arr.push(obj);
        });
      }
      console.log('getTree#arr: ', arr)
      return arr
    },
    // 页面加载时 查询parentID=0 的分类
    queryCategoryList (parentId = 0) {
      console.log('执行专辑分类queryCategoryList')
      this.albumCategoryList({
        parentId: parentId
      }).then((res) => {
        console.log(res.fields)
        if (res.status === 1) {
          this.treeData = this.getTree(res.fields)
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

    renderContent (h, { root, node, data }) {
      return h('span', {
        style: {
          display: 'inline-block',
          width: '100%'
        }
      }, [
        h('span',[
          h('Icon', {
            style: {
              marginRight: '8px'
            }
          }),
          h('span', data.categoryName)
        ]),
        h('span', {
          style: {
            display: 'inline-block',
            float: 'right',
            marginRight: '32px'
          }
        }, [
          h('span', {
            style: {
              display: 'inline-block',
              'text-align': 'center',
              width: '25px',
              marginRight: '40px'
            }
          }, data.rank),
          h('span', {
            style: {
              display: 'inline-block',
              width: '480px',
              marginRight: '20px'
            }
          }, data.imageUrl || ''),
          h('Button', {
            props: Object.assign({},  {
              type: 'primary',
              size: 'small',
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => { this.albumCategoryAdd(data) }
            }
          }, '添加'),
          h('Button', {
            props: Object.assign({},  {
              type: 'primary',
              size: 'small',
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => { this.albumCategoryEdit(data) }
            }
          }, '编辑'),
          h('Button', {
            props: Object.assign({},  {
              type: 'error',
              size: 'small',
            }),
            on: {
              click: () => { this.changeAlbumCategoryStatus(data.id, data.removed) }
            }
          }, data.removed === 0 ? '启用' : '停用' )
        ])
      ]);
    },

    changeAlbumCategoryStatus (id, removed) {
      const msg = removed === 1 ? '您是否要继续启用' : '您是否要继续停用'
      this.$confirm({
        title: '启用停用确认',
        tips: msg,
        ok: () => {
          this.albumCategoryStatus({
            id: id,
            status: 1 - removed
          }).then((res) => {
            if (res.status === 1) {
              this.$Modal.remove()
              this.queryCategoryList()
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
    // 分类编辑
    albumCategoryEdit (albumCategory) {
      this.albumCategory = albumCategory
      this.showModelEdit = true
    },
    // 分类添加
    albumCategoryAdd (albumCategory) {
      this.parentId = albumCategory.id
      this.showModelAdd = true
    },
    // 弹窗关闭
    modelCancel () {
      this.showModelAdd = false
      this.showModelEdit = false
      this.albumCategory = {}
    },
  }
 ,
  mounted () {
    this.queryCategoryList()
  }
}
</script>

<style lang="less">
  @import 'AlbumCategory.less';
</style>
