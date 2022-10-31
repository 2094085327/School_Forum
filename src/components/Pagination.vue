<template>
  <div class="block">
    <el-table
        :data="userList"
        border
        style="width: 100%"
        highlight-current-row
        empty-text="这个话题分类还没有人发帖哦，快来发布一个吧"
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
          prop="hot"
          label="热度"
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
  name: "pagination",
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
    }
  },
  methods: {
    //用来处理每页显示记录发生变化的方法
    findByPage() {
      this.$http.get("/topic/findByPage", {
        params: {
          pageNum: this.pagination.page,
          size: this.pagination.size,
          cId: this.$store.state.topicCid,
          deletes: 0
        }
      }).then(response => {
        this.userList = response.data.records;
        this.pagination.total = response.data.total;
        console.log(response.data)
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
    // 点击文章时进行初始化
    handleCurrentChangeId(val) {
      this.currentRow = val;
      this.$store.state.topicId = this.currentRow.id
      this.$store.state.replyState = true
      this.$store.state.topicTitle = val.title

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
    // 跳转至删除文章页面
    deletes(val) {
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
  // 生成页面时进行初始化
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