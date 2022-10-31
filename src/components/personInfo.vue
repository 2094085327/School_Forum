<template>
  <!--用户注册界面-->
  <body id="poster">
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
           label-position="left">
    <h3 class="register_title">
      个人中心
    </h3>
    <el-form-item label="用户名" prop="userName">
<!--      <el-input v-model.number="ruleForm.userName" placeholder="请输入用户名" clearable autocomplete="off"></el-input>-->
    <h4  style="margin-top: 0;text-align: left;">{{ruleForm.userName}}</h4>
    </el-form-item>

    <el-form-item label="手机号" prop="phoneNum">
      <el-input v-model.number="ruleForm.phoneNum" placeholder="请输入手机号" clearable autocomplete="off"></el-input>
    </el-form-item>

    <el-form-item label="性别" prop="resource">
      <el-radio-group v-model="ruleForm.sex">
        <el-radio label="男"></el-radio>
        <el-radio label="女"></el-radio>
      </el-radio-group>
    </el-form-item>

    <el-form-item label="原密码" prop="pass">
      <el-input type="password" v-model="ruleForm.oldPass" autocomplete="off" placeholder="请输入密码"
                clearable show-password></el-input>
    </el-form-item>

    <el-form-item label="新密码" prop="checkPass">
      <el-input type="password" v-model="ruleForm.newPass" autocomplete="off" placeholder="请输入密码"
                clearable show-password></el-input>
    </el-form-item>

    <el-form-item>
      <el-button class="button_register" type="primary" style="width:100%;border: none;"
                 @click="submitForm('ruleForm')">
        确认修改
      </el-button>
    </el-form-item>
    <el-form-item>
      <el-button class="button_register" type="danger" plain style="width:100%;border: none;margin-top: 25px"
                 @click="resetForm">取消
      </el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
export default {
  name: "personInfo",
  data() {
    const checkUserName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'));
      } else {
        callback()
      }
    };
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'));
      } else {
        callback()
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          callback()
        }
      }, 1000);
    };
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value === this.ruleForm.pass) {
        callback(new Error('新旧密码不能一致'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        oldPass: '',
        newPass: '',
        phoneNum: '',
        userName: '',
        sex: '',
      },
      rules: {
        oldPass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        newPass: [
          {validator: validatePass2, trigger: 'blur'}
        ],
        phoneNum: [
          {validator: checkPhone, trigger: 'blur'}
        ],
        userName: [
          {validator: checkUserName, trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          // this.$http.get("/user/findByName", {
          //       params: {
          //         userName: this.ruleForm.userName
          //       }
          //     }
          // ).then(response => {
          //   if (response.data === '') {
              // let params = new URLSearchParams();
              // params.append("phoneNumber", this.ruleForm.phoneNum)
              // params.append("password", this.ruleForm.pass)
              // params.append("makeUserName", this.ruleForm.userName)
              // this.$http.post("/user/insert", params).then((response) => {
              //   console.log(response)
              // }).catch(function (error) {
              //   console.log(error);
              // });
              // this.ruleForm.userName = ""
              // this.ruleForm.pass = ""
              // this.ruleForm.checkPass = ""
              // this.ruleForm.phoneNum = ""
              // this.open()

              this.$http.get("/user/updateById", {
                params: {
                  userName: this.ruleForm.userName,
                  userPhone: this.ruleForm.phoneNum,
                  userSex: this.ruleForm.sex,
                  userId: this.$store.state.userId,
                  oldPwd: this.ruleForm.oldPass,
                  newPwd: this.ruleForm.newPass,
                }
              }).then(response => {
                // this.userList = response.data.records;
                // this.pagination.total = response.data.total;
                if (response.data === '') {
                  this.$message.error('原密码错误')
                } else {
                  console.log("修改成功")
                  this.open()
                  this.$router.push({name:'pagination'})
                }
              })
          //   } else {
          //     this.$message.error('这个用户名已经有人使用了，换一个吧')
          //   }
          // })
        } else {
          this.$message.error('修改失败，请查看是否输入正确或网络');
          return false;
        }
      });
    }
    ,
    resetForm() {
      this.$router.push({path: 'Pagination'})
    }
    ,
    toLogin() {
      this.$router.push({path: 'login'})
    }
    ,
    open() {
      this.$message({
        message: '修改成功，点击登录按钮去登录吧！',
        type: 'success'
      });
    }
    ,
    findUserName() {
      this.$http.get("/user/findById", {
        params: {
          userId: this.$store.state.userId
        }
      }).then(response => {
        console.log(response)
        this.ruleForm.userName = response.data.username
        this.ruleForm.phoneNum = response.data.phone
        if (response.data.sex === 1) {
          this.ruleForm.sex = "男"
        }
        if (response.data.sex === 2) {
          this.ruleForm.sex = "女"
        }
      });

    }
  },
  created() {
    this.$store.state.replyState = false
    this.findUserName()
  }
  ,
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

</style>