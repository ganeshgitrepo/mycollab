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

package com.esofthead.mycollab.module.file.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.configuration.FileStorageConfiguration;
import com.vaadin.server.DownloadStream;
import com.vaadin.server.FileResource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class StreamDownloadResource extends FileResource {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(StreamDownloadResource.class);

	public StreamDownloadResource(String documentPath) {
		super(
				new File(FileStorageConfiguration.baseContentFolder,
						documentPath));
	}

	@Override
	public DownloadStream getStream() {
		try {
			String fileName = getFilename();
			fileName = fileName.replaceAll(" ", "_").replaceAll("-", "_");
			final DownloadStream ds = new DownloadStream(new FileInputStream(
					getSourceFile()), getMIMEType(), fileName);
			ds.setParameter("Content-Disposition", "attachment; filename="
					+ fileName);
			ds.setCacheTime(0);
			return ds;
		} catch (final FileNotFoundException e) {
			log.error("Error to create download stream", e);
			return null;
		}
	}

}
