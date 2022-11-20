import jdk.internal.util.xml.impl.Input;
import org.apache.log4j.spi.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;


public class Main {

    private static final Logger logger = Logger.getLogger(String.valueOf(Main.class));


    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        Properties props = new Properties();
        InputStream inputStream  = new FileInputStream("employee.properties");
        props.load(inputStream);

        Class.forName(props.getProperty("jdbc.className"));
        Connection con= DriverManager.getConnection(
                props.getProperty("jdbc.url"),props.getProperty("jdbc.user"),props.getProperty("jdbc.password"));

        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from employee");

        while(rs.next())
            logger.info(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
        con.close();


        logger.info("Hello world!");
    }
}