<template>
  <div>
    <el-row :gutter="12">
      <el-col :span="100">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>图片分析</span>
            <!--        <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
          </div>
          <div>

            <div class="camera_outer">
              <video
                  id="videoCamera"
                  :width="videoWidth"
                  :height="videoHeight"
                  autoplay
                  style="background: #42b983"



              >

              </video>
              <canvas
                  style="display: none;background: #42b983"
                  id="canvasCamera"
                  :width="videoWidth"
                  :height="videoHeight"
              ></canvas>
              <div v-if="imgSrc" class="img_bg_camera">
                <img :src="imgSrc" alt="" class="tx_img"/>
              </div>

            </div>
            <div class="card_but">
              <el-button type="primary" size="small" @click="openvideo()"> 打开摄像头
                <!--            <i class="el-icon-video-camera-solid"> </i>-->
              </el-button>
              <el-button type="danger" size="small" @click="closevideo()"> 关闭摄像头
                <!--            <i class="el-icon-video-camera-solid"> </i>-->
              </el-button>
              <el-button type="primary" size="small" @click="screenshot()">截屏
                <!--            <i class="el-icon-camera-solid"></i>-->

              </el-button>

              <!--          <el-button type="success" size="small" @click="upload()">上传-->
              <!--&lt;!&ndash;            <i class="el-icon-upload el-icon&#45;&#45;right"></i>&ndash;&gt;-->
              <!--          </el-button>-->


            </div>
            <div class="upload_but">
              <el-upload
                  class="upload-demo"
                  ref="upload"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :before-remove="beforeRemove"

                  :file-list="fileList"
                  multiple
                  :limit="1"
                  :on-exceed="handleExceed"
                  :auto-upload="false">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="upload()">上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过5MB</div>
              </el-upload>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col  :span="100">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <span>分析结果</span>
            <!--        <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
          </div>
          <div>
<!--            <el-skeleton :rows="12" animated/>-->
            <el-empty :image-size="200"></el-empty>
          </div>
        </el-card>
      </el-col>

    </el-row>


  </div>

</template>

<script>
export default {
  name: "home",
  data() {
    return {
      os: true,// //控制摄像头开关
      videoWidth: 600,
      videoHeight: 300,
      imgSrc: "",
      thisCancas: null,
      thisContext: null,
      thisVideo: null,
      // fileList:[{name:'',url:''}]
      fileList: null,
      videoDisplay: false
    }
  },
  methods: {
    openvideo() {
      var _this = this;
      this.os = false; //切换成关闭摄像头
      this.thisCancas = document.getElementById("canvasCamera");
      this.thisContext = this.thisCancas.getContext("2d");
      this.thisVideo = document.getElementById("videoCamera");
      // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {};
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function (constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia =
              navigator.webkitGetUserMedia ||
              navigator.mozGetUserMedia ||
              navigator.getUserMedia;
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          if (!getUserMedia) {
            return Promise.reject(
                new Error("getUserMedia is not implemented in this browser")
            );
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function (resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject);
          });
        };
      }
      var constraints = {
        audio: false,
        video: {
          width: this.videoWidth,
          height: this.videoHeight,
          transform: "scaleX(-1)",
        },
      };
      navigator.mediaDevices
          .getUserMedia(constraints)
          .then(function (stream) {
            // 旧的浏览器可能没有srcObject
            if ("srcObject" in _this.thisVideo) {
              _this.thisVideo.srcObject = stream;
            } else {
              // 避免在新的浏览器中使用它，因为它正在被弃用。
              _this.thisVideo.src = window.URL.createObjectURL(stream);
            }
            _this.thisVideo.onloadedmetadata = function (e) {
              _this.thisVideo.play();
            };
          })
          .catch((err) => {
            this.$message({
              message: "没有开启摄像头权限或浏览器版本不兼容",
              type: "warning",
            });
          });
    },
    clearCanvas(id) {
      let c = document.getElementById(id);
      let cxt = c.getContext("2d");
      cxt.clearRect(0, 0, c.width, c.height);
    },
    resetBtn() {
      this.clearCanvas("canvasCamera");
    },
    closevideo() {
      this.thisVideo.srcObject.getTracks()[0].stop();
      let cxt = this.thisCancas.getContext("2d");
      cxt.drawImage("../../assets/logo.png",0,0);
      this.thisVideo.background = "#42b983"
      // if (this.thisVideo) {
      //   this.thisVideo.srcObject.getTracks()[0].stop();
       this.os = true; //切换成打开摄像头
      // }
    },

    screenshot() {
    },
    // picchange(file) {
    //   this.$refs.upload.clearFiles()
    //   console.log(file)
    //   const isJPG =
    //       file.raw.type === "image/jpeg" || file.raw.type === "image/png"
    //   const isLt5M = file.raw.size / 1024 / 1024 < 5
    //
    //   if (!isLt5M) {
    //     this.$message.warning("上传图片大小不能超过 5M!")
    //     return false
    //   } else if (!isJPG) {
    //     this.$message.warning("上传图片格式只能是jpg或png")
    //     return false
    //   } else {
    //     this.imgchecked(file).then((data) => {
    //       //data返回值即为判断结果。
    //       if (data) {
    //         this.form.logo_src = URL.createObjectURL(file.raw)//预览图
    //         this.logoupload = file.raw
    //       }
    //     })
    //   }
    // },
    upload() {

      this.$refs.upload.submit();
      console.log(this.imgSrc)

    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      this.$message.warning('只能上传jpg/png文件，且不超过5MB，')
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    }


  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;

&
:last-child {
  margin-bottom: 10px;
}

}
.el-col {
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgb(218, 241, 237);

}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #BEE7E9;
  width: auto;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 1px;
  min-height: 360px;
}

.row-bg {
  padding: 1px 0;
  background-color: #f9fafc;
}

.html {
  background: #BEE7E9;
}

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
  background-color: aquamarine;
}

.clearfix:after {
  clear: both;
  background-color: aquamarine;
}

.box-card {
  width: 480px;
  /*background-color: #99a9bf;*/
}

.card_but {
  margin-top: 10px;
  /*display: flex;*/
  align-items: center;
  justify-content: space-around;
}

.upload_but {
  margin-top: 10px;
}

/*.bgdiv{*/
/*  background: #BEE7E9;*/
/*  display: flex;*/
/*  align-items: center;*/
/*}*/
.camera_outer {
  position: relative;
  overflow: hidden;
  background-size: 100%;

video,
canvas,
.tx_img {
  -moz-transform: scaleX(-1);
  -webkit-transform: scaleX(-1);
  -o-transform: scaleX(-1);
  transform: scaleX(-1);
}

.btn_camera {
  position: absolute;
  bottom: 4px;
  left: 0;
  right: 0;
  height: 50px;
  background-color: rgba(0, 0, 0, 0.3);
  line-height: 50px;
  text-align: center;
  color: #ffffff;
}

.bg_r_img {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  top: 0;
}

.img_bg_camera {
  position: absolute;
  right: 0;
  top: 0;
}

img {
  width: 300px;
  height: 300px;
}

.img_btn_camera {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 50px;
  line-height: 50px;
  text-align: center;
  background-color: rgba(0, 0, 0, 0.3);
  color: #ffffff;
}

.loding_img {
  width: 50px;
  height: 50px;
}

}

</style>