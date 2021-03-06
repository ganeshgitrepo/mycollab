/**
 * This file is part of mycollab-scheduler.
 *
 * mycollab-scheduler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-scheduler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-scheduler.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.project.ProjectMemberInviteNotificationAction;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Service
public class ProjectMemberInviteNotificationActionImpl implements
		ProjectMemberInviteNotificationAction {

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private ProjectMemberMapper projectMemberMapper;

	@Autowired
	private ProjectService projectService;

	@Autowired
	protected ExtMailService extMailService;

	@Autowired
	private UserService userService;

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		int memberId = notification.getTypeid();
		SimpleProjectMember member = projectMemberService.findById(memberId,
				notification.getSaccountid());
		if (member != null) {
			String subdomain = projectService.getSubdomainOfProject(member
					.getProjectid());

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"$inviteUser has invited you to join the team for project \" $member.projectName\"",
					"templates/email/project/memberInvitation/memberInvitationNotifier.mt");
			templateGenerator.putVariable("member", member);
			templateGenerator.putVariable("inviteUser",
					notification.getChangeby());

			String userChange = notification.getChangeby();
			User user = userService.findUserByUserName(userChange);
			templateGenerator.putVariable(
					"urlAccept",
					SiteConfiguration.getSiteUrl(subdomain)
							+ "project/member/invitation/confirm_invite/"
							+ UrlEncodeDecoder.encode(member.getSaccountid()
									+ "/" + member.getId() + "/"
									+ user.getEmail() + "/"
									+ user.getUsername()));
			templateGenerator.putVariable(
					"urlDeny",
					SiteConfiguration.getSiteUrl(subdomain)
							+ "project/member/invitation/deny_invite/"
							+ UrlEncodeDecoder.encode(member.getSaccountid()
									+ "/" + member.getId() + "/"
									+ user.getEmail() + "/"
									+ user.getUsername()));

			templateGenerator.putVariable("userName",
					member.getMemberFullName());
			extMailService.sendHTMLMail("noreply@mycollab.com",
					"noreply@mycollab.com", Arrays
							.asList(new MailRecipientField(member.getEmail(),
									member.getMemberFullName())), null, null,
					templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);

			// Send email and change register status of user to
			// RegisterStatusConstants.SENT_VERIFICATION_EMAIL
			member.setStatus(RegisterStatusConstants.SENT_VERIFICATION_EMAIL);
			projectMemberService.updateWithSession(member,
					notification.getChangeByUserFullName());
		}
	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		// do nothing
	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		// do nothing
	}

}
