package it.ttsolution.form.tt.api.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;

import it.ttsolution.form.tt.api.service.JobService;

public class PeriodicJobUpdater {

	@Autowired
	private PubNub pubnub;

	String channelName = "awesomeChannel";

	@Autowired
	JobService service;

	int size = 0;

	PNCallback<PNPublishResult> cb = new PNCallback<PNPublishResult>() {

		@Override
		public void onResponse(PNPublishResult result, PNStatus status) {
		}
	};

	@Scheduled(fixedRateString = "5000")
	public void checkJobsUpdate() {

		int newSize = service.getAllJobsAsDtoList().size();

		if (newSize > size) {

			size = newSize;
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("jobs", size);
			pubnub.publish().channel(channelName).message(jsonObj).async(cb);
		}
	}

}
