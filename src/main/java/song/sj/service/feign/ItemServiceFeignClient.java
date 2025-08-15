package song.sj.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import song.sj.dto.Result;
import song.sj.dto.feign_dto.item.ItemCategoryDto;

import java.util.List;

@FeignClient(name = "sj-item-service")
public interface ItemServiceFeignClient {

    @GetMapping("/item/item-category-name")
    Result<List<ItemCategoryDto>> getItemCategoryNameList(List<String> itemCategoryNameList);
}
