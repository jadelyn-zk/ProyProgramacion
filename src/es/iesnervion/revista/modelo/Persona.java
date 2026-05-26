package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Clase base para representar a cualquier persona en el sistema.
 */
public abstract class Persona {

    // El DNI de la persona
    private String dni;

    // Su nombre completo
    private String nombre;

    // Su número de teléfono
    private String tlf;

    // Su dirección de email
    private String email;

    // Su edad
    private int edad;

    /**
     * Constructor por defecto.
     */
    public Persona() {
    }

    /**
     * Constructor con todos los atributos.
     * 
     * @param dni    El DNI.
     * @param nombre El nombre.
     * @param tlf    El teléfono.
     * @param email  El email.
     * @param edad   La edad.
     */
    public Persona(String dni, String nombre, String tlf, String email, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.tlf = tlf;
        this.email = email;
        this.edad = edad;
    }

    // -- Getters --

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }

    // -- Setters --

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Compara personas por su DNI.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tlf='" + tlf + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                '}';
    }
}
