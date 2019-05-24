package feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("diningTable")
public interface DiningTableFeign {

}
