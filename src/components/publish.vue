<template>
  <div id="poster">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="文章标题" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="文章分类" prop="resource">
        <el-radio-group v-model="ruleForm.resource" style="margin-right: 2px;float: left">
          <el-radio label="JavaWeb" style="left: 10px"></el-radio>
          <el-radio label="Web前端" style="left: 30px"></el-radio>
          <el-radio label="人工智能" style="left: 16.8px"></el-radio>
          <el-radio label="自动化测试" style="left: 37px"></el-radio>
          <el-radio label="大数据" style="left: 2px"></el-radio>
          <el-radio label="Java开发" style="left: 37px"></el-radio>
          <el-radio label="数据库" style="left: -12px"></el-radio>
          <el-radio label="后端架构" style="left: 23px"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="文章内容" prop="desc">
        <el-input type="textarea" v-model="ruleForm.desc"></el-input>
      </el-form-item>
      <el-form-item style="margin-left: -100px">
        <el-button type="primary" @click="submitForm('ruleForm')">立即发布</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "publish",
  data() {
    return {
      ruleForm: {
        name: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入主题标题', trigger: 'blur'},
        ],
        resource: [
          {required: true, message: '请选择文章分类', trigger: 'change'}
        ],
        desc: [
          {required: true, message: '请填写文章内容', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let params = new URLSearchParams();
          params.append("topicTitle", this.ruleForm.name)
          params.append("topicContent", this.ruleForm.desc)
          params.append("userId", this.$store.state.userId)
          params.append("cId", this.ruleForm.resource)
          this.$http.post("/topic/insertTopic", params).then((response) => {
            this.$message({
              message: '你的主题已经发布了，快去看看吧',
              type: 'success',
            });
            // 发布主题后跳转回主题页
            this.$router.push({path: '/Pagination'})
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
    }
  },
  created() {
    this.$store.state.replyState=false
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
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #a2a2a2;
}

.button_register {
  position: absolute;
  left: -50px;
}

</style>