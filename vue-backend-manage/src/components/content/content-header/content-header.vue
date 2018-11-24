<!-- 内容头 -->
<template>
  <div class="content-header">
    <Card :bordered="false">
      <Row>
        <Col span="16" class="content-header-left">
          <slot name="contentHeaderLeft"></slot>
        </Col>
        <Col span="8" class="content-header-right">
          <slot name="contentHeaderRight"></slot>
        </Col>
      </Row>
    </Card>
  </div>
</template>

<script>
  export default {
    name: 'content-header'
  }
</script>

<style lang="less" >
  .content-header {
    width: 100%;
    height: auto;
    text-align: center;
    padding-bottom:10px;
    .content-header-left {
      display: flex;
      justify-content: flex-start;
      .ivu-col {
        display: flex;
        justify-content: flex-start;
        margin-right:5px;
        .ivu-btn {
          margin-right:5px;
        }
      }

    }
    .content-header-right {
      display: flex;
      justify-content: flex-end;
      .ivu-btn {
        margin-right:5px;
      }
    }
  }
</style>
