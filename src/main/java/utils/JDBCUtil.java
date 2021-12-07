package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
    private static DataSource ds;
    static{
        try {
            Properties pro=new Properties();
            pro.load(JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    public static void getClose(Statement stmt, Connection conn){
        try {
			if (stmt!=null){
			    stmt.close();
			}
			if (conn!=null){
			    conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void getClose(ResultSet res,Connection conn){
        try {
			if (res!=null){
			    res.close();
			}
			if (conn!=null){
			    conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void getClose(Statement stmt,ResultSet res,Connection conn) {
    	try {
			if(stmt!=null) {
				stmt.close();
			}
			if (res!=null){
			    res.close();
			}
			if (conn!=null){
			    conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static DataSource getDataSource(){
        return ds;
    }

}
