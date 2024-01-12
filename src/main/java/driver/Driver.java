package driver;

public class Driver {
    public static Driver dbDriver=null;
    private Driver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(Exception e) {
            e.getMessage();
        }
    }
    public static Driver getInstance() {
        if(dbDriver == null) {
            dbDriver= new Driver();
        }
        return dbDriver;
    }
}
