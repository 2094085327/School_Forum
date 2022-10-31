<template>
  <div id="app">
    <navs/>
<!--    <pagination/>-->
<!--    <test/>-->
<!--    <register/>-->
  </div>
</template>

<script>
import navs from './components/navs.vue'
import container from "@/components/container";
import pagination from "@/components/Pagination";
import test from "@/components/test";
import register from "@/components/register";
import upTop from "@/components/upTop";

export default {
  name: 'App',
  components: {
    navs,
    container,
    pagination,
    test,
    register,
    upTop
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
}

#test {

}
</style>
