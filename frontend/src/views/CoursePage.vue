<template>
    <div>
        <div class="canvas" style="margin: auto;width: 1200px">
            <div class="course_title clearbox"> 计算机组成原理实验</div>
            <div class="course_detail">
                <div class="fl show_border" style="width: 800px;margin-left: 20px">
                    <div class="course_intro">
                        <div class="sec_title fl">
                            <div class="_title">
                                课 程 简 介
                            </div>
                            <div class="_title_e">
                                Course Introduction
                            </div>
                        </div>
                        <div class="line fr"></div>
                        <div class="course_body clearbox">
                            &emsp;&emsp;计算机组成原理课程是计算机类专业主要的一门重要主干课程。设置这一课程的目的是，使学生了解和掌握计算机系统中
                            硬件部分的组成原理， 掌握计算机的基本工作原理，了解计算机各主要部件的硬件结构，相互联系和作用，从面对整个计算机系统
                            从硬件角度有完整的了解，为学习专业后继课程打下基础。
                            <br/><br/>
                            &emsp;&emsp;计算机组成原理课程主要介绍运用数字电路技术实现计算机部件的原理，以及计算机系统的工作原理。本课程的一个主要特点是综合性强，
                            需要运用多学科的知识，各章之间相互联系又相互独立。
                        </div>
                    </div>
                    <div class="course_labs">
                        <div class="sec_title fl">
                            <div class="_title">
                                课 程 实 验
                            </div>
                            <div class="_title_e">
                                Course Labs
                            </div>
                        </div>
                        <div class="line fr"></div>
                        <div class="lab_body clearbox">
                            <ul v-for="(lab,index) in labs" :key="index">
                                <li class="lab">
                                    实验（{{ index + 1 }}）{{lab.name}}
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="fl show_border" style="width: 250px;margin-left: 20px">
                    <div class="bulletin">
                        <div class="sec_title fl">
                            <div class="_title">课程公告</div>
                            <div class="_title_e">Course Bulletin</div>
                        </div>
                        <div class="line2 fr">
                            更多 >
                        </div>
                        <div class="todo clearbox">
                            <div class="todo_title">待办事项</div>
                            <div class="todo_body">
                                <ul v-for="(todo, index) in todos" :key="index">
                                    <li class="todo_item">
                                        <div style="font-size: 16px;">&emsp;<a href="#" style="">{{todo.name}}</a></div>
                                        <div>&emsp;{{todo.ddl}}</div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="feedback">
                            <div class="feedback_title">反馈事项</div>
                            <div class="feedback_body">
                                <ul v-for="(feedback, index) in feedbacks" :key="index">
                                    <li class="feedback_item">
                                       <div style="font-size: 16px">&emsp;{{feedback.name}}</div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
export default {
    name: "CoursePage",
    data(){
        return{
            labs:[
                {
                    name:"门电路逻辑功能及参数测试实验",
                },
                {
                    name:"译码器和数码显示管实验",
                },
                {
                    name:"数据选择器和加法器实验",
                },
                {
                    name:"触发器实验",
                },
            ],
            todos:[
                {
                    name:"实验4实验报告提交",
                    content:"请在本周末提交实验报告",
                    ddl:"12月16日23:59",
                },
                {
                    name:"阅读实验5实验指导书",
                    content:"请在本周末提交实验报告",
                    ddl:"12月18日23:59",
                },
            ],
            feedbacks:[
                {
                    name:"触发器相关",
                    content:"电路看不懂",
                    time:"2022/12/01 00:00:00",
                },
                {
                    name:"触发器相关",
                    content:"电路看不懂",
                    time:"2022/12/01 00:00:00",
                },
            ],
        }
    },

}
</script>

<style scoped>

ul, ol{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    list-style: none;
}
.clearbox{
    clear:both;
}
.show_border{
    /*border: 2px red solid;*/
}
.fl{
    float: left;
}
.fr{
    float: right;
}
.line{
    border-bottom: #B3C0D1 2px solid;
    width: 650px;
    height: 45px;
}
.line2{
    border-bottom: #B3C0D1 2px solid;
    width: 100px;
    height: 45px;
    text-align: right;
    font-size: 14px;
    line-height: 60px;
    color: lightskyblue;
    font-weight: bolder;
}
.canvas{
    background-color: ghostwhite;
    overflow: hidden;
}
.course_title{
    font-size: 36px;
    height: 100px;
    line-height: 100px;
}
.course_body{

}
.course_intro{
    margin-bottom: 50px;
}
.sec_title{
    margin-bottom: 20px;
}
._title{
    font-size: 28px;
    color: deepskyblue;
}
._title_e{
    font-size: 14px;
    color: limegreen;
}

.lab{
    height: 50px;
    margin-left: 10px;
}

.bulletin{
    margin-left: 20px;
}
.todo_title,
.feedback_title{
    font-size: 20px;
    height: 25px;
    line-height: 20px;
    margin-bottom: 10px;
    border-bottom: gray 2px solid;
}
.todo_body,
.feedback_body{
    margin-bottom: 10px;
    font-size: 14px;
    color: dimgray;
    border-radius: 10px;
}

.todo_item,
.feedback_item{
    line-height: 25px;
    height: 50px;
    margin-bottom: 10px;
}

</style>
