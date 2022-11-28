<template>
    <div>
        <el-form>
            <el-row v-for="(guidebook,index) in guidebookList" :key="index">
                <el-col :span="20" @click="click2ShowGuidebook(index)">
                    {{guidebook.name}}
                </el-col>
                <el-col :span="4" @click="click2DownloadGuidebook(index)">
                    <FileDownloader url="/api/guidebook/getGuidebookBytes"
                                    :gid=guidebook.g_id
                                    :file-name=guidebook.name>
                    </FileDownloader>
                </el-col>
            </el-row>
        </el-form>


        <PDFReader
            url="/api/guidebook/getGuidebookBytes"
            :gid=choose
            v-if="show"
        >
        </PDFReader>
    </div>
</template>

<script>
import PDFReader from "@/components/PDFReader";
import FileDownloader from "@/components/FileDownloader";
export default {
    name: "GuidebookPage",
    components: {FileDownloader, PDFReader},
    data(){
        return{
            guidebookList:[],
            choose:1,
            show:false
        }
    },
    mounted() {
        this.showGuidebookList()
    },
    methods:{
        showGuidebookList(){
            this. axios({
                method:"get",
                url:"/api/guidebook/getLabGuidebooks",
                params:{
                    l_id:1
                }
            }).then(resp =>{
                console.log(resp.data)
                for (let i in resp.data){
                    this.guidebookList.push({
                        g_id:resp.data[i].g_id,
                        name:resp.data[i].name,
                    })
                }
            })
        },
        click2ShowGuidebook(index){
            this.choose = this.guidebookList[index].g_id
            this.show = true
        },
        click2DownloadGuidebook(index){
            this. axios({
                method:"get",
                url:"/api/guidebook/getGuidebookBytes",
                params:{
                    g_id: this.guidebookList[index].g_id
                },
                responseType: 'blob'
            }).then((res)=>{
                console.log('文件下载成功');
                const blob = new Blob([res.data]);
                const fileName = this.guidebookList[index].name;
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

        },
    }
}
</script>

<style scoped>
.el-row{
    height: 50px;
    line-height: 50px;
    font-size: 16px;
}
</style>
