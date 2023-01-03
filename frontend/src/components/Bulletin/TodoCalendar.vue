<template>
    <div style="width: 1150px;">
        <el-calendar ref="calendar">
            <template #dateCell="{data}">
                <div class="cell_date">
                    {{data.day.split('-')[2]}}
                </div>
                <div class="cell_todo">
                    <div v-for="(todo,index) in todoFilter(data)" :key="index">
                        <div class="todo_item" v-if="pastTodo(todo.end_time)">
                            <i class="el-icon-edit" v-if="todo.type===2"></i>
                            <i class="el-icon-position" v-else></i>
                            {{ todo.name }}
                        </div>
                        <div class="todo_item_past" v-else>
                            <i class="el-icon-edit" v-if="todo.type===2"></i>
                            <i class="el-icon-position" v-else></i>
                            {{ todo.name }}
                        </div>
                    </div>
                </div>
            </template>
        </el-calendar>
    </div>
</template>
<script>
export default {
    name: "TodoCalendar",
    data(){
        return{
            calendarDay:new Date(),
            calendar:{
                u_id:"",
                start_time:new Date(),
                end_time:new Date(),
            },
            todoList:[],
        }
    },
    mounted() {
        // this.$el.querySelector('')
        this.addMethod()
        // this.getCalendar()
    },
    watch:{

    },
    methods: {
        addMethod(){
            this.$nextTick(() => {
                // 点击上个月
                let prevBtn1 = document.querySelector('.el-calendar__button-group .el-button-group>button:nth-child(1)');
                prevBtn1.addEventListener('click',() => {
                    console.log('上个月');
                    this.minusMonth()
                    // this.getCalendar()
                })
                // 点击今天
                let prevBtn2 = document.querySelector('.el-calendar__button-group .el-button-group>button:nth-child(2)');
                prevBtn2.addEventListener('click',() => {
                    this.calendarDay = new Date()
                    // this.getCalendar()
                })
                // 点击下个月
                let prevBtn3 = document.querySelector('.el-calendar__button-group .el-button-group>button:nth-child(3)');
                prevBtn3.addEventListener('click',() => {
                    console.log('下个月');
                    this.addMonths()
                    // this.getCalendar()
                })
            })
        },
        todoFilter(date){
            let todoDailyList = []
            for (let i in this.todoList){
                let day = this.todoList[i].end_time.getDate().toString()
                if(day.length < 2){
                    day = "0" + day
                }
                if(date.day.toString().split('-')[2] === day)
                {
                    todoDailyList.push(this.todoList[i])
                }
            }
            return todoDailyList
        },
        getCalendar() {
            let curDay = new Date(this.calendarDay)
            // console.info('curDay..........:', curDay)
            let firstDay = new Date(curDay)
            firstDay.setDate(1)
            firstDay.setHours(0)
            firstDay.setMinutes(0)
            firstDay.setSeconds(0)
            // console.info('firstDay..........:', firstDay)
            let endDay = new Date(curDay)
            endDay.setMonth(curDay.getMonth() + 1)
            endDay.setDate(0)
            endDay.setHours(23)
            endDay.setMinutes(59)
            endDay.setSeconds(59)
            // console.info('endDay..........:', endDay)

            this.calendar.start_time = new Date(firstDay)
            this.calendar.end_time = new Date(endDay)
            this.calendar.u_id = this.$store.state.userInfo.id
            let i_url = "/api/bulletin/getStudentCalendar"
            // 如果是老师那么 url换成/api/bulletin/getTeacherCalendar
            if(this.$store.state.userInfo.role ==='teacher')
            {
                i_url = "/api/bulletin/getTeacherCalendar"
            }
            this.axios({
                method: "post",
                url: i_url,
                data: this.calendar,
                headers: {
                    'Content-Type': 'application/json'
                },
            }).then((resp) => {
                console.log(resp.data);
                this.todoList = []
                for (let i in resp.data) {
                   this.todoList.push({
                       name: resp.data[i].name,
                       end_time: new Date(resp.data[i].end_time),
                       type:resp.data[i].type
                   })
                }
            });
        },
        pastTodo(ed){
            return ed >= new Date();
        },
        //月份加减
        addMonths(){
            let currentDay =this.calendarDay
            let month = currentDay.getMonth()
            console.log(month)
            console.log(currentDay.getFullYear())
            if(month===11){
                this.calendarDay.setMonth(0)
                this.calendarDay.setYear(currentDay.getFullYear()+1)
            }
            else {
                this.calendarDay.setMonth(currentDay.getMonth()+1)
            }
            console.log(this.calendarDay)
        },
        minusMonth(){
            let currentDay = this.calendarDay
            let month = currentDay.getMonth()
            console.log(month)
            console.log(currentDay.getFullYear())
            if(month===0){
                this.calendarDay.setYear(currentDay.getFullYear()-1)
                this.calendarDay.setMonth(11)
            }
            else {
                this.calendarDay.setMonth(currentDay.getMonth()-1)

            }
            console.log(this.calendarDay)
        }
    }
}
</script>

<style scoped>
.cell_date{
    /*text-align: center;*/
    height: 15px;
    line-height: 15px;
}
.cell_todo{
    height: 105px;
    overflow-y: auto;
    overflow-x: hidden;
}
.todo_item{
    background-color: #1593f9;
    margin-top: 2px;
    border-radius: 5px;
    height: 25px;
    line-height: 25px;
    color: white;
    width: 140px;
    overflow: hidden;
}
.todo_item_past{
    background-color: #aaaaaa;
    margin-top: 2px;
    border-radius: 5px;
    height: 25px;
    line-height: 25px;
    color: white;
    width: 140px;
    overflow: hidden;
    text-decoration:line-through;
    text-decoration-color: coral;
}
/deep/  .el-calendar-table .el-calendar-day{
    height: 135px;
}
/deep/ .el-calendar-table:not(.is-range) td.next{/*隐藏下个月的日期*/
    visibility:hidden;
}

/deep/ .el-calendar-table:not(.is-range) td.prev{/*隐藏上个月的日期*/
    visibility: hidden;
}
</style>
