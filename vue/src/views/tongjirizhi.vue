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
        <el-card>
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
                    prop="address"
                    label="助攻"
                    width="300">
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
                    prop="address"
                    label="助攻"
                    width="300">
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
        <el-row>
          <el-card shadow="hover" style="width: 200px">
            <el-select v-model="value" placeholder="请选择" @change="log()">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              >
              </el-option>
            </el-select>
          </el-card>
        </el-row>
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
        {date: '球员A',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员B',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员C',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员D',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员E',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员F',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员G',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员H',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员I',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员J',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
      ],
      tableData2: [
        {date: '球员1',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员2',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员3',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员4',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员5',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员6',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员7',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员8',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员9',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
        {date: '球员10',name: '42', province: '16/22', city: '10', address: '上海市普陀区金沙江路 1518 弄', zip: 6, foul:4, fouled:7, plus:'+15'},
      ],
      options: [{
        value: '1',
        label: '黄金糕'
      }, {
        value: '2',
        label: '双皮奶'
      }, {
        value: '3',
        label: '蚵仔煎'
      }, {
        value: '4',
        label: '龙须面'
      }, {
        value: '5',
        label: '北京烤鸭'
      }],
      value: '',
      mychart: null,
      mychart1: null,
  }
  },
  mounted(){
    this.drawlines();
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
              { value: 40, name: '最高得分' },
              { value: 18, name: '最多篮板' },
              { value: 13, name: '最多助攻' },
              { value: 6, name: '最多抢断' },
              { value: 4, name: '最多盖帽' },
              { value: 3, name: '最多失误' },
            ]
          }
        ]
      };
      mychart.setOption(option);
      mychart1.setOption(option);
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