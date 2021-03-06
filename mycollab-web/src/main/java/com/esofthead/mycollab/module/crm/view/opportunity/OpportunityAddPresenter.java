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
package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class OpportunityAddPresenter extends
		CrmGenericPresenter<OpportunityAddView> {

	private static final long serialVersionUID = 1L;

	public OpportunityAddPresenter() {
		super(OpportunityAddView.class);
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<SimpleOpportunity>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final SimpleOpportunity item) {
						saveOpportunity(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final SimpleOpportunity item) {
						saveOpportunity(item);
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNTIES_HEADER));

			SimpleOpportunity opportunity = null;
			if (data.getParams() instanceof SimpleOpportunity) {
				opportunity = (SimpleOpportunity) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				OpportunityService accountService = ApplicationContextUtil
						.getSpringBean(OpportunityService.class);
				opportunity = accountService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (opportunity == null) {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}
			}
			super.onGo(container, data);
			view.editItem(opportunity);

			if (opportunity.getId() == null) {
				AppContext.addFragment("crm/opportunity/add",
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
								"Opportunity"));
			} else {
				AppContext
						.addFragment(
								"crm/opportunity/edit/"
										+ UrlEncodeDecoder.encode(opportunity
												.getId()),
								LocalizationHelper.getMessage(
										GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
										"Opportunity",
										opportunity.getOpportunityname()));
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

	public void saveOpportunity(Opportunity opportunity) {
		OpportunityService opportunityService = ApplicationContextUtil
				.getSpringBean(OpportunityService.class);

		opportunity.setSaccountid(AppContext.getAccountId());
		if (opportunity.getId() == null) {
			opportunityService.saveWithSession(opportunity,
					AppContext.getUsername());

			if ((opportunity.getExtraData() != null)
					&& (opportunity.getExtraData() instanceof SimpleContact)) {
				ContactOpportunity associateOpportunity = new ContactOpportunity();
				associateOpportunity.setOpportunityid(opportunity.getId());
				associateOpportunity.setContactid(((SimpleContact) opportunity
						.getExtraData()).getId());
				associateOpportunity.setCreatedtime(new GregorianCalendar()
						.getTime());
				ContactService contactService = ApplicationContextUtil
						.getSpringBean(ContactService.class);
				contactService.saveContactOpportunityRelationship(
						Arrays.asList(associateOpportunity),
						AppContext.getAccountId());
			}
		} else {
			opportunityService.updateWithSession(opportunity,
					AppContext.getUsername());
		}

	}
}
