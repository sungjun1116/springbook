package com.springbook.user.sqlservice;

import com.springbook.SqlServiceContext;
import org.springframework.context.annotation.Import;

@Import(SqlServiceContext.class)
public @interface EnableSqlService {
}
