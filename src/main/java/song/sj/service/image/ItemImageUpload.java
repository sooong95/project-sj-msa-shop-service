package song.sj.service.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import song.sj.entity.ItemImages;
import song.sj.entity.item.Item;
import song.sj.repository.ItemImageRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ItemImageUpload {

    private final ItemImageRepository itemImageRepository;

    public void uploadItemImage(List<MultipartFile> files, Item item) throws IOException {

        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("상품 이미지를 등록해주세요!");
        }

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
        }

    }

    // 이미지 파일로 압축
    public byte[] downloadItemImage(String imageName) {

        ItemImages itemImages = itemImageRepository.findByImageName(imageName).orElseThrow(RuntimeException::new);
        return ImageUtils.decompressImage(itemImages.getImages());
    }
}
