<template>
    <div style="width: 1050px;margin: auto">
        <div style="font-size: 26px;font-weight: bolder;height: 80px;line-height: 80px">
            {{reportName}}
        </div>
        <el-table
            :data="reportList"
            min-height="450"
            style="width: 100%"
            :default-sort="{ prop: 's_id', order: 'ascending' }"
        >
            <el-table-column prop="s_id" label="学号" width="200px">
            </el-table-column>
            <el-table-column prop="stu_name" label="姓名" width="300px">
            </el-table-column>
            <el-table-column prop="status" label="提交状态" width="200px">
            </el-table-column>
            <el-table-column label="操作" width="250px">
                <template v-slot="scope">
                    <el-button
                        size="mini"
                        type="primary"
                        icon="el-icon-search"
                        @click="gotoReport(scope.row)" plain :disabled="scope.row.status==='未提交'">批阅报告</el-button >
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    name: "LabReportList",
    data(){
        return{
            reportName:"",
            reportList:[],
            statusList:["未完成","未提交","已提交", "已批改"],
        }
    },
    mounted() {
        this.getLabReportList()
    },
    methods:{
        getLabReportList(){
            this. axios({
                method:"get",
                url:"/api/report/getLabReport",
                params:{
                    l_id:2
                }
            }).then(resp =>{
                console.log(resp.data)
                for(let i in resp.data){
                    this.reportName  = resp.data[i].name
                    this.reportList.push({
                        r_id:resp.data[i].r_id,
                        s_id:resp.data[i].s_id,
                        stu_name:resp.data[i].stu_name,
                        status:this.statusList[resp.data[i].status]
                    })
                }
            })
        },
        gotoReport(row){
            console.log(row)
            this.$router.push({
                path: '/teacher/coursePage/reportreader',
                query:{
                    l_id:2,
                    s_id:row.s_id,
                    name:row.stu_name,
                    report:this.reportName,
                }
            })
        }
    }
}
</script>

<style scoped>
.el-row{
    height: 40px;
    font-size: 18px;
    line-height: 40px;
}
</style>