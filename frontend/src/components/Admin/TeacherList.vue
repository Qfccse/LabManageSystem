<template>
    <div>
        <div style="width: 1100px" v-if="teacherList.length===0">
            null
        </div>
        <div style="width: 1100px" v-else>
            <el-row style="font-weight: bolder;font-size: 20px;">
                <el-col :span="6">学工号</el-col>
                <el-col :span="6">姓名</el-col>
                <el-col :span="6">课号</el-col>
                <el-col :span="6">角色</el-col>
            </el-row>
            <el-row v-for="(teacher,index) in teacherList" :key="index">
                <el-col :span="6">{{teacher.t_id}}</el-col>
                <el-col :span="6">{{teacher.name}}</el-col>
                <el-col :span="6">{{teacher.c_id}}</el-col>
                <el-col :span="6">
                    <el-select v-model="teacherRole[index]" @change="(val)=>click2Change(val,index)">
                        <el-option
                            v-for="role in roleOptions"
                            :key="role.index"
                            :label="role.label"
                            :value="role.index" >
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
export default {
    name: "TeacherList",
    data(){
        return{
            teacherList:[],
            teacherRole:[],
            roleOptions: [
                {
                    index: 1,
                    label: '责任教师',

                },
                {
                    index: 2,
                    label: '教师',

                }
            ],
            roleList:['责任教师','教师'],
        }
    },
    mounted() {
        this.getTeacherList()
        console.log(this.$route.query.name)
    },
    watch:{
        $route(){
            this.getTeacherList()
        }
    },
    methods:{
        getTeacherList(){
            this. axios({
                method:"get",
                url:"/api/admin/getCourseTeacher",
                params:{
                   name:this.$route.query.name
                }
            }).then(resp =>{
                console.log(resp.data)
                this.teacherList = []
                for (let i in resp.data){
                    this.teacherList.push({
                        t_id:resp.data[i].t_id,
                        name:resp.data[i].name,
                        c_id:resp.data[i].c_id,
                        role:resp.data[i].role,
                    })
                    this.teacherRole.push(this.roleList[resp.data[i].role-1])
                }
            })
        },
        click2Change(value,index){
            console.log(value)
            this.roleOptions.forEach(item => {
                if(item.index===value){
                    this.teacherRole[index] = this.roleList[item.index-1]
                    this.teacherList[index].role = item.index
                    this. axios({
                        method:"post",
                        url:"/api/admin/postUpdateTeachesRole",
                        params:{
                            t_id:this.teacherList[index].t_id,
                            c_id:this.teacherList[index].c_id,
                            role:item.index
                        }
                    }).then(resp =>{
                        console.log(resp.data)
                    })
                }
            })
            // console.log(this.teacherList)
        }
    }

}
</script>

<style scoped>
.el-row{
    height: 50px;
    line-height: 50px;
}
.el-select{
    width: 75%;
}
</style>
