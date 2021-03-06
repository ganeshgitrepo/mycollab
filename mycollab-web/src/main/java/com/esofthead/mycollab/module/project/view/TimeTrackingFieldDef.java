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
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class TimeTrackingFieldDef {
	public static TableViewField summary = new TableViewField("Summary",
			"summary", UIConstants.TABLE_EX_LABEL_WIDTH);

	public static TableViewField logUser = new TableViewField("User",
			"logUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField project = new TableViewField("Project",
			"projectName", UIConstants.TABLE_X_LABEL_WIDTH);

	public static TableViewField createdTime = new TableViewField(
			"Created Time", "createdtime", UIConstants.TABLE_DATE_TIME_WIDTH);

	public static TableViewField timeLogValue = new TableViewField("Hours",
			"logvalue", UIConstants.TABLE_S_LABEL_WIDTH);
}
