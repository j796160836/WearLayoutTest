package com.johnny.wearlayouttest;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

import com.johnny.wearlayouttest.adapter.MyLibraryPlaylistDetailGridPagerAdapter;

public class MyLibraryPlaylistDetailActivity extends WearableActivity {

	public static final String TAG = MyLibraryPlaylistDetailActivity.class.getSimpleName();

	private GridViewPager pager;
	private MyLibraryPlaylistDetailGridPagerAdapter mAdapter;
	private WaitTask waitTask;

	private Runnable postWait = new Runnable() {
		@Override
		public void run() {
			updateDataList();
		}
	};

	private void updateDataList() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!isFinishing()) {
					mAdapter.setLoadingPlaylist(false);
					mAdapter.notifyDataSetChanged();
				}
			}
		});
	}

	private OnApplyWindowInsetsListener onApplyWindowInsetsListener = new OnApplyWindowInsetsListener() {
		@Override
		public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
			// Adjust page margins:
			//   A little extra horizontal spacing between pages looks a bit
			//   less crowded on a round display.
			final boolean round = insets.isRound();
			int rowMargin = getResources().getDimensionPixelOffset(R.dimen.page_row_margin);
			int colMargin = getResources().getDimensionPixelOffset(round ?
					R.dimen.page_column_margin_round : R.dimen.page_column_margin);
			pager.setPageMargins(rowMargin, colMargin);

			// GridViewPager relies on insets to properly handle
			// layout for round displays. They must be explicitly
			// applied since this listener has taken them over.
			pager.onApplyWindowInsets(insets);
			return insets;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_library_playlist_detail);
		pager = (GridViewPager) findViewById(R.id.pager);
		pager.setOnApplyWindowInsetsListener(onApplyWindowInsetsListener);

		mAdapter = new MyLibraryPlaylistDetailGridPagerAdapter(MyLibraryPlaylistDetailActivity.this);
		mAdapter.setLoadingPlaylist(true);
		pager.setAdapter(mAdapter);
		DotsPageIndicator dotsPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
		dotsPageIndicator.setPager(pager);

		waitTask = new WaitTask(postWait, 3500);
		waitTask.execute();
	}
}
