<template>
    <div>
        <el-row :gutter="20" v-for="(lab,index) in labs" :key="index">
            <el-col :span="20">{{lab.name}}</el-col>
            <el-col :span="4" v-if="compareDate(lab.start_time,lab.end_time)===0">
                <i class="el-icon-circle-check"></i>未开始
            </el-col>
            <el-col :span="4" v-else-if="compareDate(lab.start_time,lab.end_time)===1">
                <i class="el-icon-error" style="color: red"></i>已结束
            </el-col>
            <el-col :span="4" v-else>
                <i class="el-icon-success" style="color: green"></i>进行中
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    name: "LabList",
    props:{
        cid:{
            type: Number,
            default: 1
        },
    },
    data(){
        return{
            labs:[]
        }
    },
    mounted() {
        this.getLabs();
    },
    methods:{
        getLabs(){
            this. axios({
                method:"get",
                url:"/api/laboratory/getCourseLabs",
                params:{
                    c_id:this.cid
                }
            }).then(resp =>{
                console.log(resp.data)
                for (let i in resp.data){
                    this.labs.push({
                        l_id:resp.data[i].l_id,
                        name:resp.data[i].name,
                        start_time:resp.data[i].start_time,
                        end_time:resp.data[i].end_time,
                        desc:resp.data[i].desc,
                        proportion:resp.data[i].proportion,
                    })
                }
            })
        },
        compareDate(st,ed){
            // 0 未开始
            // 1 已结束
            let std = new Date(st)
            let edd = new Date(ed)
            if(std > new Date() && edd > new Date()){
                return 0
            }
            else if(edd < new Date() && std < new Date()) {
                return 1
            }
        },
        trimDate(date){
            return date.getDate()
        }
    }
}
</script>

<style scoped>
.el-row{
    font-size: 20px;
    height: 50px;
    line-height: 50px;
    width: 600px;
}

</style>
