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
                url:"/api/report/getReportTemplate",
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
                        rt_id:this.formList[i].rt_id,
                        r_id:1,
                        content:this.formList[i].content
                    }
                )
            }
            console.log(JSON.stringify(dataList))
            let fd = new FormData()

            fd.append("l_id",1)
            fd.append("s_id","1952168")
            fd.append("status",type)
            for (let i in this.formList){
                fd.append("forms",JSON.stringify({
                    rt_id:this.formList[i].rt_id,
                    r_id:1,
                    content:this.formList[i].content
                }))
                dataList.push(
                    {
                        rt_id:this.formList[i].rt_id,
                        r_id:1,
                        content:this.formList[i].content
                    }
                )
            }
            // fd.append("forms",dataList)
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
