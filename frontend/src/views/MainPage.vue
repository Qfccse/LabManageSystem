<template>
    <div  style="width: 100%">
        <el-container>
            <el-aside width="200px">
                <!-- 个人信息 -->
                <div class="personalInfo"  min-height="100%">
                    <div style="margin-left: 65px; ">
                        <img
                            src="../assets/logo.png"
                            style="height: 60px; width: 60px; margin-top: 20px;  border-radius:50%; "
                        />
                        <h5 style="color: #b2aeaf; width: 60px; text-align: center; margin-top: 5px;">{{studendName}}</h5>
                        <p style="color: #b2aeaf; ">{{studentId}}</p>
                    </div>
                </div>

                <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#393836"
                    text-color="#b2aeaf"
                    active-text-color="#349f50"
                >
                    <el-submenu index="1">
                        <template v-slot:title>
                            <i class="el-icon-location"></i>
                            <span>我的实验</span>
                        </template>
                        <!-- <el-menu-item-group>
                          <template v-slot:title>分组一</template>
                          <el-menu-item index="1-1">选项1</el-menu-item>
                          <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                          <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group> -->
                        <el-submenu index="1-1">
                            <template v-slot:title>计算机组成原理</template>
                            <el-menu-item index="1-1-1">门电路逻辑功能实验</el-menu-item>
                            <el-menu-item index="1-1-2">译码器实验</el-menu-item>
                            <el-menu-item index="1-1-3">数据选择器实验</el-menu-item>
                            <el-menu-item index="1-1-4">触发器实验</el-menu-item>
                        </el-submenu>
                        <el-submenu index="1-2">
                            <template v-slot:title>计算机网络实验</template>
                            <el-menu-item index="1-2-1">以太组网实验</el-menu-item>
                        </el-submenu>
                        <el-submenu index="1-3">
                            <template v-slot:title>物理实验</template>
                            <el-menu-item index="1-3-1">密里根油滴实验</el-menu-item>
                        </el-submenu>
                    </el-submenu>

                    <el-submenu index="2">
                        <template v-slot:title>
                            <i class="el-icon-location"></i>
                            <span>我的课程</span>
                        </template>
                        <el-menu-item index="2-1">计算机组成原理实验</el-menu-item>
                        <el-menu-item index="2-2">计算机网络实验</el-menu-item>
                        <el-menu-item index="2-3">物理实验</el-menu-item>
                    </el-submenu>

                    <el-menu-item index="3">
                        <i class="el-icon-document"></i>
                        <template v-slot:title>批阅实验</template>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-setting"></i>
                        <template v-slot:title>个人中心</template>
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

                        <el-menu-item index="1" ><b>同济大学实验教学课程管理系统</b></el-menu-item>

                        <el-submenu index="2" style="margin-left: 350px">
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
                            <i class="el-icon-unlock"></i>退出登录</el-menu-item>
                    </el-menu>
                </el-header>

                <el-main style="height: 1000px;">
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
export default {
    name:"MainPage",
    data() {
        return {
            activeIndex: "1",
            activeIndex2: "1",
            studendName: "于然",
            studentId: "2053677",
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

/* body > .el-container {

} */

.personalInfo {
    height: 160px;
    background-color: #292929;
}

</style>
