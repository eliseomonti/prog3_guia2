import Excepciones.DireccionInexistenteException;

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

	public List<Direccion> listarDirecciones (){
		List<Direccion> direcciones = new ArrayList<>();

		String sql = "SELECT * FROM direcciones";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return direcciones;
	}

	public boolean actualizarCalle(int id, String calle) throws DireccionInexistenteException {
		return actualizar("UPDATE direcciones SET calle = ? WHERE id = ?", calle, id);
	}

	public boolean actualizarAltura(int id, int altura) {
		String sql = "UPDATE direcciones SET altura = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, altura);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean actualizar(String sql, String valor, int id) throws DireccionInexistenteException {
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, valor);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void existeDireccion(int id) throws DireccionInexistenteException {
		String sql = "SELECT id FROM direcciones WHERE id = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new DireccionInexistenteException(id);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean eliminarDireccion(int id) throws DireccionInexistenteException {
		existeDireccion(id); // si no existe lanza la excepción
		String sql = "DELETE FROM direcciones WHERE id = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
