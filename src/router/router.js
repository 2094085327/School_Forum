import VueRouter from "vue-router";
import Vue from "vue";
import reply from "@/components/reply";
import login from "@/components/login.vue";
import register from "@/components/register";
import helloPage from "@/components/helloPage";
import Pagination from "@/components/Pagination";
import topicDetail from "@/components/topic-detail";
import publish from "@/components/publish";
import whitePage from "@/components/whitePage";
import whitePage2 from "@/components/whitePage2";
import deletePage from "@/components/deletePage";
import recycleBin from "@/components/recycleBin";
import recoveryPage from "@/components/recoveryPage";
import personInfo from "@/components/personInfo";
import myContentNav from "@/components/myContentNav";
import myContent from "@/components/myContent";
import myTopic from "@/components/myTopic";
import myReply from "@/components/myReply";

Vue.use(VueRouter)
const router = new VueRouter({
    routes: [
        {path: '/', redirect: '/helloPage'},
        {path: '/helloPage', component: helloPage},
        {path: '/reply', component: reply},
        {path: '/login', name: 'login', component: login},
        {path: '/register', name: 'register', component: register},
        {path: '/Pagination', name: 'pagination', component: Pagination},
        {path: '/topicDetail/:id', name: 'topic-detail', component: topicDetail},
        {path: '/publish', name: 'publish', component: publish},
        {path: '/whitePage', name: 'whitePage', component: whitePage},
        {path: '/whitePage2', name: 'whitePage2', component: whitePage2},
        {path: '/deletePage', name: 'deletePage', component: deletePage},
        {path: '/recycleBin', name: 'recycleBin', component: recycleBin},
        {path: '/recoveryPage', name: 'recoveryPage', component: recoveryPage},
        {path: '/personInfo', name: 'personInfo', component: personInfo},
        {path: '/myContentNav', name: 'myContentNav', component: myContentNav},
        {path: '/myContent', name: 'myContent', component: myContent},
        {path: '/myTopic', name: 'myTopic', component: myTopic},
        {path: '/myReply', name: 'myReply', component: myReply},
    ]
})
export default router