<template>
  <div style="margin-left: 5%">
    <div class="course_intro">
      <div class="sec_title fl">
        <div class="_title">课 程 反 馈</div>
        <div class="_title_e">Course Feedback</div>
      </div>
      <div class="line fl"></div>

      <el-table
        :data="feedback"
        max-height="450"
        stripe
        style="width: 90%"
        :default-sort="{ prop: 'l_id', order: 'ascending' }"
      >
        <el-table-column prop="l_id" label="实验编号" width="100px">
        </el-table-column>
        <el-table-column prop="l_name" label="实验名称" width="200px">
        </el-table-column>
        <el-table-column prop="s_id" label="学号" width="150px">
        </el-table-column>

        <el-table-column prop="s_name" label="学生姓名" width="100px">
        </el-table-column>

        <el-table-column prop="feedback" label="反馈" width="380px">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      feedback: [],
    };
  },
  methods: {
    getFeedback() {
      this.axios
        .get(
          "/api/course/getFeedbackAsTeacher/" +
            this.$route.query.c_id
        )
        .then((response) => {
          console.log("this is feedback");
          // this.feedback = response.data.feedbacks;
          response.data.feedbacks.forEach(element => {
            console.log("check")
            if (element.feedback!=null && element.feedback!='') {
              this.feedback.push(element)
            }
        })
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
    this.getFeedback();
  },
};
</script>

<style scoped>

.course_intro{
   margin-bottom: 50px;
   
}
.sec_title{
   margin-bottom: 20px;
}
._title{
   font-size: 28px;
   color: deepskyblue;
}
._title_e{
   font-size: 14px;
   color: limegreen;
}

.line{
   border-bottom: #B3C0D1 2px solid;
   margin-left: 20px;
   width: 600px;
   height: 45px;
}

.fl{
   float: left;
}
.fr{
   float: right;
}
</style>