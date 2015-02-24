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
package com.github.tarrsalah.jaxrs.core;

import com.github.tarrsalah.jaxrs.db.DB;
import com.github.tarrsalah.jaxrs.db.TaskDAO;
import java.util.List;

/**
 *
 * @author tarrsalah
 */
public class TaskService {

    public Tasks getAll() {
        List<Task> tasks;
        TaskDAO taskDAO = DB.dbi.open(TaskDAO.class);
        tasks = taskDAO.getAll();
        return new Tasks(tasks);
    }

    public void insert(Task task) {
//        try (SqlSession sqlSession = Database.getSqlSessionFactory().openSession()) {
//            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
//            taskMapper.insert(task);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
