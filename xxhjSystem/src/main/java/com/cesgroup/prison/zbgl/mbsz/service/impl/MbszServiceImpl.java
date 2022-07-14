package com.cesgroup.prison.zbgl.mbsz.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.zbgl.lbbm.dao.LbbmMapper;
import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;
import com.cesgroup.prison.zbgl.mbsz.dao.MbszMapper;
import com.cesgroup.prison.zbgl.mbsz.dto.DutyPoliceDto;
import com.cesgroup.prison.zbgl.mbsz.entity.MbszEntity;
import com.cesgroup.prison.zbgl.mbsz.service.MbszService;
import com.cesgroup.prison.zbgl.mbxq.dao.MbxqMapper;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;
import com.cesgroup.prison.zbgl.rlwh.dao.ZbRlwhMapper;
import com.cesgroup.prison.zbgl.rlwh.entity.ZbRlwhEntity;
import com.cesgroup.prison.zbgl.rygl.dao.DutyLastFlagMapper;
import com.cesgroup.prison.zbgl.rygl.dao.RyglMapper;
import com.cesgroup.prison.zbgl.rygl.dto.DutyQueryDto;
import com.cesgroup.prison.zbgl.rygl.dto.LastDutyRyDto;
import com.cesgroup.prison.zbgl.rygl.entity.DutyFlagEntity;
import com.cesgroup.prison.zbgl.rygl.entity.DutyLastFlagEntity;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import com.cesgroup.prison.zbgl.rygl.util.RyCommon;
import com.cesgroup.prison.zbgl.rygl.util.RyglConstant;
import com.cesgroup.prison.zbgl.zbbp.dao.ZbbpMapper;
import com.cesgroup.prison.zbgl.zbbp.entity.ZbbpEntity;
import com.cesgroup.prison.zbgl.zbbp.service.ZbbpService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service("MbszService")
public class MbszServiceImpl extends BaseDaoService<MbszEntity, String, MbszMapper> implements MbszService {

    @Resource
    private MbszMapper mbszMapper;
    @Resource
    private MbxqMapper mbxqMapper;
    @Resource
    private LbbmMapper lbbmMapper;
    @Resource
    private ZbbpMapper zbbpMapper;

    @Resource
    private RyglMapper ryglMapper;
    @Resource
    private ZbRlwhMapper zbRlwhMapper;
    @Resource
    private DutyLastFlagMapper dutyLastFlagMapper;

    //存指挥长值中班时顺序找在原指挥长值班的顺序中找不到的 id  姓名  和 日期
    List<Map<String,Object>> noZhzMessage = new ArrayList<Map<String, Object>>();

    @Override
    public MbszEntity getById(String id) {
        //类别
        MbszEntity mbsz = mbszMapper.getById(id);

        return mbsz;
    }

    @Override
    public Page<MbszEntity> findList(MbszEntity mbszEntity, PageRequest pageRequest) {

        return mbszMapper.findList(mbszEntity, pageRequest);
    }

    @Override
    public List<MbszEntity> findAllList(MbszEntity mbszEntity) {

        return mbszMapper.findAllList(mbszEntity);
    }

    @Override
    @Transactional
    public void saveOrUpdate(MbszEntity mbszEntity) throws Exception {

        String id = mbszEntity.getId();
        String orderData = mbszEntity.getCdmOrderData();
        String[] order = orderData.split(";");

        if (id != null && !"".equals(id)) {
            //修改
            deleteById(id);
        }
        //新增模板
        MbszEntity mbsz = new MbszEntity();
        mbsz.setCdmCusNumber(mbszEntity.getCdmCusNumber());
        mbsz.setCdmCategoryId(mbszEntity.getCdmCategoryId());
        mbsz.setCdmModeName(mbszEntity.getCdmModeName());
        mbsz.setCdmOrderCount(mbszEntity.getCdmOrderCount());
        mbsz.setCdmPeriod(mbszEntity.getCdmPeriod());
        //mbsz.setCdmStatus(mbszEntity.getCdmStatus());
        mbsz.setCdmCrteUserId(mbszEntity.getCdmCrteUserId());
        mbsz.setCdmCrteTime(new Date());
        mbsz.setCdmUpdtUserId(mbszEntity.getCdmUpdtUserId());
        mbsz.setCdmUpdtTime(new Date());
        mbsz.setCdmSfqy(mbszEntity.getCdmSfqy());
        mbszMapper.insert(mbsz);

        //新增模板详细
        List<MbxqEntity> mbxqEntityList = new ArrayList<MbxqEntity>();
        int sqlNo = 1;
        for (int i = 0; i < order.length; i++) {

            String[] job = (order[i].split("-")[1]).split(",");

            for (int j = 0; j < job.length; j++) {

                MbxqEntity mbxq = new MbxqEntity();
                mbxq.setMojCusNumber(mbszEntity.getCdmCusNumber());
                mbxq.setMojModeId(mbsz.getId());
                mbxq.setMojOrderId(order[i].split("-")[0].trim());
                mbxq.setMojJobId(job[j].trim());
                mbxq.setMojCrteTime(new Date());
                mbxq.setMojCrteUserId(mbszEntity.getCdmCrteUserId());
                mbxq.setMojUpdtTime(new Date());
                mbxq.setMojUpdtUserId(mbszEntity.getCdmUpdtUserId());
                mbxqEntityList.add(mbxq);
                mbxq.setMojSqlno(sqlNo);
                sqlNo++;
            }
        }
        if (mbxqEntityList.size() > 0) {
            mbxqMapper.insert(mbxqEntityList);
        }
    }

    @Override
    @Transactional
    public void deleteById(String id) {

        mbszMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void updateSfqyById(String id, String sfqy) {
        MbszEntity mbszEntity = new MbszEntity();
        mbszEntity.setId(id);
        mbszEntity.setCdmSfqy(sfqy);
        this.getDao().updateSfqyById(mbszEntity);
    }

    @Override
    public AjaxResult searchSfqy(String cusNumber, String id) {
        //MbszEntity mbszEntity =  new MbszEntity();
        //mbszEntity.setCdmCusNumber(cusNumber);
		/*List<MbszEntity> selectByEntity = this.getDao().selectByEntity(mbszEntity);
		for(MbszEntity mb:selectByEntity) {
			if("1".equals(mb.getCdmSfqy())) {
				return AjaxResult.success(mb);
			}
		}
		mbszEntity.setCdmSfqy("0");*/
        MbszEntity mbszEntity = this.getDao().selectOne(id);
        MbszEntity mbsz = new MbszEntity();
        mbsz.setCdmCategoryId(mbszEntity.getCdmCategoryId());
        List<MbszEntity> mbszEntities = this.getDao().selectByEntity(mbsz);
        List<String> mbNames = new ArrayList<>(); //储存启用的模板名称返回给前端作为提示
        //如果存在其他模板为启用转态 就返回模板名称集合 ,没有其他启用的不返回
        for (MbszEntity mb : mbszEntities) {
            if ("1".equals(mb.getCdmSfqy())) {//1为启用状态
                mbNames.add(mb.getCdmModeName());
            }
        }
        return AjaxResult.success(mbNames);
    }

    /**
     * 获取自动排班数据
     * @return
     */
    @Override
    @Transactional
    public  Map<String, List<Map<String,Object>>> getAutoZbData(String startDate, String endDate) {
        noZhzMessage.clear();
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        //mbszEntity.setCdmOrderData(orderData);
        Map<String, List<Map<String,Object>>> reslutMap = new HashMap<>();
        //计算天数
        List<Map<String, Object>> orderData = new ArrayList<Map<String, Object>>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String cusNumber = user.getCusNumber();
        try {
            //计算排班的天数
            //mbszEntity
            Date start = DateUtils.parseDate(startDate);
            Date end = DateUtils.parseDate(endDate);
            String dateDiff = CommonUtil.getDateDiff(start, end);
            Integer diff = Integer.parseInt(dateDiff);//相隔天数
            RyglEntity rygl = new RyglEntity();
            rygl.setCusNumber(user.getCusNumber());
            rygl.setJob("指挥长");
            /**
             * 状态 1.在编 2.借调 3.退休 4.培训 5.病假  6.其他
             *
             * 获取数据 需要排除的数据  排除 小于培训开始时间和大于结束时间
             */
            //指挥长值中班人员已排序
            List<RyglEntity> zhzDutyMidOrderRy = ryglMapper.selectListByJobOrderzhzMidOrder(rygl);


            List<RyglEntity> zhzRy = ryglMapper.selectListByJob(rygl);//指挥长
            rygl.setJob("男值班长");
            List<RyglEntity> mzbzRy = ryglMapper.selectListByJob(rygl);//男值班长
            rygl.setJob("女值班长");
            List<RyglEntity> wzbzRy = ryglMapper.selectListByJob(rygl);//女值班长
            rygl.setJob("男值班员");
            List<RyglEntity> mzbyRy = ryglMapper.selectListByJob(rygl);//男值班员
            rygl.setJob("女值班员");
            List<RyglEntity> wzbyRy = ryglMapper.selectListByJob(rygl);//女值班员
            rygl = null;//置空
            Map<String,Integer> ryIdIndexMap =new HashMap<>();

            Map<String,Integer> zhzMidIndexMap =new HashMap<>();
            putMap(zhzMidIndexMap,zhzDutyMidOrderRy);
            putMap(ryIdIndexMap,zhzRy);
            putMap(ryIdIndexMap,mzbzRy);
            putMap(ryIdIndexMap,wzbzRy);
            putMap(ryIdIndexMap,mzbyRy);
            putMap(ryIdIndexMap,wzbyRy);
            // 指挥长  值班长  值班员   值班长  值班员  值班长  值班员
            //dutyZd.put(, value)
            /**
             *
             * 1.每周有一个工作日局领导担任指挥长的同时担任当天中班值班长
             * 2.全年的值班长和值班员的中晚班数量相对均衡
             *3.有退休前6个月,病假或在党校学习1个月以上不予排班的选项,并有不予排班的时间段,跳过排班
             *4.女性警察只排早班，不排中、晚班，排班到处室时应先安排女性四级调研员
             * (含)以上干部，担任早班值班长；
             *
             * 5.局办公室值班排班按照法定节假日、双休日一轮，工作日一轮分别排定，每
             * 月一次性安排；
             *
             * 规则分析:
             *    1.指挥长由指挥长担任循环
             *   2.早值班长和早值班员 分别由 女值班长和 女值班员担任
             *   3.中班 -值班长人员来源于男值班长 或指挥长
             *   4.中班值班长由指挥长担任时  条件为:当本周工作日为>3天需要由指挥长兼任  需要判断这周有几天工作日?在判断是否排指挥长
             *   5.中班值班员-男值班员担任
             *   6.晚班值班长-男值班长担任
             *   7.晚值班员-男值班员担任
             *  8.局办公室值班排班按照法定节假日、双休日一轮，工作日一轮分别排定 双轮回分每类分工作日和节假日两个指针位置
             */
            //计算某天后的日期
            // CommonUtil.addDate(sdf.parse(startDate), i)
            // startDate   endDate
            //指挥长指针
            int zhzIndex = 0;
            //指挥长值中班指针
            int zhzMidIndex = 0;
            //女值班长节假日指针
            int wzbzHIndex = 0;
            //女值班长工作日指针
            int wzbzWIndex = 0;
            //女值班员节假日指针
            int wzbyHIndex = 0;
            //女值班员工作日指针
            int wzbyWIndex = 0;
            //男值班长节假日指针
            int mzbzHIndex = 0;
            //男值班长工作日中班指针
            int mzbzWMidIndex = 0;
            //男值班长工作日晚班指针
            int mzbzWNightIndex = 0;

            //男值班员节假日指针
            int mzbyHIndex = 0;
            //男值班员工作日指针
            int mzbyWIndex = 0;
            //上次排班的最后人员
            DutyFlagEntity dutyFlag = ryglMapper.getDutyFlag(cusNumber);
            //查询startDate之前的最后工作日
            String wDate = zbRlwhMapper.selectMaxDutyDateByDate(startDate, "0").getDutyDate();
            //查询startDate之前的最后节假日
            String hDate = zbRlwhMapper.selectMaxDutyDateByDate(startDate, "1").getDutyDate();
            DutyLastFlagEntity dutyLastFlagEntity = new DutyLastFlagEntity();
            dutyLastFlagEntity.setCusNumber(cusNumber);
            dutyLastFlagEntity.setWorkday(wDate);
            dutyLastFlagEntity.setHoliday(hDate);
            //dutyLastFlagEntity.setSaveZt("1");
            List<DutyLastFlagEntity> dutyLastFlagEntities = dutyLastFlagMapper.selectByEntity(dutyLastFlagEntity);

            //确认指挥长在 zhzRy 最后的位置  从下个开始排班
            //指挥长索引
            DutyQueryDto dutyQueryDto1 = new DutyQueryDto();
            dutyQueryDto1.setCusNumber(cusNumber);
            dutyQueryDto1.setJob("指挥长");
            List<LastDutyRyDto> lastDutyzhz = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto1);

            if(lastDutyzhz ==null || lastDutyzhz.size()<=0){
                zhzIndex =  ryIdIndexMap.get(dutyFlag.getZhzFlag());
            }else{//这里直接查询最后值班标记表(新增功能)
                if(dutyLastFlagEntities.size()>0){
                    zhzIndex =ryIdIndexMap.get(dutyLastFlagEntities.get(0).getZhzId());
                }else{
                    zhzIndex = ryIdIndexMap.get(lastDutyzhz.get(lastDutyzhz.size()-1).getId());
                }
            }
            dutyQueryDto1.setJobName("值班长");
            dutyQueryDto1.setDutyOrderName("中班");
            List<LastDutyRyDto> lastDutyRyzhzMid = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto1);

            if(lastDutyRyzhzMid==null || lastDutyRyzhzMid.size() <=0){
                zhzMidIndex = 0;

            }else{
                if(dutyLastFlagEntities.size()>0){
                    if(zhzMidIndexMap.get(dutyLastFlagEntities.get(0).getZhzMidId())==(zhzDutyMidOrderRy.size()-1)){
                        zhzMidIndex = 0;
                    }else{
                        zhzMidIndex = zhzMidIndexMap.get(dutyLastFlagEntities.get(0).getZhzMidId())+1;
                    }
                }else{
                    if(zhzMidIndexMap.get(lastDutyRyzhzMid.get(lastDutyRyzhzMid.size() - 1).getId())==(zhzDutyMidOrderRy.size()-1)){
                        zhzMidIndex = 0;
                    }else{
                        zhzMidIndex = zhzMidIndexMap.get(lastDutyRyzhzMid.get(lastDutyRyzhzMid.size() - 1).getId())+1;
                    }
                }
            }


            DutyQueryDto dutyQueryDto2 = new DutyQueryDto();
            dutyQueryDto2.setCusNumber(cusNumber);
            dutyQueryDto2.setDutyOrderName("早班");
            dutyQueryDto2.setJob("女值班长");
            dutyQueryDto2.setJobName("值班长");


            //查询最后工作日的女值班长
            dutyQueryDto2.setDutyDate(wDate);
            List<LastDutyRyDto> wzbzWorkDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto2);
            dutyQueryDto2.setDutyDate(hDate);
            List<LastDutyRyDto> wzbzHDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto2);

            //女值班长索引
            if(wzbzWorkDay.size() > 0){
                if(dutyLastFlagEntities.size()>0){
                    wzbzWIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbzWId());
                    wzbzHIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbzHId());
                }else{
                    wzbzWIndex = ryIdIndexMap.get(wzbzWorkDay.get(wzbzWorkDay.size()-1).getId());
                    wzbzHIndex = ryIdIndexMap.get(wzbzHDay.get(wzbzHDay.size() - 1).getId());
                }

            }else{
                wzbzWIndex = ryIdIndexMap.get(dutyFlag.getWzbzWFlag());
                wzbzHIndex =ryIdIndexMap.get(dutyFlag.getWzbzHFlag());
            }



            DutyQueryDto dutyQueryDto3= new DutyQueryDto();
            dutyQueryDto3.setCusNumber(cusNumber);
            dutyQueryDto3.setJob("女值班员");
            dutyQueryDto3.setJobName("值班员");
            dutyQueryDto3.setDutyDate(wDate);
            List<LastDutyRyDto> wzbyWorkDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto3);

            dutyQueryDto3.setDutyDate(hDate);
            List<LastDutyRyDto> wzbyHDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto3);

            if(wzbyWorkDay.size() >0){
                if(dutyLastFlagEntities.size()>0){
                    wzbyWIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbyWId());
                    wzbyHIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbyHId());
                }else{
                    wzbyWIndex = ryIdIndexMap.get(wzbyWorkDay.get(wzbyWorkDay.size()-1).getId());
                    wzbyHIndex = ryIdIndexMap.get(wzbyHDay.get(wzbyHDay.size()-1).getId());
                }
            }else{
                wzbyWIndex = ryIdIndexMap.get(dutyFlag.getWzbyWFlag());
                wzbyHIndex = ryIdIndexMap.get(dutyFlag.getWzbyHFlag());
            }


            DutyQueryDto dutyQueryDto4= new DutyQueryDto();
            dutyQueryDto4.setCusNumber(cusNumber);
            dutyQueryDto4.setJob("男值班长");
            dutyQueryDto4.setJobName("值班长");
            dutyQueryDto4.setDutyDate(wDate);
            dutyQueryDto4.setDutyOrderName("中班");
            List<LastDutyRyDto> mzbzWorkMidDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto4);

            dutyQueryDto4.setDutyOrderName("晚班");
            List<LastDutyRyDto> mzbzWorkNightDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto4);

            dutyQueryDto4.setDutyOrderName("");
            dutyQueryDto4.setDutyDate(hDate);
            List<LastDutyRyDto> mzbzHDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto4);
            List<RyglEntity> reverRy = reversalMzbzRy(mzbzRy);
            if(mzbzWorkMidDay.size() > 0){
                if(dutyLastFlagEntities.size()>0){
                    mzbzWMidIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbzWMidId());
                    //男值班长反转后的索引位置
                    mzbzWNightIndex =getmzbzIndex(reverRy,dutyLastFlagEntities.get(0).getMzbzWNightId());
                    mzbzHIndex =ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbzHId());
                }else{
                    mzbzWMidIndex = ryIdIndexMap.get(mzbzWorkMidDay.get(mzbzWorkMidDay.size()-1).getId());
                    //男值班长反转后的索引位置
                    mzbzWNightIndex =getmzbzIndex(reverRy,mzbzWorkNightDay.get(mzbzWorkNightDay.size()-1).getId());
                    mzbzHIndex =ryIdIndexMap.get(mzbzHDay.get(mzbzHDay.size()-1).getId());
                }

            }else{
                //男值班长工作日中班索引
                mzbzWMidIndex = ryIdIndexMap.get(dutyFlag.getMzbzWFlag());
                //男值班长工作日晚班索引 男值班长反转后的索引位置
                mzbzWNightIndex =getmzbzIndex(reverRy,dutyFlag.getMzbzWNFlag());
                //男值班长节假日索引
                mzbzHIndex = ryIdIndexMap.get(dutyFlag.getMzbzHFlag());
            }



            DutyQueryDto dutyQueryDto5= new DutyQueryDto();
            dutyQueryDto5.setCusNumber(cusNumber);
            dutyQueryDto5.setJob("男值班员");
            dutyQueryDto5.setJobName("值班员");
            dutyQueryDto5.setDutyDate(wDate);

            List<LastDutyRyDto> mzbyWorkDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto5);

            dutyQueryDto5.setDutyDate(hDate);
            List<LastDutyRyDto> mzbyHDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto5);

            if(mzbyWorkDay.size() > 0){
                if(dutyLastFlagEntities.size()>0){
                    mzbyWIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbyWId());
                    mzbyHIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbyHId());
                }else{
                    mzbyWIndex = ryIdIndexMap.get(mzbyWorkDay.get(mzbyWorkDay.size()-1).getId());
                    mzbyHIndex = ryIdIndexMap.get(mzbyHDay.get(mzbyHDay.size()-1).getId());
                }
            }else{
                //男值班员索引
                mzbyWIndex = ryIdIndexMap.get(dutyFlag.getMzbyWFlag());
                mzbyHIndex =ryIdIndexMap.get(dutyFlag.getMzbyHFlag());
            }


            //查询在数据库中已经排过的指挥长值中班的情况
            DutyQueryDto dutyQueryDto = new DutyQueryDto();
            dutyQueryDto.setCusNumber(cusNumber);
            dutyQueryDto.setDutyOrderName("中班");
            dutyQueryDto.setJob("指挥长");
            dutyQueryDto.setJobName("值班长");
            List<LastDutyRyDto> lastDutyRyDtos = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto);



            String zhzZbFlagDutyDate ="";
            String zhzZbFlag = "";
            if(lastDutyRyDtos.size() <=0 ||lastDutyRyDtos ==null){
                zhzZbFlagDutyDate = dutyFlag.getZhzZbFlagDutyDate();
                zhzZbFlag = dutyFlag.getZhzZbFlag();
            }else{
                zhzZbFlag = lastDutyRyDtos.get(lastDutyRyDtos.size()-1).getId();
                Date dutyDate = lastDutyRyDtos.get(lastDutyRyDtos.size() - 1).getDutyDate();
                zhzZbFlagDutyDate = sdf.format(dutyDate);
            }


            //找到男值班员  工作日标识 下标表示人员的序号
            int[] mzbyWorkdayOrderFlag = getOrderNameFlag(mzbyRy, cusNumber, "0");
            //节假日 下标表示人员的序号
            int[] mzbyHolidayOrderFlag = getOrderNameFlag(mzbyRy, cusNumber, "1");


            //找到男值班长 工作日标识 下标表示人员的序号
            int[] mzbzWorkdayOrderFlag = getOrderNameFlag(mzbzRy, cusNumber, "0");
            //找到男值班长 节假日 下标表示人员的序号
            int[] mzbzHolidayOrderFlag = getOrderNameFlag(mzbzRy, cusNumber, "1");




            for (int i = 0; i < diff + 1; i++) {
                Map<String, Object> dutyPolices = new HashMap<String, Object>();
                String dutyDate =sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
                //排指挥长  1.先确定指挥长从哪个人排起  2.过滤掉当天不值班的人
                zhzIndex = startDuty(zhzIndex,zhzRy,dutyDate,dutyPolices,RyglConstant.ZHZ);

                //排早班值班长 ->  女值班长
                // wzbzIndex =  startDuty(wzbzIndex, wzbzRy, dutyDate, dutyPolices,RyglConstant.ZZBZ);

                if(checkIsworkDay(dutyDate)){//如果是工作日
                    wzbzWIndex = startDuty(wzbzWIndex, wzbzRy, dutyDate, dutyPolices,RyglConstant.ZZBZ);
                }else{//节假日
                    wzbzHIndex = startDuty(wzbzHIndex, wzbzRy, dutyDate, dutyPolices,RyglConstant.ZZBZ);
                }





                // 早班值班员-> 女值班员
                //wzbyIndex = startDuty(wzbyIndex, wzbyRy, dutyDate, dutyPolices,RyglConstant.ZZBY);
                if(checkIsworkDay(dutyDate)){
                    wzbyWIndex = startDuty(wzbyWIndex, wzbyRy, dutyDate, dutyPolices,RyglConstant.ZZBY);
                }else{
                    wzbyHIndex = startDuty(wzbyHIndex, wzbyRy, dutyDate, dutyPolices,RyglConstant.ZZBY);
                }




                //  mzbyIndex =   startContinue(mzbyRy,mzbyIndex,dutyDate,dutyPolices, RyglConstant.ZHZBY,RyglConstant.WZBY);

                if(checkIsworkDay(dutyDate)){//工作日
                    mzbyWIndex = startMidAndNignt(null,null,mzbyWorkdayOrderFlag,mzbyRy,mzbyWIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBY,RyglConstant.WZBY);
                }else{
                    mzbyHIndex = startMidAndNignt(null,null,mzbyHolidayOrderFlag,mzbyRy,mzbyHIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBY,RyglConstant.WZBY);
                }

                orderData.add(dutyPolices);
            }
            //排 中 值班员  -> 来源 男值班员   和 晚班 值班员-> 来源 男值班员
            // Map<String, Object> dutyPolices1 = new HashMap<String, Object>();
            /*List<Map<String, Object>> mzbyList = startDutyZbAndWb(mzbyWIndex, mzbyHIndex, startDate, endDate, mzbyRy, RyglConstant.ZHZBY, RyglConstant.WZBY, sdf);
            for (int i = 0; i < orderData.size(); i++) {
                orderData.get(i).putAll(mzbyList.get(i));
            }*/

            // List<String> lastResult = convertToLastResult(lastDutyRyDtos);
            List<RyglEntity> zhzOrderData = getZhzToOrderData(orderData, RyglConstant.ZHZ);
            List<String> zbIndexByZhz = getZBIndexByZhz2(zhzMidIndex,zhzZbFlagDutyDate, startDate, endDate, sdf, zhzOrderData, zhzDutyMidOrderRy);


            List<Map<String, Object>> mzbzorderData = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < diff + 1; i++) {
                Map<String, Object> dutyPolices = new HashMap<String, Object>();
                String dutyDate = sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
                //男值班长
                if(checkIsworkDay(dutyDate)){//工作日
                    int[] ints = startWorkDutyMidOrder(zbIndexByZhz, zhzOrderData, mzbzRy, mzbzWMidIndex, i, mzbzWNightIndex, dutyDate, dutyPolices, RyglConstant.ZHZBZ, RyglConstant.WZBZ);
                    mzbzWMidIndex = ints[0];
                    mzbzWNightIndex = ints[1];
                }else{
                    mzbzHIndex = startMidAndNignt(zbIndexByZhz,zhzOrderData,mzbzHolidayOrderFlag,mzbzRy,mzbzHIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBZ,RyglConstant.WZBZ);
                }
                mzbzorderData.add(dutyPolices);
            }



            //先排中班值班长 男值班长
            /* List<Map<String, Object>> zwZhzData = startDutyzbzZbAndWb(zbIndexByZhz, zhzOrderData, mzbzWMidIndex, mzbzHIndex, startDate, endDate, mzbzRy, RyglConstant.ZHZBZ, RyglConstant.WZBZ, sdf);*/
            for (int i = 0; i < orderData.size(); i++) {
                orderData.get(i).putAll(mzbzorderData.get(i));
            }
            //保存最后标记数据
            saveLastDutyFlag(orderData,zbIndexByZhz,startDate,endDate,diff,user);


        } catch (Exception e) {
            e.printStackTrace();
        }
        reslutMap.put("orderData",orderData);
        reslutMap.put("noZhzMessage",noZhzMessage);
        return reslutMap;
    }




    /**
     * 获取自动排班数据
     * @return
     */
    @Override
    @Transactional
    public  Map<String, List<Map<String,Object>>> getAutoZbDataNew(String startDate, String endDate) {
        noZhzMessage.clear();
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        //mbszEntity.setCdmOrderData(orderData);
        Map<String, List<Map<String,Object>>> reslutMap = new HashMap<>();
        //计算天数
        List<Map<String, Object>> orderData = new ArrayList<Map<String, Object>>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String cusNumber = user.getCusNumber();
        try {
            //计算排班的天数
            //mbszEntity
            Date start = DateUtils.parseDate(startDate);
            Date end = DateUtils.parseDate(endDate);
            String dateDiff = CommonUtil.getDateDiff(start, end);
            Integer diff = Integer.parseInt(dateDiff);//相隔天数
            RyglEntity rygl = new RyglEntity();
            rygl.setCusNumber(user.getCusNumber());
            rygl.setJob("指挥长");
            /**
             * 状态 1.在编 2.借调 3.退休 4.培训 5.病假  6.其他
             *
             * 获取数据 需要排除的数据  排除 小于培训开始时间和大于结束时间
             */


            List<RyglEntity> zhzRy = ryglMapper.selectListByJob(rygl);//指挥长
            rygl.setJob("男值班长");
            List<RyglEntity> mzbzRy = ryglMapper.selectListByJob(rygl);//男值班长
            rygl.setJob("女值班长");
            List<RyglEntity> wzbzRy = ryglMapper.selectListByJob(rygl);//女值班长
            rygl.setJob("男值班员");
            List<RyglEntity> mzbyRy = ryglMapper.selectListByJob(rygl);//男值班员
            rygl.setJob("女值班员");
            List<RyglEntity> wzbyRy = ryglMapper.selectListByJob(rygl);//女值班员
            rygl = null;//置空
            Map<String,Integer> ryIdIndexMap =new HashMap<>();
            putMap(ryIdIndexMap,zhzRy);
            putMap(ryIdIndexMap,mzbzRy);
            putMap(ryIdIndexMap,wzbzRy);
            putMap(ryIdIndexMap,mzbyRy);
            putMap(ryIdIndexMap,wzbyRy);
            // 指挥长  值班长  值班员   值班长  值班员  值班长  值班员
            //dutyZd.put(, value)
            /**
             *
             *
             * 2.全年的值班长和值班员的中晚班数量相对均衡
             *4.女性警察只排早班，不排中、晚班，排班到处室时应先安排女性四级调研员
             * (含)以上干部，担任早班值班长；
             *
             * 5.局办公室值班排班按照法定节假日一轮、双休日一轮，工作日一轮分别排定，每
             * 月一次性安排；
             *
             * 规则分析:
             *    1.指挥长由指挥长担任循环
             *   2.早值班长和早值班员 分别由 女值班长和 女值班员担任
             *   3.中班 -值班长人员来源于男值班长
             *   5.中班值班员-男值班员担任
             *   6.晚班值班长-男值班长担任
             *   7.晚值班员-男值班员担任
             *  8.局办公室值班排班按照法定节假日、双休日一轮，工作日一轮分别排定 双轮回分每类分工作日和节假日两个指针位置
             */
            //计算某天后的日期
            // CommonUtil.addDate(sdf.parse(startDate), i)
            // startDate   endDate
            //指挥长指针
            int zhzIndex = 0;

            //女值班长节假日指针
            int wzbzHIndex = 0;
            //女值班长工作日指针
            int wzbzWIndex = 0;
            //女值班长法定节假日指针
            int wzbzFIndex =0;

            //女值班员节假日指针
            int wzbyHIndex = 0;
            //女值班员工作日指针
            int wzbyWIndex = 0;
            //女值班员法定节假日指针
            int wzbyFIndex = 0;


            //男值班长节假日指针
            int mzbzHIndex = 0;
            //男值班长工作日指针
            int mzbzWIndex = 0;
            //男值班长法定节假日指针
            int mzbzFIndex = 0;

            //男值班员节假日指针
            int mzbyHIndex = 0;
            //男值班员工作日指针
            int mzbyWIndex = 0;
            //男值班员法定节假日指针
            int mzbyFIndex = 0;

            //上次排班的最后人员
            DutyFlagEntity dutyFlag = ryglMapper.getDutyFlag(cusNumber);
            //查询startDate之前的最后工作日
            String wDate = zbRlwhMapper.selectMaxDutyDateByDate(startDate, "0").getDutyDate();
            //查询startDate之前的最后节假日
            String hDate = zbRlwhMapper.selectMaxDutyDateByDate(startDate, "1").getDutyDate();
            DutyLastFlagEntity dutyLastFlagEntity = new DutyLastFlagEntity();
            dutyLastFlagEntity.setCusNumber(cusNumber);
            dutyLastFlagEntity.setWorkday(wDate);
            dutyLastFlagEntity.setHoliday(hDate);
            //dutyLastFlagEntity.setSaveZt("1");
            List<DutyLastFlagEntity> dutyLastFlagEntities = dutyLastFlagMapper.selectByEntity(dutyLastFlagEntity);

            //确认指挥长在 zhzRy 最后的位置  从下个开始排班
            //指挥长索引
            DutyQueryDto dutyQueryDto1 = new DutyQueryDto();
            dutyQueryDto1.setCusNumber(cusNumber);
            dutyQueryDto1.setJob("指挥长");
            List<LastDutyRyDto> lastDutyzhz = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto1);

            if(lastDutyzhz ==null || lastDutyzhz.size()<=0){
                zhzIndex =  ryIdIndexMap.get(dutyFlag.getZhzFlag());
            }else{//这里直接查询最后值班标记表(新增功能)
                if(dutyLastFlagEntities.size()>0){
                    zhzIndex =ryIdIndexMap.get(dutyLastFlagEntities.get(0).getZhzId());
                }else{
                    zhzIndex = ryIdIndexMap.get(lastDutyzhz.get(lastDutyzhz.size()-1).getId());
                }
            }

            DutyQueryDto dutyQueryDto2 = new DutyQueryDto();
            dutyQueryDto2.setCusNumber(cusNumber);
            dutyQueryDto2.setDutyOrderName("早班");
            dutyQueryDto2.setJob("女值班长");
            dutyQueryDto2.setJobName("值班长");


            //查询最后工作日的女值班长
            dutyQueryDto2.setDutyDate(wDate);
            List<LastDutyRyDto> wzbzWorkDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto2);
            dutyQueryDto2.setDutyDate(hDate);
            List<LastDutyRyDto> wzbzHDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto2);
            //用于女值班长节假日排班
            List<RyglEntity> wzbzHList = reversalMzbzRy(wzbzRy);


            //女值班长索引
            if(wzbzWorkDay.size() > 0){
                if(dutyLastFlagEntities.size()>0){
                    //工作日
                    wzbzWIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbzWId());
                    //节假日
                    wzbzHIndex = getmzbzIndex(wzbzHList,dutyLastFlagEntities.get(0).getWzbzHId());
                    //法定节假日
                    wzbzFIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbzFId());
                }else{
                    wzbzWIndex = ryIdIndexMap.get(wzbzWorkDay.get(wzbzWorkDay.size()-1).getId());
                    wzbzHIndex = ryIdIndexMap.get(wzbzHDay.get(wzbzHDay.size() - 1).getId());
                }

            }else{
                wzbzWIndex = ryIdIndexMap.get(dutyFlag.getWzbzWFlag());
                wzbzHIndex = ryIdIndexMap.get(dutyFlag.getWzbzHFlag());
            }



            DutyQueryDto dutyQueryDto3= new DutyQueryDto();
            dutyQueryDto3.setCusNumber(cusNumber);
            dutyQueryDto3.setJob("女值班员");
            dutyQueryDto3.setJobName("值班员");
            dutyQueryDto3.setDutyDate(wDate);
            List<LastDutyRyDto> wzbyWorkDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto3);
            //反转女值班员
            List<RyglEntity> wzbyHList = reversalMzbzRy(wzbyRy);


            dutyQueryDto3.setDutyDate(hDate);
            List<LastDutyRyDto> wzbyHDay = ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto3);

            if(wzbyWorkDay.size() >0){
                if(dutyLastFlagEntities.size()>0){
                    //工作日
                    wzbyWIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbyWId());
                    //节假日
                    wzbyHIndex = getmzbzIndex(wzbyHList, dutyLastFlagEntities.get(0).getWzbyHId());
                    //法定
                    wzbyFIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getWzbyFId());

                }else{
                    wzbyWIndex = ryIdIndexMap.get(wzbyWorkDay.get(wzbyWorkDay.size()-1).getId());
                    wzbyHIndex = ryIdIndexMap.get(wzbyHDay.get(wzbyHDay.size()-1).getId());
                }
            }else{
                wzbyWIndex = ryIdIndexMap.get(dutyFlag.getWzbyWFlag());
                wzbyHIndex = ryIdIndexMap.get(dutyFlag.getWzbyHFlag());
            }


            DutyQueryDto dutyQueryDto4= new DutyQueryDto();
            dutyQueryDto4.setCusNumber(cusNumber);
            dutyQueryDto4.setJob("男值班长");
            dutyQueryDto4.setJobName("值班长");
            dutyQueryDto4.setDutyDate(wDate);
            // dutyQueryDto4.setDutyOrderName("中班");
            List<LastDutyRyDto> mzbzWorkDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto4);

            // dutyQueryDto4.setDutyOrderName("晚班");
            //  List<LastDutyRyDto> mzbzWorkNightDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto4);
            List<RyglEntity> mzbzHList = reversalMzbzRy(mzbzRy);
            //男值班长索引
            if(mzbzWorkDay.size() >0){
                if(dutyLastFlagEntities.size()>0){
                    //工作日
                    mzbzWIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbzWId());
                    //节假日
                    //mzbzHIndex = getmzbzIndex(mzbzHList, dutyLastFlagEntities.get(0).getMzbzHId());
                    mzbzHIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbzHId());
                    //法定
                    mzbzFIndex = getmzbzIndex(mzbzHList, dutyLastFlagEntities.get(0).getMzbzFId());

                }else{
                    wzbyWIndex = ryIdIndexMap.get(wzbyWorkDay.get(wzbyWorkDay.size()-1).getId());
                    wzbyHIndex = ryIdIndexMap.get(wzbyHDay.get(wzbyHDay.size()-1).getId());
                }
            }else{
                wzbyWIndex = ryIdIndexMap.get(dutyFlag.getWzbyWFlag());
                wzbyHIndex = ryIdIndexMap.get(dutyFlag.getWzbyHFlag());
            }



            DutyQueryDto dutyQueryDto5= new DutyQueryDto();
            dutyQueryDto5.setCusNumber(cusNumber);
            dutyQueryDto5.setJob("男值班员");
            dutyQueryDto5.setJobName("值班员");
            dutyQueryDto5.setDutyDate(wDate);

            List<LastDutyRyDto> mzbyWorkDay= ryglMapper.selectLastDutyRyDtoByDutyQueryDto(dutyQueryDto5);
            List<RyglEntity> mzbyHList = reversalMzbzRy(mzbyRy);
            //男值班员
            if(mzbyWorkDay.size() > 0){
                if(dutyLastFlagEntities.size()>0){
                    //工作日
                    mzbyWIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbyWId());
                    //节假日
                    mzbyHIndex = getmzbzIndex(mzbyHList, dutyLastFlagEntities.get(0).getMzbyHId());
                    //法定节假日
                    mzbyFIndex = ryIdIndexMap.get(dutyLastFlagEntities.get(0).getMzbyFId());
                }else{
                    mzbyWIndex = ryIdIndexMap.get(mzbyWorkDay.get(mzbyWorkDay.size()-1).getId());
                    //mzbyHIndex = ryIdIndexMap.get(mzbyHDay.get(mzbyHDay.size()-1).getId());
                }
            }else{
                //男值班员索引
                mzbyWIndex = ryIdIndexMap.get(dutyFlag.getMzbyWFlag());
                mzbyHIndex =ryIdIndexMap.get(dutyFlag.getMzbyHFlag());
            }

            //找到男值班员  工作日标识 下标表示人员的序号
            int[] mzbyWorkdayOrderFlag = getOrderNameFlag(mzbyRy, cusNumber, "0");
            //节假日 下标表示人员的序号
            int[] mzbyHolidayOrderFlag = getOrderNameFlag(mzbyRy, cusNumber, "1");
            //男值班员法定节假日
            int[] mzbyFOrderFlag = getOrderNameFlag(mzbyRy, cusNumber, "2");


            //找到男值班长 工作日标识 下标表示人员的序号
            int[] mzbzWorkdayOrderFlag = getOrderNameFlag(mzbzRy, cusNumber, "0");
            //找到男值班长 节假日 下标表示人员的序号
            int[] mzbzHolidayOrderFlag = getOrderNameFlag(mzbzRy, cusNumber, "1");
            //男值班长法定节假日
            int[] mzbzFOrderFlag = getOrderNameFlag(mzbzRy, cusNumber, "2");



            for (int i = 0; i < diff + 1; i++) {
                Map<String, Object> dutyPolices = new HashMap<String, Object>();
                String dutyDate =sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
                //排指挥长  1.先确定指挥长从哪个人排起  2.过滤掉当天不值班的人
                zhzIndex = startDuty(zhzIndex,zhzRy,dutyDate,dutyPolices,RyglConstant.ZHZ);

                //这里分三轮 工作日  节假日  法定节假日
                if(checkIsDay(dutyDate)==0){//如果是工作日
                    wzbzWIndex = startDuty(wzbzWIndex, wzbzRy, dutyDate, dutyPolices,RyglConstant.ZZBZ);
                }else if(checkIsDay(dutyDate)==1){//节假日
                    wzbzHIndex = startDuty(wzbzHIndex, wzbzHList, dutyDate, dutyPolices,RyglConstant.ZZBZ);
                } else if(checkIsDay(dutyDate)==2){//法定节假日
                    wzbzFIndex = startDuty(wzbzFIndex,wzbzRy,dutyDate,dutyPolices,RyglConstant.ZZBZ);
                }

                // 早班值班员-> 女值班员
                if(checkIsDay(dutyDate)==0){
                    wzbyWIndex = startDuty(wzbyWIndex, wzbyRy, dutyDate, dutyPolices,RyglConstant.ZZBY);
                }else if(checkIsDay(dutyDate)==1){
                    wzbyHIndex = startDuty(wzbyHIndex, wzbyHList, dutyDate, dutyPolices,RyglConstant.ZZBY);
                }else if(checkIsDay(dutyDate)==2){
                    wzbyFIndex = startDuty(wzbyFIndex, wzbyRy, dutyDate, dutyPolices,RyglConstant.ZZBY);
                }


                //中值班员长和晚值班长
                if(checkIsDay(dutyDate)==0){
                    mzbzWIndex = startMidAndNignt(null,null,mzbzWorkdayOrderFlag,mzbzRy,mzbzWIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBZ,RyglConstant.WZBZ);
                }else if(checkIsDay(dutyDate)==1){
                    mzbzHIndex = startMidAndNignt(null,null,mzbzHolidayOrderFlag,mzbzRy,mzbzHIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBZ,RyglConstant.WZBZ);
                }else if(checkIsDay(dutyDate)==2){
                    mzbzFIndex = startMidAndNignt(null,null,mzbzFOrderFlag,mzbzHList,mzbzFIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBZ,RyglConstant.WZBZ);
                }

                //中值班员 和晚值班员
                if(checkIsDay(dutyDate)==0){
                    mzbyWIndex = startMidAndNignt(null,null,mzbyWorkdayOrderFlag,mzbyRy,mzbyWIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBY,RyglConstant.WZBY);
                }else if(checkIsDay(dutyDate)==1){
                    mzbyHIndex = startMidAndNignt(null,null,mzbyHolidayOrderFlag,mzbyHList,mzbyHIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBY,RyglConstant.WZBY);
                }else if(checkIsDay(dutyDate)==2){
                    mzbyFIndex = startMidAndNignt(null,null,mzbyFOrderFlag,mzbyRy,mzbyFIndex,i,dutyDate,dutyPolices,RyglConstant.ZHZBY,RyglConstant.WZBY);
                }
                orderData.add(dutyPolices);
            }
            //保存最后标记数据
            //调整为发布排班表之后再保存
            //避免因为调整排班人员的情况
            ////////saveLastDutyFlagNew(orderData,startDate,endDate,diff,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reslutMap.put("orderData",orderData);
        reslutMap.put("noZhzMessage",noZhzMessage);
        return reslutMap;
    }

    private String getStringValue(Object value) {
        String result = "";
        if (value instanceof BigDecimal) {
            result = ((BigDecimal)value).toString();
        } else if (value instanceof Long) {
            result = ((Long)value).toString();
        } else if (value instanceof Integer) {
            result = ((Integer)value).toString();
        } else if (value instanceof Double) {
            result = ((Double)value).toString();
        } else if (value instanceof Float) {
            result = ((Float)value).toString();
        } else if (value instanceof String) {
            result = (String)value;
        }
        return result;
    }

    /**
     * 发布值班表时更新
     * @param mbbm
     * @throws Exception
     */
    @Override
    @Transactional
    public void updateLastDutyFlagNew(MbbmEntity mbbm) throws Exception {
        List<Map<String, Object>> list = mbszMapper.findOrderData(mbbm.getId());
        Map<String, List<Map<String, Object>>> groupBySqlno =
                list.stream().collect(Collectors.groupingBy(e -> getStringValue(e.get("SQLNO"))));
        List<Map<String, Object>> orderData = new ArrayList<Map<String, Object>>();
        int length = groupBySqlno.get("1").size();
        for (int i = 0; i < length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("zhz", groupBySqlno.get("1").get(i).get("ID") + "&" + groupBySqlno.get("1").get(i).get("NAME"));
            map.put("zzbz", groupBySqlno.get("2").get(i).get("ID") + "&" + groupBySqlno.get("2").get(i).get("NAME"));
            map.put("zzby", groupBySqlno.get("3").get(i).get("ID") + "&" + groupBySqlno.get("3").get(i).get("NAME"));
            map.put("zhzbz", groupBySqlno.get("4").get(i).get("ID") + "&" + groupBySqlno.get("4").get(i).get("NAME"));
            map.put("zhzby", groupBySqlno.get("5").get(i).get("ID") + "&" + groupBySqlno.get("5").get(i).get("NAME"));
            map.put("wzbz", groupBySqlno.get("6").get(i).get("ID") + "&" + groupBySqlno.get("6").get(i).get("NAME"));
            map.put("wzby", groupBySqlno.get("7").get(i).get("ID") + "&" + groupBySqlno.get("7").get(i).get("NAME"));
            orderData.add(map);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = mbbm.getDmdStartDate();
        Date end = mbbm.getDmdEndDate();
        String dateDiff = CommonUtil.getDateDiff(start, end);
        Integer diff = Integer.parseInt(dateDiff);
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        saveLastDutyFlagNew(orderData, format.format(start), format.format(end), diff, user);
    }

    @Transactional
    public void saveLastDutyFlagNew(List<Map<String, Object>> orderData, String startDate, String endDate, Integer diff, UserBean user) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> map = getLastWdayAndHDayByStartDateAndEndDate(startDate, endDate);
        //本次排班的最后工作日期
        String lastWday = map.get("lastWday");
        //本次排班的最后节假日期
        String lastHday = map.get("lastHday");
        //map.get("lastFday");
        DutyLastFlagEntity dutyLastFlagEntity = new DutyLastFlagEntity();
        dutyLastFlagEntity.setWorkday(lastWday);
        dutyLastFlagEntity.setHoliday(lastHday);
        dutyLastFlagEntity.setCusNumber(user.getCusNumber());
        //避免重复保存数据 先查询 删除后再新增
        List<DutyLastFlagEntity> dutyLastFlagEntities = dutyLastFlagMapper.selectByEntity(dutyLastFlagEntity);
        for (DutyLastFlagEntity dlf : dutyLastFlagEntities) {
            dutyLastFlagMapper.delete(dlf.getId());
        }
        DutyLastFlagEntity dutyLastFlagEntity1 =new DutyLastFlagEntity();
        dutyLastFlagEntity1.setWorkday(lastWday);
        dutyLastFlagEntity1.setHoliday(lastHday);
        dutyLastFlagEntity1.setZhzId(orderData.get(orderData.size()-1).get(RyglConstant.ZHZ).toString().split("&")[0]);
        dutyLastFlagEntity1.setCusNumber(user.getCusNumber());
        dutyLastFlagEntity1.setGxDate(new Date());
        dutyLastFlagEntity1.setGxr(user.getUserName());
        dutyLastFlagEntity1.setGxrId(user.getUserId());
        if(StringUtil.isNull(map.get("lastFday"))){//为空,最后标记的最后一条去找
            List<DutyLastFlagEntity> dutyLastFlagEntities1 = dutyLastFlagMapper.selectListByOrderByGxDate(dutyLastFlagEntity);
            dutyLastFlagEntity1.setWzbzFId(dutyLastFlagEntities1.get(dutyLastFlagEntities1.size()-1).getWzbzFId());
            dutyLastFlagEntity1.setWzbyFId(dutyLastFlagEntities1.get(dutyLastFlagEntities1.size()-1).getWzbyFId());
            dutyLastFlagEntity1.setMzbzFId(dutyLastFlagEntities1.get(dutyLastFlagEntities1.size()-1).getMzbzFId());
            dutyLastFlagEntity1.setMzbyFId(dutyLastFlagEntities1.get(dutyLastFlagEntities1.size()-1).getMzbyFId());
            for (int i = 0; i < diff+1; i++) {
                String dutyDate = sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
                if(dutyDate.equals(lastWday)){//保存工作日的人员id
                    dutyLastFlagEntity1.setWzbzWId(orderData.get(i).get(RyglConstant.ZZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setWzbyWId(orderData.get(i).get(RyglConstant.ZZBY).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzWId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBZ).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBZ).toString().split("&")[0]));
                    dutyLastFlagEntity1.setMzbyWId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBY).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBY).toString().split("&")[0]));
                }
                if(dutyDate.equals(lastHday)){//保存节假日的人员id
                    dutyLastFlagEntity1.setWzbzHId(orderData.get(i).get(RyglConstant.ZZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setWzbyHId(orderData.get(i).get(RyglConstant.ZZBY).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzHId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBZ).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBZ).toString().split("&")[0]));
                    dutyLastFlagEntity1.setMzbyHId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBY).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBY).toString().split("&")[0]));
                }
            }
        }else{
            for (int i = 0; i < diff+1; i++) {
                String dutyDate = sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
                if(dutyDate.equals(lastWday)){//保存工作日的人员id
                    dutyLastFlagEntity1.setWzbzWId(orderData.get(i).get(RyglConstant.ZZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setWzbyWId(orderData.get(i).get(RyglConstant.ZZBY).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzWId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBZ).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBZ).toString().split("&")[0]));
                    dutyLastFlagEntity1.setMzbyWId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBY).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBY).toString().split("&")[0]));
                }
                if(dutyDate.equals(lastHday)){//保存节假日的人员id
                    dutyLastFlagEntity1.setWzbzHId(orderData.get(i).get(RyglConstant.ZZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setWzbyHId(orderData.get(i).get(RyglConstant.ZZBY).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzHId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBZ).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBZ).toString().split("&")[0]));
                    dutyLastFlagEntity1.setMzbyHId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBY).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBY).toString().split("&")[0]));
                }

                if(dutyDate.equals(map.get("lastFday"))){//保存法定节假日
                    dutyLastFlagEntity1.setWzbzFId(orderData.get(i).get(RyglConstant.ZZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setWzbyFId(orderData.get(i).get(RyglConstant.ZZBY).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzFId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBZ).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBZ).toString().split("&")[0]));
                    dutyLastFlagEntity1.setMzbyFId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBY).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBY).toString().split("&")[0]));

                }
            }
        }
        dutyLastFlagMapper.insert(dutyLastFlagEntity1);
    }


    /**
     *
     * @param orderData 排好的值班数据
     * @param zbIndexByZhz  存的是指挥长值中班的所有日期
     */
    @Transactional
    public void  saveLastDutyFlag(List<Map<String,Object>> orderData,List<String> zbIndexByZhz,String startDate,String endDate,Integer diff,UserBean user)throws  Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //指挥长值中班的最后日期
        String zhzMidLastDutyDate =zbIndexByZhz.get(zbIndexByZhz.size()-1);
        Map<String, String> map = getLastWdayAndHDayByStartDateAndEndDate(startDate, endDate);
        //本次排班的最后工作日期
        String lastWday = map.get("lastWday");
        //本次排班的最后节假日期
        String lastHday = map.get("lastHday");

        DutyLastFlagEntity dutyLastFlagEntity = new DutyLastFlagEntity();
        dutyLastFlagEntity.setWorkday(lastWday);
        dutyLastFlagEntity.setHoliday(lastHday);
        dutyLastFlagEntity.setCusNumber(user.getCusNumber());
        //避免重复保存数据 先查询 有就不保存  没有保存数据
        List<DutyLastFlagEntity> dutyLastFlagEntities = dutyLastFlagMapper.selectByEntity(dutyLastFlagEntity);
        if(dutyLastFlagEntities.size() <=0){//保存数据
            DutyLastFlagEntity dutyLastFlagEntity1 =new DutyLastFlagEntity();
            dutyLastFlagEntity1.setWorkday(lastWday);
            dutyLastFlagEntity1.setHoliday(lastHday);
            dutyLastFlagEntity1.setZhzId(orderData.get(orderData.size()-1).get(RyglConstant.ZHZ).toString().split("&")[0]);
            dutyLastFlagEntity1.setCusNumber(user.getCusNumber());
            dutyLastFlagEntity1.setGxDate(new Date());
            dutyLastFlagEntity1.setGxr(user.getUserName());
            dutyLastFlagEntity1.setGxrId(user.getUserId());
            for (int i = 0; i < diff+1; i++) {
                String dutyDate = sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
                if(dutyDate.equals(zhzMidLastDutyDate)){//存指挥长值中班的人员id
                    dutyLastFlagEntity1.setZhzMidId(orderData.get(i).get(RyglConstant.ZHZ).toString().split("&")[0]);
                }

                if(dutyDate.equals(lastWday)){//保存工作日的人员id
                    dutyLastFlagEntity1.setWzbzWId(orderData.get(i).get(RyglConstant.ZZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setWzbyWId(orderData.get(i).get(RyglConstant.ZZBY).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzWMidId(orderData.get(i).get(RyglConstant.ZHZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzWNightId(orderData.get(i).get(RyglConstant.WZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbyWId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBY).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBY).toString().split("&")[0]));
                }
                if(dutyDate.equals(lastHday)){//保存节假日的人员id
                    dutyLastFlagEntity1.setWzbzHId(orderData.get(i).get(RyglConstant.ZZBZ).toString().split("&")[0]);
                    dutyLastFlagEntity1.setWzbyHId(orderData.get(i).get(RyglConstant.ZZBY).toString().split("&")[0]);
                    dutyLastFlagEntity1.setMzbzHId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBZ).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBZ).toString().split("&")[0]));
                    dutyLastFlagEntity1.setMzbyHId(getMaxPbOder(orderData.get(i).get(RyglConstant.ZHZBY).toString().split("&")[0],orderData.get(i).get(RyglConstant.WZBY).toString().split("&")[0]));
                }


            }
            dutyLastFlagMapper.insert(dutyLastFlagEntity1);
        }

    }


    /**
     * 获取两个值班人员最排序号较大的人的id
     * @param id1 人员id1
     * @param id2
     * @return
     */
    public String getMaxPbOder(String id1,String id2){
        RyglEntity ry1 = ryglMapper.getById(id1);
        RyglEntity ry2 = ryglMapper.getById(id2);
        if(ry1.getPbOrder() > ry2.getPbOrder()){
            return id1;
        }else{
            return id2;
        }
    }




    /**
     * 获取两个日期之间的最后工作日和节假日
     * @param startDate  yyyy-MM-dd
     * @param endDate
     * @return
     */
    private Map<String, String> getLastWdayAndHDayByStartDateAndEndDate(String startDate,String endDate) throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> map  =new HashMap<>();
        Date start = DateUtils.parseDate(startDate);
        Date end = DateUtils.parseDate(endDate);
        String dateDiff = CommonUtil.getDateDiff(start, end);
        Integer diff = Integer.parseInt(dateDiff);//相隔天数
        List<String> wDays =new ArrayList<String>();
        List<String> hDays =new ArrayList<String>();
        List<String> fDays =new ArrayList<String>();
        for (int i = 0; i < diff + 1; i++) {
            String dutyDate = sdf.format(CommonUtil.addDate(start, i));
            if(checkIsDay(dutyDate)==0){//是工作日
                wDays.add(dutyDate);
            }else if(checkIsDay(dutyDate)==1){//节假日
                hDays.add(dutyDate);
            }else if(checkIsDay(dutyDate)==2){//法定节假日
                fDays.add(dutyDate);
            }
        }
        //取出最后的工作日和节假日
        map.put("lastWday",wDays.get(wDays.size()-1));
        map.put("lastHday",hDays.get(hDays.size()-1));
        if(fDays.size()>0){
            map.put("lastFday",fDays.get(fDays.size()-1));
        }
        return map;

    }




    /**
     * 用于 男值班员 值班和 男值班长值班
     * 标记每个人的最后值班班次  中班 还是晚班
     * @param rylist
     * @param cusNumber
     * @param flag  0 工作日  1.节假日
     * @return  int[]  值为0 表示 没有值过班  1 中班 2.晚班
     */
    private int[] getOrderNameFlag(List<RyglEntity> rylist,String cusNumber,String flag){
        int[] arr = new int[rylist.size()];
        for (int i = 0; i < rylist.size(); i++) {
            List<DutyQueryDto> dutyQueryDtos = ryglMapper.selectOrderNameByIdAndFlag(cusNumber, rylist.get(i).getId(), flag);
            if(dutyQueryDtos.size()>0){
                if("晚班".equals(dutyQueryDtos.get(dutyQueryDtos.size()-1).getDutyOrderName())){
                    arr[i] = 2;
                }else if("中班".equals(dutyQueryDtos.get(dutyQueryDtos.size()-1).getDutyOrderName())){
                    arr[i] = 1;
                }
            }
        }
        return  arr;
    }

    /**
     * 反转男值班长的数据
     * @param mzbzRy
     * @return
     */
    private List<RyglEntity> reversalMzbzRy(List<RyglEntity> mzbzRy){
        List<RyglEntity> list = new ArrayList<>();
        for (int i = mzbzRy.size()-1; i >=0 ; i--) {
            list.add(mzbzRy.get(i));
        }
        return list;
    }


    /**
     * 找到值班长在集合中的索引位置
     * @param mzbzRy
     * @return
     */
    private int getmzbzIndex(List<RyglEntity> mzbzRy,String ryId){
        int index = -1;
        for (int i = 0; i <mzbzRy.size() ; i++) {
            if(mzbzRy.get(i).getId().equals(ryId)){
                index = i;
                break;
            }
        }
        return index;
    }




    /**
     * 获取打印数据
     * @param mbxqList
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> getPrintData(List<MbxqEntity> mbxqList, String id)  {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ZbbpEntity zbbpEntity = new ZbbpEntity();
        List<Map<String, Object>> orderPolices = new ArrayList<>();
        List<Map<String, Object>> temp1 = new ArrayList<>();
        List<Map<String, Object>> temp2 = new ArrayList<>();
        List<Map<String, Object>> temp3 = new ArrayList<>();
        List<Map<String, Object>> temp4 = new ArrayList<>();
        List<Map<String, Object>> temp5 = new ArrayList<>();
        List<Map<String, Object>> temp6 = new ArrayList<>();
        List<Map<String, Object>> temp7 = new ArrayList<>();
        try {
            for (int i = 0; i < mbxqList.size(); i++) {
                zbbpEntity.setDbdDutyModeDepartmentId(id); //模板部门表的主键
                zbbpEntity.setDbdDutyModeOrderJobId(mbxqList.get(i).getId());
                List<ZbbpEntity> zbbpLists =	zbbpMapper.findAllList(zbbpEntity);
                if(i==0){
                    for(ZbbpEntity zbbpEntity1:zbbpLists){//指挥长
                        Map<String, Object> map = new HashMap<>();
                        map.put("dutyDate",sdf.format(zbbpEntity1.getDbdDutyDate()));
                        map.put("week",getWeek(sdf.format(zbbpEntity1.getDbdDutyDate())));
                        map.put(RyglConstant.ZHZ, formatString(zbbpEntity1.getDbdStaffName()));
                        temp1.add(map);
                    }
                }else if(i==1){//早值班长
                    for(ZbbpEntity zbbpEntity1:zbbpLists){//指挥长
                        Map<String, Object> map = new HashMap<>();
                        map.put(RyglConstant.ZZBZ, formatString(zbbpEntity1.getDbdStaffName()));
                        temp2.add(map);
                    }
                }else if(i==2){//早值班员
                    for(ZbbpEntity zbbpEntity1:zbbpLists){//指挥长
                        Map<String, Object> map = new HashMap<>();
                        map.put(RyglConstant.ZZBY, formatString(zbbpEntity1.getDbdStaffName()));
                        temp3.add(map);
                    }
                }else if(i==3){//中值班长
                    for(ZbbpEntity zbbpEntity1:zbbpLists){//指挥长
                        Map<String, Object> map = new HashMap<>();
                        map.put(RyglConstant.ZHZBZ, formatString(zbbpEntity1.getDbdStaffName()));
                        temp4.add(map);
                    }
                }else if(i==4){//中值班员
                    for(ZbbpEntity zbbpEntity1:zbbpLists){//指挥长
                        Map<String, Object> map = new HashMap<>();
                        map.put(RyglConstant.ZHZBY, formatString(zbbpEntity1.getDbdStaffName()));
                        temp5.add(map);
                    }
                }else if(i==5){//晚值班长
                    for(ZbbpEntity zbbpEntity1:zbbpLists){//指挥长
                        Map<String, Object> map = new HashMap<>();
                        map.put(RyglConstant.WZBZ, formatString(zbbpEntity1.getDbdStaffName()));
                        temp6.add(map);
                    }
                }else if(i==6){//晚值班员
                    for(ZbbpEntity zbbpEntity1:zbbpLists){//指挥长
                        Map<String, Object> map = new HashMap<>();
                        map.put(RyglConstant.WZBY, formatString(zbbpEntity1.getDbdStaffName()));
                        temp7.add(map);
                    }
                }
            }
            for (int j = 0; j <temp1.size() ; j++) {
                temp1.get(j).putAll(temp2.get(j));
                temp1.get(j).putAll(temp3.get(j));
                temp1.get(j).putAll(temp4.get(j));
                temp1.get(j).putAll(temp5.get(j));
                temp1.get(j).putAll(temp6.get(j));
                temp1.get(j).putAll(temp7.get(j));
            }
            orderPolices.addAll(temp1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return orderPolices;
    }

    private String getWeek(String date) throws  Exception{
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int w = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        Date time = sdf.parse(date);
        cal.setTime(time);
        w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        if(!checkIsworkDay(date)){
            return weekDays[w]+"(休)";
        }
        return weekDays[w];
    }


    @Override
    public String countcontinueDuty(List<Map<String, Object>> orderData,String startDate,String endDate)  {
        //RyglConstant.WZBZ
        // RyglConstant.ZHZBZ
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Map<Integer, String> dutyDateMap = createDutyDateMap2(startDate, endDate, sdf);
            for (int i = 0; i <orderData.size()-1; i++) {
                //Util.daysBetween()
                //男值班长值班重复
                if(orderData.get(i).get(RyglConstant.ZHZBZ).toString().equals(orderData.get(i+1).get(RyglConstant.ZHZBZ).toString())){
                    result = result+ getName(orderData.get(i).get(RyglConstant.ZHZBZ).toString())+","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }

                if(orderData.get(i).get(RyglConstant.ZHZBZ).toString().equals(orderData.get(i+1).get(RyglConstant.WZBZ).toString())){
                    result+= getName(orderData.get(i).get(RyglConstant.ZHZBZ).toString())+","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }

                if(orderData.get(i).get(RyglConstant.WZBZ).toString().equals(orderData.get(i+1).get(RyglConstant.ZHZBZ).toString())){
                    result+=getName(orderData.get(i).get(RyglConstant.WZBZ).toString())+","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }

                if(orderData.get(i).get(RyglConstant.WZBZ).toString().equals(orderData.get(i+1).get(RyglConstant.WZBZ).toString())){
                    result +=getName(orderData.get(i).get(RyglConstant.WZBZ).toString()) +","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }
                //男值班员重复
                if(orderData.get(i).get(RyglConstant.ZHZBY).toString().equals(orderData.get(i+1).get(RyglConstant.ZHZBY).toString())){
                    result = result+ getName(orderData.get(i).get(RyglConstant.ZHZBY).toString())+","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }

                if(orderData.get(i).get(RyglConstant.ZHZBY).toString().equals(orderData.get(i+1).get(RyglConstant.WZBY).toString())){
                    result+= getName(orderData.get(i).get(RyglConstant.ZHZBY).toString())+","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }

                if(orderData.get(i).get(RyglConstant.WZBY).toString().equals(orderData.get(i+1).get(RyglConstant.ZHZBY).toString())){
                    result+=getName(orderData.get(i).get(RyglConstant.WZBY).toString())+","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }

                if(orderData.get(i).get(RyglConstant.WZBY).toString().equals(orderData.get(i+1).get(RyglConstant.WZBY).toString())){
                    result +=getName(orderData.get(i).get(RyglConstant.WZBY).toString()) +","+dutyDateMap.get(i)+","+dutyDateMap.get(i+1)+";";
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String selectNodutyByStartDateAndEndDate(String mojStartDate, String mojEndDate) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        List<RyglEntity> ryglEntities = ryglMapper.selectByNodutyByStartDate(user.getCusNumber(), mojStartDate);
        String noDuty = "";
        if(ryglEntities.size() >0){
            for(RyglEntity ryglEntity:ryglEntities){
                noDuty += ryglEntity.getName() + ",原因:" + converToName(ryglEntity.getRyzt(), ryglEntity.getRemark()) + ",日期:" + ryglEntity.getStartDate() + "至" + ryglEntity.getEndDate()+";";
            }
        }
        return noDuty;
    }

    private String converToName(String ryzt,String remark){
        String str = "";
        if(StringUtil.isNull(remark)){
            if("4".equals(ryzt)){
                str ="培训";
            }else if("5".equals(ryzt)){
                str ="病假";
            }else if("6".equals(ryzt)){
                str ="其他";
            }
        }else{
            if("4".equals(ryzt)){
                str ="培训,"+remark;
            }else if("5".equals(ryzt)){
                str ="病假,"+remark;
            }else if("6".equals(ryzt)){
                str ="其他,"+remark;
            }

        }
        return str;
    }



    private String getName(String idsAndName){
        String name = idsAndName.split("&")[1];
        return name;
    }




    /**
     * 给只有两个汉字的姓名中间加个空格
     * @param str
     * @return
     */
    private String formatString(String str){
        if(str.length()==2){
            String s2 =str.substring(0,str.length()-1)+" "+str.substring(str.length()-1);
            return s2;
        }
        return str;
    }

    /**
     * 找到指挥长在这次值班表中可以排中班值班长的位置
     * @param zhzZbFlag
     * @param zhzZbFlagDutyDate
     * @param startDate
     * @param endDate
     * @param sdf
     * @param zhzOrderData
     * @param zhzRy
     * @param lastResult
     * @return reslut 表示可以排的日期位置
     * @throws Exception
     */
    private List<String>  getZBIndexByZhz(String zhzZbFlag, String zhzZbFlagDutyDate,String startDate,String endDate,SimpleDateFormat sdf,List<RyglEntity> zhzOrderData,List<RyglEntity> zhzRy,List<String> lastResult) throws Exception{
        //获取第一次可以排的 这个人在zhzOrderData 的位置索引
        Integer inIndex = 0;
        List<String> reslut =new ArrayList<>();//表示可以排的日期位置
        int tempIndex =0;
        Map<String, Integer> dutyDateMap = createDutyDateMap(startDate, endDate, sdf);
        if(ContainsId(zhzOrderData,zhzZbFlag)==-1){//没有找到 取下一个
            //一直循环直到在zhzOrderData找到为止
            inIndex = getinitIndex(zhzOrderData,zhzRy,zhzZbFlag);//zhzOrderData 这个的索引对应着排班的日期
            //开始找可以排班的位置 可以排班的这天是否是zhzZbFlagDutyDate 的下一周,
            String nextWeekFirstDay = getNextWeekFirstDay(zhzZbFlagDutyDate, sdf);
            // temp = dutyDateMap.get(nextWeekFirstDay);
            //loopDutyZhz(nextWeekFirstDay,startDate,dutyDateMap,zhzOrderData,reslut,endDate,sdf,lastResult);
        }else{//找到了
            if(ContainsId(zhzOrderData,zhzZbFlag) ==zhzOrderData.size()-1){
                inIndex =0;
            }
            inIndex =ContainsId(zhzOrderData,zhzZbFlag)+1;
            String nextWeekFirstDay = getNextWeekFirstDay(zhzZbFlagDutyDate, sdf);
            //loopDutyZhz(nextWeekFirstDay,startDate,dutyDateMap,zhzOrderData,reslut,endDate,sdf,lastResult);
        }

        return reslut;

    }



    private List<String>  getZBIndexByZhz2(Integer zhzMidIndex, String zhzZbFlagDutyDate,String startDate,String endDate,SimpleDateFormat sdf,List<RyglEntity> zhzOrderData,List<RyglEntity> zhzDutyMidOrderRy ) throws Exception{
        //获取第一次可以排的 这个人在zhzOrderData 的位置索引
        Integer inIndex = 0;
        List<String> reslut =new ArrayList<>();//表示可以排的日期位置
        int tempIndex =0;
        Map<String, Integer> dutyDateMap = createDutyDateMap(startDate, endDate, sdf);

        //检查指挥长是否可以排班
        String nextWeekFirstDay = getNextWeekFirstDay(zhzZbFlagDutyDate, sdf);
        loopDutyZhz(nextWeekFirstDay,startDate,dutyDateMap,zhzOrderData,reslut,endDate,sdf,zhzMidIndex,zhzDutyMidOrderRy);
        return reslut;

    }


    private List<String> convertToLastResult(List<LastDutyRyDto> lastDutyRyDtos){
        List<String> ryIds = new ArrayList<>();
        if(lastDutyRyDtos.size()<=0){
            return ryIds;
        }
        for(LastDutyRyDto lastDutyRyDto:lastDutyRyDtos){
            ryIds.add(lastDutyRyDto.getId());
        }
        return ryIds;
    }

    /**
     * 循环排指挥长排中班
     * @param nextWeekFirstDay 下周第一天
     * @param
     * @param zhzOrderData  已经排好的指挥长数据
     * @param
     * @param reslut 记录总的排班的位置结果
     * @param
     * @param endDate  排班的结束日期
     * @throws Exception
     */
    private void  loopDutyZhz(String nextWeekFirstDay, String startDate,Map<String, Integer> dutyDateMap,List<RyglEntity> zhzOrderData,List<String> reslut,String endDate,SimpleDateFormat sdf,Integer zhzMidIndex,List<RyglEntity> zhzDutyMidOrderRy) throws Exception{
        while (true){
            if(sdf.parse(nextWeekFirstDay).after(sdf.parse(endDate)) || nextWeekFirstDay.equals(endDate)){
                break;
            }
            if(getWeekWorkDayCount(nextWeekFirstDay)>0){//本周要排
                Map<String, Object> nextWeekFirstDay3 = getNextWeekFirstDay3(nextWeekFirstDay, startDate, endDate, dutyDateMap, zhzOrderData, sdf, zhzMidIndex, zhzDutyMidOrderRy);
                zhzMidIndex = (Integer) nextWeekFirstDay3.get("zhzMidIndex");
                nextWeekFirstDay = (String)nextWeekFirstDay3.get("nextWeekFirstDay");
                reslut.add((String) nextWeekFirstDay3.get("date"));
            }
            else{
                //跳过从下周一开始
                nextWeekFirstDay =  getNextWeekFirstDay(nextWeekFirstDay,sdf);
            }

        }
    }

    private boolean checkEndDate(String nextWeekFirstDay,String endDate, Integer zhzMidIndex,List<RyglEntity> zhzDutyMidOrderRy,Map<String, Integer> dutyDateMap,List<RyglEntity> zhzOrderData) throws Exception{

        boolean flag= false;
        if(nextWeekFirstDay.equals(endDate)){
            if(!zhzOrderData.get(dutyDateMap.get(endDate)).getId().equals(zhzDutyMidOrderRy.get(zhzMidIndex))){
                flag = true;
            }
        }
        return flag;
    }


    //方案三
    private Map<String,Object>  getNextWeekFirstDay3(String nextWeekFirstDay, String startDate,String endDate ,Map<String, Integer> dutyDateMap,List<RyglEntity> zhzOrderData,SimpleDateFormat sdf,Integer zhzMidIndex,List<RyglEntity> zhzDutyMidOrderRy) throws Exception{
        //if(checkIsworkDay(nextWeekFirstDay)){//获取这周的几个工作日SimpleDateFormat sdf
        Map<String,Object> resultMap = new HashMap<>();
        List<String> weekWorkDays = getWeekWorkDays(nextWeekFirstDay);
        //找到本周已经排过班的指挥长  正好对应 zhzDutyMidOrderRy  zhzMidIndex 位置的人
        // if(weekWork)

        if(weekWorkDays.contains(startDate)){
            //获取从开始日期的本周的工作日
            List<String> weekWorkDays2  = new ArrayList<>();
            int temp = 0;
            for (int i = 0; i <weekWorkDays.size() ; i++) {
                if(weekWorkDays.get(i).equals(startDate)){
                    temp = i;
                    break;
                }
            }
            //获取之后的所有的日期
            for (int i = temp; i < weekWorkDays.size(); i++) {
                weekWorkDays2.add(weekWorkDays.get(i));
            }
            Map<String, String> ryIdToDateMap = createryIdToDutyDateMap(startDate, endDate, sdf, zhzOrderData,weekWorkDays2);
            List<String> weekWorkDayToRyId = getWeekWorkDayToRyId(dutyDateMap, weekWorkDays2, zhzOrderData);
            Map<String, Object> zhzDutyMidDate = getZhzDutyMidDate(weekWorkDayToRyId, zhzMidIndex, zhzDutyMidOrderRy, ryIdToDateMap);
            resultMap.put("date", zhzDutyMidDate.get("date"));
            resultMap.put("zhzMidIndex", zhzDutyMidDate.get("zhzMidIndex"));
        }else{
            Map<String, String> ryIdToDateMap = createryIdToDutyDateMap(startDate, endDate, sdf, zhzOrderData,weekWorkDays);
            List<String> weekWorkDayToRyId = getWeekWorkDayToRyId(dutyDateMap, weekWorkDays, zhzOrderData);
            Map<String, Object> zhzDutyMidDate = getZhzDutyMidDate(weekWorkDayToRyId, zhzMidIndex, zhzDutyMidOrderRy, ryIdToDateMap);
            resultMap.put("date", zhzDutyMidDate.get("date"));
            resultMap.put("zhzMidIndex", zhzDutyMidDate.get("zhzMidIndex"));
        }
        //List<String> weekWorkDayToRyId = getWeekWorkDayToRyId(dutyDateMap, weekWorkDays, zhzOrderData);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        nextWeekFirstDay = getNextWeekFirstDay(nextWeekFirstDay, s);
        resultMap.put("nextWeekFirstDay", nextWeekFirstDay);
        return resultMap;
    }




    private Map<String,Object>  getZhzDutyMidDate(List<String> weekWorkDayToRyId ,Integer zhzMidIndex,List<RyglEntity> zhzDutyMidOrderRy,Map<String, String> ryIdToDateMap){
        Map<String,Object> map = new HashMap<>();
        String date = "";
        Integer temp = 0;
        while(true){
            if(zhzMidIndex >= zhzDutyMidOrderRy.size()){//越界
                zhzMidIndex = 0;
            }
            if(weekWorkDayToRyId.contains(zhzDutyMidOrderRy.get(zhzMidIndex).getId())){
                //那就排这个人
                String ryId = zhzDutyMidOrderRy.get(zhzMidIndex).getId();
                date =  ryIdToDateMap.get(ryId);
                temp = ++zhzMidIndex;
                break;
            }else{
                temp =zhzMidIndex;
                int getzhzMidIndex = getzhzMidIndex2(weekWorkDayToRyId, zhzMidIndex, zhzDutyMidOrderRy);
                date = ryIdToDateMap.get(zhzDutyMidOrderRy.get(getzhzMidIndex).getId());
                // noZhzMessage.add(map)
                Map<String,Object> tempMap = new HashMap<>();
                tempMap.put("name",zhzDutyMidOrderRy.get(getzhzMidIndex).getName());
                tempMap.put("date",date);
                tempMap.put("id",zhzDutyMidOrderRy.get(getzhzMidIndex).getId());
                noZhzMessage.add(tempMap);
                break;
            }

        }
        map.put("date",date);
        map.put("zhzMidIndex",temp);
        return map;
    }



    private int getzhzMidIndex2(List<String> weekWorkDayToRyId,int zhzMidIndex,List<RyglEntity> zhzDutyMidOrderRy){
        while (true){
            if(zhzDutyMidOrderRy.size() <= zhzMidIndex){
                zhzMidIndex =0;
            }
            if(weekWorkDayToRyId.contains(zhzDutyMidOrderRy.get(zhzMidIndex).getId())){
                //那就排这个人
                break;
            }
            zhzMidIndex++;
        }

        return zhzMidIndex;
    }




    private int getzhzMidIndex(List<String> weekWorkDayToRyId,int zhzMidIndex,List<RyglEntity> zhzDutyMidOrderRy){
        int temp =zhzMidIndex;
        int k =1;
        while (true){
            zhzMidIndex= zhzMidIndex +k;
            if(zhzMidIndex >= zhzDutyMidOrderRy.size()){//越界
                zhzMidIndex = zhzMidIndex-zhzDutyMidOrderRy.size();
            }
            if(weekWorkDayToRyId.contains(zhzDutyMidOrderRy.get(zhzMidIndex).getId())){
                //那就排这个人
                break;
            }
            zhzMidIndex = zhzMidIndex-k;
            if(zhzMidIndex < 0){//越界
                zhzMidIndex = zhzMidIndex+zhzDutyMidOrderRy.size();
            }
            if(weekWorkDayToRyId.contains(zhzDutyMidOrderRy.get(zhzMidIndex).getId())){
                //那就排这个人
                break;
            }
            zhzMidIndex =temp;//还是没有找到就还原


            k++;


        }

        return zhzMidIndex;
    }





    /**
     * 检查本周工作日小于等于3天的时候是否补班情况导致连续工作日的天数大于三天
     * @return
     */
    private boolean checkWeekDayIsDuty(String date,SimpleDateFormat sdf)throws Exception{
        boolean flag = false;
        int count = 0;
        int i =0;
        while (true){//找前一天一直找到节假日为止
            String addDayDate = Util.getAddDayDate(date, sdf, i--);
            if(!checkIsworkDay(addDayDate)){
                break;
            }else{
                count++;
            }
            int sum =  count + getWeekWorkDayCount(date);
            if(sum> 3 ){
                flag =true;
            }
        }
        return flag;
    }


    //真正开始选人的方法选指挥长人员 方案一
    private String  getNextWeekFirstDay(String nextWeekFirstDay, Map<String, Integer> dutyDateMap,List<RyglEntity> zhzOrderData,Integer inIndex,List<Integer> reslut) throws Exception{
        //if(checkIsworkDay(nextWeekFirstDay)){//获取这周的几个工作日
        List<String> weekWorkDays = getWeekWorkDays(nextWeekFirstDay);
        List<String> weekWorkDayToRyId = getWeekWorkDayToRyId(dutyDateMap, weekWorkDays, zhzOrderData);
        //这几个工作日 对应的人按顺序 inIndex 位置去找人 当这个工作日排的人是按顺序找到的那个人,就把本周排的工作日的指挥长就是这天
        //  int  workIndex = 0; //工作日索引
        while (true){
            if(inIndex>zhzOrderData.size()-1){
                inIndex = 0;
            }
            if(weekWorkDayToRyId.contains(zhzOrderData.get(inIndex).getId())){
                break;
            }
            inIndex++;
        }
        for(int i = 0;i < weekWorkDayToRyId.size();i++){
            if(weekWorkDayToRyId.get(i).equals(zhzOrderData.get(inIndex).getId())){
                reslut.add(dutyDateMap.get(weekWorkDays.get(i))) ;//找到本周要第一次要排的位置了
                //  tempIndex = i;
                break;
            }
        }

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        nextWeekFirstDay = getNextWeekFirstDay(nextWeekFirstDay, s);
        return nextWeekFirstDay;
    }



    //方案二
    private String  getNextWeekFirstDay2(String nextWeekFirstDay, String startDate,String endDate ,Map<String, Integer> dutyDateMap,List<RyglEntity> zhzOrderData,List<String> reslut,SimpleDateFormat sdf, List<String> lastResult) throws Exception{
        //if(checkIsworkDay(nextWeekFirstDay)){//获取这周的几个工作日SimpleDateFormat sdf
        List<String> weekWorkDays = getWeekWorkDays(nextWeekFirstDay);
        // String[] strs= new String[2];
        // List<String> weekWorkDayToRyId = getWeekWorkDayToRyId(dutyDateMap, weekWorkDays, zhzOrderData);
        //这几个工作日 对应的人按顺序 inIndex 位置去找人 当这个工作日排的人是按顺序找到的那个人,就把本周排的工作日的指挥长就是这天
        //  int  workIndex = 0; //工作日索引
        Map<String, String> dutyDateToRyIdMap = createDutyDateToRyIdMap(startDate, endDate, sdf, zhzOrderData);
        //剩余的都是可以参与排班的 指挥长总共11人
        if(lastResult.size() <=0){//说明之前没有排过班 只有第一次才会进这个方法
            if (reslut.size() <= 0) {//排第一个
                reslut.add(weekWorkDays.get(0));
            }else{
                List<String> reslutIds = getWeekWorkDayToRyId(dutyDateMap, reslut, zhzOrderData);
                putResult(weekWorkDays,dutyDateMap, reslut,reslutIds, zhzOrderData, dutyDateToRyIdMap,startDate);
            }

        }else{
            if(reslut.size() <= 0 ){//从之前的结果集取最少的去排班
                int dutyByMinCount = dutyByMinCount(lastResult, weekWorkDays, zhzOrderData, dutyDateMap,startDate);

                reslut.add(weekWorkDays.get(dutyByMinCount));
            }else{//把这次排过的人和之前的人进行汇总再取排班次数最小的进行排班

                List<String> weekWorkDayToRyId = getWeekWorkDayToRyId(dutyDateMap, reslut, zhzOrderData);
                lastResult.addAll(weekWorkDayToRyId);//汇总人数
                putResult( weekWorkDays,dutyDateMap, reslut,lastResult, zhzOrderData, dutyDateToRyIdMap,startDate);
                /*int k = 0;
                while (true){
                    if(k ==weekWorkDays.size()-1){
                        //说明结果集都包含了 按结果集的次数最少的开始排起
                        // reslut.add(weekWorkDays.get(random.nextInt(weekWorkDays.size())));
                        //所有结果集包含的人员
                        //去重排序 取到最少的人进行排班
                        int dutyByMinCount = dutyByMinCount(lastResult, weekWorkDays, zhzOrderData, dutyDateMap);
                        reslut.add(weekWorkDays.get(dutyByMinCount));
                        break;
                    }else{
                        if(!lastResult.contains(dutyDateToRyIdMap.get(weekWorkDays.get(k)))){
                            reslut.add(weekWorkDays.get(k));
                            break;
                        }
                    }
                    k++;
                }*/

            }

        }
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        nextWeekFirstDay = getNextWeekFirstDay(nextWeekFirstDay, s);
        return nextWeekFirstDay;
    }







    private void putResult(List<String> weekWorkDays,Map<String, Integer> dutyDateMap,List<String> reslut,List<String> lastResult,List<RyglEntity> zhzOrderData,Map<String, String> dutyDateToRyIdMap,String starDate)throws Exception{
        int i = 0;
        while (true){
            if(i ==weekWorkDays.size()-1){
                //说明结果集都包含了 按结果集的次数最少的开始排起
                // reslut.add(weekWorkDays.get(random.nextInt(weekWorkDays.size())));
                //所有结果集包含的人员
                //去重排序 取到最少的人进行排班
                int dutyByMinCount = dutyByMinCount(lastResult, weekWorkDays, zhzOrderData, dutyDateMap,starDate);
                reslut.add(weekWorkDays.get(dutyByMinCount));
                break;
            }else{
                if(!lastResult.contains(dutyDateToRyIdMap.get(weekWorkDays.get(i)))){
                    reslut.add(weekWorkDays.get(i));
                    break;
                }
            }
            i++;
        }
    }


    /**
     *
     * @param reslutIds
     * @param weekWorkDays
     * @return
     */
    private int  dutyByMinCount(List<String> reslutIds,List<String> weekWorkDays,List<RyglEntity> zhzOrderData,Map<String, Integer> dutyDateMap,String starDate )throws Exception{
        //去重排序
        SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Integer> sortMap = getSortMap(reslutIds);

        List<String> weekWorkDayToRyId = getWeekWorkDayToRyId(dutyDateMap, weekWorkDays, zhzOrderData);
        int k = 0;
        //若出现次数最少这这个人对应的工作日期不在这次排班范围内 从剩下的工作日再找排的次数最少的
        int wIndex =0;
        while (true){
            if(!slf.parse(weekWorkDays.get(wIndex)).before(slf.parse(starDate))){
                break;
            }
            wIndex++;
        }

        if(sortMap.get(weekWorkDayToRyId.get(k)) ==null){
            return k;
        }
        int min = sortMap.get(weekWorkDayToRyId.get(k));
        int minIndex = k;
        for(int j = k ;j <weekWorkDayToRyId.size();j++){
            if(sortMap.get(weekWorkDayToRyId.get(j))==null){
                minIndex = j;
                break;
            }
            if(min>sortMap.get(weekWorkDayToRyId.get(j))){
                min =sortMap.get(weekWorkDayToRyId.get(j));
                minIndex = j ;
            }
        }



        return minIndex+wIndex;
    }

    private Integer getFirstElement(List<String> list1, List<String> list2){
        for(int i =0 ; i < list1.size();i++){
            for(String l2:list2){
                if(list1.get(i).equals(l2)){
                    return i ;
                }
            }
        }
        return 0;
    }



    /**
     * 将集合去重然后按顺序出现的次数 key 对应strs中的元素 value对应出现的次数
     * @param strs
     * @return
     */
    private  Map<String, Integer> getSortMap(List<String> strs) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Set<String> set = new HashSet<String>(strs);
        for (String str : set) {
            for (String lstr : strs) {
                if (str.equals(lstr)) {
                    if (map.containsKey(str)) {
                        Integer count = map.get(str);
                        count++;
                        map.put(str, count);
                    } else {
                        map.put(str, 1);
                    }

                }
            }
        }
        Map<String, Integer> sortMap = sortMapByValue(map);
        return sortMap;
    }


    private  Map<String,Integer> sortMapByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> mapList = new ArrayList<Map.Entry<String, Integer>>(
                map.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        Map<String,Integer> result = new LinkedHashMap<String,Integer>();
        for(Map.Entry<String, Integer> entry:mapList){
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }





    /**
     * java统计List集合中每个元素出现的次数
     * 例如frequencyOfListElements(["111","111","222"])
     *  ->
     * 则返回Map {"111"=2,"222"=1}
     * @param items
     * @return  Map<String,Integer>
     * @author wuqx
     */
    public static Map<String,Integer> frequencyOfListElements( List<String> items ) {
        if (items == null || items.size() == 0) return null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String temp : items) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return map;
    }


    /**
     * 值班日期对应指挥长值班成员 id
     * @param startDate
     * @param endDate
     * @param sdf
     * @param zhzOrderData
     * @return
     * @throws Exception
     */
    private Map<String, String> createDutyDateToRyIdMap(String startDate,String endDate,SimpleDateFormat sdf,List<RyglEntity> zhzOrderData)throws Exception{
        Map<String,String> map = new HashMap<>();
        String dateDiff = CommonUtil.getDateDiff(sdf.parse(startDate), sdf.parse(endDate));
        Integer diff = Integer.parseInt(dateDiff);//相隔天数
        String[] dates = new String[diff+1];
        for (int i = 0;i <dates.length ;i++){
            dates[i] = sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
            map.put(dates[i], zhzOrderData.get(i).getId());
        }
        return map;
    }


    /**
     * 指挥长值班成员 id 对应的值班日期
     * @param startDate
     * @param endDate
     * @param sdf
     * @param zhzOrderData
     * @return
     * @throws Exception
     */
    private Map<String, String> createryIdToDutyDateMap(String startDate,String endDate,SimpleDateFormat sdf,List<RyglEntity> zhzOrderData,List<String> weekWorkDays)throws Exception{
        Map<String,String> map = new HashMap<>();
        String dateDiff = CommonUtil.getDateDiff(sdf.parse(startDate), sdf.parse(endDate));
        Integer diff = Integer.parseInt(dateDiff);//相隔天数
        // String[] dates = new String[diff+1];
        for (int i = 0;i <diff+1 ;i++){
            String date = sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
            if(weekWorkDays.contains(date)){
                map.put(zhzOrderData.get(i).getId(),date);
            }
        }
        return map;
    }





    /**
     * 获取工作日值班对应的人员id
     * @param dutyDateMap
     * @param weekWorkDays
     * @param zhzOrderData
     * @return
     */
    private List<String> getWeekWorkDayToRyId(Map<String, Integer> dutyDateMap, List<String> weekWorkDays,List<RyglEntity> zhzOrderData) {
        List<String> list = new ArrayList<>();
        List<String> date= new ArrayList<>();
        Set<Map.Entry<String, Integer>> entries = dutyDateMap.entrySet();
        for(Map.Entry<String, Integer> entry:entries){
            for(int i=0; i <weekWorkDays.size();i++){
                if(entry.getKey().equals(weekWorkDays.get(i))){
                    date.add(weekWorkDays.get(i));
                    break;
                }
            }
            if(entry.getKey().equals(weekWorkDays.get(weekWorkDays.size()-1))){
                break;
            }
        }

        for(String d:date){
            Integer index = dutyDateMap.get(d);
            list.add(zhzOrderData.get(index).getId());
        }
        // Integer index = dutyDateMap.get(weekWorkDays.get(i));
        return list;

    }






    /**
     * 获取这周的工作日
     * @param date
     * @return
     * @throws Exception
     */
    private List<String>  getWeekWorkDays(String date) throws Exception{
        String[] weekAllDay = Util.getWeekAllDay(date);
        List<String> list = new ArrayList<>();
        for(String day:weekAllDay){
            if(checkIsworkDay(day)){
                list.add(day);
            }
        }
        return list;
    }



    /**
     *获取下周第一天
     * @param date
     * @return
     */
    private String getNextWeekFirstDay(String date,SimpleDateFormat sf) throws Exception{
        String[] weekAllDay = Util.getWeekAllDay(date);
        String lastDay = weekAllDay[weekAllDay.length-1];
        String nextFirstDay = sf.format(CommonUtil.addDate(sf.parse(lastDay), 1));
        return nextFirstDay;
    }


    /**
     * 找到在已经排好的zhzOrderData的指挥长的初始位置
     * @param zhzOrderData
     * @param zhzRy 最大的范围 包含 zhzOrderData
     * @param zhzZbFlag
     * @return
     */
    private int getinitIndex(List<RyglEntity> zhzOrderData,List<RyglEntity> zhzRy,String zhzZbFlag){
        int initIndex = 0;
        int temp = 0;
        while(true){
            int index = ContainsId(zhzRy, zhzZbFlag);
            if(index == zhzRy.size()-1) {
                index =0;
                if(ContainsId(zhzOrderData, zhzRy.get(index).getId())!=-1){
                    temp =ContainsId(zhzOrderData, zhzRy.get(index).getId());
                    break;
                }
            }
            index++;
            if(ContainsId(zhzOrderData, zhzRy.get(index).getId())!=-1){
                temp =ContainsId(zhzOrderData, zhzRy.get(index).getId());
                break;
            }
        }
        if(temp !=zhzOrderData.size()-1){
            initIndex =temp;
        }
        return initIndex;
    }


    /**
     * 返回 id 第一次 出现在 zbrys位置
     * @param zbrys
     * @param id
     * @return
     */
    private int ContainsId(List<RyglEntity> zbrys, String id ){
        int index =-1;
        for(int i =0 ;i < zbrys.size();i++){
            if(zbrys.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }




    /**
     * 获取已经排过的指挥长数据
     * @param orderData
     * @param key
     * @return
     */
    private List<RyglEntity>  getZhzToOrderData(List<Map<String, Object>> orderData,String key){
        List<RyglEntity> list = new ArrayList<>();
        for(Map<String, Object> data:orderData){
            RyglEntity ryglEntity = new RyglEntity();
            String zhzData = data.get(key).toString();
            ryglEntity.setId(zhzData.split("&")[0]);
            ryglEntity.setName(zhzData.split("&")[1]);
            list.add(ryglEntity);
        }
        return list;
    }



    /**
     * 建立存放日期的数组
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    private String[] createDutyDateArray(String startDate,String endDate,SimpleDateFormat sdf) throws Exception{
        String dateDiff = CommonUtil.getDateDiff(sdf.parse(startDate), sdf.parse(endDate));
        Integer diff = Integer.parseInt(dateDiff);//相隔天数
        String[] dates = new String[diff+1];
        for (int i = 0;i <dates.length ;i++){
            dates[i] = sdf.format(CommonUtil.addDate(sdf.parse(startDate), i));
        }

        return  dates;
    }


    /**
     * 建立存放日期的Map
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    private Map<String,Integer> createDutyDateMap(String startDate,String endDate,SimpleDateFormat sdf) throws Exception{
        String dateDiff = CommonUtil.getDateDiff(sdf.parse(startDate), sdf.parse(endDate));
        Map<String,Integer> map = new TreeMap<>();
        Integer diff = Integer.parseInt(dateDiff);//相隔天数
        for (int i = 0;i <diff+1 ;i++){
            map.put(sdf.format(CommonUtil.addDate(sdf.parse(startDate), i)),i);

        }

        return  map;
    }


    /**
     * 建立存放日期的Map
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    private Map<Integer, String> createDutyDateMap2(String startDate,String endDate,SimpleDateFormat sdf) throws Exception{
        String dateDiff = CommonUtil.getDateDiff(sdf.parse(startDate), sdf.parse(endDate));
        Map<Integer, String> map = new TreeMap<>();
        Integer diff = Integer.parseInt(dateDiff);//相隔天数
        for (int i = 0;i <diff+1 ;i++){
            map.put(i,sdf.format(CommonUtil.addDate(sdf.parse(startDate), i)));
        }
        return  map;
    }




    /**
     * 检查两天是否是同一周
     * @param date1 格式yyyy-MM-dd
     * @param date2
     * @return
     */
    private boolean checkDuobleDateIsWeek(String date1,String date2) throws Exception{
        boolean flag = false;
        String[] weekAllDay = Util.getWeekAllDay(date1);
        List<String> list = Arrays.asList(weekAllDay);
        if(list.contains(date2)){
            flag = true;
        }
        return flag;
    }


    /**
     *
     * 获取本周有几个工作日
     * @param dutyDate
     * @return
     */
    private Integer getWeekWorkDayCount(String dutyDate) throws  Exception{
        String[] weekAllDay = Util.getWeekAllDay(dutyDate);
        int workCount =0;//工作日的数量
        for(String day:weekAllDay){
            if(checkIsworkDay(day)){
                workCount++;
            }
        }
        return workCount;
    }

    /**
     *获取下周有几个工作日
     * @param date
     * @return
     */
    private Integer getNextWeekWorkDayCount(String date,SimpleDateFormat sf) throws Exception{
        String[] weekAllDay = Util.getWeekAllDay(date);
        String lastDay = weekAllDay[weekAllDay.length-1];
        Integer count = 0;
        String nextFirstDay = sf.format(CommonUtil.addDate(sf.parse(lastDay), 1));
        for(int i=0;i<weekAllDay.length;i++){
            String dutyDate = sf.format(CommonUtil.addDate(sf.parse(nextFirstDay), i));
            if(checkIsworkDay(dutyDate)){
                count++;
            }
        }
        return count;
    }


    /**
     * 检查当天是否是工作日
     * @param dutyDate
     * @return
     */
    private boolean checkIsworkDay(String dutyDate){
        boolean workDay = false;
        ZbRlwhEntity zbRlwhEntity = new ZbRlwhEntity();
        zbRlwhEntity.setDutyDate(dutyDate);
        List<ZbRlwhEntity> zbRlwhEntities = zbRlwhMapper.selectByEntityOrderBydutyDate(zbRlwhEntity);
        //0.工作日 1.节假日
        String flag = zbRlwhEntities.get(0).getFlag();
        if("0".equals(flag)){
            workDay = true;
        }
        return workDay;
    }


    /**
     * 检查当天是什么日子  0为工作日 1节假日 2.法定节假日
     * @param dutyDate
     * @return
     */
    private Integer checkIsDay(String dutyDate){
        ZbRlwhEntity zbRlwhEntity = new ZbRlwhEntity();
        zbRlwhEntity.setDutyDate(dutyDate);
        List<ZbRlwhEntity> zbRlwhEntities = zbRlwhMapper.selectByEntityOrderBydutyDate(zbRlwhEntity);
        //0.工作日 1.节假日2.法定节假日
        String flag = zbRlwhEntities.get(0).getFlag();

        return Integer.valueOf(flag);
    }


    /**
     * Map 存放 key 为id  value 为 所在集合的索引
     * @param ryIdIndex
     * @param ryList
     */
    private void putMap(Map<String,Integer> ryIdIndex,List<RyglEntity> ryList){
        for (int i=0; i < ryList.size(); i++) {
            ryIdIndex.put(ryList.get(i).getId(),i);
        }
    }

    //男值班长工作日分两轮值班 1.中班按现有工作日顺序  2.晚班按最后一位男值班长逆序排班


    private int[] startWorkDutyMidOrder(List<String> zbIndexByZhz,List<RyglEntity> zhzOrderData,List<RyglEntity> ryList,int mzbzWMidIndex,int dateIndex,int mzbzwNightIndex,String dutyDate,Map<String, Object> dutyPolices,String key1,String key2){
        //先排晚班
        int[] arr = new int[2];
        List<RyglEntity> reverMzbzRy = reversalMzbzRy(ryList);
        mzbzwNightIndex = startDuty(mzbzwNightIndex,reverMzbzRy,dutyDate,dutyPolices,key2);
        arr[1] = mzbzwNightIndex;
        //再排中班
        mzbzWMidIndex = startmzbzMidDuty(zbIndexByZhz,zhzOrderData,mzbzWMidIndex,dateIndex,ryList,dutyDate,dutyPolices,key1);
        arr[0] = mzbzWMidIndex;
        return arr;
    }










    //男值班员值班新方案
    private int startMidAndNignt(List<String> zbIndexByZhz,List<RyglEntity> zhzOrderData,int[] orderNameFlag,List<RyglEntity> ryList,int index,int dateIndex,String dutyDate,Map<String, Object> dutyPolices,String key1,String key2){
        if (index >= ryList.size()-1) {//最后一个 从0位置个开始排班
            index = 0;
        }else{//不是取下一个开始排班
            index++;
        }
        //检查当天开始值班的人是否值班
        if(checkRyglIsDuty(ryList.get(index).getId(),dutyDate)){
            // dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            index = startContinue(zbIndexByZhz,zhzOrderData,orderNameFlag, ryList, index,dateIndex,dutyDate, dutyPolices, key1, key2);

        }else{
            //若不值班 下一个 一直到这个人员是参与值班为止
            index =getNextRyIndex(index, ryList, dutyDate);//zhzIndex已经指向找到的位置
            // dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            index = startContinue(zbIndexByZhz,zhzOrderData,orderNameFlag, ryList, index,dateIndex, dutyDate, dutyPolices, key1, key2);
        }
        return index;
    }


    /**
     * 连续排班
     * @param ryList 值班人员来源集合
     * @param index  上次最后值班的人所在的位置 排班从下一个开始
     * @param dutyPolices 存放最终排的人Map
     * @param key1  RyglConstant.ZHZBY
     * @param key2  RyglConstant.WZBY
     * @return   index 在  ryList的索引位置
     */
    private int startContinue(List<String> zbIndexByZhz,List<RyglEntity> zhzOrderData,int[] orderNameFlag,List<RyglEntity> ryList,int index,int dateIndex,String dutyDate,Map<String, Object> dutyPolices ,String key1,String key2){
        if(zbIndexByZhz==null){
            index = startUnitDuty(orderNameFlag, ryList, index, dutyDate, dutyPolices, key1, key2);
        }else{//这里中班要排指挥长
            if(zbIndexByZhz.contains(dutyDate)){
                dutyPolices.put(key1, zhzOrderData.get(dateIndex).getId() + "&" + zhzOrderData.get(dateIndex).getName());
                dutyPolices.put(key2, ryList.get(index).getId() + "&" + ryList.get(index).getName());//下一个必须排晚班
                orderNameFlag[index] = 2;
            }
            else{
                index = startUnitDuty(orderNameFlag, ryList, index, dutyDate, dutyPolices, key1, key2);
            }
        }

        return index;
    }


    private int  startUnitDuty(int[] orderNameFlag,List<RyglEntity> ryList,int index,String dutyDate,Map<String, Object> dutyPolices ,String key1,String key2){
        // if(checkIsworkDay(dutyDate)){
        if(orderNameFlag[index]==0 ||orderNameFlag[index]==2){
            //排了中班
            dutyPolices.put(key1, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            orderNameFlag[index] = 1;//这次排了中班 标记一下
        }
        else if(orderNameFlag[index]==1){
            //排了晚班
            dutyPolices.put(key2, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            orderNameFlag[index] = 2;
        }
        //排下一个
        if(orderNameFlag[index]==1){//说明上一个已经排了中班 下个就排晚班
            index =getNextRyIndex(index, ryList, dutyDate);
            dutyPolices.put(key2, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            orderNameFlag[index] = 2;
        }else{//说明这个排了晚班  下一个就排中班
            index =getNextRyIndex(index, ryList, dutyDate);
            dutyPolices.put(key1, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            orderNameFlag[index] = 1;
        }
        //  }
        // else{
           /* if( orderNameFlag[index]==2){
                //排了中班
                dutyPolices.put(key1, ryList.get(index).getId() + "&" + ryList.get(index).getName());
                orderNameFlag[index] = 1;//这次排了中班 标记一下
            }
            else if(orderNameFlag[index]==1 ||orderNameFlag[index]==0){
                //排了晚班
                dutyPolices.put(key2, ryList.get(index).getId() + "&" + ryList.get(index).getName());
                orderNameFlag[index] = 2;
            }*/


        //  }

        //排下一个
        /*if(orderNameFlag[index]==1){//说明上一个已经排了中班 下个就排晚班
            index =getNextRyIndex(index, ryList, dutyDate);
            dutyPolices.put(key2, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            orderNameFlag[index] = 2;
        }else{//说明这个排了晚班  下一个就排中班
            index =getNextRyIndex(index, ryList, dutyDate);
            dutyPolices.put(key1, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            orderNameFlag[index] = 1;
        }*/

        return index;
    }


    /**
     * 排班中班和晚班值班员
     * @param wIndex 工作日指针
     * @param hIndex  节假日指针
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param ryList  人员来源
     * @param key1  中班
     * @param key2  晚班
     * @param sdf
     * @return
     */
    private List<Map<String,Object>>  startDutyZbAndWb(int wIndex,int hIndex,String startDate,String endDate,List<RyglEntity> ryList,String key1,String key2,SimpleDateFormat sdf)throws Exception{
        //String dateDiff = CommonUtil.getDateDiff(sdf.parse(startDate), sdf.parse(endDate));
        int dateDiff = (int)Util.daysBetween(endDate, startDate, sdf);
        Map<Integer, String> dutyDateMap2 = createDutyDateMap2(startDate, endDate, sdf);
        List<Map<String,Object>> result = new ArrayList<>();


        int dateIndex = 0;

        if(checkIsworkDay(dutyDateMap2.get(0))){

            //先排工作日再排节假日
            while (true){
                if(dateIndex > dateDiff){
                    break;
                }
                List<Map<String,Object>> list1 = new ArrayList<>();
                List<Map<String,Object>> list2 = new ArrayList<>();
                // dateIndex = dutyWorkDay(dateIndex, wIndex, ryList, dutyDateMap2, key1, key2,sdf,list1)[0];
                int[] dutyWorkDays = dutyWorkDay(dateIndex, wIndex, ryList, dutyDateMap2, key1, key2, sdf, list1);
                dateIndex = dutyWorkDays[0];
                wIndex = dutyWorkDays[1];
                result.addAll(list1);


                if(dateIndex > dateDiff){
                    break;
                }

                int[] dutyHkDays = dutyHkDay(dateIndex, hIndex, ryList, dutyDateMap2, key1, key2, sdf, list2);
                dateIndex = dutyHkDays[0];
                hIndex = dutyHkDays[1];
                // hIndex = dutyHkDay(dateIndex, hIndex, ryList, dutyDateMap2, key1, key2,sdf,list2)[1];
                result.addAll(list2);
            }

            return result;
        }else{
            //先排节假日再排工作日
            while (true){
                if(dateIndex > dateDiff){
                    break;
                }
                List<Map<String,Object>> list1 = new ArrayList<>();
                List<Map<String,Object>> list2 = new ArrayList<>();
                int[] dutyHkDay = dutyHkDay(dateIndex, hIndex, ryList, dutyDateMap2, key1, key2, sdf, list1);
                dateIndex = dutyHkDay[0];
                hIndex = dutyHkDay[1];
                result.addAll(list1);

                if(dateIndex > dateDiff){
                    break;
                }
                int[] dutyWorkDay = dutyWorkDay(dateIndex, wIndex, ryList, dutyDateMap2, key1, key2, sdf, list2);
                dateIndex = dutyWorkDay[0];
                wIndex = dutyWorkDay[1];
                result.addAll(list2);
            }
            return result;
        }

    }

    /**
     * 排值班长中晚班
     * @param zbIndexByZhz
     * @param zhzOrderData
     * @param wIndex
     * @param hIndex
     * @param startDate
     * @param endDate
     * @param ryList
     * @param key1
     * @param key2
     * @param sdf
     * @return
     * @throws Exception
     */
    private  List<Map<String,Object>> startDutyzbzZbAndWb(List<String> zbIndexByZhz,List<RyglEntity> zhzOrderData,int wIndex,int hIndex,String startDate,String endDate,List<RyglEntity> ryList,String key1,String key2,SimpleDateFormat sdf)throws Exception{
        int dateDiff = (int)Util.daysBetween(endDate, startDate, sdf);
        Map<Integer, String> dutyDateMap2 = createDutyDateMap2(startDate, endDate, sdf);
        List<Map<String,Object>> result = new ArrayList<>();


        int dateIndex = 0;
        if(checkIsworkDay(dutyDateMap2.get(0))){//要排的第一天
            //先排工作日再排节假日
            while (true){
                if(dateIndex > dateDiff){
                    break;
                }
                List<Map<String,Object>> list1 = new ArrayList<>();
                List<Map<String,Object>> list2 = new ArrayList<>();
                int[] dutyWorkDay = dutyWorkDay(zbIndexByZhz, zhzOrderData, dateIndex, wIndex, ryList, dutyDateMap2, key1, key2, sdf, list1);
                dateIndex = dutyWorkDay[0];
                wIndex = dutyWorkDay[1];
                result.addAll(list1);

                if(dateIndex > dateDiff){
                    break;
                }
                int[] dutyHkDay = dutyHkDay(dateIndex, hIndex, ryList, dutyDateMap2, key1, key2, sdf, list2);
                dateIndex = dutyHkDay[0];
                hIndex = dutyHkDay[1];

                result.addAll(list2);
            }
            return result;
        }else{
            //先排节假日再排工作日
            while (true){
                if(dateIndex > dateDiff){
                    break;
                }
                List<Map<String,Object>> list1 = new ArrayList<>();
                List<Map<String,Object>> list2 = new ArrayList<>();
                int[] dutyHkDay = dutyHkDay(dateIndex, hIndex, ryList, dutyDateMap2, key1, key2, sdf, list1);
                dateIndex = dutyHkDay[0];
                hIndex = dutyHkDay[1];
                result.addAll(list1);

                if(dateIndex > dateDiff){
                    break;
                }
                int[] dutyWorkDay = dutyWorkDay(zbIndexByZhz, zhzOrderData, dateIndex, wIndex, ryList, dutyDateMap2, key1, key2, sdf, list2);
                dateIndex = dutyWorkDay[0];
                wIndex = dutyWorkDay[1];
                result.addAll(list2);
            }
            return result;
        }
    }


    //连续排中晚值班长
    /*private List<Map<String,Object>> startContinue2(List<String> zbIndexByZhz,List<RyglEntity> zhzOrderData,int dateIndex,int hIndex,int wIndex,List<RyglEntity> ryList,Map<Integer, String> dutyDateMap2,Map<String, Object> dutyPolices,String key1,String key2,SimpleDateFormat sdf,int dateDiff) throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        while (true){
            dateIndex = dutyWorkDay(dateIndex, hIndex, ryList, dutyDateMap2, dutyPolices, key1, key2,sdf);

            dateIndex=  dutyWorkDay(dateIndex, wIndex, ryList, dutyDateMap2, dutyPolices, key1, key2,sdf);

            if(dateIndex < dateDiff+1){
                break;
            }
        }
        list.add(dutyPolices);
        return list;
    }*/




    /**
     * 连续排连续排中晚值班长
     * @param zbIndexByZhz  指挥长值中班的日期
     * @param zhzOrderData
     * @param dateIndex
     * @param wIndex
     * @param ryList
     * @param dutyDateMap2
     * @param
     * @param key1
     * @param key2
     * @param sdf
     * @return
     * @throws Exception
     */
    private int[] dutyWorkDay(List<String> zbIndexByZhz,List<RyglEntity> zhzOrderData,int dateIndex,int wIndex,List<RyglEntity> ryList,Map<Integer, String> dutyDateMap2,String key1,String key2,SimpleDateFormat sdf,List<Map<String, Object>> list) throws Exception {
        List<Map<String, Object>>  tempList = new ArrayList<>();
        List<Map<String, Object>>  tempList2 = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        int temp = dateIndex;
        while (true){
            if(dutyDateMap2.get(dateIndex)==null){//表示已经排到最后一日安
                dateIndex =temp;
                break;
            }
            if(zbIndexByZhz.contains(dutyDateMap2.get(dateIndex))){//表示这天要排指挥长
                map.put(key1, zhzOrderData.get(dateIndex).getId() + "&" + zhzOrderData.get(dateIndex).getName());
                tempList.add(map);
                if (!checkNextDayIsWorkDay(dutyDateMap2.get(dateIndex),sdf)) {//下一天为节假日为止 在重新排晚班
                    dateIndex =temp;
                    break;
                }
            }else{
                //  wIndex = startDuty(wIndex, ryList, dutyDateMap2.get(dateIndex),dutyPolices,key1);//先排中班 ryIndex 若为工作日索引
                wIndex = startDutyZWB(wIndex, ryList, dutyDateMap2.get(dateIndex), tempList, key1);
                if (!checkNextDayIsWorkDay(dutyDateMap2.get(dateIndex),sdf)) {//下一天为节假日为止 在重新排晚班
                    dateIndex =temp;
                    break;
                }
            }
            dateIndex++;
        }
        int[] arr = dutyNightOrder(dateIndex, wIndex, ryList, dutyDateMap2, key2, sdf,tempList2);
        for (int i = 0; i <tempList.size() ; i++) {
            tempList.get(i).putAll(tempList2.get(i));
        }
        list.addAll(tempList);
        return arr;

    }

    //工作日值晚班的情况
    private int[]  dutyNightOrder(int dateIndex,int wIndex,List<RyglEntity> ryList,Map<Integer, String> dutyDateMap2,String key2,SimpleDateFormat sdf,List<Map<String, Object>>  tempList)throws Exception{

        int[] arr = new int[2];
        while (true){
            if(dutyDateMap2.get(dateIndex)==null){//表示已经排到最后一日安
                break;
            }
            wIndex = startDutyZWB(wIndex, ryList, dutyDateMap2.get(dateIndex),tempList,key2);//排晚班
            if (!checkNextDayIsWorkDay(dutyDateMap2.get(dateIndex),sdf)) {//下一天为节假日为止 在重新排晚班
                break;
            }
            dateIndex++;
        }
        dateIndex++;
        arr[0] = dateIndex;
        arr[1] = wIndex;
        return arr;
    }





    //连续排中晚值班员
    /*private List<Map<String,Object>> startContinue2(int dateIndex,int hIndex,int wIndex,List<RyglEntity> ryList,Map<Integer, String> dutyDateMap2,Map<String, Object> dutyPolices,String key1,String key2,SimpleDateFormat sdf,int dateDiff) throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        while (true){
           // dateIndex = dutyWorkDay(dateIndex, hIndex, ryList, dutyDateMap2, dutyPolices, key1, key2,sdf);

            //dateIndex=  dutyWorkDay(dateIndex, wIndex, ryList, dutyDateMap2, dutyPolices, key1, key2,sdf);



            if(dateIndex < dateDiff+1){
                break;
            }
        }
        list.add(dutyPolices);
        return list;
    }*/


    //排中晚值班员
    private int[] dutyWorkDay(int dateIndex,int wIndex,List<RyglEntity> ryList,Map<Integer, String> dutyDateMap2,String key1,String key2,SimpleDateFormat sdf,List<Map<String,Object>> list) throws Exception {
        List<Map<String, Object>>  tempList = new ArrayList<>();
        List<Map<String, Object>>  tempList2 = new ArrayList<>();
        int temp = dateIndex;
        while (true){
            if(dutyDateMap2.get(dateIndex)==null){//表示已经排到最后一日安
                dateIndex =temp;
                break;
            }
            //wIndex = startDuty(wIndex, ryList, dutyDateMap2.get(dateIndex),dutyPolices,key1);//先排中班 若为工作日索引
            wIndex = startDutyZWB(wIndex, ryList, dutyDateMap2.get(dateIndex), tempList, key1);
            if (!checkNextDayIsWorkDay(dutyDateMap2.get(dateIndex),sdf)) {//下一天为节假日为止 在重新排晚班
                dateIndex =temp;
                break;
            }
            dateIndex++;
        }

        int[] arr = dutyNightOrder(dateIndex, wIndex, ryList, dutyDateMap2, key2, sdf,tempList2);

        for (int i = 0; i <tempList.size() ; i++) {
            tempList.get(i).putAll(tempList2.get(i));
        }
        list.addAll(tempList);
        return arr;

    }

    //排中晚值班员 节假日
    private int[] dutyHkDay(int dateIndex,int hIndex,List<RyglEntity> ryList,Map<Integer, String> dutyDateMap2,String key1,String key2,SimpleDateFormat sdf,List<Map<String,Object>> list) throws Exception {
        List<Map<String, Object>>  tempList = new ArrayList<>();
        List<Map<String, Object>>  tempList2 = new ArrayList<>();
        int[] arr = new int[2];
        int temp = dateIndex;
        while (true){
            if(dutyDateMap2.get(dateIndex)==null){//表示已经排到最后一日安
                dateIndex =temp;
                break;
            }
            //hIndex = startDuty(hIndex, ryList, dutyDateMap2.get(dateIndex),dutyPolices,key1);//先排中班  dateIndex若为工作日索引
            hIndex = startDutyZWB(hIndex, ryList, dutyDateMap2.get(dateIndex), tempList, key1);
            if (checkNextDayIsWorkDay(dutyDateMap2.get(dateIndex),sdf)) {//下一天为工作日索引为止 在重新排晚班
                dateIndex =temp;
                break;
            }
            dateIndex++;
        }
        while (true){
            if(dutyDateMap2.get(dateIndex)==null){//表示已经排到最后一日安
                break;
            }
            hIndex =  startDutyZWB(hIndex, ryList, dutyDateMap2.get(dateIndex), tempList2, key2);//排晚班
            if (checkNextDayIsWorkDay(dutyDateMap2.get(dateIndex),sdf)) {//下一天为工作日索引为止 在重新排晚班
                break;
            }
            dateIndex++;
        }
        for (int i = 0; i <tempList.size() ; i++) {
            tempList.get(i).putAll(tempList2.get(i));
        }
        list.addAll(tempList);
        dateIndex++;
        arr[0] = dateIndex;//记录日期位置
        arr[1] = hIndex;//记录工作日人员索引位置
        return arr;

    }





    /**
     * 计算下一天是否是工作日
     * @param date
     * @param sdf
     * @return
     * @throws Exception
     */
    private boolean checkNextDayIsWorkDay(String date,SimpleDateFormat sdf)throws Exception{
        boolean workDay = false;
        ZbRlwhEntity zbRlwhEntity = new ZbRlwhEntity();
        //下一天
        zbRlwhEntity.setDutyDate(sdf.format( CommonUtil.addDate(sdf.parse(date), 1)));
        List<ZbRlwhEntity> zbRlwhEntities = zbRlwhMapper.selectByEntityOrderBydutyDate(zbRlwhEntity);
        //0.工作日 1.节假日
        String flag = zbRlwhEntities.get(0).getFlag();
        if("0".equals(flag)){
            workDay = true;
        }
        return workDay;
    }




    /**
     * 开始排班
     * @param index
     * @param ryList
     * @param dutyDate
     * @param dutyPolices
     * @param key
     *
     * @return index 在ryList中的索引
     */
    private int  startDuty(int index,List<RyglEntity> ryList,String dutyDate,Map<String, Object> dutyPolices,String key){
        if (index >= ryList.size()-1) {//最后一个 从0位置个开始排班
            index = 0;
        }else{//不是取下一个开始排班
            index++;
        }
        //检查当天开始值班的人是否值班
        if(checkRyglIsDuty(ryList.get(index).getId(),dutyDate)){
            dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
        }else{
            //若不值班 下一个 一直到这个人员是参与值班为止
            index = getNextRyIndex(index, ryList, dutyDate);//zhzIndex已经指向找到的位置
            dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
        }
        return index;
    }

    /**
     * 排男值班长中班
     * @param zbIndexByZhz
     * @param zhzOrderData
     * @param index
     * @param dateIndex
     * @param ryList
     * @param dutyDate
     * @param dutyPolices
     * @param key
     * @return
     */
    private int  startmzbzMidDuty(List<String> zbIndexByZhz,List<RyglEntity> zhzOrderData,int index,int dateIndex,List<RyglEntity> ryList,String dutyDate,Map<String, Object> dutyPolices,String key){
        if(zbIndexByZhz.contains(dutyDate)){
            dutyPolices.put(key, zhzOrderData.get(dateIndex).getId() + "&" + zhzOrderData.get(dateIndex).getName());
        }else{
            if (index >= ryList.size()-1) {//最后一个 从0位置个开始排班
                index = 0;
            }
            else{//不是取下一个开始排班
                index++;
            }
            //检查当天开始值班的人是否值班
            if(checkRyglIsDuty(ryList.get(index).getId(),dutyDate)){
                dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            }
            else{
                //若不值班 下一个 一直到这个人员是参与值班为止
                index = getNextRyIndex(index, ryList, dutyDate);//zhzIndex已经指向找到的位置
                dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
            }
        }
        return index;
    }




    /**
     * 开始排班中晚班
     * @param index
     * @param ryList
     * @param dutyDate
     * @param
     * @param key
     *
     * @return index 在ryList中的索引
     */
    private int  startDutyZWB(int index,List<RyglEntity> ryList,String dutyDate,List<Map<String, Object>> tempList,String key){
        Map<String, Object> dutyPolices = new HashMap<>();
        if (index >= ryList.size()-1) {//最后一个 从0位置个开始排班
            index = 0;
        }else{//不是取下一个开始排班
            index++;
        }
        //检查当天开始值班的人是否值班
        if(checkRyglIsDuty(ryList.get(index).getId(),dutyDate)){
            dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
        }else{
            //若不值班 下一个 一直到这个人员是参与值班为止
            getNextRyIndex(index, ryList, dutyDate);//zhzIndex已经指向找到的位置
            dutyPolices.put(key, ryList.get(index).getId() + "&" + ryList.get(index).getName());
        }
        tempList.add(dutyPolices);
        return index;
    }





    /**
     * 找到下一个参与值班的人员
     * index 为当前索引位置
     * @return
     */
    private int getNextRyIndex(int index,List<RyglEntity> list,String dutyDate){
        while(true){
            index++;
            if(index >= list.size()){//表示已经下标越界 重新归零
                index =0;
            }
            if(checkRyglIsDuty(list.get(index).getId(),dutyDate)){
                break;//找到要排的人 退出循环
            }
        }
        return index;
    }








    /**
     * 判断该人员是否参与排班
     * @param dutyFlag
     * @param dutyDate
     * @return
     */
    private boolean checkRyglIsDuty(String dutyFlag,String dutyDate){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        RyglEntity rygl = ryglMapper.getById(dutyFlag);
        boolean flag = false;
        try {
            if("1".equals(rygl.getRyzt())){//在编
                flag = true;
            }
            else if("2".equals(rygl.getRyzt())){//借调 在借调时间段内才值班
                if(StringUtil.isNull(rygl.getJdEndDate())){
                    boolean before = sf.parse(dutyDate).before(sf.parse(rygl.getJdStartDate()));
                    if(!before){
                        flag = true;
                    }
                }else{
                    boolean effectiveDate = Util.isEffectiveDate(sf.parse(dutyDate), sf.parse(rygl.getJdStartDate()), sf.parse(rygl.getJdEndDate()));
                    if(effectiveDate){
                        flag = true;
                    }
                }


            }
            else if("3".equals(rygl.getRyzt())){//退休
                boolean before = sf.parse(dutyDate).before(sf.parse(rygl.getStartDate()));
                if(before){
                    flag = true;
                }
            }
            else if("4".equals(rygl.getRyzt()) || "5".equals(rygl.getRyzt())||"6".equals(rygl.getRyzt())){
                if(!StringUtil.isNull(rygl.getEndDate())){
                    boolean effectiveDate = Util.isEffectiveDate(sf.parse(dutyDate), sf.parse(rygl.getStartDate()), sf.parse(rygl.getEndDate()));
                    if(!effectiveDate){//当天值班日期不在 该人员的开始和结束日期之间 参与值班
                        flag = true;
                    }
                }else{
                    boolean before = sf.parse(dutyDate).before(sf.parse(rygl.getStartDate()));
                    if(before){
                        flag = true;
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
