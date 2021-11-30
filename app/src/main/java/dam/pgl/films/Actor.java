package dam.pgl.films;

public class Actor {

    private String imagen,nombre,nombreSecundario;


    public Actor(String imagen, String nombre, String nombreSecundario) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.nombreSecundario = nombreSecundario;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreSecundario() {
        return nombreSecundario;
    }

    public void setNombreSecundario(String nombreSecundario) {
        this.nombreSecundario = nombreSecundario;
    }
}