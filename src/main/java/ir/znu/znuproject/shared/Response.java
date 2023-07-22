package ir.znu.znuproject.shared;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private boolean success;
    private String message = "";
    private Map<String, T> data;

    public Response(Map<String, T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
