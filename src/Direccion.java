public class Direccion {

	private int id;
	private String calle;
	private int altura;
	private int alumnoId;

	public Direccion(String calle, int altura, int alumnoId) {
		this.calle = calle;
		this.altura = altura;
		this.alumnoId = alumnoId;
	}

	public Direccion(int id, String calle, int altura, int alumnoId) {
		this.id = id;
		this.calle = calle;
		this.altura = altura;
		this.alumnoId = alumnoId;
	}

	public int getId() {
		return id;
	}
	public String getCalle() {
		return calle;
	}
	public int getAltura() {
		return altura;
	}
	public int getAlumnoId() {
		return alumnoId;
	}

	@Override
	public String toString() {
		return "Direccion{" +
				"id=" + id +
				", calle='" + calle + '\'' +
				", altura=" + altura +
				", alumnoId=" + alumnoId +
				'}';
	}
}
