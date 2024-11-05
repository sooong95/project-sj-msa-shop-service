package song.sj.repository.query;

import song.sj.entity.ItemImages;

import java.util.List;

public interface ItemImageQueryRepository {

    List<ItemImages> findByItemId(Long id);
}
