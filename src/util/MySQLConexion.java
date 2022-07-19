package util;
import java.sql.*;//n
public class MySQLConexion {
	public static Connection getConexion(){ 
		Connection con = null;
		try { Class.forName("com.mysql.jdbc.Driver"); 
		//String url = "jdbc:mysql://mysql5031.site4now.net/db_a6a425_sistema"; 
                String url = "jdbc:mysql://localhost:3306/bd_clinica_v2"; 
		String usr = "root";
		String psw = ""; 
		con = DriverManager.getConnection(url,usr,psw); 
		//System.out.println("conexion ok");
		} catch (ClassNotFoundException ex)
		{ System.out.println("No hay Driver!!"); } 
		catch (SQLException ex) { System.out.println("Error con la BD "); }
		return con; 
		}
}

