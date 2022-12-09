 <template>
  <div>
    <el-container>
      <el-aside width="180px">
        <!-- 个人信息 -->
        <div class="personalInfo" height="2000px">
          <div style="margin-left: 65px">
            <img
              src="../../src/assets/avatars/teacher.jpg"
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
            <p style="color: #b2aeaf; margin-left: 5px;">{{ Id }}</p>
          </div>
        </div>

        <el-menu
          class="el-menu-vertical-demo"      
          background-color="#393836"
          text-color="#b2aeaf"
          active-text-color="#349f50"
        >
         


          <el-submenu index="1">
            <template v-slot:title>
              <i class="el-icon-location"></i>
              <span>我的课程</span>
            </template>


                 <el-popover
                     placement="right"
                     width="800px"
                     trigger="click">
                      
                    <!-- 想要作为教师的界面 -->
                       <el-table :data="TeacherCourseList">
                         <el-table-column prop="c_id" label="课程编号" width="100px">
                         </el-table-column>
                         <el-table-column
                         ref="filterTable"
                           prop="name"
                           label="课程名"
                           width="200px"
                         >
                         </el-table-column>
                         <el-table-column
                           prop="status"
                           label="状态"
                           width="100px"
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
                        
                         <el-table-column label="操作" width="150px">
                           <template v-slot="scope">
                             <el-button
                               size="mini"              
                               type="primary"
                               icon="el-icon-search"
                               @click="gotoCourseAsTeacher(scope.row)"
                             >查看详情</el-button>
                            
                           </template>
                          
                         </el-table-column>
                        
                       </el-table>
                      
                     <template v-slot:reference> 
                       <el-menu-item index="1-1">
                         <i class="el-icon-document"></i>
                         <template v-slot:title>普通教师</template>
                       </el-menu-item>
                     </template>
                 </el-popover>
                
                 <el-popover
                     placement="right"
                     width="600px"
                     trigger="click">
                      
                     <!-- 责任教师界面 -->
                       <el-table :data="HeadTeacherCourseList" max-height="450">
                           <el-table-column prop="c_id" label="课程编号" width="100px">
                           </el-table-column>
                           <el-table-column
                           ref="filterTable"
                             prop="name"
                             label="课程名"
                             width="200px"
                           >        
                           </el-table-column>
                           <el-table-column
                             prop="status"
                             label="状态"
                             width="100px"
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
                          

                           <!-- 操作 + 添加课程按钮 -->
                           <el-table-column :render-header="tableHead" width="230px">

                          
                           <template v-slot="scope">
                             <el-button
                               size="mini"
                               type="primary"
                               icon="el-icon-search"
                               @click="gotoCourseAsHT(scope.row)"
                             >查看详情</el-button>
                             <el-button
                               size="mini"
                               type="success"
                               icon="el-icon-edit"
                               v-show="scope.row.status !== '进行中' ? true : false"
                               @click="alterCourseStatus(scope.row)"
                             >开始</el-button>
                             <el-button
                               size="mini"
                               type="danger"
                               icon="el-icon-edit" 
                               v-show="scope.row.status === '进行中' ? true : false"      
                               @click="alterCourseStatus(scope.row)"
                             >关闭</el-button>
                            
                           </template>
                          
                           </el-table-column>
                          
                                                                         
                           
                         </el-table>
                        
                     <template v-slot:reference> 
                       <el-menu-item index="1-2">
                           <i class="el-icon-document"></i>
                           <template v-slot:title>责任教师</template>
                       </el-menu-item>
                     </template>
                 </el-popover>
                
                
               </el-submenu>
              
         

               <!-- 添加课程的弹出框 -->
                 <el-dialog title="添加课程" :visible="dialogFormVisible" :before-close="handleDialogClose" style="width: 90%">
                     <el-form :model="newCourse">
                       <el-form-item label="课程名称:" label-width="120px">
                         <el-input v-model="newCourse.name" autocomplete="off"  style="width: 90%;"></el-input>
                       </el-form-item>     
                       <el-form-item label="课程描述:" label-width="120px">
                          <el-input
                            style="width: 90%;"
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4}"
                            placeholder="请输入内容"
                            v-model="newCourse.desc">
                          </el-input>
                       </el-form-item>                                                         
                     </el-form>

                     <div style="margin-top: 40px;">
                      <el-button type="primary" 
                        @click="dialogFormVisible = false; addCourse()"  
                        style="margin-left: 400px; "
                      >确 定</el-button>
<!-- 
                       <el-button type="success" 
                        @click="dialogFormVisible = false" 
                        icon="el-icon-circle-plus-outline" 
                        style="margin-left: 100px; "
                      >批量添加</el-button> -->
                       
                     </div>
                 </el-dialog>
        

                 
             <el-menu-item index="3"  @click="($router.push('/teacher/board'))">
                      <i class="el-icon-document"></i>
                      <template v-slot:title>公告板</template>
                    </el-menu-item>


          <el-menu-item index="4">
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

            <el-menu-item index="4" style="float: right;">
              <i class="el-icon-unlock"></i>退出登录</el-menu-item
            >

        
          
          </el-menu>
        
        </el-header>

        <el-main style="height: 1200px">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "TeacherLayout",
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      dialogFormVisible: false,
      Name: this.$store.state.userInfo.name,
      Id: this.$store.state.userInfo.id,
      TeacherCourseList: null,
      HeadTeacherCourseList: null,
      newCourse: {
          name: '',
          desc: '',
          status: 2,
          t_id: this.$store.state.userInfo.id,
        },
    };
  },
  methods: {
    testVuex() {


      alert(this.$store.state.userInfo.role + "   " + this.$store.state.userInfo.id 
      + "\n"  + this.Name + "  " + this.Id + "\n"  + this.$store.state.c_id + "  " 
      + this.$store.state.l_id + "  "  + this.$store.state.roleInCourse)
    },
    tableHead() {
      return (
          <div>操作
            <el-button size="mini" onClick={()=>this.dialogFormVisible=true}   style="margin-left: 80px;"
            type="primary" plain>添加课程</el-button>  </div>
      )
    },
    handleDialogClose() {
      this.dialogFormVisible=false;
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
    getCourses() {
        // eslint-disable-next-line
        this.axios
          .get(
            "/api/course/getAllCoursesById/" +
              this.$store.state.userInfo.role +
              "/" +
              this.$store.state.userInfo.id
          )
          .then((response) => {
            console.log("this is get methods");
            console.log(response.data);
            this.TeacherCourseList = response.data.CoursesAsTeacher;
            
            this.TeacherCourseList.forEach(element => {
              if (element.status == 1) {
                element.status = "进行中"
              } else if (element.status == 2) {
                element.status = "未开课"
              } else {
                element.status = "已结课"
              } 
            });
  
            this.HeadTeacherCourseList = response.data.CoursesAsHeadTeacher;
  
            this.HeadTeacherCourseList.forEach(element => {
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
    addCourse() {
      console.log(this.newCourse);
      this.axios.post('/api/course', this.newCourse
      ).then((response) => {
        console.log(response);
        this.$message.success("添加课程成功!")
        this.getCourses();
      }).catch(function (response) {
        // this.$message.error("添加课程失败!")
        console.log(response); //发生错误时执行的代码
          
      });
    },
    alterCourseStatus(row) {
      // row.status='进行中'
      var newStatus;
      if (row.status != '进行中') {
        newStatus = 1;
      } else {
        newStatus = 3;
      }

      this.axios.put('/api/course', {c_id: row.c_id,  status: newStatus}
      ).then((response) => {
        console.log(response.data);
        this.getCourses()
      }).catch(function (response) {
        alert("error");
        console.log(response);
      });
    },
    gotoCourseAsTeacher(row) {
      this.$store.commit('setCurrentCid', row.c_id);
      this.$store.commit('setCourseRole', 'Teacher');
      this.$router.push('/teacher/coursePage/mainpage?c_id=' + this.$store.state.c_id);
    },
    gotoCourseAsHT(row) {
      this.$store.commit('setCurrentCid', row.c_id);
      this.$store.commit('setCourseRole', 'HT');
      this.$router.push('/teacher/coursePage/mainpage?c_id=' + this.$store.state.c_id);
    }

  },
  mounted() {
      //先把vuex初始化一下  以后这步应该在登录界面做
      sessionStorage.setItem(
        "UserInfo",
        JSON.stringify({ id: "12345", role: "teacher", name: "唐剑锋" })
      );
      this.$store.commit("setUserInfo");
  
      this.getCourses();
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
