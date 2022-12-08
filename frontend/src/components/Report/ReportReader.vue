<template>
    <div style="width: 800px;margin:  auto">
        <div style="font-size: 36px;font-weight: bolder;margin-bottom: 10px">{{this.$route.query.report}}</div>
        <div style="clear: both">
            <div class="row_title">姓名：{{stu.name}}</div>
            <div class="row_title">学号：{{stu.id}}</div>
        </div>
        <div v-for="(item,index) in this.formList" :key="index" style="clear: both">
            <div class="row_title">{{item.title}}</div>
            <div v-if="item.type === 'text'"  style="clear: both">
                <el-input type="textarea"
                          readonly
                          :autosize="{minRows:3}"
                          v-model="item.content"
                          :placeholder="item.placeholder?item.placeholder:'该项未填写'"></el-input>
            </div>
            <div v-else style="clear: both">
                <div v-for="(image,i) in item.imgList" :key="i">
                    <img :src="require('../../../../../LabAssets/' + image)" style="max-width: 700px">
                </div>
                <div v-if="item.imgList.length===0">
                    未上传图片
                </div>
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
    async mounted() {
        this.stu.name = this.$route.query.name
        this.stu.id = this.$route.query.s_id
        await this.receiveFormList()
        await this.getImages()
    },
    methods:{
       async receiveFormList(){
            await this. axios({
                method:"get",
                url:"/api/report/getReport",
                params:{
                    //id 可以是lab的id
                    l_id:this.$route.query.l_id,
                    s_id:this.$route.query.s_id
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
        async getImages(){
           for(let i in this.formList){
               if(this.formList[i].type==='image'){
                   await this.receiveFromImages(i)
               }
           }
        },
        async receiveFromImages(index){
           console.log(this.formList[index].rf_id)
           await this. axios({
                method:"get",
                url:"/api/report/getFormImages",
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
.row_title{
    float: left;
    width: 50%;
    font-size: 20px;
    font-weight: bolder;
    margin-top: 10px;
    margin-bottom: 10px;
}

</style>
