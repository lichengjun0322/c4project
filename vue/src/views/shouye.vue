<template>
  <div class="app_shouye" ref="shouye"></div>
</template>

<script>
//import request from "../utils/request.js"
import axios from "axios";

export default {
  data(){
    return {
      new_html:""
    }
  },
  created() {
    axios.interceptors.response.use(res => {
      // 请求成功对响应数据做处理，此处返回的数据是axios.then(res)中接收的数据
      if (res.data.status !== 1&&res.data.status !==200) {
        // code值为 0 或 200 时视为成功
        return Promise.reject(res)
      }
      return Promise.resolve(res)
    }, err => {
      // 在请求错误时要做的事儿

      // 此处返回的数据是axios.catch(err)中接收的数据
      return Promise.reject(err)
    })
    axios.defaults.headers.get['Access-Control-Allow-Origin'] = "*";
    axios.defaults.headers.post['Access-Control-Allow-Origin'] = "*";
    axios.get("http://localhost:8080/api").then((res) => {
      console.log("这里是good");
      this.$refs.shouye.innerHTML=res.data+"<style>.bbs-index-web-bbsindexWarp {min-width:80%; max-width:100%;}</style>";
    }).catch((err) => {
      console.log(err);
      console.log("这里是err");
      this.$refs.shouye.innerHTML=err.data+"<style>.bbs-index-web-bbsindexWarp {min-width:80%; max-width:100%;}</style>";
    })
  }
}
</script>



<style scoped>

.el-card__body {
  padding: 10px;
}

.userCard {
  height: 120px;
  display: flex;
  border-bottom: 2px solid #DCDFE6;
  border-radius: 2px;
}

.userInfo {
  width: auto;
  padding: 6% 0 0 6%;
}

.important-font {
  font-weight: 900;
  font-size: 25px;
  color: #ffffff;
}

.secondary-font {
  color: #F1F2F7;
}

.login-info {
  height: 40px;
  text-align: left;
  color: #ffffff;
}


.el-card {
  border: none;
}

.num {
  display: flex;
  flex-wrap: wrap;
}

.graph {
  margin: 15px 0 0 0;
}


.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}


.userInfo {
  width: auto;
  padding: 6% 0 0 6%;
}


.OrderCard {
  margin: 0 0 30px 30px;
  border: #DCDFE6 1px solid;
  border-radius: 10px;
}

i {
  width: 30%;
  line-height: 120px;
  font-size: 30px;
  color: #fff
}

.test /deep/ .el-calendar-table .el-calendar-day {
  width: 80px;
  height: 50px;
}

.is-selected {
  color: #f8a535;
  font-size: 10px;
  margin-top: 5px;
}

.is-success {
  color: #67c23a;
}

.is-danger {
  color: #f56c6c;
}

.is-common {
  color: #909399;
}

#calendar .el-button-group>.el-button:not(:first-child):not(:last-child):after {
  content: '当月';
}

/*最外层透明*/
.regional_table /deep/ .el-table,
.regional_table /deep/ .el-table__expanded-cell {
  background-color: transparent;
}

/* 表格内背景颜色 */
.regional_table /deep/ .el-table th,
.regional_table /deep/ .el-table tr,
.regional_table /deep/ .el-table td {
  background-color: transparent !important;
}

/*.el-table{*/
/*  border: 1px solid black;*/
/*}*/

.el-table>>>.el-table__row>td {
  /* 去除表格线 */
  border: none;
}

/deep/ .el-table {
  color: #DD1144 !important;
  font-size: 12px;
}

.nowEcharts {
  width: 100%;
  height: 210px;
}
</style>
