package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.sj.entity.item.Item;
import song.sj.repository.ItemRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemQueryService {

    private final ItemRepository itemRepository;

    /*public Item findItem(Long id) {
        Item item = itemRepository.findById(id).get();
    }*/
}
