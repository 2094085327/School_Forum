<template>
  <div id="poster">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="文章标题" prop="name">
        <span>{{ this.$store.state.topicTitle }}</span>
      </el-form-item>
      <el-form-item label="回复内容" prop="desc">
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
  name: "reply",
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
          {message: '请输入主题标题', trigger: 'blur'},
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
          params.append("topicId", this.$store.state.topicId)
          params.append("topicContent", this.ruleForm.desc)
          params.append("userId", this.$store.state.userId)
          this.$http.post("/reply/insertReply", params).then((response) => {
            console.log(response.data)

            this.$message({
              message: '回复成功',
              type: 'success',
            });

            // this.$store.state.name = response.data.username
            this.$router.push({name: 'topic-detail', params: {id: this.$store.state.topicId}});
            this.ruleForm.name = ""
            this.ruleForm.resource = ""
            this.ruleForm.desc = ""
            // }
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
  }
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