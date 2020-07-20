package com.baiding.vue.controller;

import com.baiding.vue.model.Login;
import com.baiding.vue.model.Result;
import com.baiding.vue.model.po.User;
import com.baiding.vue.model.vo.UserParam;
import com.baiding.vue.model.vo.UserQueryCondition;
import com.baiding.vue.service.IndexService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: BaiDing
 * @Date: 2018/9/24 10:33
 * @Email: liujiabaiding@foxmail.com
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/userPage")
    public Result queryUserByPagination(UserQueryCondition condition, HttpServletRequest request){
        Object authorization = request.getHeader("token");
        System.out.println(authorization.toString());
        return Result.build(1,"用户分页信息",indexService.userPage(condition));
    }

    @GetMapping("/user/{id}")
    public Result getUser(@PathVariable("id") long id) {
        return Result.build(1,"用户信息",indexService.user(id));
    }

    @PutMapping("/editUser")
    public Result editUser(@RequestBody UserParam userParam){
        indexService.editUser(userParam);
        return Result.build(1,"编辑成功了");
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody UserParam userParam){
        indexService.addUser(userParam);
        return Result.build(1,"添加成功了");
    }

    @PutMapping("/userRemoved")
    public Result changeUserRemoved(@RequestBody UserParam userParam){
        indexService.changeUserRemoved(userParam.getId(), userParam.getRemoved());
        return Result.build(1,"修改成功了");
    }

    /**
     * 批量导出用户
     * @param condition
     * @param response
     */
    @PostMapping("/exportUser")
    public void exportUser(@RequestBody UserQueryCondition condition,HttpServletResponse response){
        XSSFWorkbook book = new XSSFWorkbook();
        try {
            List<UserParam> list = indexService.exportUser(condition);
            if (list != null && list.size() > 0) {
                XSSFSheet sheet = book.createSheet("mySheent");
                String[] vals = {"用户ID", "邮箱账号","昵称","年龄","性别","状态", "注册时间"};
                createExcel(sheet, 0, vals);
                for (int i = 0; i < list.size(); i++) {
                    UserParam entity = list.get(i);
                    String[] vals2 = new String[]{String.valueOf(entity.getId()), entity.getEmail(), entity.getName(), String.valueOf(entity.getAge()),
                        entity.getSex() == 0 ? "女":"男",entity.getRemoved() == 0 ? "启用":"禁用",entity.getRegisterDate()};
                    createExcel(sheet, i + 1, vals2);
                }

                book.write(generateResponseExcel("用户列表",response));
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 导入用户模板
     * @param response
     */
    @PostMapping("/importUserTemplate")
    public void importUserTemplate(HttpServletResponse response){
        XSSFWorkbook book = new XSSFWorkbook();
        try {
                XSSFSheet sheet = book.createSheet("mySheent");
                String[] vals = {"邮箱账号","昵称", "密码","年龄","性别","状态"};
                createExcel(sheet, 0, vals);
                String[] vals2 = new String[]{"111@123.com", "张三","password1234", String.valueOf(18),
                            "男","禁用"};
                createExcel(sheet, 1, vals2);
                book.write(generateResponseExcel("用户列表模板",response));
                book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 批量导入用户
     * @param file
     * @return
     */
    @PostMapping("/importUser")
    public Result importUser(MultipartFile file) {
        try {
            List<String> fieldList = new ArrayList<>();
            fieldList.add("email");
            fieldList.add("name");
            fieldList.add("password");
            fieldList.add("age");
            fieldList.add("sex");
            fieldList.add("removed");

            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            // 得到第一个sheet
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row ;
            List<UserParam> correctList=new ArrayList<>();
            int errorCount = 0;
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                boolean isRight = true;
                UserParam userParam = new UserParam();
                if (row != null) {
                    for (int j = 0; j < fieldList.size(); j++) {
                        String cellName = fieldList.get(j);
                        XSSFCell cell = row.getCell(j);

                        isRight = validateUser(isRight, cellName, userParam, cell);
                    }
                }
                if(isRight){
                    correctList.add(userParam);
                }else{
                    errorCount++;
                }
            }
            indexService.batchAddUser(correctList);
            return Result.build(1,"添加成功了"+correctList.size()+"条,"+"添加失败了"+errorCount+"条");
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"批量导入用户出异常了");
        }
    }

    /**
     * 简单模拟数据库验证密码
     * @param login
     * @return
     */
    @PostMapping("/user/login")
    public Result login(@RequestBody Login login){
        if(StringUtils.isEmpty(login.getEmail())){
            return Result.build(0,"邮箱账号不能为空");
        }
        User user = indexService.validateuser(login.getEmail(), login.getPassword());
        if(user == null){
            return Result.build(0,"用户名或者密码不正确");
        }else if(user.getRemoved() != 0){
            return Result.build(0,"该用户被禁止登录");
        }else{
            return Result.build(1,"登录成功",generateUserInfoFields(user));
        }
    }


    private Map<String,Object> generateUserInfoFields(User user){
        Map<String,Object> fields= new HashMap<>();
        fields.put("userId", user.getId());
        fields.put("token","sgdtdvbdjcgcjh122363467dgetdghd");
        fields.put("userName", user.getName());
        return fields;
    }

    /**
     * @param rowNo
     *            插入的数据的行号
     * @param vals
     *            插入的数据值
     */
    private void createExcel(XSSFSheet sheet, int rowNo, String[] vals)  {
        if (vals.length > 0) {
            XSSFRow row = sheet.createRow(rowNo);
            for (int i = 0; i < vals.length; i++) {
                String val = vals[i];
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(val);
            }
        }
    }

    /**
     * @param excelName
     *            要生成的文件名字
     * @return
     * @throws IOException
     */
    private ServletOutputStream generateResponseExcel(String excelName, HttpServletResponse response) throws IOException {
        excelName = excelName == null || "".equals(excelName) ? "excel" : URLEncoder.encode(excelName, "UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + excelName + ".xlsx");

        return response.getOutputStream();
    }

    private boolean validateUser(boolean isRight,String colName,UserParam userParam,XSSFCell cell){
        switch (colName){
            case "email":
                if(isRight){
                    String email = String.valueOf(cell);
                    if(email != null && !email.equals("")){
                        // 简单判断下邮箱账号
                        userParam.setEmail(email);
                    } else {
                        isRight = false;
                    }
                }
                break;
            case "name":
                if(isRight){
                    String name = String.valueOf(cell);
                    if(name != null && !name.equals("")){
                        userParam.setName(name);
                    } else {
                        isRight = false;
                    }
                }
                break;
            case "password":
                if(isRight){
                    String password = String.valueOf(cell);
                    if(password != null && !password.equals("")){
                        if(password.endsWith(".0")){
                            password = password.substring(0, password.indexOf(".0"));
                        }
                        userParam.setPassword(password);
                    } else {
                        isRight = false;
                    }
                }
                break;
            case "age":
                if(isRight){
                    String age = String.valueOf(cell);
                    if(age != null && !age.equals("")){
                        if(age.endsWith(".0")){
                            age = age.substring(0, age.indexOf(".0"));
                        }
                        try{
                            int ageNum =Integer.valueOf(age);
                            userParam.setAge(ageNum);
                        }catch(Exception e){
                            isRight = false;
                            e.printStackTrace();
                        }
                    }else {
                        isRight = false;
                    }
                }
                break;
            case "sex":
                if(isRight){
                    String sex = String.valueOf(cell);
                    if(sex != null && !sex.equals("")){
                        switch (sex) {
                            case "男":
                                userParam.setSex(1);
                                break;
                            case "女":
                                userParam.setSex(0);
                                break;
                            default:
                                isRight = false;
                                break;
                        }
                    } else {
                        isRight = false;
                    }
                }
                break;
            case "removed":
                if(isRight){
                    String removed = String.valueOf(cell);
                    if(removed != null && !removed.equals("")){
                        switch (removed) {
                            case "启用":
                                userParam.setRemoved(0);
                                break;
                            case "禁用":
                                userParam.setRemoved(1);
                                break;
                            default:
                                isRight = false;
                                break;
                        }
                    } else {
                        isRight = false;
                    }
                }
                break;

        }
        return isRight;
    }
}
