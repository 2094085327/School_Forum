<template>
  <!--  导航栏-->
  <div id="navs">
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item index="1">
        <router-link to="/helloPage">首页</router-link>
      </el-menu-item>

      <el-menu-item index="2" v-if="!this.$store.state.paginationState" disabled>
        <router-link to="/login">论坛</router-link>
      </el-menu-item>
      <el-menu-item index="2" v-else>
        <router-link to="/Pagination">论坛</router-link>
      </el-menu-item>

      <el-menu-item index="3" v-if="!this.$store.state.publishState" disabled>发布主题</el-menu-item>
      <el-menu-item index="3" v-else>
        <router-link to="/publish">发布主题</router-link>
      </el-menu-item>

      <el-menu-item index="4" disabled v-if="!this.$store.state.replyState">
        <router-link to="">盖楼回复</router-link>
      </el-menu-item>
      <el-menu-item index="4" v-else>
        <router-link to="/reply">盖楼回复</router-link>
      </el-menu-item>
      <el-menu-item index="5" v-if="this.$store.state.name==='登录'">
        <router-link to="login">{{ this.$store.state.name }}</router-link>
      </el-menu-item>
      <el-menu-item index="6" v-if="this.$store.state.name==='登录'">
        <router-link to="/Register">注册</router-link>
      </el-menu-item>

      <!--分类-->
      <el-submenu index="8" text-color="#fff" v-if="this.$store.state.name!=='登录'">
        <template slot="title">分类:{{ this.$store.state.topicCid }}</template>
        <el-menu-item index="全部" style="text-align: center">全部</el-menu-item>
        <el-menu-item index="JavaWeb" style="text-align: center">JavaWeb</el-menu-item>
        <el-menu-item index="Web前端" style="text-align: center">Web前端</el-menu-item>
        <el-menu-item index="人工智能" style="text-align: center">人工智能</el-menu-item>
        <el-menu-item index="自动化测试" style="text-align: center">自动化测试</el-menu-item>
        <el-menu-item index="大数据" style="text-align: center">大数据</el-menu-item>
        <el-menu-item index="Java开发" style="text-align: center">Java开发</el-menu-item>
        <el-menu-item index="数据库" style="text-align: center">数据库</el-menu-item>
        <el-menu-item index="后端架构" style="text-align: center">后端架构</el-menu-item>
      </el-submenu>

      <el-menu-item index="9" v-if="this.$store.state.name==='登录'">
        <router-link to="/Register">留言板</router-link>
      </el-menu-item>
      <el-menu-item index="10" v-if="this.$store.state.name==='登录'">
        <router-link to="/Register">我来留言</router-link>
      </el-menu-item>

      <!--回收站-->
      <el-submenu index="10" text-color="#fff" v-if="this.$store.state.role===2">
        <template slot="title">回收站</template>
        <el-menu-item index="主题" style="text-align: center">主题</el-menu-item>
        <el-menu-item index="回复" style="text-align: center">回复</el-menu-item>
      </el-submenu>

      <!--用户选项-->
      <el-container v-if="this.$store.state.name!=='登录'">
        <el-header style="text-align: right; font-size: 18px ;padding-top: 20px;color:white">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button @click="personInfo" type="text" size="small">个人中心</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button @click="myContent" type="text" size="small">内容管理</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button @click="signOut" type="text" size="small">退出登录</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>{{ this.$store.state.name }}</span>
        </el-header>
      </el-container>

    </el-menu>
    <router-view/>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import login from "@/components/login";
import Common from "@/components/Common";

export default {
  name: "navs",
  data() {
    return {
      activeIndex: '1',
      headPic: "static/pic/1.jpg"
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      if (key === "JavaWeb" || key === "Web前端" || key === "人工智能" || key === "自动化测试" || key === "大数据" || key === "Java开发" ||
          key === "数据库" || key === "后端架构" || key === "全部") {
        this.$store.state.topicCid = key
        this.$router.replace({
          path: '/whitePage',
        })
        console.log("this.$store.state.topicCid")
        console.log(this.$store.state.topicCid)
      }
      if (key === "主题" || key === "回复") {
        this.$store.state.replyType = key
        console.log(this.$store.state.replyState)
        this.$router.push({
          name: 'recycleBin'
        })
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
    },
    myContent() {
      this.$store.state.myState = "我发布的主题"
      this.$router.push({name: "myContent"})
    }
  },
  components: {
    login,
    Common
  }
}
</script>
<style scoped>
a {
  text-decoration: none
}

a:hover {
  text-decoration: none
}

#navs {
  padding: 0;
  border: 0;
  margin: -10px -10px 0;
}
</style>