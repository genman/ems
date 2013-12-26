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
 * @version $Revision: 572 $($Author: ghinkl $ / $Date: 2006-04-17 05:07:26 +0200 (Mo, 17 Apr 2006) $)
 */
public class JSR160ConnectionTypeDescriptor extends AbstractConnectionTypeDescriptor  {

    public boolean isMEJBCompliant() {
        return true;
    }

    public String getDisplayName() {
        return "JSR160";
    }

    public String getRecongnitionPath() {
        return null;
    }

    public String getDefaultServerUrl() {
        return "service:jmx:rmi:///jndi/rmi://localhost:9999/server";
    }

    public String getDefaultPrincipal() {
        return "";
    }

    public String getDefaultCredentials() {
        return "";
    }

    public String getDefaultInitialContext() {
        return "com.sun.jndi.rmi.registry.RegistryContextFactory";
    }

    public String getDefaultJndiName() {
        return null;
    }

    public String getConnectionType() {
        return "JSR160";
    }

    public String getConnectionMessage() {
        return null;
    }

    public String[] getConnectionClasspathEntries() {
        return null;
    }

    public String getConnectionNodeClassName() {
        return "org.mc4j.ems.impl.jmx.connection.support.providers.JMXRemotingConnectionProvider";
    }

    public String getExtrasLibrary() {
        return "JSR160";
    }
}
