<template>
  <el-submenu
    class="tag-menu"
    :class="{ 'tag-level4':tagLevel4 , 'active2':style2  }"
    :index="tag.id + ''"
    @click.native="handleClick(tag,$event)"
  >
    <template slot="title">
      <i class="el-icon-s-order"></i>
      <span v-if="tag.level == 1 || tag.level == 2 || tag.level == 3 ">{{tag.name}}</span>
      <span v-else>{{tag.tag.name}}</span>
    </template>

    <div v-if="tag.level == 1 || tag.level == 2 || tag.level == 3">
      <tag-item-group v-if="showSubItems" :pid="tag.id+''" :level="tag.level+''"></tag-item-group>
    </div>
    <div v-else>
      <tag-item-group v-if="showSubItems" :pid="tag.tag.id+''" :level="tag.level+''"></tag-item-group>
    </div>
    
  </el-submenu>
</template>

<script>
export default {
  name: "TagItem",
  props: {
    tag: {
      type: Object,
      default: {}
    }
  },
  components: {
    TagItemGroup: () => import("./TagItemGroup")
  },
  data() {
    return {
      showSubItems: false,
      tagLevel4: false,
      style2: true
    }
  },
  mounted() {
    //当组件加载的时候判断级别是否为4级,如果为4级,那么隐藏四级后面的箭头
    if (this.tag.level == 4) {
      this.tagLevel4 = true
    }
  },
  methods: {
    handleClick: function(tag, event) {
      event.stopPropagation()
      this.showSubItems = ! this.showSubItems
      if (tag.level == 3){
        this.$router.push("/tags/four/"+tag.id).catch(err => {})
      }
      if (tag.level != 1 && tag.level != 2 && tag.level != 3){
        //隐藏5级子标签
        this.showSubItems = false
        this.$router.push("/tags/five/"+tag.tag.id).catch(err => {})
      }
    }
  }
}
</script>

<style>
.tag-level4 .el-submenu__icon-arrow {
   visibility: hidden !important;
}
</style>