<template>
    <div class="hello">
        <h1>启动时调用selectById接口</h1>
        <el-upload
            :on-preview="handlePictureCardPreview"
            :disabled="upload.isUploading"
            :multiple="false"
            :on-change="handleFileChange"
            :before-remove="handleFileRemove"
            :auto-upload="false"
            :file-list="upload.fileList"
            :class="{ hide: uploadDisabled }"
            :show-file-list="true"
            list-type="picture-card"
            :limit="limit"
            :accept="fileType?fileType:'image/*'"
            action='https://jsonplaceholder.typicode.com/posts/'
            :on-success="handleUploadSuccess"
            :on-remove="handleRemove"
        >
           <i class="el-icon-plus"></i>
        </el-upload>
        <el-button @click="testFd()">点击测试上传文件的内容</el-button>
    </div>
</template>

<script>
export default {
    name: 'HelloWorld',
    props: {
        value: {
            type: Array,
            default: () => []
        },
        limit: {
            type: Number,
            default: 9
        },
        fileType: String,
    },
    data(){
        return{
            uploadDisabled:false,
            upload: {
                // 是否显示弹出层（用户导入）
                open:true,
                // 弹出层标题（用户导入）
                title: '',
                // 是否禁用上传
                isUploading: false,
                // 是否更新已经存在的用户数据
                updateSupport: 0,
                // 设置上传的请求头部
                // 上传的地址
                url: 'http://localhost:6001/api/column/uploadColumnContent',
                fileList: [],
                fileName: []
            }
        }
    },
    mounted() {
        // this.test()
    },
    methods:{
        test(){
            this. axios({
                method:"get",
                // url:"/api/testParam",
                // params: {
                //     id:1
                // },
                url:"/api/order/101",
            }).then(resp =>{
                //设置表格数据
                console.log(resp.data)
                console.log(resp.data.id)
            })
        },
        testFd(){
            let fd = new FormData()
            fd.append("id","1")
            for(let i in this.upload.fileList){
                fd.append('file',this.upload.fileList[i].raw)
            }
            this.axios({
                method:"post",
                url:"/api/test/uploadImage",
                data:fd,
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(resp =>{
                //设置表格数据
                console.log(resp.data)
                console.log(resp.data.id)
                this.upload.fileList=[]
            })
        },
        // 移除封面图
        handleRemove(file, fileList) {
            fileList,
                console.log(file);
            setTimeout(() => {
                this.uploadDisabled = false;
            }, 1000);
        },
        // 文件状态变化
        handleChange(file, fileList) { //上传成功与否都会触发
            this.uploadDisabled = fileList.length >= this.limit;
        },
        // 预览封面图
        handlePictureCardPreview(file) {
            this.form.dialogImageUrl = file.url;
            this.dialogVisible = true;
            this.uploadDisabled = true;
        },
        //上传成功
        handleUploadSuccess(file) {
            console.log('上传成')
            this.form.dialogImageUrl = file.result; //上传服务器成功
            this.uploadDisabled = true;
        },
        // 上传发生变化钩子
        handleFileChange(file, fileList) {
            this.upload.fileList = fileList;
        },
        // 删除之前钩子
        handleFileRemove(file, fileList) {
            this.upload.fileList = fileList;
        },
    }
}
</script>
