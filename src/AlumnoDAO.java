import Excepciones.AlumnoInexistenteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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















}
