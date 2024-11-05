package song.sj.service.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import song.sj.entity.ItemImages;
import song.sj.entity.item.Item;
import song.sj.repository.ItemImageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ItemImageUpload {

    private final ItemImageRepository itemImageRepository;

    public List<ItemImages> uploadItemImage(List<MultipartFile> files) throws IOException {

        List<ItemImages> images = new ArrayList<>();

        String uuid = UUID.randomUUID().toString();

        for (MultipartFile file : files) {
            ItemImages itemImage = itemImageRepository.save(
                    ItemImages.builder()
                            .imageName(file.getOriginalFilename())
                            .serverImageName(uuid+"."+file.getOriginalFilename())
                            .imageType(file.getContentType())
                            .images(ImageUtils.compressImage(file.getBytes()))
                            .build()
            );
            log.info("itemImage={}", itemImage);
            images.add(itemImage);
        }
        return images;
    }

    // 이미지 파일로 압축
    public byte[] downloadItemImage(String imageName) {

        ItemImages itemImages = itemImageRepository.findByImageName(imageName).orElseThrow(RuntimeException::new);
        return ImageUtils.decompressImage(itemImages.getImages());
    }
}
