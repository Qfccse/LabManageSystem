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
    }
}
</script>
