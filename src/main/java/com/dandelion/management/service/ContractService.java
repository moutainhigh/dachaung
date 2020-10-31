package com.dandelion.management.service;

import com.dandelion.management.bean.Contract;
import com.dandelion.management.bean.Project;

import java.util.List;
import java.util.Map;

public interface ContractService {

    List<Contract> getAllContracts();

    List<Contract> getContractInfoByOptions(Map<String, Object> options);

    Contract getContractInfoById(Integer projectId);

    Boolean updateContract(Contract contract);

    /**
     * 通过id 删除数据
     * @param id
     * @return
     */
    Boolean deleteContract(Integer id);

    Boolean deleteByList(List<Integer> lists);

    Boolean createContract(Contract contract);
}
