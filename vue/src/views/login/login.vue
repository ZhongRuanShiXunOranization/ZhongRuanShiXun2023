<template>
    <div class="container">
        <el-card class="box-card">
            <el-form :model="formData" :rules="rule" ref="form">
                <h3>登录系统</h3>
                <el-form-item prop="phone">
                    <el-input v-model="formData.phone" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" v-model="formData.password" placeholder="密码"></el-input>
                </el-form-item>
                <el-form-item prop="word">
                    <el-row>
                        <el-col :span="14">
                            <el-input placeholder="验证码" v-model="formData.word">
                            </el-input>
                        </el-col>
                        <el-col :span="10">
                            <div>
                                <el-image
                                        src="data:image/jpeg;base64,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"
                                        alt=""
                                        @click="refresh()"
                                        style="width:80%; height:80%"
                                ></el-image>
                            </div>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login">登录</el-button>
                </el-form-item>
                <el-container direction="vertical">
                    <el-form-item style="margin-bottom:-20px">
                        <el-button style="float:right" type="text" @click="forget">忘记密码</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="float:right" type="text" @click="register">注册</el-button>
                    </el-form-item>
                </el-container>
            </el-form>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: 'Login',
        data() {
            return {
                img: '',
                formData: {
                    phone: '',
                    password: '',
                    word: ''
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
                    ],
                    word: [
                        {
                            required: true, message: "验证码为空！", trigger: "blur"
                        }
                    ]
                }
            }
        },
        methods: {
            //登录
            login() {
                if (this.formData.phone == null || this.formData.password == null) {
                    alert("null");
                    return;
                }
                alert(this.formData.phone)
            },
            // 验证码刷新
            refresh() {
            },
            //忘记密码
            forget() {
                this.$router.push('/forgetPassword');
            },
            //注册
            register() {
                this.$router.push('/register');
            }
        }
    }
</script>

<style>
    .box-card {
        margin: 100px auto;
        width: 400px;
    }
</style>

