<template>
    <div style="width: 800px;margin:  auto">
        <h1>XXX实验报告</h1>
        <div style="clear: both">
            <h3 style="float: left;width: 400px">姓名：{{stu.name}}</h3>
            <h3 style="float: left ;width: 380px">学号：{{stu.id}}</h3>
        </div>
        <div v-for="(item,index) in this.formList" :key="index" style="clear: both">
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
    </div>
</template>

<script>
export default {
    name: "ReportReader",
    data(){
        return{
            stu:{
                name:"zy",
                id:"123456"
            },
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
                url:"/api/test/getReportForm",
                params:{
                    //id 可以是lab的id
                    l_id:1,
                    s_id:1
                }
            }).then(resp =>{
                console.log(resp.data)
                for(let i in resp.data){
                    this.formList.push(
                        {
                            rf_id:resp.data[i].rf_id,
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
    }
}
</script>

<style scoped>

</style>
