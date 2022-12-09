<template>

<div style="margin-left: 5%">
 <div class="course_intro">
   <div class="sec_title fl">
     <div class="_title">我 的 成 绩</div>
     <div class="_title_e">My Grade</div>
   </div>
   <div class="line fl"></div>
   


   <el-table
      :data="grade.labScores"
      max-height="600px"
      stripe
      style="width: 90%"
    >
      <el-table-column prop="l_id" label="实验编号" width="100px">
      </el-table-column>
      <el-table-column prop="name" label="实验名称" width="250px">
      </el-table-column>


      <el-table-column
          prop="attend_score"
          label="实验出勤"
          width="200px"
          :filters="[
            { text: '已完成', value: '已完成' },
            { text: '未签到', value: '未签到' },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template v-slot="scope">
            <el-tag
              :type="
                scope.row.attend_score === '已完成'
                  ? 'primary'
                  : 'success'
              "
              disable-transitions
              >{{ scope.row.attend_score }}</el-tag
            >
          </template>
        </el-table-column>

     
      <el-table-column prop="score" label="报告成绩" width="200px">
      </el-table-column>
    </el-table>
   
 </div>
</div>




</template>
  
  <script>
export default {
  data() {
    return {
      grade: null,
    };
  },
  methods: {
    filterTag(value, row) {
      return row.attend_score === value;
    },
    getGrade() {
      this.axios
        .get(
          "/api/grade/getSingleGrade/" +
            this.$store.state.userInfo.id +
            "/" +
            this.$route.query.c_id
        )
        .then((response) => {
          console.log("this is grade");
          this.grade = response.data.grade;
          this.grade.labScores.forEach(element => {
            if (element.attend_score == 1) {
              element.attend_score = '已完成'
            } else if (element.attend_score == 0) {
              element.attend_score = '未签到'
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
  },
  mounted() {
    this.getGrade();
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