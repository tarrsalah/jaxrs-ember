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

import com.github.tarrsalah.jaxrs.api.config.Resources;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import org.flywaydb.core.Flyway;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

/**
 *
 * @author tarrsalah
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    public static final int HTTP_PORT = 3003;
    public static final URI BASE_URI = getBaseURI();

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost").port(getPort(HTTP_PORT)).build();
    }

    private static HttpServer getHttpServer() throws IOException {
        HttpServer server = GrizzlyHttpServerFactory
                .createHttpServer(UriBuilder
                        .fromUri("http://localhost/")
                        .port(HTTP_PORT)
                        .build(),
                        new Resources());
        return server;
    }

    public static void main(String[] args) throws IOException {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:mem:todo;DB_CLOSE_DELAY=1000", "", "");
        flyway.migrate();

        HttpServer httpServer = getHttpServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));
        System.in.read();
        httpServer.shutdown();
    }
}