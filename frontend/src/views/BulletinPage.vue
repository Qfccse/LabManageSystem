<template>
    <div style="width: 800px">
        <el-row v-for="(todo,index) in todoList" :key="index">
            <el-col :span="16">
                <div style="font-weight: bolder">{{todo.name}}</div>
                <div>{{todo.desc}}</div>
            </el-col>
            <el-col :span="8">
                <div style="font-weight: bolder">发布于：</div>
                <div>{{todo.create_time}}</div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    name: "BulletinPage",
    data(){
        return{
            todoList:[]
        }
    },
    mounted() {
        this.getTodo()
    },
    methods:{
        getTodo(){
            this. axios({
                method:"get",
                url:"/api/todo/getCourseTodo",
                params:{
                    c_id:1
                }
            }).then(resp =>{
                //设置表格数据
                console.log(resp.data)
                for (let i in resp.data){
                    this.todoList.push({
                        name:resp.data[i].name,
                        desc:resp.data[i].desc,
                        create_time:resp.data[i].create_time,
                        start_time:resp.data[i].start_time,
                        end_time:resp.data[i].end_time,
                    })
                }
            })
        }
    }
}
</script>

<style scoped>
.el-row{
    height: 80px;
    line-height: 40px;
    border-bottom: 1px silver solid;
    border-top: 1px silver solid;
}
</style>
