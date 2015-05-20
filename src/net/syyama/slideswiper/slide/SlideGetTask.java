package net.syyama.slideswiper.slide;

import java.util.ArrayList;

import android.os.AsyncTask;

import com.benfante.jslideshare.SlideShareAPI;
import com.benfante.jslideshare.SlideShareAPIFactory;
import com.benfante.jslideshare.messages.SlideshowInfo;

public class SlideGetTask extends AsyncTask<String, Void, ArrayList<String>> {
	private final static String API_KEY = "RCyF3upv";
	private final static String SHARED_SECRET = "adMbRXaG";

	private final static String ID = "45573715";
	private final static String URL = "http://www.slideshare.net/motch1cm/20150308-01";

	private final static String IMG_URL_1 = "http://image.slidesharecdn.com/";
	private final static String IMG_URL_2 = "/95/slide-";
	private final static String IMG_URL_3 = "-1024.jpg";

	private ISlideGetTask callback;

	public SlideGetTask(ISlideGetTask iSlideGetTask) {
		this.callback = iSlideGetTask;
	}

	@Override
	protected ArrayList<String> doInBackground(String... params) {

		ArrayList<String> slideUrlList = new ArrayList<String>();

		SlideShareAPI ssapi = SlideShareAPIFactory.getSlideShareAPI(API_KEY,
				SHARED_SECRET);

		SlideshowInfo slideshowInfo = ssapi.getSlideshowInfo(ID, URL);

		for (int i = 1; i <= slideshowInfo.getTotalSlides(); i++) {
			String pageUrl = IMG_URL_1 + slideshowInfo.getPlayerDoc()
					+ IMG_URL_2 + String.valueOf(i) + IMG_URL_3;

			slideUrlList.add(pageUrl);
		}

		return slideUrlList;
	}

	@Override
	protected void onPostExecute(ArrayList<String> result) {
		callback.OnGetSlideURLs(result);
	}
}
