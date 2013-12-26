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

package org.mc4j.ems.connection.support.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>This classloader is able to load classes from the contents of jar files available
 * in the parent classloader. This mechanism creates support for nested jar file
 * deployment.</p>
 * <p/>
 * <p>To use this classloader, create a jar with other jars in it and put the main jar
 * in the parent classloader passed to the constructor of this classloader. In the
 * urls parameter, supply jars with the protocol "deepjar" and their path within the
 * parent classloader.</p>
 * <p/>
 * <p>Note: This class and the deepjar handler must currently reside in the application
 * classpath as the URL class will not be able to find the handler otherwise.</p>
 *
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), May 9, 2005
 * @version $Revision: 629 $($Author: ianpspringer $ / $Date: 2011-10-28 23:44:26 +0200 (Fr, 28 Okt 2011) $)
 */
public class NestedJarClassLoader extends URLClassLoader {

    public final static String JAVA_PROTOCOL_HANDLER = "java.protocol.handler.pkgs";

    public static Log log = LogFactory.getLog(NestedJarClassLoader.class);

    /**
     * TODO this is a hack because the JBoss NamingContext feels compelled to send
     * along the context classloader's URLs. Not having the deepjar handler on the
     * other side of course makes the server RMI side unhappy when it goes to
     * deserialize and reconstitute a deepjar URL. Stupid URL handler system.
     * Stupid JBoss. Boy, what a bitter moment.
     */
    public URL[] getURLs() {
        URL[] all = super.getURLs();
        List<URL> safe = new ArrayList<URL>();
        for (URL url : all) {
            if (url.getProtocol().indexOf("deepjar") < 0)
                safe.add(url);
        }
        return safe.toArray(new URL[safe.size()]);
    }

    static {

        String handlerPackage = "org.mc4j.ems.connection.support.classloader";
        String value = System.getProperty(JAVA_PROTOCOL_HANDLER);
        if (value == null) {
            value = handlerPackage;
        } else {
            value += "|" + handlerPackage;
        }
        System.setProperty("java.protocol.handler.pkgs", value);
        log.debug("Updated URL Handler Packages to: " + System.getProperty("java.protocol.handler.pkgs"));
    }

    // TODO synchronize me
    static Map<URL, ClassLoader> loadMap = new HashMap<URL, ClassLoader>();

    public static NestedJarClassLoader newInstance(URL[] urls, final ClassLoader parent) {
        return new NestedJarClassLoader(urls, parent);
    }

/*

    protected void addParentClassloaderArchive(String resource) throws IOException {
        File file = File.createTempFile("lib-",".jar");
        InputStream is = getParent().getResourceAsStream(resource);
        FileOutputStream fos = new FileOutputStream(file);


        super.addURL(url);
    }
*/


    protected Class<?> findClass(String string) throws ClassNotFoundException {
        if (string.indexOf("MBeanServer") >= 0) {
            log.debug("MBeanServer Class being loaded in ClassLoader " + this);
        }

        return super.findClass(string);
    }


    public NestedJarClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        for (URL url : urls) {
            if ("deepjar".equals(url.getProtocol()))
                loadMap.put(url, parent);
        }

    }

    public static ClassLoader getRunningLoader(URL url) {
        return loadMap.get(url);
    }


    public static void main(String[] args) throws Exception {
        //test
        long start = System.currentTimeMillis();
        NestedJarClassLoader cl = NestedJarClassLoader.newInstance(new URL[]{
                new URL("deepjar://org-mc4j-ems-impl.jar")},
                NestedJarClassLoader.class.getClassLoader());

        Class testClass = Class.forName("org.mc4j.ems.impl.jmx.connection.DConnection", false, cl);
        log.info(testClass);

        log.info("Time: " + (System.currentTimeMillis() - start) + "ns");


        start = System.currentTimeMillis();
        testClass = Class.forName("org.mc4j.ems.impl.jmx.connection.PooledConnectionTracker", false, cl);
        log.info(testClass);
        log.info("Time: " + (System.currentTimeMillis() - start) + "ns");

    }
}
 