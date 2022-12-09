import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'

import store from './store'
// /* eslint-disable */
//
// // 引入bootstrap
// // import $ from 'jquery'
// import 'bootstrap'
// import 'jquery/dist/jquery.min'
// import 'bootstrap/dist/css/bootstrap.min.css'
// import 'bootstrap/dist/js/bootstrap.js'


Vue.prototype.axios = axios;
Vue.use(VueAxios, axios);
Vue.use(ElementUI)


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
