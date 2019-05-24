package feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("status")
public interface StatusFeign {
	
}
