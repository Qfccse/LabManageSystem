import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from "@/components/HelloWorld";
import CoursePage from "@/views/CoursePage";
import TEST from "@/components/TEST";
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
        component:TEST
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
