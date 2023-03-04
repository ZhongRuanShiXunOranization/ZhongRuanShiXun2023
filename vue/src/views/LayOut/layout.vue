<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px" class="common-aside" style="height: 100%">
        <el-menu background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b" :router="true">
          <!--              <el-menu background-color="F0FFF0" text-color="#F0FFF0" active-text-color="#409EFF" :router="true">-->
          <el-menu-item index="/home">
            <i class="el-icon-menu"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-submenu >
            <template>
              <span slot="title"><i class="el-icon-user"></i>个人中心</span>
            </template>
              <el-menu-item-group>
                <el-menu-item index="/settings"><i class="el-icon-setting" style="margin-left: 25px "></i>个人设置</el-menu-item>
                <el-menu-item index="/historyPage"><i class="el-icon-time" style="margin-left: 25px "></i>历史记录</el-menu-item>
              </el-menu-item-group>


          </el-submenu>

        </el-menu>

      </el-aside>

      <el-container>
        <el-header class="common-header flex-float">
          <div class="flex-float">
            <!--              <h1 class="title"> 依托答辩</h1>-->
          </div>
          <!--        <div >-->
          <!--          <el-menu  class="el-menu-demo" mode="horizontal" text-color="#000000" active-text-color="#409EFF">-->

          <!--            <router-link to="/home"><el-menu-item>处理中心</el-menu-item></router-link>-->
          <!--            <router-link to="/settings"><el-menu-item >个人中心</el-menu-item></router-link>-->
          <!--          </el-menu>-->
          <!--        </div>-->
          <el-button type="danger" size="small" round @click="logOut"><i class="el-icon-switch-button"></i>  退出登录</el-button>

        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>

    </el-container>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "layout",
  data() {
    return {}
  },
  methods: {
    logOut() {
      router.push({
        path: "/"
      })
    }
  }
}
</script>

<style scoped>
.el-container {
  height: 100vh;

}

.common-header {
  background: white;
  display: flex;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  /*box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)*/
}

.common-aside {
  /*display: flex;*/
  background-color: #545c64;
}

</style>