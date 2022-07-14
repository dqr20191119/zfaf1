package com.cesgroup.prison.xtcs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.PersistenceException;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.cache.TXtcsCache;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.xtcs.dao.XtcsMapper;
import com.cesgroup.prison.xtcs.entity.XtcsEntity;
import com.cesgroup.prison.xtcs.service.XtcsService;


/**
 * 系统参数业务处理实现类
 * XtcsServiceImpl
 *
 * @author cheng.jie
 * @created Create Time: 2020-04-28
 */
@Service
@Transactional
public class XtcsServiceImpl extends BaseDaoService<XtcsEntity, String, XtcsMapper> implements XtcsService {
    /**
     * 系统参数缓存
     */
    @Resource
    private TXtcsCache xtcsCache;

    @Override
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            return this.getDao().findWithPage(paramMap, pageable);
        } catch (PersistenceException e) {
            throw new ServiceException("分页查询系统参数信息发生异常，原因：" + e);
        } catch (Exception e) {
            throw new ServiceException("分页查询系统参数信息发生异常，原因：" + e);
        }
    }

    @Override
    public XtcsEntity queryById(String id) throws ServiceException {
        try {
            return this.getDao().findById(id);
        } catch (PersistenceException e) {
            throw new ServiceException("根据主键ID查询系统参数发生异常，原因：" + e);
        } catch (Exception e) {
            throw new ServiceException("根据主键ID查询系统参数发生异常，原因：" + e);
        }
    }

    @Override
    public XtcsEntity queryByCsbm(String csbm) throws ServiceException {
        List<XtcsEntity> xtcsEntityList = null;
        try {
            xtcsEntityList = this.getDao().findByCsbm(csbm);
        } catch (PersistenceException e) {
            throw new ServiceException("根据编码查询系统参数发生异常，原因：" + e);
        } catch (Exception e) {
            throw new ServiceException("根据编码查询系统参数发生异常，原因：" + e);
        }
        return xtcsEntityList != null && xtcsEntityList.size() > 0  ? xtcsEntityList.get(0) : null;
    }

    @Override
    public void saveOrUpdate(XtcsEntity xtcsEntity) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(xtcsEntity == null) {
            throw new ServiceException("系统参数实体类为空");
        }

        if(xtcsEntity.getCsbm() == null || "".equals(xtcsEntity.getCsbm())) {
            throw new ServiceException("编码为空");
        }
        if(xtcsEntity.getCsmc() == null || "".equals(xtcsEntity.getCsmc())) {
            throw new ServiceException("名称为空");
        }
        // 判断编码是否重复
        try {
            if (this.isCsbmExists(xtcsEntity)) {
                throw new ServiceException("编码在数据库表中已存在");
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        // 声明系统参数操作实类体
        XtcsEntity xtcsEntityOperate = null;
        if(xtcsEntity.getId() != null && !"".equals(xtcsEntity.getId())) {
            try {
                xtcsEntityOperate = this.getDao().findById(xtcsEntity.getId());
            } catch (Exception e) {
                throw new ServiceException("根据系统参数ID查询系统参数发生异常");
            }
        }

        if(xtcsEntityOperate == null) {
            xtcsEntityOperate = new XtcsEntity();
            try {
                xtcsEntityOperate.setId(CommonUtil.createUUID());
                xtcsEntityOperate.setCsbm(xtcsEntity.getCsbm());
                xtcsEntityOperate.setCsmc(xtcsEntity.getCsmc());
                xtcsEntityOperate.setCsz(xtcsEntity.getCsz());
                xtcsEntityOperate.setBz(xtcsEntity.getBz());
                xtcsEntityOperate.setSjzt("0");
                Integer sjpx = this.getDao().findMaxSjpx();
                xtcsEntityOperate.setSjpx(Long.valueOf(sjpx == null ? 1 : sjpx + 1));
                xtcsEntityOperate.setCjrid(user.getUserId());
                xtcsEntityOperate.setCjr(user.getUserName());
                xtcsEntityOperate.setCjrq(new Date());
                xtcsEntityOperate.setGxrid(user.getUserId());
                xtcsEntityOperate.setGxr(user.getUserId());
                xtcsEntityOperate.setGxrq(new Date());
                this.getDao().insertSelective(xtcsEntityOperate);
            } catch (PersistenceException e) {
                throw new ServiceException("新增系统参数失败，原因：" + e);
            } catch(Exception e) {
                throw new ServiceException("新增系统参数失败，原因：" + e);
            }
        } else {
            try {
                xtcsEntityOperate.setCsbm(xtcsEntity.getCsbm());
                xtcsEntityOperate.setCsmc(xtcsEntity.getCsmc());
                xtcsEntityOperate.setCsz(xtcsEntity.getCsz());
                xtcsEntityOperate.setBz(xtcsEntity.getBz());
                xtcsEntityOperate.setSjzt("0");
                xtcsEntityOperate.setGxrid(user.getUserId());
                xtcsEntityOperate.setGxr(user.getUserId());
                xtcsEntityOperate.setGxrq(new Date());
                this.getDao().updateSelective(xtcsEntityOperate);
            } catch (PersistenceException e) {
                throw new ServiceException("更新系统参数失败，原因：" + e);
            } catch (Exception e) {
                throw new ServiceException("更新系统参数失败，原因：" + e);
            }
        }

        // 刷新缓存
        try {
            xtcsCache.refresh();
        } catch (Exception e) {
            throw new ServiceException("刷新系统参数缓存失败，原因：" + e);
        }
    }

    @Override
    public void deleteByIds(String ids) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }

        String[] idArr = ids != null && !"".equals(ids) ? ids.split(",") : null;
        if(idArr == null) {
            return;
        }

        // 根据系统参数ID数组，查询系统参数、系统参数显示内容、系统参数显示内容判定条件
        List<XtcsEntity> xtcsEntityList = null;
        try {
            xtcsEntityList = this.getDao().findByIdArray(idArr);
        } catch (PersistenceException e) {
            throw new ServiceException("根据警情ID数组，获取系统参数出错：" + e.getMessage());
        } catch (Exception e) {
            throw new ServiceException("根据警情ID数组，获取系统参数出错：" + e.getMessage());
        }

        // 逻辑删除系统参数
        if(xtcsEntityList != null && xtcsEntityList.size() > 0) {
            for(XtcsEntity xtcsEntity : xtcsEntityList) {
                xtcsEntity.setSjzt("1");
                xtcsEntity.setSjpx(Long.valueOf(0));
                xtcsEntity.setGxrid(user.getUserId());
                xtcsEntity.setGxr(user.getUserId());
                xtcsEntity.setGxrq(new Date());
                try {
                    this.getDao().updateSelective(xtcsEntity);
                } catch (Exception e) {
                    throw new ServiceException("将系统参数数据状态置为删除，发生异常：" + e.getMessage());
                }
            }
        }

        // 刷新缓存
        try {
            xtcsCache.refresh();
        } catch (Exception e) {
            throw new ServiceException("刷新系统参数缓存失败，原因：" + e);
        }
    }

    @Override
    public String queryCzsByCsbm(String csbm) throws ServiceException {
        Map<String, Object> xtcsMap = RedisCache.getHashMap(TXtcsCache.tableName, csbm, new String[] {TXtcsCache.ID, TXtcsCache.CSBM, TXtcsCache.CSMC, TXtcsCache.CSZ, TXtcsCache.SJZT});

        if(xtcsMap == null) {
            // 刷新缓存
            try {
                xtcsCache.refresh();
            } catch (Exception e) {
                throw new ServiceException("刷新系统参数缓存失败，原因：" + e);
            }

            // 再次从缓存中获取系统参数
            xtcsMap = RedisCache.getHashMap(TXtcsCache.tableName, csbm, new String[] {TXtcsCache.ID, TXtcsCache.CSBM, TXtcsCache.CSMC, TXtcsCache.CSZ, TXtcsCache.SJZT});
        }

        return xtcsMap != null && xtcsMap.get(TXtcsCache.CSZ) != null ? xtcsMap.get(TXtcsCache.CSZ).toString() : null;
    }

    /**
     * 判断编码是否重复
     *
     * @param xtcsEntity
     * @return
     * @throws ServiceException
     */
    private boolean isCsbmExists(XtcsEntity xtcsEntity) throws ServiceException {
        if(xtcsEntity == null) {
            return false;
        }
        if(xtcsEntity.getCsbm() == null || "".equals(xtcsEntity.getCsbm() )) {
            throw new ServiceException("判断编码是否重复失败，编码为空");
        }

        //根据编码查询系统参数数据
        List<XtcsEntity> xtcsEntityList = null;
        try {
            xtcsEntityList = this.getDao().findByCsbm(xtcsEntity.getCsbm());
        } catch (PersistenceException e) {
            throw new ServiceException("根据编码查询系统参数数据发生异常，原因：" + e.getMessage());
        } catch (Exception e) {
            throw new ServiceException("根据编码查询系统参数数据发生异常，原因：" + e.getMessage());
        }
        //如果查询到的结果集为空
        if(xtcsEntityList == null || xtcsEntityList.size() <= 0) {
            return false;
        }

        //如果查询到的结果集不为空
        //判断参数xtcsEntity的id是否为空（id为空表示新增，id不为空表示修改）
        if(xtcsEntity.getId() == null || "".equals(xtcsEntity.getId())) {
            return true;
        }
        //判断参数xtcsEntity的id不为空
        for(XtcsEntity tempXtcsEntity : xtcsEntityList) {
            if(!tempXtcsEntity.getId().equals(xtcsEntity.getId())) {
                return true;
            }
        }
        return false;
    }
}
