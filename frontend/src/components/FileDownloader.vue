<template>
    <div>
        <el-button @click="download">下载文件</el-button>
    </div>
</template>

<script>
export default {
    name: "FileDownloader",
    props: {
        filePath:{
            type: String,
            default: ""
        },
    },
    data(){
        return{

        }
    },
    mounted() {
    },
    methods:{
        download(){
            this. axios({
                method:"get",
                url:"/api/test/download",
                params:{
                    path: this.filePath
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

<style scoped>

</style>
