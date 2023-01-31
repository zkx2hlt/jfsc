package com.cntomorrow.modules.test.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author speng
 * @Description
 * @date 2021/6/29 15:16
 */
//@MappedJdbcTypes({JdbcType.CHAR})
//@MappedTypes({Boolean.class})
public class MtBooleanTypeHandler extends BaseTypeHandler {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object param, JdbcType jdbcType) throws SQLException {
        System.out.println("**********************入参转换************************************");
        Boolean parameter = (Boolean) param;
        preparedStatement.setString(i, parameter ? "1" : "0");

    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        System.out.println("********************结果转换******************************");
        //把数字转为boolean
        return resultSet.getString(s).equals("1") ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
