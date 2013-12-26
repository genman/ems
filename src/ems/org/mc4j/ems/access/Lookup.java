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

package org.mc4j.ems.access;

import java.util.HashMap;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), Apr 12, 2005
 * @version $Revision: 629 $($Author: ianpspringer $ / $Date: 2011-10-28 23:44:26 +0200 (Fr, 28 Okt 2011) $)
 */
public class Lookup {


    private static Lookup INSTANCE = new Lookup();

    public static Lookup getLookup() {
        return INSTANCE;
    }

    public Object lookup(String ognlExpression, Map contex) {
        try {
            Object expression = Ognl.parseExpression(ognlExpression);

            Object value = Ognl.getValue(expression, contex);
            return value;
        } catch (OgnlException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {

        Map context = new HashMap();
        context.put("a",new Integer(3));
        context.put("b","hello, world!");
        context.put("c",new String[] { "one", "two", "three", "four" });

        System.out.println("Value is: " + Lookup.getLookup().lookup("c[3]",context));

        System.out.println("Value is: " + Lookup.getLookup().lookup("c[3] = 'hi there'",context));

        System.out.println("Value is: " + Lookup.getLookup().lookup("c[3]",context));

    }
}
