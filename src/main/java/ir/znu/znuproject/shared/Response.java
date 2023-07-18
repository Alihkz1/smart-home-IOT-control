package ir.znu.znuproject.shared;

import ir.znu.znuproject.Log.Log;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response<T> {
    private int status;
    private String message = "";

    private Map<String, List<T>> data;

    public Response() {
    }

    public Response(Map<String, List<T>> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, List<T>> getData() {
        return data;
    }

    public void setData(Map<String, List<T>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
