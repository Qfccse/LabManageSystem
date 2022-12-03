<template>
    <div>
        <el-container>
            <el-aside width="200px">
                <!-- 个人信息 -->
                <div class="personalInfo" height="2000px">
                    <div style="margin-left: 65px">
                        <img
                            src="../../src/assets/avatars/admin.jpg"
                            style="
                height: 60px;
                width: 60px;
                margin-top: 20px;
                border-radius: 50%;
              "
                        />
                        <h5
                            style="
                color: #b2aeaf;
                width: 60px;
                text-align: center;
                margin-top: 5px;
              "
                        >
                            {{ Name }}
                        </h5>
                        <p style="color: #b2aeaf">{{ Id }}</p>
                    </div>
                </div>

                <el-menu
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#393836"
                    text-color="#b2aeaf"
                    active-text-color="#349f50"
                >
                    <!-- 侧边栏导航区 -->
                    <el-menu-item index="1" @click="gotoTeacherList">
                        <i class="el-icon-document"></i>
                        <template v-slot:title>教师管理</template>
                    </el-menu-item>

                    <el-menu-item index="2" @click="gotoStudentList">
                        <i class="el-icon-document"></i>
                        <template v-slot:title>学生管理</template>
                    </el-menu-item>

                    <el-menu-item index="3"   @click="gotoUserList">
                        <i class="el-icon-document"></i>
                        <template v-slot:title>用户管理</template>
                    </el-menu-item>

                    <el-menu-item index="4">
                        <i class="el-icon-document"></i>
                        <template v-slot:title>公告板</template>
                    </el-menu-item>

                    <el-menu-item index="5">
                        <i class="el-icon-setting"></i>
                        <template v-slot:title>我的信息</template>
                    </el-menu-item>
                </el-menu>
            </el-aside>

            <el-container>
                <el-header>
                    <el-menu
                        :default-active="activeIndex2"
                        class="el-menu-demo"
                        mode="horizontal"
                        @select="handleSelect"
                        background-color="#393836"
                        text-color="#b2aeaf"
                        active-text-color="#349f50"
                    >
                        <el-menu-item index="1"
                        ><b>同济大学实验教学课程管理系统</b></el-menu-item
                        >

                        <el-submenu index="2" style="margin-left: 500px">
                            <template v-slot:title>我的工作台</template>
                            <el-menu-item index="2-1">选项1</el-menu-item>
                            <el-menu-item index="2-2">选项2</el-menu-item>
                            <el-menu-item index="2-3">选项3</el-menu-item>
                            <el-submenu index="2-4">
                                <template v-slot:title>选项4</template>
                                <el-menu-item index="2-4-1">选项1</el-menu-item>
                                <el-menu-item index="2-4-2">选项2</el-menu-item>
                                <el-menu-item index="2-4-3">选项3</el-menu-item>
                            </el-submenu>
                        </el-submenu>
                        <el-menu-item index="3" disabled>消息中心</el-menu-item>
                        <el-menu-item index="4">
                            <i class="el-icon-unlock"></i>退出登录</el-menu-item
                        >
                    </el-menu>
                </el-header>
                <el-main style="min-height: 800px">
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
export default {
    name: "AdminLayout",
    data() {
        return {
            activeIndex: "1",
            activeIndex2: "1",
            Name: "于然",
            Id: "2053677",
        };
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        gotoTeacherList(name) {
            console.log(name)
            this.$router.push({
                path: "/admin/teacherCourses",
            });
        },
        gotoStudentList(c_id) {
            console.log(c_id)
            this.$router.push({
                path: "/admin/studentCourses",
            });
        },
        gotoUserList() {
            this.$router.push({
                path: "/admin/user",
            });
        },
    },
};
</script>


<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}

.el-header {
    background-color: #393836;
}

.el-aside {
    background-color: #393836;
}

.el-main {
    background-color: #e9eef3;
}

.personalInfo {
    height: 160px;
    background-color: #292929;
}
</style>
