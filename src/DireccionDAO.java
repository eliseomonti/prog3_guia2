import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List<Direccion> listarDireccionesPorAlumno (int alumnoId){
		List<Direccion> direcciones = new ArrayList<>();
		String sql = "SELECT * FROM direcciones WHERE alumno_id = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1,alumnoId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Direccion d = new Direccion(
						rs.getInt("id"),
						rs.getString("calle"),
						rs.getInt("altura"),
						rs.getInt("alumno_id")
				);
				direcciones.add(d);
			}
			rs.close();
			stmt.close();

		}catch (SQLException e){
			e.printStackTrace();
		}
		return direcciones;
	}








}
