<template>
    <div class="test">
        <div v-if="false">
            <div style="margin-top: 50px" @click="test">普通接口测试</div>
            <div>
                获取的 id:{{course.id}}
                <br/>
                获取的 name:{{course.name}}
            </div>

            <div style="margin-top: 50px">上传图片</div>
            <FileUploader list-type="picture-card" url="/api/test/uploadFile"></FileUploader>

            <div style="margin-top: 50px">上传文件</div>
            <FileUploader file-type="*" url="/api/test/uploadFile"></FileUploader>

            <div style="margin-top: 50px">下载</div>
            <FileDownloader
                file-path="C:/Users/ASUS/Desktop/s/1669044608648.png"
            >
            </FileDownloader>

            <div style="margin-top: 50px">pdf阅读</div>
            <PDFReader
                file-path="C:/Users/ASUS/Desktop/s/1.pdf"
            >
            </PDFReader>

            <div style="margin-top: 50px">报告表单</div>
            <ReportCreator></ReportCreator>
            <ReportFiller></ReportFiller>
            <ReportReader></ReportReader>
        </div>
        <ReportReader></ReportReader>
    </div>
</template>

<script>
import FileUploader from "@/components/FileUploader";
import FileDownloader from "@/components/FileDownloader";
import PDFReader from "@/components/PDFReader";
import ReportCreator from "@/components/ReportCreator";
import ReportFiller from "@/components/ReportFiller";
import ReportReader from "@/components/ReportReader";
export default {
    name: 'TEST',
    components: {ReportReader, ReportFiller, ReportCreator, PDFReader, FileDownloader, FileUploader},
    data(){
        return{
            course:"",
            id:3
        }
    },
    mounted() {
        // this.test()
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
        testUp(){
            this.$refs.saveImage.fileUpload()
        }
    }
}
</script>

<style scoped>
.test{
    margin: auto;
    width: 80%;
}
</style>
