package it.ttsolution.form.tt.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

@Configuration
public class PubNubConfig {
	
	@Bean
	PubNub pubnub() {
		PNConfiguration pnConf = new PNConfiguration();
		pnConf.setSubscribeKey("sub-c-aef1ccd4-8377-11e9-99de-d6d3b84c4a25");
		pnConf.setPublishKey("pub-c-a19b1d0f-ca7f-47fd-8f0e-a09206fa62f1");
		pnConf.setSecure(false);
		return new PubNub(pnConf);
	}

}
