package com.accenture.fsadd.util;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; 
 
 
public class JsonUtils { 
    private static ObjectMapper objectMapper = new ObjectMapper(); 
 
    public static String writeValueAsString(Object object) throws JsonUtilException { 
        try { 
            return objectMapper.writeValueAsString(object); 
        } catch (IOException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static byte[] writeValueAsBytes(Object object) throws JsonUtilException { 
        try { 
            return objectMapper.writeValueAsBytes(object); 
        } catch (IOException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static <T> T readValue(String s, Class<T> clazz) throws JsonUtilException { 
        try { 
            if (StringUtils.hasText(s)) { 
                return objectMapper.readValue(s, clazz); 
            } else { 
                return null; 
            } 
        } catch (IOException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static <T> T readValue(byte[] data, Class<T> clazz) throws JsonUtilException { 
        try { 
            if (data!=null && data.length>0) { 
                return objectMapper.readValue(data, clazz); 
            } else { 
                return null; 
            } 
        } catch (IOException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static <T> T readValue(String s, TypeReference typeReference) { 
        try { 
            if (StringUtils.hasText(s)) { 
                return objectMapper.readValue(s, typeReference); 
            } else { 
                return null; 
            } 
        } catch (IOException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static <T> T readValue(byte[] data, TypeReference typeReference) { 
        try { 
            if (data!=null && data.length>0) { 
                return objectMapper.readValue(data, typeReference); 
            } else { 
                return null; 
            } 
        } catch (IOException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static <T> T convertValue(Object object, Class<T> toClazz) throws JsonUtilException { 
        try { 
            if (object == null) { 
                return null; 
            } else { 
                return objectMapper.convertValue(object, toClazz); 
            } 
        } catch (IllegalArgumentException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static JsonNode readTree(String s) { 
        try { 
            if (StringUtils.hasText(s)) { 
                return objectMapper.readTree(s); 
            } else { 
                return null; 
            } 
        } catch (JsonProcessingException e) { 
            throw new JsonUtilException(e); 
        } catch (IOException e) { 
            throw new JsonUtilException(e); 
        } 
    } 
 
    public static class JsonUtilException extends RuntimeException { 
 
        private static final long serialVersionUID = -4804245225960963421L; 
 
        public JsonUtilException(Throwable cause) { 
            super(cause); 
        } 
 
    } 
 
}
