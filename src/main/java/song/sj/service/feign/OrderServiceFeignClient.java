package song.sj.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sj-ordering-service")
public interface OrderServiceFeignClient {

    @DeleteMapping("/order/{orderId}")
    void cancelOrder(@PathVariable("orderId") Long orderId);
}
