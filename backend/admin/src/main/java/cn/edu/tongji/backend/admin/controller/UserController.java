package cn.edu.tongji.backend.admin.controller;

import cn.edu.tongji.backend.admin.pojo.User;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/postUserFile")
    public List<User> postUserFile(@RequestParam("id") int id,@RequestParam("file") MultipartFile[]  multipartFile) throws IOException {
        List<User> ids = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            System.out.println("file is " + file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String localFileName = System.currentTimeMillis() + fileSuffix;

            File excel = new File("C:\\Users\\ASUS\\Desktop\\s\\" + File.separator + localFileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), excel);
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！
            Workbook wb;
            //根据文件后缀（xls/xlsx）进行判断
            if ( "xls".equals(split[1])){
                FileInputStream fiStream = new FileInputStream(excel);   //文件流对象
                wb = new HSSFWorkbook(fiStream);
            }else{
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
                    int cIndex = firstCellIndex;
                    User user = new User();
                    user.setU_id(row.getCell(cIndex++).toString());
                    user.setName(row.getCell(cIndex++).toString());
                    user.setPassword(row.getCell(cIndex++).toString());
                    user.setStatus(Integer.parseInt(row.getCell(cIndex++).toString()));
                    user.setRole(Integer.parseInt(row.getCell(cIndex++).toString()));
                    System.out.println(user);
                    if(userService.findUser(user.getU_id())==null){
                        userService.addUser(user);
                    }else {
                        ids.add(user);
                    }
                }
            }

            wb.close();
            if(excel.delete()) {
                System.out.println(file.getName() + " is deleted!");
            }else {
                System.out.println("Delete operation is failed.");
            }
        }

        return ids;
    }
}
