package song.sj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import song.sj.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Boolean existsByEmail(String email);

    // email 을 받아 DB 테이블에서 회원을 조회하는 메서드 작성
    Shop findByEmail(String email);
}
