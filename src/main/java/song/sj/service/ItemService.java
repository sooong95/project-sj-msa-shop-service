package song.sj.service;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.CustomUserDetails;
import song.sj.dto.item.ItemSaveDto;
import song.sj.entity.Member;
import song.sj.entity.item.Bottom;
import song.sj.entity.item.Item;
import song.sj.entity.item.Shoes;
import song.sj.entity.item.Top;
import song.sj.enums.ItemValue;
import song.sj.repository.ItemRepository;

import java.util.Objects;

import static song.sj.enums.ItemValue.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private Member getMemberFromJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return customUserDetails.getMember();
        }
        return null;
    }

    private Item toEntity(ItemSaveDto dto) {

        if (dto.getValue().equals(TOP)) {
            Top top = new Top(dto.getProductClassification());
            top.changeItemName(dto.getItemName());
            top.changeMaterial(dto.getMaterial());
            top.changeSize(dto.getSize());
            top.changeDesign(dto.getDesign());
            top.changeDescription(dto.getDescription());
            return top;
        }

        if (dto.getValue().equals(BOTTOM)) {
            Bottom bottom = new Bottom(dto.getProductClassification());
            bottom.changeItemName(dto.getItemName());
            bottom.changeMaterial(dto.getMaterial());
            bottom.changeSize(dto.getSize());
            bottom.changeDesign(dto.getDesign());
            bottom.changeDescription(dto.getDescription());
            return bottom;
        }

        if (dto.getValue().equals(SHOES)) {
            Shoes shoes = new Shoes(dto.getProductClassification());
            shoes.changeItemName(dto.getItemName());
            shoes.changeMaterial(dto.getMaterial());
            shoes.changeSize(dto.getSize());
            shoes.changeDesign(dto.getDesign());
            shoes.changeDescription(dto.getDescription());
            return shoes;
        }
        throw new IllegalArgumentException("상품 카테고리를 입력해주세요");
    }

    public void save(ItemSaveDto dto) {

        Item item = toEntity(dto);
        item.setMember(getMemberFromJwt());

        itemRepository.save(item);
    }

    public void updateItem(ItemSaveDto dto) {


    }

    public Item findItem() {
        return null;
    }
}
