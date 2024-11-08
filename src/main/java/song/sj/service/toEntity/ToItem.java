package song.sj.service.toEntity;

import song.sj.dto.item.ItemSaveDto;
import song.sj.entity.item.*;

public class ToItem {

    private static Item initializeFields(Item item, ItemSaveDto dto) {
        item.changeItemName(dto.getItemName());
        item.changeMaterial(dto.getMaterial());
        item.changeSize(dto.getSize());
        item.changeDesign(dto.getDesign());
        item.changeDescription(dto.getDescription());

        return item;
    }

    public static Item toItemEntity(ItemSaveDto dto) {

        Item item;

        switch (dto.getValue()) {

            case TOP -> item = new Top(dto.getProductClassification());
            case BOTTOM -> item = new Bottom(dto.getProductClassification());
            case SHOES -> item = new Shoes(dto.getProductClassification());
            case OUTER -> item = new Outer(dto.getProductClassification());
            case BAG -> item = new Bag(dto.getProductClassification());
            case ACCESSORY -> item = new Accessory(dto.getProductClassification());
            case ETC -> item = new Etc(dto.getProductClassification());
            default -> throw new IllegalArgumentException("상품 카테고리를 입력해주세요");
        }

        return initializeFields(item, dto);
    }
}
