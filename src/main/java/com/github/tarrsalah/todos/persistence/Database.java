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
package com.github.tarrsalah.todos.persistence;

import javax.sql.DataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 *
 * @author tarrsalah
 */
public class Database {
    private static final String PACKAGE_MAPPERS = "com.github.tarrsalah.todos.data.mappers";

    private static DataSource getDataSource() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:mem:todo;DB_CLOSE_DELAY=1000";
        String username = "";
        String password = "";
        PooledDataSource dataSource = new PooledDataSource(driver, url, username, password);
        return dataSource;
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            DataSource dataSource = getDataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("developement", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
            configuration.addMappers(PACKAGE_MAPPERS);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return sqlSessionFactory;
    }
}
