package cn.edu.tongji.backend.admin.controller;

import cn.edu.tongji.backend.admin.pojo.Student;
import cn.edu.tongji.backend.admin.pojo.Teacher;
import cn.edu.tongji.backend.admin.pojo.User;
import cn.edu.tongji.backend.admin.service.StudentService;
import cn.edu.tongji.backend.admin.service.TeacherService;
import cn.edu.tongji.backend.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/postTakesTeaches")
    public String postStuTakes(@RequestParam("id") int id,@RequestParam("file") MultipartFile[]  multipartFile) throws IOException {
        for (MultipartFile file : multipartFile) {
            System.out.println("file is " + file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String localFileName = System.currentTimeMillis() + fileSuffix;
            File excel = new File("D:\\TJ-LabResource\\tmp" + File.separator + localFileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), excel);
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！

            if("csv".equals(split[1])){
                InputStreamReader isr = new InputStreamReader(new FileInputStream(excel),"GBK");
                BufferedReader br  = new BufferedReader(isr);
                String data = br.readLine(); //第一行是列名，所以不读
                while ((data = br.readLine())!=null){
                    String[] userInfo = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    User user = new User();
                    user.setAttributeByIndex(0,userInfo[0]);
                    user.setAttributeByIndex(4,userInfo[1]);
                    user.setAttributeByIndex(6,userInfo[2]);
                    System.out.println(user);
                    insertT(user);
                }
                br.close();
                isr.close();
            }
            else {
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fiStream = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fiStream);
                }
                else{
                    wb = new XSSFWorkbook(new FileInputStream(excel));
                }
                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        User user = new User();
                        user.setAttributeByIndex(0,row.getCell(firstCellIndex).toString());
                        user.setAttributeByIndex(4,row.getCell(firstCellIndex +1).toString());
                        user.setAttributeByIndex(6,row.getCell(firstCellIndex + 2).toString()==null?"":row.getCell(firstCellIndex + 1).toString());
                        System.out.println(user);
                        insertT(user);
                    }
                }
                wb.close();
            }
            if(excel.delete()) {
                System.out.println(file.getName() + " is deleted!");
            }else {
                System.out.println("Delete operation is failed.");
            }
        }
        return null;
    }

    @PostMapping("/postUserFile")
    public List<User> postUserFile(@RequestParam("id") int id,@RequestParam("file") MultipartFile[]  multipartFile) throws IOException {
        System.out.println(id);

        List<User> ids = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            System.out.println("file is " + file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String localFileName = System.currentTimeMillis() + fileSuffix;
            File excel = new File("D:\\TJ-LabResource\\s" + File.separator + localFileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), excel);
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！

            if("csv".equals(split[1])){
                InputStreamReader isr = new InputStreamReader(new FileInputStream(excel),"GBK");
                BufferedReader br  = new BufferedReader(isr);
                String data = br.readLine(); //第一行是列名，所以不读
                while ((data = br.readLine())!=null){
                    System.out.println(data);
                    String[] userInfo = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    User user = new User();
                    for (int i = 0; i < userInfo.length; i++) {
                        user.setAttributeByIndex(i,userInfo[i]);
                    }
                    System.out.println(user);
                    if (!insertUser(user)){
                        ids.add(user);
                    }
                }
                br.close();
                isr.close();
            }
            else {
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fiStream = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fiStream);
                }
                else{
                    wb = new XSSFWorkbook(new FileInputStream(excel));
                }
                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        User user = new User();
                        for(int cIndex = firstCellIndex;cIndex<lastCellIndex;cIndex++){
                            if(row.getCell(cIndex)!=null){
                                //System.out.println(row.getCell(cIndex).toString());
                                user.setAttributeByIndex(cIndex,row.getCell(cIndex).toString());
                            }
                            else {
                                //System.out.println("null");
                                user.setAttributeByIndex(cIndex,"");
                            }
                        }
                        System.out.println(user);
                        if (!insertUser(user)){
                            ids.add(user);
                        }
                    }
                }
                wb.close();
            }

            if(excel.delete()) {
                System.out.println(file.getName() + " is deleted!");
            }else {
                System.out.println("Delete operation is failed.");
            }
        }

        return ids;
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.selectAllUser();
    }

    @GetMapping("/getCourseStudent")
    public List<Student> getCourseStudent(@RequestParam("c_id")int c_id){
        return studentService.selectCourseStudent(c_id);
    }

    @GetMapping("/getCourseTeacher")
    public List<Teacher> getCourseTeacher(@RequestParam("name")String name){
        return teacherService.selectCourseTeacher(name);
    }

    @PostMapping("/postUpdateTakesRole")
    public void postToUpdateTakes(@RequestParam("s_id")String s_id,@RequestParam("c_id")int c_id,@RequestParam("role")int role){
        studentService.updateTakesRole(s_id, c_id, role);
    }

    @PostMapping("postUpdateTeachesRole")
    public void postToUpdateTeaches(@RequestParam("t_id")String t_id,@RequestParam("c_id")int c_id,@RequestParam("role")int role){
        teacherService.updateTeachesRole(t_id, c_id, role);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("u_id") String u_id){
        User user = userService.findUser(u_id);
        if(user!=null){
            userService.deleteUserById(u_id);
            if(user.getRole()<=2){
                teacherService.deleteTeacherById(u_id);
            }
            else {
                studentService.deleteStudentById(u_id);
            }
        }

        return null;
    }

    public boolean insertUser(User user){
        if(userService.findUser(user.getU_id())==null){
            userService.addUser(user);
            // 加入老师表
            // 暂时没考虑教师、学生中已有对应学号
            if(user.getRole()<=2){
                Teacher teacher = new Teacher();
                teacher.setT_id(user.getU_id());
                teacher.setName(user.getName());
                teacherService.addTeacher(teacher);
            }
            else {
                Student student = new Student();
                student.setS_id(user.getU_id());
                student.setName(user.getName());
                studentService.addStudent(student);
            }
            //String cidList =  user.getCidList();
            //if (cidList!=null&&!cidList.isEmpty()){
            //    String[] cidSplit = cidList.split(",");;
            //    for (String cid : cidSplit) {
            //        //System.out.println(cid);
            //        int role = user.getRole();
            //        if(role>=3){
            //            //System.out.println("加入学生");
            //            studentService.addTakes(user.getU_id(),Integer.parseInt(cid),role);
            //        }
            //        else {
            //            //System.out.println("加入老师");
            //            teacherService.addTeaches(user.getU_id(),Integer.parseInt(cid),role);
            //        }
            //    }
            //}
            insertT(user);
            return true;
        }
        else {
            return false;
        }
    }

    public void insertT(User user){
        String cidList =  user.getCidList();
        boolean flag = true;
        if (cidList!=null&&!cidList.isEmpty()){
            String[] cidSplit = cidList.split(",");;
            for (String cid : cidSplit) {
                int role = user.getRole();
                if(role>=3){
                    //System.out.println("加入学生");
                    studentService.addTakes(user.getU_id(),Integer.parseInt(cid),role);
                }
                else {
                    //System.out.println("加入老师");
                    teacherService.addTeaches(user.getU_id(),Integer.parseInt(cid),role);
                }
            }
        }
    }
}
