<template>
    <div>
        <div style="width: 800px;margin: auto">
            <h1>{{ this.$route.query.l_name }}实验报告</h1>
            <ReportFiller
                :lid="this.$route.query.l_id"
                :sid="this.$route.query.s_id">
            </ReportFiller>
        </div>

    </div>
</template>

<script>
import ReportFiller from "@/components/Report/ReportFiller";
export default {
    name: "ReportPage",
    components: {ReportFiller}
}
</script>

<style scoped>

</style>
