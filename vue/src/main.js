import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import 'font-awesome/css/font-awesome.min.css'
import axios from "axios"; 
import store from './store'
import VueCoreVideoPlayer from 'vue-core-video-player'
import echarts from 'echarts'
import JsonExcel from "vue-json-excel";
Vue.prototype.$echarts = echarts
// 引入方式一
import 'vue-video-player/src/custom-theme.css'
import 'video.js/dist/video-js.css'
Vue.use(VueCoreVideoPlayer)
import VideoPlayer from 'vue-video-player'
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
Vue.use(VideoPlayer)

axios.defaults.baseURL = "http://localhost:8088"
Vue.prototype.$http=axios
Vue.config.productionTip = false
Vue.component('downloadExcel',JsonExcel);
Vue.use(ElementUI,{size:"mini"});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
