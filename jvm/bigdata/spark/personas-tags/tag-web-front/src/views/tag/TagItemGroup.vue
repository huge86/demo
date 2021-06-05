<template>

  <div class="item-group">
    <tag-item v-for="tag in tags" :key="tag.id" :tag="tag"></tag-item>
  </div>
</template>

<script>
import axios from "axios"
import config from "../../Config"
import TagItem from "./TagItem"

export default {
  name: "TagItemGroup",
  components: {
    TagItem
  },
  props: {
    pid: {
      type: String,
      default: '0'
    },
    level: {
      type: String,
      default: '0'
    }
  },
  mounted() {
    //先判断是否是3级,如果是3级,发送获取model请求
    if (this.level == 3) {
      axios
        .get(config.baseApi + "tags/model?pid=" + this.pid)
        .then(response => {
          this.tags = response.data.data;
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
    } else {
      axios
        .get(config.baseApi + "tags?pid=" + this.pid)
        .then(response => {
          this.tags = response.data.data;
        })
        .catch(error => {
          this.$message.error("网络请求异常");
        });
    }
    
  },
  data() {
    return {
      tags: []
    }
  }
};
</script>

<style lang="scss" scoped>

</style>