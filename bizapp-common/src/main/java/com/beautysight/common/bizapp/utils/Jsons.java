/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package com.beautysight.common.bizapp.utils;

import com.beautysight.common.bizapp.ex.JsonHandlingException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;

/**
 * @author chenlong
 * @since 1.0
 */
public class Jsons {

    private static final ObjectMapper mapper = new CustomObjectMapper();

    /*static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }*/

    public static String toJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonHandlingException(String.format("Convert %s obj to json string", obj.getClass()), e);
        }
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new JsonHandlingException(String.format("Read json file [%s] as %s",
                    jsonString, clazz.getName()), e);
        } catch (IOException e) {
            throw new JsonHandlingException(String.format("Read json file: %s", jsonString), e);
        }
    }

    public static <T> T toObject(File srcJsonFile, Class<T> clazz) {
        try {
            return mapper.readValue(srcJsonFile, clazz);
        } catch (JsonProcessingException e) {
            throw new JsonHandlingException(String.format("Read json file [%s] as %s",
                    srcJsonFile.getAbsolutePath(), clazz.getName()), e);
        } catch (IOException e) {
            throw new JsonHandlingException(String.format("Read json file: %s", srcJsonFile.getAbsolutePath()), e);
        }
    }

    public static <T> T toObject(File srcJsonFile, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(srcJsonFile, typeReference);
        } catch (JsonProcessingException e) {
            throw new JsonHandlingException(String.format("Read json file [%s] as %s",
                    srcJsonFile.getAbsolutePath(), typeReference.getType()), e);
        } catch (IOException e) {
            throw new JsonHandlingException(String.format("Read json file: %s", srcJsonFile.getAbsolutePath()), e);
        }
    }

    public static <T> T toObject(Reader reader, Class<T> type) {
        try {
            return mapper.readValue(reader, type);
        } catch (JsonProcessingException e) {
            throw new JsonHandlingException(String.format("Read from reader as %s", type.getName()), e);
        } catch (IOException e) {
            throw new JsonHandlingException(String.format("Read from reader as %s", type.getName()), e);
        }
    }

    public static class CustomObjectMapper extends ObjectMapper {

        public CustomObjectMapper() {
            super();

            final SimpleModule customModule = new SimpleModule("CustomSimpleModule", Version.unknownVersion());
            customModule.addSerializer(Date.class, new CustomDateSerializer());
            this.registerModule(customModule);

            this.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
            this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            // 反序列化时忽略不认识的属性
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            /*this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
                @Override
                public void serialize(
                        Object value,
                        JsonGenerator gen,
                        SerializerProvider sp) throws IOException, JsonProcessingException {
                    gen.writeString("");
                }
            });*/
        }

    }

    public static class CustomDateSerializer extends JsonSerializer<Date> {

        private static final DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // 统一转换为UTC时间
            gen.writeString(fmt.print(new DateTime(value, DateTimeZone.UTC)));
        }

    }
}
