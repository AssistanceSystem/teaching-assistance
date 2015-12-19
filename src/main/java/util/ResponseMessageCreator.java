package util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by zhihu on 15/12/18.
 */
public class ResponseMessageCreator {

    public static ResponseEntity<String> createResponse(String status, String message, HttpStatus httpStatus) {
        ResponseMessage responseMessage = new ResponseMessage(status, message);
        return new ResponseEntity<String>(responseMessage.toString(), httpStatus);
    }

    public static ResponseEntity<String> createResponse(
            String status, String message, String data, HttpStatus httpStatus) {
        ResponseMessage responseMessage = new ResponseMessage(status, message, data);
        return new ResponseEntity<String>(responseMessage.toString(), httpStatus);
    }
}
