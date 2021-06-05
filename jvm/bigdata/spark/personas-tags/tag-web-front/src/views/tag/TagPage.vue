<template>
  <div class="container">
    <tag-view class="left"></tag-view>
    <router-view class="right"></router-view>
  </div>
</template>

<script>
import TagView from "./TagView";

export default {
  components: {
    TagView
  }
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.left {
  width: 20%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.right {
  width: 80%;
  height: 100%;
}
</style>