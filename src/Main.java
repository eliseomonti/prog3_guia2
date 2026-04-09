import Excepciones.AlumnoInexistenteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AlumnoDAO alumnoDAO = new AlumnoDAO();
		DireccionDAO direccionDAO = new DireccionDAO();

		int opcion;
		int subopcion;

		do {
			System.out.println("\n===== MENU =====");
			System.out.println("1. Insertar alumno");
			System.out.println("2. Insertar dirección");
			System.out.println("3. Listar todos los alumnos");
			System.out.println("4. Listar todas las direcciones");
			System.out.println("5. Listar todas las direcciones de un alumno especifico");
			System.out.println("6. Actualizar edad de un alumno");
			System.out.println("7. Eliminar un alumno");
			System.out.println("8. Eliminar una dirección");
			System.out.println("0. Salir");
			System.out.print("Elegí una opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion){
				case 1:

					System.out.print("Nombre: ");
					String nombre = scanner.nextLine();
					System.out.print("Apellido: ");
					String apellido = scanner.nextLine();
					System.out.print("Edad: ");
					int edad = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Email: ");
					String email = scanner.nextLine();

					Alumno alumno = new Alumno(nombre,apellido,edad,email);
					if(alumnoDAO.insertarAlumno(alumno)){
						System.out.println("Alumno insertado correctamente");
					}else{
						System.out.println("Error al insertar el alumno");
					}
					break;
				case 2:
					System.out.println("ID del alumno:");
					int alumnoId = scanner.nextInt();
					scanner.nextLine();

					try {
						alumnoDAO.existeAlumno(alumnoId);
					}catch (AlumnoInexistenteException e) {
						System.out.println(e.getMessage());
						break;
					}

					System.out.print("Calle: ");
					String calle = scanner.nextLine();
					System.out.print("Altura: ");
					int altura = scanner.nextInt();
					scanner.nextLine();

					Direccion direccion = new Direccion(calle,altura,alumnoId);

					if (direccionDAO.insertarDireccion(direccion)){
						System.out.println("Direccion insertada correctamente");
					}else {
						System.out.println("Error al insertar la direccion");
					}
					break;
				case 3:
					System.out.println("1. Solo alumnos");
					System.out.println("2. Alumnos con direcciones");
					System.out.print("Elegí una opción: ");
					subopcion = scanner.nextInt();
					scanner.nextLine();

					List<Alumno> alumnos = alumnoDAO.listarAlumnos();

					if (alumnos.isEmpty()) {
						System.out.println("No hay alumnos registrados.");
						break;
					}

					for (Alumno a : alumnos) {
						System.out.println(a);
						if(subopcion == 2){
							List<Direccion> direcciones = direccionDAO.listarDireccionesPorAlumno(a.getId());
							if (direcciones.isEmpty()) {
								System.out.println("  Sin direcciones registradas.");
							} else {
								for (Direccion d : direcciones) {
									System.out.println("  → " + d);
								}
							}
						}
					}
					break;
				case 4:
					List<Direccion> direcciones = direccionDAO.listarDirecciones();

					for (Direccion d : direcciones){
						System.out.println(d);
					}
					break;
				case 5:
					System.out.println("Ingrese id del alumno:");
					int id = scanner.nextInt();
					scanner.nextLine();
					Alumno a = alumnoDAO.mostrarAlumno(id);
					System.out.println(a);
					List<Direccion> direcciones2 = direccionDAO.listarDireccionesPorAlumno(id);
					for (Direccion d : direcciones2){
						System.out.println(d);
					}
					break;
				case 0:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opcion invalida");
			}

		}while(opcion != 0);



	}

}
