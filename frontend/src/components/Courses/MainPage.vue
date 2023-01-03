<template>
    <div style="margin-left: 5%;"> 
        <div class="canvas" style="margin: auto;width: 1050px">
            <div class="course_title clearbox" > {{ this.course.name.split('').join(' ') }} </div>
            <div class="course_detail">
                <div class="fl show_border" style="width: 800px;margin-left: 20px">
                    <div class="course_intro">
                        <div class="sec_title fl">
                            <div class="_title">
                                课 程 简 介
                            </div>
                            <div class="_title_e">
                                Course Introduction
                            </div>
                        </div>
                        <div class="line fr"></div>
                        <div class="course_body clearbox">
                            
                            
                            <el-input
                              type="textarea"
                              :autosize="{ minRows: 2}"
                              placeholder="请输入课程简介"
                              v-model="course.desc"
                              :readonly="edit"
                              style="font-size:20px;;"
                            >
                            </el-input>

                            <div style="margin-top: 50px; margin-left: 700px"
                             v-if="(this.$store.state.userInfo.role == 'teacher')">
                            <el-button        
                                style="margin-left: 10px"            
                               type="primary"                        
                               icon="el-icon-edit"
                               v-show="edit"
                               @click="startEdit"
                             >编辑</el-button>

                             <el-button                               
                               type="success"
                               icon="el-icon-edit" 
                               v-show="!edit"      
                               @click="saveEdit"
                             >保存</el-button>
                            
                            </div>
                        </div>
                    </div>
                    
                   
                    
                </div>
                
            </div>
        </div>
    </div>

</template>

<script>
export default {
    name: "CoursePage",
    data(){
        return{
            edit: true,
            c_id: this.$route.query.c_id,
            course: null,
        }
    },
    methods: {
        saveEdit() {
            this.edit=true;
            this.modifyCourse()
        },
        startEdit() {
            this.edit=false;
        },
        getCourse() {
            this.axios
          .get(
            "/api/course/getCourseById/" + this.c_id
            ).then((response) => {
                console.log("this is mainpage mounted");
            console.log(response.data);
            this.course=response.data.course
          })
          .catch(function (response) {
            console.log(response); //发生错误时执行的代码
            // ElMessage({
            //     message: "服务器连接失败",
            //     type: "error",
            //     offset: "8",
            // });
            this.$message.error("服务器连接失败!")
          });
        },
        modifyCourse() {
            this.axios
            .put(
            "/api/course", {desc: this.course.desc, c_id: this.course.c_id, name: this.course.name}
            ).then((response) => {
                console.log("this is modification");
                console.log(response.data);
            })
            .catch(function (response) {
            console.log(response); //发生错误时执行的代码
            // ElMessage({
            //     message: "服务器连接失败",
            //     type: "error",
            //     offset: "8",
            // });
             });
        }

    },
    watch: {
      $route(to, from) {
        if (to.query.c_id != from.query.c_id) {
            this.c_id = to.query.c_id
            this.getCourse()
        }
      }
    },
    mounted() {
        this.getCourse();
    }
}
</script>

<style scoped>

ul, ol{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    list-style: none;
}
.clearbox{
    clear:both;
}
.show_border{
    /*border: 2px red solid;*/
}
.fl{
    float: left;
}
.fr{
    float: right;
}
.line{
    border-bottom: #B3C0D1 2px solid;
    width: 650px;
    height: 45px;
}
.line2{
    border-bottom: #B3C0D1 2px solid;
    width: 100px;
    height: 45px;
    text-align: right;
    font-size: 14px;
    line-height: 60px;
    color: lightskyblue;
    font-weight: bolder;
}
.canvas{
    /* background-color: #e9eef3; */
    overflow: hidden;
}
.course_title{
    margin-left: 10px;
    font-size: 36px;
    font-weight: bolder;
    height: 100px;
    line-height: 100px;
}
.course_body{

}
.course_intro{
    margin-bottom: 50px;
}
.sec_title{
    margin-bottom: 20px;
}
._title{
    font-size: 28px;
    color: deepskyblue;
}
._title_e{
    font-size: 14px;
    color: limegreen;
}

.lab{
    height: 50px;
    margin-left: 10px;
}

.bulletin{
    margin-left: 20px;
}
.todo_title,
.feedback_title{
    font-size: 20px;
    height: 25px;
    line-height: 20px;
    margin-bottom: 10px;
    border-bottom: gray 2px solid;
}
.todo_body,
.feedback_body{
    margin-bottom: 10px;
    font-size: 14px;
    color: dimgray;
    border-radius: 10px;
}

.todo_item,
.feedback_item{
    line-height: 25px;
    height: 50px;
    margin-bottom: 10px;
}

</style>
