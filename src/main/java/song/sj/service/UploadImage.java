package song.sj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import song.sj.entity.ItemImages;
import song.sj.entity.ShopImages;
import song.sj.repository.ItemImageRepository;
import song.sj.repository.ShopImageRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Slf4j
@RequiredArgsConstructor
public class UploadImage {

    private final ItemImageRepository itemImageRepository;
    private final ShopImageRepository shopImageRepository;

    public void uploadItemImage(MultipartFile file) throws IOException {

        ItemImages itemImage = itemImageRepository.save(
                ItemImages.builder()
                        .imageName(file.getOriginalFilename())
                        .imageType(file.getContentType())
                        .images(compressImage(file.getBytes()))
                        .build()
        );
        log.info("itemImage={}", itemImage);
    }

    public void uploadShopImage(MultipartFile file) throws IOException {

        ShopImages itemImage = shopImageRepository.save(
                ShopImages.builder()
                        .imageName(file.getOriginalFilename())
                        .imageType(file.getContentType())
                        .images(compressImage(file.getBytes()))
                        .build()
        );
    }

    // 이미지 파일로 압축
    public byte[] downloadItemImage(String imageName) {

        ItemImages itemImages = itemImageRepository.findByImageName(imageName).orElseThrow(RuntimeException::new);
        return decompressImage(itemImages.getImages());
    }

    public byte[] downloadShopImage(String imageName) {

        ShopImages shopImages = shopImageRepository.findByImageName(imageName).orElseThrow(RuntimeException::new);
        return decompressImage(shopImages.getImages());
    }

    // 이미지 데이터 압축
    private byte[] compressImage(byte[] data) {
        // Deflater 객체는 압축 작업을 수행하는 Java 클래스
        Deflater deflater = new Deflater();
        // 압축 수준이 높을수록 결과 파일 크기는 작아지지만, 속도는 느려질 수 있다.
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        // 메모리 내에서 바이트 데이터를 저장하고 관리. ByteArrayOutputStream 에 쓰여진 모든 데이터는 나중에 바이트 배열로 변환할 수 있다.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        // 압축 또는 압축 해제된 데이터를 처리할 때 사용할 임시 버퍼 배열.
        // 4KB의 크기를 가지는 바이트 배열을 만든다. 압축 라이브러리는 데이터를 한 번에 모두 처리하지 않고 부분적으로 처리하기 때문에,
        // 이 임시 버퍼를 사용하여 데이터를 일정 부분씩 스트림에 쓰는 방식.
        byte[] tmp = new byte[4 * 1024];

        while (!deflater.finished()) {
            // deflater 가 압축한 데이터를 tmp 배열에 저장하고, 그 데이터 크기를 반환.
            // size 는 실제로 tmp 에 저장된 압축된 데이터의 바이트 수를 나타낸다.
            int size = deflater.deflate(tmp);
            // outputStream 에 tmp 배열의 데이터를 기록하는 부분. size 만큼의 데이터만 기록하기 때문에 남는 바이트가 기록되지 않는다.
            outputStream.write(tmp, 0, size);
        }

        try {
            outputStream.close();
        } catch (Exception e) {

        }
        // outputStream.close()로 스트림을 종료하고, outputStream.toByteArray()를 통해 압축 해제된 데이터를 바이트 배열로 반환.
        return outputStream.toByteArray();
    }

    // 압축된 이미지 데이터 해제
    private byte[] decompressImage(byte[] data) {

        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];

        try {

            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {

        }
        return outputStream.toByteArray();
    }
}
