package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandelion.management.bean.Contract;
import com.dandelion.management.mapper.ContractMapper;
import com.dandelion.management.service.ContractService;
import com.dandelion.management.utils.Final;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {

    private static final String CONTRACT_NO = "contractNo";

    private static final String CONTRACT_NAME = "contractName";

    private static final String TYPE_CONTRACT = "typeContract";

    private static final String SIGNING_DATE = "signingDate";

    /**
     * 未删除
     */
    private static final Integer UNDELETE = 0;
    /**
     * 未更新
     */
    private static final Integer UNUPDATE = 0;
    /**
     * 未插入
     */
    private static final Integer UNINSERT = 0;

    @Autowired
    ContractMapper mapper;


    /**
     * 查询所有合同  未完成
     * @return
     */

    @Override
    public List<Contract> getAllContracts() {
        List<Contract> contracts;

        contracts = mapper.selectList(null);
        return contracts;
    }

    @Override
    public List<Contract> getContractInfoByOptions(Map<String, Object> options) {

        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();

        //合同编号
        if (options.get(CONTRACT_NO) != null && !Final.EMPTY_MSG.equals(options.get(CONTRACT_NO))){
            queryWrapper.like(Final.CONTRACT_NO_COLUMN,options.get(CONTRACT_NO));
        }

        //合同名
        if (options.get(Final.CONTRACT_NAME) != null && !Final.EMPTY_MSG.equals(options.get(Final.CONTRACT_NAME))){
            queryWrapper.like(Final.CONTRACT_NAME_COLUMN,options.get(Final.CONTRACT_NAME));
        }

        //合同类型
        if (options.get(TYPE_CONTRACT) != null && !Final.EMPTY_MSG.equals(options.get(TYPE_CONTRACT))){
            queryWrapper.eq(Final.TYPE_CONTRACT_COLUMN,options.get(TYPE_CONTRACT));
        }

        //签订日期
        if (options.get(SIGNING_DATE) != null && !Final.EMPTY_MSG.equals(options.get(SIGNING_DATE))){
            queryWrapper.eq(Final.SIGNING_DATE_COLUMN,options.get(SIGNING_DATE));
        }

        List<Contract> contractList = mapper.selectList(queryWrapper);
        return contractList;

    }

    @Override
    public Contract getContractInfoById(Integer conteactId) {
        Contract contract;
        contract = mapper.selectById(conteactId);
        if(contract != null){
            return contract;
        }else{
            return null;
        }
    }

    @Override
    public Boolean updateContract(Contract contract) {
        int result = mapper.updateById(contract);
        if (result == UNUPDATE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean deleteContract(Integer id) {
        int result = mapper.deleteById(id);
        if (result == UNDELETE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean deleteByList(List<Integer> lists) {
        Integer num = mapper.deleteBatchIds(lists);
        if (num != 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 合同创建  首先要创建用户
     * @param contract form表单返回 获得的数据
     * @return
     */
    @Override
    public Boolean createContract(Contract contract) {

       int result = mapper.insert(contract);

        //将id赋值给文件id
       contract.setFileId(contract.getContractId());
       int result2 = mapper.updateById(contract);

       if(result == UNINSERT || result2 == UNUPDATE){
           return false;
       }else {
           return true;
       }

    }


}
