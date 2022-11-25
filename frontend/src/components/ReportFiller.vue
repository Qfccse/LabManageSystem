<template>
    <div style="width: 800px;margin:  auto">
        <h1>XXX实验报告</h1>
        <div v-for="(item,index) in this.formList" :key="index">
            <h3>{{item.title}}</h3>
            <template v-if="item.type === 'text'">
                <el-input type="textarea"
                          :autosize="{minRows:3}"
                          v-model="item.content"
                          :placeholder="item.placeholder?item.placeholder:'请输入'"></el-input>
            </template>
            <template v-else>
                <FileUploader ref="saveImage" :show="false" list-type="picture-card" url="/api/test/postReportImages" :id="idList[index]"></FileUploader>
            </template>
        </div>
        <div style="margin-top: 20px;">
            <el-button type="primary" @click="saveReport(1)">保存</el-button>
            <el-button type="success" @click="saveReport(2)">提交</el-button>
        </div>
    </div>
</template>

<script>
import FileUploader from "@/components/FileUploader";
export default {
    name: "ReportFiller",
    components: {FileUploader},
    data(){
        return{
            formList:[],
            idList:[],
        }
    },
    mounted() {
      this.receiveFormList()
    },
    methods:{
        receiveFormList(){
            this. axios({
                method:"get",
                url:"/api/test/getReportTemplate",
                params:{
                    //id 可以是lab的id
                    l_id:1,
                }
            }).then(resp =>{
                // console.log(resp.data)
                for(let i in resp.data){
                    this.formList.push(
                        {
                            rt_id:resp.data[i].rt_id,
                            icon:resp.data[i].icon,
                            label:resp.data[i].label,
                            title:resp.data[i].title,
                            placeholder:resp.data[i].placeholder,
                            required:resp.data[i].required,
                            type:resp.data[i].type,
                            typeName:resp.data[i].typeName,
                            content:resp.data[i].content
                        }
                    )
                }
            })
        },
        saveReport(type){
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
                        l_id:1,
                        s_id:2,
                        icon:this.formList[i].icon,
                        label:this.formList[i].label,
                        title:this.formList[i].title,
                        placeholder:this.formList[i].placeholder,
                        required:this.formList[i].required,
                        type:this.formList[i].type,
                        typeName:this.formList[i].typeName,
                        content:this.formList[i].content
                    }
                )
            }
            console.log(JSON.stringify(dataList))
            this. axios({
                method:"post",
                url:"/api/test/postReportForm",
                data: dataList ,
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(resp =>{
                //设置表格数据
                for (let i in resp.data){
                    this.idList.push(resp.data[i])
                }
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
