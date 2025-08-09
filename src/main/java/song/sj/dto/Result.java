package song.sj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {

    private int count;
    private T data;
}
