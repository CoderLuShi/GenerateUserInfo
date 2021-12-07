package dao;

import org.apache.commons.dbutils.ResultSetHandler;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	//优化查询
	public Object query(String sql,ResultSetHandler<?> rsh,Object... params) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//定义一个返回结果
		Object obj=null;
		try {
			//获得连接
			conn=JDBCUtil.getConnection();
			//预编译sql
			pstmt=conn.prepareStatement(sql);
			//将参数设置进去
			for(int i=0 ; params != null && i < params.length ; i++) {
				pstmt.setObject(i+1, params[i]);
			}
			//发送sql
			rs=pstmt.executeQuery();
			//让调用者去处理结果集
			obj=rsh.handle(rs);
		} catch (SQLException e) {
			//释放
			return new Exception(e.getMessage());
		}finally {
			JDBCUtil.getClose(pstmt, rs, conn);
		}
		return obj;
	};

}
