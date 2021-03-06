/*Domain class of table m_crm_campaign*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@com.esofthead.mycollab.core.db.metadata.Table("m_crm_campaign")
class Campaign extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.id
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.campaignName
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("campaignName")
    private String campaignname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.startDate
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("startDate")
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.endDate
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("endDate")
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.currencyId
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("currencyId")
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.impressionnote
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("impressionnote")
    private String impressionnote;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.budget
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("budget")
    private Long budget;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.actualCost
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("actualCost")
    private Long actualcost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedRevenue
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("expectedRevenue")
    private Long expectedrevenue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedCost
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("expectedCost")
    private Long expectedcost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.impression
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("impression")
    private Integer impression;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdTime
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdUser
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.sAccountId
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.status
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.type
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.assignUser
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("assignUser")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.lastUpdatedTime
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.id
     *
     * @return the value of m_crm_campaign.id
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.id
     *
     * @param id the value for m_crm_campaign.id
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.campaignName
     *
     * @return the value of m_crm_campaign.campaignName
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public String getCampaignname() {
        return campaignname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.campaignName
     *
     * @param campaignname the value for m_crm_campaign.campaignName
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setCampaignname(String campaignname) {
        this.campaignname = campaignname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.startDate
     *
     * @return the value of m_crm_campaign.startDate
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.startDate
     *
     * @param startdate the value for m_crm_campaign.startDate
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.endDate
     *
     * @return the value of m_crm_campaign.endDate
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.endDate
     *
     * @param enddate the value for m_crm_campaign.endDate
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.currencyId
     *
     * @return the value of m_crm_campaign.currencyId
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Integer getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.currencyId
     *
     * @param currencyid the value for m_crm_campaign.currencyId
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.impressionnote
     *
     * @return the value of m_crm_campaign.impressionnote
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public String getImpressionnote() {
        return impressionnote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.impressionnote
     *
     * @param impressionnote the value for m_crm_campaign.impressionnote
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setImpressionnote(String impressionnote) {
        this.impressionnote = impressionnote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.budget
     *
     * @return the value of m_crm_campaign.budget
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Long getBudget() {
        return budget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.budget
     *
     * @param budget the value for m_crm_campaign.budget
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setBudget(Long budget) {
        this.budget = budget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.actualCost
     *
     * @return the value of m_crm_campaign.actualCost
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Long getActualcost() {
        return actualcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.actualCost
     *
     * @param actualcost the value for m_crm_campaign.actualCost
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setActualcost(Long actualcost) {
        this.actualcost = actualcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.expectedRevenue
     *
     * @return the value of m_crm_campaign.expectedRevenue
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Long getExpectedrevenue() {
        return expectedrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.expectedRevenue
     *
     * @param expectedrevenue the value for m_crm_campaign.expectedRevenue
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setExpectedrevenue(Long expectedrevenue) {
        this.expectedrevenue = expectedrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.expectedCost
     *
     * @return the value of m_crm_campaign.expectedCost
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Long getExpectedcost() {
        return expectedcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.expectedCost
     *
     * @param expectedcost the value for m_crm_campaign.expectedCost
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setExpectedcost(Long expectedcost) {
        this.expectedcost = expectedcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.impression
     *
     * @return the value of m_crm_campaign.impression
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Integer getImpression() {
        return impression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.impression
     *
     * @param impression the value for m_crm_campaign.impression
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.createdTime
     *
     * @return the value of m_crm_campaign.createdTime
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.createdTime
     *
     * @param createdtime the value for m_crm_campaign.createdTime
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.createdUser
     *
     * @return the value of m_crm_campaign.createdUser
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.createdUser
     *
     * @param createduser the value for m_crm_campaign.createdUser
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.sAccountId
     *
     * @return the value of m_crm_campaign.sAccountId
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.sAccountId
     *
     * @param saccountid the value for m_crm_campaign.sAccountId
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.status
     *
     * @return the value of m_crm_campaign.status
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.status
     *
     * @param status the value for m_crm_campaign.status
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.type
     *
     * @return the value of m_crm_campaign.type
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.type
     *
     * @param type the value for m_crm_campaign.type
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.assignUser
     *
     * @return the value of m_crm_campaign.assignUser
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.assignUser
     *
     * @param assignuser the value for m_crm_campaign.assignUser
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.lastUpdatedTime
     *
     * @return the value of m_crm_campaign.lastUpdatedTime
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_campaign.lastUpdatedTime
     *
     * @mbggenerated Sun Feb 09 16:13:44 ICT 2014
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}