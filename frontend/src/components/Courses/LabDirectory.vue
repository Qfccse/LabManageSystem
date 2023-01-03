<template>
    <div style="margin-left: 5%">
        <div class="course_intro">
            <div class="sec_title fl">
                <div class="_title">实 验 目 录</div>
                <div class="_title_e">Laboratory Directory</div>
            </div>
            <div class="line fl"></div>

            <div style="float: right; margin-right: 120px">
                <el-button
                    type="primary"
                    @click="dialogFormVisible = true"
                    v-if="this.$store.state.userInfo.role == 'teacher'"
                >发布实验</el-button
                >
            </div>

            <!-- 添加课程的弹出框 -->
            <el-dialog
                title="新建实验"
                :visible="dialogFormVisible"
                :before-close="handleDialogClose"
                style="width: 100%"
            >
                <el-form style="width: 600px">
                    <el-form-item label="实验名称:" label-width="120px">
                        <el-input
                            v-model="lab.name"
                            autocomplete="off"
                            style="width: 100%"
                        ></el-input>
                    </el-form-item>

                    <el-form-item label="起止时间:" label-width="120px">
                        <!-- <el-col :span="colspan.left"></el-col> -->
                        <el-col :span="colspan.left + 2">
                            <el-date-picker
                                style="width: 100%"
                                v-model="lab.start_time"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                type="datetime"
                                range-separator="至"
                                start-placeholder="开始日期"
                            ></el-date-picker>
                        </el-col>
                        <el-col :span="colspan.left" style="margin-left: 30px">
                            ————
                        </el-col>
                        <el-col :span="colspan.left + 2">
                            <el-date-picker
                                style="width: 100%"
                                v-model="lab.end_time"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                type="datetime"
                                range-separator="至"
                                start-placeholder="开始日期"
                            ></el-date-picker>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="实验描述:" label-width="120px">
                        <el-input
                            style="width: 100%"
                            type="textarea"
                            :autosize="{ minRows: 2, maxRows: 4 }"
                            placeholder="请输入实验描述"
                            v-model="lab.desc"
                        >
                        </el-input>
                    </el-form-item>
                </el-form>

                <div style="margin-top: 40px">
                    <el-button
                        type="primary"
                        @click="
              dialogFormVisible = false;
             click2CreateLabTodo();
            "
                        style="margin-left: 400px"
                    >创建</el-button
                    >
                </div>
            </el-dialog>

            <el-table
                :data="labs"
                max-height="450"
                style="width: 100%"
                :default-sort="{ prop: 'l_id', order: 'ascending' }"
            >
                <el-table-column prop="l_id" label="实验编号" width="100px">
                </el-table-column>
                <el-table-column prop="name" label="实验名称" width="250px">
                </el-table-column>
                <el-table-column prop="start_time" label="起始时间" width="200px">
                </el-table-column>

                <el-table-column prop="end_time" label="截止时间" width="200px">
                </el-table-column>

                <el-table-column label="操作" width="260px">
                    <template v-slot="scope">
                        <el-button
                            size="mini"
                            type="primary"
                            icon="el-icon-search"
                            @click="gotoLab(scope.row)"
                            plain
                        >进入实验</el-button
                        >

                        <el-button
                            v-if="
                $store.state.userInfo.role == 'teacher' ||
                $route.query.role == 'TA'
              "
                            size="mini"
                            type="success"
                            icon="el-icon-search"
                            @click="gotoReportList(scope.row)"
                            plain
                        >批阅报告</el-button
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
            c_id: 0,
            labs: null,
            newBulletin: {
                c_id: this.$route.query.c_id,
                t_id: this.$store.state.userInfo.id,
                desc: "",
                name: "",
            },

            //labcreater
            colspan: {
                left: 5,
                right: 19,
            },
            gutter: 20,
            lab: {
                l_id: 0,
                c_id: this.$store.state.c_id,
                name: "",
                start_time: "",
                end_time: "",
                desc: "",
                proportion: 0,
            },
        };
    },
    methods: {
        testRoute() {
            alert(
                this.$store.state.userInfo.role == "teacher" ||
                this.$route.query.role == "TA"
            );
            // alert("jump to piyue")
        },
        handleDialogClose() {
            this.dialogFormVisible = false;
        },
        gotoLab(row) {
            this.$store.commit("setCurrentLid", row.l_id);
            this.$router.push(
                "/" +
                this.$store.state.userInfo.role +
                "/coursePage/lab?l_id=" +
                row.l_id
            );
        },
        gotoReportList(row) {
            this.$router.push(
                "/" +
                this.$store.state.userInfo.role +
                "/coursePage/reportlist?l_id=" +
                row.l_id
            );
        },
        getLabs() {
            this.axios
                .get("/api/course/getLabsById/" + this.$route.query.c_id)
                .then((response) => {
                    console.log(response.data);
                    this.labs = response.data.labs;

                    this.labs.forEach((element) => {
                        element.start_time = element.start_time
                            .substring(0, 16)
                            .replace("T", " ");
                        element.end_time = element.end_time
                            .substring(0, 16)
                            .replace("T", " ");
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
        createLab() {
            this.lab.start_time = new Date(this.lab.start_time);
            this.lab.end_time = new Date(this.lab.end_time);
            console.log("这里创建课程");
            console.log(this.lab);
            this.axios({
                method: "post",
                url: "/api/laboratory/postNewLab",
                data: this.lab,
                headers: {
                    "Content-Type": "application/json",
                },
            }).then((resp) => {
                console.log(resp.data);
                this.$message.success("添加实验成功!");
                this.getLabs()
            });
        },
        createTodo(){
            console.log('create todo')
            let todo = {
                todo_id:0,
                c_id: this.$store.state.c_id,
                t_id:this.$store.state.userInfo.id,
                name:this.lab.name,
                desc:"请在截止期日前完成实验项目",
                start_time: new Date(this.lab.start_time),
                end_time: new Date(this.lab.end_time),
                type:2,
            }
            console.log(this.lab)
            console.log(todo)
            this. axios({
                method: "post",
                url:"/api/bulletin/addBulletin",
                data:todo,
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(resp =>{
                console.log(resp.data)
            })
        },
        click2CreateLabTodo(){
            this.createLab()
            this.createTodo()
        }
    },
    mounted() {
        this.getLabs();
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
