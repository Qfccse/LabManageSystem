<template>
  <el-container style="height: 800px">
    <el-aside width="150px">

    <el-col :span="24" >
    <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      active-text-color="#FFFFFF"
      >



      <el-menu-item index="1" @click="gotoMainPage">
        <template  v-slot:title>
          <i class="el-icon-location"></i>
          <span>课程主页</span>
        </template>
      </el-menu-item>




      <el-menu-item index="2" @click="gotoMessage">
        <template  v-slot:title>
          <i class="el-icon-postcard"></i>
          <span>课程公告</span>
        </template>
      </el-menu-item>

      <el-menu-item index="3"  @click="gotoLabDirectory" >
        <template  v-slot:title>
          <i class="el-icon-s-operation" ></i>
          <span>实验目录</span>
        </template>
      </el-menu-item>

      <el-menu-item index="7"
          @click="gotoFileList">
          <template  v-slot:title>
            <i class="el-icon-files"></i>
            <span>课程文件</span>
          </template>
        </el-menu-item>


      <el-menu-item index="4" @click="gotoFeedback">
        <template  v-slot:title>
          <i class="el-icon-notebook-1"></i>
          <span>学生反馈</span>
        </template>
      </el-menu-item>

      <el-menu-item index="5" @click="gotoFaculty">
        <template  v-slot:title>
          <i class="el-icon-s-custom"></i>
          <span>课程人员</span>
        </template>
      </el-menu-item>

      <el-menu-item index="6" @click="gotoGrade">
        <template  v-slot:title>
          <i class="el-icon-s-data"></i>
          <span>成绩管理</span>
        </template>
      </el-menu-item>



    </el-menu>
  </el-col>

    </el-aside>
  <el-main style="height: 1100px">
    <router-view></router-view>
</el-main>
</el-container>
</template>

<script>
export default {
    name: "TeacherCourseLayout",
    data() {
        return{

        }
    },
    computed: {
      role() {
        if (this.$store.state.roleInCourse == 'HT') {
          return "HT"
        } else {
          return undefined
        }
      },
    },
    methods: {
      gotoMainPage() {
        this.$router.push({
          path: '/teacher/coursePage/mainpage',
          query: {
            c_id: this.$store.state.c_id,
            role: this.role
          }
        });
      },
      gotoMessage() {
        this.$router.push({
          path: '/teacher/coursePage/message',
          query: {
            c_id: this.$store.state.c_id,
            role: this.role
          }
        });
      },
      gotoLabDirectory() {
        this.$router.push({
          path: '/teacher/coursePage/Labs',
          query: {
            c_id: this.$store.state.c_id,
            role: this.role
          }
        });
      },
      gotoFeedback() {
        this.$router.push({
          path: '/teacher/coursePage/feedback',
          query: {
            c_id: this.$store.state.c_id,
            role: this.role
          }
        });
      },
      gotoFaculty() {
        this.$router.push({
          path: '/teacher/coursePage/faculty',
          query: {
            c_id: this.$store.state.c_id,
            role: this.role
          }
        });
      },
      gotoGrade() {
        this.$router.push({
          path: '/teacher/coursePage/grade',
          query: {
            c_id: this.$store.state.c_id,
            role: this.role
          }
        });
      },
      gotoFileList() {
        this.$router.push({
          path: '/teacher/coursePage/fileList',
          query: {
           c_id: this.$store.state.c_id,
          }
        });
      }
    }

}
</script>

<style scoped>

.el-menu-item.is-active {
    background-color: #1593f9;
}

.el-menu-item {
    border-radius: 10px;
}
</style>
