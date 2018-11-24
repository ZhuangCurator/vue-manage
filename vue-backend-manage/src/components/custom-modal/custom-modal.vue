<template>
  <Modal
    v-model="show"
    :closable="true"
    :width="width"
    @on-visible-change="modalVisible"
    @on-cancel="modalCancel"
    class-name="custom-modal">
    <p slot="header" style="text-align:center">
      <Icon type="ios-information-circle"></Icon>
      <span>{{ title }}</span>
    </p>
    <!--<Divider slot="header" class="modal-divider">{{ title }}</Divider>-->
    <slot></slot>
    <div class="modal-footer" slot="footer">
      <!--<Divider class="modal-divider" />-->
      <Button @click="modalCancel">取 消</Button>
      <Button type="primary" @click="modalConfirm">确 定</Button>
    </div>
  </Modal>
</template>

<script>
  export default {
    name: 'custom-modal',
    data () {
      return {
        show: false
      }
    },
    methods: {
      modalCancel () {
        this.$emit('cancel')
      },
      modalConfirm () {
        this.$emit('confirm')
      },
      modalVisible (state) {
        if (!state) {
          this.$emit('modalRemove')
        }
      }
    },
    props: {
      title: {
        type: String,
        required: true
      },
      modalShow: {
        type: Boolean,
        required: true
      },
      width: {
        type: Number,
        default: 520
      }
    },
    watch: {
      modalShow (val) {
        this.show = val
      }
    }
  }
</script>

<style lang="less" scoped></style>
