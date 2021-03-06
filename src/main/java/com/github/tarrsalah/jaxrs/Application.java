/*
 * The MIT License
 *
 * Copyright 2014 tarrsalah.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.tarrsalah.jaxrs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.logging.Logger;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.filter.LoggingFilter;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.skife.jdbi.v2.DBI;

/**
 *
 * @author tarrsalah
 */
public class Application extends ResourceConfig {

    public static final String URL = "jdbc:sqlite:file:target/todo";

    public Application() {
        packages("com.github.tarrsalah.jaxrs.api");
        register(new HK2Binder());
        register(JacksonFeature.class);
        register(new ObjectMapperProvider());
        register(new LoggingFilter(Logger.global, true));
        property(ServerProperties.TRACING, "ALL");
    }

}

class HK2Binder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(new DBI(Application.URL)).to(DBI.class);
    }
}

@Provider
class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public ObjectMapperProvider() {
        super();
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Override
    public ObjectMapper getContext(@SuppressWarnings(value = "rawtypes") Class type) {
        return objectMapper;
    }
}
