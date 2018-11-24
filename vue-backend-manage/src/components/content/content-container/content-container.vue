<!-- 内容区域 -->
<template>
  <div class="content-container">
    <slot></slot>
  </div>
</template>

<script>
  export default {
    name: 'content-container'
  }
</script>

<style lang="less" >
  .content-container{
    width: 100%;
    height: auto;
    padding-bottom:10px;
  }
</style>
