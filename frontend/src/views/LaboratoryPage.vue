<template>
    <div style="width: 1050px;margin: auto">
        <el-button @click="createReport">123123</el-button>
        <el-row class="lab_name">
            <el-col :span="16">{{lab.name}}</el-col>
            <el-col :span="8">
<!--                <router-link :to="{}">-->
                    <el-button type="primary" class="fr" style="margin-top: 20px" @click="click2Fill">完成实验报告</el-button>
<!--                </router-link>-->
            </el-col>
        </el-row>
        <div style="width: 750px;" class="fl">
            <el-row class="lab_info clear_box">
                <el-col :span="3" class="lab_ddl">截止时间：</el-col>
                <el-col :span="8" class="l_time">{{getNowFormatDate(lab.end_time)}}</el-col>
                <el-col :span="3" class="lab_prop">分数权重：</el-col>
                <el-col :span="2" class="l_prop">{{lab.proportion}}</el-col>
                <!--            <el-col :span="6" ><el-button type="success" class="fr">完成实验报告</el-button></el-col>-->
            </el-row>
            <div class="lab_intro clear_box" style="min-height: 400px">
                {{lab.desc}}
            </div>
            <div style="font-size: 16px">
                <div style="font-size: 18px">实验指导书</div>
                <div v-for="(guidebook,index) in guidebookList" :key="index">
                    <router-link :to="{name:'GuidebookPage',query:{g_id:guidebook.g_id,name:guidebook.name}}">
                        <div class="li_guidebook">
                            <i class="el-icon-document">&nbsp;</i><a href="#">{{guidebook.name}}</a>
                        </div>
                    </router-link>
                </div>
            </div>
        </div>
        <div class="fl" style="width: 250px;margin-left: 50px;">
            <FeedbackCreator :lid="lab.l_id" sid="1952168"></FeedbackCreator>
        </div>
    </div>

</template>

<script>

import FeedbackCreator from "@/components/Bulletin/FeedbackCreator";
export default {
    name: "LaboratoryPage",
    components: {FeedbackCreator},
    data(){
        return{
            l_id:0,
            lab:{},
            guidebookList:[],
        }
    },
    mounted() {
        // this.l_id = this.$route.query.l_id
        this.l_id = "1"
        this.getLab()
        this.showGuidebookList()
        console.log(new Date(this.lab.end_time))
    },
    methods:{
        fillZero(str){
            if (str >= 0 && str <= 9) {
                return  "0" +str
            }
            else {
                return str + ""
            }
        },
        getNowFormatDate(dt) {
            var date = new Date(dt)
            var s1 = "-"
            var s2 = ":"
            var year = date.getFullYear()
            var month = this.fillZero(date.getMonth() + 1)
            var strDate = this.fillZero(date.getDate())
            var hour = this.fillZero(date.getHours())
            var mini = this.fillZero(date.getMinutes())
            var sec = this.fillZero(date.getSeconds())
            var currentdate = year + s1 + month + s1 + strDate + " " + hour + s2 + mini + s2 + sec;
            return currentdate;
        },
        click2Fill(){
            if(new Date() >= new Date(this.lab.end_time))
            {
                alert("实验已结束")
                return
            }
            this.createReport()
            this.$router.push({
                name:'ReportPage',
                query:{
                    l_id:this.lab.l_id,
                    s_id:'1952168',
                    l_name:this.lab.name
                }
            })
        },
        getLab(){
            this. axios({
                method:"get",
                url:"/api/laboratory/getLabInfo",
                params:{
                    l_id:this.$route.query.l_id
                    // l_id:1
                }
            }).then(resp =>{
                console.log(resp.data)
                this.lab = {
                    l_id:Number(this.$route.query.l_id),
                    name:resp.data.name,
                    start_time:resp.data.start_time,
                    end_time:resp.data.end_time,
                    desc:resp.data.desc,
                    proportion:resp.data.proportion
                }
            })
        },
        showGuidebookList(){
            this. axios({
                method:"get",
                url:"/api/guidebook/getLabGuidebooks",
                params:{
                    // l_id:this.$route.query.l_id
                    l_id:1
                }
            }).then(resp =>{
                console.log(resp.data)
                for (let i in resp.data){
                    this.guidebookList.push({
                        g_id:resp.data[i].g_id,
                        name:resp.data[i].name,
                    })
                }
            })
        },
        createReport(){
            let report = {
                r_id:0,
                s_id:"1952168",
                l_id:this.lab.l_id,
                name:this.lab.name,
                submit_time :new Date(),
                status:0
            }
            this. axios({
                method:"post",
                url:"/api/report/postReport",
                data:report,
                headers:{
                    'Content-Type': 'application/json'
                }
            }).then(resp =>{
                console.log(resp.data)
            })
        }
    }
}
</script>

<style scoped>
.clear_box{
    clear:both;
}
.show_border{
    /*border: 2px red solid;*/
}
.fl{
    float: left;
}
.fr{
    float: right;
}
.lab_name{
    height: 70px;
    line-height: 70px;
    border-bottom: 1px gray solid;
    font-size: 36px;
}
.el-button{
    font-size: 16px;
}
.lab_intro{
    margin-top: 20px;
    font-size: 18px;
    white-space: pre-wrap;
}
.lab_info{
    height: 50px;
    line-height: 50px;
    font-size: 16px;
    margin-top: 10px;
    border-bottom: 1px solid gray;
}
.lab_ddl,
.lab_prop{
    font-weight: bolder;
}
.l_time{
}

.li_guidebook{
    height: 30px;
    font-size: 16px;
    line-height: 30px;
}
</style>
