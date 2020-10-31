package com.dandelion.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dandelion.management.bean.*;
import com.dandelion.management.mapper.*;
import com.dandelion.management.service.EventService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 事件 Service 实现类
 *
 * @author hongjianYang
 * @date 2020/7/14
 */
@Service
public class EventServiceImpl implements EventService {
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
    /**
     * 事件申报
     */
    private static final String DECLARE = "1";
    /**
     * 事件派单
     */
    private static final String DISPATCH = "2";
    /**
     * 事件结束
     */
    private static final String CLOSED = "3";
    /**
     * 事件已评价
     */
    private static final String EVALUATION = "4";

    @Autowired
    EventMapper mapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    AssessMapper assessMapper;

    @Autowired
    AssetMapper assetMapper;


    /**
     * 根据项目编号返回项目中所使用的资产信息
     *
     * @param projectId 项目id
     * @return 项目所涉及的资产信息
     */
    @Override
    public List<Asset> selectAssetByProject(Integer projectId) {
        List<Asset> assets = new ArrayList<>();
        Map<String,Object> option = new HashedMap<>();
        option.put("project_id",projectId);
        assets = assetMapper.selectByMap(option);
        return assets;
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
     * 条件查询
     *
     * @param options 条件
     * @return 查询结果
     */
    @Override
    public List<Event> getEventsByOptions(Map<String, Object> options) throws Exception {
        //需要进行数据转换 注入项目id 与运维人id 未完成
        SimpleDateFormat spd = new SimpleDateFormat("yyyy/MM/dd");
        Date beforeTime = spd.parse((String) options.get("beforeTime"));
        Date afterTime = spd.parse((String) options.get("AfterTime"));
        options.put("beforeTime", beforeTime);
        options.put("AfterTime", afterTime);

        List<Event> events = mapper.selectByOptions(options);

        if (events.size() == 0) {
            return events;
        } else {
            return events;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        int result = mapper.deleteById(id);
        if (result == UNDELETE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean updateEvent(Event event) {
        int result = mapper.updateById(event);
        if (result == UNUPDATE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Event getEventById(Integer id) {
        Event e;
        if (id != null) {
            e = mapper.selectById(id);
            return e;
        } else {
            return null;
        }

    }

    @Override
    public List<Event> getEventsByStatus(String status) {
        Map<String, Object> map = new HashMap<>(16);
        List<Event> events;
        //通过状态进行的条件查询
        if(status.equals("5")){
            map.put("status", 1);
            events = mapper.selectByMap(map);
            map.put("status", 2);
            events.addAll(mapper.selectByMap(map));
        }else{
            if (!status.equals("0")) {
                map.put("status", status);
                events = mapper.selectByMap(map);
            } else {
                //若无参数则请求所有数据
                events = mapper.selectList(null);
            }
        }
        return events;
    }

    /**
     * 在添加事件之前 首先对用户进行处理 若当前用户存在 则直接进行关联，若不存在，则进行添加操作后再进行关联
     *
     * @param event         Form数据
     * @param reporterName  用户名
     * @param reporterPhone 用户id
     * @param reporterUnit  用户所在公司
     * @return 是否创建完成
     */
    @Override
    public Boolean createEvent(Event event, String reporterName, String reporterPhone, String reporterUnit) {
        Boolean result = autoAdaptUpdateEvent(event,reporterName,reporterPhone,reporterUnit,1);
        return result;
    }

    @Override
    public Boolean updateEvent(Event event, String processorName, String processorPhone, String processorUnit) {
        Boolean result = autoAdaptUpdateEvent(event,processorName,processorPhone,processorUnit,2);
        return result;
    }

    /**
     * 根据提交的表单进行信息注入 结案管理
     *
     * @param event                 事件对象
     * @param processingDescription 处理描述
     * @param processorName         处理人
     * @param processorPhone        处理人手机
     * @param processorCompany      处理单位
     * @return
     */
    @Override
    public Boolean completeEvent(Event event, String processingDescription, String processorName, String processorPhone, String processorCompany) {
        event.setProcessingDes(processingDescription);
        Boolean result = autoAdaptUpdateEvent(event,processorName,processorPhone,processorCompany,3);
        return result;
    }

    /**
     * 根据提交的表单进行更新操作
     *
     * @param event  事件对象
     * @param common 评论
     * @param level  评级
     * @return 返回是否更新成功
     */
    @Override
    public Boolean assessEvent(Event event, String common, Integer level) {
        Assess assess = new Assess();
        //注入属性
        assess.setComment(common);
        assess.setRating(level);
        assess.setRatingTime(new Date());
        assess.setEventId(event.getId());
        assess.setProjectId(event.getProjectId());
        //在此处将当前登录状态的userId获取 因无Session故先固定注入
        assess.setReporterId(1);
        //插入评论
        Integer res = assessMapper.insert(assess);
        //根据官方文档 此处可直接获取到更新的主键id
        Integer assessId = assess.getAssessId();

        //判断是否成功插入
        if (res.equals(UNUPDATE)){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 更改资产数量 单向降低操作
     * 此处可能发生并发读写 使用同步锁进行控制
     * @param assets 资产
     * @return 是否降低成功
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public synchronized Boolean dedlineAssets(List<Asset> assets) {
        Map<String,Object> option = new HashedMap<>();
        List<Asset> assetMsg;
        Asset asset;
        //查询每一个在返回列表中的资产
        for (Asset item:assets) {
            option.put("asset_number",item.getAssetNumber());
            asset = assetMapper.selectByMap(option).get(0);
            //对每份资产进行处理 当某项资产数量不足时 发生回滚 抛出时需要借助RuntimeException 否则不发生回滚
            if (asset.getQuantity()-item.getQuantity() < 0){
                throw new RuntimeException(asset.getAssetName() + "数量不足,发生回滚");
            }else{
                asset.setQuantity(asset.getQuantity()-item.getQuantity());
                assetMapper.updateById(asset);
            }
        }
        return true;
    }

    /**
     * 对原有方法进行抽象处理 以实现其泛用
     * @param event 事件
     * @param name  人员姓名
     * @param phone 人员联系方法
     * @param unit  人员所在单位
     * @return
     */
    private Boolean autoAdaptUpdateEvent(Event event,String name, String phone, String unit,Integer status){
         //因其下标必然固定 故直接获取对应位置用的元素
         final Integer userIndex = 0;
         Integer companyId = 0;
         Integer userId = 0;
 
         //储存查询结果
         User user = new User();
 
         Map<String, Object> companyOption = new HashedMap<>();
         companyOption.put("company_name", unit);
         List<Company> companies = companyMapper.selectByMap(companyOption);
 
         //针对公司是否存在的问题进行处理
         if (companies.size() == 0) {
             Company company = new Company();
             company.setCompanyName(unit);
             Integer createCompanyResult = companyMapper.insert(company);
             if (createCompanyResult.equals(UNINSERT)) {
                 return false;
             }
 
             //查询新创建的id结果
             List<Company> c = companyMapper.selectByMap(companyOption);
             companyId = c.get(userIndex).getCompanyId();
         } else {
             //若已存在结果直接获取 需保证公司的名称具有唯一性
             companyId = companies.get(userIndex).getCompanyId();
         }
 
         Map<String, Object> options = new HashedMap<>();
         options.put("name", name);
         options.put("phone", phone);
         List<User> users = userMapper.selectByMap(options);
 
         //针对用户是否存在的问题进行处理
         if (users.size() != 0) {
             //精确查询 应只存在一个返回值
             userId = users.get(userIndex).getId();
             event.setReporterId(userId);
         } else {
             //当不存在用户信息时 先创建用户信息 再进行关联处理
             user.setName(name);
             user.setPhone(phone);
             user.setCompanyId(companyId);
             Integer createUserResult = userMapper.insert(user);
             List<User> createdUser = userMapper.selectByMap(options);
             userId = createdUser.get(userIndex).getId();
 
             if (createUserResult.equals(UNINSERT)) {
                 return false;
             }
         }
 
         //置入id完成创建
         if(status == 1){
            event.setReporterId(userId);
         }else if(status == 2){
            event.setOperationId(userId);
         }else if(status == 3){
            event.setProcessorId(userId);
         }

         Integer result = 0;
         if(event.getId() == null){
            result = mapper.insert(event);
         }else{
            result = mapper.updateById(event);
         }
         if (result.equals(UNINSERT)) {
            return false;
         } else {
            return true;
         }
    }


}
