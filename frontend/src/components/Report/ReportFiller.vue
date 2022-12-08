<template>
    <div style="width: 800px;margin:  auto">
        <div v-for="(item,index) in this.formList" :key="index">
            <h3>{{item.title}}</h3>
            <template v-if="item.type === 'text'">
                <el-input type="textarea"
                          :autosize="{minRows:3}"
                          v-model="item.content"
                          :placeholder="item.placeholder?item.placeholder:'请输入'"></el-input>
            </template>
            <template v-else>
                <FileUploader ref="saveImage" :show="true" list-type="picture-card" url="/api/report/postReportImages" :id="idList[index]" ></FileUploader>
            </template>
        </div>
        <div style="margin-top: 20px;">
            <el-button type="primary" @click="saveReport(1)">保存</el-button>
            <el-button type="success" @click="saveReport(2)">提交</el-button>
        </div>
    </div>
</template>

<script>
import FileUploader from "@/components/Utils/FileUploader";
export default {
    name: "ReportFiller",
    components: {FileUploader},
    data(){
        return{
            formList:[],
            idList:[],
            endTime:'',
        }
    },
    mounted() {
        // this.receiveFormList()
        this.endTime = new Date()
        this.receiveReport();
    },
    methods:{
        receiveFormList(){
            this. axios({
                method:"get",
                url:"/api/report/getReportTemplate",
                params:{
                    //id 可以是lab的id
                    l_id:2,
                }
            }).then(resp =>{
                console.log(resp.data)
                for(let i in resp.data){
                    this.formList.push(
                        {
                            rt_id:resp.data[i].rt_id,
                            title:resp.data[i].title,
                            placeholder:resp.data[i].placeholder,
                            required:resp.data[i].required,
                            type:resp.data[i].type,
                            content:"",
                        }
                    )
                }
            })
        },
        async getReportDDL(){
            await this. axios({
                method:"get",
                url:"/api/laboratory/getLabInfo",
                params:{
                    // l_id:this.$route.query.l_id
                    l_id:2
                }
            }).then(resp =>{
                console.log(this.endTime)
                return new Date(resp.data.end_time)
            })
            return new Date()
        },
        checkRequired(type){
            let ii = 0
            for (let i in this.formList){
                if(this.formList[i].type === 'image'){
                    if(type===2&&(this.formList[i].required === "true" &&
                        this.$refs.saveImage[ii++].upload.fileList.length===0))
                    {
                        alert("上传" + this.formList[i].title)
                        return true
                    }
                }
                else {
                    if(this.formList[i].required === "true"&&  (
                        this.formList[i].content===null||
                        this.formList[i].content.length===0))
                    {
                        alert("请填写" + this.formList[i].title)
                        return true
                    }
                }
            }

            return false
        },
        receiveReport(){
            this. axios({
                method:"get",
                url:"/api/report/getReportFiller",
                params:{
                    //id 可以是lab的id
                    l_id:this.$route.query.l_id,
                    s_id:this.$route.query.s_id
                }
            }).then(resp =>{
                console.log("获取到的report form")
                console.log(resp.data)
                for(let i in resp.data){
                    this.formList.push(
                        {
                            rt_id:resp.data[i].rt_id,
                            rf_id:resp.data[i].rf_id,
                            title:resp.data[i].title,
                            placeholder:resp.data[i].placeholder,
                            required:resp.data[i].required,
                            type:resp.data[i].type,
                            content:resp.data[i].content,
                        }
                    )
                }
            })
        },
        saveReport(type){
            if(this.checkRequired(type)){
                return
            }

            if(this.getReportDDL() <= new Date())
            {
                alert( "实验已结束，无法提交")
                return
            }
            console.log(type)
            this.saveReportForm(type)
            if(type===2){
                setTimeout(this.saveReportImages,1000)
            }
        },
        saveReportForm(type){
            console.log(type)
            let dataList = []
            for (let i in this.formList){
                dataList.push(
                    {
                        rt_id:this.formList[i].rt_id,
                        r_id:this.$route.query.r_id,
                        content:this.formList[i].content
                    }
                )
            }
            console.log(JSON.stringify(dataList))
            let fd = new FormData()

            fd.append("l_id",1)
            fd.append("s_id","1952166")
            fd.append("status",type)
            for (let i in this.formList){
                fd.append("forms",JSON.stringify({
                    rt_id:this.formList[i].rt_id,
                    rf_id:this.formList[i].rf_id,
                    r_id:this.$route.query.r_id,
                    content:this.formList[i].content
                }))
            }
            // fd.append("forms",dataList)
            console.log(fd)
            this. axios({
                method:"post",
                url:"/api/report/postReportForm",
                data:fd,
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(resp =>{
                //设置表格数据
                for (let i in resp.data){
                    this.idList.push(resp.data[i])
                }
                console.log("idlist")
                console.log(this.idList)
                // console.log(resp.data.id)
            })
        },
        saveReportImages(){
            for(let i in this.$refs.saveImage){
                this.$refs.saveImage[i].fileUpload()
            }
        }
    }
}
</script>

<style scoped>

</style>
