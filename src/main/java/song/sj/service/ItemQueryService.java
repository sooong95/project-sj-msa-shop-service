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
import song.sj.entity.ItemImages;
import song.sj.entity.item.Item;
import song.sj.repository.ItemImageRepository;
import song.sj.repository.ItemRepository;
import song.sj.repository.query.ItemQueryRepository;
import song.sj.service.image.ImageFile;
import song.sj.service.image.ImageUtils;

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

    public Result<List<SearchItemDto>> searchItems(ItemSearchConditionDto dto, String categoryName) {

        List<SearchItemDto> searchItem = itemRepository.itemSearch(dto, categoryName)
                .stream().map(i -> new SearchItemDto(i.getItemName()))
                .toList();

        return new Result<>(searchItem.size(), searchItem);

    }

    /*public FindItemDto findItem(Long id) {

        Item item = itemRepository.findById(id).orElseThrow();
        List<byte[]> images = itemImageRepository.findByItemId(id)
                .stream().map(image -> ImageUtils.decompressImage(image.getImages())).toList();

        return new FindItemDto(item.getItemName(), item.getMaterial(), item.getSize(), item.getDesign(), item.getDescription(), images);
    }

    public List<byte[]> findItemImage(Long id) {

        return itemImageRepository.findByItemId(id).stream().map(image -> ImageUtils.decompressImage(image.getImages())).toList();
    }*/

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
