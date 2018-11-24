<style scoped>
  .expand-row{
    margin-bottom: 8px;
  }
</style>
<template>
  <div>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">rss地址: </span>
        <span class="expand-value">{{ row.rssAddress }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">作者: </span>
        <span class="expand-value">{{ row.author }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">专辑封面: </span>
        <span class="expand-value">{{ row.coverImageUrl }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">标题封面: </span>
        <span class="expand-value">{{ row.titleImageUrl }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">集数: </span>
        <span class="expand-value">1</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">简介: </span>
        <span class="expand-value">{{ row.summary }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">描述: </span>
        <span class="expand-value">{{ row.description }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">更新时间: </span>
        <span class="expand-value">{{ row.updateTime }}</span>
      </i-col>
    </Row>
  </div>
</template>
<script>
export default {
  name: 'AlbumExpand',
  props: {
    row: Object
  }
}
</script>
