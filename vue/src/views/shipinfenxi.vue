<template>
    <div class="big">
        <!-- 导航 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>视频分析</el-breadcrumb-item>
        </el-breadcrumb>

        <div class="bottom">
            <div class="left">
                <vue-core-video-player :src="this.$route.query.url" ref="video_video"></vue-core-video-player>
            </div>
            <div class="right">
                <div class="top_top">
                    <div>
                        <download-excel class="excel" :data="all_data" :fields="data_fields"
                            type="xls"><el-button>excel</el-button></download-excel>
                        <el-button>pdf</el-button>
                    </div>
                    <div>
                        <el-button @click="openFullScreen1" v-loading.fullscreen.lock="fullscreenLoading">开始转换</el-button>
                        <el-button @click="go()">跳转</el-button>
                    </div>
                </div>
                <div class="come_out"></div>
                <div class="inp">
                    <input type="text" class="enter" />
                    <div class="but">
                        <button type="primary" class="nihao" @click="cli()">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            all_data: [],
            data_fields: {
                time: "time",
                advices: "text",
            },

            fullscreenLoading: false


        };
    },
    methods: {
        go(){
            this.$router.push("/tongjirizhi");
        },
        openFullScreen1() {
            this.fullscreenLoading = true;
            this.$refs.video_video.pause();
           // this.$refs.video_video.src="1";
            console.log(this.$refs.video_video.src);
            setTimeout(() => {
                this.fullscreenLoading = false;
                this.$message({
                    message: '转换成功',
                    type: 'success'
                });
            }, 5000);
        },
        cli() {
            //这里是点击按钮
            if (document.querySelector(".enter").value == "") {
                console.log("");
            } else {
                //获取时间
                let valueName = (
                    new Date().getFullYear() +
                    "/" +
                    (new Date().getMonth() + 1) +
                    "/" +
                    new Date().getDate() +
                    " " +
                    (new Date().getHours() >= 10
                        ? new Date().getHours()
                        : "0" + new Date().getHours()) +
                    ":" +
                    (new Date().getMinutes() >= 10
                        ? new Date().getMinutes()
                        : "0" + new Date().getMinutes()) +
                    ":" +
                    (new Date().getSeconds() >= 10
                        ? new Date().getSeconds()
                        : "0" + new Date().getSeconds())
                ).toString();

                //获取一个实施的对象存储数据
                let data = {
                    time: valueName,
                    text: document.querySelector(".enter").value,
                };
                this.all_data.push(data);

                let new_node_01 = document.createElement("LI");
                let text_node_01 = document.createTextNode(valueName);
                new_node_01.appendChild(text_node_01);

                new_node_01.classList.add("li_01");

                let new_node = document.createElement("LI");
                let text_node = document.createTextNode(
                    document.querySelector(".enter").value
                );
                new_node.appendChild(text_node);
                new_node.classList.add("li_02");

                //总的div
                let new_node_02 = document.createElement("DIV");
                new_node_02.appendChild(new_node_01);
                new_node_02.appendChild(new_node);

                document.querySelector(".come_out").appendChild(new_node_02);
                document.querySelector(".enter").value = "";
            }
        },
    },
};
</script>


<style scoped>
.el-breadcrumb {
    margin-bottom: 20px;
}

.excel {
    width: 45%;
    height: 95%;
}

.excel>.el-button {
    width: 100%;
    height: 100%;
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
    display: flex;
    flex-direction: column;
}

.come_out {
    width: 100%;
    height: 70%;
    background-color: #fff;
    border: black solid 1px;
    display: flex;
    flex-direction: column;
    overflow-y: scroll;
}

.top_top {
    width: 100%;
    height: 20%;
    display: flex;
    flex-direction: column;
    align-items: center;
    align-content: stretch;
    justify-content: space-between;
}

.top_top>div {
    width: 100%;
    height: 50%;
    display: flex;
    align-items: center;
    align-content: stretch;
    justify-content: space-between;
}

.el-button {
    width: 45%;
    height: 95%;
}

.come_out>div {
    width: 100%;
    height: 10%;
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
    height: 30%;
    font-size: small;
    list-style: none;
}

.li_02 {
    height: 70%;
    font-size: large;
    list-style: none;
}
</style>