package song.sj.service.image;

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

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<ItemImages> serverFiles(List<MultipartFile> files) throws IOException {

        List<ItemImages> serverFileResult = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                serverFileResult.add(serverFile(file));
            }
        }
        return serverFileResult;
    }

    public ItemImages serverFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("이미지를 등록해 주세요");
        }

        String originalFilename = file.getOriginalFilename();
        String severFileName = createServerFileName(originalFilename);
        String contentType = file.getContentType();
        file.transferTo(new File(getFullPath(severFileName)));
        return new ItemImages(originalFilename, severFileName, contentType);
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
