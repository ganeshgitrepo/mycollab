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
package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.ActivityRelatedItemListComp;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class CaseReadComp extends AbstractPreviewItemComp<SimpleCase> {
	private static final long serialVersionUID = 1L;

	protected CaseContactListComp associateContactList;
	protected NoteListItems noteListItems;
	protected ActivityRelatedItemListComp associateActivityList;

	public CaseReadComp() {
		super(MyCollabResource.newResource("icons/22/crm/case.png"));
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleCase> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleCase>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				CaseHistoryLogWindow historyLog = new CaseHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.CASE);
				historyLog.loadHistory(beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new CrmPreviewFormControlsGenerator<SimpleCase>(previewForm)
				.createButtonControls(RolePermissionCollections.CRM_CASE);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabSheet tabContainer = new TabSheet();
		tabContainer.setWidth("100%");

		tabContainer.addTab(noteListItems, "Notes",
				MyCollabResource.newResource("icons/16/crm/note.png"));
		tabContainer.addTab(this.associateContactList, "Contacts",
				MyCollabResource.newResource("icons/16/crm/contact.png"));
		tabContainer.addTab(this.associateActivityList, "Activities",
				MyCollabResource.newResource("icons/16/crm/calendar.png"));
		return tabContainer;
	}

	@Override
	protected void onPreviewItem() {
		displayNotes();
		displayActivities();
		displayContacts();
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getSubject();
	}

	@Override
	protected void initRelatedComponents() {
		associateContactList = new CaseContactListComp();
		associateActivityList = new ActivityRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.CASE,
				CasesDefaultFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleCase> initBeanFormFieldFactory() {
		return new CaseReadFormFieldFactory(previewForm);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CASE, beanItem.getId());
	}

	protected void displayActivities() {
		ActivitySearchCriteria criteria = new ActivitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CASE));
		criteria.setTypeid(new NumberSearchField(beanItem.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	protected void displayContacts() {
		associateContactList.displayContacts(beanItem);
	}

	public ActivityRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public CaseContactListComp getAssociateContactList() {
		return associateContactList;
	}

	public SimpleCase getCase() {
		return beanItem;
	}

	public AdvancedPreviewBeanForm<SimpleCase> getPreviewForm() {
		return previewForm;
	}

}
