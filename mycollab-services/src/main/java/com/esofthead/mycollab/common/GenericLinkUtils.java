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
package com.esofthead.mycollab.common;

import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author MyCollab Ltd.
 *
 */
public abstract class GenericLinkUtils {
	public static String URL_PREFIX_PARAM = "#";

	/**
	 * 
	 * @param params
	 * @return
	 */
	public static String encodeParam(Object... params) {
		StringBuffer paramStr = new StringBuffer("");
		for (int i = 0; i < params.length; i++) {
			paramStr.append(params[i].toString());
			if (i != params.length - 1) {
				paramStr.append("/");
			}
		}
		return UrlEncodeDecoder.encode(paramStr.toString());
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public String generateUserPreviewFullLink(String username) {
		return getSiteUrl() + GenericLinkUtils.URL_PREFIX_PARAM
				+ "account/user/preview/" + UrlEncodeDecoder.encode(username);
	}

	/**
	 * 
	 * @param sAccountId
	 * @return
	 */
	public static String generateSiteUrlByAccountId(Integer sAccountId) {
		String siteUrl = "";
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			BillingAccountService billingAccountService = ApplicationContextUtil
					.getSpringBean(BillingAccountService.class);
			BillingAccount account = billingAccountService
					.getAccountById(sAccountId);
			if (account != null) {
				siteUrl = String.format(ApplicationProperties
						.getString(ApplicationProperties.APP_URL), account
						.getSubdomain());
			}
		} else {
			siteUrl = ApplicationProperties
					.getString(ApplicationProperties.APP_URL);
		}
		return siteUrl;
	}

	abstract public String getSiteUrl();
}
