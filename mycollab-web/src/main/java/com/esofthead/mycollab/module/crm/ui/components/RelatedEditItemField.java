/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionWindow;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignSelectionWindow;
import com.esofthead.mycollab.module.crm.view.cases.CaseSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionWindow;
import com.esofthead.mycollab.module.crm.view.lead.LeadSelectionWindow;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunitySelectionWindow;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class RelatedEditItemField extends CustomField<String> implements
		FieldSelection {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(RelatedEditItemField.class);

	private RelatedItemComboBox relatedItemComboBox;
	private Object bean;

	private TextField itemField;
	private Image browseBtn;
	private Image clearBtn;

	public RelatedEditItemField(String[] types, Object bean) {
		this.bean = bean;

		relatedItemComboBox = new RelatedItemComboBox(types);
		itemField = new TextField();
		itemField.setEnabled(true);

		browseBtn = new Image(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		browseBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
				String type = (String) relatedItemComboBox.getValue();
				if ("Account".equals(type)) {
					AccountSelectionWindow accountWindow = new AccountSelectionWindow(
							RelatedEditItemField.this);
					UI.getCurrent().addWindow(accountWindow);
					accountWindow.show();
				} else if ("Campaign".equals(type)) {
					CampaignSelectionWindow campaignWindow = new CampaignSelectionWindow(
							RelatedEditItemField.this);
					UI.getCurrent().addWindow(campaignWindow);
					campaignWindow.show();
				} else if ("Contact".equals(type)) {
					ContactSelectionWindow contactWindow = new ContactSelectionWindow(
							RelatedEditItemField.this);
					UI.getCurrent().addWindow(contactWindow);
					contactWindow.show();
				} else if ("Lead".equals(type)) {
					LeadSelectionWindow leadWindow = new LeadSelectionWindow(
							RelatedEditItemField.this);
					UI.getCurrent().addWindow(leadWindow);
					leadWindow.show();
				} else if ("Opportunity".equals(type)) {
					OpportunitySelectionWindow opportunityWindow = new OpportunitySelectionWindow(
							RelatedEditItemField.this);
					UI.getCurrent().addWindow(opportunityWindow);
					opportunityWindow.show();
				} else if ("Case".equals(type)) {
					CaseSelectionWindow caseWindow = new CaseSelectionWindow(
							RelatedEditItemField.this);
					UI.getCurrent().addWindow(caseWindow);
					caseWindow.show();
				} else {
					relatedItemComboBox.focus();
				}
			}
		});

		clearBtn = new Image(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				try {
					PropertyUtils.setProperty(RelatedEditItemField.this.bean,
							"typeid", null);
				} catch (Exception e) {
					log.error("Error while saving type", e);
				}
			}
		});
	}

	@Override
	protected Component initContent() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		layout.addComponent(relatedItemComboBox);

		layout.addComponent(itemField);
		layout.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);

		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		return layout;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		Object value = newDataSource.getValue();
		if (value instanceof String) {
			setType((String) value);
			super.setPropertyDataSource(newDataSource);
		} else {
			super.setPropertyDataSource(newDataSource);
		}
	}

	@Override
	public void commit() throws SourceException, InvalidValueException {
		String value = (String) relatedItemComboBox.getValue();
		this.setInternalValue(value);
		super.commit();
	}

	public void setType(String type) {
		log.debug("Set type: " + type);
		relatedItemComboBox.select(type);
		try {
			Integer typeid = (Integer) PropertyUtils
					.getProperty(bean, "typeid");
			if (typeid != null) {
				if ("Account".equals(type)) {
					AccountService accountService = ApplicationContextUtil
							.getSpringBean(AccountService.class);
					SimpleAccount account = accountService.findById(typeid,
							AppContext.getAccountId());
					if (account != null) {
						itemField.setValue(account.getAccountname());
					}
				} else if ("Campaign".equals(type)) {
					CampaignService campaignService = ApplicationContextUtil
							.getSpringBean(CampaignService.class);
					SimpleCampaign campaign = campaignService.findById(typeid,
							AppContext.getAccountId());
					if (campaign != null) {
						itemField.setValue(campaign.getCampaignname());
					}
				} else if ("Contact".equals(type)) {
					ContactService contactService = ApplicationContextUtil
							.getSpringBean(ContactService.class);
					SimpleContact contact = contactService.findById(typeid,
							AppContext.getAccountId());
					if (contact != null) {
						itemField.setValue(contact.getContactName());
					}
				} else if ("Lead".equals(type)) {
					LeadService leadService = ApplicationContextUtil
							.getSpringBean(LeadService.class);
					SimpleLead lead = leadService.findById(typeid,
							AppContext.getAccountId());
					if (lead != null) {
						itemField.setValue(lead.getLeadName());
					}
				} else if ("Opportunity".equals(type)) {
					OpportunityService opportunityService = ApplicationContextUtil
							.getSpringBean(OpportunityService.class);
					SimpleOpportunity opportunity = opportunityService
							.findById(typeid, AppContext.getAccountId());
					if (opportunity != null) {
						itemField.setValue(opportunity.getOpportunityname());
					}
				} else if ("Case".equals(type)) {
					CaseService caseService = ApplicationContextUtil
							.getSpringBean(CaseService.class);
					SimpleCase cases = caseService.findById(typeid,
							AppContext.getAccountId());
					if (cases != null) {
						itemField.setValue(cases.getSubject());
					}
				}
			}

		} catch (Exception e) {
			log.error("Error when set type", e);
		}
	}

	@Override
	public void fireValueChange(Object data) {
		try {
			if (data instanceof SimpleAccount) {
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleAccount) data).getId());
				itemField.setValue(((SimpleAccount) data).getAccountname());
			} else if (data instanceof SimpleCampaign) {
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleCampaign) data).getId());
				itemField.setValue(((SimpleCampaign) data).getCampaignname());
			} else if (data instanceof SimpleContact) {
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleContact) data).getId());
				itemField.setValue(((SimpleContact) data).getContactName());
			} else if (data instanceof SimpleLead) {
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleLead) data).getId());
				itemField.setValue(((SimpleLead) data).getLeadName());
			} else if (data instanceof SimpleOpportunity) {
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleOpportunity) data).getId());
				itemField.setValue(((SimpleOpportunity) data)
						.getOpportunityname());
			} else if (data instanceof SimpleCase) {
				PropertyUtils.setProperty(bean, "typeid",
						((SimpleCase) data).getId());
				itemField.setValue(((SimpleCase) data).getSubject());
			}
		} catch (Exception e) {
			log.error("Error when fire value", e);
		}
	}

	private class RelatedItemComboBox extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public RelatedItemComboBox(String[] types) {
			super();
			setCaption(null);
			this.setWidth("100px");
			this.loadData(types);
			this.select(getNullSelectionItemId());
		}
	}
}
