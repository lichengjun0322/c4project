<template>
  <div>
    <el-main>
      <div style="margin-bottom: 30px">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>数据统计</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </el-main>
    <el-row>
      <el-col :span="24">
        <el-card style="padding-bottom: 20px">
          <div slot="header" class="clearfix">
            <span style="font-size: 25px">总体分析</span>
          </div>
          <el-col :span="24">
            <el-card>
              <div slot="header" class="clearfix">
                <span style="font-size: 18px">主队数据分析</span>
              </div>
              <el-col :span="10"><div id="main" style="width: 600px;height:400px;"></div></el-col>
              <el-col :span="12" offset="1"><el-table
                  :data="tableData"
                  style="width: 100%"
                  height="380">
                <el-table-column
                    fixed
                    prop="date"
                    label="球员编号"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="得分"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="province"
                    label="投篮"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="city"
                    label="篮板"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="rebound"
                    label="助攻"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="zip"
                    label="失误"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="foul"
                    label="犯规"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="fouled"
                    label="被犯规"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="plus"
                    label="正负值"
                    width="120">
                </el-table-column>
              </el-table></el-col>
            </el-card>
          </el-col>

          <el-col :span="24">
            <el-card>
              <div slot="header" class="clearfix">
                <span style="font-size: 18px">客队数据分析</span>
              </div>
              <el-col :span="10"><div id="main1" style="width: 600px;height:400px;"></div></el-col>
              <el-col :span="12" offset="1"><el-table
                  :data="tableData2"
                  style="width: 100%"
                  height="380">
                <el-table-column
                    fixed
                    prop="date"
                    label="球员编号"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="得分"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="province"
                    label="投篮"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="city"
                    label="篮板"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="rebound"
                    label="助攻"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="zip"
                    label="失误"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="foul"
                    label="犯规"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="fouled"
                    label="被犯规"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="plus"
                    label="正负值"
                    width="120">
                </el-table-column>
              </el-table></el-col>
            </el-card>
          </el-col>
        </el-card>
      </el-col>
    </el-row>

    <el-col :span="24">
      <el-card shadow="always">
        <el-col :span="20" offset="2">
          <el-card shadow="hover">
            <div id="main2" style="height:400px;"></div>
          </el-card>
        </el-col>
      </el-card>
    </el-col>

  </div>
</template>
<script>
import * as echarts from 'echarts';
export default {
  methods: {
    deleteRow(index, rows) {
      rows.splice(index, 1);
    }
  },
  data() {
    return {
      name: "tongjirizhi",
      tableData: [
        {date: '球员A',name: '32', province: '12/21', city: '21', rebound: '10', zip: 3, foul:5, fouled:7, plus:'+15'},
        {date: '球员B',name: '34', province: '12/22', city: '10', rebound: '10', zip: 7, foul:2, fouled:5, plus:'+14'},
        {date: '球员C',name: '2', province: '1/7', city: '7', rebound: '0', zip: 0, foul:1, fouled:0, plus:'+11'},
        {date: '球员D',name: '11', province: '5/10', city: '10', rebound: '5', zip: 0, foul:3, fouled:4, plus:'+16'},
        {date: '球员E',name: '6', province: '1/4', city: '3', rebound: '0', zip: 1, foul:2, fouled:2, plus:'+12'},
        {date: '球员F',name: '5', province: '1/5', city: '2', rebound: '0', zip: 1, foul:3, fouled:1, plus:'+8'},
        {date: '球员G',name: '0', province: '0/1', city: '1', rebound: '1', zip: 0, foul:0, fouled:0, plus:'-2'},
        {date: '球员H',name: '0', province: '0/0', city: '0', rebound: '0', zip: 0, foul:0, fouled:0, plus:'0'},
        {date: '球员I',name: '4', province: '2/2', city: '0', rebound: '1', zip: 0, foul:1, fouled:1, plus:'-1'},
        {date: '球员J',name: '15', province: '7/8', city: '4', rebound: '0', zip: 1, foul:1, fouled:2, plus:'+2'},
      ],
      tableData2: [
        {date: '球员1',name: '28', province: '11/24', city: '2', rebound: '4', zip: 2, foul:2, fouled:4, plus:'-11'},
        {date: '球员2',name: '22', province: '7/21', city: '17', rebound: '3', zip: 0, foul:2, fouled:9, plus:'-20'},
        {date: '球员3',name: '3', province: '1/7', city: '4', rebound: '5', zip: 0, foul:1, fouled:0, plus:'-11'},
        {date: '球员4',name: '7', province: '2/10', city: '0', rebound: '0', zip: 0, foul:3, fouled:2, plus:'-16'},
        {date: '球员5',name: '6', province: '2/5', city: '2', rebound: '0', zip: 0, foul:2, fouled:1, plus:'-10'},
        {date: '球员6',name: '9', province: '4/8', city: '3', rebound: '5', zip: 1, foul:4, fouled:0, plus:'-4'},
        {date: '球员7',name: '10', province: '4/9', city: '3', rebound: '3', zip: 1, foul:4, fouled:1, plus:'-5'},
        {date: '球员8',name: '0', province: '0/0', city: '0', rebound: '0', zip: 0, foul:1, fouled:1, plus:'+4'},
        {date: '球员9',name: '9', province: '3/6', city: '1', rebound: '0', zip: 0, foul:2, fouled:0, plus:'-4'},
        {date: '球员10',name: '0', province: '0/0', city: '0', rebound: '0', zip: 0, foul:0, fouled:0, plus:'+1'},
      ],
      value: '',
      mychart: null,
      mychart1: null,
      mychart2: null,
  }
  },
  mounted(){
    this.drawlines();
    this.drawlines1();
    this.drawcompare();
  },
  methods: {
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    log(){
      console.log(this.value);
    },
    drawlines(){
      let mychart = echarts.init(document.getElementById("main"))
      let option = {
        legend: {
          bottom: 30
        },
        title: {
          text: '各项数据之最',
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
          {
            name: '数据之最',
            type: 'pie',
            radius: [30, 100],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              { value: 34, name: '最高得分' },
              { value: 21, name: '最多篮板' },
              { value: 10, name: '最多助攻' },
              { value: 12, name: '最多命中数' },
              { value: 0, name: '\n' },
              { value: 1, name: '最多抢断' },
              { value: 3, name: '最多盖帽' },
              { value: 7, name: '最多失误' },
              { value: 16, name: '最高正负值' },
            ]
          }
        ]
      };
      mychart.setOption(option);
    },
    drawlines1(){
      let mychart1 = echarts.init(document.getElementById("main1"))
      let option = {
        legend: {
          bottom: 30
        },
        title: {
          text: '各项数据之最',
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
          {
            name: '数据之最',
            type: 'pie',
            radius: [30, 100],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              { value: 28, name: '最高得分' },
              { value: 17, name: '最多篮板' },
              { value: 5, name: '最多助攻' },
              { value: 11, name: '最多命中数' },
              { value: 0, name: '\n' },
              { value: 2, name: '最多抢断' },
              { value: 1, name: '最多盖帽' },
              { value: 2, name: '最多失误' },
              { value: 4, name: '最高正负值' },
            ]
          }
        ]
      };
      mychart1.setOption(option);
    },
    drawcompare(){
      let mychart2 = echarts.init(document.getElementById("main2"))
      let option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        title: {
          text: '主客队数据之最对比',
        },
        legend: {
          data: ['主队', '客队']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'value'
          }
        ],
        yAxis: [
          {
            type: 'category',
            axisTick: {
              show: false
            },
            data: ['最高得分', '最多篮板', '最多助攻', '最多命中数', '最多抢断', '最多盖帽', '最多失误',"最高正负值"]
          }
        ],
        series: [
          {
            name: '主队',
            type: 'bar',
            label: {
              show: true,
              position: 'inside'
            },
            emphasis: {
              focus: 'series'
            },
            data: [34,21,10,12,1,3,7,16]
          },

          {
            name: '客队',
            type: 'bar',
            stack: 'Total',
            label: {
              show: true,
            },
            emphasis: {
              focus: 'series'
            },
            data: [28,17,5,11,2,1,2,4]
          }
        ]
      };
      mychart2.setOption(option);
    },
  },
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}
</style>