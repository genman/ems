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

package org.mc4j.ems.connection.support.metadata;


/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), Sep 30, 2004
 * @version $Revision: 570 $($Author: ghinkl $ / $Date: 2006-04-12 21:14:16 +0200 (Mi, 12 Apr 2006) $)
 */
public class WebsphereStudioConnectionTypeDescriptor extends WebsphereConnectionTypeDescriptor {



    public String getConnectionMessage() {
        return "You must use the IBM JVM for MC4J when connection to WebSphere Test Server 5.x. The Sun JVM " +
            "can only be used for WS 6.";
    }

    public String[] getConnectionClasspathEntries() {
        return new String[] {
            "runtimes/base_v51/lib/*",
            "runtimes/base_v51/java/jre/lib/ext/ibmjsse.jar",
            "runtimes/base_v51/java/jre/lib/ext/mail.jar",
            "runtimes/base_v51/deploytool/itp/plugins/org.apache.xerces_4.0.13/xercesImpl.jar",
            "runtimes/base_v51/deploytool/itp/plugins/org.apache.xerces_4.0.13/xmlParserAPIs.jar",
        };
    }

    public String getDisplayName() {
        return "WSAD Test Environment 5.x+";
    }

    public String getRecongnitionPath() {
        return "runtimes/base_v51/lib/runtime.jar";
    }
}
