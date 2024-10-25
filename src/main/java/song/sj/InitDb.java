package song.sj;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.member.MemberJoinDto;
import song.sj.dto.member.ShopMemberJoinDto;
import song.sj.entity.Address;
import song.sj.entity.Member;
import song.sj.enums.Role;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitMember initMember;

    @PostConstruct
    public void init() {
        initMember.initDb();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitMember {

        private final EntityManager em;

        public void initDb() {

            MemberJoinDto memberDto1 = new MemberJoinDto("song1", "song1@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.MEMBER);
            MemberJoinDto memberDto2 = new MemberJoinDto("song2", "song2@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.MEMBER);
            MemberJoinDto memberDto3 = new MemberJoinDto("song3", "song3@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.MEMBER);
            MemberJoinDto memberDto4 = new MemberJoinDto("song4", "song4@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.MEMBER);
            MemberJoinDto memberDto5 = new MemberJoinDto("song5", "song5@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.MEMBER);

            Member member1 = memberDto1.toEntity();
            Member member2 = memberDto2.toEntity();
            Member member3 = memberDto3.toEntity();
            Member member4 = memberDto4.toEntity();
            Member member5 = memberDto5.toEntity();

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);

            ShopMemberJoinDto shopMemberJoinDto1 = new ShopMemberJoinDto("shop1", "shop1", "1234324", "shop1@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.SHOP);
            ShopMemberJoinDto shopMemberJoinDto2 = new ShopMemberJoinDto("shop2", "shop2", "9808345", "shop2@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.SHOP);
            ShopMemberJoinDto shopMemberJoinDto3 = new ShopMemberJoinDto("shop3", "shop3", "878732894", "shop3@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.SHOP);
            ShopMemberJoinDto shopMemberJoinDto4 = new ShopMemberJoinDto("shop4", "shop4", "18798234", "shop4@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.SHOP);
            ShopMemberJoinDto shopMemberJoinDto5 = new ShopMemberJoinDto("shop5", "shop5", "32958445", "shop5@nnnn.com", "password", new Address("city", "street", "zipcode"), Role.SHOP);

            Member shopMember1 = shopMemberJoinDto1.toEntity();
            Member shopMember2 = shopMemberJoinDto2.toEntity();
            Member shopMember3 = shopMemberJoinDto3.toEntity();
            Member shopMember4 = shopMemberJoinDto4.toEntity();
            Member shopMember5 = shopMemberJoinDto5.toEntity();

            em.persist(shopMember1);
            em.persist(shopMember2);
            em.persist(shopMember3);
            em.persist(shopMember4);
            em.persist(shopMember5);
        }
    }
}
