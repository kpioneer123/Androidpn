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
package org.androidpn.demoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.androidpn.client.Constants;
import org.androidpn.client.NotificationHistory;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is an androidpn client demo application.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class NotificationHistoryActivity extends Activity {

    private ListView mListView;

    private NotificationHistoryAdapter mAdapter;

    private List<NotificationHistory> mList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_history);

        mList = DataSupport.findAll(NotificationHistory.class);
        Collections.reverse(mList);
        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new NotificationHistoryAdapter(this, 0, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NotificationHistory notificationHistory = mList.get(position);
                String apiKey = notificationHistory.getApiKey();
                String title = notificationHistory.getTitle();
                String message = notificationHistory.getMessage();
                String uri = notificationHistory.getUri();
                String imageUrl = notificationHistory.getImageUrl();

                Intent intent = new Intent(NotificationHistoryActivity.this,
                        NotificationDetailsActivity.class);
                intent.putExtra(Constants.NOTIFICATION_ID, "");
                intent.putExtra(Constants.NOTIFICATION_API_KEY, apiKey);
                intent.putExtra(Constants.NOTIFICATION_TITLE, title);
                intent.putExtra(Constants.NOTIFICATION_MESSAGE, message);
                intent.putExtra(Constants.NOTIFICATION_URI, uri);
                intent.putExtra(Constants.NOTIFICATION_IMAGE_URL, imageUrl);
                intent.setAction(Constants.ACTION_NOTIFICATION_CLICKED);
                NotificationHistoryActivity.this.startActivity(intent);
            }
        });
        registerForContextMenu(mListView);
    }


    //复写上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        /**
         * Add a new item to the menu. This item displays the given title for its
         * label.
         *
         * @param groupId  分组的Id
         * @param itemId  添加菜单的Id
         * @param order  给item进行排序
         * @param title The text to display for the item.
         * @return The newly added menu item.
         */
        menu.add(0,0,0,"Remove");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==0){
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int index = menuInfo.position;
            NotificationHistory history = mList.get(index);
            history.delete();
            mList.remove(index);
            mAdapter.notifyDataSetChanged();
        }

        return super.onContextItemSelected(item);

    }

    class NotificationHistoryAdapter extends ArrayAdapter<NotificationHistory> {

        public NotificationHistoryAdapter(Context context, int textViewResourcedId, List<NotificationHistory> list) {
            super(context, textViewResourcedId, list);

        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            NotificationHistory history = getItem(position);
            if (convertView == null) {

                view = LayoutInflater.from(getContext()).inflate(R.layout.notification_history_item, null);
            } else {
                view = convertView;
            }
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_time = (TextView) view.findViewById(R.id.tv_time);

            tv_title.setText(history.getTitle());
            tv_time.setText(history.getTime());
            return view;
        }
    }
}