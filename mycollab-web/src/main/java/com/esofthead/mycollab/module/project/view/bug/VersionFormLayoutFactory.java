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

import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@SuppressWarnings("serial")
public class VersionFormLayoutFactory implements IFormLayoutFactory {
	private GridFormLayoutHelper informationLayout;

	@Override
	public Layout getLayout() {
		this.informationLayout = new GridFormLayoutHelper(2, 3, "100%",
				"167px", Alignment.MIDDLE_LEFT);
		this.informationLayout.getLayout().setWidth("100%");
		this.informationLayout.getLayout().setMargin(false);
		this.informationLayout.getLayout().addStyleName("colored-gridlayout");
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(this.informationLayout.getLayout());
		return layout;
	}

	@Override
	public boolean attachField(final Object propertyId, final Field<?> field) {
		if (propertyId.equals("versionname")) {
			this.informationLayout.addComponent(field, "Version Name", 0, 0, 2,
					"100%");
		} else if (propertyId.equals("description")) {
			this.informationLayout.addComponent(field, "Description", 0, 1, 2,
					"100%");
		} else if (propertyId.equals("duedate")) {
			this.informationLayout.addComponent(field, "Due Date", 0, 2);
		} else {
			return false;
		}

		return true;
	}
}
