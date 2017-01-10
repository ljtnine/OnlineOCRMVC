package com.onlineocr.nick.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by GrIfOn on 10.01.2017.
 */
@Configuration
@ComponentScan({ "com.onlineocr.nick" })
public class SpringRoot {
}


/*
 XML equivalent:
 <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd ">

	<context:component-scan base-package="com.mkyong.helloworld.service" />

</beans>
* */