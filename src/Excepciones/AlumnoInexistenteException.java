package Excepciones;

public class AlumnoInexistenteException extends Exception {
	public AlumnoInexistenteException(int id) {
		super("El alumno con ID " + id + " no existe.");
	}
}
