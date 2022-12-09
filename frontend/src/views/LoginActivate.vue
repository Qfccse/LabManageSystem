<template>
    <div class="login-register">
        <div>
            <div v-if="choose===0"  class="board">
                <div style="height: 30px"></div>
                <div class="btitle">账户登录</div>
                <div class="bform">
                    <input type="email" placeholder="学工号" v-model="user.u_id">
                    <div class="errTips" v-if="conflict.u_id">* 学号填写错误 *</div>
                    <div class="errTips" v-else></div>
                    <input type="password" placeholder="密码" v-model="user.password" @keydown.enter="login">
                    <div class="errTips" v-if="conflict.password">* 密码填写错误 *</div>
                    <div class="errTips" v-else></div>
                </div>
                <div style="text-align: center">
                    <button class="bbutton" @click="userLogin">登录</button>
                </div>
                <div style="font-size: 16px;width: 50%;margin: 30px auto auto;">
                    <div style="float: left" @click="changeType(1)">
                        激活
                    </div>
                    <div style="float: right" @click="changeType(2)">
                        找回密码
                    </div>
                </div>
            </div>
            <div v-else-if="choose===2"  class="board">
                <div style="height: 30px"></div>
                <div class="btitle">
                    <div style="float: left;width: 20%">
                        <i class="el-icon-back" @click="changeType(0)"></i>
                    </div>
                    <div style="width: 60%;float: left">
                        找回密码
                    </div>
                    <div style="float: right;width: 20%">
                    </div>

                </div>
                <div class="bform" style="clear:both;">
                    <input type="text" placeholder="学号" v-model="user.u_id">
                    <div class="errTips" v-if="conflict.u_id">* 学号不存在！ *</div>
                    <div class="errTips" v-else></div>
                    <input type="email" placeholder="邮箱" v-model="user.email">
                    <div class="errTips" v-if="conflict.email">* 邮箱不存在！ *</div>
                    <div class="errTips" v-else></div>
                    <div style="width: 67%;height:40px;line-height: 40px;margin-top: 20px">
                        <input style="width: 45%;margin-top: 0;float: left" type="email" v-model="user.code">
                        <button  class="sender" @click="countDown" :disabled="senderDisable">{{content}}</button>
                    </div>
                    <input type="password" placeholder="新密码" v-model="user.password" @keydown.enter="register">
                </div>
                <div style="text-align: center">
                    <button class="bbutton" @click="userForget">确定</button>
                </div>
            </div>
            <div v-else class="board">
                <div style="height: 30px"></div>
                <div class="btitle">
                    <div style="float: left;width: 20%">
                    </div>
                    <div style="float: right;width: 20%">
                        <i class="el-icon-right" @click="changeType(0)"></i>
                    </div>
                    <div style="width: 60%;float: right">
                        激活账户
                    </div>
                </div>
                <div class="bform" style="clear:both;">
                    <input type="text" placeholder="学号" v-model="user.u_id">
                    <div class="errTips" v-if="conflict.u_id">* 学号不存在！ *</div>
                    <div class="errTips" v-else></div>
                    <input type="email" placeholder="邮箱" v-model="user.email">
                    <div class="errTips" v-if="conflict.email">* 邮箱绑定！ *</div>
                    <div class="errTips" v-else></div>
                    <div style="width: 67%;height:40px;line-height: 40px;margin-top: 20px">
                        <input style="width: 45%;margin-top: 0;float: left" type="email" v-model="user.code">
                        <button  class="sender" @click="countDown" :disabled="senderDisable">{{content}}</button>
                    </div>
                    <input type="password" placeholder="设置密码" v-model="user.password" @keydown.enter="register">
                </div>
                <div style="text-align: center">
                    <button class="bbutton" @click="userActivate">激活</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>


export default {
    name: 'LoginActivate',
    data() {
        return {
            choose:0,
            conflict:{
                u_id:false,
                email:false,
                password:false,
                //    后面可以加验证码之类的
            },
            content:"获取验证码",
            totalTime:60,
            senderDisable:false,
            user:{
                u_id:'',
                email:'',
                // 验证码
                code:"",
                password:'',
            }
        }
    },
    methods: {
        changeType(type) {
            this.conflict = {
                u_id:false,
                email:false,
                password:false,
            }
            this.user = {
                u_id:'',
                email:'',
                code:"",
                password:'',
            }
            if (type===0)
            {
                this.choose = 0
            }
            else if (type===1){
                this.choose = 1
            }
            else {
                this.choose = 2
            }
        },
        countDown() {
            if(this.senderDisable){
                return
            }
            this.senderDisable = true
            this.content = this.totalTime + 's后重新发送' //这里解决60秒不见了的问题
            let clock = window.setInterval(() => {
                this.totalTime--
                this.content = this.totalTime + 's后重新发送'
                if (this.totalTime < 0) {     //当倒计时小于0时清除定时器
                    window.clearInterval(clock)
                    this.content = '重新发送验证码'
                    this.totalTime = 60
                    this.senderDisable = false
                }
            },1000)
        },
        userLogin() {

        },
        // 激活
        userActivate(){

        },
        // 忘记密码
        userForget(){

        }
    }
}
</script>




<style scoped>
.btitle{
    font-size: 1.5em;
    font-weight: bold;
    color: #444444;
    text-align: center;
}
.board{
    width: 400px;
    height: 500px;
    background-color: #B3C0D1;
    border-radius: 20px
}
.sender{
    width: 40%;
    font-size: 14px;
    height: 40px;
    display: inline-block;
    float: right;
    border: none;
    background-color: transparent;
}
.counter{

}
.bform{
    width: 100%;
    padding: 2em 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.errTips{
    display: block;
    height: 10px;
    width: 50%;
    text-align: left;
    color: red;
    font-size: 0.7em;
    margin-left: 1em;
}
input{
    width: 60%;
    height: 40px;
    border: none;
    outline: none;
    margin-top: 30px;
    border-radius: 10px;
    padding-left: 2em;
    background-color: #f0f0f0;
}
.bbutton{
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
.bbutton:hover{
    background-color: #aaaaaa;
    color: #000000;
    transition: background-color 0.5s ease;
}
</style>
