package com.component.attendance.controller;

import com.component.attendance.entity.UserGroup;
import com.component.attendance.entity.UserInfo;
import com.component.attendance.entity.WeekEnum;
import com.component.attendance.service.ExcelImportService;
import com.component.attendance.service.ExcelImportUserGroupService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author ZHZJ
 * @create 2022/11/19 21:58
 */

@RestController
@RequestMapping("/excel")
public class ExcelImportController {

    private static int year =2022;
    private static int month =10;

    @Resource
    private ExcelImportService excelImportService;

    @Resource
    private ExcelImportUserGroupService excelImportUserGroupService;

    @RequestMapping("/importData")
    public  List<UserInfo> createCode( HttpServletRequest request, HttpServletResponse response) {

        try{
            File file = new File("C:\\Users\\Windows\\Desktop\\10月货运站考勤表 .xlsx");
            InputStream is = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(is);
            for(int j=0;j<workbook.getNumberOfSheets();j++){
                List<UserInfo> users = new ArrayList<>();
                Sheet sheet = workbook.getSheetAt(j);
                for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if("NUMERIC".equals(getCellValue(row.getCell(0)))){
                        System.out.println(row.getCell(1));
                        //判断当月天数
                        int Days = getMonthLastDay(year,month);
                        for(int dates=1;dates<=Days;dates++){
                            UserInfo user = new UserInfo();
                            String value ="";
                            user.setIfEarlyWork(0);
                            user.setIfMiddleWork(0);
                            user.setIfNgintWork(0);
                            if("STRING".equals(getCellValue(row.getCell(dates+1)))){
                                 value =row.getCell(dates+1).getStringCellValue();
                                Collection stringList = java.util.Arrays.asList(value);
                                 value = string2Unicode(value);
                                 System.out.println(value);
                                 String[] a = value.split("\\\\u");

                                 for(int b=1;b<a.length;b++){
                                     if("221a".equals(a[b])){
                                         user.setIfEarlyWork(1);
                                     }
                                     if("25c7".equals(a[b])){
                                        user.setIfMidNgint(1);
                                     }
                                     if("5c0f".equals(a[b])){
                                         user.setIfMiddleWork(1);
                                     }
                                     if("5927".equals(a[b])){
                                         user.setIfNgintWork(1);
                                     }
                                     if("5e74".equals(a[b])){
                                         user.setIfAnnualLeave(1);
                                     }
                                     if("9694".equals(a[b])){
                                         user.setIfRest(1);
                                     }
                                     if("54".equals(a[b])){
                                         user.setIfCompassionateLeave(1);
                                     }
                                     if("653e".equals(a[b])){
                                         user.setIfRest(1);
                                     }
                                     if("25b3".equals(a[b])){
                                         user.setIfSickLeave(1);
                                     }
                                 }
                            }
                            if("NUMERIC".equals(getCellValue(row.getCell(dates+1)))){
                                user.setIfRest(1);
                            }
                            user.setUsername( row.getCell(1).toString().replace(" ", ""));
                            user.setWorkDate(LocalDateTime.of(year,month,dates,0,0,0));
//                            user.setWorkGroup(value);
                            users.add(user);
                        }

                    }
                }
                excelImportService.saveBatch(users);
            }

        }catch (Exception e){
            System.out.println(e);
            StackTraceElement ste =e.getStackTrace()[0];
            System.out.println(ste.getLineNumber());
        }


        return null;
    }

    @RequestMapping("/importUser")
    public Boolean importUser( HttpServletRequest request, HttpServletResponse response) {
        List<String> workGroupList = new ArrayList();
        workGroupList.add("雷霆班组");
        workGroupList.add("货运站");
        try{
            File file = new File("C:\\Users\\Windows\\Desktop\\班组.xls");
            InputStream is = new FileInputStream(file);
            Workbook workbook = new HSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            List<UserGroup> groups = new ArrayList<>();
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i*3-1);
                UserGroup userGroup = new UserGroup();
                if(row==null){
                    continue;
                }
                if(workGroupList.contains(row.getCell(2).getStringCellValue())){
                    Double doubleValueObject = new Double(row.getCell(0).getNumericCellValue());
                    userGroup.setIdentifierNo(doubleValueObject.intValue());
                    userGroup.setCompany(row.getCell(1).getStringCellValue());
                    userGroup.setDepartment(row.getCell(2).getStringCellValue());
                    userGroup.setNumberId(row.getCell(3).getStringCellValue());
                    userGroup.setUsername(row.getCell(4).getStringCellValue());
                    groups.add(userGroup);
                }
            }
            excelImportUserGroupService.saveBatch(groups);
        }catch (Exception e){
            System.out.println(e);
            StackTraceElement ste =e.getStackTrace()[0];
            System.out.println(ste.getLineNumber());
        }
      return true;
    }

    @RequestMapping("/exportExcel")
    public  void exportExcel( HttpServletRequest request, HttpServletResponse response) {
//
        Workbook wb = new HSSFWorkbook();
        //生成sheet
        Sheet sheet = wb.createSheet("第1页");
        Row row = null;
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//⽔平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //创建列
        Cell cell = null;

        row = sheet.createRow(0);
        cell =row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(cellStyle);
        cell =row.createCell(1);
        cell.setCellValue("单位");
        cell.setCellStyle(cellStyle);
        cell =row.createCell(2);
        cell.setCellValue("部门");
        cell.setCellStyle(cellStyle);
        cell =row.createCell(3);
        cell.setCellValue("证件号码");
        cell.setCellStyle(cellStyle);
        cell =row.createCell(4);
        cell.setCellValue("姓名");
        cell.setCellStyle(cellStyle);
        int dateNums = getMonthLastDay(year,month);
        for(int i =1;i<=dateNums;i++){
            cell =row.createCell(4+i);
            cell.setCellStyle(cellStyle);
            LocalDate localDate = LocalDate.of(year, month, i);
            String date = DateTimeFormatter.ofPattern("yyyy.MM.dd").format(localDate);
            cell.setCellValue(date);
        }
        row = sheet.createRow(1);
        for(int i =1;i<=dateNums;i++){
            cell =row.createCell(4+i);
            LocalDate localDate = LocalDate.of(year, month, i);
            cell.setCellValue(WeekEnum.getDesByCode(String.valueOf(localDate.getDayOfWeek())));
            cell.setCellStyle(cellStyle);
        }
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
        sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
        sheet.addMergedRegion(new CellRangeAddress(0,1,2,2));
        sheet.addMergedRegion(new CellRangeAddress(0,1,3,3));
        sheet.addMergedRegion(new CellRangeAddress(0,1,4,4));
        List<UserGroup> groupList = excelImportUserGroupService.list();
        int rownum =2;
        for(UserGroup userGroup:groupList){
            for(int a:Arrays.asList(1,2,3)){
                sheet.setDefaultColumnStyle(rownum-1,cellStyle);
                row = sheet.createRow(rownum);

                cell= row.createCell(0);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(userGroup.getIdentifierNo());

                cell=row.createCell(1);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(userGroup.getCompany());

                cell=row.createCell(2);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(userGroup.getDepartment());


                cell=row.createCell(3);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(userGroup.getNumberId());

                cell=row.createCell(4);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(userGroup.getUsername());

                List<UserInfo> users=  excelImportService.findUser(userGroup.getUsername());
                if(a==1){
                    for(int i=0;i<users.size();i++){
                        String firestValue ="";
                        if(users.get(i).getIfEarlyWork()==1){
                            firestValue = "白班";
                        }else{
                            if(users.get(i).getIfRest()==1){
                                firestValue = "休息";
                            }
                            if(users.get(i).getIfAnnualLeave()==1){
                                firestValue = "年假";
                            }
                            if(users.get(i).getIfSickLeave()==1){
                                firestValue = "病假";
                            }
                            if(users.get(i).getIfCompassionateLeave()==1){
                                firestValue = "调休";
                            }
                            if(users.get(i).getIfPublicErrand()==1){
                                firestValue = "公差";
                            }

                        }
                        row.createCell(5+i).setCellValue(firestValue);
                        cell.setCellStyle(cellStyle);
                    }
                }
                if(a==2){
                    for(int i=0;i<users.size();i++){
                        String secondValue = "";
                        if(users.get(i).getIfMidNgint()==1){
                            secondValue = "中夜班";
                        }
                        if(users.get(i).getIfMiddleWork()==1){
                            secondValue = "中班";
                        }
                        row.createCell(5+i).setCellValue(secondValue);

                        cell.setCellStyle(cellStyle);
                    }
                }
                if(a==3){
                    for(int i=0;i<users.size();i++){
                        row.createCell(5+i).setCellValue(users.get(i).getIfNgintWork()==1?"夜班":"");
                        cell.setCellStyle(cellStyle);
                    }
                }
                rownum++;
            }
            sheet.addMergedRegion(new CellRangeAddress(rownum-3,rownum-1,0,0));
            sheet.addMergedRegion(new CellRangeAddress(rownum-3,rownum-1,1,1));
            sheet.addMergedRegion(new CellRangeAddress(rownum-3,rownum-1,2,2));
            sheet.addMergedRegion(new CellRangeAddress(rownum-3,rownum-1,3,3));
            sheet.addMergedRegion(new CellRangeAddress(rownum-3,rownum-1,4,4));
        }
        String fileName = null;
        try {
            fileName = URLEncoder.encode("POIExcel下载测试","UTF-8");
            //浏览器默认服务器传过去的是html，不是excel文件
            //设置响应类型:传输内容是流，并支持中文
            response.setContentType("application/octet-stream;charset=UTF-8");
            //设置响应头信息header，下载时以文件附件下载
            response.setHeader("Content-Disposition","attachment;filename="+fileName+".xls");
            //输出流对象
            OutputStream os = response.getOutputStream();
            wb.write(os);
            //强制刷新
            os.flush();
            os.close();
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private String getCellValue(Cell cell){
        if(cell == null){
            return "";
        }
        return cell.getCellType().name();
    }
    public  int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }


}
