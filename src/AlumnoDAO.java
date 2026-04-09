import Excepciones.AlumnoInexistenteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
	private Connection conexion;

	public AlumnoDAO(){
		this.conexion = ConexionBD.getInstancia().getConexion();
	}

	public boolean insertarAlumno(Alumno alumno){
		String sql = "INSERT INTO alumnos (nombre, apellido, edad, email) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1,alumno.getNombre());
			stmt.setString(2,alumno.getApellido());
			stmt.setInt(3,alumno.getEdad());
			stmt.setString(4, alumno.getEmail());
			stmt.executeUpdate();
			stmt.close();
			return true;
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	public void existeAlumno(int id) throws AlumnoInexistenteException {
		String sql = "SELECT id FROM alumnos WHERE id = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()){
				throw new AlumnoInexistenteException(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Alumno> listarAlumnos() {
		List<Alumno> alumnos = new ArrayList<>();
		String sql = "SELECT * FROM alumnos";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Alumno a = new Alumno(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getInt("edad"),
						rs.getString("email")
				);
				alumnos.add(a);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}

	public Alumno mostrarAlumno(int id) {
		String sql = "SELECT * FROM alumnos WHERE id = ?";
		Alumno a = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				a = new Alumno(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getInt("edad"),
						rs.getString("email")
				);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public boolean actualizarNombre(int id, String nombre) throws AlumnoInexistenteException{
		return actualizar("UPDATE alumnos SET nombre = ? WHERE id = ?", nombre, id);
	}

	public boolean actualizarApellido(int id, String apellido) throws AlumnoInexistenteException{
		return actualizar("UPDATE alumnos SET apellido = ? WHERE id = ?", apellido, id);
	}

	public boolean actualizarEdad(int id, int edad) throws AlumnoInexistenteException{
		String sql = "UPDATE alumnos SET edad = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, edad);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizarEmail(int id, String email)throws AlumnoInexistenteException {
		return actualizar("UPDATE alumnos SET email = ? WHERE id = ?", email, id);
	}

	private boolean actualizar(String sql, String valor, int id) {
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

	public boolean eliminarAlumno(int id) throws AlumnoInexistenteException {
		existeAlumno(id);
		String sql = "DELETE FROM alumnos WHERE id = ?";
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
