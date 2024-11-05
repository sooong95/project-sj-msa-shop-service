package song.sj.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import song.sj.dto.item.FindItemDto;
import song.sj.dto.item.ItemSaveDto;
import song.sj.entity.ItemImages;
import song.sj.entity.item.Item;
import song.sj.repository.ItemImageRepository;
import song.sj.repository.ItemRepository;
import song.sj.service.image.ItemImageUpload;
import song.sj.service.toEntity.ToItem;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final ItemImageRepository itemImageRepository;

    private Item privateFindItem(Long id) {

        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public void save(ItemSaveDto dto, List<MultipartFile> files) {

        Item item = ToItem.toItemEntity(dto);
        item.setMember(memberService.getMemberFromJwt());

        itemRepository.save(item);

        try {
            ItemImageUpload itemImageUpload = new ItemImageUpload(itemImageRepository);
            List<ItemImages> itemImages = itemImageUpload.uploadItemImage(files);

            for (ItemImages itemImage : itemImages) {

                if (itemImage.getImageType() == null) {
                    throw new RuntimeException("이미지를 등록해 주세요!");
                }

                log.info("이미지 정보={}", itemImage);

                ItemImages image = itemImageRepository.findById(itemImage.getId()).orElseThrow();
                item.addImage(image);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    public void updateItem(Long id, ItemSaveDto dto) {

        Item item = privateFindItem(id);
        item.changeItemName(dto.getItemName());
        item.changeSize(dto.getSize());
        item.changeMaterial(dto.getMaterial());
        item.changeDesign(dto.getDesign());
        item.changeDescription(dto.getDescription());
    }

    public void deleteImage(Long id) {

        ItemImages itemImage = itemImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이미지를 찾을 수 없습니다."));

        Item item = itemImage.getItem();

        item.deleteImage(itemImage);

        itemImageRepository.delete(itemImage);
    }
}
