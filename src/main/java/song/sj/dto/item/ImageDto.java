package song.sj.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import song.sj.service.image.ImageUtils;

@Data
@AllArgsConstructor
public class ImageDto {

    private String imageName;
    private String imageType;
    private byte[] images;
}
