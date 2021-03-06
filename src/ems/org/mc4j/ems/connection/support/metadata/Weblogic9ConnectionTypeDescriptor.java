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

import java.net.MalformedURLException;
import java.util.Properties;

/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), Sep 30, 2004
 * @version $Revision: 570 $($Author: ghinkl $ / $Date: 2006-04-12 21:14:16 +0200 (Mi, 12 Apr 2006) $)
 */
public class Weblogic9ConnectionTypeDescriptor extends JSR160ConnectionTypeDescriptor {

    public static void main(String[] args) throws MalformedURLException {
//        String protocol = "t3";
//        String jndiroot = "/jndi/";
//        String mserver = "weblogic.management.mbeanservers.domainruntime";
//        JMXServiceURL serviceURL = new JMXServiceURL(protocol, "localhost", 7001,
//            jndiroot + mserver);
//        System.out.println("URL: " + serviceURL);
    }

    public boolean isMEJBCompliant() {
        return false;
    }

    public boolean isUseManagementHome() {
        return false;
    }

    public String getDisplayName() {
        return "WebLogic 9";
    }

    public String getConnectionMessage() {
        return "Has only been tested against Diablo Beta. No JSR 77 MBeans are available with this view. " +
            "Select the WebLogic 9 JSR 77 connection type to view those.";
    }

    public String getDefaultServerUrl() {
        return "service:jmx:t3://localhost:7001/jndi/weblogic.management.mbeanservers.domainruntime";
    }

    public String getConnectionType() {
        return "weblogic";
    }

    public Properties getDefaultAdvancedProperties() {
        Properties props = super.getDefaultAdvancedProperties();

        props.put("jmx.remote.protocol.provider.pkgs", "weblogic.management.remote");
        return props;
    }

    public String getRecongnitionPath() {
       return "server/lib/weblogic.jar";
    }

    public String getDefaultInitialContext() {
        return "weblogic.jndi.WLInitialContextFactory";
    }

    public String[] getConnectionClasspathEntries() {
        return new String[] {
            "server/lib/weblogic.jar", // their doco is wrong... says the provider pkg is in wlclient.jar... it isn't
            "server/lib/wlclient.jar"
        };
    }
}
