package driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
    private String url="jdbc:oracle:thin:@192.168.0.78:1521:orcl";
    private String username="system";
    private String pass="1111";
    protected Connection connection=null;


    public boolean getConnection() {
        try {
            connection= DriverManager.getConnection(url , username , pass);
            return true;
        }catch(Exception e) {
            e.getMessage();
        }
        return false;
    }

    //자원반납
    public void close() {
        try {
            if(connection!=null) {
                connection.close();
            }
        }catch(Exception e) {
            e.getMessage();
        }
    }
}
