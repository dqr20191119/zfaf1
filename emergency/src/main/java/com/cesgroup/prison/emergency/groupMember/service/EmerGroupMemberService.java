package com.cesgroup.prison.emergency.groupMember.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.groupMember.dto.EmerGroupMemberDto;
import com.cesgroup.prison.emergency.groupMember.entity.EmerGroupMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案工作组成员业务访问接口
 */
public interface EmerGroupMemberService extends IBaseCRUDService<EmerGroupMember, String> {
    /**
     * 分页查询应急预案工作组成员信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 保存或更新应急预案工作组成员
     *
     * @param emerGroupMemberDto
     * @throws ServiceException
     */
    public void saveOrUpdate(EmerGroupMemberDto emerGroupMemberDto) throws ServiceException;

    /**
     * 删除应急预案工作组成员
     *
     * @param ids
     * @throws ServiceException
     */
    public void deleteByIds(String ids) throws ServiceException;

    /**
     * 根据工作组ID，查询工作组成员列表
     *
     * @param groupId
     * @return
     * @throws ServiceException
     */
    public List<EmerGroupMemberDto> queryGroupMemberListByGroupId(String groupId) throws ServiceException;
}
