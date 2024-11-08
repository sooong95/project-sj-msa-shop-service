package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.Result;
import song.sj.dto.item.FindItemDto;
import song.sj.dto.item.ImageDto;
import song.sj.dto.item.ItemSearchConditionDto;
import song.sj.dto.item.SearchItemDto;
import song.sj.entity.item.Item;
import song.sj.repository.ItemImageRepository;
import song.sj.repository.ItemRepository;
import song.sj.service.image.ImageFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemQueryService {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final ImageFile imageFile;

    public Result<List<SearchItemDto>> searchItems(ItemSearchConditionDto dto) {

        List<SearchItemDto> searchItem = itemRepository.itemSearch(dto)
                .stream()
                .map(i -> {

                    List<String> imageUrlList = itemImageRepository.findByItemId(i.getId()).stream().map(image -> imageFile.getFullPath(image.getImageName())).toList();

                    return new SearchItemDto(
                            i.getItemName(),
                            itemRepository.findById(i.getId()).orElseThrow().getItemCategory().getItemCategoryName(),
                            i.getSize(),
                            imageUrlList // 단일 이미지 URL
                    );
                })
                .collect(Collectors.toList());

        return new Result<>(searchItem.size(), searchItem);
    }

    public FindItemDto findItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        return new FindItemDto(item.getItemName(), item.getMaterial(), item.getSize(), item.getDesign(), item.getDescription());
    }

    public Result<List<ImageDto>> findItemImage(Long id) {
        List<ImageDto> itemImages = itemImageRepository.findByItemId(id).stream()
                .map(image -> new ImageDto(image.getImageName(), image.getImageType(), imageFile.getFullPath(image.getImageName()))).toList();

        return new Result<>(itemImages.size(), itemImages);

    }
}
