<template>
    <div style="width: 1150px;">
        <div style="min-height: 80px" class="exp_head">
            <el-input v-model="filterText"
                      style="width:200px;float:left;"
                      @input="filterFile"
                      placeholder="检索文件"></el-input>
            <el-button type="primary" style="float: left;margin-left: 20px" @click="filterFile">检索文件</el-button>
        </div>
        <div class="exp_body">
            <div class="exp_body_left">
                <el-tree :data="guidebookData"
                         :props="defaultProps"
                         accordion
                         ref="tree"
                         :highlight-current="true"
                         node-key="id"
                         :default-expanded-keys="treeExpandData"
                         :filter-node-method="filterNode"
                         @node-click="handleNodeClick">
                </el-tree>
            </div>
            <div class="exp_body_right">
                <div v-for="(content,index) in curNode" :key="index">
                    <div style="clear: both">
                        <div class="right_row" >
                            <div @click="click2Open(index)" style="float: left">
                                <div style="float: left">
                                    <i class="el-icon-folder" v-if="curNode[index].depth===2" style="font-size: 20px"></i>
                                    <i class="el-icon-document" v-if="curNode[index].depth===3" style="font-size: 20px"></i>
                                </div>
                                <div style="float: left">
                                    <span v-if="showNameInput!==index">{{content.label}}</span>
                                    <div  v-else>
                                        <el-input v-model="newNodeName" style="float: left;width: 350px;height: 30px"></el-input>
                                        <el-button style="float: left;margin-left: 20px;margin-top: 5px" type="small" icon="el-icon-close" @click="close(index)"></el-button>
                                        <el-button style="float: left;margin-left: 0;margin-top: 5px" type="small" icon="el-icon-check" @click="rename(content.id,index)"></el-button>
                                    </div>
                                </div>
                            </div>
                            <div v-if="curNode[index].depth===3"
                                 style="float: right">
                                <el-popover placement="top-start" width="100" trigger="click">
                                    <template v-slot:reference>
                                        <el-button style="border: none;height: 40px;width: 20px;text-align: center;">
                                            <i class="el-icon-more" style="transform:rotate(90deg);width: 20px;text-align: center;line-height: 40px;height: 20px"></i>
                                        </el-button>
                                    </template>
                                    <div class="pop_selector" @click="click2Download(index)">&emsp;下载</div>
                                    <div class="pop_selector" @click="click2Delete(content.id)">&emsp;删除</div>
                                    <div class="pop_selector" @click="click2Rename(index,content.label)">&emsp;重命名</div>
                                    <FileDownloader ref="download" v-show="false"
                                                    url="/api/guidebook/getGuidebookBytes"
                                                    :gid="content.id.toString()"
                                                    :file-name="content.label">
                                    </FileDownloader>
                                </el-popover>
                            </div>
                        </div>
                    </div>
                </div>
                <el-dialog :title="dialog.dialogTitle"
                           :visible="dialog.dialogVisible"
                           width="1050px"
                           :before-close="handleClose">
                    <PDFReader url="api/guidebook/getGuidebookBytes"
                               style="box-shadow:5px 5px 5px rgba(0,0,0,0.1) "
                               :gid="dialog.dialogGid"></PDFReader>
                </el-dialog>
            </div>
        </div>
    </div>
</template>

<script>
import FileDownloader from "@/components/Utils/FileDownloader";
import PDFReader from "@/components/Guidebook/PDFReader";
export default {
    name: "GuidebookList",
    components: {PDFReader, FileDownloader},
    data(){
        return{
            filterText:"",
            curNode: [],
            dialog:{
                dialogVisible:false,
                dialogTitle:"",
                dialogGid:-1
            },
            newNodeName:"",
            isRenaming:false,
            showNameInput:-1,
            curNodeBackUp:{},
            treeExpandData:[],
            guidebookData: [],
            defaultProps: {
                children: 'children',
                label: 'label',
            },
        }
    },
    props:{
        scope:{
            type:Number,
            default:0
        }
    },
    async mounted() {
        console.log("---------------")
        await this.getCourseLabs()
        this.curNode= this.guidebookData[0].children
        this.curNodeBackUp = this.curNode
        this.$nextTick().then(() =>{
            const firstNode = document.querySelector('.el-tree-node')
            firstNode.click();
        })
    },

    methods: {
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
        filterFile() {
            this.$refs.tree.filter(this.filterText)
            this.curNode = []
            if(this.filterText.length===0)
            {
                this.curNode = this.curNodeBackUp
                this.$nextTick().then(() =>{
                    const firstNode = document.querySelector('.el-tree-node')
                    firstNode.click();
                })
                return
            }
            for(let i in this.guidebookData[0].children){
                // if(this.guidebookData[0].children[i].label.indexOf(this.filterText) !== -1){
                //     // console.log(this.guidebookData[0].children[i].label)
                //     this.curNode.push(this.guidebookData[0].children[i])
                // }
                for(let j in this.guidebookData[0].children[i].children){
                    if(this.guidebookData[0].children[i].children[j].label.indexOf(this.filterText) !== -1){
                        // console.log(this.guidebookData[0].children[i].children[j].label)
                        this.curNode.push(this.guidebookData[0].children[i].children[j])
                    }
                }
            }
        },
        click2Open(index){
            console.log('index = '+index)
            console.log('depth = ' + this.curNode[index].depth)
            if(this.curNode[index].depth === 2)
            {
                this.curNode = this.guidebookData[0].children[index].children
                this.curNodeBackUp = this.curNode
            }
            else if(this.curNode[index].depth === 3&&this.isRenaming===false){
                console.log('阅读pdf')
                this.dialog.dialogTitle = this.curNode[index].label
                this.dialog.dialogVisible = true
                this.dialog.dialogGid = this.curNode[index].id
            }
            else {
                console.log('-1 ')
            }
        },
        handleNodeClick(data,node,obj) {
            // console.log("/////////")
            // console.log(data);
            console.log(node)
            console.log(obj)
            console.log("---------")
            for(let i in data.children){
                console.log(data.children[i].label)
            }
            this.curNode = data.children
            this.curNodeBackUp = this.curNode
        },
        handleClose(){
          this.dialog.dialogVisible = false
        },
        async getCourseLabs(){
            this.guidebookData.push(
                {
                    label: this.$store.state.c_name,
                    depth:1,
                    id:0,
                    children: []
                }
            )
            await this.axios({
                method:"get",
                url:"/api/laboratory/getCourseLabs",
                params:{
                    c_id: this.$route.query.c_id,
                }
            }).then( async (res)=>{
                console.log(res.data)
                for(let i in res.data){
                    let temp = {
                        label: res.data[i].name,
                        depth:2,
                        id:res.data[i].l_id,
                        children: [],
                    }
                    await this.getGuidebooks(res.data[i].l_id,temp)
                    this.guidebookData[0].children.push(temp)
                }
            })
        },
        async getGuidebooks(lid,temp){
            await this.axios({
                method:"get",
                url:"/api/guidebook/getLabGuidebooks",
                params:{
                    l_id:lid,
                }
            }).then((res)=>{
                console.log(res.data)
                for (let i in res.data){
                    temp.children.push(
                        {
                            label:res.data[i].name,
                            depth:3,
                            id:res.data[i].g_id
                        }
                    )
                }
            })
        },
        click2Download(index) {
            this.$refs.download[index].click2Download()
        },
        click2Rename(index,name){
            console.log(index)
            this.showNameInput = index
            this.newNodeName = name
            this.isRenaming = true
        },
        rename(gid,index){
            this.axios({
                method:"post",
                url:"api/guidebook/postGuidebookNewName",
                params:{
                    g_id:gid,
                    name:this.newNodeName
                }
            }).then((res)=>{
                console.log(res.data)
            })
            this.close(index)
        },
        close(index){
            this.curNode[index].label = this.newNodeName
            this.showNameInput = -1
            this.newNodeName = ""
            this.isRenaming = false
        },
        click2Delete(gid){
            console.log(gid)
            this.axios({
                method:"delete",
                url:"api/guidebook/deleteGuidebook",
                params:{
                    g_id:gid,
                }
            }).then((res)=>{
                console.log(res.data)
            })
        },
    }
}
</script>

<style scoped>
.exp_body{
    min-height: 500px;
    overflow: hidden;
}
.exp_body_left{
    border: 1px gray solid;
    width: 300px;
    float: left;
    min-height: 500px;
    overflow: hidden;
    font-size: 16px;
}
.exp_body_right{
    border: 1px gray solid;
    width: 700px;
    float: left;
    margin-left: 10px;
    min-height: 500px;
    overflow: hidden;
}
.right_row{
    height: 40px;
    line-height: 40px;
}
.pop_selector{
    height: 30px;
    line-height: 30px;
}
.pop_selector:hover{
    background-color: #eeeeee;
}
</style>
