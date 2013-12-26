/*
 * Copyright 2002-2005 Greg Hinkle
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

import java.util.Date;

import org.mc4j.ems.connection.bean.EmsBean;

/**
 * @author Greg Hinkle (ghinkle@users.sourceforge.net), Nov 10, 2005
 * @version $Revision: 629 $($Author: ianpspringer $ / $Date: 2011-10-28 23:44:26 +0200 (Fr, 28 Okt 2011) $)
 */
public class EmsNotificationEvent {


    private EmsBean bean;
    private String message;
    private long sequenceNumber;
    private String type;
    private long timeStamp;
    private Object userData;
    private Object source;


    public EmsNotificationEvent() {
    }

    public EmsNotificationEvent(EmsBean bean, String message, long sequenceNumber, String type, long timeStamp, Object userData, Object source) {
        this.bean = bean;
        this.message = message;
        this.sequenceNumber = sequenceNumber;
        this.type = type;
        this.timeStamp = timeStamp;
        this.userData = userData;
        this.source = source;
    }

    public EmsBean getBean() {
        return bean;
    }

    public String getMessage() {
        return message;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public String getType() {
        return type;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Object getUserData() {
        return userData;
    }

    public Object getSource() {
        return source;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("Notification Event on bean [");
        buf.append(bean.getBeanName().getCanonicalName());
        buf.append("]\n\tMessage: ");
        buf.append(message);
        buf.append("\n\tSequence: ");
        buf.append(sequenceNumber);
        buf.append("\n\tType: ");
        buf.append(type);
        buf.append("\n\tTimeStamp: ");
        buf.append(new Date(timeStamp));
        buf.append("\n\tUser Data: ");
        buf.append(userData);
        buf.append("\n\tSource: ");
        buf.append(source);

        return buf.toString();
    }

}
