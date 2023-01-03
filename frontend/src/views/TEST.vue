<template>
    <div class="test">

<div v-if="false">
        <PDFReader
                url="/api/guidebook/getGuidebookBytes"
                :gid=1
            >
            </PDFReader>

            <LabList></LabList>

            <div style="margin-top: 50px">发布报告表单</div>
            <ReportCreator></ReportCreator>

            <div style="margin-top: 50px">填写报告</div>
            <ReportFiller></ReportFiller>
            <div style="margin-top: 50px">阅读报告</div>
            <ReportReader></ReportReader>

            <LabCreator></LabCreator>


</div>


<div style="margin-top: 50px">阅读报告</div>
            <ReportReader></ReportReader>


        <div v-if="false">
            <div style="margin-top: 50px" @click="test">普通接口测试</div>
            <div>
                获取的 id:{{course.id}}
                <br/>
                获取的 name:{{course.name}}
            </div>

            <div style="margin-top: 50px">上传图片</div>
            <FileUploader list-type="picture-card" url="/api/test/uploadFile"></FileUploader>

            <div style="margin-top: 50px">上传文件</div>
            <FileUploader file-type="*" url="/api/test/uploadFile"></FileUploader>

            <div style="margin-top: 50px">下载</div>
            <FileDownloader
                file-path="C:/Users/ASUS/Desktop/s/1669044608648.png"
            >
            </FileDownloader>

            <div style="margin-top: 50px">pdf阅读</div>
            <PDFReader
                file-path="C:/Users/ASUS/Desktop/s/1.pdf"
            >
            </PDFReader>


            <div style="margin-top: 50px">发布报告表单</div>
            <ReportCreator></ReportCreator>
            <div style="margin-top: 50px">填写报告</div>
            <ReportFiller></ReportFiller>
            <div style="margin-top: 50px">阅读报告</div>
            <ReportReader></ReportReader>
            <div style="margin-top: 50px">上传文档</div>
            <FileUploader ref="saveImage" file-type="*" url="/api/guidebook/postLabGuidebook" :id="{
             g_id:0,
             l_id:1,
             t_id:'1',
             type:1,
             name:'123',
             path:'123',}"></FileUploader>
            <div style="margin-top: 50px">阅读pdf</div>
           
            <GuidebookPage></GuidebookPage>
            <LabCreator></LabCreator>
            <LabList></LabList>
            <LaboratoryPage :lid="7"></LaboratoryPage>
            <PDFReader
                url="/api/guidebook/getGuidebookBytes"
                :gid=this.$route.query.g_id
            >
            </PDFReader>
            <LaboratoryPage></LaboratoryPage>
            <BulletinPage></BulletinPage>
            <UserList></UserList>
            <div style="margin-top: 50px"></div>
            <h1>计算机组成原理实验8课程名单</h1>
            <StudentList></StudentList>
            <div style="margin-top: 50px"></div>
            <h1>计算机组成原理实验授课教师</h1>
            <TeacherList></TeacherList>
            <CourseStudentList></CourseStudentList>
            <CourseTeacherList></CourseTeacherList>

            <AdminLayout></AdminLayout>
            <LoginActivate></LoginActivate>

        </div>
        <!--        <LaboratoryPage></LaboratoryPage>-->
        <!--        <LabList></LabList>-->
        <!--        <ReportCreator></ReportCreator>-->
<!--        <AdminLayout></AdminLayout>-->
        <!-- <LoginActivate></LoginActivate> -->
    </div>
</template>

<script>
import FileUploader from "@/components/Utils/FileUploader";
import FileDownloader from "@/components/Utils/FileDownloader";
import PDFReader from "@/components/Guidebook/PDFReader";
import ReportCreator from "@/components/Report/ReportCreator";
import ReportFiller from "@/components/Report/ReportFiller";
import ReportReader from "@/components/Report/ReportReader";
import GuidebookPage from "@/views/GuidebookPage";
import LabCreator from "@/components/Laboratory/LabCreator";
import LaboratoryPage from "@/views/LaboratoryPage";
import LabList from "@/components/Laboratory/LabList";


// import BulletinPage from "@/views/BulletinPage";
import UserList from "@/components/Admin/UserList";
import StudentList from "@/components/Admin/StudentList";
import TeacherList from "@/components/Admin/TeacherList";
import AdminLayout from "@/layout/AdminLayout";
import CourseStudentList from "@/components/Admin/CourseStudentList";
import CourseTeacherList from "@/components/Admin/CourseTeacherList";
import LoginActivate from "@/views/LoginActivate";
export default {
    name: 'TEST',
    components: {
        LoginActivate,
        CourseTeacherList,
        CourseStudentList,
        AdminLayout,
        TeacherList,
        StudentList,
        UserList,
        // BulletinPage,
        LabList,
        LaboratoryPage,
        LabCreator,
        GuidebookPage,
        ReportReader,
        ReportFiller,
        ReportCreator,
        PDFReader,
        FileDownloader,
        FileUploader
    },
    data(){
        return{
            course:"",
            id:3
        }
    },
    mounted() {
        // this.test()
        // console.log(this.$route.query.l_id)
    },
    methods:{
        test(){
            // restfull 风格
            // this. axios({
            //     method:"get",
            //     url:"/api/test/1",
            // }).then(resp =>{
            //     //设置表格数据
            //     console.log(resp.data)
            //     console.log(resp.data.id)
            // })

            // 半 restfull风格
            this. axios({
                method:"get",
                url:"/api/test/selectById",
                params:{
                    id:1
                }
            }).then(resp =>{
                //设置表格数据
                this.course = resp.data;
                console.log(resp.data)
                console.log(resp.data.id)
            })
        },
        testUp(){
            this.$refs.saveImage.fileUpload()
        }
    }
}
</script>

<style scoped>
.test{
    /*margin: auto;*/
    /*width: 80%;*/
}
</style>
