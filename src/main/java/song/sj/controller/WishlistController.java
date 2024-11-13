package song.sj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import song.sj.service.WishlistService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<String> saveWishlist(@RequestParam("shopId") Long shopId) {
        wishlistService.saveWishlist(shopId);

        return new ResponseEntity<>("위시리스트에 추가 되었습니다.", HttpStatus.CREATED);
    }
}
