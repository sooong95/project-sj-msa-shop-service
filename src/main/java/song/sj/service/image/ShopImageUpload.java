package song.sj.service.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import song.sj.entity.ShopImages;
import song.sj.repository.ShopImageRepository;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ShopImageUpload {

    private final ShopImageRepository shopImageRepository;

    public void uploadShopImage(MultipartFile file) throws IOException {

        ShopImages itemImage = shopImageRepository.save(
                ShopImages.builder()
                        .imageName(file.getOriginalFilename())
                        .imageType(file.getContentType())
                        .images(ImageUtils.compressImage(file.getBytes()))
                        .build()
        );
    }

    public byte[] downloadShopImage(String imageName) {

        ShopImages shopImages = shopImageRepository.findByImageName(imageName).orElseThrow(RuntimeException::new);
        return ImageUtils.decompressImage(shopImages.getImages());
    }
}
