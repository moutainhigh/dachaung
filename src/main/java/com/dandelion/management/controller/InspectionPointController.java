package com.dandelion.management.controller;

import com.dandelion.management.bean.*;
import com.dandelion.management.service.InspectionPointService;
import com.dandelion.management.utils.Excel;
import com.dandelion.management.utils.Final;
import com.dandelion.management.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InspectionPointController {

    @Autowired
    InspectionPointService service;


    @RequestMapping(method = RequestMethod.GET,value = "/inspectionPoint/all")
    public ResponseResult<List<InspectionPoint>> getAllinspectionPoint() throws MyException {
        ResponseResult<List<InspectionPoint>> info = new ResponseResult<>();
        List<InspectionPoint> inspectionPoints = service.getAllInspectionPoint();
        if (inspectionPoints != null) {
            info.setData(inspectionPoints);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询数据失败");
        }

        return info;
    }
    @RequestMapping(method = RequestMethod.GET,value = "/inspectionPoint/select/{id}")
    public ResponseResult<InspectionPoint> getinspectionPointById(@PathVariable("id")Integer inspectionPointId) throws MyException {
        ResponseResult<InspectionPoint> info = new ResponseResult<>();
        InspectionPoint inspectionPoint = service.getInspectionPointInfoById(inspectionPointId);
        if (inspectionPoint != null){
            info.setData(inspectionPoint);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/inspectionPoint/delete/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable(value = "id") final Integer id) throws MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean b = service.deleteInspectionPointById(id);
        //boolean b = true;
        if (!b) {
            throw new MyException("删除失败");
        } else {
            info.setCode(ResponseResult.OK);
            info.setData(b);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/inspectionPoint/deletes")
    public ResponseResult<Boolean> deleteinspectionPointsByList(@RequestParam("lists[]") final List<Integer> lists) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean res = service.deleteByList(lists);
        if (res){
            info.setCode(Final.OK);
            info.setData(true);
        }else{
            throw new MyException("批量删除失败");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/inspectionPoint/create")
    public ResponseResult<Boolean> createinspectionPoint(@RequestBody InspectionPoint inspectionPoint) throws MyException{
        ResponseResult<Boolean> ResponseResult = new ResponseResult<>();
        Boolean b = service.createInspectionPoint(inspectionPoint);
        if(!b){
            throw  new MyException("创建项目失败");
        } else {
            ResponseResult.setCode(ResponseResult.OK);
            ResponseResult.setData(b);
        }
        return ResponseResult;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/inspectionPoint/update")
    public ResponseResult<Boolean> updateEvent(@RequestBody InspectionPoint inspectionPoint) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = service.updateInspectionPoint(inspectionPoint);
        if (!b) {
            throw new MyException("数据更新失败,请查看数据类型是否正确");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/inspectionPoint/select")
    public ResponseResult<List<InspectionPoint>> getEventInfoByOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<InspectionPoint>> info = new ResponseResult<>();
        List<InspectionPoint> inspectionPoints = service.getInspectionPointInfoByOptions(options);
        if (inspectionPoints.size() != 0){
            info.setData(inspectionPoints);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/inspectionPoints/uploadInfo")
    public ResponseResult<Boolean> getFileDataList(MultipartFile file, HttpServletRequest httpServletRequest) throws IOException, MyException, ParseException {
        ResponseResult<List<Object>> infoData = new ResponseResult<>();
        ResponseResult<Boolean> info = new ResponseResult<>();
        //上传文件
        boolean ishave = ImageUploadUtil.upload(file,httpServletRequest);
        if (ishave){
            //取值
            infoData = Excel.getExcelData(file,0);
            if(infoData.getData().isEmpty()||infoData.getData()==null){
                info.setMessage("获取数据失败！");
                info.setData(false);
                info.setCode(ResponseResult.ERROR);
            }
            //插入数据库
            for (int i = 0; i < infoData.getData().size(); i++) {
                InspectionPoint inspectionPoint = new InspectionPoint();
                HashMap<String,String> temp = (HashMap<String, String>) infoData.getData().get(i);
                inspectionPoint.setCompanyId(Double.valueOf(temp.get("建设单位id")).intValue());
                inspectionPoint.setProjectId(Double.valueOf(temp.get("隶属项目id")).intValue());
                inspectionPoint.setProjectId(Double.valueOf(temp.get("资产id")).intValue());
                inspectionPoint.setPointName(temp.get("巡检点"));
                inspectionPoint.setPointAddress(temp.get("地址"));
                inspectionPoint.setPointNumber(temp.get("巡查点编号").substring(0, temp.get("巡查点编号").indexOf(".")));
                inspectionPoint.setLongitude("".equals(temp.get("经度"))?null:Double.valueOf(temp.get("经度")));
                inspectionPoint.setLatitude("".equals(temp.get("纬度"))?null:Double.valueOf(temp.get("纬度")));
                inspectionPoint.setRemark(temp.get("备注"));
                service.createInspectionPoint(inspectionPoint);
            }
            info.setMessage("Success");
            info.setCode(ResponseResult.OK);
        }else{
            info.setMessage("Fail");
            info.setCode(ResponseResult.ERROR);
        }

        return  info;
    }

}
