import Vue from 'vue'
import VueRouter from 'vue-router'

import AdminLayout from "@/layout/AdminLayout";
import CourseTeacherList from "@/components/Admin/CourseTeacherList";
import CourseStudentList from "@/components/Admin/CourseStudentList";

import StudentList from "@/components/Admin/StudentList";
import TeacherList from "@/components/Admin/TeacherList";
import UserList from "@/components/Admin/UserList";

import HelloWorld from "@/components/HelloWorld";
import CoursePage from "@/views/CoursePage";
import TeacherLabPage from "@/views/TeacherLabPage";
import LaboratoryPage from "@/views/LaboratoryPage";
import GuidebookPage from "@/views/GuidebookPage";
import ReportPage from "@/views/ReportPage";
import ReportCreator from "@/components/Report/ReportCreator";
import ReportReader from "@/components/Report/ReportReader";

import TEST from "@/views/TEST";
Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
const routes = [
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
        ]
    },

    {
        path:"/.",
        name:"hello",
        component:HelloWorld
    },
    {
        path:"/.",
        name:"CoursePage",
        component:CoursePage
    },
    {
        path:"/",
        name:"TEST",
        component:TEST,
    },
    {
        path:"/guidebook",
        name:"GuidebookPage",
        component:GuidebookPage,
    },
    {
        path:"/report",
        name:"ReportPage",
        component:ReportPage,
    },
    {
        path:"/lab",
        name:"Lab",
        component:LaboratoryPage,
    },
    {
        path:"/teacher_lab",
        name:"TeacherLab",
        component:TeacherLabPage,
    },
    {
        path:"/template",
        name:"ReportCreator",
        component:ReportCreator,
    },
    {
        path:"/stu_report",
        name:"ReportReader",
        component:ReportReader,
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
