import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DireccionDAO {
	private Connection conexion;

	public DireccionDAO(){
		this.conexion = ConexionBD.getInstancia().getConexion();
	}

	public boolean insertarDireccion(Direccion direccion){
		String sql = "INSERT INTO direcciones (calle, altura, alumno_id) VALUES (?, ?, ?)";
		try{
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1,direccion.getCalle());
			stmt.setInt(2,direccion.getAltura());
			stmt.setInt(3,direccion.getAlumnoId());
			stmt.executeUpdate();
			stmt.close();
			return true;

		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}








}
