/*
 * Copyright 2002-2004 Greg Hinkle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mc4j.ems.connection.support.classloader.deepjar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.mc4j.ems.connection.support.classloader.NestedJarClassLoader;

/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), May 9, 2005
 * @version $Revision: 629 $($Author: ianpspringer $ / $Date: 2011-10-28 23:44:26 +0200 (Fr, 28 Okt 2011) $)
 */
public class Handler extends URLStreamHandler {

    private static Log log = LogFactory.getLog(Handler.class);

    static {
        log.debug("Deepjar handler initialized");
    }

    ClassLoader parent;

    public Handler(ClassLoader parent) {
        this.parent = parent;
    }

    public Handler() {
        super();
    }

	public static String PROTOCOL = "deepjar";

	protected static int PROTOCOL_LENGTH = PROTOCOL.length()+3;


	/**
	 * @see java.net.URLStreamHandler#openConnection(java.net.URL)
	 */
	protected URLConnection openConnection(final URL u) throws IOException {
        log.debug("Deepjar handler is opening " + u);

		final String resource = u.toString().substring(PROTOCOL_LENGTH);
		return new URLConnection(u) {

			public void connect() {
			}
			public InputStream getInputStream() {
                ClassLoader parent = NestedJarClassLoader.getRunningLoader(u);
                return parent.getResourceAsStream(resource);
			}
		};
	}

}

