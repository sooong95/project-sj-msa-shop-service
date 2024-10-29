package song.sj.repository.query;

import song.sj.dto.item.ItemSearchConditionDto;
import song.sj.entity.item.Item;

import java.util.List;

public interface ItemQueryRepository {

    List<Item> itemSearch(ItemSearchConditionDto dto, String categoryName);
}
