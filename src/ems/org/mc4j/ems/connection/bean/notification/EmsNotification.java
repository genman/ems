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

package org.mc4j.ems.connection.bean.notification;

import java.util.List;

/**
 * An MBean notification.
 * 
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), Apr 4, 2005
 * @version $Revision: 620 $($Author: ianpspringer $ / $Date: 2010-08-02 21:03:05 +0200 (Mo, 02 Aug 2010) $)
 */
public interface EmsNotification extends Comparable {

    String getName();

    String getDescription();

    String[] getTypes();

    void addNotificationListener(EmsNotificationListener listener);

    boolean removeNotificationListener(EmsNotificationListener listener);

    void startListening();

    void stopListening();

    List<EmsNotificationEvent> getEvents();

    boolean isListening();
}
