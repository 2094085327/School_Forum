<template>
  <div class="content-area">
    <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>
  </div>
</template>

<script>
export default {
  name: "upTop"
}
</script>

<style scoped>
/*.content-area {*/
/*  !* 这里高度减60像素减的是顶部导航条的高度。避免出现浏览器自带的滚动条 *!*/
/*  height: calc(100vh - 60px);*/
/*  !* 溢出高度自动显示滚动条 *!*/
/*  overflow: auto;*/
/*}*/
</style>