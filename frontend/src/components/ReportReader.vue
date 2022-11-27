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
            <div v-else>
                <div v-for="(image,i) in item.imgList" :key="i">
                    <img :src="require('../assets/' + image)" style="max-width: 700px">
<!--                    <img :src="require('../../../../LabImages/' + image)" style="max-width: 700px">-->
<!--                    <img src="../../../../LabImages/1.png">-->
                </div>
                <el-button @click="receiveFromImages(index)" v-if="item.imgList.length===0">查看实验图片</el-button>
            </div>
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
            formList:[],
            show:false,
        }
    },
    mounted() {
        this.receiveFormList()
    },
    methods:{
        receiveFormList(){
            this. axios({
                method:"get",
                url:"/api/test/getReport",
                params:{
                    //id 可以是lab的id
                    l_id:1,
                    s_id:"1952168"
                }
            }).then(resp =>{
                console.log(resp.data)
                for(let i in resp.data){
                    this.formList.push(
                        {
                            rf_id:resp.data[i].rf_id,
                            title:resp.data[i].title,
                            required:resp.data[i].required,
                            type:resp.data[i].type,
                            content:resp.data[i].content,
                            imgList:[]
                        }
                    )
                }
            })
        },
        receiveFromImages(index){
            this. axios({
                method:"get",
                url:"/api/test/getFormImages",
                params:{
                    //id 可以是lab的id
                    rf_id:this.formList[index].rf_id
                }
            }).then(resp =>{
                console.log(resp.data)
                for(let i in resp.data){
                    this.formList[index].imgList.push(resp.data[i])
                }
                this.show = true;
            })
        }
    }
}
</script>

<style scoped>

</style>
