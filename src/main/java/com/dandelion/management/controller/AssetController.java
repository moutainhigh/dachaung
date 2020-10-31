package com.dandelion.management.controller;

import com.dandelion.management.bean.Asset;
import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.bean.Project;
import com.dandelion.management.service.AssetService;
import com.dandelion.management.utils.Excel;
import com.dandelion.management.utils.Final;
import com.dandelion.management.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;


/**
 *  @author: Sun Qingxin
 *  @Date: 2020/8/2 10:10
 *  @Description: 资产管理的Controller
 */
@RestController
public class AssetController {

    @Autowired
    private AssetService assetService;

    private ResponseResult<List<Asset>> getListResponseResult(ResponseResult<List<Asset>> info, List<Asset> assetList) {
        if(assetList != null){
            info.setData(assetList);
            info.setCode(Final.OK);
            info.setMessage("Success");
        }else{
            info.setCode(Final.ERROR);
            info.setMessage("Fail");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/asset/selectAll")
    public ResponseResult<List<Asset>> SelectAll () throws MyException{
        ResponseResult<List<Asset>> info = new ResponseResult<>();
        List<Asset> assetList = assetService.selectAll();
        return getListResponseResult(info, assetList);
    }

    @RequestMapping(method = RequestMethod.GET , value = "/asset/selectById")
    public ResponseResult<List<Asset>> SelectById(List<Integer> id)throws MyException{
        ResponseResult<List<Asset>> info = new ResponseResult<List<Asset>>();
        List<Asset> assetList = assetService.selectByID(id);
        return getListResponseResult(info, assetList);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/asset/select/{id}")
    public ResponseResult<Asset> getProjectById(@PathVariable("id")Integer assetId) throws MyException {
        ResponseResult<Asset> info = new ResponseResult<>();
        Asset asset = assetService.getAssetInfoById(assetId);
        if (asset != null){
            info.setData(asset);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/asset/selectByQuery")
    public ResponseResult<List<Asset>> SelectByQuery(@RequestParam Map<String,Object> options)throws MyException{
        ResponseResult<List<Asset>> info = new ResponseResult<List<Asset>>();
        List<Asset> assetList = assetService.selectByQuery(options);
        return getListResponseResult(info, assetList);
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/asset/deleteById")
    private ResponseResult<List<Asset>> DeleteById(@RequestParam("assetId") Integer id) throws MyException{
        ResponseResult<List<Asset>> info = new ResponseResult<List<Asset>>();
        boolean res = assetService.deleteByID(id);
        List<Asset> list = new ArrayList<>();
        if(res){
            info.setCode(Final.OK);
            info.setMessage("Success");
        }else {
            info.setCode(Final.ERROR);
            info.setMessage("Fail");
        }
        list.add(assetService.getById(id));
        info.setData(list);
        return info;
    }

    @RequestMapping(method = RequestMethod.POST , value = "/asset/deleteBatchIds")
    private ResponseResult<List<Asset>> DeleteBatchIds(@RequestParam("idList[]")List<Integer> idList) throws MyException{
        ResponseResult<List<Asset>> info = new ResponseResult<List<Asset>>();
        //存List data
        boolean res =  assetService.deleteBatchIds(idList);
        List<Asset> list = new ArrayList<>();

        if(res){
            info.setCode(Final.OK);
            info.setMessage("Success");
        }else {
            info.setCode(Final.ERROR);
            info.setMessage("Fail");
        }
        for (int i = 0; i < idList.size(); i++) {
            list.add(assetService.getById(idList.get(i)));
        }
        info.setData(list);
        return info;
    }

    @RequestMapping(method = RequestMethod.POST , value = "/asset/insert")
    private ResponseResult<List<Asset>> Insert(Asset asset) throws MyException{
        ResponseResult<List<Asset>> info = new ResponseResult<List<Asset>>();
        System.out.println("asset.getPurchaseDate()    "+asset.getPurchaseDate());
        System.out.println("asset.getWarrantyDays()    "+asset.getWarrantyDays());
        boolean res = assetService.insert(asset);
        List<Asset> list = new ArrayList<>();
        list.add(asset);
        if(res){
            info.setMessage("Success");
            info.setCode(Final.OK);

        }else{
            info.setCode(Final.ERROR);
            info.setMessage("Fail");
        }
        //返回插入的值
        info.setData(list);
        return info;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/asset/update")
    private ResponseResult<List<Asset>> Update(Asset asset) throws MyException{
        ResponseResult<List<Asset>> info = new ResponseResult<List<Asset>>();
        boolean res = assetService.update(asset);
        List<Asset> list = new ArrayList<>();
        list.add(asset);
        if(res){
            info.setMessage("Success");
            info.setCode(Final.OK);

        }else{
            info.setCode(Final.ERROR);
            info.setMessage("Fail");
        }
        //返回插入的值
        info.setData(list);
        return info;
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/asset/upload")
    private ResponseResult<Asset> getFileDataList(MultipartFile file, HttpServletRequest httpServletRequest) throws MyException, IOException {
        ResponseResult<List<Object>> infoData = new ResponseResult<>();
        ResponseResult<Asset> info = new ResponseResult<>();
        System.out.println("1111111=--------"+file.toString());
        //上传文件
        boolean ishave = ImageUploadUtil.upload(file,httpServletRequest);
        if (ishave){
            //取值
            infoData = Excel.getExcelData(file,0);
            if(infoData.getData().isEmpty()||infoData.getData()==null){
                info.setMessage("获取数据失败！");
                info.setCode(ResponseResult.ERROR);
            }
            //插入数据库
            for (int i = 0; i < infoData.getData().size(); i++) {
                Asset asset = new Asset();
                HashMap<String,String> temp = (HashMap<String, String>) infoData.getData().get(i);
                asset.setProjectId( Double.valueOf(temp.get("项目id")).intValue());
                asset.setAssetId(Double.valueOf(temp.get("资产id")).intValue());
                asset.setAssetNumber(temp.get("编号"));
                asset.setAssetName(temp.get("资产名称"));
                asset.setAssetBrand("品牌");
                asset.setAssetModel(temp.get("规格型号"));
                asset.setManufacturer(temp.get("厂商"));
                asset.setFactorySite(temp.get("厂址"));
                asset.setUnit(temp.get("计量单位"));
                asset.setQuantity(Double.valueOf(temp.get("数量")).intValue());
                asset.setUnitPrice(new BigDecimal(temp.get("单价")));
                asset.setAmount(new BigDecimal(temp.get("金额")));
                asset.setAssetType(temp.get("资产类型"));
                //后续更改
                Date date = new Date(temp.get("采购日期"));
                asset.setPurchaseDate(date);
                asset.setWayBuying(temp.get("采购渠道"));
                asset.setTypeBuying(temp.get("采购类型"));
                asset.setWarrantyType(temp.get("质保类型"));
                asset.setWarrantyLevel(temp.get("质保等级"));
                asset.setWarrantyDays(Double.valueOf(temp.get("质保天数")).intValue());
                Date date1 = new Date(temp.get("质保日期至"));
                asset.setWarrantyDateTo(date1);
                asset.setParameterDescription(temp.get("参数说明"));
                asset.setAssetState(temp.get("资产状态"));
                asset.setMaintenanceUnit(temp.get("运维单位id"));
                asset.setAssetCategory(temp.get("类别"));
                asset.setRemarks(temp.get("备注"));
                asset.setMaintainceState(temp.get("维修情况"));
                assetService.insert(asset);
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
