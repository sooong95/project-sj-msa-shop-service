package song.sj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import song.sj.TimeStamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseImages extends TimeStamp {

    private String imageName;
    private String serverImageName;
    private String imageType;

    @Builder
    public BaseImages(String imageName, String serverImageName, String imageType) {
        this.imageName = imageName;
        this.serverImageName = serverImageName;
        this.imageType = imageType;
    }
}
