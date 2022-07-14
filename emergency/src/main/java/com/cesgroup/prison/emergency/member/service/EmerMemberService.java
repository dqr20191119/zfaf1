package com.cesgroup.prison.emergency.member.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急成员业务访问接口
 */
public interface EmerMemberService extends IBaseCRUDService<EmerMember, String> {
    /**
     * 分页查询应急成员信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 根据主键ID，查询应急成员数据
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerMember queryById(String id) throws ServiceException;

    /**
     * 保存或更新应急成员
     *
     * @param emerMember
     * @return
     * @throws ServiceException
     */
    public EmerMember saveOrUpdate(EmerMember emerMember) throws ServiceException;

    /**
     * 删除应急成员
     *
     * @param ids
     * @throws ServiceException
     */
    public void deleteByIds(String ids) throws ServiceException;

    /**
     * 根据工作组ID，查询关联的工作组成员列表
     * @param groupId
     * @return
     * @throws ServiceException
     */
    public List<EmerMember> queryListByGroupId(String groupId) throws ServiceException;
}
