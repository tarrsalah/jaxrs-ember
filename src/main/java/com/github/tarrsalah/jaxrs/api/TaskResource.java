/*
 * The MIT License
 *
 * Copyright 2014 tarrsalah.
 *
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * Permission is hereby granted, free of charge, to any person obtaining a copy
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
package com.github.tarrsalah.jaxrs.api;

import com.github.tarrsalah.jaxrs.core.Task;
import com.github.tarrsalah.jaxrs.core.Tasks;
import com.github.tarrsalah.jaxrs.jdbi.TaskDAO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.exceptions.DBIException;

/**
 *
 * @author tarrsalah
 */
@Path("/tasks")
public class TaskResource {

    @Inject
    private DBI dbi;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasks() {
        final TaskDAO taskDAO = dbi.onDemand(TaskDAO.class);
        final List<Task> tasks = taskDAO.getAll();
        return Response.status(Response.Status.OK)
                .entity(new Tasks(tasks))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Task task) {
        try {
            final TaskDAO taskDAO = dbi.onDemand(TaskDAO.class);
            int id = taskDAO.insert(task);
            System.out.println(id);
            task.setId(id);
        } catch (DBIException exception) {
            System.out.println(exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.CREATED)
                .entity(task)
                .build();
    }
}
