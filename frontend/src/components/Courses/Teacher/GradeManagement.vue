<template>
  <div style="margin-left: 5%">
    <div class="course_intro">
      <div class="sec_title fl">
        <div class="_title">成 绩 管 理</div>
        <div class="_title_e">Grade Management</div>
      </div>
      <div class="line fl"></div>

      <div style="float: right; margin-right: 120px">
        <el-button
          type="primary"
          @click="dialogFormVisible = true"
          v-if="this.$route.query.role=='HT'"
          >分配权重</el-button
        >
      </div>

      <!-- 添加课程的弹出框 -->
      <el-dialog
        title="设置实验成绩占比"
        :visible="dialogFormVisible"
        :before-close="handleDialogClose"
        style="width: 100%"
      >

        <el-table
        :data="labs"
        max-height="450"
        style="width: 80%; margin-left: 100px;"
        :default-sort="{ prop: 'l_id', order: 'ascending' }"
      >
        <el-table-column prop="l_id" label="实验编号" width="100px">
        </el-table-column>
        <el-table-column prop="name" label="实验名称" width="250px">
        </el-table-column>

        <el-table-column label="权重" width="150px">
          <template v-slot="scope">
            <el-input  size="mini" type="number" v-model="scope.row.proportion">
            </el-input>
          </template>

          
        </el-table-column>
      </el-table>


        <div style="margin-top: 40px">
          <el-button
            type="primary"
            @click="
              dialogFormVisible = false;
              setWeight();
            "
            style="margin-left: 400px"
            >确 定</el-button
          >
        </div>
      </el-dialog>

      <el-table
        :data="grades"
        max-height="1000px"
        stripe
        style="width: 90%"
        ref="table"
      >
        <el-table-column type="expand" width="10px">
          <template v-slot="scope">
            <el-table
              :data="scope.row.labScores"
              max-height="450px"
              stripe
              style="width: 90%;  margin-left: 60px"
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
          </template>
        </el-table-column>

        <el-table-column prop="s_id" label="学号" width="130px">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="130px">
        </el-table-column>
        <el-table-column prop="attend_score" label="出勤分" width="150px">
        </el-table-column>
        <el-table-column prop="lab_score" label="实验总分" width="150px">
        </el-table-column>

        <el-table-column prop="final_grade" label="课程总分" width="150px">
        </el-table-column>

        <el-table-column label="操作" width="150px">
          <template v-slot="scope">
            <el-button
              size="mini"
              type="primary"
              plain
              icon="el-icon-search"
              @click="expand(scope.row)"
              >查看详情</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      grades: null,
      labs: [],
    };
  },
  methods: {
    handleDialogClose() {
      this.dialogFormVisible = false;
    },
    filterTag(value, row) {
      return row.attend_score === value;
    },
    expand(row) {
      this.$refs.table.toggleRowExpansion(row);
    },
    getGrade() {
      this.axios
        .get("/api/grade/getAllGrades/" + this.$route.query.c_id)
        .then((response) => {
          console.log("this is grade");
          this.grades = response.data.grades;

          this.grades.forEach((element) => {
            if (element.attend_score < 0) {
              element.attend_score = "暂未评分";
            }

            if (element.lab_score < 0) {
              element.lab_score = "暂未评分";
            }

            if (element.final_grade < 0) {
              element.final_grade = "暂未评分";
            }

            element.labScores.forEach((element) => {
              if (element.attend_score == 1) {
                element.attend_score = "已完成";
              } else if (element.attend_score == 0) {
                element.attend_score = "未签到";
              }
            });
          });

          // this.grades = response.data.grade;
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
    getLabs() {
      // alert("/api/course/getLabsById/" + this.$store.state.c_id)
      this.axios
        .get("/api/course/getLabsById/" + this.$store.state.c_id)
        .then((response) => {
          console.log(response.data);

          response.data.labs.forEach(element => {
            var lab = {l_id: element.l_id, name: element.name, proportion: element.proportion}
            this.labs.push(lab)
          });
         
          console.log(this.labs);

          
          
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
    setWeight() { 
      var sum = 0;
      this.labs.forEach(element=>{
        sum +=  parseInt(element.proportion);  
        console.log(sum);    
      })

      if (sum != 100) {
        this.$message.warning("请确保权重之和为100！")
        this.dialogFormVisible=true;
      }

      console.log(this.labs);
      this.axios.post('/api/grade/calcWeightedGrade', this.labs
      ).then((response) => {
        console.log(response);
        this.$message.success("添加权重成功!")
        this.getGrade();
      }).catch(function (response) {
        this.$message.error("添加权重失败!")
        console.log(response); //发生错误时执行的代码
          
      });
    }
  },
  mounted() {
    this.getGrade();
    this.getLabs()
  },
};
</script>
  
<style >
/* 把二级表格的小箭头隐藏 */
.el-table__expand-column .cell {
  display: none;
}
</style>

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