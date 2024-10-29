package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.dto.Result;
import song.sj.dto.item.ItemSearchConditionDto;
import song.sj.dto.item.SearchItemDto;
import song.sj.entity.item.Item;
import song.sj.repository.ItemRepository;
import song.sj.repository.query.ItemQueryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemQueryService {

    private final ItemRepository itemRepository;

    public Result<List<SearchItemDto>> searchItems(ItemSearchConditionDto dto, String categoryName) {

        List<SearchItemDto> searchItem = itemRepository.itemSearch(dto, categoryName)
                .stream().map(i -> new SearchItemDto(i.getItemName()))
                .toList();

        return new Result<>(searchItem.size(), searchItem);

    }
}
