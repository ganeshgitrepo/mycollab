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

package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ComponentSearchPanel extends
		GenericSearchPanel<ComponentSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private final SimpleProject project;
	protected ComponentSearchCriteria searchCriteria;

	public ComponentSearchPanel() {
		this.project = CurrentProjectVariables.getProject();
	}

	@Override
	public void attach() {
		super.attach();
		this.createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new ComponentBasicSearchCriteria());
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(true);

		final Image titleIcon = new Image(null,
				MyCollabResource.newResource("icons/24/project/component.png"));
		layout.addComponent(titleIcon);
		layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

		final Label componenttitle = new Label("Components");
		componenttitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(componenttitle);
		layout.setExpandRatio(componenttitle, 1.0f);
		layout.setComponentAlignment(componenttitle, Alignment.MIDDLE_LEFT);

		final Button createBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_COMPONENT_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoAdd(this, null));
					}
				});
		createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.COMPONENTS));
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));

		UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class ComponentBasicSearchCriteria extends
			GenericSearchPanel.BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public ComponentBasicSearchCriteria() {
			super(ComponentSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructHeader() {
			return ComponentSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.setMargin(true);
			basicSearchBody.addComponent(new Label("Name"));
			this.nameField = new TextField();
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);
			this.myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button("Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							ComponentBasicSearchCriteria.this
									.callSearchAction();
						}
					});
			searchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			basicSearchBody.addComponent(searchBtn);

			final Button clearBtn = new Button("Clear",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							ComponentBasicSearchCriteria.this.nameField
									.setValue("");
						}
					});
			clearBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			basicSearchBody.addComponent(clearBtn);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			ComponentSearchPanel.this.searchCriteria = new ComponentSearchCriteria();
			ComponentSearchPanel.this.searchCriteria
					.setProjectid(new NumberSearchField(SearchField.AND,
							ComponentSearchPanel.this.project.getId()));
			ComponentSearchPanel.this.searchCriteria
					.setComponentName(new StringSearchField(this.nameField
							.getValue().toString().trim()));
			return ComponentSearchPanel.this.searchCriteria;
		}
	}

}
