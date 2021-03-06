/**
 * This file is part of mycollab-web-community.
 *
 * mycollab-web-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.community.module.project.view.bug;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.community.ui.chart.PieChartDescriptionBox;
import com.esofthead.mycollab.community.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.view.bug.IBugResolutionSummaryChartWidget;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.ComponentContainer;

@ViewComponent
public class BugResolutionSummaryChartWidget extends
		PieChartWrapper<BugSearchCriteria> implements
		IBugResolutionSummaryChartWidget {
	private static final long serialVersionUID = 1L;

	public BugResolutionSummaryChartWidget(int width, int height) {
		super(
				LocalizationHelper
						.getMessage(BugI18nEnum.CHART_RESOLUTION_TITLE), width,
				height);
	}

	public BugResolutionSummaryChartWidget() {
		super(
				LocalizationHelper
						.getMessage(BugI18nEnum.CHART_RESOLUTION_TITLE), 400,
				280);

	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {
	}

	@Override
	protected DefaultPieDataset createDataset() {
		// create the dataset...
		final DefaultPieDataset dataset = new DefaultPieDataset();

		BugService bugService = ApplicationContextUtil
				.getSpringBean(BugService.class);

		List<GroupItem> groupItems = bugService
				.getResolutionDefectsSummary(searchCriteria);

		String[] bugPriorities = ProjectDataTypeFactory.getBugResolutionList();
		for (String priority : bugPriorities) {
			boolean isFound = false;
			for (GroupItem item : groupItems) {
				if (priority.equals(item.getGroupid())) {
					dataset.setValue(priority, item.getValue());
					isFound = true;
					break;
				}
			}

			if (!isFound) {
				dataset.setValue(priority, 0);
			}
		}

		return dataset;
	}

	@Override
	protected void onClickedDescription(String key) {
		BugSearchCriteria searchCriteria = new BugSearchCriteria();
		searchCriteria.setResolutions(new SetSearchField<String>(
				SearchField.AND, new String[] { key }));
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		BugSearchParameter param = new BugSearchParameter(key + " Bug List",
				searchCriteria);
		EventBus.getInstance().fireEvent(
				new BugEvent.GotoList(this, new BugScreenData.Search(param)));
	}

	@Override
	protected ComponentContainer createLegendBox() {
		return PieChartDescriptionBox.createLegendBox(this, pieDataSet);
	}

}
