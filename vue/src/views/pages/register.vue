
<template>

  <div >
    <el-card class="boxcard">
      <el-form :model="formMess" :rules="rules" ref="formMess" label-width="100px" label-position="right">
        <el-form-item label="用户名" required prop="username">
          <el-input  placeholder="请输入用户名" v-model="formMess.username" clearable ></el-input>
        </el-form-item>
        <el-form-item label="电话号码" required prop="userphone">
          <el-input  placeholder="请输入电话号码" v-model="formMess.userphone" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" required prop="password">
          <el-input  placeholder="请输入密码" v-model="formMess.password" show-password clearable></el-input>
        </el-form-item>
        <el-form-item label="确认密码" required prop="password1">
          <el-input  placeholder="请再次输入密码" v-model="formMess.password1" show-password clearable></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="usersex">
<!--          <el-input  placeholder="请输入性别" v-model="usersex" clearable></el-input>-->
          <el-radio v-model="formMess.usersex" label="1" border>男</el-radio>
          <el-radio v-model="formMess.usersex" label="2" border>女</el-radio>
        </el-form-item>
        <el-form-item label="出生日期" prop="uesrbirth">
          <el-date-picker v-model="formMess.uesrbirth" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-button  @click="onSubmit('formMess')" type="success">立即注册</el-button>
        <el-button @click="resetForm('formMess')" type="warning">重置</el-button>
        <el-button  @click="cancel" type="danger">取消</el-button>
      </el-form>
    </el-card>

  </div>

</template>

<script>


import {reactive, toRefs} from "vue";
import router from "@/router";

export default {
  name: "register",
  data(){
    var  validatePass=(rule, value, callback)=>{
      if(value===""){
        callback(new Error('请再次输入密码'))
      }else if(value!==this.formMess.password){
        callback(new Error('两次输入密码不一致!'));
      }else{
        callback();
      }

    };

    return{
      formMess:{
        username:"",
        userphone:"",
        password:"",
        password1:"",
        usersex:"",
        uesrbirth:""
      },
      rules: {
        //验证用户名是否合法
        //required: true表示为必填项
        //trigger: 'blur'表示文本框不被鼠标点击时触发验证行为
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {max: 15,message: "用户名长度不能超过15个字符",}
        ],
        //验证电话号码是否合法
        userphone:[
          { required: true,message: "请输入手机号/电话号", trigger: "blur"},
          { pattern: /^1(3\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\d|9[0-35-9])\d{8}$/,message: "请输入合法手机号/电话号",trigger: "blur"}
        ],
        //验证密码是否合法
        password:[
          {required: true, message: "6-20位英文字母、数字或者符号，且至少包含两种", trigger: "blur"},
          // {min: 6, max: 15, message: "长度在 6 到 15 个字符之间", trigger: "blur",},
          {pattern:/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$)([^\u4e00-\u9fa5\s]){6,15}$/ ,
            message:"6-20位英文字母、数字或者符号，且至少包含两种",trigger: "blur"},
        ],
        password1:[
          // {required: true, message: "请输入密码", trigger: "blur"},
          // {min: 6, max: 15, message: "长度在 6 到 15 个字符之间", trigger: "blur",}
          {validator:validatePass,trigger: 'blur'}
        ]

      }

    }
  },
  methods:{
    onSubmit(formMess) {
      this.$refs[formMess].validate((valid) =>{
        if(valid){
          this.$message({
            // showClose: true,
            showClose: false,
            message: '注册成功',
            duration:1000,
            type: 'success'
          });
          let formData = JSON.stringify(this.formMess);
          console.log(formData)
        }else{//校验不通过
          return false;
        }
      })

      // if(this.formMess.username!==""&&this.formMess.username.length<15){
      //   this.$message({
      //     // showClose: true,
      //     showClose: false,
      //     message: '注册成功',
      //     duration:1000,
      //     type: 'success'
      //   });
      // }

    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    cancel(){
      router.push({
        path:"/layout"
      })
    }

  }

}
</script>

<style scoped>
.el-input {
  width: 300px;
}
.boxcard{
  width: 450px;
  margin: 120px auto;
  /*margin-left:auto;*/
  /*margin-right:auto;*/
  /*margin: 20px;*/
  /*justify-content:center;*/
  /*border: 1px solid blue;*/
  border: 3px solid transparent;
}

</style>