module org.oop.lostfound {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Apache HttpClient modules
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpmime;
    
    // Jackson modules
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    
    // Java base modules
    requires java.base;
    requires java.desktop;
    requires javafx.graphics;

    opens org.oop.lostfound.controller to javafx.fxml;
    opens org.oop.lostfound.model to javafx.base, javafx.fxml;
    opens org.oop.lostfound to javafx.fxml;
    exports org.oop.lostfound;
    exports org.oop.lostfound.controller;
    exports org.oop.lostfound.model;
    exports org.oop.lostfound.dao;
    exports org.oop.lostfound.config;
    
}