package com.cesgroup.prison.zbgl.zbbp.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.StyleSet;
import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.zbgl.mbbm.dao.MbbmMapper;
import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;
import com.cesgroup.prison.zbgl.mbsz.web.MbszController;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import com.cesgroup.prison.zbgl.zbbp.dto.OrderDto;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.zbgl.mbxq.dao.MbxqMapper;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;
import com.cesgroup.prison.zbgl.rygl.dao.RyglMapper;
import com.cesgroup.prison.zbgl.rygl.util.RyglConstant;
import com.cesgroup.prison.zbgl.zbbp.dao.ZbbpMapper;
import com.cesgroup.prison.zbgl.zbbp.dto.ZbbpDto;
import com.cesgroup.prison.zbgl.zbbp.entity.ZbbpEntity;
import com.cesgroup.prison.zbgl.zbbp.service.ZbbpService;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.web.multipart.MultipartFile;

@Service("zbbpService")
public class ZbbpServiceImpl extends BaseDaoService<ZbbpEntity, String, ZbbpMapper> implements ZbbpService {
    private final Logger logger = LoggerFactory.getLogger(MbszController.class);
    @Resource
    private ZbbpMapper zbbpMapper;
    @Resource
    private RyglMapper ryglMapper;

    @Resource
    private MbxqMapper mbxqMapper;
    @Resource
    private MbbmMapper mbbmMapper;

    @Override
    public List<ZbbpEntity> findAllList(ZbbpEntity ZbbpEntity) {

        return zbbpMapper.findAllList(ZbbpEntity);

    }

    @Override
    @Transactional
    public void saveDutyData(ZbbpEntity zbbpEntity) throws Exception {

        // String[] allPoliceData = (zbbpEntity.getDutyPoliceData()).split(",");
        // String[] allPoliceData = (zbbpEntity.getDutyPoliceData()).split(";");//????????????
        // int dateDiff = Integer.valueOf((CommonUtil.getDateDiff(startDate,endDate)));
        // //??????????????????
        /*
         * for(int i = 0; i < dateDiff+1; i++) {
         * zbbpEntity.setDbdDutyModeOrderJobId((allPoliceData[i].split("%")[0]).split(
         * "-")[0]); String policeId[] =
         * (allPoliceData[i].split("%")[0]).split("-")[1].split("_"); String
         * policeName[] = allPoliceData[i].split("%")[1].split("_");
         *
         * zbbpEntity.setDbdDutyModeOrderJobId((allPoliceData[i].split("%")[0]).split(
         * "&")[0]); String policeId[] =
         * (allPoliceData[i].split("%")[0]).split("&")[1].split("@"); String
         * policeName[] = allPoliceData[i].split("%")[1].split("@"); Date date =
         * startDate; zbbpEntity.setDbdDutyDate(CommonUtil.addDate(date, i));
         *
         * for (int j = 0;j < policeId.length; j++) {
         *
         * zbbpEntity.setDbdStaffId(policeId[j].trim()); // ????????????
         * zbbpEntity.setDbdStaffName(policeName[j].trim()); // ????????????
         * zbbpMapper.insert(zbbpEntity); zbbpEntity.setId(null); }
         *
         * allPoliceList[i] for (int j = 0; j < allPoliceData.length; j++) {
         *
         * } }
         */
        String[] allPoliceDataAndJobId = zbbpEntity.getDutyPoliceData().split(",");
        for (int i = 0; i < allPoliceDataAndJobId.length; i++) {
            zbbpEntity.setDbdDutyModeOrderJobId(allPoliceDataAndJobId[i].split("&")[0]);
            String[] allPoliceData = (allPoliceDataAndJobId[i].split("&")[1]).split("%");
            String[] policeIds = allPoliceData[0].split("@");
            String[] policeINames = allPoliceData[1].split("@");
            for (int j = 0; j < policeIds.length; j++) {
                ryglMapper.updatePbCount(policeIds[j].trim(), "1");//??????????????????
                zbbpEntity.setDbdStaffId(policeIds[j].trim());
                zbbpEntity.setDbdStaffName(policeINames[j].trim());
                zbbpMapper.insert(zbbpEntity);
                zbbpEntity.setId(null);
            }
        }

    }

    @Override
    @Transactional
    public void deleteByConditions(ZbbpEntity zbbpEntity) {

        zbbpMapper.deleteByConditions(zbbpEntity);
    }

    /**
     * ???????????????????????????
     *
     * @param dutyModelDepartmentId ?????????????????????id
     */
    @Override
    @Transactional
    public void updateRyPbCount(String dutyModelDepartmentId) {
        ZbbpEntity zb = new ZbbpEntity();
        zb.setDbdDutyModeDepartmentId(dutyModelDepartmentId);
        List<ZbbpEntity> zbbpList = zbbpMapper.selectByEntity(zb);
        for (int i = 0; i < zbbpList.size(); i++) {
            ryglMapper.updatePbCount(zbbpList.get(i).getDbdStaffId(), "2");//??????????????????
        }
    }

    @Override
    public AjaxResult writerZbpById(ZbbpDto zbbpDto) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        //???????????????
        String path = "";
        int level = AuthSystemFacade.whatLevelForLoginUser();
        if(level==1 ||level==2){
            path = CommonConstant.jggzUploadsRootPath + File.separator + CommonConstant.uploadGlobalPath + File.separator + RyglConstant.zbrymb + File.separator + "jyzbbprint.xlsx";
        }else{
            path = CommonConstant.jggzUploadsRootPath + File.separator + CommonConstant.uploadGlobalPath + File.separator + RyglConstant.zbrymb + File.separator + "jqzbbprint.xlsx";
        }
        String copyPath = CommonConstant.jggzUploadsRootPath + File.separator + CommonConstant.uploadGlobalPath + File.separator + RyglConstant.zbrymb + File.separator + IdUtil.simpleUUID() + ".xlsx";
        ExcelWriter writer = null;
        try {
            //MbszEntity mbszEntity = new MbszEntity();        //id ???????????????????????????
            MbxqEntity mbxqEntity = new MbxqEntity();
            mbxqEntity.setMojModeId(zbbpDto.getDmdModeId());
            List<MbxqEntity> mbxqList = mbxqMapper.findAllList(mbxqEntity);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

            ZbbpEntity zbbpEntity = new ZbbpEntity();
            List<List<Map<String, Object>>> zbbpRows = new ArrayList<List<Map<String, Object>>>();
            List<ZbbpEntity> zbbpList = new ArrayList<ZbbpEntity>();
            for (int i = 0; i < mbxqList.size(); i++) {
                zbbpEntity.setDbdDutyModeDepartmentId(zbbpDto.getId()); //????????????????????????
                zbbpEntity.setDbdDutyModeOrderJobId(mbxqList.get(i).getId());
                List<ZbbpEntity> zbbps = this.getDao().findAllList(zbbpEntity);
                for (ZbbpEntity zb : zbbps) {
                    zbbpList.add(zb);
                }
//						List<Map<String,Object>> zbbpRow = new ArrayList<Map<String,Object>>();
//						for(int j = 0 ;j< zbbps.size();j++) {
//							Map<String,Object> map = new LinkedHashMap<String, Object>();
//									if(i==0) {
//									map.put("??????", sf.format(zbbps.get(j).getDbdDutyDate()));
//										map.put("??????", getWeekDay(sf.format(zbbps.get(j).getDbdDutyDate()))); 
//									}
//										map.put(mbxqList.get(i).getCdjJobName(),zbbps.get(j).getDbdStaffName());
//										zbbpRow.add(map);
//						}
//						
//						zbbpRows.add(zbbpRow);
            }


            String orderData = zbbpList.get(0).getMojOrderId() + "&" + mbxqList.get(0).getDorDutyOrderName() + "&"
                    + mbxqList.get(0).getDorStartTime() + "&" + mbxqList.get(0).getDorEndTime() + "*" + zbbpList.get(0).getDbdDutyModeOrderJobId() + "&"
                    + mbxqList.get(0).getCdjJobName() + "%" + zbbpList.get(0).getDbdStaffId() + "/" + zbbpList.get(0).getDbdStaffName();
            try {
                int k = 1;
                for (int i = 1; i < zbbpList.size(); i++) {

                    if (zbbpList.get(i).getMojOrderId().equals(zbbpList.get(i - 1).getMojOrderId())) {  //???????????????

                        if (zbbpList.get(i).getDbdDutyModeOrderJobId().equals(zbbpList.get(i - 1).getDbdDutyModeOrderJobId())) {
                            orderData = orderData + "@" + zbbpList.get(i).getDbdStaffId() + "/" + zbbpList.get(i).getDbdStaffName();
                        } else {
                            orderData = orderData + "~" + mbxqList.get(k).getId() + "&" + mbxqList.get(k).getCdjJobName() + "%" + zbbpList.get(i).getDbdStaffId() + "/"
                                    + zbbpList.get(i).getDbdStaffName();
                            k = k + 1;
                        }
                    } else {
                        orderData = orderData + ";" + mbxqList.get(k).getMojOrderId() + "&" + mbxqList.get(k).getDorDutyOrderName() + "&"
                                + mbxqList.get(k).getDorStartTime() + "&" + mbxqList.get(k).getDorEndTime() + "*" + mbxqList.get(k).getId() + "&"
                                + mbxqList.get(k).getCdjJobName() + "%" + zbbpList.get(i).getDbdStaffId() + "/" + zbbpList.get(i).getDbdStaffName();
                        k = k + 1;
                        continue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<String> orderDate = new ArrayList<String>();
            orderDate.add(mbxqList.get(0).getDorStartTime() + "-" + mbxqList.get(0).getDorEndTime());
            for (int i = 1; i < mbxqList.size(); i++) {
                if (!mbxqList.get(i).getMojOrderId().equals(mbxqList.get(i - 1).getMojOrderId())) {
                    orderDate.add(mbxqList.get(i).getDorStartTime() + "-" + mbxqList.get(i).getDorEndTime());
                }
            }


            MbbmEntity mbbmEntity = mbbmMapper.getById(zbbpDto.getId());


            FileUtil.copy(path, copyPath, false);
            // ?????????????????????writer
            writer = ExcelUtil.getWriter(copyPath);
            //????????????
            if(level==1 ||level==2){
                String jytitle =this.convetCode(user.getCusNumber())+"-"+mbbmEntity.getDmdName();
                writer.merge(0,0,0,8,jytitle,true);
                int k = 1;
                for (int i = 1; i < orderDate.size(); i++) {
                    k += 2;
                    writer.writeCellValue(k, 1, orderDate.get(i));// 3  5  7 ????????????
                }
            }else{
                String jqtitle =mbbmEntity.getDmdName();
                writer.merge(0,0,0,7,jqtitle,true);
                int k = 0;
                for (int i = 0; i < orderDate.size(); i++) {
                    k += 2;
                    writer.writeCellValue(k, 1, orderDate.get(i));// 3  5  7 ????????????
                }
            }


           // writer.passRows(1);
            List<String> jobNames = new ArrayList<String>();
            jobNames.add("??????");
            jobNames.add("??????");
            for (MbxqEntity mbxq : mbxqList) {
                jobNames.add(mbxq.getCdjJobName());
            }

            for (int i = 0; i < jobNames.size(); i++) {//???????????? ??????
                writer.writeCellValue(i, 2, jobNames.get(i));
            }
            // writer.write(jobNames);//??????????????????


            String dateDiff = CommonUtil.getDateDiff(sf.parse(zbbpDto.getDmdStartDate()), sf.parse(zbbpDto.getDmdEndDate()));
            for (int i = 0; i < Integer.valueOf(dateDiff) + 1; i++) {//?????????????????????
                writer.writeCellValue(0, 3 + i, sf.format(CommonUtil.addDate(sf.parse(zbbpDto.getDmdStartDate()), i)));
                writer.writeCellValue(1, 3 + i, getWeekDay(sf.format(CommonUtil.addDate(sf.parse(zbbpDto.getDmdStartDate()), i))));
            }
            String[] orders = orderData.split(";");
            int row = 3;//?????????????????????????????????
            int col = 2;
            //CommonUtil.addDate(date, day);
            for (int i = 0; i < orders.length; i++) {
                String[] jobList = (orders[i].split("\\*")[1]).split("~");
                for (int j = 0; j < jobList.length; j++) {
                    String[] dutyPoliceData = (jobList[j].split("%")[1]).split("@");
                    for (int g = 0; g < Integer.valueOf(dateDiff) + 1; g++) {
                        String policeName = dutyPoliceData[g].split("/")[1];
                        writer.writeCellValue(col, row, policeName);
                        row++;
                    }
                    row = 3;
                    col++;
                }
            }


            // List<String> row1 = CollUtil.newArrayList("??????", "??????", "?????????", "?????????","?????????","?????????","?????????","?????????","?????????");

            // writer.w
            // writer.writeRow(rowMap, isWriteKeyAsHead);

            //????????????
            // writer.passRows(2);
            // writer.write(zbbpRows, true);
            // ???????????????????????????????????????????????????????????????
            // writer.write(rows, true);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        } finally {
            writer.close();
        }
        return AjaxResult.success(copyPath);
    }

    @Override
    public AjaxResult exportZbmbExcel(ZbbpDto zbbpDto) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        //???????????????
        String path = CommonConstant.jggzUploadsRootPath + File.separator + CommonConstant.uploadGlobalPath + File.separator + RyglConstant.zbrymb + File.separator + "zbexportmb.xlsx";
        String copyPath = CommonConstant.jggzUploadsRootPath + File.separator + CommonConstant.uploadGlobalPath + File.separator + RyglConstant.zbrymb + File.separator + IdUtil.simpleUUID() + ".xlsx";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        ExcelWriter writer = null;
        try{
            MbxqEntity mbxqEntity = new MbxqEntity();
            mbxqEntity.setMojModeId(zbbpDto.getDmdModeId());
            List<MbxqEntity> mbxqList = mbxqMapper.findAllList(mbxqEntity);
            FileUtil.copy(path, copyPath, false);
            // ?????????????????????writer
            writer = ExcelUtil.getWriter(copyPath);
            StyleSet style = writer.getStyleSet();
            style.setBackgroundColor(IndexedColors.YELLOW,false);
            //???????????????Excel???
            List<OrderDto> orderDtos = new ArrayList<OrderDto>();
            OrderDto orderDto = new OrderDto();
           // Map<String,List<Integer>> orderIndex = new HashMap<>();
            List<String> orderIds = new ArrayList<String>();
            orderIds.add(mbxqList.get(0).getMojOrderId());
            orderDto.setOrderIdName(mbxqList.get(0).getDorDutyOrderName());
            orderDto.setStartDate(mbxqList.get(0).getDorStartTime());
            orderDto.setEndDate(mbxqList.get(0).getDorEndTime());
            orderDtos.add(orderDto);
            for (int i = 1; i < mbxqList.size(); i++) {
                if (!mbxqList.get(i).getMojOrderId().equals(mbxqList.get(i - 1).getMojOrderId())) {
                    OrderDto orderDtoTemp = new OrderDto();
                    orderDtoTemp.setOrderIdName(mbxqList.get(i).getDorDutyOrderName());
                    orderDtoTemp.setStartDate(mbxqList.get(i).getDorStartTime());
                    orderDtoTemp.setEndDate(mbxqList.get(i).getDorEndTime());
                    orderDtos.add(orderDtoTemp);
                    orderIds.add(mbxqList.get(i).getMojOrderId());
                }
            }

            for (int i = 0; i < orderIds.size(); i++) {
                mbxqEntity.setMojOrderId(orderIds.get(i));
                List<MbxqEntity> mbxqOrderList = mbxqMapper.findAllList(mbxqEntity);
                List<String> jobs = new ArrayList<String>();
                for (MbxqEntity job:mbxqOrderList){
                    jobs.add(job.getCdjJobName());
                }
                orderDtos.get(i).setJob(jobs);
            }
            //??????????????????
            int col = 2;
            for (int i = 0; i <orderDtos.size() ; i++) {
                List<String> jobList =  orderDtos.get(i).getJob();
                String content = orderDtos.get(i).getOrderIdName()+"("+orderDtos.get(i).getStartDate()+"-"+orderDtos.get(i).getEndDate()+")";
                if(jobList.size()==1){
                    writer.writeCellValue(col,0,content);
                }else{
                    writer.merge(0,0,col,col+jobList.size()-1,content,false);
                }
                col=col+jobList.size();
            }
            //???????????? ?????? ??????
            writer.writeCellValue(0,1,"??????");
            writer.writeCellValue(1,1,"??????");
            //?????????
            for (int i = 0; i <mbxqList.size() ; i++) {//???????????????????????????
                writer.writeCellValue(2+i,1,mbxqList.get(i).getCdjJobName());
            }
            String dateDiff = CommonUtil.getDateDiff(sf.parse(zbbpDto.getDmdStartDate()), sf.parse(zbbpDto.getDmdEndDate()));
            for (int i = 0; i < Integer.valueOf(dateDiff) + 1; i++) {//??????????????????
                writer.writeCellValue(0, 2 + i, sf.format(CommonUtil.addDate(sf.parse(zbbpDto.getDmdStartDate()), i)));
                writer.writeCellValue(1, 2 + i, getWeekDay(sf.format(CommonUtil.addDate(sf.parse(zbbpDto.getDmdStartDate()), i))));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writer.close();
        }
        return AjaxResult.success(copyPath);
    }

    @Override
    @Transactional
    public AjaxResult importZbData(MultipartFile file, ZbbpDto zbbpDto,String dmdName) {
        //????????????????????????
        // ????????????
        ExcelReader reader = null;
        String path = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            UserBean user = AuthSystemFacade.getLoginUserInfo();
            String filename = file.getOriginalFilename();
            String[] strArray = filename.split("\\.");
            int suffixIndex = strArray.length - 1;
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + strArray[suffixIndex];
            path = CommonConstant.jggzUploadsRootPath + File.separator + CommonConstant.uploadGlobalPath + File.separator + RyglConstant.zbrymb + File.separator + fileName;
            // ???????????????
            file.transferTo(new File(path));
            //????????????
            reader = ExcelUtil.getReader(FileUtil.file(path));
            //?????????Map???????????????????????????????????????Map??????key????????????value?????????????????????????????????
           // List<Map<String, Object>> readAll = reader.;
            MbxqEntity mbxqEntity = new MbxqEntity();
            mbxqEntity.setMojModeId(zbbpDto.getDmdModeId());
            List<MbxqEntity> mbxqList = mbxqMapper.findAllList(mbxqEntity);

            String dateDiff = CommonUtil.getDateDiff(sf.parse(zbbpDto.getDmdStartDate()), sf.parse(zbbpDto.getDmdEndDate()));
            //????????????
            AjaxResult ajaxResult = checkZbryData(Integer.valueOf(dateDiff), mbxqList, user, reader);
            if(ajaxResult.getCode()==200){//????????????????????????,????????????
                //???????????????????????????
                MbbmEntity mbbmEntity = new MbbmEntity();
                mbbmEntity.setDmdName(dmdName);
                mbbmEntity.setDmdModeId(zbbpDto.getDmdModeId());
                mbbmEntity.setDmdDprtmntId(zbbpDto.getDmdDprtmntId());
                mbbmEntity.setDmdStartDate(sf.parse(zbbpDto.getDmdStartDate()));
                mbbmEntity.setDmdEndDate(sf.parse(zbbpDto.getDmdEndDate()));
                mbbmEntity.setDmdCusNumber(user.getCusNumber());
                mbbmEntity.setDmdCategoryId(zbbpDto.getCategoryId());
                mbbmEntity.setDmdCrteTime(new Date());
                mbbmEntity.setDmdCrteUserId(user.getUserId());
                mbbmEntity.setDmdUpdtTime(new Date());
                mbbmEntity.setDmdUpdtUserId(user.getUserId());
                mbbmEntity.setDmdZt("0");//???????????????
                mbbmMapper.insert(mbbmEntity);
               // List<ZbbpEntity> zbbpEntities = new ArrayList<ZbbpEntity>();
                ZbbpEntity zbbpEntity = new ZbbpEntity();
                zbbpEntity.setDbdDutyModeDepartmentId(mbbmEntity.getId());
                for (int i = 0; i < Integer.valueOf(dateDiff) + 1; i++) {//i??????
                    zbbpEntity.setDbdCusNumber(user.getCusNumber());
                    zbbpEntity.setDbdCrteTime(new Date());
                    zbbpEntity.setDbdCrteUserId(user.getUserId());
                    zbbpEntity.setDbdUpdtTime(new Date());
                    zbbpEntity.setDbdUpdtUserId(user.getUserId());
                    Date date = sf.parse(zbbpDto.getDmdStartDate());
                    zbbpEntity.setDbdDutyDate(CommonUtil.addDate(date, i));
                    for (int j = 0; j <mbxqList.size() ; j++) {//j??????
                        zbbpEntity.setDbdDutyModeOrderJobId(mbxqList.get(j).getId());
                        String data = reader.readCellValue(2 + j, 2 + i)==null?"":reader.readCellValue(2 + j, 2 + i).toString();
                        saveZbData(zbbpEntity,data,user,zbbpDto.getDmdDprtmntId());
                    }

                }
               // zbbpMapper.insert(zbbpEntities);
            }else{//????????????,??????????????????
                return ajaxResult;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
           // return AjaxResult.error(e.getMessage());
        } finally {
            reader.close();
            //????????????Excel
            FileUtil.del(new File(path));
        }
        return AjaxResult.success("??????????????????");
    }
    @Transactional
    public void saveZbData(ZbbpEntity zbbpEntity, String data,UserBean user,String deptCode)throws Exception {
        StrUtil.replace(data,"???","???");
        StrUtil.replace(data,",","???");
        String[] zbryNames = data.trim().split("???");
        for(String zbryName:zbryNames){
            String id = IdUtil.simpleUUID();
            zbbpEntity.setId(id);
            if(user.getUserLevel().equals(EUserLevel.AREA)){//??????????????????
                zbbpEntity.setDbdStaffName(zbryName);
                List<Map<String, Object>> userList = zbbpMapper.getUser(user.getCusNumber(), deptCode, zbryName);
                String userId = userList.get(0).get("PBD_USER_ID").toString();
                zbbpEntity.setDbdStaffId(userId);
            }else{//???????????????
                zbbpEntity.setDbdStaffName(zbryName);
                List<Map<String, Object>> userList = zbbpMapper.getUser(user.getCusNumber(), null, zbryName);
                String userId = userList.get(0).get("PBD_USER_ID").toString();
                zbbpEntity.setDbdStaffId(userId);
            }
            zbbpMapper.insert(zbbpEntity);
           // zbbpEntities.add(zbbpEntity);
        }

    }

    /**
     * ????????????
     * @param dateDiff ????????????
     * @param mbxqList ????????????
     * @param user
     * @param reader
     * @return
     */
    public AjaxResult checkZbryData(Integer dateDiff, List<MbxqEntity> mbxqList, UserBean user,ExcelReader reader) {
        String result ="";
        String str ="";//??????????????????
        String cmStr = "";//???????????????
        for (int i = 0; i < Integer.valueOf(dateDiff) + 1; i++) {//i??????
            for (int j = 0; j < mbxqList.size(); j++) {//j??????
                String data = reader.readCellValue(2 + j, 2 + i) == null ? "" : reader.readCellValue(2 + j, 2 + i).toString();
                if(StringUtil.isNull(data)){
                    return AjaxResult.error("???????????????????????????,????????????????????????");
                }
                StrUtil.replace(data,"???","???");
                StrUtil.replace(data,",","???");
                String[] zbryNames = data.trim().split("???");
                for (String zbryName:zbryNames){
                    //???????????????????????????  ??? ????????????
                    if(user.getUserLevel().equals(EUserLevel.AREA)){//??????????????????
                        List<Map<String, Object>> userList = zbbpMapper.getUser(user.getCusNumber(), user.getDprtmntCode(), zbryName);
                        if(userList.size()<=0){//?????????
                            str = str+zbryName+";";
                        }
                        if(userList.size() >1){//??????
                            cmStr = cmStr+zbryName+";";
                        }
                    }else{//???????????????
                        List<Map<String, Object>> userList = zbbpMapper.getUser(user.getCusNumber(), null, zbryName);
                        if(userList.size()<=0){//?????????
                            str = str+zbryName+";";
                        }
                        if(userList.size() >1){//??????
                            cmStr = cmStr+zbryName+";";
                        }
                    }
                }
            }
        }

        if(!StringUtil.isNull(str)){
            str = str+"??????????????????????????????????????????,???????????????????????????????????????";
        }
        if(!StringUtil.isNull(cmStr)){
            str = str+"????????????????????????????????????,??????????????????????????????????????????";
        }
        result =str+cmStr;
        if(StringUtil.isNull(result)){
            return AjaxResult.success();
        }else{
            return AjaxResult.error(result);
        }
    }








    /**
     * ????????????
     *
     * @param date
     * @return
     */
    public static String getWeekDay(String date) {
        String[] weekDays = {"?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????"};
        int w = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //??????????????????
            Calendar cal = Calendar.getInstance();
            Date time = sdf.parse(date);
            cal.setTime(time);
            w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return weekDays[w];
    }

    /**
     * ???????????????????????????
     *
     * @param cusNumber
     * @return
     */
    private String convetCode(String cusNumber) {
        String res = "";
        try {
            List<OrgEntity> allJyInfo = AuthSystemFacade.getAllJyInfo();
            if (cusNumber.equals("4300")) {
                res = "????????????????????????";
            } else {
                for (OrgEntity or : allJyInfo) {
                    if (cusNumber.equals(or.getOrgKey())) {
                        res = or.getOrgName();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


}
