import Vue from 'vue'
import VueRouter from 'vue-router'


import Course from "@/views/CoursePage";

//于然
// 导航栏  教师/学生/管理员
import TeacherLayout from "@/layout/TeacherLayout"
import StudentLayout from "@/layout/StudentLayout"
// import AdminLayout from "@/layout/AdminLayout"
import BulletinBoard from "@/components/BulletinBoard"

//课程的二级目录
import TeacherCourseLayout from "@/layout/TeacherCourseLayout"
import StudentCourseLayout from "@/layout/StudentCourseLayout"

//课程的页面
import MainPage from "@/components/Courses/MainPage"
import Message from "@/components/Courses/CourseMessage"
import LabDirectory from "@/components/Courses/LabDirectory"
import AllFeedback from "@/components/Courses/Teacher/AllFeedBack"
import Feedback from "@/components/Courses/Student/FeedBack"
import Faculty from "@/components/Courses/CourseFaculty"
import GradeManagement from "@/components/Courses/Teacher/GradeManagement"
import GradeCheck from "@/components/Courses/Student/GradeCheck"
import GradeTA from "@/components/Courses/Student/GradeTA"

//登陆界面
import LoginActivate from "@/views/LoginActivate"


//张宇
import AdminLayout from "@/layout/AdminLayout";
import CourseTeacherList from "@/components/Admin/CourseTeacherList";
import CourseStudentList from "@/components/Admin/CourseStudentList";

import StudentList from "@/components/Admin/StudentList";
import TeacherList from "@/components/Admin/TeacherList";
import UserList from "@/components/Admin/UserList";


import LaboratoryPage from "@/views/LaboratoryPage";
import TeacherLabPage from "@/views/TeacherLabPage"
import GuidebookPage from "@/views/GuidebookPage";
import ReportCreator from "@/components/Report/ReportCreator";
import ReportReader from "@/components/Report/ReportReader";
import LabReportList from "@/components/Report/LabReportList";
import ReportPage from "@/views/ReportPage";
import TEST from "@/views/TEST";





Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const routes = [
    {
        path:"/teacher",
        name:"teacher",
        component:TeacherLayout,
        children:[
            {
                path:'coursePage',
                name:'coursePage',
                component: TeacherCourseLayout,
                children:[
                    {path:'mainpage',component: MainPage},
                    {path:'message',component: Message},
                    {path:'Labs',component: LabDirectory},
                    {path:'feedback',component: AllFeedback},
                    {path:'faculty',component: Faculty},
                    {path:'grade',component: GradeManagement},
                    {path:'lab', component: TeacherLabPage },
                    {path:"guidebook", component: GuidebookPage},
                    {path:"report", component: ReportPage},
                    {path:"createReport", component: ReportCreator},
                    {path:"reportlist", component:LabReportList},
                    {path:"reportreader", component:ReportReader}
                ]
            },
            {
                path:'board',
                name:'board',
                component: BulletinBoard
            }
        ]
    },
    {
        path:"/student",
        name:"student",
        component:StudentLayout,
        children:[
            {
                path:'coursePage',
                name:'coursePage',
                component: StudentCourseLayout,
                children:[
                    {path:'mainpage',component:  MainPage},
                    {path:'message',component: Message},
                    {path:'Labs',component: LabDirectory},
                    {path:'feedback',component: Feedback},
                    {path:'faculty',component: Faculty},
                    {path:'grade',component: GradeCheck},
                    {path:'gradeTA',component: GradeTA},
                    {path:'lab', component: LaboratoryPage},
                    {path:"guidebook", component: GuidebookPage},
                    {path:"report", component: ReportPage},
                ]
            },
            {
                path:'board',
                name:'board',
                component: BulletinBoard
            }
        ]
    },
    {
        path:"/course",
        name:"course",
        component: Course,
    },
    {
        path: "/login",
        name: "login",
        component: LoginActivate
    },
    //张宇   管理员
    {
        path:"/admin",
        name:"AdminLayout",
        component:AdminLayout,
        children:[
            {
                path:'teacherCourses',
                name:"CourseTeacherList",
                component:CourseTeacherList,
                children:[
                    {
                        path:'/admin/teacherCourses/teachers',
                        component:TeacherList
                    },
                ]
            },
            {
                path:'studentCourses',
                name:"CourseStudentList",
                component:CourseStudentList,
                children:[
                    {
                        path:'/admin/studentCourses/students',
                        component:StudentList
                    },
                ]
            },
            {
                path:'user',
                component:UserList
            },
            {
                path:'board',
                name:'board',
                component: BulletinBoard
            }
        ]
    },
    {
        path:"/",
        component:TEST
    },



]


const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
