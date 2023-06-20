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

        <el-row>
          <el-col :span="2">
            <el-button type="primary" @click="upload()">上传视频<i class="el-icon-upload el-icon--right"></i></el-button>
          </el-col>
          <el-col :span="2">
            <el-button type="primary" @click="convert()" v-loading.fullscreen.lock="fullscreenLoading">开始转换<i class="el-icon-s-promotion el-icon--right"></i></el-button>
          </el-col>
        </el-row>

        <el-row style="margin-top: 50px">
          <el-col :span="12" offset="6">
            <video-player  class="video-player vjs-custom-skin"
                           ref="videoPlayer"
                           :playsinline="true"
                           :options="playerOptions1"
            ></video-player>
          </el-col>
        </el-row>
      <el-row>
        <el-col :span="10" offset="1">
          <video-player  class="video-player vjs-custom-skin"
                         ref="videoPlayer"
                         :playsinline="true"
                         :options="playerOptions2"
          ></video-player>
        </el-col>
        <el-col :span="10" offset="2">
          <video-player  class="video-player vjs-custom-skin"
                         ref="videoPlayer"
                         :playsinline="true"
                         :options="playerOptions3"
          ></video-player>
        </el-col>
      </el-row>
      <el-card style="width:100%; display:none;"  ref="caonima">
        <div style="width: 100%; height: 500px; display:flex; ">
        <el-image
            style="width: 900px; height: 500px; display:block; flex: 2"
            :src="url"
            :fit="fit"></el-image>
          <div class="rig" style="flex:1; margin-left: 50px;" >
          <div class="text item" style="color: blue;font-size: 20px">
            蓝色：自然状态下球的轨迹
          </div>
          <div class="text item" style="color: red;font-size: 20px">
            红色：未取得进球
          </div>
          <div class="text item" style="color: purple;font-size: 20px">
            紫色：未确定状态的球的
          </div>
          <div class="text item" style="color: green;font-size: 20px">
            绿色：球进啦！！
          </div>
          </div>
        </div>
      </el-card>

  </div>
</template>

<script>
import {videoPlayer} from 'vue-video-player'
import 'video.js/dist/video-js.css'
export default {
    name: 'analysic',
    data() {
        return {
            fullscreenLoading: false,
            dialogFormVisible: false,
            formLabelWidth:200,
            all_name:[],
            playerOptions1:[],
            playerOptions2:[],
          playerOptions3:[],
          url:require("../../public/img/result.png")
        }
    },
    methods: {
      queding(){
        this.dialogFormVisible=false;
        let arrs = {
          playbackRates: [0.5, 1.0, 2.0], //播放速度
          autoplay: false, //如果true,浏览器准备好时开始回放。
          muted: false, // 默认情况下将会消除任何音频。
          loop: false, // 导致视频一结束就重新开始。
          preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
          language: "zh-CN",
          aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
          fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
          sources: [
            {
              type: "video/mp4",
              src: require('../../public/video/origin.mp4') //url地址
            }
          ],
          poster: "", //封面地址
          notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
          controlBar: {
            timeDivider: true,
            durationDisplay: true,
            remainingTimeDisplay: false,
            fullscreenToggle: true //全屏按钮
          }
        };
        this.playerOptions1=arrs;
      },
      upload(){
        this.dialogFormVisible=true;
      },
      convert(){
        setTimeout(()=>{
          console.log(this.$refs.caonima);
          this.$refs.caonima.$el.style.display="block";
          let arrs = {
            playbackRates: [0.5, 1.0, 2.0], //播放速度
            autoplay: false, //如果true,浏览器准备好时开始回放。
            muted: false, // 默认情况下将会消除任何音频。
            loop: false, // 导致视频一结束就重新开始。
            preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
            language: "zh-CN",
            aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
            fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
            sources: [
              {
                type: "video/mp4",
                src: require('../../public/video/2.mp4') //url地址
              }
            ],
            poster: "", //封面地址
            notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
            controlBar: {
              timeDivider: true,
              durationDisplay: true,
              remainingTimeDisplay: false,
              fullscreenToggle: true //全屏按钮
            }
          };
          let arrs2 = {
            playbackRates: [0.5, 1.0, 2.0], //播放速度
            autoplay: false, //如果true,浏览器准备好时开始回放。
            muted: false, // 默认情况下将会消除任何音频。
            loop: false, // 导致视频一结束就重新开始。
            preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
            language: "zh-CN",
            aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
            fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
            sources: [
              {
                type: "video/mp4",
                src: require('../../public/video/3.mp4') //url地址
              }
            ],
            poster: "", //封面地址
            notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
            controlBar: {
              timeDivider: true,
              durationDisplay: true,
              remainingTimeDisplay: false,
              fullscreenToggle: true //全屏按钮
            }
          };
          this.playerOptions2=arrs;
          this.playerOptions3=arrs2;
        },5000);

      },
    }

}
</script>

<style>
.all {
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
    width:50px;
    height:40px;
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

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
  margin-left: 1000px;
}

/*.d2{*/
/*  vertical-align: middle;*/
/*}*/

</style>