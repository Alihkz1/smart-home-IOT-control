package ir.znu.znuproject.shared;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response<T> {
    private boolean success = true;
    private String message = "";
    private T data;

    public Response(T data) {
        this.data = data;
    }
}
