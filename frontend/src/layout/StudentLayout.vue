 <template>
  <div>
    <el-container>
      <el-aside width="180px">
        <!-- 个人信息 -->
        <div class="personalInfo" height="2000px">
          <div style="margin-left: 65px">
            <img
              src="../../src/assets/avatars/student.jpg"
              style="
                height: 60px;
                width: 60px;
                margin-top: 20px;
                border-radius: 50%;
              "
              @click="testVuex"
            />
            <h5
              style="
                color: #ffffff;
                width: 60px;
                font-weight: bolder;
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
          <el-submenu index="1">
            <template v-slot:title >
              <i class="el-icon-location"></i>
              <span >我的课程</span>
            </template>


            <el-popover
                placement="right"
                width="600px"
                trigger="click">
                  <!-- 想要看详情的界面 -->
                  <el-table  :data="StudentCourseList" max-height="450" >
                           <el-table-column prop="c_id" label="课程编号" width="100" >
                           </el-table-column>
                           <el-table-column
                           ref="filterTable"
                             prop="name"
                             label="课程名"
                             width="200"
                           >
                           </el-table-column>

                           <el-table-column
                             prop="status"
                             label="状态"
                             width="100"
                             :filters="[
                               { text: '进行中', value: '进行中'},
                               { text: '未开课', value: '未开课' },
                               { text: '已结课', value: '已结课' },
                             ]"
                             :filter-method="filterTag"
                             filter-placement="bottom-end"
                           >
                           <template v-slot="scope">
                               <el-tag
                                 :type="scope.row.status === '进行中' ? 'primary' : scope.row.status === '未开课' ? 'success' : 'warning'"
                                 disable-transitions
                                 >{{ scope.row.status }}</el-tag>
                             </template>
                           </el-table-column>


                           <el-table-column label="操作" width="150">
                           <template v-slot="scope">
                             <el-button
                               size="mini"
                               type="primary"
                               icon="el-icon-search"
                               @click="gotoCourseAsStudent(scope.row)">查看详情</el-button>
                           </template>
                           </el-table-column>

                         </el-table>

                <template v-slot:reference>
                  <el-menu-item
                    index="1-1">
                    <i class="el-icon-document"></i>
                    <template v-slot:title>学生</template>
                  </el-menu-item>
                </template>
          </el-popover>




          <el-popover
                placement="right"
                width="600px"
                trigger="click">
                <!-- 助教界面 -->
                <el-table :data="TACourseList" max-height="450">
                             <el-table-column prop="c_id" label="课程编号" width="100">
                             </el-table-column>
                             <el-table-column
                             ref="filterTable"
                               prop="name"
                               label="课程名"
                               width="200"
                             >
                             </el-table-column>
                             <el-table-column
                               prop="status"
                               label="状态"
                               width="100"
                               :filters="[
                                 { text: '进行中', value: '进行中'},
                                 { text: '未开课', value: '未开课' },
                                 { text: '已结课', value: '已结课' },
                               ]"
                               :filter-method="filterTag"
                               filter-placement="bottom-end"
                             >
                             <template v-slot="scope">
                                 <el-tag
                                   :type="scope.row.status === '进行中' ? 'primary' : scope.row.status === '未开课' ? 'success' : 'warning'"
                                   disable-transitions
                                   >{{ scope.row.status }}</el-tag
                                 >
                               </template>
                             </el-table-column>

                             <el-table-column label="操作" width="150">
                           <template v-slot="scope">
                             <el-button
                               size="mini"
                               type="primary"
                               icon="el-icon-search"
                               @click="gotoCourseAsTA(scope.row)">查看详情</el-button>
                           </template>
                           </el-table-column>
                           </el-table>
                <template v-slot:reference>

            <el-menu-item index="1-2">
              <i class="el-icon-document"></i>
              <template v-slot:title>助教</template>
            </el-menu-item>
                </template>
          </el-popover>

          </el-submenu>


          <el-menu-item index="3" @click="($router.push('/student/board'))">
            <i class="el-icon-data-board"></i>
            <template v-slot:title>公告板</template>
          </el-menu-item>
            <el-menu-item index="5"  @click="($router.push('/teacher/calendar'))">
                <i class="el-icon-date"></i>
                <template v-slot:title>日历</template>
            </el-menu-item>
          <el-menu-item index="4" disabled>
            <i class="el-icon-setting"></i>
            <template v-slot:title >我的信息</template>
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

            <el-menu-item index="4"  style="float: right;" @click="logout">
              <i class="el-icon-unlock"></i>退出登录</el-menu-item
            >


          </el-menu>
        </el-header>

        <el-main style="height: 1200px;">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "StudentLayout",
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      Name: this.$store.state.userInfo.name,
      Id: this.$store.state.userInfo.id,
      StudentCourseList: null,
      TACourseList: null,
    };
  },
  methods: {
    testVuex() {
      alert(this.$store.state.userInfo.role + "   " + this.$store.state.userInfo.id
      + "\n"  + this.Name + "  " + this.Id + "\n"  + this.$store.state.c_id + "  "
      + this.$store.state.l_id + "  "  + this.$store.state.roleInCourse + "  "
      + this.$store.state.c_name)
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
    filterTag(value, row) {
      return row.status === value;
    },
    getCoursesAsStudent() {
      // eslint-disable-next-line
      this.axios
        .get(
          "/api/course/getAllCoursesById/" +
            this.$store.state.userInfo.role +
            "/" +
            this.$store.state.userInfo.id
        )
        // this. axios({
        //         method:"get",
        //         url:"/api/course/getAllCoursesById/" +
        //            this.$store.state.userInfo.role +
        //            "/" +
        //            this.$store.state.userInfo.id

        //     })
        .then((response) => {
          console.log(response.data);
          this.StudentCourseList = response.data.CoursesAsStudent;

          this.StudentCourseList.forEach(element => {
            if (element.status == 1) {
              element.status = "进行中"
            } else if (element.status == 2) {
              element.status = "未开课"
            } else {
              element.status = "已结课"
            }
          });

          this.TACourseList = response.data.CoursesAsTA;

          this.TACourseList.forEach(element => {
            if (element.status == 1) {
              element.status = "进行中"
            } else if (element.status == 2) {
              element.status = "未开课"
            } else {
              element.status = "已结课"
            }
          });

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
    gotoCourseAsStudent(row) {
      this.$store.commit('setCurrentCid', row.c_id);
      this.$store.commit('setCourseName', row.name);
      this.$router.push('/student/coursePage/mainpage?c_id=' + this.$store.state.c_id);
    },
    gotoCourseAsTA(row) {
      this.$store.commit('setCurrentCid', row.c_id);
      this.$store.commit('setCourseName', row.name);
      this.$router.push('/student/coursePage/mainpage?c_id=' + this.$store.state.c_id);
    },
    logout() {
      this.$store.commit("clear");
      sessionStorage.clear();
      this.$router.push("/login");
    }

  },

  mounted() {
    // //先把vuex初始化一下  以后这步应该在登录界面做
    // sessionStorage.setItem(
    //   "UserInfo",
    //   JSON.stringify({ id: "2053677", role: "student" , name: "于然"})
    // );
    // this.$store.commit("setUserInfo");

    this.getCoursesAsStudent();
    console.log("layout has been mounted");
  },
};
</script>

<style >
.el-submenu__icon-arrow {
  right: 50px !important;
}

</style>

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
  /* background-color: #e9eef3; */
}

.personalInfo {
  height: 160px;
  background-color: #292929;
}
</style>
