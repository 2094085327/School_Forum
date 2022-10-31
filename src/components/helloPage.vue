<template>
  <body id="background">
  <body id="poster">
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
           label-position="left">
    <h2 class="register_title">
      兰州理工大学论坛
    </h2>
    <el-form-item>
      <el-button class="button_register" type="primary" style="width:100%;border: none;"
                 @click="toPagination" v-if="this.$store.state.name!=='登录'">
        进入论坛
      </el-button>
      <el-button class="button_register" type="primary" style="width:100%;border: none;"
                 @click="toPagination2" v-if="this.$store.state.name==='登录'">
        进入论坛
      </el-button>
    </el-form-item>
  </el-form>
  </body>
  </body>
</template>

<script>

export default {
  name: "helloPage",
  data() {
    const checkUserName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入用户名'));
      } else {
        callback()
      }
    };
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        pass: '',
        userName: '',
        loginName: '登录'
      },
      rules: {
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        userName: [
          {validator: checkUserName, trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    toPagination() {
      this.$router.push({path: '/Pagination'})
      this.$store.state.paginationState = true
    },
    toPagination2() {
      this.$router.push({path: '/login'})
    },
  },
  components: {},
  created() {
    this.$store.state.replyState = false

    if (this.$store.state.name === '登录') {
      this.$store.state.paginationState = false
      this.$store.state.publishState = false
    } else {
      this.$store.state.paginationState = true
      this.$store.state.publishState = true
    }
  },
}
</script>

<style scoped>
#poster {
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

body {
  margin: 0;
  padding: 0;
}

.demo-ruleForm {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 60px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #a2a2a2;
}

.button_register {
  position: absolute;
  left: -50px;
}

#background {
  background: url("../assets/background.jpg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
</style>