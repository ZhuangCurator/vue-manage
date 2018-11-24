<template>
  <div>
    <tree-select
      :options="options"
      placeholder="请选择分类..."
      v-model="value"
    />
  </div>

</template>

<script>
  import { mapActions } from 'vuex'
  import Vue from 'vue'
  // import the component
  import TreeSelect from '@riophae/vue-treeselect'
  // import the styles
  import '@riophae/vue-treeselect/dist/vue-treeselect.css'

  import { LOAD_CHILDREN_OPTIONS } from '@riophae/vue-treeselect'


  export default {
    name: 'AlbumCategoryTreeSelect',
    components: { TreeSelect },
    data() {
      return {
        value: this.parentId || 0,
        options: [{
          id: 0,
          label: '顶级分类',
          children: []

        }],
      }
    },
    props: {
      parentId: {
        type: Number,
        default: 0
      },
      removed: {
        type: Number
      },
      clean: {
        type: Boolean
      }
    },
    methods: {
      ...mapActions([
        'albumCategoryList'
      ]),

      queryCategoryList () {
        console.log('执行treeSelect#queryCategoryList')
        this.albumCategoryList({
          parentId: 0,
          removed: this.removed,
          all: 1
        }).then((res) => {
          console.log(res.fields)
          if (res.status === 1) {
            let result = []
            const children = this.getTree(res.fields)
            let obj = {}
            obj.label = '顶级分类'
            obj.id = 0
            obj.children = children
            result.push(obj)
            console.log('执行下拉树查找：', obj)
            Vue.set(this.options,0,obj)
            // this.options = result
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
            obj.label = item.categoryName;
            obj.id = item.id;
            if(item.child === 1) {
              obj.children = this.getTree(item.children);
            }
            arr.push(obj);
          });
        }
        console.log('getTree#arr: ', arr)
        return arr
      },
    },
    watch: {
      // 将选择的分类id传给父组件
      value(curVal) {
        this.$emit('getParentCategoryId', curVal)
      },
      parentId(val) {
        this.value = val
      },
      clean(val) {
        console.log('执行clean')
        if(val) {
          this.queryCategoryList()
        }
      }
    },
    mounted () {
      this.queryCategoryList()
    }
  }


</script>
