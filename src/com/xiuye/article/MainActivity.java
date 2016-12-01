package com.xiuye.article;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.createListView();
	}

	private static int[] colors = { R.color.DarkCyan, R.color.MedSpringGreen, R.color.Orange, };

	private void createListView() {
		final String[] titles = this.getResources().getStringArray(R.array.articleTitles);
		BaseAdapter ba = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LinearLayout layout = null;
				int colorIndex = position % colors.length;
				String[] tf = titles[position].split("-----");
				TextView tv = null;
				if (convertView == null) {
					layout = new LinearLayout(MainActivity.this);
					layout.setOrientation(LinearLayout.HORIZONTAL);
					layout.setPadding(10, 10, 10, 10);
					tv = new TextView(MainActivity.this);

					tv.setTextSize(24.0f);
					layout.addView(tv);

				} else {
					layout = (LinearLayout) convertView;
					tv = (TextView) layout.getChildAt(0);

				}

				tv.setTextColor(MainActivity.this.getResources().getColor(colors[colorIndex]));
				tv.setText(tf[0]);
				tv.setTag(tf[1]);

				return layout;
			}

			@Override
			public long getItemId(int position) {
				return 0;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public int getCount() {
				return titles.length;
			}
		};
		ListView lv = (ListView) this.findViewById(R.id.titles);
		lv.setAdapter(ba);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LinearLayout layout = (LinearLayout) view;
				TextView tv = (TextView) layout.getChildAt(0);

				String filePath = (String) tv.getTag();

				MainActivity.this.enterToWebViewActivity(filePath);
			}
		});
	}

	private void enterToWebViewActivity(String articleUrl) {
		Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("url", articleUrl);
		intent.putExtra("urlBundle", bundle);
		MainActivity.this.startActivity(intent);
	}

}
