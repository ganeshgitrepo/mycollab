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
package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.crm.data.CustomViewScreenData;
import com.esofthead.mycollab.module.crm.data.NotificationSettingScreenData;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.VerticalTabsheet;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

@ViewComponent
public class CrmSettingContainer extends CssLayout implements PageView {
	private static final long serialVersionUID = 1L;

	private final VerticalTabsheet settingTab;

	private ICrmCustomViewPresenter customViewPresenter;
	private CrmNotifcationSettingPresenter notificationPresenter;

	public CrmSettingContainer() {
		this.setWidth("100%");

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setStyleName("verticalTabView");
		contentWrapper.setWidth("100%");
		this.addComponent(contentWrapper);

		settingTab = new VerticalTabsheet();
		settingTab.setSizeFull();
		settingTab.setNavigatorWidth("170px");
		settingTab.setNavigatorStyleName("sidebar-menu");
		settingTab.setContainerStyleName("tab-content");
		settingTab.setHeight(null);

		buildComponents();
		contentWrapper.addComponent(settingTab);
	}

	private void buildComponents() {
		settingTab.addTab(constructNotificationSettingView(), "Notifications",
				MyCollabResource.newResource("icons/22/crm/notification.png"));

		settingTab.addTab(constructCustomLayoutView(), "Custom Layouts",
				MyCollabResource.newResource("icons/22/crm/layout.png"));

		settingTab
				.addSelectedTabChangeListener(new SelectedTabChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void selectedTabChange(SelectedTabChangeEvent event) {
						Tab tab = ((VerticalTabsheet) event.getSource())
								.getSelectedTab();
						String caption = tab.getCaption();

						if ("Notifications".equals(caption)) {
							notificationPresenter.go(CrmSettingContainer.this,
									new NotificationSettingScreenData.Read());
						} else if ("Custom Layouts".equals(caption)) {
							customViewPresenter.go(CrmSettingContainer.this,
									new CustomViewScreenData.Read());
						}

					}
				});
	}

	private Component constructNotificationSettingView() {
		notificationPresenter = PresenterResolver
				.getPresenter(CrmNotifcationSettingPresenter.class);
		return notificationPresenter.initView();
	}

	private Component constructCustomLayoutView() {
		customViewPresenter = PresenterResolver
				.getPresenter(ICrmCustomViewPresenter.class);
		return customViewPresenter.initView();
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {

	}

	public Component gotoSubView(String name) {
		PageView component = (PageView) settingTab.selectTab(name);
		return component;
	}

}
