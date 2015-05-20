package net.syyama.slideswiper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Image取得クラス<br>
 * http://kiwamunet.hateblo.jp/entry/2014/09/24/112005
 * 
 * @author s-yamamoto
 * 
 */
public class ImageGetTask extends AsyncTask<String, Void, Bitmap> {

	private ImageView imageView;

	public ImageGetTask(ImageView imageView) {
		this.imageView = imageView;
	}

	/**
	 * Image取得メソッド
	 */
	@Override
	protected Bitmap doInBackground(String... params) {
		// TODO 自動生成されたメソッド・スタブ
		Bitmap imageBitmap;

		try {
			URL imageUrl = new URL(params[0]);
			InputStream imageInputStream;
			imageInputStream = imageUrl.openStream();
			imageBitmap = BitmapFactory.decodeStream(imageInputStream);

			return imageBitmap;
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 取得した画像をImageViewに変換
	 */
	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO 自動生成されたメソッド・スタブ
		// super.onPostExecute(result);
		imageView.setImageBitmap(result);
	}
}
