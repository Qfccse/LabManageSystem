<template>
    <div>
        <el-row :gutter="20" v-for="(lab,index) in labs" :key="index">
            <el-col :span="20">
                <router-link :to="{name:'Lab',query:{l_id:lab.l_id}}">
                    {{lab.name}}
                </router-link>
            </el-col>
            <div>
<!--                <el-col :span="4" v-if="labStatus[index]===0">-->
<!--                    <i class="el-icon-circle-check"></i>未开始-->
<!--                </el-col>-->
<!--                <el-col :span="4" v-else-if="labStatus[index]===1">-->
<!--                    <i class="el-icon-error"></i>已结束-->
<!--                </el-col>-->
<!--                <el-col :span="4" v-else>-->
<!--                    <i class="el-icon-success"></i>进行中-->
<!--                </el-col>-->
                <el-col :span="4">
                     <span v-if="labStatus[index]===0">
                        <i class="el-icon-circle-check" @click="updateLabTime(index)"></i>
                    </span>
                    <span v-else-if="labStatus[index]===1">
                        <i class="el-icon-error" @click="updateLabTime(index)"></i>
                    </span>
                    <span v-else>
                        <i class="el-icon-success" @click="updateLabTime(index)"></i>
                    </span>
                </el-col>
            </div>
        </el-row>
<!--        <i :class="temp"></i>-->
        <span v-show="false">{{temp}}</span>
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
            labs:[],
            labStatus:[],
            temp:"this is a trigger to activate icon class change",
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
                        c_id:resp.data[i].c_id,
                        name:resp.data[i].name,
                        start_time:resp.data[i].start_time,
                        end_time:resp.data[i].end_time,
                        desc:resp.data[i].desc,
                        proportion:resp.data[i].proportion,
                    })
                    this.labStatus.push(this.compareDate(resp.data[i].start_time,resp.data[i].end_time))
                }
                console.log(this.labStatus)
            })
        },
        compareDate(st,ed){
            // 0 未开始
            // 1 已结束
            // 2 正在进行
            let std = new Date(st)
            let edd = new Date(ed)
            if(std > new Date() && edd > new Date()){
                return 0
            }
            else if(edd < new Date() && std < new Date()) {
                return 1
            }
            else {
                return 2
            }
        },
        changeClass(index){
            let type = this.labStatus[index]
            if(type===0){
                this.labStatus[index] = 2
                this.temp = "el-icon-error"
            }else if(type===2){
                this.labStatus[index] = 1
                this.temp = "el-icon-success"
            }
            {
                // this.labStatus[index] = 0
                this.temp = "el-icon-circle-check"
            }
            console.log(this.labStatus)
        },
        updateLabTime(index){
            let st = new Date(this.labs[index].start_time)
            let et = new Date(this.labs[index].end_time)
            if(this.labStatus[index]===0){
                st = new Date()
            }else if(this.labStatus[index]===2){
                et =new Date()
            }else {
                return
            }
            this.changeClass(index)
            this.axios({
                method:"post",
                url:"/api/laboratory/postLabInfo",
                data: {
                    l_id:this.labs[index].l_id,
                    c_id:this.labs[index].c_id,
                    name:this.labs[index].name,
                    start_time:st,
                    end_time:et,
                    desc:this.labs[index].desc,
                    proportion:this.labs[index].proportion,
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
.el-row{
    font-size: 20px;
    height: 50px;
    line-height: 50px;
    width: 600px;
}
.el-icon-error{
    color: red;
}
.el-icon-error:hover:after {
    position: absolute;
    /*top: -10px;*/
    /*right: 5px;*/
    /*top: 35px;*/
    padding: 2px;
    color: black;
    content: '实验已结束';
    z-index: 2;
    width: 120px;
    height: 15px;
    line-height: 15px;
}


.el-icon-success{
    color: green;
}
.el-icon-success:hover:after {
    position: absolute;
    padding: 2px;
    color: black;
    content: '点击结束实验';
    z-index: 2;
    width: 120px;
    height: 15px;
    line-height: 15px;
}


.el-icon-circle-check{
    color: gray;
}
.el-icon-circle-check:hover:after {
    position: absolute;
    padding: 2px;
    color: black;
    content: '点击开始实验';
    z-index: 2;
    width: 120px;
    height: 15px;
    line-height: 15px;
}

</style>
