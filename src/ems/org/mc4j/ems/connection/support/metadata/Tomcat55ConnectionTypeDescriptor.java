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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), Sep 30, 2004
 * @version $Revision: 570 $($Author: ghinkl $ / $Date: 2006-04-12 21:14:16 +0200 (Mi, 12 Apr 2006) $)
 */
public class Tomcat55ConnectionTypeDescriptor extends J2SE5ConnectionTypeDescriptor {

    public boolean isMEJBCompliant() {
        return true;
    }

    public String getDisplayName() {
        return "Tomcat 5.5+";
    }

    public String[] getConnectionClasspathEntries() {
        return new String[] {
            "catalina.jar",
            "catalina-cluster.jar",
            "catalina-optional.jar"
        };
    }


    public String getRecongnitionPath() {
        return "server/lib/catalina.jar";
    }

    public String getConnectionType() {
        return "Tomcat55";
    }

    public String getServerVersion(File recognitionFile) {
        try {
            String version;

            URLClassLoader ld =
                new URLClassLoader(new URL[] { recognitionFile.toURL() });

            Class serverInfoClass =
                Class.forName("org.apache.catalina.util.ServerInfo",true,ld);

            Method m = serverInfoClass.getMethod("getServerInfo",new Class[] {});

            version = (String) m.invoke(null,(Object[]) null);

            return version;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }




}
