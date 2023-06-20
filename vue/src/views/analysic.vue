<template>
    <div class="all">
        <el-dialog title="新增视频" :visible.sync="dialogFormVisible">
            <el-form>
                <el-form-item label="视频名称" :label-width="formLabelWidth">
                    <el-input v-model="all_name.name" autocomplete="off" width="200"></el-input>
                </el-form-item>
                <el-form-item label="视频文件" :label-width="formLabelWidth">
                    
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

        <div class="header_01">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>视频管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>


        <div class="all_button">
            <el-button @click="dialogFormVisible=true">上传视频</el-button>
            <el-button @click="openFullScreen1" v-loading.fullscreen.lock="fullscreenLoading">开始转换</el-button>
        </div>

        
        <div class="bodyer_01">
            <vue-core-video-player :src="require('../../public/video/2.mp4')" ref="video_video_01" class="vid_01"></vue-core-video-player>
            
            <vue-core-video-player :src="require('../../public/video/2.mp4')" ref="video_video_02" class="vid_02"></vue-core-video-player>
        </div>
    </div>
</template>

<script>
export default {
    name: 'analysic',
    data() {
        return {
            fullscreenLoading: false,
            dialogFormVisible: false,
            formLabelWidth:200,
            all_name:[]


        };
    },
    methods: {
        queding(){
            this.dialogFormVisible = false;
            this.$refs.video_video_01.src=require("../../public/video/origin.mp4");
        },
        openFullScreen1() {
            this.fullscreenLoading = true;
            setTimeout(() => {
                this.fullscreenLoading = false;
                this.$refs.video_video_02.src=require("../../public/video/2.mp4");
                this.$message({
                    message: '转换成功',
                    type: 'success'
                });
            }, 5000);
        },
    }
}
</script>

<style>
.all {
    height:100%;
    width:100%;
    display:flex;
    flex-direction: column;
    justify-content: center;

}
.header_01 {
    width:100%;
    height:5%;
    background-color: rgb(240,240,240);
    margin-bottom: 10px;
}
.all_button {
    width:100%;
    height:7%;
    

}
.all_button>.el-button {
    width:10%;
    height:100%;

}
.bodyer_01 {
    width:100%;
    height:87%;
    display:flex;
    flex-direction: row;
    justify-content:flex-start;
    align-items: center;
}
.bodyer_01>.vid_01 {
    display:block;
    width:40%;
    height:80%;
}

.bodyer_01>.vid_02 {
    display:block;
    width:40%;
    height:80%;
}
</style>