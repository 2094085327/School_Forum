<template>
  <!--管理我的回复页面-->
  <div>
    <el-table
        :data="userList"
        border
        style="width: 100%"
        highlight-current-row
        empty-text="你还没有回复过主题，快去回复吧"
        @current-change="handleCurrentChangeId"
        :cell-style="{'text-align':'center'}"
        :header-cell-style="{'text-align':'center'}">
      <el-table-column
          prop="title"
          label="文章标题"
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
          prop="replyContent"
          label="我的回复"
          width="180">
      </el-table-column>
      <el-table-column
          prop="floor"
          label="楼层"
          width="180">
      </el-table-column>
      <el-table-column
          prop="replyTime"
          label="回复时间"
          width="180">
      </el-table-column>
      <el-table-column
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
  name: "myReply",
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
      titleList: [],
    }
  },
  methods: {
    //用来处理每页显示记录发生变化的方法
    findByPage() {
      console.log("页面")
      this.$http.get("/more/topicAndReplyPage", {
        params: {
          pageNum: this.pagination.page,
          size: this.pagination.size,
          // cId: this.$store.state.topicCid,
          replyId: this.$store.state.userId,
          deletes: 0
        },
      }).then(response => {
        console.log("response.data")
        console.log(response.data.records)
        this.userList = response.data.records;
        this.pagination.total = response.data.total;

        console.log("response")
        console.log(response)
        console.log(response.data.records[0].floor)
        console.log(this.userList)
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
    handleCurrentChangeId(val) {
      this.currentRow = val;
      this.$store.state.topicId = this.currentRow.id
      this.$store.state.replyState = true
      this.$router.push({name: 'topic-detail', params: {id: this.currentRow.id}});
      this.$http.get("/topic/updateHot", {
        params: {
          topicId: val.id,
          deletes: 0
        }
      }).then(response => {
        console.log(response.data.total)
      })
    },
    deletes(val) {
      // 跳转至删除文章页面
      this.$store.state.topicTitle = val.title
      this.$store.state.topicContent = val.content
      console.log(val.id)
      this.$store.state.topicId = val.id
      this.$http.get("/reply/findByPage", {
        params: {
          pageNum: 1,
          size: 5,
          topicId: val.id,
          deletes: 0
        }
      }).then(response => {
        console.log(response.data.total)
        this.$store.state.replyTimes = response.data.total
      })
      this.$store.state.topicType = "主题"
      this.$router.push({name: 'deletePage'});
    }
  },
  created: function () {
    this.findByPage()
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