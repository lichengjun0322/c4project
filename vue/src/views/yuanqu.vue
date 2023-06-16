<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item >园区导航</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row gutter="20" style="margin-bottom: 20px">
      <el-col span="4">
        <el-container>
          <div class='demo' style="width: 900px">
            <h1 style="font-size: 30px">{{this.$route.query.name}}</h1>
          </div>
        </el-container>
      </el-col>

      <el-col span="2">
        <el-container>
          <el-button type="primary" size="medium" @click="jump()">制作操作票</el-button>
        </el-container>
      </el-col>
    </el-row>
    <el-row>
      <div class="big">
        <el-card style="width:400px">
          <el-button type="danger" plain>开始转换</el-button>
          <el-button type="danger" plain>文本导出</el-button>
          <el-button type="danger" plain>Word导出</el-button>
        </el-card>
        <div class="bottom">
          <div class="left"><video-player class="video-player vjs-custom-skin"
                                          ref="videoPlayer"
                                          :playsinline="true"
                                          @play="changestatus1"
                                          :options="playerOptions1[0]">
          </video-player>
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
    </el-row>
<!--    <el-row gutter="10">-->
<!--      <el-col span="12">-->
<!--        <video-player class="video-player vjs-custom-skin"-->
<!--                      ref="videoPlayer"-->
<!--                      :playsinline="true"-->
<!--                      :options="playerOptions">-->
<!--        </video-player>-->
<!--      </el-col>-->
<!--      <el-col span="12">-->
<!--        <el-table-->
<!--            :data="tableData"-->
<!--            header-cell-style="font-size:15px"-->
<!--            cell-style="font-size:15px"-->
<!--            style="width: 100%">-->
<!--          <el-table-column label="操作信息" align="center" >-->
<!--            <el-table-column label="操作票信息" prop="info_on_list"></el-table-column>-->
<!--            <el-table-column label="实际操作信息" prop="info_actual"></el-table-column>-->
<!--          </el-table-column>-->
<!--          <el-table-column label="操作人" width="80" prop="operator"></el-table-column>-->
<!--          <el-table-column label="动作是否正确" width="115" prop="check"></el-table-column>-->
<!--        </el-table>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    <el-row gutter="20">-->
<!--      <el-col span="8" v-for="(item,index) in yuanqu" :key="item.id">-->
<!--        <el-card shadow="hover" style="margin-top: 10px">-->
<!--          {{ item.name }}-->
<!--        </el-card>-->
<!--        <video-player-->
<!--            class="video-player vjs-custom-skin"-->
<!--            ref="videoPlayer"-->
<!--            :playsinline="true"-->
<!--            :options="playerOptions1[index]"-->
<!--        ></video-player>-->
<!--      </el-col>-->
<!--    </el-row>-->


<!--    <el-row>-->
<!--      <el-col span="6">-->
<!--        <el-card>-->
<!--          <el-row>-->
<!--            <div class="text item" style="align-content: center;margin-left: 140px;font-size: 20px;">-->
<!--              着装诊断-->
<!--            </div>-->
<!--          </el-row>-->
<!--          <el-row>-->
<!--            <div>-->
<!--              <video-player class="video-player vjs-custom-skin"-->
<!--                            ref="clothvideo"-->
<!--                            :playsinline="true"-->
<!--                            @play="changestatus"-->
<!--                            :options="clothplayerOption">-->
<!--              </video-player>-->
<!--            </div>-->
<!--          </el-row>-->
<!--          <el-row v-for="(item,index) in clothdetect" :key="item.id" style="margin-bottom: 22px;">-->
<!--            <div v-if="item.status==1">-->
<!--              <el-alert-->
<!--                  class="myalert"-->
<!--                  :title=item.name-->
<!--                  type="success"-->
<!--                  show-icon-->
<!--                  :closable="false">-->
<!--              </el-alert>-->
<!--            </div>-->
<!--            <div v-if="item.status==0" >-->
<!--              <el-alert-->
<!--                  class="myalert"-->
<!--                  :title=item.name-->
<!--                  type="error"-->
<!--                  show-icon-->
<!--                  :closable="false">-->
<!--              </el-alert>-->
<!--            </div>-->

<!--          </el-row>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--      <el-col span="17" style="margin-left: 30px">-->
<!--        <el-card>-->
<!--            <div class="text item" style="align-content: center;margin-left: 530px;margin-bottom: 20px;font-size: 20px;">-->
<!--              操作判断-->
<!--            </div>-->
<!--          <el-row>-->
<!--            <el-col span="7" style="margin-left: 33px">-->
<!--              <div>-->
<!--                <video-player class="video-player vjs-custom-skin"-->
<!--                              ref="videoPlayer"-->
<!--                              :playsinline="true"-->
<!--                              @play="changestatus1"-->
<!--                              :options="playerOptions1[0]">-->
<!--                </video-player>-->
<!--              </div>-->
<!--            </el-col>-->
<!--            <el-col span="7" style="margin-left: 33px">-->
<!--              <div>-->
<!--                <video-player class="video-player vjs-custom-skin"-->
<!--                              ref="videoPlayer"-->
<!--                              :playsinline="true"-->
<!--                              @play="changestatus2"-->
<!--                              :options="playerOptions1[1]">-->
<!--                </video-player>-->
<!--              </div>-->
<!--            </el-col>-->
<!--            <el-col span="7" style="margin-left: 33px">-->
<!--              <div>-->
<!--                <video-player class="video-player vjs-custom-skin"-->
<!--                              ref="videoPlayer"-->
<!--                              :playsinline="true"-->
<!--                              @play="changestatus3"-->
<!--                              :options="playerOptions1[2]">-->
<!--                </video-player>-->
<!--              </div>-->
<!--            </el-col>-->
<!--          </el-row>-->
<!--          <el-row style="margin-bottom: 15px">-->
<!--            <el-col span="7" style="margin-left: 33px;" v-for="(item) in step">-->
<!--              <el-card>-->
<!--                <div>-->
<!--                  <el-row>-->
<!--                    <el-alert-->
<!--                        :title=item.name-->
<!--                        type="info"-->
<!--                        :closable="false">-->
<!--                    </el-alert>-->
<!--                  </el-row>-->
<!--                  <el-row>-->
<!--                    <el-alert-->
<!--                        title="应操作："-->
<!--                        :description=item.wantstep-->
<!--                        type="info"-->
<!--                        show-icon-->
<!--                        :closable="false">-->
<!--                    </el-alert>-->
<!--                  </el-row>-->
<!--                  <el-row>-->
<!--                    <div v-if="item.status==1">-->
<!--                      <el-alert-->
<!--                          class="myalert"-->
<!--                          title="实操作："-->
<!--                          :description=item.actualstep-->
<!--                          type="success"-->
<!--                          show-icon-->
<!--                          :closable="false">-->
<!--                      </el-alert>-->
<!--                    </div>-->
<!--                    <div v-if="item.status==-1">-->
<!--                      <el-alert-->
<!--                          class="myalert"-->
<!--                          title="实操作："-->
<!--                          :description=item.actualstep-->
<!--                          type="warning"-->
<!--                          show-icon-->
<!--                          :closable="false">-->
<!--                      </el-alert>-->
<!--                    </div>-->
<!--                    <div v-if="item.status==0" >-->
<!--                      <el-alert-->
<!--                          class="myalert"-->
<!--                          title="实操作："-->
<!--                          :description=item.actualstep-->
<!--                          type="error"-->
<!--                          show-icon-->
<!--                          :closable="false">-->
<!--                      </el-alert>-->
<!--                    </div>-->
<!--                  </el-row>-->
<!--                  <el-row>-->
<!--                    <div v-if="item.status==1">-->
<!--                      <el-alert-->
<!--                          title="判定时间："-->
<!--                          type="success"-->
<!--                          show-icon-->
<!--                          :description="item.detecttime"-->
<!--                          :closable="false">-->
<!--                      </el-alert>-->
<!--                    </div>-->
<!--                    <div v-if="item.status==0">-->
<!--                      <el-alert-->
<!--                          title="判定时间："-->
<!--                          type="error"-->
<!--                          show-icon-->
<!--                          :description="item.detecttime"-->
<!--                          :closable="false">-->
<!--                      </el-alert>-->
<!--                    </div>-->
<!--                    <div v-if="item.status==-1">-->
<!--                      <el-alert-->
<!--                          title="判定时间："-->
<!--                          type="warning"-->
<!--                          show-icon-->
<!--                          :description="item.detecttime"-->
<!--                          :closable="false">-->
<!--                      </el-alert>-->
<!--                    </div>-->
<!--                  </el-row>-->
<!--                </div>-->
<!--              </el-card>-->
<!--            </el-col>-->
<!--          </el-row>-->


<!--        </el-card>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    <el-row>-->
<!--      <el-card style="padding-bottom: 50px">-->
<!--        <el-card shadow="hover" style="margin-top: 10px;width: 300px">-->
<!--          模型组合-->
<!--        </el-card>-->
<!--        <el-col span="5" style="margin-top: 20px">-->
<!--          <el-checkbox-group v-model="checkList" @change="ch">-->
<!--            <div v-for="(c,index) in tableData1" :key="index">-->
<!--              <el-card shadow="hover" style="margin-top: 10px;height: 60px;width: 300px">-->
<!--                <el-checkbox :label="c.key" style="width: 300px">{{c.name}}</el-checkbox>-->
<!--              </el-card>-->
<!--            </div>-->
<!--          </el-checkbox-group>-->
<!--          <el-button type="success" round @click="check" style="height: 60px;width: 200px;font-size: 20px;margin-top: 20px;">确认选择模型</el-button>-->
<!--        </el-col>-->
<!--        <el-col span="16">-->
<!--          <video-player class="video-player vjs-custom-skin"-->
<!--                        ref="videoPlayer"-->
<!--                        :playsinline="true"-->
<!--                        @play="detect"-->
<!--                        :options="playerOptions2">-->
<!--          </video-player>-->
<!--        </el-col>-->
<!--      </el-card>-->

<!--    </el-row>-->

  </div>
</template>

<script>

// 组件内引用vue-video-player插件
import {videoPlayer} from 'vue-video-player'
import 'video.js/dist/video-js.css'

export default {
  name: 'yuanqu1',
  data() {
    return {
      checkList:[],
      clothdetect:[
        {id:1,name:"身穿工作服",status: ""},
        {id:2,name:"佩戴绝缘手套",status: ""},
        {id:3,name:"佩戴安全帽",status: ""},
      ],
      step:[
        {id:1,name:"步骤1",wantstep:"远方/就地开关设置为就地",actualstep:"检测动作中...",detecttime:"检测动作中...",status:"-1"},
        {id:2,name:"步骤2",wantstep:"分合闸开关置于中间",actualstep:"检测动作中...",detecttime:"检测动作中...",status:"-1"},
        {id:3,name:"步骤3",wantstep:"接地保护跳闸开关打开",actualstep:"检测动作中...",detecttime:"检测动作中...",status:"-1"},
      ],
      stepvideo:[
        // {id:1,src:require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/步骤1.mp4')},
        // {id:2,src:require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/步骤2.mp4')},
        // {id:3,src:require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/步骤3.mp4')}
      ],
      yuanqu: [
        // {id: 1, name: "头盔检测", type: "video/mp4", value:1 ,src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/cloth-1.mp4')},
        // {id: 2, name: "头盔+工作服检测", type: "video/mp4", value:11 ,src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/cloth-11.mp4')},
        // {id: 3, name: "头盔+工作服+手套检测", type: "video/mp4", value:111 ,src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/cloth-111.mp4')},
        // {id: 4, name: "工作服检测", type: "video/mp4", value:10 ,src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/cloth-10.mp4')},
        // {id: 5, name: "手套检测", type: "video/mp4", value:100 ,src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/cloth-100.mp4')},
        // {id: 6, name: "头盔+手套检测", type: "video/mp4", value:101 ,src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/cloth-101.mp4')},
        // {id: 7, name: "工作服+手套检测", type: "video/mp4", value:110 ,src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/cloth-110.mp4')},
      ],
      playerOptions1: [],
      playerOptions: {
        playbackRates: [0.5, 1.0, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          // {
          //   type: "video/mp4",
          //   src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题\\tested.mp4')//url地址
          // }
        ],
        poster: "", //封面地址
        notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true //全屏按钮
        }
      },
      playerOptions2: {
        playbackRates: [0.5, 1.0, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          // {
          //   type: "video/mp4",
          //   src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题\\1_helmet_coat_glove_0001.mp4')//url地址
          // }
        ],
        poster: "", //封面地址
        notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true //全屏按钮
        }
      },
      clothplayerOption: {
        playbackRates: [0.5, 1.0, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          // {
          //   type: "video/mp4",
          //   src: require('E:\\我的文件\\各课课件及资料\\服务外包大赛\\服务外包大赛泰坦智慧赛题/服装检测.mp4')//url地址
          // }
        ],
        poster: "", //封面地址
        notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true //全屏按钮
        }
      },
      tableData:[
        {info_on_list:"断开 I段出线102开关",info_actual:"断开 I段出线102开关",operator:"张三",check:"正确"},
        {info_on_list:"拉出 10kV I段出线002手车开关至试验位置，查确已到位",info_actual:"拉出 10kV I段出线002手车开关至试验位置，查确已到位",operator:"张三",check:"正确"},
        {info_on_list:"取下 10kV I段出线002手车的二次插头，查确已取下",info_actual:"取下 10kV I段出线002手车的二次插头，查确已取下",operator:"张三",check:"正确"},
        {info_on_list:"拉出 10kV I段出线102开关002手车开关至检修位置，查确已到位",info_actual:"拉出 10kV I段出线102开关002手车开关至检修位置，查确已到位",operator:"张三",check:"正确"},
        {info_on_list:"断开 10kV I段出线102开关的控制及储能电源，查确已断开",info_actual:"断开 10kV I段出线102开关的控制及储能电源，查确已断开",operator:"张三",check:"正确"},
        {info_on_list:"锁上 10kV I段出线102开关柜门，并在柜门悬挂“止步，高压危险！”及“禁止合闸，有人工作”的标示牌",info_actual:"锁上 10kV I段出线102开关柜门，并在柜门悬挂“止步，高压危险！”及“禁止合闸，有人工作”的标示牌",operator:"张三",check:"正确"},
      ],
      tableData1: [{
        name: '头盔',
        key:1
      }, {
        name: '工作服',
        key:10
      }, {
        name: '手套',
        key:100
      },]
    }
  },
  components: {
  },
  created() {
    this.getplayeroptions();
  },
  methods: {
    jump () {
      this.$router.push('/make_oplist')
    },
    cli() {
      console.log(this.$route.params);
      //console.log(document.querySelector(".enter").value)
      if(document.querySelector(".enter").value==""){
        console.log("");
      }else{
        //获取时间
        let valueName =
            (new Date().getFullYear() +
                "/" +
                (new Date().getMonth() + 1) +
                "/" +
                new Date().getDate() +
                " " +
                (new Date().getHours() >= 10 ? new Date().getHours() : "0" + new Date().getHours()) +
                ":" +
                (new Date().getMinutes() >= 10 ? new Date().getMinutes() : "0" + new Date().getMinutes()) +
                ":" +
                (new Date().getSeconds() >= 10 ? new Date().getSeconds() : "0" + new Date().getSeconds())).toString();

        let new_node_01 = document.createElement("LI");
        let text_node_01 = document.createTextNode(valueName);
        new_node_01.appendChild(text_node_01);

        new_node_01.classList.add("li_01");


        let new_node=document.createElement("LI");
        let text_node=document.createTextNode(document.querySelector(".enter").value);
        new_node.appendChild(text_node);
        new_node.classList.add("li_02");

        //总的div
        let new_node_02=document.createElement("DIV");
        new_node_02.appendChild(new_node_01);
        new_node_02.appendChild(new_node);

        document.querySelector(".come_out").appendChild(new_node_02);
        document.querySelector(".enter").value="";
      }
    },
    getplayeroptions() {
      for (var i = 0; i < this.stepvideo.length; i++) {
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
              src: this.stepvideo[i].src //url地址
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
        this.playerOptions1.push(arrs);
      }
    },
    ch(){
      console.log(this.checkList)
    },
    check(){
      this.$notify({
        title: '模型设置成功',
        message: '已成功设置检测模型',
        type: 'success'
      });
      this.checkList.map(Number)
      let sum=0;
      for(let i=0;i<this.checkList.length;i++){
        sum+=parseInt(this.checkList[i]);
      }
      this.selectvalue=sum;
      console.log(sum);
      if(sum==1){
        this.playerOptions2.sources[0].src=this.yuanqu[0].src;
      }
      if(sum==11){
        this.playerOptions2.sources[0].src=this.yuanqu[1].src;
      }
      if(sum==111){
        this.playerOptions2.sources[0].src=this.yuanqu[2].src;
      }
      if(sum==10){
        this.playerOptions2.sources[0].src=this.yuanqu[3].src;
      }
      if(sum==100){
        this.playerOptions2.sources[0].src=this.yuanqu[4].src;
      }
      if(sum==110){
        this.playerOptions2.sources[0].src=this.yuanqu[6].src;
      }
      if(sum==101){
        this.playerOptions2.sources[0].src=this.yuanqu[5].src;
      }
      if(sum==0){
        this.$alert('请至少选择一种模型', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      }
    },
    detect(){
      setTimeout(() => {this.$notify({
        title: '检测成功',
        message: '已成功检测模型',
        type: 'success'
      });},6000);
    },
    changestatus(){
      setTimeout(() => {this.clothdetect[0].status=1,this.clothdetect[2].status=1},4200);
      setTimeout(() => {this.clothdetect[1].status=1},7000);
      setTimeout(() => {this.clothdetect[1].status=0},17500);
      setTimeout(() => {this.clothdetect[1].status=1},20500);
    },
    changestatus1(){
      setTimeout(() => {this.step[0].status=1,this.step[0].actualstep="远方/就地开关设置为就地",this.step[0].detecttime="2019-03-08 10:59:32"},6000);
    },
    changestatus2(){
      setTimeout(() => {this.step[1].status=1,this.step[1].actualstep="分合闸开关置于中间",this.step[1].detecttime="2019-03-08 10:59:32"},6000);
    },
    changestatus3(){
      setTimeout(() => {this.step[2].status=0,this.step[2].actualstep="接地保护跳闸开关闭合",this.step[2].detecttime="2019-03-08 10:59:32",
          this.$alert('检测到操作不匹配', '警告！', {
        confirmButtonText: '确定',
            type: 'warning',
      })},6000);
    },
  }

}
</script>

<style scoped>


.el-select-dropdown{
  border: none;
  background-color: rgba(1, 28, 82, 0.8);
}

.el-input__inner{
  color:#eee;
  border-color: #00fff6;
  background-color: rgba(1, 28, 82, 0.8);
}

.el-select .el-input.is-focus .el-input__inner{
  border-color: #0B61AA;
  background-color: rgba(1, 28, 82, 0.8);
  color:#00D3E9;
}

.el-select-dropdown__item{
  color: #eee;
}

.el-select-dropdown__item.hover,
.el-select-dropdown__item:hover{
  color:#00D3E9;
  background-color: #0F3360;
}
.checkbox{
  zoom: 200%;
}

/deep/ .el-alert__title {
  font-size: 17px;
}


.video-js .vjs-control-bar{ /* 控制条默认显示 */
  display: grid;
}
.el-button {
  margin-top: 6px;
  margin-left: 6px;
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
  height: 91%;
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
  height: 645px;
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
  width: 80%;
  height: 50px;
}

.but {
  width: 20%;
  height: 50px;
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
