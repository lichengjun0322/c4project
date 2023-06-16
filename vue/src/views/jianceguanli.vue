<template>
 
  <div class="big">
    <!-- 导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>视频管理</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="bottom">
            <div class="left"><vue-core-video-player :src="this.$route.params.url"></vue-core-video-player>
            </div>
            <div class="right">
                <div class="come_out"></div>
                <div class="inp">
                    <input type="text" class="enter">
                    <div class="but"> <button type="primary" class="nihao" @click="cli()">主要按钮</button></div>
                </div>
            </div>
        </div>
  </div>
</template>


<style scoped>
.el-breadcrumb {
  margin-bottom: 20px;
}
.big {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.top {
    width: 100%;
    height: 7%;
    background-color: red;
}

.bottom {
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: space-between;

}

.left {
    width: 74%;
    height: 100%;
    background-color: red;
}

.right {
    width: 24%;
    height: 100%;
    background-color: green;
    display: flex;
    flex-direction: column;
}

.come_out {
    width: 100%;
    height: 90%;
    background-color: #fff;
    border: black solid 1px;
    display:flex;
    flex-direction: column;
    overflow-y: scroll;

}
.come_out>div {
    width:100%;
    height:10%;
}
/* .come_out>div>li {
    
    height:10%;
    width:100%;

} */
.inp {
    width: 100%;
    height: 10%;
    display: flex;
}

input {
    display: block;
    width: 60%;
    height: 100%;
}

.but {
    width: 40%;
    height: 100%;
    background-color: #fff;
}

.nihao {
    width: 100%;
    height: 100%;

}
.li_01 {
    height:30%;
    font-size:small;
    list-style: none;
}
.li_02 {
    height:70%;
    font-size:large;
    list-style: none;
}
</style>