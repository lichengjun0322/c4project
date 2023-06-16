const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})


module.exports = {
  // 相当于webpack-dev-server，对本地服务器进行配置 
  devServer: {
    //https:true,
    hot:true,
      proxy: {
          "/api": {
              target: "https://www.hupu.com", // 需要跨域的目标url，我这里用到的是豆瓣API
              changeOrigin: true, // 将基于名称的虚拟托管网站的选项，如果不配置，请求会报404
              ws: true,
              pathRewrite: {
                  "^/api": ''
              }
          }
      }
  }
}
