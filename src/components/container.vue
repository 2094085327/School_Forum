<template>
  
  <el-table
      :data="userList"
      border
      style="width: 100%"
      :cell-style="{'text-align':'center'}"
      :header-cell-style="{'text-align':'center'}">
    <el-table-column
        prop="title"
        label="标题"
        width="180">
    </el-table-column>
    <el-table-column
        prop="content"
        label="内容">
    </el-table-column>
    <el-table-column
        prop="username"
        label="作者"
        width="180">
    </el-table-column>
    <el-table-column
        prop="createTime"
        label="创建时间"
        width="180">
    </el-table-column>
    <el-table-column
        prop="operation"
        label="操作"
        width="180">
      <a href="">详情</a>
    </el-table-column>
  </el-table>
</template>
<script>
export default {
  name: "container",
  data() {
    return {
      ages: 1,
      id: "001", title: '标题1', content: "内容1", username: "用户1", createTime: "创建时间1",
      userList: []
    }
  },
  created: function () {
    this.$http.get("/topic/list").then((response) => {
      // console.log(response)
      // console.log(response.data)
      this.userList = response.data
    })
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
</style>