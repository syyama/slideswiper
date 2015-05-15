package net.syyama.slideswiper;

import io.onthego.ari.KeyDecodingException;
import io.onthego.ari.android.ActiveAri;
import io.onthego.ari.android.Ari;
import io.onthego.ari.event.ClosedHandEvent;
import io.onthego.ari.event.LeftSwipeEvent;
import io.onthego.ari.event.OpenHandEvent;
import io.onthego.ari.event.RightSwipeEvent;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements Ari.StartCallback,
		Ari.ErrorCallback, ClosedHandEvent.Listener, LeftSwipeEvent.Listener,
		OpenHandEvent.Listener, RightSwipeEvent.Listener {

	private final String TAG = "MainActivity";
	private ActiveAri mAri;

	@Override
	protected void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);

		try {
			mAri = ActiveAri
					.getInstance(getString(R.string.ari_license_key),
							getApplicationContext()).addErrorCallback(this)
					.addListeners(this);
		} catch (KeyDecodingException e) {
			Log.e(TAG, "Faied to init Ari:", e);
			finish();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");

		mAri.start(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");

		if (mAri != null) {
			mAri.stop();
		}
	}

	@Override
	public void onAriStart() {
		// 必要に応じて、より高速スワイプ検出のための符号検出を無効にしてください。
		// ライセンスキーはProまたはエンタープライズティアのためではない場合、これは例外をスローします。
		// mAri.disable(HandEvent.Type.CLOSED_HAND, HandEvent.Type.OPEN_HAND);
	}

	@Override
	public void onAriError(Throwable e) {
		Log.e(TAG, "Ari had an error:", e);
		finish();
	}

	@Override
	public void onClosedHandEvent(ClosedHandEvent event) {
		Log.i(TAG, "閉じる");
		// Toast.makeText(this, "閉じる", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onOpenHandEvent(OpenHandEvent event) {
		Log.i(TAG, "開く");
		// Toast.makeText(this, "開く", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onLeftSwipeEvent(LeftSwipeEvent event) {
		Log.i(TAG, "スワイプ: " + event);
		// Toast.makeText(this, "スワイプ: " + event, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRightSwipeEvent(RightSwipeEvent event) {
		Log.i(TAG, "スワイプ: " + event);
		// Toast.makeText(this, "スワイプ: " + event, Toast.LENGTH_SHORT).show();

	}
}
