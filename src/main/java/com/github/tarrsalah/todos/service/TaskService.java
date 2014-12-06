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
package com.github.tarrsalah.todos.service;

import com.github.tarrsalah.todos.persistence.Database;
import com.github.tarrsalah.todos.persistence.mappers.TaskMapper;
import com.github.tarrsalah.todos.model.Task;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author tarrsalah
 */
public class TaskService {

    public List<Task> getAllTAsks() {
        List<Task> tasks = null;

        try (SqlSession sqlSession = Database.getSqlSessionFactory().openSession()) {
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
            tasks = taskMapper.getAllTasks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }
}
