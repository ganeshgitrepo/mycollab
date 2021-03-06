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

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ComponentScreenData;
import com.esofthead.mycollab.module.project.view.parameters.VersionScreenData;
import com.esofthead.mycollab.shell.BillingPlanCheckerContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.IPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class TrackerPresenter extends AbstractPresenter<TrackerContainer> {

	private static final long serialVersionUID = 1L;

	public TrackerPresenter() {
		super(TrackerContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		projectViewContainer.gotoSubView("Bugs");

		IPresenter<?> presenter = null;

		if (BillingPlanCheckerContext.isBugComponentEnable()) {
			if (ClassUtils.instanceOf(data, BugScreenData.Search.class,
					BugScreenData.Add.class, BugScreenData.Edit.class,
					BugScreenData.Read.class)) {
				presenter = PresenterResolver.getPresenter(BugPresenter.class);
			} else if (ClassUtils.instanceOf(data,
					ComponentScreenData.Add.class,
					ComponentScreenData.Edit.class,
					ComponentScreenData.Search.class,
					ComponentScreenData.Read.class)) {
				presenter = PresenterResolver
						.getPresenter(ComponentPresenter.class);
			} else if (ClassUtils.instanceOf(data, VersionScreenData.Add.class,
					VersionScreenData.Edit.class,
					VersionScreenData.Search.class,
					VersionScreenData.Read.class)) {
				presenter = PresenterResolver
						.getPresenter(VersionPresenter.class);
			} else if (data == null
					|| data instanceof BugScreenData.GotoDashboard) {
				presenter = PresenterResolver
						.getPresenter(BugDashboardPresenter.class);
			} else {
				throw new MyCollabException("Do not support screen data "
						+ data);
			}
		} else {
			presenter = PresenterResolver
					.getPresenter(BugAdvertisementPresenter.class);
		}

		presenter.go(view, data);
	}

	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {

		ScreenData<?> pageAction = pageActionChain.pop();
		onGo(container, pageAction);
	}

}
