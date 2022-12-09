<template>
    <div style="width: 1000px;">
        <div v-if="stuList.length===0">
            暂无选课学生
        </div>
       <div v-else>
           <el-row style="font-weight: bolder;font-size: 20px;">
               <el-col :span="8">学工号</el-col>
               <el-col :span="8">姓名</el-col>
               <el-col :span="8">角色</el-col>
           </el-row>
           <el-row v-for="(stu,index) in stuList" :key="index">
               <el-col :span="8">{{stu.s_id}}</el-col>
               <el-col :span="8">{{stu.name}}</el-col>
               <el-col :span="8">
                   <el-select v-model="stuRole[index]" @change="(val)=>click2Change(val,index)">
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
    name: "StudentList",
    data(){
      return{
          stuList:[],
          stuRole:[],
          roleOptions: [
              {
                  index: 3,
                  label: '助教',

              },
              {
                  index: 4,
                  label: '学生',

              }
          ],
          roleList:['助教','学生'],
      }
    },
    mounted() {
        this.getStuList()
    },
    watch:{
        $route(){
            this.getStuList()
        }
    },
    methods:{
        getStuList(){
            console.log('tu list get' + this.$route.query.c_id)
            this. axios({
                method:"get",
                url:"/api/admin/getCourseStudent",
                params:{
                    c_id:this.$route.query.c_id
                }
            }).then(resp =>{
                console.log(resp.data)
                this.stuList = []
                for (let i in resp.data){
                    this.stuList.push({
                        s_id:resp.data[i].s_id,
                        name:resp.data[i].name,
                        role:resp.data[i].role,
                    })
                    this.stuRole.push(this.roleList[resp.data[i].role-3])
                }
            })
        },
        click2Change(value,index){
            this.roleOptions.forEach(item => {
                if(item.index===value){
                    console.log(item)
                    console.log(index)
                    this.stuRole[index] = this.roleList[item.index-3]
                    this.stuList[index].role = item.index
                    this. axios({
                        method:"post",
                        url:"/api/admin/postUpdateTakesRole",
                        params:{
                            s_id:this.stuList[index].s_id,
                            c_id:this.$route.query.c_id,
                            role:item.index
                        }
                    }).then(resp =>{
                        console.log(resp.data)
                    })
                }
            })
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
    width: 50%;
}
</style>
