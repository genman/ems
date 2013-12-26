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
public class JDMKConnectionTypeDescriptor extends AbstractConnectionTypeDescriptor {

    public boolean isMEJBCompliant() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getDisplayName() {
        return "JDMK";
    }

    public String getRecongnitionPath() {
        return null;
    }

    public String getDefaultServerUrl() {
        return "localhost:8086";
    }

    public String getDefaultJndiName() {
        return "name=RmiConnectorServer";
    }

    public String getDefaultInitialContext() {
        return null;
    }

    public String getDefaultPrincipal() {
        return null;
    }

    public String getDefaultCredentials() {
        return null;
    }

    public String getConnectionType() {
        return "JDMK";
    }

    public String getConnectionMessage() {
        return null;
    }

    public String[] getConnectionClasspathEntries() {
        return null;
    }

    public String getConnectionNodeClassName() {
        return null;
    }

    public String getExtrasLibrary() {
        return "JDMK";
    }
}
