import Vue from "vue";
import Vuex from "vuex"

Vue.use(Vuex)
const store = new Vuex.Store({
    state: {
        count: 0,
        name: '登录',
        userId: 0,
        role: 0,
        topicId: '',
        topicTitle:'',
        topicContent:'',
        topicType:'',
        userImg:'@/',
        topicCid:'全部',
        replyTimes:0,
        replyId:'',
        recycleState:'主题',
        replyState:false,
        replyType:'',
        paginationState:false,
        publishState:false,
        floor:0,
        floorUserName:0,
        myState:''
    },
    mutations: {
        increment(state) {
            state.count++
        },
        login(name) {
            console.log(name)
            this.state.name = name;
        },
        signOut(state) {
            state.name = '登录'
        }
    }
})
export default store