import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from "@/components/HelloWorld";
import CoursePage from "@/views/CoursePage";
import LaboratoryPage from "@/views/LaboratoryPage";
import GuidebookPage from "@/views/GuidebookPage";
import ReportPage from "@/views/ReportPage";
import TEST from "@/views/TEST";
Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
const routes = [
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
        path:"/guidebook?g_id=:g_id?name=:name",
        name:"GuidebookPage",
        component:GuidebookPage,
    },
    {
        path:"/report?l_id:=l_id?s_id=s_id?l_name=l_name",
        name:"ReportPage",
        component:ReportPage,
    },
    {
        path:"/.",
        name:"Lab",
        component:LaboratoryPage
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
