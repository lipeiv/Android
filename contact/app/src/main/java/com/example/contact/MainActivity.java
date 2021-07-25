package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    List<String> contactsList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //获取到listview并且设置适配器
    ListView contactsView= (ListView) findViewById(R.id.contacts_view);
    adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
    contactsView.setAdapter(adapter);

        //判断是否开启读取通讯录的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager
                .PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            readContacts();
        }
    }
        private void readContacts(){
            Cursor cursor=null;
            try {
                //查询联系人数据,使用了getContentResolver().query方法来查询系统的联系人的数据
                //CONTENT_URI就是一个封装好的Uri，是已经解析过得常量
                cursor=getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );
                //对cursor进行遍历，取出姓名和电话号码
                if (cursor!=null){
                    Map<String, String> map = new HashMap<String, String>();
                    while (cursor.moveToNext()){
                        //获取联系人姓名
                        String displayName=cursor.getString(cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                        ));
                        //获取联系人手机号
                        String number=cursor.getString(cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        ));
                        //把取出的两类数据进行拼接，中间加换行符，然后添加到listview中
                        map.put(displayName, number);
                        contactsList.add(displayName+"\n"+number);
                    }
                    //contactsList.add(map.toString());

                    //刷新listview
                    adapter.notifyDataSetChanged();
                    System.out.println(map.toString());
                    //HttpURLConnection
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //记得关掉cursor
                if (cursor!=null){
                    cursor.close();
            }
        }
    }
}