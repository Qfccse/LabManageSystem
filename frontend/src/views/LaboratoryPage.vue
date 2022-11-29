<template>
    <div style="width: 1000px;margin: auto">
        <el-row class="lab_name">
            <el-col :span="16">{{lab.name}}</el-col>
            <el-col :span="8">
                <router-link :to="{name:'ReportPage',query:{l_id:lab.l_id,s_id:'1952168',l_name:lab.name}}">
                    <el-button type="primary" class="fr" style="margin-top: 20px">完成实验报告</el-button>
                </router-link>
            </el-col>
        </el-row>
        <div style="width: 750px;" class="fl">
            <el-row class="lab_info clear_box">
                <el-col :span="3" class="lab_ddl">截止时间：</el-col>
                <el-col :span="8" class="l_time">{{lab.start_time}}</el-col>
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
                        <i class="el-icon-document"></i><a href="#">{{guidebook.name}}</a>
                    </router-link>
                </div>
            </div>
        </div>
        <div class="fl" style="width: 200px;margin-left: 50px;">
            <FeedbackCreator :lid="lab.l_id" sid="1952168"></FeedbackCreator>
        </div>
    </div>

</template>

<script>

import FeedbackCreator from "@/components/FeedbackCreator";
export default {
    name: "LaboratoryPage",
    components: {FeedbackCreator},
    props:{
        lid:{
            type: Number,
            default: 1
        },
    },
    data(){
        return{
            lab:{},
            guidebookList:[],
        }
    },
    mounted() {

        this.getLab()
        this.showGuidebookList()
    },
    methods:{
        getLab(){
            this. axios({
                method:"get",
                url:"/api/laboratory/getLabInfo",
                params:{
                    l_id:this.$route.query.l_id
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
                    l_id:this.$route.query.l_id
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
</style>
