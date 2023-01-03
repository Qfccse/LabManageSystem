<template>
  <div style="margin-left: 5%; margin-top: -50px">
    <div class="course_intro">
      <div class="sec_title fl">
        <div class="_title">公 告 板</div>
        <div class="_title_e">Bulletin Board</div>
      </div>
      <div class="line fl"></div>
    </div>

    <div style="width: 200px; height: 70px;">&nbsp;</div>

    <div
      class="my-3 p-3 bg-white rounded shadow"
      style="width: 1050px"
      v-for="(bulletin, index) in bulletins"
      :key="index"
    >
      <h4 class="border-bottom border-gray pb-2 mb-0">
        {{ bulletin.date.split("-").join("/") }}
      </h4>

      <div
        class="media text-muted pt-3"
        v-for="(note, index) in bulletin.notification"
        :key="index"
      >
        <!-- <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg> -->
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
          <strong class="d-block text-gray-dark">   <span style="font-size: 18px; margin-right:50px;" >{{note.name}}</span>  {{note.t_name}} @{{ note.c_name }}</strong>
            详细内容:   {{ note.desc }}
        </p>
      </div>

      <!-- <small class="d-block text-right mt-3">
      <a href="#">All updates</a>
    </small> -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      bulletins: [],
    };
  },
  methods: {
    getTodos() {
      this.axios
        .get("/api/course/getTodo/0")
        .then((response) => {          
          response.data.todos.forEach(element => {
            element.end_time = element.end_time
              .substring(0, 16)
              .replace("T", " ");
          });

          console.log(response.data.todos);

          function sortRule(a, b) {
            if ( a.end_time < b.end_time) {
               return -1;
            } else if ( a.end_time == b.end_time) {
               return 0;
            } else if ( a.end_time > b.end_time) {
               return 1;
            }
          }

          //按照日期排序 之后按照日期生成对象 展示在公告板中
          response.data.todos.sort(sortRule);

          var date = response.data.todos[0].end_time.substring(0, 10);
          var arr = [];
          var note = null;

          console.log(response.data.todos);

          response.data.todos.forEach(element => {
            var curdate = element.end_time.substring(0,10)
            if(curdate == date) {
              arr.push(element);
            } else {
              note = {date: date};
              note['notification'] = arr;
              this.bulletins.push(note);

              date = curdate;
              arr = [];
              arr.push(element);

              note = null;
            }
          });

          //把最后一个日期放入
          note = {date: date};
          note['notification'] = arr;
          this.bulletins.push(note);

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
   this.getTodos()
  }
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