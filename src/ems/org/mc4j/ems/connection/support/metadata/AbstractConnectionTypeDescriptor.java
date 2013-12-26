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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), Sep 30, 2004
 * @version $Revision: 629 $($Author: ianpspringer $ / $Date: 2011-10-28 23:44:26 +0200 (Fr, 28 Okt 2011) $)
 */
public abstract class AbstractConnectionTypeDescriptor implements ConnectionTypeDescriptor {

    private static Log log = LogFactory.getLog(AbstractConnectionTypeDescriptor.class);
    public String toString() {
        return getDisplayName();
    }

    public boolean isUseManagementHome() {
        return false;
    }

    public String getServerVersion(File recognitionFile) {
        try {
            String version;
            JarFile recJarFile = new JarFile(recognitionFile);

            version = recJarFile.getManifest().getMainAttributes().getValue("Implementation-Version");

            if (version == null) {
                Map attrMap = recJarFile.getManifest().getEntries();
                for (Iterator iterator = attrMap.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String name = (String) entry.getKey();
                    Attributes attr  = (Attributes) entry.getValue();
                    version = attr.getValue("Implementation-Version");
                }
            }
            return version;
        } catch (MalformedURLException e) {
            log.warn("Could not determine server version from matched file " + recognitionFile.getAbsolutePath(),e);
        } catch (IOException e) {
            log.warn("Could not determine server version from matched file " + recognitionFile.getAbsolutePath(),e);
        }
        return null;
    }

    public String getExtrasLibrary() {
        return null;
    }

    public Properties getDefaultAdvancedProperties() {
        return new Properties();
    }

    public boolean isUseChildFirstClassLoader() {
        return false;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractConnectionTypeDescriptor)) return false;

        final AbstractConnectionTypeDescriptor other = (AbstractConnectionTypeDescriptor) o;

        if (getDisplayName() != null ? !getDisplayName().equals(other.getDisplayName()) : other.getDisplayName() != null) return false;

        return true;
    }

    public int hashCode() {
        return (getDisplayName() != null ? getDisplayName().hashCode() : 0);
    }

}