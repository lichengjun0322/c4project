<template>
  <div>
      <el-main>
        <div style="margin-bottom: 30px">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/shouye' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>园区管理</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div>
          <el-carousel :interval="4000" type="card" height="300px">
            <el-carousel-item v-for="item in imgList" :key="item.id">
              <el-row :gutter="20">
                <el-col :span="6" offset="1"><div class="grid-content bg-purple"><img class="medium" :src="item.idView"></div></el-col>
                <el-col :span="6" offset="6"><div class="grid-content bg-purple">
                  <h1 style="margin-top:15px">{{item.name}}</h1>
                  <p style="margin-top: 20px;white-space:pre-wrap">{{item.description}}</p>
                </div></el-col>
              </el-row>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div style="margin-bottom: 30px">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item style="font-size: 20px">机房信息管理</el-breadcrumb-item>
          </el-breadcrumb>
        </div>

        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline" style="margin-left: 2px"></i></el-button>

        </div>
        <el-table :data="tableData" border stripe height="500px">
          <!--          : 表示使用这个属性，在下面的return内定义，是v-bind:的缩写-->
          <el-table-column prop="id" label="ID" width="80">
          </el-table-column>
          <el-table-column prop="name" label="机房名称" width="300">
          </el-table-column>
          <el-table-column prop="location" label="所处位置" width="300">
          </el-table-column>
          <el-table-column prop="fromYuanqu" label="所属园区号" width="120">
          </el-table-column>
          <el-table-column prop='modelimg' label="所选择模型" width="120">
            <template slot-scope="scope">
              <img slot="reference" :src="getImgUrl(scope.row.modelimg)" style="width: 50px;height: 50px">
            </template>
          </el-table-column>
          <el-table-column prop="description" label="机房描述" width="400">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit" style="margin-left: 2px"></i></el-button>
              <el-popconfirm
                  confirm-button-text='好的'
                  cancel-button-text='不用了'
                  icon="el-icon-info"
                  icon-color="red"
                  title="这是一段内容确定删除吗？"
                  @confirm="del(scope.row.id)"
              >
              <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline" style="margin-left: 2px"></i></el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
          <el-pagination @size-change="sizeChange" @current-change="currentChange"
                         :current-page="page" :page-size="size" :page-sizes="pageSizes"
                         layout="total, sizes, prev, pager, next, jumper" :total="total">
          </el-pagination>
        </div>
        <el-dialog title="新增机房" :visible.sync="dialogFormVisible">
          <el-form label-width="100px" size="small">
            <el-form-item label="ID" :label-width="formLabelWidth">
              <el-input v-model="form.id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="机房名称" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="所属位置" :label-width="formLabelWidth">
              <el-input v-model="form.location" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="所属园区号" :label-width="formLabelWidth">
              <el-select v-model="form.fromYuanqu" placeholder="请选择" >
                <el-option
                    v-for="item in yuanquoptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="所对应模型" :label-width="formLabelWidth">
              <el-select v-model="form.modelimg" placeholder="请选择" >
                <el-option
                    v-for="item in modeloptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="机房描述" :label-width="formLabelWidth">
              <el-input v-model="form.description" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>
      </el-main>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "showdatabase",

  data() {
    return {
      page: 1, //第几页
      size: 10, //一页多少条
      total: 0, //总条目数
      pageSizes: [3, 5, 10, 20, 50, 100, 200, 300, 400, 500, 1000], //可选择的一页多少条
      tableData: [],
      imgurl: require('/src/assets/logo.png'),
      ALLData:[],
      form:{},
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      dialogFormVisible: false,
      sideWidth: 200,
      logoTextShow: true,
      imgList: [
        {id:0,idView:require('/src/assets/factories_models/模型1.jpg'),name:"厂房模型1",description:"可添加具体描述:\r\n如适用于何种工业生产\r\n生产规模如何..."},
        {id:1,idView:require('/src/assets/factories_models/模型2.jpg'),name:"厂房模型2",description:"可添加具体描述:\r\n如适用于何种工业生产\r\n生产规模如何..."},
        {id:2,idView:require('/src/assets/factories_models/模型3.jpg'),name:"厂房模型3",description:"可添加具体描述:\r\n如适用于何种工业生产\r\n生产规模如何..."},
        {id:3,idView:require('/src/assets/factories_models/模型4.jpg'),name:"厂房模型4",description:"可添加具体描述:\r\n如适用于何种工业生产\r\n生产规模如何..."},
        {id:4,idView:require('/src/assets/factories_models/模型5.jpg'),name:"厂房模型5",description:"可添加具体描述:\r\n如适用于何种工业生产\r\n生产规模如何..."},
        {id:5,idView:require('/src/assets/factories_models/模型6.jpg'),name:"厂房模型6",description:"可添加具体描述:\r\n如适用于何种工业生产\r\n生产规模如何..."}
      ],
      yuanquoptions:[{
        value: '园区1',
        label: '园区1'
      },{
        value: '园区2',
        label: '园区2'
      },{
        value: '园区3',
        label: '园区3'
      },{
        value: '园区4',
        label: '园区4'
      },{
        value: '园区5',
        label: '园区5'
      },{
        value: '园区6',
        label: '园区6'
      },{
        value: '园区7',
        label: '园区7'
      },{
        value: '园区8',
        label: '园区8'
      },],
      modeloptions: [{
        value: 'assets/factories_models/模型1.jpg',
        label: '模型1'
      }, {
        value: 'assets/factories_models/模型2.jpg',
        label: '模型2'
      }, {
        value: 'assets/factories_models/模型3.jpg',
        label: '模型3'
      }, {
        value: 'assets/factories_models/模型4.jpg',
        label: '模型4'
      }, {
        value: 'assets/factories_models/模型5.jpg',
        label: '模型5'
      },{
        value: 'assets/factories_models/模型6.jpg',
        label: '模型6'
      },],
      value: ''
    }
  },

  created() {
    this.$http.get("http://localhost:8088/yuanqu").then((response) => {//箭头函数使作用域继承父级，即created当前级，this可生效
      console.log(response.data)
      this.ALLData = response.data
      this.getTabelData()
    })
  },
  methods:{
    getTabelData() {
      //allData为全部数据
      this.tableData = this.ALLData.slice(
          (this.page - 1) * this.size,
          this.page * this.size
      );
      this.total=this.ALLData.length
    },
    currentChange(val) {
      console.log("翻页，当前为第几页", val);
      this.page = val;
      this.getTabelData();
    },
    getImgUrl(src) {
      return require("@/" + src);
    },
    //size改变时回调的函数，参数为当前的size
    sizeChange(val) {
      console.log("改变每页多少条，当前一页多少条数据", val);
      this.size = val;
      this.page = 1;
      this.getTabelData();
    },
    handleAdd()
    {
      this.dialogFormVisible = true
      this.form = {}
    },
    del(id){
      request.delete("http://localhost:8088/yuanqu/"+id).then(res=> {
        if(res){
          this.$message.success("删除成功")
          this.$router.go(0)
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    save(){
      request.post("http://localhost:8088/insertyuanqu",this.form).then(res =>{
        if(res){
          this.$message.success("保存成功")
          this.$router.go(0)
          this.dialogFormVisible=false
        }else{
          this.$message.error("保存失败")
        }
      })
    },
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>