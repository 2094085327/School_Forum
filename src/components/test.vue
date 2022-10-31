<template>
  <div class="demo-input-suffix" >
    <body id="poster">
    <h1>用户注册</h1>
    <el-form :label-position="labelPosition"  :model="formLabelAlign" class="register">

      <el-form-item label="用户名">
        <el-input
            placeholder="请输入用户名"
            v-model="makeUserName"
            clearable>
        </el-input>
      </el-form-item>
      <h6 v-if="this.makeUserName===''" style="color:#dc3545;">用户名不能为空</h6>

      <el-form-item label="手机号码">
        <el-input
            placeholder="请输入手机号"
            v-model="phoneNumber"
            clearable>
        </el-input>
      </el-form-item>
      <h6 v-if="this.phoneNumber===''" style="color:#dc3545;">手机号不能为空</h6>

      <el-form-item label="密码">
        <el-input
            placeholder="请输入密码"
            v-model="password"
            show-password
            clearable>
        </el-input>
      </el-form-item>
      <h6 v-if="this.password===''" style="color:#dc3545;">密码不能为空</h6>

      <el-form-item label="确认密码">
        <el-input
            placeholder="请输入密码"
            v-model="passwordAgain"
            show-password
            clearable>
        </el-input>
      </el-form-item>
      <h6 v-if="passwordAgain===''" style="color:#dc3545;">密码不能为空</h6>
    </el-form>
    <el-row>
      <el-button type="primary" plain @click="register">注册</el-button>
      <el-button type="danger" plain @click="cancel">取消</el-button>
    </el-row>
    </body>
  </div>
</template>

<script>
export default {
  name: "test",
  data() {
    return {
      labelPosition: "left",
      makeUserName: '',
      password: '',
      passwordAgain: '',
      phoneNumber: '',
      userNameShow: false,
      passwordShow: false,
      phoneNumberShow: false,
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      }
    }
  },
  methods: {
    cancel() {
      this.makeUserName = ""
      this.password = ""
      this.passwordAgain = ""
      this.phoneNumber = ""
    },
    register() {
      if (this.password !== this.passwordAgain) {
        alert("两次输入的密码不一致")
      }
      if (this.makeUserName === "") {
        this.userNameShow = true
      }
      if (this.phoneNumber === "") {
        this.phoneNumberShow = true
      }
      if (this.password === "" || this.passwordAgain === "") {
        this.passwordShow = true
      } else {
        let params = new URLSearchParams();
        params.append("phoneNumber", this.phoneNumber)
        params.append("password", this.password)
        params.append("makeUserName", this.makeUserName)
        params.append("phoneNumber", this.phoneNumber)
        this.$http.post("/user/insert", params).then((response) => {
          console.log(response)
        }).catch(function (error) {
          console.log(error);
        });
        this.makeUserName = ""
        this.password = ""
        this.passwordAgain = ""
        this.phoneNumber = ""
        alert("注册成功")
      }
    }
  }
}
</script>

<style scoped>
#poster{
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: 0;
  padding: 0;
}
.register{
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  padding: 35px 35px 15px 35px;
}
</style>