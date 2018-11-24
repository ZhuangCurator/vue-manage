<style scoped>
  .expand-row{
    margin-bottom: 8px;
  }
</style>
<template>
  <div>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">标题图片: </span>
        <span class="expand-value">{{ row.titleImageUrl }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">列表小图: </span>
        <span class="expand-value">{{ row.imageUrl }}</span>
      </i-col>
    </Row>
    <Row class="expand-row">
      <i-col span="24">
        <span class="expand-key">音频url: </span>
        <span class="expand-value">{{ row.mediaUrl }}</span>
      </i-col>
    </Row>
  </div>
</template>
<script>
export default {
  name: 'AlbumTrackExpand',
  props: {
    row: Object
  }
}
</script>
