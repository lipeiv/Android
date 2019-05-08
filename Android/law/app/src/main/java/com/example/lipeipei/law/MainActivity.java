package com.example.lipeipei.law;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

            private String[] dep = new String[]
                    {
                            "   宪法",
                            "   刑法",
                            "   刑事诉讼法",
                            "   民法",
                            "   民事诉讼法",
                            "   商法",
                            "   经济法",
                            "   劳动法",
                            "   行政法",
                            "   行政诉讼法",
                            "   国际法",
                            "   条例规章"
                    };
            private String[] [] law = new String[][]
                    {
                            {
                                "中华人民共和国宪法（2018修正）",

                            },

                            { "中华人民共和国刑法（2017修正）",
                              "刑法修正案（一）",
                              "刑法修正案（二）",
                              "刑法修正案（三）",
                              "刑法修正案（四）",
                              "刑法修正案（五）",
                              "刑法修正案（六）",
                              "刑法修正案（七）",
                              "刑法修正案（八）",
                              "刑法修正案（九）",
                              "刑法修正案（十）"
                            }



                    };

            @Override
            public int getGroupCount() {
                return dep.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return law[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return dep[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return law [groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            private TextView getTextView()
            {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,64);
                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(MainActivity.this);
                //ll.setOrientation();
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };

         ExpandableListView expandListView =  (ExpandableListView)findViewById(R.id.list);
         expandListView.setAdapter(adapter);
    }

}
