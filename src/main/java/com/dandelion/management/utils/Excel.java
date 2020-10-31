package com.dandelion.management.utils;

import com.dandelion.management.bean.ResponseResult;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author: Sun Qingxin
 *  @Date: 2020/7/17 11:30
 *  @Description: 读取表格
 *  @Param: file 文件  header 头部所在行（0行算起）
 *  @Return: ResponseResult  message：提示信息  code ： OK/ERROR  data：表格数据
 */
public class Excel {

     public static ResponseResult<List<Object>> getExcelData(MultipartFile file, int header) {
        ResponseResult<List<Object>> result = new ResponseResult<>();
        if(file == null || file.isEmpty()) {
            result.setCode(Final.ERROR);
            result.setMessage("文件不存在");
            return result;
        }
        // 工作簿
        Workbook wbs = null;
        try{
            //暂时先写死
            File file1 = new File("D:\\image\\"+file.getOriginalFilename());
            wbs= WorkbookFactory.create(file1);
        }catch(IOException e){
            e.printStackTrace();
            result.setCode(Final.ERROR);
            result.setMessage("读取excel文件错误");
            return result;
        }
        if(wbs != null) {
            List<Object> sheetDataList = new ArrayList<>();

            if(wbs.getNumberOfSheets() == 0){
                result.setCode(Final.ERROR);
                result.setMessage("上传excel文件不存在工作表");
                return result;
            }
            Map<String, String> cellValueMap =null;
            //依次遍历每个sheet的数据
            for(int sheetNum=0; sheetNum < wbs.getNumberOfSheets(); sheetNum++){
                Sheet sheet = wbs.getSheetAt(sheetNum);
                if(sheet != null){
                    //总行数
                    int rowCnt = sheet.getLastRowNum();
                    //总行数小于header行(排除空数据)继续读下一张sheet数据
                    if(rowCnt < header+1){
                        continue;
                    }
                    //逐行获取Excel列数据
                    for(int rowNum = header+1; rowNum <= rowCnt; rowNum++){
                        //第header+1行开始读取数据
                        Row rowData = sheet.getRow(rowNum);
                        //剔除空行
                        if(rowData==null){
                            continue;
                        }
                        //读取每行的每列数据
                        cellValueMap = new HashMap<>(16);
                        for(int cellNum = 0; cellNum <= rowData.getLastCellNum()&&sheet.getRow(header).getCell(cellNum)!=null; cellNum++){
                            //字段名
                            String cellName =  sheet.getRow(header).getCell(cellNum).getStringCellValue();
                            //字段值
                            String cellValue = rowData.getCell(cellNum) ==  null ? "":getCellValue(rowData.getCell(cellNum)).trim();
                            cellValueMap.put(cellName, cellValue);
                        }
                        //每一个sheet
                        sheetDataList.add(cellValueMap);
                    }
                }
            }
            result.setCode(Final.OK);
            result.setMessage("成功");
            result.setData(sheetDataList);
        }
        return result;
    }


    /**
     *  @author: Sun Qingxin
     *  @Date: 2020/8/4 16:05
     *  @Description: 处理cell
     */
    private static String  getCellValue(Cell cell){
        String value="";
        switch (cell.getCellType().getCode()){
            case 0:value=cell.getNumericCellValue()+"";break;
            case 1:value=cell.getStringCellValue();break;
            case 2:value=cell.getCellFormula();break;
            case 4:value=cell.getBooleanCellValue()+"";break;
            default:break;
        }
        return value;
    }


}
