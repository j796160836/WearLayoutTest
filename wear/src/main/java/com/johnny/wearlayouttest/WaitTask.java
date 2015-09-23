package com.johnny.wearlayouttest;

import android.os.AsyncTask;

public class WaitTask extends AsyncTask<Void, Void, Void> {
	private Runnable postRunnable;
	private long time;

	public WaitTask(Runnable postRunnable) {
		this(postRunnable, 5000);
	}

	public WaitTask(Runnable postRunnable, long time) {
		this.postRunnable = postRunnable;
		this.time = time;
	}

	@Override
	protected Void doInBackground(Void... voids) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		if (postRunnable != null) {
			postRunnable.run();
		}
	}
}
