package song.sj.controller.external_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import song.sj.dto.Result;
import song.sj.dto.shop.ShopInfoDto;
import song.sj.service.external_service.order.ExternalOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external")
public class ExternalOrderController {

    private final ExternalOrderService externalOrderService;

    @PostMapping("/shop-info")
    public Result<List<ShopInfoDto>> getShopInfo(@RequestBody List<Long> shopIdList) {

        List<ShopInfoDto> shopInfo = externalOrderService.getShopInfo(shopIdList);
        return new Result<>(shopInfo.size(), shopInfo);
    }
}
