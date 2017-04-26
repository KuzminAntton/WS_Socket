package com.epam.ws_socet.utils.jackson;

import com.epam.ws_socet.bean.Book;
import com.epam.ws_socet.bean.BooksPojo;
import com.epam.ws_socet.utils.constants.CommonConstants;
import com.epam.ws_socet.service.handler.method.Response;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public static void writeBookInJsonFormat(Book book, String body, Response rp) {
        body = JsonUtils.toJson(book);
        rp.setContentType(CommonConstants.ACCEPT_TYPE_JSON);
        rp.setContentLength(String.valueOf(body.getBytes().length));
        rp.setBody(body);

    }
    public static void writeBookInJsonFormat(BooksPojo book, String body, Response rp) {
        body = JsonUtils.toJson(book);
        rp.setContentType(CommonConstants.ACCEPT_TYPE_JSON);
        rp.setContentLength(String.valueOf(body.getBytes().length));
        rp.setBody(body);

    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return mapper.readValue(json, classOfT);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public static String toJson(Object src) {
        try {
            return mapper.writeValueAsString(src);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
