package com.dandelion.management.controller;

import com.dandelion.management.bean.Contract;
import com.dandelion.management.bean.ResponseResult;
import com.dandelion.management.bean.Event;
import com.dandelion.management.bean.MyException;
import com.dandelion.management.service.ContractService;
import com.dandelion.management.utils.FileUploadUtil;
import com.dandelion.management.utils.Final;
import com.dandelion.management.utils.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/contacts")
public class ContractController {

    @Autowired
    ContractService contractService;


    @RequestMapping(method = RequestMethod.GET, value = "/contracts/show")
    public ResponseResult<List<Contract>> getAllContracts() throws MyException {
        List<Contract> contracts = contractService.getAllContracts();
        ResponseResult<List<Contract>> info = new ResponseResult<>();
        if (contracts.size() == 0) {
            throw new MyException("查询失败");
        } else {
            info.setCode(ResponseResult.OK);
            info.setData(contracts);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contracts/updateWithFile")
    public ResponseResult<Boolean> update(@RequestParam Map<String,String> info, @RequestParam("file")MultipartFile file) throws MyException, ParseException, IOException {
        ResponseResult<Boolean> ResponseResult = new ResponseResult<>();

        Contract contract = new Contract();
        contract.setContractId(Integer.valueOf(info.get("contractId")));
        contract.setProjectId(Integer.valueOf(info.get("projectId")));
        contract.setContractNo(info.get("contractNo"));
        contract.setContractName(info.get("contractName"));
        contract.setPartyA(info.get("partyA"));
        contract.setPartyB(info.get("partyB"));
        contract.setAmount("".equals(info.get("amount"))?null:new BigDecimal(info.get("amount")));
        contract.setStartDate("".equals(info.get("startDate"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(info.get("startDate")));
        contract.setSigningDate("".equals(info.get("signingDate"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(info.get("signingDate")));
        contract.setDeadline("".equals(info.get("deadline"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(info.get("deadline")));
        contract.setContractIntroduction(info.get("contractIntroduction"));
        contract.setTypeContract(info.get("typeContract"));
        contract.setRemarks(info.get("remarks"));
        Boolean b = contractService.updateContract(contract);
        String fileName = contract.getContractId().toString();
        if (!b) {
            throw new MyException("数据更新失败,请查看数据类型是否正确");
        } else {
            boolean ishave = FileUploadUtil.upload(file,"contract",fileName);
            if (ishave){
                ResponseResult.setCode(Final.OK);
                ResponseResult.setData(true);
            } else {
                ResponseResult.setCode(Final.ERROR);
                ResponseResult.setMessage("Fail");
                ResponseResult.setData(false);
            }
        }
        return ResponseResult;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contracts/update")
    public ResponseResult<Boolean> update(@RequestBody Contract contract) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean b = contractService.updateContract(contract);
        if (!b) {
            throw new MyException("数据更新失败,请查看数据类型是否正确");
        } else {
            info.setCode(Final.OK);
            info.setData(true);
        }
        return info;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/contracts/select/{id}")
    public ResponseResult<Contract> getContractById(@PathVariable("id")Integer contractId) throws MyException {
        ResponseResult<Contract> info = new ResponseResult<>();
        Contract contract = contractService.getContractInfoById(contractId);
        if (contract != null){
            info.setData(contract);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }


    /**
     * 通过id删除  合同删除时 项目级联删除
     * @param id
     * @return
     * @throws MyException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/contracts/delete/{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable(value = "id") Integer id) throws MyException {
        ResponseResult<Boolean> info = new ResponseResult<>();
        Boolean b = contractService.deleteContract(id);
        //boolean b = true;
        if (!b) {
            throw new MyException("删除失败");
        } else {
            info.setCode(ResponseResult.OK);
            info.setData(b);
        }
        return info;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/contracts/deletes")
    public ResponseResult<Boolean> deleteContractsByList(@RequestParam("lists[]") final List<Integer> lists) throws MyException {
        final ResponseResult<Boolean> info = new ResponseResult<>();
        final Boolean res = contractService.deleteByList(lists);
        if (res){
            info.setCode(Final.OK);
            info.setData(true);
        }else{
            throw new MyException("批量删除失败");
        }
        return info;
    }
    /**
     * 合同创建  用户创建  未完成
     * @return 是否创建成功
     * @throws MyException
     */
    @RequestMapping(method = RequestMethod.POST,value = "/contracts/createWithFile")
    public ResponseResult<Boolean> createContrect(@RequestParam Map<String,String> info, @RequestParam("file")MultipartFile file) throws MyException, IOException, ParseException {
        ResponseResult<Boolean> ResponseResult = new ResponseResult<>();

        Contract contract = new Contract();
        contract.setProjectId(Integer.valueOf(info.get("projectId")));
        contract.setContractNo(info.get("contractNo"));
        contract.setContractName(info.get("contractName"));
        contract.setPartyA(info.get("partyA"));
        contract.setPartyB(info.get("partyB"));
        contract.setAmount("".equals(info.get("amount"))?null:new BigDecimal(info.get("amount")));
        contract.setStartDate("".equals(info.get("startDate"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(info.get("startDate")));
        contract.setSigningDate("".equals(info.get("signingDate"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(info.get("signingDate")));
        contract.setDeadline("".equals(info.get("deadline"))?null:new SimpleDateFormat("yyyy-MM-dd").parse(info.get("deadline")));
        contract.setContractIntroduction(info.get("contractIntroduction"));
        contract.setTypeContract(info.get("typeContract"));
        contract.setRemarks(info.get("remarks"));

        Boolean b = contractService.createContract(contract);
        String fileName = contract.getContractId().toString();
        if (!b) {
            throw new MyException("创建合同失败");
        } else {
            //创建文件
            boolean ishave = FileUploadUtil.upload(file,"contract",fileName);

            if (ishave){
                ResponseResult.setCode(Final.OK);
                ResponseResult.setData(true);
            } else {
                ResponseResult.setCode(Final.ERROR);
                ResponseResult.setMessage("Fail");
                ResponseResult.setData(false);
            }
        }
        return ResponseResult;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/contracts/create")
    public ResponseResult<Boolean> createContrect(@RequestBody Contract contract) throws MyException {
        ResponseResult<Boolean> ResponseResult = new ResponseResult<>();
        Boolean b = contractService.createContract(contract);
        if (!b) {
            throw new MyException("创建合同失败");
        } else {
            ResponseResult.setCode(ResponseResult.OK);
            ResponseResult.setData(b);
        }
        return ResponseResult;
    }
    //搜索选择合同
    @RequestMapping(method = RequestMethod.GET,value = "/contracts/select")
    public ResponseResult<List<Contract>> getEventInfoByOptions(@RequestParam Map<String,Object> options) throws MyException {
        ResponseResult<List<Contract>> info = new ResponseResult<>();
        List<Contract> contractInfo = contractService.getContractInfoByOptions(options);
        if (contractInfo.size() != 0){
            info.setData(contractInfo);
            info.setCode(Final.OK);
        }else{
            throw new MyException("查询结果为空");
        }
        return info;
    }

}
