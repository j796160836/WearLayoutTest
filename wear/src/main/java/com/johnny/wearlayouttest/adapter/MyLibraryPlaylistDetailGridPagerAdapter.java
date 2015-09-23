package com.johnny.wearlayouttest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.ActionPage;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.johnny.wearlayouttest.R;

public class MyLibraryPlaylistDetailGridPagerAdapter extends GridPagerAdapter
		implements GridViewPager.OnPageChangeListener {

	public static final String TAG = MyLibraryPlaylistDetailGridPagerAdapter.class.getSimpleName();

	private static final int GRID_COLUMN_COUNT = 2;

	private static class PagerColumn {
		private static final int PAGER_LIST = 0;
		private static final int PAGER_ACTION = 1;
	}

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private boolean loadingPlaylist;

	public MyLibraryPlaylistDetailGridPagerAdapter(
			Context context) {
		super();
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	public boolean isLoadingPlaylist() {
		return loadingPlaylist;
	}

	public void setLoadingPlaylist(boolean loadingPlaylist) {
		this.loadingPlaylist = loadingPlaylist;
	}

	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public int getColumnCount(int i) {
		return (!loadingPlaylist) ? GRID_COLUMN_COUNT : 1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int row, final int column) {
		View view;
		TextView labelTitle;
		ActionPage navActionPage;

		if (!loadingPlaylist) {
			switch (column) {
				case PagerColumn.PAGER_LIST:
					view = mLayoutInflater.inflate(R.layout.layout_page_list, container, false);

					container.addView(view);
					return view;
				case PagerColumn.PAGER_ACTION:
					// The navigate action
					navActionPage = (ActionPage) mLayoutInflater.inflate(
							R.layout.gridpage_action, container, false);

					navActionPage.setImageResource(R.drawable.ic_full_shuffle);
					navActionPage.setText(mContext.getString(R.string.shuffle));
					container.addView(navActionPage);
					return navActionPage;
			}
		} else {
			// Loading state
			view = mLayoutInflater.inflate(R.layout.layout_page_loading
					, container, false);
			final ProgressBar viewProgressBar = (ProgressBar) view.findViewById(R.id.view_progressBar);
			final TextView labelLoading = (TextView) view.findViewById(R.id.label_loading);
			view.setBackgroundColor(mContext.getResources().getColor(R.color.background_color));
			viewProgressBar.setVisibility(View.VISIBLE);
			labelLoading.setTextColor(Color.WHITE);
			labelLoading.getPaint().setAntiAlias(true);
			container.addView(view);
			return view;
		}
		return new View(mContext);
	}

	@Override
	public Drawable getBackgroundForPage(int row, int column) {
		if (column == 0) {
			return new ColorDrawable(0); // Empty black drawable
		}
		return super.getBackgroundForPage(row, column);
	}

	@Override
	public void destroyItem(ViewGroup viewGroup, int row, int column, Object object) {
		viewGroup.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void onPageScrolled(int posX, int posY, float posOffsetX, float posOffsetY,
							   int posOffsetPixelsX, int posOffsetPixelsY) {
	}

	@Override
	public void onPageSelected(int row, int col) {
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}
}
