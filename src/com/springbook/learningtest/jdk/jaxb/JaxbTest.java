package com.springbook.learningtest.jdk.jaxb;

import com.springbook.user.sqlservice.jaxb.SqlType;
import com.springbook.user.sqlservice.jaxb.Sqlmap;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JaxbTest {
    @Test
    public void readSqlmap() throws JAXBException, IOException {
        String contextPath = Sqlmap.class.getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(contextPath);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("sqlmap.xml"));

        List<SqlType> sqList = sqlmap.getSql();

        assertThat(sqList.size(), is(3));
        assertThat(sqList.get(0).getKey(), is("add"));
        assertThat(sqList.get(0).getValue(), is("insert"));
        assertThat(sqList.get(1).getKey(), is("get"));
        assertThat(sqList.get(1).getValue(), is("select"));
        assertThat(sqList.get(2).getKey(), is("delete"));
        assertThat(sqList.get(2).getValue(), is("delete"));
    }
}
