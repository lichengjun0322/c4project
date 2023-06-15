<template>
  <div>
    <el-row :gutter="20">
      <el-col offset="0">
        <div class="grid-content bg-purple">
          <div class="demo-image">
            <img :src="imgurl" height="830" width="100%" usemap="#plantmap" style="position: relative;background-color: transparent;z-index: -1;background-size: 100%;height: 100%"/>
            <map name="plantmap">
              <!-- area 定义图像映射中的区域 -->
              <area v-for="area in props" :shape="area.shape" :coords="area.coords" style="cursor: pointer"
                    @click="open(area.id)">
            </map>
          </div>
        </div>
      </el-col>
      <div class="regional_table">
        <el-table
            :data="ALLData"
            class="eltable"
            stripe
            height="900px"
            :header-cell-style="{background:'#eef1f6',color:'#606266'}"
            :cell-style="cellstyle"
            style="position: absolute;width: 300px;right: 0%;background-color: rgba(255,255,255,0.5);">
          <el-table-column
              prop="name"
              label="机房名称"
              width="80"
              color="red">
          </el-table-column>
          <el-table-column
              prop="location"
              label="机房位置"
              width="140">
          </el-table-column>
          <el-table-column
              prop="fromYuanqu"
              label="所属园区">
          </el-table-column>
        </el-table>
      </div>
      <el-card class="box-card" style="position: absolute;background-color: rgba(255,255,255,0.5);bottom: 350px">
        <h1 style="color: #ffffff">园区当前状态统计</h1>
        <div id="pie" style="width: 400px; height: 200px;"></div>
      </el-card>

      <el-card shadow='hover' style="position: absolute;background-color: rgba(255,255,255,0.3);width: 440px">
<!--        <div class="userCard">-->
<!--          <el-row>-->
<!--            <el-col :span="6"><div class="grid-content bg-purple"><img style="height: 100px" :src=logoimgurl></img></div></el-col>-->
<!--            <el-col :span="12" offset="6"><div class="grid-content bg-purple"><div class="userInfo">-->
<!--              <p class="important-font">慕安电力</p>-->
<!--              <p class="secondary-font">Mu'an Electricity</p>-->
<!--            </div></div></el-col>-->
<!--          </el-row>-->
<!--        </div>-->
<!--        <div class="login-info">-->
<!--          <p>专注于智能电力管理系统</p>-->
<!--        </div>-->
        <div class="my-container">
          <h1 style="color: #ffffff">功耗实时统计</h1>
          <div class="nowEcharts" id="nowEcharts"></div>
        </div>
      </el-card>


      <el-card class="box-card" style="height: 345px;position: absolute;bottom: 0%;background-color: rgba(255,255,255,0.5);">
        <div id="line" style="width: 400px; height: 300px;opacity: 1;"></div>
      </el-card>



    </el-row>
    <div>
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in ALLData" :key="item.id" style="width: 700px">
          <el-row :gutter="20">
            <el-col :span="6" offset="2">
              <div class="grid-content bg-purple"><img class="medium" :src="getImgUrl(item.modelimg)" height="200px"
                                                       width="200px"></div>
            </el-col>
            <el-col :span="10" offset="2">
              <div class="grid-content bg-purple">
                <h1 style="margin-top:15px">{{ item.name }}</h1>
                <p style="margin-top: 20px;white-space:pre-wrap">{{ item.location }}</p>
                <p style="margin-top: 20px;white-space:pre-wrap">{{item.description}}</p>
              </div>
            </el-col>
          </el-row>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "shouye",
  data() {
    return {
      resDate: [
        {date: '2023-04-20', content: '放假', grade: '一般日程'},
        {date: '2023-04-22', content: '去交电费', grade: '紧急日程'},
        {date: '2023-04-26', content: '去学习vue', grade: '重要日程'},
      ],
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      dialogVisible: false,
      rcdata: [],
      editForm: {
        content: '',
        day: '',
        grade: '一般日程'
      },
      value: new Date(),
      imgurl: require('/src/assets/jietu2.png'),
      logoimgurl: require('/src/assets/muanlogo.png'),
      ALLData: [],
      props: [
        {id: 1, shape: "circle", coords: "150,150,30"},
        // {id: 2, shape: "rect", coords: "60,60,100,100"},
        {id: 3, shape: "rect", coords: "0,0,50,50"},
      ],
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
      oneDay: 1000,
      nowOptions: {
        visualMap: [
          {
            show: false,
            type: "continuous",
            seriesIndex: 0,
            min: 0,
            max: 400,
          },
        ],
        tooltip: {
          trigger: "axis",
          formatter: function (params) {
            params = params[0];
            var date = new Date(params.name);
            return (
                date.getDate() +
                "/" +
                (date.getMonth() + 1) +
                "/" +
                date.getFullYear() +
                " : " +
                params.value[1]
            );
          },
          axisPointer: {
            animation: false,
          },
        },
        grid: {
          top: "15%",
          bottom: "10%",
        },
        xAxis: {
          type: "time",
          splitLine: {
            show: false,
          },
          show: false,
          axisLabel:{
            color:"#ffffff"
          },
          triggerEvent: true,
        },
        yAxis: {
          type: "value",
          boundaryGap: [0, "100%"],
          min:600,
          max:900,
          splitLine: {
            show: false,
          },
          axisLabel:{
            color:"#ffffff"
          },
        },
        series: [
          {
            type: "line",
            showSymbol: false,
            hoverAnimation: false,
            data: [],
          },
        ],
      },
      myChart: null,
      timer: null,
      data: [],
      temp: 59,
      now: "",

    }
  },
  created() {
    this.$http.get("http://localhost:8088/yuanqu").then((response) => {//箭头函数使作用域继承父级，即created当前级，this可生效
      console.log(response.data)
      this.ALLData = response.data
    })
  },

  mounted() {
    this.nowChart();
    this.getData();
    this.drawlines();
    this.drawpie();
    this.drawline();
  },
  methods: {
    cellstyle(){//修改表格字体颜色
      return "color:#055194 !important"
    },
    getImgUrl(src) {
      // src的值是你绝对路径下图片的地址,此地址需省略开头的 @/
      return require("@/" + src);
    },
    open(id) {
      this.$alert(this.yuanqu[id - 1].name, '标题名称', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${action}`
          });
        }
      });
    },
    calItem(d) {
      console.log('点击的日期是：', d)
      this.editForm.day = d
      this.rcdata = this.dealMyDate(d)
      this.editForm.content = this.rcdata.content
      this.editForm.grade = this.rcdata.grade
      this.dialogVisible = true
      console.log('sadsadasdasd是：', this.editForm, this.rcdata.index)
    },
    dealMyDate(v) {
      // console.log(v)
      let len = this.resDate.length
      let res = []
      for (let i = 0; i < len; i++) {
        if (this.resDate[i].date == v) {
          res.content = this.resDate[i].content
          res.grade = this.resDate[i].grade
          res.index = i
          break
        }
      }
      return res
    },
    handleClose(done) {
      done()
    },
    add() {
      var a = {
        date: this.editForm.day,
        content: this.editForm.content,
        grade: this.editForm.grade,
      }

      if (this.rcdata.grade) {
        console.log('填入的日程', this.resDate, this.rcdata.grade, a)
        this.$set(this.resDate, this.rcdata.index, a)
        console.log("修改后的：", this.resDate)
      } else {
        this.resDate.push(a)
      }

    },
    callOf(formName) {
      console.log("sadad", formName)
      // this.$refs.formName.resetFields();
      // this.$refs['formName'].clearValidate()
      this.dialogVisible = false
    },
    dynamiclines(_rawData) {
      // var countries = ['Australia', 'Canada', 'China', 'Cuba', 'Finland', 'France', 'Germany', 'Iceland', 'India', 'Japan', 'North Korea', 'South Korea', 'New Zealand', 'Norway', 'Poland', 'Russia', 'Turkey', 'United Kingdom', 'United States'];
      const countries = [
        'Finland',
        'France',
        'Germany',
        'Iceland',
        'Norway',
        'Poland',
        'Russia',
        'United Kingdom'
      ];
      const datasetWithFilters = [];
      const seriesList = [];
      echarts.util.each(countries, function (country) {
        var datasetId = 'dataset_' + country;
        datasetWithFilters.push({
          id: datasetId,
          fromDatasetId: 'dataset_raw',
          transform: {
            type: 'filter',
            config: {
              and: [
                {dimension: 'Year', gte: 1950},
                {dimension: 'Country', '=': country}
              ]
            }
          }
        });
        seriesList.push({
          type: 'line',
          datasetId: datasetId,
          showSymbol: false,
          name: country,
          endLabel: {
            show: true,
            formatter: function (params) {
              return params.value[3] + ': ' + params.value[0];
            }
          },
          labelLayout: {
            moveOverlap: 'shiftY'
          },
          emphasis: {
            focus: 'series'
          },
          encode: {
            x: 'Year',
            y: 'Income',
            label: ['Country', 'Income'],
            itemName: 'Year',
            tooltip: ['Income']
          }
        });
      });
    },

    drawline(){
      let mychart = echarts.init(document.getElementById("lines"))
      let option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['告警次数', '错误次数']
        },
        xAxis: [
          {
            type: 'category',
            data: ['园区1', '园区2', '园区3', '园区4', '园区5', '园区6', '园区7'],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '数量',
            min: 0,
            max: 50,
            interval: 5,
            axisLabel: {
              formatter: '{value} '
            }
          },
        ],
        series: [
          {
            name: '告警次数',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value;
              }
            },
            data: [
              12,20,16,19,6,23,2
            ]
          },
          {
            name: '错误次数',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value;
              }
            },
            data: [
              15,26,19,23,15,30,10
            ]
          },
        ]
      };
      mychart.setOption(option);
    },
    drawpie() {
      let mychart = echarts.init(document.getElementById("pie"))
      mychart.setOption({
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '90%',
          left: 'center',
          textStyle:{
            color:"#fff"
          },
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center',
              color:"#ffffff"
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 24,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: 10, name: '工作中' },
              { value: 2, name: '空闲中' },
              { value: 3, name: '检修中' },
            ]
          }
        ]
      })
    },
    drawlines(){
      let mychart = echarts.init(document.getElementById("line"))
      let option = {
        color: ['#80FFA5', '#00DDFF', '#107ad7', '#FF0087', '#FFBF00'],
        title: {
          text: '告警总数前五园区',
          top:"-5px",
          textStyle: {
            color:"#fff"
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        axisLabel:{
          color:"#ffffff"
        },
        legend: {
          data: ['园区4', '园区2', '园区3', '园区5', '园区6'],
          textStyle:{
            color:"#fff"
          },
          top:"20px"
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: ['Nov' ,'Dec', 'Jan', 'Feb', 'Mar', 'Apri']
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '园区4',
            type: 'line',
            stack: 'Total',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: 'rgb(128, 255, 165)'
                },
                {
                  offset: 1,
                  color: 'rgb(1, 191, 236)'
                }
              ])
            },
            emphasis: {
              focus: 'series'
            },
            data: [4,6,8,10,5,16]
          },
          {
            name: '园区2',
            type: 'line',
            stack: 'Total',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: 'rgb(0, 221, 255)'
                },
                {
                  offset: 1,
                  color: 'rgb(77, 119, 255)'
                }
              ])
            },
            emphasis: {
              focus: 'series'
            },
            data: [10,5,6,8,12,3]
          },
          {
            name: '园区3',
            type: 'line',
            stack: 'Total',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: 'rgb(55, 162, 255)'
                },
                {
                  offset: 1,
                  color: 'rgb(116, 21, 219)'
                }
              ])
            },
            emphasis: {
              focus: 'series'
            },
            data: [12,10,1,13,4,2]
          },
          {
            name: '园区5',
            type: 'line',
            stack: 'Total',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: 'rgb(255, 0, 135)'
                },
                {
                  offset: 1,
                  color: 'rgb(135, 0, 157)'
                }
              ])
            },
            emphasis: {
              focus: 'series'
            },
            data: [13,4,6,15,3,10]
          },
          {
            name: '园区6',
            type: 'line',
            stack: 'Total',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            label: {
              show: true,
              position: 'top'
            },
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: 'rgb(255, 191, 0)'
                },
                {
                  offset: 1,
                  color: 'rgb(224, 62, 76)'
                }
              ])
            },
            emphasis: {
              focus: 'series'
            },
            data: [10,12,6,8,4,7]
          }
        ]
      };
      mychart.setOption(option);
    },

    nowChart() {
      let that = this;
      this.now = +new Date();
      var value = Math.floor((Math.random()-0.5) * (100) ) + 750;
      for (var i = 0; i < 60; i++) {
        this.now = new Date(+this.now + this.oneDay);
        this.data.push(this.randomData(this.now, value));
      }
      // 基于准备好的dom，初始化echarts实例
      this.myChart = echarts.init(document.getElementById("nowEcharts"));

      // 绘制图表
      let options = Object.assign(that.nowOptions, {});
      options.series.forEach((item) => {
        item.data = that.data;
        item.markPoint = {
          data: [
            [
              {
                symbol: "none",
                x: "95%",
              },
              {
                symbol: "circle",
                name: "实时数据",
                value: this.data[this.temp].value[1],
                xAxis: this.data[this.temp].value[0],
              },
            ],
          ],
          yAxis: {
            min: 500,
            max: 1000,
            interval: 5,
          },
        };
      });
      this.myChart.setOption(options);
      // 1秒定时器
      //   setInterval(() => {
      //     for (var i = 0; i < 1; i++) {
      //       data.shift();
      //       now = new Date(+now + this.oneDay);
      //       data.push(this.randomData(now, value));
      //     }
      //     myChart.setOption(options);
      //   }, 1000);
    },
    // 获取接口返回数据 这里用定时器模拟一秒一个数据
    getData() {
      let that = this;
      var value = Math.floor((Math.random()-0.5) * (100) ) + 750;
      this.timer = setInterval(() => {
        for (var i = 0; i < 1; i++) {
          that.data.shift();
          that.now = new Date(+that.now + that.oneDay);
          that.data.push(this.randomData(that.now, value));
        }
        that.updateChart()
      }, 1000);
    },
    //更新图表
    updateChart() {
      let options = Object.assign(this.nowOptions, {});
      options.series.forEach((item) => {
        item.data = this.data;
        item.markPoint = {
          data: [
            [
              {
                symbol: "none",
                x: "95%",
              },
              {
                symbol: "circle",
                name: "实时数据",
                value: this.data[this.temp].value[1],
                xAxis: this.data[this.temp].value[0],
              },
            ],
          ],
        };
      });
      this.myChart.setOption(options);
    },
    // 产生随机数
    randomData(now, value) {
      value = Math.floor((Math.random()-0.5) * (100) ) + 750;
      var valueName =
          now.getFullYear() +
          "/" +
          (now.getMonth() + 1) +
          "/" +
          now.getDate() +
          " " +
          (now.getHours() >= 10 ? now.getHours() : "0" + now.getHours()) +
          ":" +
          (now.getMinutes() >= 10 ? now.getMinutes() : "0" + now.getMinutes()) +
          ":" +
          (now.getSeconds() >= 10 ? now.getSeconds() : "0" + now.getSeconds());
      return {
        name: now.toString(),
        value: [valueName, Math.round(value)],
      };
    },
  }
}
</script>


<style scoped>
.el-card__body {
  padding: 10px;
}

.userCard {
  height: 120px;
  display: flex;
  border-bottom: 2px solid #DCDFE6;
  border-radius: 2px;
}

.userInfo {
  width: auto;
  padding: 6% 0 0 6%;
}

.important-font {
  font-weight: 900;
  font-size: 25px;
  color: #ffffff;
}

.secondary-font {
  color: #F1F2F7;
}

.login-info {
  height: 40px;
  text-align: left;
  color: #ffffff;
}


.el-card {
  border: none;
}

.num {
  display: flex;
  flex-wrap: wrap;
}

.graph {
  margin: 15px 0 0 0;
}


.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}


.userInfo {
  width: auto;
  padding: 6% 0 0 6%;
}


.OrderCard {
  margin: 0 0 30px 30px;
  border: #DCDFE6 1px solid;
  border-radius: 10px;
}

i {
  width: 30%;
  line-height: 120px;
  font-size: 30px;
  color: #fff
}

.test /deep/  .el-calendar-table .el-calendar-day{
  width: 80px;
  height: 50px;
}

.is-selected {
  color: #f8a535;
  font-size: 10px;
  margin-top: 5px;
}

.is-success {
  color: #67c23a;
}

.is-danger {
  color: #f56c6c;
}

.is-common {
  color: #909399;
}

#calendar
.el-button-group
> .el-button:not(:first-child):not(:last-child):after {
  content: '当月';
}

/*最外层透明*/
.regional_table /deep/ .el-table,
.regional_table /deep/ .el-table__expanded-cell {
  background-color: transparent;
}
/* 表格内背景颜色 */
.regional_table /deep/ .el-table th,
.regional_table /deep/ .el-table tr,
.regional_table /deep/ .el-table td {
  background-color: transparent !important;
}

/*.el-table{*/
/*  border: 1px solid black;*/
/*}*/

.el-table >>> .el-table__row>td{
  /* 去除表格线 */
  border: none;
}
/deep/ .el-table{
  color: #DD1144 !important;
  font-size: 12px;
}
.nowEcharts {
  width: 100%;
  height: 210px;
}
</style>
