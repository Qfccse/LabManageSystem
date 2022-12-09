<template>
    <div style="width: 1200px">
        <div style="min-height: 650px;">
            <el-form>
                <el-row style="height: 60px">
                    <el-col :span="8">
                        查找：
                        <el-input
                            @input="search"
                            style="width: 60%"
                            v-model="inputText"
                            suffix-icon="el-icon-search">
                        </el-input>
                    </el-col>
                    <el-col :span="8">
                        角色：
                        <el-select v-model="roleValue" placeholder="请选择角色"
                                   @change="click2ChooseRole">
                            <el-option
                                v-for="role in roleOptions"
                                :key="role.index"
                                :label="role.label"
                                :value="role.index"
                            >
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="8">
                        激活状态：
                        <el-select v-model="statusValue" placeholder="是否激活"
                                   @change="click2ChooseStatus">
                            <el-option
                                v-for="status in statusOptions"
                                :key="status.index"
                                :label="status.label"
                                :value="status.index"
                            >
                            </el-option>
                        </el-select>
                    </el-col>
                </el-row>
                <el-row style="font-size: 20px;font-weight: bolder">
                    <el-col :span="3">学工号 </el-col>
                    <el-col :span="4">姓名</el-col>
                    <el-col :span="4">密码</el-col>
                    <el-col :span="3">激活状态 </el-col>
                    <el-col :span="3">角色</el-col>
                    <el-col :span="6">邮箱</el-col>
                    <el-col :span="1">删除</el-col>
                </el-row>
                <div v-for="(user,index) in showData" :key="index">
<!--                    <div v-if="user.name.indexOf(inputText)!=-1||user.u_id.indexOf(inputText)!=-1">-->
<!--                        <div v-if="((user.status+1===showStatus||showStatus===0)&&(user.role===showRole||showRole===0))">-->
                            <el-row>
                                <el-col :span="3"> {{user.u_id}} </el-col>
                                <el-col :span="4"> {{user.name}} </el-col>
                                <el-col :span="4">
                                    <el-input style="width: 80%;" type="password"
                                              v-model="user.password" show-password>
                                    </el-input>
                                </el-col>
                                <el-col :span="3"> {{statusList[user.status]}} </el-col>
                                <el-col :span="3"> {{roleList[user.role-1]}} </el-col>
                                <el-col :span="6">
                                    <span v-if="user.email">{{user.email}}</span>
                                    <span v-else>-----</span>
                                </el-col>
                                <el-col :span="1" style="text-align: center">
                                    <i class="el-icon-delete-solid" @click="deleteUser(index)"></i>
                                </el-col>
                            </el-row>
                        </div>
<!--                    </div>-->
<!--                </div>-->
            </el-form>
        </div>
        <div v-show="filteredUserList.length>pageSize" style="margin-top: -70px">
            <el-pagination
                style="text-align: center"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[30, 25, 20, 15, 9]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total=filteredUserList.length>
            </el-pagination>
        </div>

    </div>
</template>

<script>
export default {
    name: "UserList",
    data(){
        return{
            userList:[],
            filteredUserList:[],
            statusList:["未激活","已激活"],
            roleList:[" ", "教师"," ", "学生"],
            roleOptions: [
                {
                    index: 0,
                    label: '全部',
                },
                {
                    index: 1,
                    label: '教师',
                },
                {
                    index: 2,
                    label: '学生',

                },
            ],
            statusOptions:[
                {
                    index: 0,
                    label: '全部',
                },
                {
                    index: 1,
                    label: '未激活',
                },
                {
                    index: 2,
                    label: '已激活',
                },
            ],
            roleValue:0,
            statusValue:0,
            // 筛选
            inputText: '',
            showRole:0,
            showStatus:0,
            // 分页
            pageSize:9,
            currentPage: 1,
            showData:[]
        }
    },
    mounted () {
        this.showAllUser()
        this.getPageData()
    },
    methods:{
        search(){
            this.userFilter()
        },
        userFilter(){
            this.filteredUserList = []
            for(let i in this.userList){
                let user = this.userList[i]
                if(user.name.indexOf(this.inputText)!==-1||user.u_id.indexOf(this.inputText)!==-1)
                {
                    if((user.status+1)===this.showStatus||this.showStatus===0)
                    {
                        if(user.role===this.showRole||this.showRole===0)
                        {
                            this.filteredUserList.push(user)
                        }
                    }
                }
            }
            this.getPageData()
        },
        // 根据分页设置的数据控制每页显示的数据条数及页码跳转页面刷新
        async getPageData() {
            let start = (this.currentPage - 1) * this.pageSize;
            let end = start + this.pageSize;
            this.showData = this.filteredUserList.slice(start, end);
            console.log(this.showData.length)
        },
        // 分页自带的函数，当pageSize变化时会触发此函数
        handleSizeChange(val) {
            this.pageSize = val;
            this.getPageData();
        },
        // 分页自带函数，当currentPage变化时会触发此函数
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getPageData();
        },
        showAllUser(){
            this. axios({
                method:"get",
                url:"/api/admin/getAllUser",
            }).then(resp =>{
                console.log(resp.data)
                for (let i in resp.data){
                    const user =  {
                        u_id:resp.data[i].u_id,
                        name:resp.data[i].name,
                        password:resp.data[i].password,
                        status:resp.data[i].status,
                        role:resp.data[i].role,
                        email:resp.data[i].email
                    }
                    this.userList.push(user)
                    this.filteredUserList.push(user)
                }
                this.getPageData()
            })
        },
        showUser(key,f1,f2,pgn){
            console.log(key,f1,f2,pgn)

        },
        deleteUser(index){
            console.log('delete ' + index)

            this. axios({
                method:"delete",
                url:"/api/admin/deleteUser",
                params:{
                    u_id:this.userList[index].u_id
                }
            }).then(resp =>{
                //设置表格数据
                console.log(resp.data)
                let temp = []
                for (let i =0;i< this.userList.length;i++){
                    if(i!==index)
                    {
                        temp.push(this.userList[i])
                    }
                }
                this.userList = temp
            })
        },
        click2ChooseRole(value){
            console.log(value)
            this.roleOptions.forEach(item => {
                if(item.index===value){
                    console.log(item)
                    this.showRole=item.index
                }
            })
            this.userFilter()
        },
        click2ChooseStatus(value){
            console.log(value)
            this.statusOptions.forEach(item => {
                if(item.index===value){
                    console.log(item)
                    this.showStatus=item.index
                }
            })
            this.userFilter()
        },
    }
}
</script>

<style scoped>
.el-row{
    height: 50px;
    line-height: 40px;
    font-size: 18px;
    border: 1px solid #DCDFE6;
    margin-top:-1px
}
.el-col{
    /*border: 1px solid gray;*/
}
</style>
