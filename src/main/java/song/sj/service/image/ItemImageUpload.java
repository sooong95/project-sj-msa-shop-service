package song.sj.service.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import song.sj.entity.ItemImages;
import song.sj.repository.ItemImageRepository;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ItemImageUpload {

    private final ItemImageRepository itemImageRepository;

    public void uploadItemImage(MultipartFile file) throws IOException {

        ItemImages itemImage = itemImageRepository.save(
                ItemImages.builder()
                        .imageName(file.getOriginalFilename())
                        .imageType(file.getContentType())
                        .images(ImageUtils.compressImage(file.getBytes()))
                        .build()
        );
        log.info("itemImage={}", itemImage);
    }

    // 이미지 파일로 압축
    public byte[] downloadItemImage(String imageName) {

        ItemImages itemImages = itemImageRepository.findByImageName(imageName).orElseThrow(RuntimeException::new);
        return ImageUtils.decompressImage(itemImages.getImages());
    }
}
