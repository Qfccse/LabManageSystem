<template>
  <div>
    <div style="width: 750px; margin-left: 3%; float: left">
      <div style="font-size: 36px; font-weight: bolder; margin-bottom: 10px">
        {{ this.$route.query.report }}
      </div>
      <div style="clear: both">
        <div class="row_title">姓名：{{ stu.name }}</div>
        <div class="row_title">学号：{{ stu.id }}</div>
      </div>
      <div
        v-for="(item, index) in this.formList"
        :key="index"
        style="clear: both"
      >
        <div class="row_title">{{ item.title }}</div>
        <div v-if="item.type === 'text'" style="clear: both">
          <el-input
            type="textarea"
            readonly
            :autosize="{ minRows: 3 }"
            v-model="item.content"
            :placeholder="item.placeholder ? item.placeholder : '该项未填写'"
          ></el-input>
        </div>
        <div v-else style="clear: both">
          <div v-for="(image, i) in item.imgList" :key="i">
            <img :src="require('../../../../../TJ-LabImages/' + image)" />
          </div>
          <div v-if="item.imgList.length === 0">未上传图片</div>
        </div>
      </div>
    </div>
    <div class="comment">
      <b>报告评分:</b>
      <input
        type="number"
        style="width: 100px; margin-left: 30px"
        v-model="score"
        max="100"
        min="0"
      />

      <br /><br />

      <p><b>报告评语：</b></p>

      <el-input type="textarea" :autosize="{ minRows: 3 }" v-model="comment">
      </el-input>
      <el-button
        type="primary"
        style="margin-top: 20px; float: right"
        @click="submitComment"
        v-if="this.marked == 0"
        >提交</el-button
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "ReportReader",
  data() {
    return {
      stu: {
        name: "",
        id: "",
      },
      formList: [],
      show: false,
      score: 0,
      comment: "",
      marked: 0,
    };
  },
  async mounted() {
    this.stu.name = this.$route.query.name;
    this.stu.id = this.$route.query.s_id;
    await this.receiveFormList();
    await this.getImages();
    this.getComment();
  },
  methods: {
    getComment() {
      var data = {
        l_id: this.$route.query.l_id,
        s_id: this.$route.query.s_id
      }

      this.axios
        .post("/api/grade/getComment/", data)
        .then((response) => {
          // this.$message.success("评分成功！");
          console.log(response.data);

          this.marked = response.data.marked;
          this.score = response.data.mark.score;
          this.comment = response.data.mark.comment;

        })
        .catch(function (response) {
          console.log(response); //发生错误时执行的代码
        });

    },
    submitComment() {
      // alert(this.score + this.comment);
      if (this.score < 0 || this.score > 100) {
        this.$message.warning("请确保评分在 0 - 100内!");
      }

      var newMark = {
        l_id: this.$route.query.l_id,
        s_id: this.$route.query.s_id,
        t_id: this.$store.state.userInfo.id,
        score: this.score,
        comment: this.comment,
      };

      console.log(newMark);

      this.axios
        .post("/api/grade/markReport/", newMark)
        .then((response) => {
          this.$message.success("评分成功！");
          console.log(response.data);
          this.getComment();
        })
        .catch(function (response) {
          console.log(response); //发生错误时执行的代码
        });
    },
    async receiveFormList() {
      await this.axios({
        method: "get",
        url: "/api/report/getReport",
        params: {
          //id 可以是lab的id
          l_id: this.$route.query.l_id,
          s_id: this.$route.query.s_id,
        },
      }).then((resp) => {
        console.log(resp.data);
        for (let i in resp.data) {
          this.formList.push({
            rf_id: resp.data[i].rf_id,
            title: resp.data[i].title,
            required: resp.data[i].required,
            type: resp.data[i].type,
            content: resp.data[i].content,
            imgList: [],
          });
        }
      });
    },
    async getImages() {
      for (let i in this.formList) {
        if (this.formList[i].type === "image") {
          await this.receiveFromImages(i);
        }
      }
    },
    async receiveFromImages(index) {
      console.log(this.formList[index].rf_id);
      await this.axios({
        method: "get",
        url: "/api/report/getFormImages",
        params: {
          //id 可以是lab的id
          rf_id: this.formList[index].rf_id,
        },
      }).then((resp) => {
        console.log(resp.data);
        for (let i in resp.data) {
          this.formList[index].imgList.push(resp.data[i]);
        }
        this.show = true;
      });
    },
  },
};
</script>

<style scoped>
.row_title {
  float: left;
  width: 50%;
  font-size: 20px;
  font-weight: bolder;
  margin-top: 10px;
  margin-bottom: 10px;
}

.comment {
  /* background-color: aqua;  */
  position: fixed;
  left: 1200px;
  bottom: 20px;
  /* float:left;  */
  width: 260px;
  height: 400px;
  /* margin-top: 160px; */
  /* margin-left: 40px */
}
</style>