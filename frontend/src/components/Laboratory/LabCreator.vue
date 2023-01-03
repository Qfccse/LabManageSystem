<template>
    <div>
        <el-form style="width: 720px">
            <el-row :gutter="gutter">
                <el-col :span="colspan.left">实验名称：</el-col>
                <el-col :span="colspan.right"><el-input v-model="lab.name"></el-input></el-col>
            </el-row>
            <el-row :gutter="gutter">
                <el-col :span="colspan.left">开始时间：</el-col>
                <el-col :span="colspan.left + 2">
                    <el-date-picker
                        style="width: 100%"
                        v-model="lab.start_time"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        type="datetime"
                        range-separator="至"
                        start-placeholder="开始日期"
                    ></el-date-picker>
                </el-col>
                <el-col :span="colspan.left">结束时间：</el-col>
                <el-col :span="colspan.left + 2">
                    <el-date-picker
                        style="width: 100%"
                        v-model="lab.end_time"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        type="datetime"
                        range-separator="至"
                        start-placeholder="开始日期"
                    ></el-date-picker>
                </el-col>
            </el-row>
            <el-row :gutter="gutter">
                <el-col :span="colspan.left">实验描述：</el-col>
                <el-col :span="colspan.right">
                    <el-input
                        type="textarea"
                        :autosize="{minRows:2}"
                        v-model="lab.desc"
                    >
                    </el-input>
                </el-col>
            </el-row>
            <el-row :gutter="gutter">
                <el-col :span="colspan.left">分数权重：</el-col>
                <el-col :span="colspan.right"><el-input v-model="lab.proportion"></el-input></el-col>
            </el-row>
            <el-row :gutter="gutter"><el-button @click="click2CreateLabTodo">创建</el-button></el-row>
        </el-form>
    </div>
</template>

<script>
export default {
    name: "LabCreator",
    cid:{
        type: Number,
        default: 1
    },
    data(){
        return{
            colspan:{
                left:5,
                right:19
            },
            gutter:20,
            lab:{
                l_id:0,
                c_id:0,
                name:"test",
                start_time:"",
                end_time:"",
                desc:"123",
                proportion:10
            }
        }
    },
    methods:{
        createLab(){
            this.lab.start_time = new Date(this.lab.start_time)
            this.lab.end_time = new Date(this.lab.end_time)
            console.log(this.lab)
            this. axios({
                method:"post",
                url:"/api/laboratory/postNewLab",
                data:this.lab,
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(resp =>{
                console.log(resp.data)
                this.$message.success("实验添加成功!")
            })
        },
        createTodo(){
            console.log('create todo')
            let todo = {
                todo_id:0,
                c_id: this.lab.c_id,
                t_id:this.$store.state.userInfo.id,
                name:this.lab.name,
                desc:"请在截止期日前完成实验项目",
                start_time: new Date(this.lab.start_time),
                end_time: new Date(this.lab.end_time),
                type:2,
            }
            this. axios({
                method:"post",
                url:"/api/bulletin/addBulletin",
                data:todo,
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(resp =>{
                console.log(resp.data)
            })
        },
        click2CreateLabTodo(){
            this.createLab()
            this.createTodo()
        }
    }
}
</script>

<style scoped>
.el-row{
    height: 60px;
    line-height: 60px;
    font-size: 22px;
}
</style>
