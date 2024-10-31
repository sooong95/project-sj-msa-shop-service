package song.sj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.item.ItemSaveDto;
import song.sj.entity.ItemImages;
import song.sj.entity.item.Item;
import song.sj.repository.ItemImageRepository;
import song.sj.repository.ItemRepository;
import song.sj.repository.ShopImageRepository;
import song.sj.service.image.ImageUtils;
import song.sj.service.image.ItemImageUpload;
import song.sj.service.toEntity.ToItem;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final ItemImageRepository itemImageRepository;

    private Item findItem(Long id) {

        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public void save(ItemSaveDto dto, MultipartFile file) {

        Item item = ToItem.toItemEntity(dto);
        item.setMember(memberService.getMemberFromJwt());

        try {
            ItemImageUpload itemImageUpload = new ItemImageUpload(itemImageRepository);
            itemImageUpload.uploadItemImage(file);
        } catch (IOException e) {
            throw new RuntimeException("이미지를 등록해주세요!");
        }

        ItemImages itemImages = itemImageRepository.findByImageName(file.getOriginalFilename()).orElseThrow();
        item.addImage(itemImages);

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
