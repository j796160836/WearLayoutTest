package com.johnny.wearlayouttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends WearableActivity {

	private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			Intent intent = new Intent(MainActivity.this, MyLibraryPlaylistDetailActivity.class);
			startActivity(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setAmbientEnabled();

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(buttonOnClickListener);
	}
}
