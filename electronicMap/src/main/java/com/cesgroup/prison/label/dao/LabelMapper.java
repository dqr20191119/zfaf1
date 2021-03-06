package com.cesgroup.prison.label.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.label.entity.Label;

public interface LabelMapper extends BaseDao<Label, String>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CDS_MESSAGE_LABEL_INFO
     *
     * @mbggenerated
     */
	public int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CDS_MESSAGE_LABEL_INFO
     *
     * @mbggenerated
     */
    public int insertLabel(Label record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CDS_MESSAGE_LABEL_INFO
     *
     * @mbggenerated
     */
    public int insertSelectiveLabel(Label record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CDS_MESSAGE_LABEL_INFO
     *
     * @mbggenerated
     */
    public Label selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CDS_MESSAGE_LABEL_INFO
     *
     * @mbggenerated
     */
    public int updateByPrimaryKeySelective(Label record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CDS_MESSAGE_LABEL_INFO
     *
     * @mbggenerated
     */
    public int updateByPrimaryKey(Label record);
    
    /**
     * 分页查询标签
     * @param label
     * @return
     */
    public Page<Label> selectPageLabel(Label label,Pageable pageable);
    /**
     * 根据监狱编号和区域编号查询
     * @param cusNumber
     * @param areaId
     * @return
     */
    public List<Label> findByCusNumberAndAreaId(@Param("mliCusNumber")String mliCusNumber,@Param("mliAreaId")String mliAreaId);

    /**
     * 根据标签Code查询
     * @param label
     * @return
     */
    public Label findByLabelCode(Label label);
}