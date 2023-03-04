<template>

  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item >个人中心</el-breadcrumb-item>
      <el-breadcrumb-item>设置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="12" style="margin-top: 20px">
      <el-col :span="100">
        <el-card class="box-card" shadow="hover">
          <el-descriptions class="margin-top" title="带边框列表" :column="3" :size="size" border>
            <template slot="extra">
              <el-button type="primary" size="small" @click="openChange()">操作</el-button>
            </template>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                用户名
              </template>
              kooriookami
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                手机号
              </template>
              18100000000
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                性别
              </template>
              男
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                出生日期
              </template>
              2001-01-01
            </el-descriptions-item>
          </el-descriptions>

        </el-card>


      </el-col>
      <el-col :span="100">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix" align="left">
            <span>更多操作</span>
          </div>
          <div align="left">
            <el-button type="warning">修改密码</el-button>
            <el-button type="danger">注销账号</el-button>
          </div>

<!--          </el-row>-->
        </el-card>
      </el-col>
    </el-row>


    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sure()">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "settings",
  data(){

    return{
      dialogFormVisible: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px'
    }
  },
  methods:{
    openChange(){
      this.dialogFormVisible = true
    },
    sure(){
      alert("确定修改")
      this.dialogFormVisible = false
    }
  }


}
</script>

<style scoped>
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

.box-card {
  width: 800px;
  margin-bottom: 20px;
}
</style>