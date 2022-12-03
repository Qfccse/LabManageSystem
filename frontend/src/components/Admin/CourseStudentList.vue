<template>
    <div>
        <el-container style="background-color: white">
            <el-aside style="width: 200px;min-height: 800px;border: 1px red solid">
                <el-menu
                         class="el-menu-vertical-demo"
                         @open="handleOpen"
                         @close="handleClose"
                         text-color="#b2aeaf"
                         active-text-color="#349f50"
                         default-active="1-0">
                    <el-menu-item  v-for="(course,index) in coursesList" :key="index" :index="'1-'+ index" @click="gotoStudentList(index)">
                        <i class="el-icon-document"></i>
                        <template v-slot:title>{{ course.name }}</template>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container style="margin-left: 20px">
                <router-view></router-view>
            </el-container>
        </el-container>
    </div>
</template>

<script>
export default {
    name: "CourseStudentList",
    data(){
        return{
            coursesList:[
                {
                    c_id:8,
                    name:"计算机组成原理3",
                    desc:"这个是计算机组成原理",
                    status:1
                },
                {
                    c_id:9,
                    name:"计算机组成原理3",
                    desc:"这个是计算机组成原理",
                    status:1
                },
                {
                    c_id:11,
                    name:"计算机组成原理3",
                    desc:"这个是计算机组成原理3",
                    status:1
                },
            ],
        }
    },
    mounted() {
        setTimeout(this.delayClick,200)
    },
    watch:{
        $route(){
            this.gotoStudentList(0)
        }
    },
    methods:{
        delayClick(){
            this.gotoStudentList(0)
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
        gotoStudentList(index) {
            console.log('stu list click' + index)
            this.$router.push({
                path: "/admin/studentCourses/students",
                query: {
                    c_id: this.coursesList[index].c_id,
                },
            });
        },
    }
}
</script>

<style scoped>

</style>
