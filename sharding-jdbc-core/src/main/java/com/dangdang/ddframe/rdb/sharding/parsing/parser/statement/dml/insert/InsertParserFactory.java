/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.parsing.parser.statement.dml.insert;

import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.constant.DatabaseType;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.LexerEngine;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.mysql.MySQLInsertParser;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.oracle.OracleInsertParser;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.postgresql.PostgreSQLInsertParser;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.sqlserver.SQLServerInsertParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Insert语句解析器工厂.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InsertParserFactory {
    
    /**
     * 创建Insert语句解析器.
     *
     * @param dbType 数据库类型
     * @param shardingRule 分库分表规则配置
     * @param lexerEngine 解析器
     * @return Insert语句解析器
     */
    public static AbstractInsertParser newInstance(final DatabaseType dbType, final ShardingRule shardingRule, final LexerEngine lexerEngine) {
        switch (dbType) {
            case H2:
            case MySQL:
                return new MySQLInsertParser(shardingRule, lexerEngine);
            case Oracle:
                return new OracleInsertParser(shardingRule, lexerEngine);
            case SQLServer:
                return new SQLServerInsertParser(shardingRule, lexerEngine);
            case PostgreSQL:
                return new PostgreSQLInsertParser(shardingRule, lexerEngine);
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database [%s].", dbType));
        }
    }
}