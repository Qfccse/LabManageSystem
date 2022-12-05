<template>
    <div>
        <el-button @click="click2Download">下载文件</el-button>
    </div>
</template>

<script>
export default {
    name: "FileDownloader",
    props: {
        url:{
            type: String,
            default: ""
        },
        gid:{
            type: String,
            default: "0"
        },
        fileName:{
            type: String,
            default: "0"
        },
    },
    data(){
        return{

        }
    },
    mounted() {
    },
    methods:{
        click2Download(){
            console.log("-------------下载中---------------")
            this. axios({
                method:"get",
                url:this.url,
                params:{
                    g_id: Number(this.gid)
                },
                responseType: 'blob'
            }).then((res)=>{
                console.log('文件下载成功');
                const blob = new Blob([res.data]);
                const fileName = this.fileName;

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
