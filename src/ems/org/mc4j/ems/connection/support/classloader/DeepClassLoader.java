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

import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Map;

import org.mc4j.ems.connection.support.classloader.deepjar.Handler;

/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), May 17, 2005
 * @version $Revision: 629 $($Author: ianpspringer $ / $Date: 2011-10-28 23:44:26 +0200 (Fr, 28 Okt 2011) $)
 */
public class DeepClassLoader extends ClassLoader {


    private URL[] searchPaths;
    
    private Map<String, ByteCode> codeMap;
    private Map<String, ProtectionDomain> protectionDomainMap;

    public final static String JAVA_PROTOCOL_HANDLER = "java.protocol.handler.pkgs";

    static {
        // Register a protocol handler so that deepjar:// references will look in jars in
        // the classloader
        String handlerPackage = "org.mc4j.ems.connection.support.classloader.deepjar";
        String existingHandlers = System.getProperty(JAVA_PROTOCOL_HANDLER);
        String newHandlers = null;
        if (existingHandlers != null && existingHandlers.length() > 0)
            newHandlers = handlerPackage + "|" + existingHandlers;
        else
            newHandlers = handlerPackage;

        System.setProperty(JAVA_PROTOCOL_HANDLER, newHandlers);
    }

    public DeepClassLoader(ClassLoader parent, URL[] urls) {
        super(parent);
    }


    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class cls = findLoadedClass(name);
        if (cls != null)
            return cls;

        // Look up the class in the byte codes.
        String cache = name.replace('/', '.') + ".class";
        ByteCode bytecode = (ByteCode) codeMap.get(cache);

        if (bytecode != null) {
            // Use a protectionDomain to associate the codebase with the
            // class.

            // Associate the codebase with the class with a protection domain
            ProtectionDomain pd = (ProtectionDomain) protectionDomainMap.get(bytecode.codebase);
            if (pd == null) {
                ProtectionDomain cd = this.getClass().getProtectionDomain();
                URL url = cd.getCodeSource().getLocation();
                try {
                    url = new URL("jar:" + url + "!/" + bytecode.codebase);
                } catch (MalformedURLException mux) {
                    mux.printStackTrace(System.out);
                }

                CodeSource source = new CodeSource(url, (CodeSigner[]) null);
                pd = new ProtectionDomain(source, null, this, null);
                protectionDomainMap.put(bytecode.codebase, pd);
            }
            return defineClass(name, bytecode.bytes, 0, bytecode.length, pd);
        }
        throw new ClassNotFoundException(name);
    }

    protected URL findResource(String resourceName) {
        try {

            // Do we have the named resource in our cache?  If so, construct a
            // 'onejar:' URL so that a later attempt to access the resource
            // will be redirected to our Handler class, and thence to this class.
            String resource = resolve(resourceName);
            if (resource != null) {
                // We know how to handle it.
                return new URL(Handler.PROTOCOL + ":" + resource);
            }
            // If all else fails, return null.
            return null;
        } catch (MalformedURLException mux) {
            System.out.println("Unable to find resource " + resourceName);
        }
        return null;
    }




    protected String resolve(String resourceName) {

        if (resourceName.startsWith("/")) resourceName = resourceName.substring(1);
        resourceName = resourceName.replace('/', '.');
        String resource = null;

        if (resource == null) {
            // One last try.
            if (codeMap.get(resourceName) == null) {
                resource = null;
            } else {
                resource = resourceName;
            }
        }
        return resource;
    }







    protected class ByteCode {
        public byte bytes[];
        public int length;
        public String name, original, codebase;

        public ByteCode(String name, String $original, byte bytes[], int length, String codebase) {
            this.name = name;
            original = $original;
            this.bytes = bytes;
            this.codebase = codebase;
        }
    }
}
