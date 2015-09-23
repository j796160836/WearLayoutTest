package com.johnny.wearlayouttest;

import android.support.wearable.view.WearableListView;
import android.view.View;

public class WearListHeaderScrollListener implements WearableListView.OnScrollListener {
	private View header;

	public WearListHeaderScrollListener(View header) {
		this.header = header;
	}

	@Override
	public void onScroll(int i) {
	}

	@Override
	public void onAbsoluteScrollChange(int i) {
		if (header != null) {
			header.setY(-i);
		}
	}

	@Override
	public void onScrollStateChanged(int i) {
	}

	@Override
	public void onCentralPositionChanged(int i) {
	}
}
