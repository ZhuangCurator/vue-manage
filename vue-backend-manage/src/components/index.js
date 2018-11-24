/**
 * @description: 自定义全局组件
 */
import customModal from './custom-modal'
import albumCategoryTreeSelect from './album-category-tree-select'

import contentHeader from './content/content-header'
import contentFooter from './content/content-footer'
import contentContainer from './content/content-container'

const components = {
  install: function (Vue) {
    Vue.component('customModal', customModal)
    Vue.component('albumCategoryTreeSelect', albumCategoryTreeSelect)

    Vue.component('contentHeader', contentHeader)
    Vue.component('contentFooter', contentFooter)
    Vue.component('contentContainer', contentContainer)
  }
}
export default components
