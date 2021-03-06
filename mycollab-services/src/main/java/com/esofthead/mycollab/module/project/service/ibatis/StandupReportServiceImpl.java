/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.StandupReportMapper;
import com.esofthead.mycollab.module.project.dao.StandupReportMapperExt;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.service.StandupReportService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "forday", type = ProjectContants.STANDUP, extraFieldName = "projectid")
public class StandupReportServiceImpl
		extends
		DefaultService<Integer, StandupReportWithBLOBs, StandupReportSearchCriteria>
		implements StandupReportService {
	@Autowired
	private StandupReportMapper standupReportMapper;
	@Autowired
	private StandupReportMapperExt standupReportMapperExt;

	@Override
	public SimpleStandupReport findStandupReportById(int standupId,
			Integer sAccountId) {
		return standupReportMapperExt.findReportById(standupId);
	}

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return standupReportMapper;
	}

	@Override
	public ISearchableDAO<StandupReportSearchCriteria> getSearchMapper() {
		return standupReportMapperExt;
	}

	@Override
	public SimpleStandupReport findStandupReportByDateUser(int projectId,
			String username, Date onDate, Integer sAccountId) {
		StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
		criteria.setProjectId(new NumberSearchField(projectId));
		criteria.setLogBy(new StringSearchField(SearchField.AND, username));
		criteria.setOnDate(new DateSearchField(SearchField.AND, onDate));
		List reports = standupReportMapperExt.findPagableListByCriteria(
				criteria, new RowBounds(0, Integer.MAX_VALUE));
		
		if (reports != null && reports.size() > 0) {
			return (SimpleStandupReport) reports.get(0);
		}

		return null;
	}

	@Override
	public List<GroupItem> getReportsCount(StandupReportSearchCriteria criteria) {
		return standupReportMapperExt.getReportsCount(criteria);
	}

}
