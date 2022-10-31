import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";
import router from "@/router/router";
import Common from "@/components/Common";
import store from "@/store";
// axios.defaults.baseURL = "http://39.106.15.166:8986/"
axios.defaults.baseURL = "http://localhost:8986/"
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$Common = Common;
Vue.use(ElementUI);
new Vue({
    render: h => h(App),
    router:router,
    store
}).$mount('#app')
