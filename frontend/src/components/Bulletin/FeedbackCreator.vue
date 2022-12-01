<template>
    <div>
        <h2>反馈课程学习情况</h2>
        <el-input
            type="textarea"
            :autosize="{minRows:3}"
            v-model="feedback">
        </el-input>
        <el-button type="primary"
                   style="margin-top: 20px"
                   @click="sendFeedback">保存</el-button>
    </div>
</template>

<script>
export default {
    name: "FeedbackCreator",
    props:{
        lid:{
            type: Number,
            default: 1
        },
        sid:{
            type: String,
            default: "*****"
        },
    },
    data(){
        return{
            feedback:"",
        }
    },
    methods:{
        sendFeedback(){
            console.log(this.feedback)
            this. axios({
                method:"post",
                url:"/api/laboratory/postFeedback",
                data:{
                    l_id:this.lid,
                    s_id:this.sid,
                    feedback:this.feedback
                },
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(resp =>{
                console.log(resp.data)
            })
        }
    }
}
</script>

<style scoped>

</style>
