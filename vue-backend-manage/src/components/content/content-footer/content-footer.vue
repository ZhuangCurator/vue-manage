<template>
  <div class="content-footer">
    <Card :bordered="false">
      <Page :total="total" :curent="pageNum" :page-size="pageSize" @on-change="handlePage" show-total show-elevator  />
    </Card>
  </div>
</template>

<script>
  export default {
    name: 'content-footer',
    props: {
      total: {
        type: Number
      },
      pageSize: {
        type: Number
      },
      pageNum: {
        type: Number
      }
    },
    methods: {
      handlePage (value) {
        this.$parent.handlePage(value)
      }
    }
  }
</script>

<style lang="less" >
  .content-footer{
    width: 100%;
    height: auto;
  }
</style>
