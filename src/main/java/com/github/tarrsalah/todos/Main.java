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
package com.github.tarrsalah.todos;

import com.github.tarrsalah.todos.service.TaskService;
import java.util.logging.Logger;
import org.flywaydb.core.Flyway;

/**
 *
 * @author tarrsalah
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private static void flyMigrateDB() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:mem:todo;DB_CLOSE_DELAY=1000", "", "");
        flyway.migrate();
    }

    public static void main(String[] args) {
        flyMigrateDB();
        new TaskService().getAllTAsks().stream().forEach(task -> {
            LOG.info(task.getName());
        });
    }
}
