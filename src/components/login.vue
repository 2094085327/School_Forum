<template>
  <!--  登录界面-->
  <div id="background">
    <body id="poster">
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
             label-position="left">
      <h3 class="register_title">
        用户登录
        <el-button @click="toLogin()">还没有账号，注册</el-button>
      </h3>
      <el-form-item label="用户名" prop="userName">
        <el-input v-model.number="ruleForm.userName" placeholder="请输入用户名" clearable autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" placeholder="请输入密码"
                  clearable show-password></el-input>
      </el-form-item>

      <el-form-item>
        <el-button class="button_register" type="primary" style="width:100%;border: none;"
                   @click="submitForm('ruleForm')">
          登录
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="button_register" type="danger" plain style="width:100%;border: none;margin-top: 25px"
                   @click="resetForm('ruleForm')">重置
        </el-button>
      </el-form-item>
    </el-form>

    </body>
  </div>
</template>

<script>
export default {
  name: "login",
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
    // 检查登录状态
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let params = new URLSearchParams();
          params.append("password", this.ruleForm.pass)
          params.append("makeUserName", this.ruleForm.userName)
          this.$http.post("/user/login", params).then((response) => {
            console.log(response.data)
            if (response.data === '') {
              this.$message.error('登录失败，请检查用户名和密码');
              return false;
            } else {
              if (response.data.role === 1) {
                this.$message({
                  message: '欢迎回来 ' + response.data.username + '，快去论坛发帖吧',
                  type: 'success',
                });
              }
              if (response.data.role === 2) {
                this.$message({
                  message: '欢迎你，管理员 ' + response.data.username,
                  type: 'success',
                });
              }
              this.$store.state.name = response.data.username
              this.$store.state.userId = response.data.id
              this.$store.state.role = response.data.role

              this.$store.state.userImg = response.data.img
              this.$store.state.paginationState = true
              this.$store.state.publishState = true

              this.$router.push({path: '/Pagination'})
              this.ruleForm.userName = ""
              this.ruleForm.pass = ""
            }
          }).catch(function (error) {
            console.log(error);
          });

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    toLogin() {
      this.$router.push({path: '/register'})
    },
  },
  created() {
    this.$store.state.replyState = false
    this.$store.state.paginationState = false
    this.$store.state.publishState = false
  },
  components: {}
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
  padding: 0;
  margin: 50px 0 0 250px;
}

.demo-ruleForm {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
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