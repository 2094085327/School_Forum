<template>
  <div class="block">
    <body v-if="this.$store.state.replyType==='主题'">
    <el-table
        :data="userList"
        border
        style="width: 100%"
        highlight-current-row
        empty-text="回收站还是空的~"
        @current-change="handleCurrentChangeId"
        :cell-style="{'text-align':'center'}"
        :header-cell-style="{'text-align':'center'}">
      <el-table-column
          prop="title"
          label="标题"
          width="180">
      </el-table-column>
      <el-table-column
          prop="cid"
          label="分类"
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
          v-if="this.$store.state.role===2"
          prop="operation"
          label="操作"
          width="180">
        <template slot-scope="scope">
          <el-button @click="recovery(scope.row)" type="text" size="small">恢复</el-button>
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
    </body>
    <body v-if="this.$store.state.replyType==='回复'">
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
          <el-button @click="recovery(scope.row)" type="text" size="small">恢复</el-button>
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
    </body>
  </div>
</template>

<script>
export default {
  name: "recycleBin",
  data() {
    return {
      currentPage: 1,
      pagination: {
        page: 1,
        size: 5,
        count: 0,
        total: 0,
      },
      userList: [],
      userReply: [],
    }
  },
  methods: {//用来处理每页显示记录发生变化的方法
    findByPage() {
      // 查询页面
      this.$http.get("/topic/findByPage", {
        params: {
          pageNum: this.pagination.page,
          size: this.pagination.size,
          cId: this.$store.state.topicCid,
          deletes: 1
        }
      }).then(response => {
        this.userList = response.data.records;
        this.pagination.total = response.data.total;
      })
    },
    findByPage2() {
      // 查询回复的全部条数
      this.$http.get("/reply/findByPage", {
        params: {
          pageNum: this.pagination.page,
          size: this.pagination.size,
          topicId: 'null',
          deletes: 1
        }
      }).then(response => {
        this.userReply = response.data.records;
        console.log("this.userReply")
        console.log(this.userReply)
        console.log(response)
        this.pagination.total = response.data.total;
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pagination.size = val;
      if (this.$store.state.replyType === '主题') {
        this.findByPage()
      } else {
        this.findByPage2()
      }
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pagination.page = val;
      if (this.$store.state.replyType === '主题') {
        this.findByPage()
      } else {
        this.findByPage2()
      }
    },
    handleCurrentChangeId(val) {
      // 跳转到详情页面
      this.currentRow = val;
      this.$store.state.topicId = this.currentRow.id
      this.$store.state.replyState = true
      this.$router.push({name: 'topic-detail', params: {id: this.currentRow.id}});
    },
    recovery(val) {
      if (this.$store.state.replyType === '主题') {
        // 按下删除后的操作
        this.$store.state.topicTitle = val.title
        this.$store.state.topicContent = val.content
        console.log(val.id)
        this.$store.state.topicId = val.id
        // 获取回复条数
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
      } else {
        // 按下恢复后的操作
        console.log(val)
        this.$store.state.topicContent = val.content
        this.$store.state.replyId = val.id
        // 获取回复条数
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
      }
      // 跳转至删除文章页面
      this.$router.push({name: 'recoveryPage'});
    }
  },
  created: function () {
    // 创造窗口时查询页面
    this.findByPage()
    this.findByPage2()
    this.$store.state.replyState = false
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