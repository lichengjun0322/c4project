<template>
    <div class="big">
        <!-- 导航 -->
        <el-dialog title="新增视频" :visible.sync="dialogFormVisible">
            <el-form>
                <el-form-item label="视频名称" :label-width="formLabelWidth">
                    <el-input v-model="all_name.name" autocomplete="off" width="200"></el-input>
                </el-form-item>
                <el-form-item label="视频文件" :label-width="formLabelWidth">
                    <!-- 这里的action为服务器地址 -->
                    <el-upload class="upload-demo" :data="all_name" ref="upload" :auto-upload="false" drag
                        action="https://jsonplaceholder.typicode.com/posts/" multiple>
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传视频文件，且不超过500Mb</div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="queding()">确
                    定</el-button>
            </div>
        </el-dialog>
        <div class="big_01">
            <div class="head_head">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>视频管理</el-breadcrumb-item>
                </el-breadcrumb>
                <el-button @click="dialogFormVisible = true"> 新增视频</el-button>
            </div>
            <div class="body">
                <div class="column" v-for="(item, index) in all_src" :key="index"><a :href="getUrl(item.video_href)"
                        class="a_01"><img :src="item.img_src" alt=""></a><a class="a_02" :href="getUrl(item.video_href)">
                        <p>20210512140033333333</p>
                    </a></div>

            </div>
            <div class="fenye">
                <el-pagination background layout="prev, pager, next" :total=page_count>
                </el-pagination>
            </div>

        </div>
    </div>
</template>

<script>
import bus from "../Bus.js/bus"
//import Get_data from "../../api/video";
export default {

    data() {
        return {

            from: "",
            dialogFormVisible: false,
            all_name: {
                name: ""
            },
            formLabelWidth: "100px",

            page_count: 1,    //页数量
            img_nums: 2,   //视频数量
            all_src: [{ img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }, { img_src: require("../../public/img/jietu2.png"), video_href: require("../../public/video/202105121400.mp4") }],
            // img_src:[require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),require("../../public/img/jietu2.png"),],    //返回数组 图像地址
            // video_href:[require("../../public/video/202105121400.mp4")]   //视频地址
        }
    },
    methods: {
        getUrl(p) {
            return "/shipinfenxi?url=" + encodeURIComponent(p);
        },
        get_data() {
            Get_data();
        },
        queding() {
            //上传
            this.$refs.upload.submit();
            this.dialogFormVisible = false;
        }
    },
    created() {
        //在开始之前获取
        //this.all_src=this.get_data();

    },
    mounted() {
        bus.$on("add_video", () => {
            console.log("yes");
            this.dialogFormVisible = true;
        })
    }
}
</script>


<style scoped>


.big {
    width: 100%;
    height: 100%;
}

.big_01 {
    width: 100%;
    height: 100%;

}

.head_head {
    width: 100%;
    height: 7%;
    margin-top: 10px;
    margin-bottom: 10px;
    background-color: rgb(245, 245, 245);
    position: relative;
}

.head_head>.el-button {
    position: absolute;
    left: 89.5%;
    height: 95%;
    width: 10%;


}
.el-breadcrumb {
    position: absolute;
    left: 0.5%;
    height: 95%;
    width: 10%;
    padding-top:1%;
}

.body {
    /* margin:0 auto;
    width:80%;
    height:800px; */
    width: 100%;
    height: 83%;
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: space-between;
    align-content: stretch;
}

.column {
    width: 24%;
    height: 30%;
    /* background-color: red; */
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-content: center;
}

.fenye {
    width: 100%;
    height: 10%;
}

.el-pagination {
    text-align: center;
    padding-top: 4%;
}

.a_01 {
    display: block;
    width: 100%;
    height: 76%;
}

.a_02 {
    display: block;
    width: 100%;
    height: 20%;
    text-decoration: none;
    line-height: 20%;
    text-align: center;
    color: black;
}

.a_01:hover {
    cursor: pointer;
}

img {
    width: 100%;
    height: 100%;

}</style>