<template>
    <div>
       <LabList></LabList>
    </div>
</template>

<script>
import LabList from "@/components/LabList";
export default {
    name: "LaboratoryPage",
    components: {LabList},
    props:{
        cid:{
            type: Number,
            default: 1
        },
    },
    data(){
      return{

      }
    },
    mounted() {
        this.getLabs();
    },
    methods:{
        getLab(){

        },

    }
}
</script>

<style scoped>
.el-row{
    font-size: 20px;
    height: 50px;
    line-height: 50px;
    width: 600px;
}

</style>
