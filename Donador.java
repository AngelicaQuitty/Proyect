import java.util.ArrayList;

public class Donador {
    public String nombre;
    public String contacto;
    public ArrayList<Alimento> alimentos;

    public Donador(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.alimentos = new ArrayList<>();
    }



    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    @Override
    public String toString() {
        return nombre + " (" + contacto + ")";
    }
}

