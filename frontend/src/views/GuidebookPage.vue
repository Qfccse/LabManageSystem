<template>
    <div style="width: 800px;margin: auto">
        <el-col :span="20">
            {{this.$route.query.name}}
        </el-col>
        <FileDownloader url="/api/guidebook/getGuidebookBytes"
                        :gid="this.$route.query.g_id"
                        :file-name="this.$route.query.name">
        </FileDownloader>
        <PDFReader
            url="/api/guidebook/getGuidebookBytes"
            :gid="Number(this.$route.query.g_id)"
        >
        </PDFReader>
    </div>
</template>

<script>
import PDFReader from "@/components/Guidebook/PDFReader";
import FileDownloader from "@/components/Utils/FileDownloader";
export default {
    name: "GuidebookPage",
    components: {FileDownloader, PDFReader},
    data(){
        return{
        }
    },
}
</script>

<style scoped>
.el-row{
    height: 50px;
    line-height: 50px;
    font-size: 16px;
}
</style>
