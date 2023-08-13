module EngineersApp {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.web;
	requires spring.context;
	requires spring.jdbc;
	requires spring.aop;
	requires spring.beans;
	requires spring.core;
	requires spring.expression;
	requires spring.jcl;
	requires spring.tx;
	requires org.apache.derby.client;
	requires org.apache.derby.commons;
	requires org.apache.derby.engine;
	requires org.apache.derby.tools;
	requires org.apache.commons.pool2;
	exports mainpackage;
	exports mainpackage.customer;
	exports mainpackage.dao;
	
	opens application to javafx.graphics, javafx.fxml;
}
