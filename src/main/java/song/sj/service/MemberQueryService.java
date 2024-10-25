package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.Result;
import song.sj.dto.member.MemberSearchDto;
import song.sj.dto.member.ShopMemberSearchDto;
import song.sj.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public Result findMembers() {
        List<MemberSearchDto> memberList = memberRepository.findMembers().stream()
                .map(m -> new MemberSearchDto(m.getId(), m.getUsername(), m.getEmail(), m.getAddress()))
                .collect(Collectors.toList());
        return new Result(memberList.size(), memberList);
    }

    public Result findShopMembers() {
        List<ShopMemberSearchDto> shopMemberList = memberRepository.findShopMembers().stream()
                .map(m -> new ShopMemberSearchDto(m.getId(), m.getUsername(), m.getEmail(), m.getShopName(), m.getBusinessRegistrationNumber(), m.getAddress()))
                .collect(Collectors.toList());
        return new Result(shopMemberList.size(), shopMemberList);
    }
}
