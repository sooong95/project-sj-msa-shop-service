package song.sj.service.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import song.sj.entity.ItemImages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageFile {

    private static final Logger log = LoggerFactory.getLogger(ImageFile.class);
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    /*public List<ItemImages> serverFiles(List<MultipartFile> files) throws IOException {

        List<ItemImages> serverFileResult = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                log.info("!file.isEmpty");


                ItemImages e = serverFile(file);
                log.info("e={}", e);
                serverFileResult.add(e);
                log.info("첫번째 파일={}", serverFileResult.get(0));
            }
        }
        return serverFileResult;
    }*/

    public ItemImages serverFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("이미지를 등록해 주세요");
        }

        String originalFilename = file.getOriginalFilename();
        String serverFileName = createServerFileName(originalFilename);
        String contentType = file.getContentType();

        log.info("서버 파일 이름={}", serverFileName);

        log.info("파일 경로={}", new File(getFullPath(serverFileName)));

        try {
            file.transferTo(new File(getFullPath(serverFileName)));
        } catch (IOException e) {
            log.error("파일 저장 중 오류 발생 - 경로: {}, 오류: {}", getFullPath(serverFileName), e.getMessage());
            throw e;
        }
        /*file.transferTo(new File(getFullPath(serverFileName)));*/
        ItemImages images = new ItemImages(originalFilename, serverFileName, contentType);
        log.info("출력 확인={}", images);
        return images;
    }

    private String createServerFileName(String originalFilename) {

        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
