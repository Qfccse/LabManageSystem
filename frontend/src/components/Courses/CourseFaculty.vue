<template>
  <div style="margin-left: 5%">
    <div class="course_intro">
      <div class="sec_title fl">
        <div class="_title">课 程 人 员</div>
        <div class="_title_e">Course Faculty</div>
      </div>
      <div class="line fl"></div>
      

      <el-table
        :data="teachers"
        max-height="450"
        stripe
        style="width: 90%"
        :default-sort="{ prop: 'role', order: 'descending' }"
      >
        <el-table-column prop="u_id" label="工号" width="150px">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="200px">
        </el-table-column>
        

        <el-table-column
          prop="role"
          label="角色"
          width="200px"
          :filters="[
            { text: '教师', value: '教师' },
            { text: '责任教师', value: '责任教师' },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template v-slot="scope">
            <el-tag
              :type="
                scope.row.role === '教师'
                  ? 'primary'
                  : 'success'
              "
              disable-transitions
              >{{ scope.row.role }}</el-tag
            >
          </template>
        </el-table-column>

        <el-table-column prop="email" label="联系方式" width="200px">
        </el-table-column>
      </el-table>

      <div style="height: 50px"></div>

      <el-table
        :data="students"
        max-height="600px"
        stripe
        style="width: 90%"
        :default-sort="{ prop: 'role', order: 'ascending' }"
      >
        <el-table-column prop="u_id" label="学号" width="150px">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="200px">
        </el-table-column>
        
        <el-table-column
          prop="role"
          label="角色"
          width="200px"
          :filters="[
            { text: '助教', value: '助教' },
            { text: '学生', value: '学生' },
          ]"
          :filter-method="filterTag"
          filter-placement="bottom-end"
        >
          <template v-slot="scope">
            <el-tag
              :type="
                scope.row.role === '学生'
                  ? 'primary'
                  : 'success'
              "
              disable-transitions
              >{{ scope.row.role }}</el-tag
            >
          </template>
        </el-table-column>

        <el-table-column prop="email" label="联系方式" width="200px">
        </el-table-column>
      </el-table>

      
    </div>
  </div>
</template>
  
<script>
export default {
  data() {
    return {
      students: null,
      teachers: null,
    };
  },
  methods: {
    filterTag(value, row) {
      return row.role === value;
    },
    getFaculty() {
      this.axios
        .get(
          "/api/course/getCourseFaculty/" +
            this.$route.query.c_id
        )
        .then((response) => {
          console.log("this is raw data");
          console.log(response.data);
          this.students = response.data.students;
          this.teachers = response.data.teachers;

          this.students.forEach((element) => {
            if (element.role == "4") {
              element.role = "学生";
            } else {
              element.role = "助教";
            }
          });

          this.teachers.forEach((element) => {
            if (element.role == "2") {
              element.role = "教师";
            } else if (element.role == "1") {
              element.role = "责任教师";
            }
          });
        })
        .catch(function (response) {
          console.log(response);
        });
    },
  },
  mounted() {
    this.getFaculty();
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