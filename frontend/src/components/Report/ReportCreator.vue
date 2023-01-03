<template>
    <div>
        <el-row :gutter="20"  style="width: 1050px; border: 1px silver solid;margin: auto">
            <el-col :span="5">
                <h4>基础组件</h4>
                <draggable :options="dragOptions" v-model="compList" @end="end1" :move="onMove1">
                    <div v-for="(item, index) in compList" :key="index" class="comp-item pointer">
                        <i :class="item.icon"></i>
                        {{item.label}}
                    </div>
                </draggable>
                <el-button @click="saveTemp" style="margin-top: 200px">保存为模板</el-button>
            </el-col>
            <el-col :span="13" style="border-left:1px solid #DCDFE6;border-right:1px solid #DCDFE6">
                <el-row>
                    <el-col :span="12">
                        <h4>实验报告表单内容</h4>
                    </el-col>
                </el-row>
                <template v-if=" 0 == (formList &&formList.length)">
                    <div style="line-height: 50px;height: 50px;
                    border: 1px dashed #d9d9d9;
                    padding-left:20px;margin-top:10px">
                        拖动左边的组件进行自定义字段编辑
                    </div>
                </template>
                <draggable
                    style="height:calc(100vh - 220px);overflow-y:auto"
                    group="comp"
                    v-model="formList"
                    @start="start2"
                    @end="end2"
                    :move="onMove2" >
                    <div
                        class="temp-content"
                        @click="activeIndex=index"
                        :class="activeIndex==index?'temp-active':''"
                        v-for="(item, index) in formList"
                        :key="index"
                    >
                        <div style="padding:10px">
                            <el-row>
                                <el-col :span="20">
                                    <input
                                        placeholder="请输入字段标题，16个字内"
                                        maxlength="16"
                                        v-model="item.title"
                                        style="border:none;width:100%;outline: none;"
                                    />
                                </el-col>
                                <el-col :span="3" class="text-right pointer">
                                    <template v-if="activeIndex==index">
                                        <i class="el-icon-delete text-red" title="删除" @click.stop="delItem(index)"></i>
                                    </template>
                                </el-col>
                            </el-row>
                        </div>
                        <div>
                            <div>
                                <!-- 单选 -->
                                <template v-if="item.type=='image'">
                                    <el-upload
                                        action="http"
                                        list-type="picture-card"
                                        disabled
                                    >
                                        <i class="el-icon-plus"></i>
                                    </el-upload>
                                </template>
                                <template v-else>
                                    <el-input disabled :placeholder="item.placeholder?item.placeholder:'待填写者输入'"></el-input>
                                </template>
                            </div>
                        </div>
                    </div>
                </draggable>
            </el-col>
            <el-col :span="6" >
                <template v-if="formList&&formList.length>0">
                    <h4>
                        <i :class="formList[activeIndex].icon"></i>
                        {{formList[activeIndex].typeName}}
                    </h4>
                    <el-form style="margin-top:10px" label-suffix="：">
                        <el-form-item label="标题">
                            <el-input v-model="formList[activeIndex].title"></el-input>
                        </el-form-item>
                        <template
                            v-if="formList[activeIndex].type != 'image'">
                            <el-form-item label="提示文字">
                                <el-input v-model="formList[activeIndex].placeholder"></el-input>
                            </el-form-item>
                        </template>
                        <el-form-item label="必填">
                            <el-switch v-model="formList[activeIndex].required"></el-switch>
                        </el-form-item>
                    </el-form>
                </template>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import draggable from 'vuedraggable'
export default {
    name: 'ReportCreator',
    components: {
        draggable,
    },
    data() {
        return {
            activeIndex: 0, // 当前编辑的 index
            futureIndex: 0, // 插入的数组 index
            isRight: false, // 是否拖动到右边
            formList: [],
            dragOptions: { group: { name: 'comp', pull: 'clone' }, sort: true },
            compList: [
                {
                    label: '文本框',
                    title:'',
                    icon: 'el-icon-document-remove',
                    type: 'text',
                    content:'',
                },
                {
                    label: '上传图片',
                    title:'',
                    icon: 'el-icon-picture-outline',
                    type: 'image',
                    content:''
                },
            ],
        }
    },
    mounted() {
        this.renderForm(this.templateHtml)
    },
    watch: {
        templateHtml(newVal) {
            this.renderForm(newVal)
        }
    },
    methods: {
        saveTemp(){
            let dataList = []
            for (let i in this.formList){
                dataList.push(
                    {
                        l_id: this.$route.query.l_id,
                        t_id: this.$store.state.userInfo.id,
                        title:this.formList[i].title,
                        order:i,
                        placeholder:this.formList[i].placeholder,
                        required:this.formList[i].required,
                        type:this.formList[i].type,
                    }
                )
            }
            console.log(dataList)
            this. axios({
                method:"post",
                url:"/api/report/postReportTemplate",
                data: dataList ,
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then(resp =>{
                //设置表格数据
                console.log(resp.data)
                console.log(resp.data.id)
                this.$message.success("模板设置成功!")
            })

        },
        renderForm(newVal) {
            if (newVal == '[]' || newVal == '' || newVal == null) {
                this.formList = []
            } else {
                this.formList = JSON.parse(newVal)
            }
        },
        // 删除模板字段
        delItem(index) {
            this.formList.splice(index, 1)
            let leg = this.formList.length
            if (leg == index && leg > 0) {
                this.activeIndex = index - 1
            }
        },
        // 根据类型获取字段
        getFormByType(data) {
            let item = {
                label: data.label,
                required: false,
                type: data.type,
                icon: data.icon,
                typeName: data.label,
                placeholder: ''
            }
            return item
        },
        end1(e) {
            if (this.isRight) {
                let data = this.getFormByType(this.compList[e.oldIndex])
                this.formList.splice(this.futureIndex, 0, data)
                this.activeIndex = this.futureIndex
            }
        },
        start2(e) {e},
        end2(e) {
            // 判断移动的项目是否为编辑行
            if (e.oldIndex != e.newIndex && this.activeIndex == e.oldIndex) {
                this.activeIndex = e.newIndex
            }
        },
        onMove1(e, originalEvent) {
            originalEvent,
                this.futureIndex = e.draggedContext.futureIndex
            if (e.relatedContext.component.$attrs.group == 'comp') {
                this.isRight = true
            } else {
                this.isRight = false
            }
            return false
        },
        onMove2(e, originalEvent) {
            console.log(originalEvent)
            if(e.relatedContext.component.$attrs.group == 'comp') {
                return true
            } else {
                return false
            }
        }
    }
}
</script>

<style scoped>
</style>
