<template>
    <div class="all">
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



        <div class="header_01">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>视频管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>



        <div class="all_button">
            <el-button @click="this.dialogFormVisible=true">上传视频</el-button>
            <el-button @click="openFullScreen1" v-loading.fullscreen.lock="fullscreenLoading">开始转换</el-button>
        </div>
        <div class="bodyer">
            <vue-core-video-player :src="1" ref="video_video" class="vid"></vue-core-video-player>
            <vue-core-video-player :src="1" ref="video_video" class="vid"></vue-core-video-player>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            fullscreenLoading: false,
            dialogFormVisible: false,


        };
    },
    methods: {
        queding(){
            this.dialogFormVisible = false;
        },
        openFullScreen1() {
            this.fullscreenLoading = true;
            setTimeout(() => {
                this.fullscreenLoading = false;
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
    display:flex;
    flex-direction: column;
    justify-content: center;

}
.header_01 {
    width:100%;
    height:10%;
    background-color: rgb(240,240,240);
}
.all_button {
    width:100%;
    height:10%;

}
.all_button>.el-button {
    width:10%;
    height:100%;
}
.bodyer_01 {
    width:100%;
    height:80%;
    display:flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
}
.bodyer_01>.vid {
    display:block;
    width:40%;
    height:80%;
}
</style>