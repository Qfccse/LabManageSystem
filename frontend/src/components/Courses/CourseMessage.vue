<template>
  <div style="margin-left: 5%">
    <div class="course_intro">
      <div class="sec_title fl">
        <div class="_title">课 程 公 告</div>
        <div class="_title_e">Bulletin Board</div>
      </div>
      <div class="line fl"></div>

<!-- 
      <div>操作
            <el-button size="mini" onClick={()=>this.dialogFormVisible=true}   style="margin-left: 80px;"
            type="primary" plain>添加课程</el-button>  </div> -->

      <div style="float: right; margin-right: 120px">
        <el-button  type="primary" @click="(dialogFormVisible=true)" v-if="this.$store.state.userInfo.role=='teacher'">发布公告</el-button>
      </div>

      <!-- 添加课程的弹出框 -->
      <el-dialog title="添加公告" :visible="dialogFormVisible" :before-close="handleDialogClose" style="width: 90%">
                     <el-form :model="newBulletin">
                       <el-form-item label="公告主题:" label-width="120px">
                         <el-input v-model="newBulletin.name" autocomplete="off"  style="width: 90%;"></el-input>
                       </el-form-item>     
                       <el-form-item label="公告内容:" label-width="120px">
                          <el-input
                            style="width: 90%;"
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4}"
                            placeholder="请输入内容"
                            v-model="newBulletin.desc">
                          </el-input>
                       </el-form-item>                                                         
                     </el-form>

                     <div style="margin-top: 40px;">
                      <el-button type="primary" 
                        @click="dialogFormVisible = false; addTodo()"  
                        style="margin-left: 400px; "
                      >确 定</el-button>

                      
                       
                     </div>
                 </el-dialog>

      <div style="width: 200px">&nbsp;</div>

      <div
        class="my-3 p-3 bg-white rounded shadow"
        style="width: 950px"
        :key="index"
      >
        <div
          class="media text-muted pt-3"
          v-for="(note, index) in bulletins"
          :key="index"
        >
          <!-- <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg> -->
          <p
            class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray"
          >
            <strong class="d-block text-gray-dark">
              <span style="font-size: 18px; margin-right: 50px">{{
                note.name
              }}</span>
              {{ note.t_name }} @{{ note.c_name }}
              <span style="font-size: 18px; float: right">{{
                note.end_time
              }}</span>
            </strong>
            详细内容: {{ note.desc }}
          </p>
        </div>

        <!-- <small class="d-block text-right mt-3">
  <a href="#">All updates</a>
</small> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      bulletins: [],
      newBulletin: {
          c_id: this.$route.query.c_id,
          t_id: this.$store.state.userInfo.id,
          desc: '',
          name: '',
        },
    };
  },
  methods: {
    handleDialogClose() {
      this.dialogFormVisible = false;
    },
    addTodo() {
      console.log(this.newBulletin);
      this.axios.post('/api/course/addBulletin', this.newBulletin
      ).then((response) => {
        console.log(response);
        this.$message.success("添加公告成功!")
        this.getCourses();
      }).catch(function (response) {
        this.$message.error("添加公告失败!")
        console.log(response); //发生错误时执行的代码
          
      });
      this.newBulletin.desc= '',
      this.newBulletin.name= '',
      this.getTodos()

    },
    getTodos() {
      this.axios
        .get("/api/course/getTodo/" + this.$route.query.c_id)
        .then((response) => {
          response.data.todos.forEach((element) => {
            element.end_time = element.end_time
              .substring(0, 16)
              .replace("T", " ");
          });

          console.log(response.data.todos);

          function sortRule(a, b) {
            if (a.end_time < b.end_time) {
              return -1;
            } else if (a.end_time == b.end_time) {
              return 0;
            } else if (a.end_time > b.end_time) {
              return 1;
            }
          }

          //按照日期排序 之后按照日期生成对象 展示在公告板中
          response.data.todos.sort(sortRule);

          this.bulletins = response.data.todos;
          console.log(this.bulletins);
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
  },
  mounted() {
    this.getTodos();
  },
};
</script>

<style scoped>
.course_intro {
  margin-bottom: 50px;
}
.sec_title {
  margin-bottom: 20px;
}
._title {
  font-size: 28px;
  color: deepskyblue;
}
._title_e {
  font-size: 14px;
  color: limegreen;
}

.line {
  border-bottom: #b3c0d1 2px solid;
  margin-left: 20px;
  width: 600px;
  height: 45px;
}

.fl {
  float: left;
}
.fr {
  float: right;
}
</style>