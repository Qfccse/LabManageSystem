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
                <el-upload
                    action="http"
                    list-type="picture-card"
                >
                    <i class="el-icon-plus"></i>
                </el-upload>

            </template>
        </div>
        <div style="margin-top: 20px;">
            <el-button type="primary" @click="saveReport(1)">保存</el-button>
            <el-button type="success" @click="saveReport(2)">提交</el-button>
        </div>
    </div>
</template>

<script>
export default {
    name: "ReportFiller",
    data(){
        return{
            formList:[]
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
                console.log(resp.data)
                for(let i in resp.data){
                    this.formList.push(
                        {
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
            console.log(type)
            let dataList = []
            for (let i in this.formList){
                dataList.push(
                    {
                        l_id:1,
                        s_id:1,
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
                console.log(resp.data)
                console.log(resp.data.id)
            })
        }
    }
}
</script>

<style scoped>

</style>
