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
package com.esofthead.mycollab.module.project.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class ProjectServiceTest extends ServiceTest {

	@Autowired
	protected ProjectService projectService;

	@DataSet
	@Test
	public void testGetListProjects() {
		List projects = projectService
				.findPagableListByCriteria(new SearchRequest<ProjectSearchCriteria>(
						null, 0, Integer.MAX_VALUE));
		Assert.assertEquals(4, projects.size());
	}

	@DataSet
	@Test
	public void testGetListProjectsByCriteria() {
		ProjectSearchCriteria criteria = new ProjectSearchCriteria();
		criteria.setAccountName(new StringSearchField(StringSearchField.AND,
				"a"));
		criteria.setSaccountid(new NumberSearchField(1));

		List projects = projectService
				.findPagableListByCriteria(new SearchRequest<ProjectSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(1, projects.size());
	}

	@DataSet
	@Test
	public void testGetTotalCountProjectsByCriteria() {
		ProjectSearchCriteria criteria = new ProjectSearchCriteria();
		criteria.setOwnerName(new StringSearchField(StringSearchField.AND,
				"Nguyen"));
		criteria.setSaccountid(new NumberSearchField(1));

		Assert.assertEquals(2, projectService.getTotalCount(criteria));
	}

	@DataSet
	@Test
	public void testGetListProjectsByUsername() {
		ProjectSearchCriteria criteria = new ProjectSearchCriteria();
		criteria.setUsername(new StringSearchField(SearchField.AND, "admin"));
		criteria.setSaccountid(new NumberSearchField(1));

		List projects = projectService
				.findPagableListByCriteria(new SearchRequest<ProjectSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(2, projects.size());
	}
}
