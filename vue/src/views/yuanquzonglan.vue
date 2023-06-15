<template>
  <div>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>园区总览</el-breadcrumb-item>
      </el-breadcrumb>
      <el-row gutter="20" style="margin-bottom: 20px">
        <el-col span="12">
          <el-container>
            <div class='demo' style="width: 900px">
              <h1 style="font-size: 30px">园区总览监控</h1>
            </div>
          </el-container>
        </el-col>
      </el-row>
      <el-row gutter="20">
          <el-col span="8" v-for="(item,index) in yuanqu" :key="item.id">
            <el-card shadow="hover" style="margin-top: 10px">
              {{ item.name }}
              <el-tag v-if="item.id%2===0">空闲中</el-tag>
              <el-tag v-else type="danger">作业中</el-tag>
            </el-card>
            <video-player
                class="video-player vjs-custom-skin"
                ref="videoPlayer"
                :playsinline="true"
                :options="playerOptions[index]"
            ></video-player>
          </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>

export default {
  name: "yuanquzonglan",
  data() {
    return {
      yuanqu: [
        {id: 1, name: "园区一", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/双屏视频-613-冷备用转热备用-不规范.mp4')},
        {id: 2, name: "园区二", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/双屏视频-613-检修转热备用-规范.mp4')},
        {id: 3, name: "园区三", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/数据样例视频.mp4')},
        {id: 4, name: "园区四", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/双屏视频-613-检修转热备用-规范.mp4')},
        {id: 5, name: "园区五", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/双屏视频-613-冷备用转热备用-不规范.mp4')},
        {id: 6, name: "园区六", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/数据样例视频.mp4')},
        {id: 7, name: "园区七", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/双屏视频-613-检修转热备用-规范.mp4')},
        {id: 8, name: "园区八", type: "video/mp4", src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/数据样例视频.mp4')},
      ],
      videosource: [],
      ALLData: [],
      form: {},
      playsinline: true,
      playerOptions: [],
      options: "",
      value: [],
      yuanquoptions: [{
        value: '1',
        label: '园区1'
      }, {
        value: '2',
        label: '园区2'
      }, {
        value: '3',
        label: '园区3'
      }, {
        value: '4',
        label: '园区4'
      }, {
        value: '5',
        label: '园区5'
      }, {
        value: '6',
        label: '园区6'
      }, {
        value: '7',
        label: '园区7'
      },]
    }
  },
  components: {},
  created() {
    this.getplayeroptions();
  },
  methods: {
    handleChange(value) {
      console.log(value);
    },
    getplayeroptions() {
      for (var i = 0; i < this.yuanqu.length; i++) {
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
              src: this.yuanqu[i].src //url地址
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
        this.playerOptions.push(arrs);
      }
    }
  }
}
</script>

<style scoped>

</style>