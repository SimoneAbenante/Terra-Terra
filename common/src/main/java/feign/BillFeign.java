package feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.BillDto;

@FeignClient(value = "bill")
public interface BillFeign {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<BillDto> getAllBill();
}
