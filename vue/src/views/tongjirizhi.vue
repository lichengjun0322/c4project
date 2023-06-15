<template>
  <div>
    <el-main>
      <div style="margin-bottom: 30px">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>操作日志</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </el-main>
    <el-card>
      <el-table
          :data="tableData"
          style="width: 100%;text-align: center;font-size: 15px;line-height: 50px"
          stripe
          max-height="600">
        <el-table-column
            fixed
            prop="opID"
            label="操作序号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="user"
            label="操作用户"
            width="200">
        </el-table-column>
        <el-table-column
            prop="userid"
            label="操作用户ID"
            width="200">
        </el-table-column>
        <el-table-column
            prop="ip"
            label="操作用户IP"
            width="200">
        </el-table-column>
        <el-table-column
            prop="opcontent"
            label="操作内容"
            width="600">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200">
          <template slot-scope="scope">
            <el-button
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                type="text"
                size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

  </div>
</template>
<script>
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
        {opID:11, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户查看系统操作日志'},
        {opID:10, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户添加日程'},
        {opID:9, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户查看系统数据统计分析'},
        {opID:8, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户删除机房信息'},
        {opID:7, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户编辑机房信息'},
        {opID:6, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户新增机房信息'},
        {opID:5, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户选取模型检测着装'},
        {opID:4, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户为机房4下发操作票'},
        {opID:3, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户查看机房4'},
        {opID:2, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户查看园区总览界面'},
        {opID:1, user:'admin', userid:'2007010308', ip:'127.0.0.1', opcontent:'用户登录成功'},
      ]
    }
  }
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
</style>