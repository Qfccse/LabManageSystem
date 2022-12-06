<template>
    <div>
        <el-container style="background-color: white">
            <el-aside style="width: 200px;min-height: 800px;border: 1px red solid;">
                <div style="height: 600px;overflow-y: auto">
                    <el-menu
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose"
                        text-color="#b2aeaf"
                        active-text-color="#349f50"
                        default-active="0">
                        <el-menu-item  v-for="(course,index) in coursesList" :key="index" :index="''+ index" @click="gotoTeacherList(index)">
                            <i class="el-icon-document"></i>
                            <template v-slot:title>{{ course.name }}</template>
                        </el-menu-item>
                    </el-menu>
                </div>
                <div style="width: 200px;margin-top: 50px">
                    <div style="text-align: center;margin-bottom: 20px">
                        <div style="margin-bottom: 10px">
                            上传教学名单
                        </div>
                        <FileUploader
                            list-type="text"
                            :limit=1 file-type=".xlsx,.xls,.csv"
                            url="/api/admin/postTakesTeaches"
                            :id="1"
                            :show="false">
                        </FileUploader>
                    </div>

                </div>
            </el-aside>
            <el-container style="margin-left: 20px">
                <router-view></router-view>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import FileUploader from "@/components/Utils/FileUploader";
export default {
    name: "CourseTeacherList",
    components: {FileUploader},
    data(){
        return{
            coursesList:[
                {
                    c_id:8,
                    name:"计算机组成原理实验",
                    desc:"这个是计算机组成原理",
                    status:1
                },
                {
                    c_id:2,
                    name:"计算机组成原理",
                    desc:"这个是计算机组成原理",
                    status:1
                },
                {
                    c_id:3,
                    name:"离散数学",
                    desc:"这个是计算机组成原理",
                    status:1
                },
            ],
        }
    },
    mounted() {
        // setTimeout(this.gotoTeacherList,this.coursesList[0].name)
        // this.gotoTeacherList(this.coursesList[0].name)
        setTimeout(this.delayClick,200)
    },
    // watch:{
    //     $route(){
    //         this.gotoTeacherList(0)
    //     }
    // },
    methods:{
        delayClick(){
            this.gotoTeacherList(0)
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        gotoTeacherList(index) {
            console.log('teach list click' + index)
            this.$router.push({
                path: "/admin/teacherCourses/teachers",
                query: {
                    name: this.coursesList[index].name,
                },
            });
        },
    }
}
</script>

<style scoped>
::-webkit-scrollbar
{
    width: 3px;
    background-color: #F5F5F5;
}

::-webkit-scrollbar-thumb
{
    background-color: #999;
}

</style>
