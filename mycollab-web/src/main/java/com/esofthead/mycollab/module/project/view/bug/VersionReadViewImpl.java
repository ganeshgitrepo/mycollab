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

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class VersionReadViewImpl extends AbstractPreviewItemComp<Version>
        implements VersionReadView {
    private static final long serialVersionUID = 1L;

    private VersionHistoryLogList historyLogList;
    private RelatedBugComp relatedBugComp;

    private Button quickActionStatusBtn;

    public VersionReadViewImpl() {
        super(MyCollabResource.newResource("icons/22/project/version.png"));

        this.setMargin(new MarginInfo(true, false, false, false));
    }

    @Override
    public HasPreviewFormHandlers<Version> getPreviewFormHandlers() {
        return this.previewForm;
    }

    @Override
    protected void initRelatedComponents() {
        historyLogList = new VersionHistoryLogList(ModuleNameConstants.PRJ,
                ProjectContants.BUG_VERSION);
        relatedBugComp = new RelatedBugComp();
    }

    @Override
    protected void onPreviewItem() {
        relatedBugComp.displayBugReports();
        historyLogList.loadHistory(beanItem.getId());

        if (beanItem.getStatus() == null || beanItem.getStatus().equals("Open")) {
            removeLayoutStyleName(UIConstants.LINK_COMPLETED);
            quickActionStatusBtn.setCaption("Close");
            quickActionStatusBtn.setIcon(MyCollabResource
                    .newResource("icons/16/project/closeTask.png"));
        } else {
            addLayoutStyleName(UIConstants.LINK_COMPLETED);
            quickActionStatusBtn.setCaption("ReOpen");
            quickActionStatusBtn.setIcon(MyCollabResource
                    .newResource("icons/16/project/reopenTask.png"));

        }

    }

    @Override
    protected String initFormTitle() {
        return beanItem.getVersionname();
    }

    @Override
    protected AdvancedPreviewBeanForm<Version> initPreviewForm() {
        return new AdvancedPreviewBeanForm<Version>();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new VersionFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<Version> initBeanFormFieldFactory() {
        return new AbstractBeanFieldGroupViewFieldFactory<Version>(previewForm) {
            private static final long serialVersionUID = 1L;

            @Override
            protected Field<?> onCreateField(Object propertyId) {
                if (propertyId.equals("duedate")) {
                    return new DefaultFormViewFieldFactory.FormDateViewField(
                            beanItem.getDuedate());
                }
                return null;
            }
        };
    }

    @Override
    protected ComponentContainer createButtonControls() {
        ProjectPreviewFormControlsGenerator<Version> versionPreviewForm = new ProjectPreviewFormControlsGenerator<Version>(
                previewForm);
        final HorizontalLayout topPanel = versionPreviewForm
                .createButtonControls(ProjectRolePermissionCollections.VERSIONS);

        quickActionStatusBtn = new Button("", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                if (beanItem.getStatus() != null
                        && beanItem.getStatus().equals("Close")) {
                    beanItem.setStatus("Open");
                    VersionReadViewImpl.this
                            .removeLayoutStyleName(UIConstants.LINK_COMPLETED);
                    quickActionStatusBtn.setCaption("Close");
                    quickActionStatusBtn.setIcon(MyCollabResource
                            .newResource("icons/16/project/closeTask.png"));
                } else {
                    beanItem.setStatus("Close");

                    VersionReadViewImpl.this
                            .addLayoutStyleName(UIConstants.LINK_COMPLETED);
                    quickActionStatusBtn.setCaption("ReOpen");
                    quickActionStatusBtn.setIcon(MyCollabResource
                            .newResource("icons/16/project/reopenTask.png"));
                }

                VersionService service = ApplicationContextUtil
                        .getSpringBean(VersionService.class);
                service.updateWithSession(beanItem, AppContext.getUsername());

            }
        });

        quickActionStatusBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        versionPreviewForm.insertToControlBlock(quickActionStatusBtn);

        if (!CurrentProjectVariables
                .canWrite(ProjectRolePermissionCollections.VERSIONS)) {
            quickActionStatusBtn.setEnabled(false);
        }

        return topPanel;
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        final TabsheetLazyLoadComp tabContainer = new TabsheetLazyLoadComp();
        tabContainer.setWidth("100%");

        tabContainer.addTab(relatedBugComp, "Related Bugs",
                MyCollabResource.newResource("icons/16/project/gray/bug.png"));
        tabContainer.addTab(historyLogList, "History", MyCollabResource
                .newResource("icons/16/project/gray/history.png"));
        return tabContainer;
    }

    class RelatedBugComp extends VerticalLayout implements
            IBugReportDisplayContainer {
        private static final long serialVersionUID = 1L;

        private HorizontalLayout bottomLayout;

        public RelatedBugComp() {
            this.setMargin(new MarginInfo(false, false, true, false));
            this.setSpacing(false);
            this.setWidth("100%");
            this.addStyleName("relatedbug-comp");

            final HorizontalLayout header = new HorizontalLayout();
            header.setMargin(new MarginInfo(true, true, true, false));
            header.setSpacing(true);
            header.setWidth("100%");
            header.addStyleName("relatedbug-comp-header");
            final Label taskGroupSelection = new Label("");
            taskGroupSelection.addStyleName("h2");
            taskGroupSelection.addStyleName(UIConstants.THEME_NO_BORDER);
            header.addComponent(taskGroupSelection);
            header.setExpandRatio(taskGroupSelection, 1.0f);
            header.setComponentAlignment(taskGroupSelection,
                    Alignment.MIDDLE_LEFT);

            ToggleButtonGroup viewGroup = new ToggleButtonGroup();

            final Button simpleDisplay = new Button(null,
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            displaySimpleView();
                        }
                    });
            simpleDisplay.setIcon(MyCollabResource
                    .newResource("icons/16/project/list_display.png"));

            viewGroup.addButton(simpleDisplay);

            final Button advanceDisplay = new Button(null,
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            displayAdvancedView();
                        }
                    });
            advanceDisplay.setIcon(MyCollabResource
                    .newResource("icons/16/project/bug_advanced_display.png"));
            viewGroup.addButton(advanceDisplay);
            header.addComponent(viewGroup);
            header.setComponentAlignment(viewGroup, Alignment.MIDDLE_RIGHT);

            this.addComponent(header);

            this.bottomLayout = new HorizontalLayout();
            this.bottomLayout
                    .setMargin(new MarginInfo(false, true, true, true));
            this.bottomLayout.setSpacing(true);
            this.bottomLayout.setWidth("100%");

            advanceDisplay.addStyleName("selected");
        }

        private void displaySimpleView() {
            if (this.getComponentCount() > 1) {
                this.removeComponent(this.getComponent(1));
            }

            final BugSearchCriteria criteria = new BugSearchCriteria();
            criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
                    .getProjectId()));
            criteria.setVersionids(new SetSearchField<Integer>(beanItem.getId()));

            final BugSimpleDisplayWidget displayWidget = new BugSimpleDisplayWidget();
            this.addComponent(displayWidget);
            displayWidget.setSearchCriteria(criteria);
        }

        private void displayAdvancedView() {
            if (this.getComponentCount() > 1) {
                this.removeComponent(this.getComponent(1));
            }

            this.addComponent(this.bottomLayout);

            this.bottomLayout.removeAllComponents();
            final SimpleProject project = CurrentProjectVariables.getProject();
            final VerticalLayout leftColumn = new VerticalLayout();
            leftColumn.setSpacing(true);
            this.bottomLayout.addComponent(leftColumn);
            this.bottomLayout.setExpandRatio(leftColumn, 1.0f);

            final UnresolvedBugsByPriorityWidget unresolvedBugWidget = new UnresolvedBugsByPriorityWidget(
                    this);
            leftColumn.addComponent(unresolvedBugWidget);
            leftColumn.setComponentAlignment(unresolvedBugWidget,
                    Alignment.MIDDLE_CENTER);

            final BugSearchCriteria unresolvedByPrioritySearchCriteria = new BugSearchCriteria();
            unresolvedByPrioritySearchCriteria
                    .setProjectId(new NumberSearchField(project.getId()));
            unresolvedByPrioritySearchCriteria
                    .setVersionids(new SetSearchField<Integer>(beanItem.getId()));
            unresolvedByPrioritySearchCriteria
                    .setStatuses(new SetSearchField<String>(SearchField.AND,
                            new String[] { BugStatusConstants.INPROGRESS,
                                    BugStatusConstants.OPEN,
                                    BugStatusConstants.REOPENNED }));
            unresolvedBugWidget
                    .setSearchCriteria(unresolvedByPrioritySearchCriteria);

            final UnresolvedBugsByAssigneeWidget unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget(
                    this);
            leftColumn.addComponent(unresolvedByAssigneeWidget);
            leftColumn.setComponentAlignment(unresolvedByAssigneeWidget,
                    Alignment.MIDDLE_CENTER);

            final BugSearchCriteria unresolvedByAssigneeSearchCriteria = new BugSearchCriteria();
            unresolvedByAssigneeSearchCriteria
                    .setProjectId(new NumberSearchField(project.getId()));
            unresolvedByAssigneeSearchCriteria
                    .setVersionids(new SetSearchField<Integer>(beanItem.getId()));
            unresolvedByAssigneeSearchCriteria
                    .setStatuses(new SetSearchField<String>(SearchField.AND,
                            new String[] { BugStatusConstants.INPROGRESS,
                                    BugStatusConstants.OPEN,
                                    BugStatusConstants.REOPENNED }));
            unresolvedByAssigneeWidget
                    .setSearchCriteria(unresolvedByAssigneeSearchCriteria);

            final VerticalLayout rightColumn = new VerticalLayout();
            rightColumn.setMargin(new MarginInfo(false, false, false, true));
            this.bottomLayout.addComponent(rightColumn);

            final BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
            chartSearchCriteria.setProjectId(new NumberSearchField(
                    CurrentProjectVariables.getProjectId()));
            chartSearchCriteria.setVersionids(new SetSearchField<Integer>(
                    beanItem.getId()));

            BugChartComponent bugChartComponent = null;
            bugChartComponent = new BugChartComponent(chartSearchCriteria, 400,
                    200);
            rightColumn.addComponent(bugChartComponent);
            rightColumn.setWidth("410px");
        }

        @Override
        public void displayBugReports() {
            this.displayAdvancedView();
        }

        @Override
        public void displayBugListWidget(final String title,
                final BugSearchCriteria criteria) {
            this.bottomLayout.removeAllComponents();
            final BugListWidget bugListWidget = new BugListWidget(title
                    + " Bug List", "Back to version dashboard", criteria, this);
            bugListWidget.setWidth("100%");
            this.bottomLayout.addComponent(bugListWidget);

        }
    }

    @Override
    public Version getItem() {
        return beanItem;
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(
            ApplicationEventListener<? extends ApplicationEvent> listener) {

    }

}
