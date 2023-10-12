module com.example.endterm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.postgresql.jdbc;


    opens com.example.endterm to javafx.fxml;
    exports com.example.endterm;
}