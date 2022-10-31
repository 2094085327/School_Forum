<template>
  <div class="block">
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
    </el-table>

    <el-table
        :data="userReply"
        border
        style="width: 100%"
        empty-text="还没有人回复哦，快抢沙发"
        :cell-style="{'text-align':'center'}"
        :header-cell-style="{'text-align':'center'}">
      <el-table-column
          prop="username"
          label="用户"
          width="180">
      </el-table-column>
      <el-table-column
          prop="content"
          label="回复">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="回复时间"
          width="180">
      </el-table-column>
      <el-table-column
          prop="floor"
          label="楼层"
          width="180">
      </el-table-column>
      <el-table-column
          v-if="this.$store.state.role===2"
          prop="operation"
          label="操作"
          width="180">
        <template slot-scope="scope">
          <el-button @click="deletes(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20]"
        :page-size="100"
        layout="total, sizes, prev, pager, next, jumper"
        :total=pagination.total>
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "topic-detail",
  data() {
    return {
      currentPage: 1,
      pagination: {
        page: 1,
        size: 5,
        count: 0,
        total: 0,
        id: 0
      },
      userList: [],
      userReply: [],
    }
  },
  methods: {//用来处理每页显示记录发生变化的方法
    findById() {
      this.$http.get("/topic/findById", {
        params: {
          topicId: this.$route.params.id,
          deletes:0
        }
      }).then(response1 => {
        this.userList = response1.data;
        this.$store.state.topicTitle = response1.data[0].title
      })
    },
    findByPage() {
      this.$http.get("/reply/findByPage", {
        params: {
          pageNum: this.pagination.page,
          size: this.pagination.size,
          topicId: this.$route.params.id,
          deletes: 0
        }
      }).then(response => {
        this.userReply = response.data.records;
        this.pagination.total = response.data.total;
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pagination.size = val;
      this.findByPage()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pagination.page = val;
      this.findByPage()
    },
    getId() {
      this.pagination.id = this.$route.params.id;
    },
    deletes(val) {
      // 跳转至删除回复页面
      this.$store.state.topicContent = val.content
      this.$store.state.floor = val.floor
      this.$store.state.floorUserName = val.username
      console.log(val.id)
      console.log(val)
      console.log(val.floor)
      this.$store.state.replyId = val.id
      this.$http.get("/reply/findByPage", {
        params: {
          pageNum: 1,
          size: 5,
          topicId: val.id,
          deletes: 0
        }
      }).then(response => {
        this.$store.state.replyTimes = response.data.total
      })
      this.$store.state.topicType = "回复"
      this.$router.push({name: 'deletePage'});
    }
  }
  ,
  created: function () {
    this.findById()
    this.findByPage()
    this.getId()
    console.log(this.pagination.id)
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: #0a53be;
}

a:hover {
  text-decoration: none
}
</style>