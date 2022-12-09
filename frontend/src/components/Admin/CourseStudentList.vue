<template>
    <div>
        <el-container style="background-color: white">
            <el-aside style="width: 200px; min-height: 800px; ">
                <div style="height: 400px; overflow-y: auto">
                    <el-menu
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose"
                        default-active="1-0"
                        active-text-color="#FFFFFF"
                    >
                        <el-menu-item
                            v-for="(course, index) in coursesList"
                            :key="index"
                            :index="'1-' + index"
                            @click="gotoStudentList(index)"
                        >
                            <i class="el-icon-document"></i>
                            <template v-slot:title>{{ course.name }}</template>
                        </el-menu-item>
                    </el-menu>
                </div>
                <div style="text-align: center; margin-bottom: 20px">
                    <div style="margin-bottom: 10px">上传选课名单</div>
                    <FileUploader
                        list-type="text"
                        :limit="1"
                        file-type=".xlsx,.xls,.csv"
                        url="/api/admin/postTakesTeaches"
                        :id="1"
                        :show="false"
                    >
                    </FileUploader>
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
    name: "CourseStudentList",
    components: { FileUploader },
    data() {
        return {
            coursesList: [],
        };
    },
    async mounted() {
        await this.getCourseInfo();
        this.delayClick()
    },
    // watch:{
    //     $route(){
    //         this.gotoStudentList(0)
    //     }
    // },
    methods: {
        delayClick() {
            this.gotoStudentList(0);
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
            console.log("stu list click" + index);
            this.$router.push({
                path: "/admin/studentCourses/students",
                query: {
                    c_id: this.coursesList[index].c_id,
                },
            });
        },
        async getCourseInfo() {
            //这里吧所有课程的名字放到coursesList中
            await this.axios
                .get("/api/course/getAllCourses/")
                .then((response) => {
                    console.log(response.data);
                    this.coursesList = response.data.coursesList;
                })
                .catch(function (response) {
                    console.log(response); //发生错误时执行的代码
                    // ElMessage({
                    //     message: "服务器连接失败",
                    //     type: "error",
                    //     offset: "8",
                    // });
                });
        },
    },
};
</script>

<style scoped>
::-webkit-scrollbar {
    width: 3px;
    background-color: #f5f5f5;
}

::-webkit-scrollbar-thumb {
    background-color: #999;
}

.el-menu-item.is-active {
    background-color: #1593f9;
}

.el-menu-item {
    border-radius: 10px;
}
</style>
