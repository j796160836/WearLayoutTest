# WearLayoutTest
BoxInsetLayout layout bug in Android Wear

I found the bug when using BoxInsetLayout wrapped with ViewGroup (FrameLayout/RelativeLayout).
It will calculate the wrong window insets.
(Check the `layout_bug` branch for details)
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:layout_width="match_parent"
	android:layout_height="match_parent">

	<android.support.wearable.view.BoxInsetLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent">

		<FrameLayout
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			app:layout_box="all">

			<!-- Some layout -->

		</FrameLayout>
	</android.support.wearable.view.BoxInsetLayout>
</FrameLayout>
```


I found the way to avoid the bug is remove all the parent view group, let BoxInsetLayout always at the rootview.
Sovled the problem.
(Check the `layout_bug_fixed` branch for details)
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.wearable.view.BoxInsetLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:layout_width="match_parent"
	android:layout_height="match_parent">

	<FrameLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		app:layout_box="all">

		<!-- Some layout -->

	</FrameLayout>
</android.support.wearable.view.BoxInsetLayout>
```
