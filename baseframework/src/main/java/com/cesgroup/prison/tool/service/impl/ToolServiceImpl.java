
package com.cesgroup.prison.tool.service.impl;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.tool.dao.TKhLdgjMapper;
import com.cesgroup.prison.tool.entity.TKhLdgj;
import com.cesgroup.prison.tool.service.ToolService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 18:18 2019/5/26
 * @ Description：劳具已发放
 */
@Service("toolService")
public class ToolServiceImpl implements ToolService {

    @Resource
    private TKhLdgjMapper tKhLdgjMapper;
    //劳具数量
    @Override
    public Integer getToolOpenNum(String prisonCode) {
        UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        if (userBean != null) {
            prisonCode = userBean.getCusNumber();
        }
        return tKhLdgjMapper.getToolNum(prisonCode);
    }
    //列表页
    @Override
    public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable) {
        try {
            UserBean userBean = AuthSystemFacade.getLoginUserInfo();
            if (userBean != null) {
                map.put("prisonCode", userBean.getCusNumber());
            }
           // map.put("dsdUpdtTime", Util.toStr(Util.DF_DATE));
            return tKhLdgjMapper.listAll(map, pageable);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TKhLdgj insert(TKhLdgj entity) {
        return null;
    }

    @Override
    public TKhLdgj update(TKhLdgj entity) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public List<TKhLdgj> selectAll() {
        return null;
    }

    @Override
    public TKhLdgj selectOne(String s) {
        return null;
    }

    @Override
    public long selectCount() {
        return 0;
    }

    @Override
    public List<TKhLdgj> findAll(Specification<TKhLdgj> spec) {
        return null;
    }

    @Override
    public Page<TKhLdgj> findPage(Specification<TKhLdgj> spec, Pageable page) {
        return null;
    }
}
