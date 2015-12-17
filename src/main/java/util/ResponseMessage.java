package util;

/**
 * Created by zhihu on 15/12/16.
 */
public class ResponseMessage {

    private String status;
    private String message;
    private  String data;

    public ResponseMessage(String status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{"
                + "\"status\":\"" + status + '\"'
                + ", \"message\":\"" + message + '\"'
                + ", \"data\":\"" + data + '\"'
                + '}';
    }
}
