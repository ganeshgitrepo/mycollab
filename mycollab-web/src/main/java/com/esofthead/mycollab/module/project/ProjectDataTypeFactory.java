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
package com.esofthead.mycollab.module.project;

import com.esofthead.mycollab.module.project.view.bug.BugPriorityStatusConstants;
import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProjectDataTypeFactory {

	public static final String PROJECT_STATUS_OPEN = "Open";
	public static final String PROJECT_STATUS_CLOSED = "Closed";

	private static final String[] PROJECT_STATUSES_LIST = new String[] {
			"Open", "Closed" };

	private static String[] PROJECT_TYPE_LIST = new String[] { "Unknown",
			"Administrative", "Operative" };

	private static String[] BUG_PRIORITY_LIST = new String[] {
			BugPriorityStatusConstants.PRIORITY_BLOCKER,
			BugPriorityStatusConstants.PRIORITY_CRITICAL,
			BugPriorityStatusConstants.PRIORITY_MAJOR,
			BugPriorityStatusConstants.PRIORITY_MINOR,
			BugPriorityStatusConstants.PRIORITY_TRIVIAL };

	private static String[] BUG_SEVERITY_LIST = new String[] { "Critical",
			"Major", "Minor", "Trivial" };

	private static String[] BUG_STATUS_LIST = new String[] {
			BugStatusConstants.VERIFIED, BugStatusConstants.INPROGRESS,
			BugStatusConstants.OPEN, BugStatusConstants.RESOLVED };

	private static String[] BUG_RESOLUTION_LIST = new String[] {
			BugResolutionConstants.CAN_NOT_REPRODUCE,
			BugResolutionConstants.DUPLICATE, BugResolutionConstants.REOPEN,
			BugResolutionConstants.FIXED, BugResolutionConstants.INCOMPLETE,
			BugResolutionConstants.NEWISSUE,
			BugResolutionConstants.WAITFORVERIFICATION,
			BugResolutionConstants.WON_FIX };

	public static String[] getProjectTypeList() {
		return PROJECT_TYPE_LIST;
	}

	public static String[] getBugPriorityList() {
		return BUG_PRIORITY_LIST;
	}

	public static String[] getBugStatusList() {
		return BUG_STATUS_LIST;
	}

	public static String[] getProjectStatusList() {
		return PROJECT_STATUSES_LIST;
	}

	public static String[] getBugSeverityList() {
		return BUG_SEVERITY_LIST;
	}

	public static String[] getBugResolutionList() {
		return BUG_RESOLUTION_LIST;
	}
}
