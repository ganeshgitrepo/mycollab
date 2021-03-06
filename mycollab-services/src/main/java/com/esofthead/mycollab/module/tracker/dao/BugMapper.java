package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.BugExample;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BugMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int countByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int deleteByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int insert(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int insertSelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    List<BugWithBLOBs> selectByExampleWithBLOBs(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    BugWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExampleSelective(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByPrimaryKeySelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    Integer insertAndReturnKey(BugWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    void massUpdateWithSession(@Param("record") BugWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}