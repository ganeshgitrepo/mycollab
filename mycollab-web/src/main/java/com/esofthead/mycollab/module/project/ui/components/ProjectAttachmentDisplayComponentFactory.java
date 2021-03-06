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
package com.esofthead.mycollab.module.project.ui.components;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ProjectAttachmentDisplayComponentFactory {
	public static Component getAttachmentDisplayComponent(int projectid,
			AttachmentType type, int typeid) {
		ResourceService resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);
		List<Content> attachments = resourceService.getContents(AttachmentUtils
				.getProjectEntityAttachmentPath(AppContext.getAccountId(),
						projectid, type, typeid));
		if (attachments != null && !attachments.isEmpty()) {
			return new AttachmentDisplayComponent(attachments);
		} else {
			return new VerticalLayout();
		}
	}
}
