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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.TaskListMapper;
import com.esofthead.mycollab.module.project.dao.TaskListMapperExt;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, type = ProjectContants.TASK_LIST, nameField = "name", extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.TASK_LIST)
public class ProjectTaskListServiceImpl extends
		DefaultService<Integer, TaskList, TaskListSearchCriteria> implements
		ProjectTaskListService {

	@Autowired
	protected TaskListMapper projectTaskListMapper;
	@Autowired
	protected TaskListMapperExt projectTaskListMapperExt;

	@Override
	public ICrudGenericDAO<Integer, TaskList> getCrudMapper() {
		return projectTaskListMapper;
	}

	@Override
	public ISearchableDAO<TaskListSearchCriteria> getSearchMapper() {
		return projectTaskListMapperExt;
	}

	@Override
	public SimpleTaskList findById(int taskListId, int sAccountId) {
		return projectTaskListMapperExt.findTaskListById(taskListId);
	}

	@Override
	public void updateTaskListIndex(TaskList[] taskLists, int sAccountId) {
		for (TaskList taskList : taskLists) {
			projectTaskListMapper.updateByPrimaryKeySelective(taskList);
		}
	}
}
