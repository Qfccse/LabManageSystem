<template>
    <div style="margin-top: 20px;">
        
        <h3>实验反馈</h3>
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
    // props:{
    //     lid:{
    //         type: Number,
    //         default: 1
    //     },
    //     sid:{
    //         type: String,
    //         default: "*****"
    //     },
    // },
    data(){
        return{
            lid: this.$route.query.l_id,
            sid: this.$store.state.userInfo.id,
            feedback:"",
        }
    },
    methods:{
        sendFeedback(){
            // console.log(this.feedback)
            // var data= {
            //         l_id:this.lid,
            //         s_id:this.sid,
            //         feedback:this.feedback
            //     }

            // console.log(data);

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
                this.$message.success("反馈提交成功!")
            })
        }
    }
}
</script>

<style scoped>

</style>
