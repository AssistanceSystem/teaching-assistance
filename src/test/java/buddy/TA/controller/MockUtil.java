package buddy.TA.controller;

/**
 * Created by zhihu on 15/12/15.
 */
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//import com.conlect.oatos.dto.status.CommConstants;
//import com.conlect.oatos.dto.status.ErrorType;
//import com.conlect.oatos.http.PojoMapper;

public class MockUtil {

    /**
     * mock
     *
     * @param uri
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String mock(MockMvc mvc, String uri, String json)
            throws UnsupportedEncodingException, Exception {
        return mvc
                .perform(
                        post(uri).characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json.getBytes())).andReturn()
                .getResponse().getContentAsString();
    }

}