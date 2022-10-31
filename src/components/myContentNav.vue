<template>
  <!--  内容管理导航栏导航栏-->
  <div>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">

      <el-menu-item index="我发布的主题">
        <router-link to="">我发布的主题</router-link>
      </el-menu-item>

      <el-menu-item index="我回复的楼层">
        <router-link to="">我回复的楼层</router-link>
      </el-menu-item>

    </el-menu>
    <router-view/>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
export default {
  name: "myTopicNav",
  data() {
    return {
      activeIndex: '1',
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key)
      if (key === "我发布的主题" || key === "我回复的楼层") {
        console.log(this.$store.state.myState)
        this.$store.state.myState = key
      }
    },
    signOut() {
      this.$store.state.name = '登录'
      this.$router.push({path: '/helloPage'})
      console.log(this.$store.state.name)
      this.$store.commit("signOut")
    },
    personInfo() {
      this.$router.push({path: '/personInfo'})
    }
  },
}
</script>
<style scoped>
a {
  text-decoration: none
}

a:hover {
  text-decoration: none
}
</style>