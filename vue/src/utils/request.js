// import axios from 'axios'

// const request = axios.create({
//     baseURL: 'http://localhost:8088/',  // 注意！！ 这里是全局统一加上了 '/api' 前缀，也就是说所有接口都会加上'/api'前缀在，页面里面写接口的时候就不要加 '/api'了，否则会出现2个'/api'，类似 '/api/api/user'这样的报错，切记！！！
//     timeout: 5000
// })

// // request 拦截器
// // 可以自请求发送前对请求做一些处理
// // 比如统一加token，对请求参数统一加密
// request.interceptors.request.use(config => {
//     config.headers['Content-Type'] = 'application/json;charset=utf-8';

//     // config.headers['token'] = user.token;  // 设置请求头
//     return config
// }, error => {
//     return Promise.reject(error)
// });

// // response 拦截器
// // 可以在接口响应后统一处理结果
// request.interceptors.response.use(
//     response => {
//         let res = response.data;
//         // 如果是返回的文件
//         if (response.config.responseType === 'blob') {
//             return res
//         }
//         // 兼容服务端返回的字符串数据
//         if (typeof res === 'string') {
//             res = res ? JSON.parse(res) : res
//         }
//         return res;
//     },
//     error => {
//         console.log('err' + error) // for debug
//         return Promise.reject(error)
//     }
// )


// export default request

import axios from 'axios';

//import {Message} from 'element-ui';
const request = axios.create({
    baseUrl:"/api",
    setTimeout:8000,

});
axios.defaults.headers.get['Access-Control-Allow-Origin'] ="*";
axios.defaults.headers.post['Access-Control-Allow-Origin'] ="*";

axios.interceptors.response.use(res => {
  // 请求成功对响应数据做处理，此处返回的数据是axios.then(res)中接收的数据
  if (res.code ==0 || res.code == 200) {
     // code值为 0 或 200 时视为成功
     return Promise.resolve(res)
  }
  return Promise.reject(res)
}, err => {
    // 在请求错误时要做的事儿

    // 此处返回的数据是axios.catch(err)中接收的数据
    return Promise.reject(err)
})
// // 添加请求拦截器
// axios.interceptors.request.use(function (config) {
//     // 在发送请求之前做些什么
//     return config;
//   }, function (error) {
//     // 对请求错误做些什么
//     return Promise.reject(error);
//   });

// // 添加响应拦截器
// axios.interceptors.response.use(function (res,error) {
//   let {status,message}=res.data;
//   // 请求成功对响应数据做处理，此处返回的数据是axios.then(res)中接收的数据
//   if (status!=200) {
//     // code值为 0 或 200 时视为成功
//     Message({message:message || error});
//  }
//  return Promise.reject(res)
//     // 对响应数据做点什么

//   }, function (error) {
//     // 对响应错误做点什么
//     return Promise.reject(error);
//   });

export default request;