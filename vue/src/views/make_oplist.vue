<template>
  <div>
    <el-row>
      <el-col :span="12" class="no-print">
        <div class="grid-content bg-purple1">
          <div style="text-align: center;color: #F1F2F7" class="mains">操作侧</div>
          <div class="leftButton_top"></div>
          <div class="leftButton">
            <el-row>
              <el-col span="1" offset="0" v-for="(item, index) in titleList1" style="margin-right: 123px">
                <el-tooltip class="item" effect="dark" :content=item.action placement="top-start">
                  <el-button round @click="liClick(item.name)" :key="index" size="medium">{{ item.name }}</el-button>
                </el-tooltip>
              </el-col>
            </el-row>
          </div>
          <div class="leftButton" style="margin-top: 10px">
            <el-row>
              <el-col span="1" offset="0" v-for="(item, index) in titleList2" style="margin-right: 123px">
                <el-tooltip class="item" effect="dark" :content=item.action placement="top-start">
                  <el-button round @click="liClick(item.name)" :key="index" size="medium">{{ item.name }}</el-button>
                </el-tooltip>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple-light">
          <div style="text-align: center" class="no-print mains">展开侧</div>
          <div class="operation">
            <span>操作任务:
              <select v-model="selectedS">
                <option
                    v-for="option in optionsD"
                    v-bind:value="option.text"
                >
                  {{ option.text }}
                </option>
              </select>
            </span>
            <span
            >值长:
              <select v-model="selectedOne">
                <option
                    v-for="option in options_list"
                    v-bind:value="option.text"
                >
                  {{ option.text }}
                </option>
              </select></span
            >
          </div>
          <div class="operations">
            <span
            >监护人:
              <select v-model="selectedTwo">
                <option
                    v-for="option in optionsTwo_list"
                    v-bind:value="option.text"
                >
                  {{ option.text }}
                </option>
              </select> </span
            ><span
          >值班负责人:
              <select v-model="selected">
                <option v-for="option in options" v-bind:value="option.text">
                  {{ option.text }}
                </option>
              </select>
            </span>
          </div>
          <div id="printDown">
            <el-table :data="tableData" stripe style="width: 100%">
              <el-table-column type="index" label="序号" width="180">
              </el-table-column>
              <el-table-column prop="actionItem" label="操作项目" width="640">
              </el-table-column>
            </el-table>
          </div>

          <el-row class="no-print">
            <el-button type="primary" @click="cancelPrve">撤销上一步</el-button>
            <el-button type="primary" @click="exportWordTest">导出</el-button>
            <!-- <el-button type="primary" @click="exportWord">导出</el-button> -->
            <!-- <el-button type="primary" @click="pdfDown">导出</el-button> -->
            <el-button type="primary" @click="dialogVisible = true">下发操作票</el-button>
            <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
              <span>操作票已下发</span>
              <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
              </span>
            </el-dialog>
          </el-row>
        </div>
      </el-col
      >
    </el-row>
  </div>
</template>

<script>
import {exportWord} from '@/plugins/export'

export default {
  name: "make_oplist",
  data() {
    return {
      list: [],
      dialogVisible: false,
      imgurl: require("/src/assets/btn.svg"),
      selected: '李四', //select选中的值
      options: [
        {text: '张三', value: 'A'},
        {text: '李四', value: 'B'}
      ],
      selectedOne: '黄冲', //select选中的值
      options_list: [
        {text: '张三', value: 'A'},
        {text: '黄冲', value: 'B'}
      ],
      selectedTwo: '李帧', //select选中的值
      optionsTwo_list: [
        {text: '李帧', value: 'A'},
        {text: '李酒', value: 'B'}
      ],
      selectedS: '10kV××线***开关由运行转检修', //select选中的值
      optionsD: [
        {text: '10kV××线***开关由运行转检修', value: 'A'},
        {text: '拉出10kVXX线***手车开关至试验位置，查确已到位', value: 'B'}
      ],
      titleList1: [
        {name: 'A', action: "将 10kV I段出线102开关的“就地”切换开关切换至就地位置，查确已在就地位置"},
        {name: 'B', action: "断开 I段出线102开关"},
        {name: 'C', action: "查 10kV I段出线102开关机械位置指示及开关分合闸指示确在断开位置，电流表指示无电流，带电显示器指示确无带电"},
        {name: 'D', action: "拉出 10kV I段出线002手车开关至试验位置，查确已到位"},
      ],
      titleList2: [
        {name: 'E', action: "取下 10kV I段出线002手车的二次插头，查确已取下"},
        {name: 'F', action: "拉出 10kV I段出线102开关002手车开关至检修位置，查确已到位"},
        {name: 'G', action: "锁上 10kV I段出线102开关柜门，并在柜门悬挂“止步，高压危险！”及“禁止合闸，有人工作”的标示牌"},
        {name: 'H', action: "断开 10kV I段出线102开关的控制及储能电源，查确已断开"},
      ],
      tableData: [],
      value: ''
    }
  },

  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },
    liClick(e) {
      if (e == 'E') {
        this.tableData.push({actionItem: '取下 10kV I段出线002手车的二次插头，查确已取下'})
      } else if (e == 'F') {
        this.tableData.push({actionItem: '拉出 10kV I段出线102开关002手车开关至检修位置，查确已到位'})
      } else if (e == 'G') {
        this.tableData.push({actionItem: '锁上 10kV I段出线102开关柜门，并在柜门悬挂“止步，高压危险！”及“禁止合闸，有人工作”的标示牌'})
      } else if (e == 'H') {
        this.tableData.push({actionItem: '断开 10kV I段出线102开关的控制及储能电源，查确已断开'})
      } else if (e == 'A') {
        this.tableData.push({actionItem: '将 10kV I段出线102开关的“就地”切换开关切换至就地位置，查确已在就地位置'})
      } else if (e == 'B') {
        this.tableData.push({actionItem: '断开 I段出线102开关'})
      } else if (e == 'C') {
        this.tableData.push({actionItem: '查 10kV I段出线102开关机械位置指示及开关分合闸指示确在断开位置，电流表指示无电流，带电显示器指示确无带电'})
      } else if (e == 'D') {
        this.tableData.push({actionItem: '拉出 10kV I段出线002手车开关至试验位置，查确已到位'})
      } else if (e == 'E') {
        this.tableData.push({actionItem: '取下 10kV I段出线002手车的二次插头，查确已取下'})
      }
    },
    cancelPrve() {
      this.tableData.pop()
    },
    pdfDown() {
      window.print()
    },

    exportWordTest() {
      //具体数据根据实际情况灵活变动
      let data = {
        list: this.tableData,
        num: this.selected,
        // 操作任务
        selectedS: this.selectedS,
        // 值长
        selectedOne: this.selectedOne,
        // 监护人
        selectedTwo: this.selectedTwo
      }
      this.tableData = this.tableData.filter((item, index) => {
        return (item.index = index + 1)
      })
      exportWord('demo.docx', data, '')
    }
  }
}
</script>

<style>
/* 保存时的样式 了解更多 百度cssprint样式 */
@media print {
  .no-print {
    display: none;
  }
}

/*打印页配置*/
@page {
  margin: 60px 10px;
}

.mains {
  padding: 10px;
  font-size: 25px;
}

.operation {
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding-bottom: 10px;
}

.operations {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 70px 10px;
}

.leftButton_top {
  height: 200px;
}

.leftButton {
  padding-bottom: 100px;
  padding-left: 75px;
}

.leftButton_bottom {
  padding-left: 75px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  list-style: none;
}

.leftButton_one {
  width: 50px;
  height: 50px;
  background-color: blue;
}

.leftButton_two {
  width: 50px;
  height: 50px;
  background-color: blue;
}

.leftButton_three {
  width: 50px;
  height: 50px;
  background-color: blue;
}

.leftButton_four {
  width: 50px;
  height: 50px;
  background-color: blue;
}

.leftButton ul {
  width: 500px;
  display: flex;
  justify-content: space-evenly;
}

.leftButton ul li {
  display: flex;
  justify-content: center;
  align-items: center;
  list-style: none;
  width: 50px;
  height: 50px;
  background-color: blue;
  border-radius: 50%;
}

.el-row {
  margin-bottom: 20px;
  /* &:last-child {
      margin-bottom: 0;
    } */
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
  /* background: #99a9bf; */
}

.bg-purple1 {
  /* background: #d3dce6; */
  background: url('../assets/122d02043b71fd79a6855cd943ce80e.jpg') no-repeat 100%;
  height: 500px;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  margin-right: 10px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
