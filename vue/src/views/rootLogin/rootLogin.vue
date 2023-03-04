<template>
    <div class="container">
        <el-card class="box-card">
            <el-form :model="formData" :rules="rule" ref="form">
                <h3>管理员登录</h3>
                <el-form-item prop="phone">
                    <el-input v-model="formData.phone" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" v-model="formData.password" placeholder="密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login">登录</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: 'rootLogin',
        data() {
            return {
                formData: {
                    phone: '',
                    password: ''
                },
                rule: {
                    phone: [
                        {
                            required: true, message: "请输入用户名！", trigger: "blur"
                        },

                    ],
                    password: [
                        {
                            required: true, message: "请输入密码！", trigger: "blur"
                        },
                    ]
                }
            }
        },
        methods: {
            //登录
            login() {
                alert(this.formData.phone);
            },
        }
    }
</script>

<style>
    .box-card {
        margin: 100px auto;
        width: 400px;
    }
</style>