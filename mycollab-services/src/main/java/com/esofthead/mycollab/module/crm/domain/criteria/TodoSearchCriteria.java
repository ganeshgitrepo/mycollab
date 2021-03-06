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
package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.BitSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class TodoSearchCriteria extends SearchCriteria {

	private StringSearchField subject;
	private StringSearchField contact;
	private NumberSearchField contactId;
	private NumberSearchField accountId;
	private NumberSearchField campaignId;
	private NumberSearchField targetId;
	private NumberSearchField leadId;
	private NumberSearchField opportunityId;
	private NumberSearchField quoteId;
	private NumberSearchField productId;
	private NumberSearchField caseId;
	private StringSearchField assignUser;
	private StringSearchField type;
	private StringSearchField status;
	private NumberSearchField id;

	private BitSearchField isClosed;

	public StringSearchField getSubject() {
		return subject;
	}

	public void setSubject(StringSearchField subject) {
		this.subject = subject;
	}

	public StringSearchField getContact() {
		return contact;
	}

	public void setContact(StringSearchField contact) {
		this.contact = contact;
	}

	public NumberSearchField getContactId() {
		return contactId;
	}

	public void setContactId(NumberSearchField contactId) {
		this.contactId = contactId;
	}

	public NumberSearchField getAccountId() {
		return accountId;
	}

	public void setAccountId(NumberSearchField accountId) {
		this.accountId = accountId;
	}

	public NumberSearchField getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(NumberSearchField campaignId) {
		this.campaignId = campaignId;
	}

	public NumberSearchField getTargetId() {
		return targetId;
	}

	public void setTargetId(NumberSearchField targetId) {
		this.targetId = targetId;
	}

	public NumberSearchField getLeadId() {
		return leadId;
	}

	public void setLeadId(NumberSearchField leadId) {
		this.leadId = leadId;
	}

	public NumberSearchField getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(NumberSearchField opportunityId) {
		this.opportunityId = opportunityId;
	}

	public NumberSearchField getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(NumberSearchField quoteId) {
		this.quoteId = quoteId;
	}

	public NumberSearchField getProductId() {
		return productId;
	}

	public void setProductId(NumberSearchField productId) {
		this.productId = productId;
	}

	public NumberSearchField getCaseId() {
		return caseId;
	}

	public void setCaseId(NumberSearchField caseId) {
		this.caseId = caseId;
	}

	public StringSearchField getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(StringSearchField assignUser) {
		this.assignUser = assignUser;
	}

	public StringSearchField getType() {
		return type;
	}

	public void setType(StringSearchField type) {
		this.type = type;
	}

	public StringSearchField getStatus() {
		return status;
	}

	public void setStatus(StringSearchField status) {
		this.status = status;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return id;
	}

	public BitSearchField getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(BitSearchField isClosed) {
		this.isClosed = isClosed;
	}

}
