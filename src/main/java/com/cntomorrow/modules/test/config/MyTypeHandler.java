package com.cntomorrow.modules.test.config;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * 必须继承BaseTypeHandler 或者实现 TypeHandler接口
 * 加了下面连个注解之后就不用在xml里面写jdbctyoe 和 javatype了
 */
@MappedJdbcTypes({JdbcType.DATE})
@MappedTypes({LocalDateTime.class})
public class MyTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime param, JdbcType jdbcType) throws SQLException {
        System.out.println("**********************入参转换************************************");
        preparedStatement.setTimestamp(i, Timestamp.valueOf(param));


    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        System.out.println("********************结果转换******************************");
        int a = resultSet.getType();
        Timestamp timestamp = resultSet.getTimestamp(s);
        return timestamp.toLocalDateTime();
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getTimestamp(i).toLocalDateTime();
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getTimestamp(i).toLocalDateTime();
    }
}

