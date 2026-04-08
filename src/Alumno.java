public class Alumno {

	private int id;
	private String nombre;
	private String apellido;
	private int edad;
	private String email;

	public Alumno(String nombre, String apellido, int edad, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.email = email;
	}

	public Alumno(int id, String nombre, String apellido, int edad, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public int getEdad() {
		return edad;
	}
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Alumno{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", edad=" + edad +
				", email='" + email + '\'' +
				'}';
	}
}
