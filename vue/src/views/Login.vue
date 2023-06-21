<template>
  <div class="wrapper">

    <div
        style="margin: 200px auto; background-color: #fff; width: 350px; height: 350px; padding: 20px; border-radius: 10px;opacity: 0.8">
      <el-row gutter="20" style="margin-bottom: 30px">
        <el-col span="1">
          <img src="../assets/muanlogo.png" alt="" style="width: 50px; position: relative; top: 10px">
        </el-col>
        <el-col span="6">
          <div id="name" style="width: 300px;height: 50px;margin-top: 20px;margin-left: 10px"></div>
        </el-col>
      </el-row>

<!--      <div style="margin: 20px; text-align: center; font-size: 24px"><b>登 录</b></div>-->
      <el-form :model="sysuser" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"
                    v-model="sysuser.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                    v-model="sysuser.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>


import axios from "axios";
import * as echarts from "echarts";

export default {
  name: "Login",
  data() {
    return {
      sysuser: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  mounted() {
    this.drawtext();
  },
  methods: {
    login() {
        this.$message.success("登录成功")
        this.$router.push("/shouye")
    },
    drawtext(){
      let myChart = echarts.init(document.getElementById("name"));
      let option = {
        graphic: {
          elements: [
            {
              type: 'text',
              left: 'center',
              top: 'center',
              style: {
                text: '篮球训练智能助手',
                fontSize: 24,
                lineDash: [0, 200],
                lineDashOffset: 0,
                fill: 'transparent',
                stroke: '#000',
                lineWidth: 1
              },
              keyframeAnimation: {
                duration: 3000,
                keyframes: [
                  {
                    percent: 0.7,
                    style: {
                      fill: 'transparent',
                      lineDashOffset: 200,
                      lineDash: [200, 0]
                    }
                  },
                  {
                    // Stop for a while.
                    percent: 0.8,
                    style: {
                      fill: 'transparent'
                    }
                  },
                  {
                    percent: 1,
                    style: {
                      fill: 'black'
                    }
                  }
                ]
              }
            }
          ]
        }
      };
      myChart.setOption(option);
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
  overflow: hidden;
}
</style>
