<template>
  <div class="background">
    <div class="row">
        <div style="height: 100px"></div>
      <div class="col-lg-8 col-xl-6 mx-auto">
        <div
          class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden"
        >
          <div class="card-img-left d-none d-md-flex">
            <!-- Background image for card set in CSS! -->
          </div>
          <div class="login-register">
            <Vcode
              :show="showCode"
              @success="codeSuccess"
              @close="codeClose"
            ></Vcode>
            <div>
              <div v-if="choose === 0" class="board">
                <div style="height: 30px"></div>
                <div class="btitle">登 录</div>
                <div class="bform">
                  <input
                    type="number"
                    placeholder="学工号"
                    v-model="user.u_id"
                  />
                  <div class="errTips" v-if="conflict.u_id">
                    * 学号填写错误 *
                  </div>
                  <div class="errTips" v-else></div>
                  <input
                    type="password"
                    placeholder="密码"
                    v-model="user.password"
                    @keydown.enter="login"
                  />
                  <div class="errTips" v-if="conflict.password">
                    * 密码填写错误 *
                  </div>
                  <div class="errTips" v-else></div>
                </div>
                <div style="text-align: center">
                  <button class="bbutton" @click="clickButton">登录</button>
                </div>
                <div
                  style="font-size: 16px; width: 50%; margin: 30px auto auto"
                >
                  <div style="float: left" @click="changeType(1)">激活</div>
                  <div style="float: right" @click="changeType(2)">
                    找回密码
                  </div>
                </div>
              </div>
              <div v-else-if="choose === 2" class="board">
                <div style="height: 30px"></div>
                <div class="btitle">
                  <div style="float: left; width: 20%">
                    <i class="el-icon-back" @click="changeType(0)"></i>
                  </div>
                  <div style="width: 60%; float: left">找回密码</div>
                  <div style="float: right; width: 20%"></div>
                </div>
                <div class="bform" style="clear: both">
                  <input type="text" placeholder="学工号" v-model="user.u_id" />
                  <div class="errTips" v-if="conflict.u_id">
                    * 学号不存在！ *
                  </div>
                  <div class="errTips" v-else></div>
                  <input type="email" placeholder="邮箱" v-model="user.email" />
                  <div class="errTips" v-if="conflict.email">
                    * 邮箱不存在！ *
                  </div>
                  <div class="errTips" v-else></div>
                  <div
                    style="
                      width: 67%;
                      height: 40px;
                      line-height: 40px;
                      margin-top: 20px;
                    "
                  >
                    <input
                      style="
                        width: 45%;
                        margin-top: 0;
                        float: left;
                        margin-left: 15px;
                      "
                      type="email"
                      v-model="user.code"
                    />
                    <button
                      class="sender"
                      @click="countDown"
                      :disabled="senderDisable"
                    >
                      {{ content }}
                    </button>
                  </div>
                  <input
                    type="password"
                    placeholder="新密码"
                    v-model="user.password"
                    @keydown.enter="register"
                  />
                </div>
                <div style="text-align: center">
                  <button class="bbutton" @click="clickButton">确定</button>
                </div>
              </div>
              <div v-else class="board">
                <div style="height: 30px"></div>
                <div class="btitle">
                  <div style="float: left; width: 20%"></div>
                  <div style="float: right; width: 20%">
                    <i class="el-icon-right" @click="changeType(0)"></i>
                  </div>
                  <div style="width: 60%; float: right">激活账户</div>
                </div>
                <div class="bform" style="clear: both">
                  <input type="text" placeholder="学工号" v-model="user.u_id" />
                  <div class="errTips" v-if="conflict.u_id">
                    * 学号不存在！ *
                  </div>
                  <div class="errTips" v-else></div>
                  <input type="email" placeholder="邮箱" v-model="user.email" />
                  <div class="errTips" v-if="conflict.email">
                    * 邮箱绑定！ *
                  </div>
                  <div class="errTips" v-else></div>
                  <div
                    style="
                      width: 67%;
                      height: 40px;
                      line-height: 40px;
                      margin-top: 20px;
                    "
                  >
                    <input
                      style="
                        width: 45%;
                        margin-top: 0;
                        float: left;
                        margin-left: 15px;
                      "
                      type="email"
                      v-model="user.code"
                    />
                    <button
                      class="sender"
                      @click="countDown"
                      :disabled="senderDisable"
                    >
                      {{ content }}
                    </button>
                  </div>
                  <input
                    type="password"
                    placeholder="设置密码"
                    v-model="user.password"
                    @keydown.enter="register"
                  />
                </div>
                <div style="text-align: center">
                  <button class="bbutton" @click="clickButton">激活</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vcode from "vue-puzzle-vcode";
export default {
  name: "LoginActivate",
  components: {
    Vcode,
  },
  data() {
    return {
      choose: 0,
      content: "获取验证码",
      totalTime: 60,
      senderDisable: false,
      conflict: {
        u_id: false,
        email: false,
        password: false,
        code: false,
        //    后面可以加验证码之类的
      },
      user: {
        u_id: "",
        email: "",
        // 验证码
        code: "",
        password: "",
      },
      showCode: false,
    };
  },
  methods: {
    changeType(type) {
      this.conflict = {
        u_id: false,
        email: false,
        password: false,
      };
      this.user = {
        u_id: "",
        email: "",
        code: "",
        password: "",
      };
      if (type === 0) {
        this.choose = 0;
      } else if (type === 1) {
        this.choose = 1;
      } else {
        this.choose = 2;
      }
    },
    countDown() {
      if (this.senderDisable) {
        return;
      }
      this.senderDisable = true;
      this.content = this.totalTime + "s后重新发送"; //这里解决60秒不见了的问题
      let clock = window.setInterval(() => {
        this.totalTime--;
        this.content = this.totalTime + "s后重新发送";
        if (this.totalTime < 0) {
          //当倒计时小于0时清除定时器
          window.clearInterval(clock);
          this.content = "重新发送验证码";
          this.totalTime = 60;
          this.senderDisable = false;
        }
      }, 1000);
      this.sendCode();
    },
    userLogin() {
      this.axios({
        method: "post",
        url: "/api/login/userLogin",
        params: {
          u_id: this.user.u_id,
          password: this.user.password,
        },
      }).then((resp) => {
        console.log(resp.data);

        var roles = { 999: "admin", 4: "student", 2: "teacher" };

        //我在此处加逻辑 、  message提示  以及路由跳转
        //以及   把信息存储到vuex中
        //0 成功 1 uid错误 2 密码错误 3 未激活 4 邮箱冲突
        //5 验证码错误 6 邮箱状态错误 7 邮箱未找到 8 找回密码成功
        //9 激活成功 10 已激活不可再激活 11 邮箱更换成功

        if (resp.data.errorCode == 0) {
          var user = resp.data.detail;
          var role = roles[user.role];
          console.log(role);

          sessionStorage.setItem(
            "UserInfo",
            JSON.stringify({ id: user.u_id, role: role, name: user.name })
          );
          this.$store.commit("setUserInfo");

          this.$router.push("/" + role + "/board");
        } else if (resp.data.errorCode == 1) {
          this.$message.warning("该用户不存在!");
        } else if (resp.data.errorCode == 2) {
          this.$message.warning("密码错误!");
        } else if (resp.data.errorCode == 3) {
          this.$message.warning("请先激活该账户!");
        }
      });
    },
    // 激活
    userActivate() {
      this.axios({
        method: "post",
        url: "/api/login/userActivate",
        data: {
          u_id: this.user.u_id,
          code: this.user.code,
          email: this.user.email,
          password: this.user.password,
        },
        headers: {
          "Content-Type": "application/json",
        },
      }).then((resp) => {
        console.log(resp.data);

        if (resp.data.errorCode == 9) {
          this.$message.success("激活成功!");
        } else if (resp.data.errorCode == 10) {
          this.$message.warning("该用户已激活!");
        } else if (resp.data.errorCode == 4) {
          this.$message.warning("该邮箱已被注册!");
        } else if (resp.data.errorCode == 5) {
          this.$message.warning("验证码错误!");
        } else if (resp.data.errorCode == 6) {
          this.$message.warning("该邮箱不可用!");
        } else if (resp.data.errorCode == 7) {
          this.$message.warning("该邮箱不存在!");
        }
      });
    },
    // 忘记密码
    userForget() {
      this.axios({
        method: "post",
        url: "/api/login/recoverUserPassword",
        data: {
          u_id: this.user.u_id,
          code: this.user.code,
          email: this.user.email,
          password: this.user.password,
        },
        headers: {
          "Content-Type": "application/json",
        },
      }).then((resp) => {
        console.log(resp.data);
        if (resp.data.errorCode == 8) {
          this.$message.success("找回密码成功!");
        } else if (resp.data.errorCode == 11) {
          this.$message.warning("邮箱更改成功!");
        }
      });
    },
    sendCode() {
      this.axios({
        method: "post",
        url: "/api/login/sendEmail",
        params: {
          email: this.user.email,
        },
      }).then((resp) => {
        console.log(resp.data);
      });
    },
    checkInfo() {
      if (this.user.u_id.length === 0) {
        return false;
      } else if (this.user.password.length === 0) {
        return false;
      } else if (this.user.email.length === 0 && this.choose !== 0) {
        return false;
      } else if (this.user.code.length === 0 && this.choose !== 0) {
        return false;
      }

      return true;
    },
    clickButton() {
      if (this.checkInfo()) {
        this.showCode = true;
      } else {
        alert("请填写完整信息");
      }
    },
    codeSuccess() {
      this.showCode = false;
      if (this.choose === 0) {
        console.log("login");
        this.userLogin();
      } else if (this.choose === 1) {
        console.log("activate");
        this.userActivate();
      } else if (this.choose === 2) {
        console.log("forget");
        this.userForget();
      } else {
        console.log("bad page iter");
      }
    },
    codeClose() {
      this.showCode = false;
    },
  },
};
</script>




<style scoped>
.btitle {
  font-size: 1.5em;
  font-weight: bold;
  color: #1b0c0c;
  text-align: center;
}
.board {
  width: 415px;
  height: 500px;
  background-color: #476a86;
  /* border-radius: 20px; */
}
.sender {
  width: 40%;
  font-size: 14px;
  height: 40px;
  display: inline-block;
  float: right;
  border: none;
  background-color: transparent;
}
.counter {
}
.bform {
  width: 100%;
  padding: 2em 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.errTips {
  display: block;
  height: 10px;
  width: 50%;
  text-align: left;
  color: red;
  font-size: 0.7em;
  margin-left: 1em;
}
input {
  width: 60%;
  height: 40px;
  border: none;
  outline: none;
  margin-top: 30px;
  border-radius: 10px;
  padding-left: 2em;
  background-color: #f0f0f0;
}
.bbutton {
  width: 60%;
  height: 50px;
  font-size: 20px;
  border-radius: 24px;
  border: none;
  outline: none;
  background-color: #444444;
  color: #fff;
  cursor: pointer;
}
.bbutton:hover {
  background-color: #aaaaaa;
  color: #000000;
  transition: background-color 0.5s ease;
}
/* yuran */
.background {
    /* background: #dc651b; */
    background: linear-gradient(to right, #415a6b, #adc4ee);
    height: 100vh;
    width: 100%;
}

.card-img-left {
  width: 51%;
  /* Link to your background image using in the property below! */
  /* background: scroll center url('https://source.unsplash.com/WEQbe2jBg40/414x512'); */
  background: scroll center url("@/assets/images/TongJi.png");
  background-size: cover;
}

input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  appearance: none;
  margin: 0;
}
</style>
