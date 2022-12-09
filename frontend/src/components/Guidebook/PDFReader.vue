<template>
    <div class="hello"  >
        <pdf class="pdf_board"
             ref="pdf"
             :src="pdfUrl"
             v-for="i in numPages"
             :key="i"
             :page="i"
        >
        </pdf>
    </div>
</template>

<script>
import pdf from "vue-pdf"
export default {
    name: 'PDFReader',
    components:{
        pdf
    },
    props:{
        url:{
            type: String,
            default: ""
        },
        gid:{
            type: Number,
            default: 0
        },
    },

    data(){
        return{
            pdfUrl:"",
            numPages:null
        }
    },
    mounted() {
       this.reader()
    },
    methods:{
        reader(){
            this.pdfBytesReader()
            setTimeout(this.getNumPages,1000)
        },
        pdfBytesReader(){
            this. axios({
                method:"get",
                url:this.url,
                params:{
                    g_id: this.gid
                },
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
                responseType: 'arraybuffer',
            }).then((res)=>{
                console.log('gid' + this.gid + 'pdf 传输成功');
                this.pdfSrc = new Blob([res.data]);
                const binaryData = [];
                binaryData.push(res.data);
                //获取blob链接 转化为URL
                this.pdfUrl = window.URL.createObjectURL(
                    new Blob(binaryData, { type: "application/pdf" })
                )
            }).catch(error => {
                console.log(error);
                console.log('pdf 加载失败');
            })
        },
        // pdf 翻页
        getNumPages() {
            let loadingTask = pdf.createLoadingTask(this.pdfUrl)
            loadingTask.promise.then(pdf => {
                this.numPages = pdf.numPages
            }).catch(err => {
                console.error('pdf 加载失败', err);
            })
        },
    }
}
</script>

<style scoped>
.pdf_board{
    width: 800px;
}
</style>
