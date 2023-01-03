<template>
  <div style="width: 1050px; margin: auto">
    <el-row class="lab_name">
      <el-col :span="16">{{ lab.name }}</el-col>
      <el-col :span="8">
        <el-button
          type="primary"
          class="fr"
          style="margin-top: 20px"
          @click="click2CreateTemplate"
          >发布报告模板</el-button
        >
      </el-col>
    </el-row>
    <div style="width: 750px" class="fl">
      <el-row class="lab_info clear_box">
        <el-col :span="3" class="lab_ddl">截止时间：</el-col>
        <el-col :span="8" class="l_time">{{
          getNowFormatDate(lab.end_time)
        }}</el-col>
        <el-col :span="3" class="lab_prop">分数权重：</el-col>
        <el-col :span="2" class="l_prop">{{ lab.proportion }}</el-col>
      </el-row>

      <el-input
        type="textarea"
        :autosize="{ minRows: 2 }"
        placeholder="请输入实验简介"
        v-model="lab.desc"
        :readonly="edit"
        style="font-size: 20px; margin-top: 20px"
      >
      </el-input>

      <div
        style="margin-top: 50px; margin-left: 700px"
        v-if="this.$store.state.userInfo.role == 'teacher'"
      >
        <el-button
          style="margin-left: -50px"
          type="primary"
          icon="el-icon-edit"
          v-show="edit"
          @click="startEdit"
          >编辑</el-button
        >

        <el-button
          style="margin-left: -50px"
          type="success"
          icon="el-icon-edit"
          v-show="!edit"
          @click="saveEdit"
          >保存</el-button
        >
      </div>
    </div>
    <div class="lab_right fl">
      <div class="guidebook_title">
        <div style="min-height: 50px; line-height: 50px; align-items: center">
          <div style="width: 140px; margin-left: 70px">
            <div class="fl">实验资料</div>
            <FileUploader
              url="/api/guidebook/postLabGuidebook"
              :id="{
                g_id: 0,
                l_id: this.lab.l_id,
                t_id: this.$store.state.userInfo.id,
                type: 1,
                name: '',
                path: '',
              }"
              file-type=".pdf"
              icon="el-icon-upload"
              button-style="border:none"
              :show="true"
            ></FileUploader>
          </div>
        </div>
      </div>
      <div class="clear_box" style="margin-left: 20px">
        <div v-for="(guidebook, index) in guidebookList" :key="index">
          <router-link
            :to="{
              path: '/teacher/coursePage/guidebook',
              query: { g_id: guidebook.g_id, name: guidebook.name },
            }"
          >
            <div class="li_guidebook">
              <i class="el-icon-document">&nbsp;</i>
              {{ guidebook.name }}
            </div>
          </router-link>
        </div>
        <div
          v-if="guidebookList.length === 0"
          style="
            text-align: center;
            color: #666666;
            font-size: 20px;
            height: 80px;
            line-height: 80px;
          "
        >
          暂无实验资料
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FileUploader from "@/components/Utils/FileUploader";
export default {
  name: "TeacherLabPage",
  components: { FileUploader },
  data() {
    return {
      edit: true,
      l_id: 0,
      lab: {},
      guidebookList: [],
    };
  },
  mounted() {
    this.l_id = "1";
    this.getLab();
    this.showGuidebookList();
    console.log(new Date(this.lab.end_time));
  },
  methods: {
    testLab() {
      console.log(this.lab);
    },
    fillZero(str) {
      if (str >= 0 && str <= 9) {
        return "0" + str;
      } else {
        return str + "";
      }
    },
    getNowFormatDate(dt) {
      const date = new Date(dt);
      const s1 = "-";
      const s2 = ":";
      const year = date.getFullYear();
      const month = this.fillZero(date.getMonth() + 1);
      const strDate = this.fillZero(date.getDate());
      const hour = this.fillZero(date.getHours());
      const mini = this.fillZero(date.getMinutes());
      const sec = this.fillZero(date.getSeconds());
      return (
        year + s1 + month + s1 + strDate + " " + hour + s2 + mini + s2 + sec
      );
    },
    getLab() {
      this.axios({
        method: "get",
        url: "/api/laboratory/getLabInfo",
        params: {
          l_id: this.$route.query.l_id,
          // l_id:1
        },
      }).then((resp) => {
        console.log(resp.data);
        this.lab = {
          l_id: Number(this.$route.query.l_id),
          name: resp.data.name,
          start_time: resp.data.start_time,
          end_time: resp.data.end_time,
          desc: resp.data.desc,
          proportion: resp.data.proportion,
        };
      });
    },
    showGuidebookList() {
      this.axios({
        method: "get",
        url: "/api/guidebook/getLabGuidebooks",
        params: {
          l_id:this.$route.query.l_id
          // l_id: 1,
        },
      }).then((resp) => {
        console.log(resp.data);
        for (let i in resp.data) {
          this.guidebookList.push({
            g_id: resp.data[i].g_id,
            name: resp.data[i].name,
          });
        }
      });
    },
    click2CreateTemplate() {
      // alert("test")
      this.$router.push({
        path: "/teacher/coursePage/createReport?l_id=" + this.$route.query.l_id,
      });
    },
    // YuRan
    saveEdit() {
      this.edit = true;
      this.modifylab();
    },
    startEdit() {
      this.edit = false;
    },
    modifylab() {
      var lab = { desc: this.lab.desc, l_id: this.lab.l_id };
      console.log(lab);
      this.axios
        .post("/api/course/labModify", lab)
        .then((response) => {
          console.log("this is modification");
          console.log(response.data);
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
};
</script>

<style scoped>
.clear_box {
  clear: both;
}
.show_border {
  /*border: 2px red solid;*/
}
.fl {
  float: left;
}
.fr {
  float: right;
}
.lab_name {
  height: 70px;
  line-height: 70px;
  border-bottom: 1px gray solid;
  font-size: 36px;
}
.el-button {
  font-size: 16px;
}
.lab_intro {
  margin-top: 20px;
  font-size: 18px;
  white-space: pre-wrap;
}
.lab_info {
  height: 50px;
  line-height: 50px;
  font-size: 16px;
  margin-top: 10px;
  border-bottom: 1px solid gray;
}
.lab_ddl,
.lab_prop {
  font-weight: bolder;
}
.l_time {
}

.li_guidebook {
  height: 30px;
  font-size: 16px;
  line-height: 30px;
  text-decoration: none;
}
a {
  text-decoration: none;
}
.guidebook_title {
  font-weight: bolder;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 20px;
  font-size: 18px;
}
.lab_right {
  width: 250px;
  margin-left: 48px;
  min-height: 200px;
  border: #666666 1px solid;
  border-radius: 5px;
  margin-top: 30px;
}
</style>