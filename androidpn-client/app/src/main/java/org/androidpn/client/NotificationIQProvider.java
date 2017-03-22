/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.client;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

/** 
 * This class parses incoming IQ packets to NotificationIQ objects.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class NotificationIQProvider implements IQProvider {

    public NotificationIQProvider() {
    }

    /**
     *服务器定义的所传参数
     *
     */
//    private IQ createNotificationIQ(String id,String apiKey, String title,
//                                    String message, String uri,String imageUrl ) {
//        // String id = String.valueOf(System.currentTimeMillis());
//
//        Element notification = DocumentHelper.createElement(QName.get(
//                "notification", NOTIFICATION_NAMESPACE));
//        notification.addElement("id").setText(id);
//        notification.addElement("apiKey").setText(apiKey);
//        notification.addElement("title").setText(title);
//        notification.addElement("message").setText(message);
//        notification.addElement("uri").setText(uri);
//        notification.addElement("imageUrl").setText(imageUrl);
//        IQ iq = new IQ();
//        iq.setType(IQ.Type.set);
//        iq.setChildElement(notification);
//
//        return iq;
//    }
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {



        NotificationIQ notification = new NotificationIQ();
        for (boolean done = false; !done;) {
            int eventType = parser.next();
            if (eventType == 2) {
                if ("id".equals(parser.getName())) {
                    notification.setId(parser.nextText());
                }
                if ("apiKey".equals(parser.getName())) {
                    notification.setApiKey(parser.nextText());
                }
                if ("title".equals(parser.getName())) {
                    notification.setTitle(parser.nextText());
                }
                if ("message".equals(parser.getName())) {
                    notification.setMessage(parser.nextText());
                }
                if ("uri".equals(parser.getName())) {
                    notification.setUri(parser.nextText());
                }

                if ("imageUrl".equals(parser.getName())) //获取服务器自定义参数的值
                {
                    notification.setImageUrl(parser.nextText());
                }
            } else if (eventType == 3
                    && "notification".equals(parser.getName())) {
                done = true;
            }
        }

        return notification;
    }

}
