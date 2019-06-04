package it.ttsolution.form.tt.api.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;

import exception.LocalException;
import it.ttsolution.form.tt.api.service.JobService;

@Component
public class PeriodicJobUpdater {

	Logger logger = LoggerFactory.getLogger("PERIODIC");

	@Autowired
	private PubNub pubnub;

	String channelName = "awesomeChannel";

	@Autowired
	JobService service;

	int size = 0;

	PNCallback<PNPublishResult> cb = new PNCallback<PNPublishResult>() {

		@Override
		public void onResponse(PNPublishResult result, PNStatus status) {
			logger.debug("PubNub status: " + status.getCategory().name());
		}
	};

	@Scheduled(fixedRateString = "10000")
	public void checkJobsUpdate() throws LocalException {

		logger.info("periodic task");
		int newSize = service.getAllEntityAsList().size();

		if (newSize > size) {
			logger.info("new jobs");
			size = newSize;
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("jobs", size);
			pubnub.publish().channel(channelName).message(jsonObj).async(cb);
		} else {
			logger.debug("no new jobs");
			
		}
	}

}
