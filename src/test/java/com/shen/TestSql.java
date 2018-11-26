package com.shen;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class TestSql {
    public static void main(String[] args) {
        String sql = "insert into user('name') values(1,'zhangsan')";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement statement = parser.parseStatement();
        MySqlInsertStatement insert = (MySqlInsertStatement)statement;
        System.out.println(insert);
    }
}
