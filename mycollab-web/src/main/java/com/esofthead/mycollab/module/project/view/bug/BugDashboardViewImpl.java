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

import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@SuppressWarnings("serial")
@ViewComponent
public class BugDashboardViewImpl extends AbstractPageView implements
		BugDashboardView {

	private VerticalLayout leftColumn, rightColumn, headerWrapper;

	public BugDashboardViewImpl() {
		super();
		this.setMargin(new MarginInfo(true, false, false, false));
		this.initUI();
	}

	private void initUI() {
		headerWrapper = new VerticalLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName("bugdashboard-header");

		this.addComponent(headerWrapper);

		final HorizontalLayout body = new HorizontalLayout();
		body.setWidth("100%");
		body.setSpacing(true);

		this.leftColumn = new VerticalLayout();
		this.leftColumn.setSpacing(true);
		this.leftColumn.setMargin(new MarginInfo(false, true, false, false));
		body.addComponent(this.leftColumn);
		body.setExpandRatio(this.leftColumn, 1.0f);

		this.rightColumn = new VerticalLayout();
		this.rightColumn.setSpacing(true);

		body.addComponent(this.rightColumn);
		body.setComponentAlignment(this.rightColumn, Alignment.TOP_RIGHT);

		this.addComponent(body);
	}

	private void initHeader() {
		headerWrapper.removeAllComponents();
		final CssLayout headerTop = new CssLayout();
		headerTop.setWidth("100%");
		headerTop.addStyleName("bugdashboard-header-top");
		final HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		final Label title = new Label(
				LocalizationHelper.getMessage(BugI18nEnum.BUG_DASHBOARD_TITLE));
		title.setStyleName("h2");
		final Image icon = new Image(null, MyCollabResource.newResource("icons/24/project/bug.png"));
		header.addComponent(icon);
		header.addComponent(title);
		header.setExpandRatio(title, 1.0f);
		header.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		header.setSpacing(true);

		final Button createBugBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_BUG_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
		createBugBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));

		final SplitButton controlsBtn = new SplitButton(createBugBtn);
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlsBtn.setWidth(SIZE_UNDEFINED, Sizeable.Unit.PIXELS);

		final VerticalLayout btnControlsLayout = new VerticalLayout();
		btnControlsLayout.setWidth("150px");
		final Button createComponentBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_COMPONENT_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoAdd(this, null));
					}
				});
		createComponentBtn.setStyleName("link");
		createComponentBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.COMPONENTS));
		btnControlsLayout.addComponent(createComponentBtn);

		final Button createVersionBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_VERSION_ACTION),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoAdd(this, null));
					}
				});
		createVersionBtn.setStyleName("link");
		createVersionBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.VERSIONS));
		btnControlsLayout.addComponent(createVersionBtn);

		controlsBtn.setContent(btnControlsLayout);

		headerTop.addComponent(header);
		headerWrapper.addComponent(headerTop);

		header.addComponent(controlsBtn);
		header.setExpandRatio(controlsBtn, 1.0f);
		header.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);
	}

	@Override
	public void displayDashboard() {
		initHeader();
		this.leftColumn.removeAllComponents();
		this.rightColumn.removeAllComponents();

		this.rightColumn.setWidth("400px");

		final SimpleProject project = CurrentProjectVariables.getProject();

		final MyBugListWidget myBugListWidget = new MyBugListWidget();
		this.leftColumn.addComponent(myBugListWidget);
		final BugSearchCriteria myBugsSearchCriteria = new BugSearchCriteria();
		myBugsSearchCriteria
				.setProjectId(new NumberSearchField(project.getId()));
		myBugsSearchCriteria.setStatuses(new SetSearchField<String>(
				SearchField.AND, new String[] { BugStatusConstants.INPROGRESS,
						BugStatusConstants.OPEN, BugStatusConstants.REOPENNED,
						BugStatusConstants.RESOLVED }));
		myBugsSearchCriteria.setAssignuser(new StringSearchField(AppContext
				.getUsername()));

		myBugListWidget.setSearchCriteria(myBugsSearchCriteria);

		final DueBugWidget dueBugWidget = new DueBugWidget();
		this.leftColumn.addComponent(dueBugWidget);
		final BugSearchCriteria dueDefectsCriteria = new BugSearchCriteria();
		dueDefectsCriteria.setProjectId(new NumberSearchField(project.getId()));
		dueDefectsCriteria.setDueDate(new DateSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, new GregorianCalendar()
						.getTime()));
		dueDefectsCriteria.setStatuses(new SetSearchField<String>(
				SearchField.AND, new String[] { BugStatusConstants.INPROGRESS,
						BugStatusConstants.OPEN, BugStatusConstants.REOPENNED,
						BugStatusConstants.RESOLVED }));
		dueBugWidget.setSearchCriteria(dueDefectsCriteria);

		final RecentBugUpdateWidget updateBugWidget = new RecentBugUpdateWidget();
		this.leftColumn.addComponent(updateBugWidget);

		// Unresolved by assignee
		final UnresolvedBugsByAssigneeWidget2 unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget2();
		final BugSearchCriteria unresolvedByAssigneeSearchCriteria = new BugSearchCriteria();
		unresolvedByAssigneeSearchCriteria.setProjectId(new NumberSearchField(
				project.getId()));
		unresolvedByAssigneeSearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedByAssigneeWidget
				.setSearchCriteria(unresolvedByAssigneeSearchCriteria);
		this.rightColumn.addComponent(unresolvedByAssigneeWidget);

		// Unresolve by priority widget
		final UnresolvedBugsByPriorityWidget2 unresolvedByPriorityWidget = new UnresolvedBugsByPriorityWidget2();
		final BugSearchCriteria unresolvedByPrioritySearchCriteria = new BugSearchCriteria();
		unresolvedByPrioritySearchCriteria.setProjectId(new NumberSearchField(
				project.getId()));
		unresolvedByPrioritySearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		unresolvedByPriorityWidget
				.setSearchCriteria(unresolvedByPrioritySearchCriteria);
		this.rightColumn.addComponent(unresolvedByPriorityWidget);

		// bug chart
		final BugSearchCriteria recentDefectsCriteria = new BugSearchCriteria();
		recentDefectsCriteria.setProjectId(new NumberSearchField(project
				.getId()));
		updateBugWidget.setSearchCriteria(recentDefectsCriteria);

		final BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
		chartSearchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		BugChartComponent bugChartComponent = null;
		bugChartComponent = new BugChartComponent(chartSearchCriteria, 400, 200);
		this.rightColumn.addComponent(bugChartComponent);

	}
}
