package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmNotificationSettingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int countByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int deleteByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int insert(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int insertSelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    List<CrmNotificationSetting> selectByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    CrmNotificationSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int updateByExampleSelective(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int updateByExample(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    int updateByPrimaryKeySelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    Integer insertAndReturnKey(CrmNotificationSetting value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    void massUpdateWithSession(@Param("record") CrmNotificationSetting record, @Param("primaryKeys") List primaryKeys);
}