package song.sj;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import song.sj.entity.ItemCategory;
import song.sj.repository.ItemCategoryRepository;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    private final ItemCategoryRepository itemCategoryRepository;

    @Override
    public void run(String... args) throws Exception {

        if (itemCategoryRepository.findByItemCategoryName("ACCESSORY") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("ACCESSORY")
                    .build());
        }
        if (itemCategoryRepository.findByItemCategoryName("BAG") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("BAG")
                    .build());
        }if (itemCategoryRepository.findByItemCategoryName("BOTTOM") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("BOTTOM")
                    .build());
        }if (itemCategoryRepository.findByItemCategoryName("ETC") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("ETC")
                    .build());
        }if (itemCategoryRepository.findByItemCategoryName("OUTER") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("OUTER")
                    .build());
        }
        if (itemCategoryRepository.findByItemCategoryName("SHOES") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("SHOES")
                    .build());
        }
        if (itemCategoryRepository.findByItemCategoryName("TOP") == null) {
            itemCategoryRepository.save(ItemCategory.builder()
                    .itemCategoryName("TOP")
                    .build());
        }

    }
}
