package feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("job")
public interface JobFeign {

}
