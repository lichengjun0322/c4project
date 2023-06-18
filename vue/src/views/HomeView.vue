<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'"
              style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);text-align: left">
      <el-menu style="min-height: 100%; overflow-x: hidden; overflow-y: hidden"
               background-color="rgb(48, 65, 86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :default-active='activeIndex'
               :collapse-transition="false"
               :collapse="isCollapse"
               router
               unique-opened:true
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/muanlogo.png" alt="" style="width: 35px; position: relative; top: 10px">
          <b style="color: white;margin-left: 5px;margin-right: 10px" v-show="logoTextShow ">HUPU</b>
        </div>
        <el-menu-item index="shouye">
          <i class="el-icon-view"></i>
          <span slot="title"> 首页</span>
        </el-menu-item>
        <el-menu-item index="yuanquzonglan">
          <i class="el-icon-view"></i>
          <span slot="title"> 视频管理</span>
        </el-menu-item>
        


        <el-menu-item index="shipinfenxi?url=null">

          <i class="el-icon-cpu"></i>
          <span slot="title"> 视频分析</span>
        </el-menu-item>
        <el-menu-item index="tongjirizhi">
          <i class="el-icon-date"></i>
          <span slot="title"> 数据统计</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex;width: 100%;">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>{{ sysuser.username }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown" style="text-align: center;width: 120px">
            <el-dropdown-item style="padding: 5px 0;font-size: 14px">个人信息</el-dropdown-item>
            <el-dropdown-item style="padding: 5px 0;font-size: 14px">
              <span style="text-decoration: none;" @click="logout">退出</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main>
        <router-view>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>


export default {
  name: 'Home',
  // 组件起名字
  data() {
    return {
      tableData: [],
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      activeIndex: '',
      sysuser: localStorage.getItem("sysuser")? JSON.parse(localStorage.getItem("sysuser")) : {}
    }
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    logout() {
      this.$router.push("/")
      localStorage.removeItem("sysuser")
      this.$message.success("退出成功")
    },
    watch: {
      $route(e) {
        // 监听路由，当路由改变时获取到路由的name也就是菜单的index
        this.activeIndex = e.name
      }
    },
    handleClick(name) {
      this.$router.push({
        path: '/jifang',
        query: {
          name:name
        }
      })
    }
  },
}
</script>