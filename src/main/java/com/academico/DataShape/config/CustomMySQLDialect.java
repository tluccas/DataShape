package com.academico.DataShape.config;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes; 

public class CustomMySQLDialect extends MySQLDialect {

    public CustomMySQLDialect() {
        super();
    }

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        super.contributeFunctions(functionContributions);

        // MONTHNAME (retorna o nome do mês como String)
        functionContributions.getFunctionRegistry().register(
                "monthname",
                new StandardSQLFunction("monthname", StandardBasicTypes.STRING)
        );

        // YEAR (retorna o ano como Integer)
        functionContributions.getFunctionRegistry().register(
                "year",
                new StandardSQLFunction("year", StandardBasicTypes.INTEGER)
        );

        // MONTH (retorna o número do mês para ordenação)
        functionContributions.getFunctionRegistry().register(
                "month",
                new StandardSQLFunction("month", StandardBasicTypes.INTEGER)
        );
    }
}
