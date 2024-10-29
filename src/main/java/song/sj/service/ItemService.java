package song.sj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.item.ItemSaveDto;
import song.sj.entity.item.Bottom;
import song.sj.entity.item.Item;
import song.sj.entity.item.Shoes;
import song.sj.entity.item.Top;
import song.sj.repository.ItemRepository;
import song.sj.service.toEntity.ToItem;

import java.util.Optional;

import static song.sj.enums.ItemValue.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberService memberService;

    private Item findItem(Long id) {

        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public void save(ItemSaveDto dto) {

        Item item = ToItem.toItemEntity(dto);
        item.setMember(memberService.getMemberFromJwt());

        itemRepository.save(item);
    }

    public void updateItem(Long id, ItemSaveDto dto) {

        Item item = findItem(id);
        item.changeItemName(dto.getItemName());
        item.changeSize(dto.getSize());
        item.changeMaterial(dto.getMaterial());
        item.changeDesign(dto.getDesign());
        item.changeDescription(dto.getDescription());
    }
}
