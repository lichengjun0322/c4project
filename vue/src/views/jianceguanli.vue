<template>
  <div>
    <el-main>
      <div style="margin-bottom: 30px">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>统计分析</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="num graph">
        <el-card style="width:100%;height: 600px">
          <div class="schedule-container">
            <el-calendar>
              <template slot="dateCell" slot-scope="{ date, data }">
                <div :class="data.isSelected ? 'is-selected' : ''" @click="calItem(data.day)">
                  <p>
                    <!--这里原本有动态绑定的class，去掉-->{{ data.day.split('-').slice(1).join('-') }}<br/>
                    <span :class="dealMyDate(data.day).grade == '紧急日程'? 'is-success'
                  : dealMyDate(data.day).grade == '一般日程'? 'is-common': 'is-danger'">
              {{ dealMyDate(data.day).content }}</span>
                  </p>
                </div>
              </template>
            </el-calendar>
            <el-dialog
                :title="editForm.day"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose"
            >
              <el-form
                  @submit.native.prevent
                  :ref="editForm"
                  :model="editForm"
              >
                <el-form-item label="日程" prop="content">
                  <el-input v-model="editForm.content"></el-input>
                </el-form-item>
                <el-form-item label="性质" prop="grade">
                  <el-radio-group v-model="editForm.grade">
                    <el-radio label="一般日程"></el-radio>
                    <el-radio label="紧急日程"></el-radio>
                    <el-radio label="重要日程"></el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-form>
              <span slot="footer" class="dialog-footer">
        <el-button @click="callOf('editForm')">取 消</el-button>
        <el-button type="primary" @click="
            dialogVisible = false
            add()
          "
        >
          确 定
        </el-button>
      </span>
            </el-dialog>
          </div>
        </el-card>
      </div>
      <el-row style="margin-top: 30px">
        <el-col :span="10">
          <div class="grid-content bg-purple">
            <el-card class="box-card" style="width: 800px">
              <div id="main" style="width: 700px; height: 400px"></div>
            </el-card>
          </div>
        </el-col>
        <el-col :span="10" offset="2">
          <el-card class="box-card" style="width: 800px;margin-left: 12px">
            <div id="line" style="width: 750px; height: 400px"></div>
          </el-card>
        </el-col>
      </el-row>
<!--      <el-row>-->
<!--        <el-card>-->
<!--          <div class="my-container">-->
<!--            <div class="nowEcharts" id="nowEcharts"></div>-->
<!--          </div>-->
<!--        </el-card>-->
<!--      </el-row>-->

    </el-main>

  </div>

</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader';
import * as echarts from 'echarts';

export default {
  name: "jianceguanli",
  data() {
    return {
      map: null, //初始化 map 对象
      rcdata: [],
      dialogVisible: false,
      editForm: {
        content: '',
        day: '',
        grade: '一般日程'
      },
      value: new Date(),
      resDate: [
        {date: '2023-04-20', content: '部门会议', grade: '重要日程'},
        {date: '2023-04-16', content: '检查设备', grade: '紧急日程'},
        {date: '2023-04-26', content: '准备会议', grade: '重要日程'},
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
        title: {
          left: "left",
          text: "电量消耗实时统计",
        },
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
          triggerEvent: true,
        },
        yAxis: {
          type: "value",
          boundaryGap: [0, "100%"],
          max: 100,
          splitLine: {
            show: false,
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
  mounted() {
    //DOM初始化完成进行地图初始化
    // this.initMap();
    this.drawChart();
    this.drawlines();
    this.nowChart();
    this.getData()
  },
  methods: {
    callOf(formName) {
      console.log("sadad", formName)
      // this.$refs.formName.resetFields();
      // this.$refs['formName'].clearValidate()
      this.dialogVisible = false
    },
    calItem(d) {
      console.log('点击的日期是：', d)
      this.editForm.day = d
      this.rcdata = this.dealMyDate(d)
      this.editForm.content = this.rcdata.content
      this.editForm.grade = this.rcdata.grade
      this.dialogVisible = true
      console.log('是：', this.editForm, this.rcdata.index)
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
    drawChart() {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = echarts.init(document.getElementById("main"));
      // 指定图表的配置项和数据
      let option = {
        title: {
          text: "过去7天操作票处理统计",
        },
        tooltip: {},
        legend: {
          data: ["告警量","错误量"],
        },
        xAxis: {
          data:['园区1', '园区2', '园区3', '园区4', '园区5', '园区6', '园区7', '园区8', '园区9', '园区10'],
        },
        yAxis: {
          min: 0,
          max: 50,
          interval: 5,
        },
        series: [
          {
            name: "告警量",
            type: "bar",
            data: [12,20,16,19,6,23,2,13,14,10],
          },
          {
            name: "错误量",
            type: "bar",
            data: [15,26,19,23,15,30,10,12,3,12],
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
    drawlines(){
      let mychart = echarts.init(document.getElementById("line"))
      let option = {
        title: {
          text: '过去一天各时段处理操作票总数',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        toolbox: {
          show: true,
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // prettier-ignore
          data: ['00:00', '01:15', '02:30', '03:45', '05:00', '06:15', '07:30', '08:45', '10:00', '11:15', '12:30', '13:45', '15:00', '16:15', '17:30', '18:45', '20:00', '21:15', '22:30', '23:45']
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} '
          },
          axisPointer: {
            snap: true
          }
        },
        visualMap: {
          show: false,
          dimension: 0,
          pieces: [
            {
              lte: 6,
              color: 'green'
            },
            {
              gt: 6,
              lte: 9,
              color: 'red'
            },
            {
              gt: 9,
              lte: 14,
              color: 'green'
            },
            {
              gt: 14,
              lte: 17,
              color: 'red'
            },
            {
              gt: 17,
              color: 'green'
            }
          ]
        },
        series: [
          {
            name: 'Electricity',
            type: 'line',
            smooth: true,
            // prettier-ignore
            data: [5, 3, 4, 8, 10, 16, 30, 66, 67, 89, 23, 30, 31, 30, 84, 80, 72, 22, 18, 10],
            markArea: {
              itemStyle: {
                color: 'rgba(255, 173, 177, 0.4)'
              },
              data: [
                [
                  {
                    name: 'Morning Peak',
                    xAxis: '07:30'
                  },
                  {
                    xAxis: '11:15'
                  }
                ],
                [
                  {
                    name: 'Evening Peak',
                    xAxis: '17:30'
                  },
                  {
                    xAxis: '21:15'
                  }
                ]
              ]
            }
          }
        ]
      };
      mychart.setOption(option);
    },

    nowChart() {
      let that = this;
      this.now = +new Date();
      var value = Math.random() * 1000;
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
      var value = Math.random() * 1000;
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
      value = Math.random() * 100;
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

.nowEcharts {
  width: 100%;
  height: 300px;
}

#container {
  width: 80%;
  height: 200px;
  border: 2px solid red;
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
</style>