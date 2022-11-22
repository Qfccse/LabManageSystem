<template>
    <div class="hello">
        <div>
           获取的 id:{{course.id}}
            <br/>
            获取的 name:{{course.name}}
        </div>
        <div>上传图片</div>
        <FileUploader list-type="picture-card" url="/api/test/uploadFile"></FileUploader>
        <div>上传文件</div>
        <FileUploader file-type="*" url="/api/test/uploadFile"></FileUploader>
        <div>下载</div>
        <el-button @click="download">点击下载</el-button>
    </div>
</template>

<script>
import FileUploader from "@/components/FileUploader";
export default {
    name: 'TEST',
    components: {FileUploader},
    data(){
        return{
            course:""
        }
    },
    mounted() {
        this.test()
    },
    methods:{
        test(){
            // restfull 风格
            // this. axios({
            //     method:"get",
            //     url:"/api/test/1",
            // }).then(resp =>{
            //     //设置表格数据
            //     console.log(resp.data)
            //     console.log(resp.data.id)
            // })

            // 半 restfull风格
            this. axios({
                method:"get",
                url:"/api/test/selectById",
                params:{
                    id:1
                }
            }).then(resp =>{
                //设置表格数据
                this.course = resp.data;
                console.log(resp.data)
                console.log(resp.data.id)
            })
        },
        download(){
            this. axios({
                method:"get",
                url:"/api/test/download",
                params:{
                    path:"C:/Users/ASUS/Desktop/s/1669044608648.png"
                },
                responseType: 'blob'
            }).then((res)=>{
                console.log('文件下载成功');
                const blob = new Blob([res.data]);
                const fileName = "1.png";

                //对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
                //IE10以上支持blob，但是依然不支持download
                if ('download' in document.createElement('a')) {
                    //支持a标签download的浏览器
                    const link = document.createElement('a');//创建a标签
                    link.download = fileName;//a标签添加属性
                    link.style.display = 'none';
                    link.href = URL.createObjectURL(blob);
                    document.body.appendChild(link);
                    link.click();//执行下载
                    URL.revokeObjectURL(link.href); //释放url
                    document.body.removeChild(link);//释放标签
                } else {
                    navigator.msSaveBlob(blob, fileName);
                }
            }).catch(error => {
                console.log(error);
                console.log('文件下载失败');
            })
        }
    }
}
</script>
