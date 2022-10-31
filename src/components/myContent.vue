<template>
  <!--内容管理页面-->
  <div>
    <my-content-nav/>
    <my-topic v-if="this.$store.state.myState==='我发布的主题'"/>
    <my-reply v-else/>
  </div>
</template>

<script>
import myContentNav from "@/components/myContentNav";
import myTopic from "@/components/myTopic";
import myReply from "@/components/myReply";

export default {
  name: "myContent",
  components: {
    myContentNav,
    myTopic,
    myReply
  },
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
        },
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