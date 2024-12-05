package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.Wishlist;
import song.sj.repository.ShopRepository;
import song.sj.repository.WishlistRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ShopRepository shopRepository;
    private final MemberService memberService;

    @Transactional
    public void saveWishlist(Long shopId) {

        Wishlist wishlist = new Wishlist();

        wishlist.addWishlist(memberService.getMemberFromJwt(), shopRepository.findById(shopId).orElseThrow());

        wishlistRepository.save(wishlist);
    }
}
