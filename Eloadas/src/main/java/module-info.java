module com.example.eloadas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens com.example.eloadas.hulladekSzallitas to org.hibernate.orm.core, javafx.base;
    opens com.example.eloadas to javafx.fxml;
    exports com.example.eloadas;
}