package ucu.edu.aed.Ejercicio13;
import java.util.Objects;


public class Alumno {

    private int id;
    private String fullName;
    private String email;

    public Alumno(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Alumno alumno = (Alumno) o;

        return id == alumno.id
                && Objects.equals(fullName, alumno.fullName)
                && Objects.equals(email, alumno.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email);
    }
}