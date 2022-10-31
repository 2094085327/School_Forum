<template>
  <body id="poster" v-if="this.$store.state.topicType==='主题'">
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
           label-position="left">
    <h3 class="register_title">
      删除文章
    </h3>
    <el-form-item label="文章名:" prop="userName" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.topicTitle }}</h4>
    </el-form-item>

    <el-form-item label="文章名:" prop="userName" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.topicContent }}</h4>
    </el-form-item>

    <el-form-item label="回复数:" prop="userName" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.replyTimes }} 条</h4>
    </el-form-item>

    <el-form-item label="操作员:" prop="pass" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.name }}</h4>
    </el-form-item>

    <el-form-item>
      <el-button class="button_register" type="primary" style="width:100%;border: none;"
                 @click="submitForm('ruleForm')">
        删除
      </el-button>
    </el-form-item>
    <el-form-item>
      <el-button class="button_register" type="danger" plain style="width:100%;border: none;margin-top: 25px"
                 @click="resetForm('ruleForm')">取消
      </el-button>
    </el-form-item>
  </el-form>
  </body>
  <body id="poster" v-else>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
           label-position="left">
    <h3 class="register_title">
      删除回复
    </h3>
    <el-form-item label="文章名:" prop="userName" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.topicTitle }}</h4>
    </el-form-item>

    <el-form-item label="用户:" prop="userName" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.floorUserName }}</h4>
    </el-form-item>

    <el-form-item label="回复内容:" prop="userName" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.topicContent }}</h4>
    </el-form-item>

    <el-form-item label="楼层:" prop="userName" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.floor }}</h4>
    </el-form-item>

    <el-form-item label="操作员:" prop="pass" style="margin: 0">
      <h4 style="margin-top: 0;text-align: left;">{{ this.$store.state.name }}</h4>
    </el-form-item>

    <el-form-item>
      <el-button class="button_register" type="primary" style="width:100%;border: none;"
                 @click="submitForm('ruleForm')">
        删除
      </el-button>
    </el-form-item>
    <el-form-item>
      <el-button class="button_register" type="danger" plain style="width:100%;border: none;margin-top: 25px"
                 @click="resetForm('ruleForm')">取消
      </el-button>
    </el-form-item>
  </el-form>

  </body>
</template>

<script>
export default {
  name: "deletePage",
  data() {
    return {
      ruleForm: {
        pass: '',
        userName: '',
        loginName: '登录'
      },
      rules: {
        pass: [
          // {validator: validatePass, trigger: 'blur'}
        ],
        userName: [
          // {validator: checkUserName, trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    // 删除
    submitForm() {
      if (this.$store.state.topicType === '主题') {
        this.$http.get("/topic/updateTopic", {
          params: {
            topicId: this.$store.state.topicId,
            deletes: 1
          }
        }).then(response => {
          console.log(response)
          this.$message({
            message: '删除成功',
            type: 'success',
          });
        })
      } else {
        this.$http.get("/reply/updateReply", {
          params: {
            replyId: this.$store.state.replyId,
            deletes: 1
          }
        }).then(response => {
          console.log(response)
          this.$message({
            message: '删除成功',
            type: 'success',
          });
        })
      }
      this.$router.push({path: '/Pagination'})
    },
    resetForm() {
      this.$router.push({path: '/Pagination'})
    },
    toLogin() {
      this.$router.push({path: '/register'})
    },
  },
  created() {
    this.$store.state.replyState = false
    this.$store.state.paginationState = true
    this.$store.state.publishState = true
  },
  components: {}
}
</script>

<style scoped>
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