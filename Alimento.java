import java.time.LocalDate;

public class Alimento {
    public String descripcion;
    public LocalDate fechaExpiracion;
    public String fundacion;

    public Alimento(String descripcion, LocalDate fechaExpiracion, String fundacion) {
        this.descripcion = descripcion;
        this.fechaExpiracion = fechaExpiracion;
        this.fundacion = fundacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public String getFundacion() {
        return fundacion;
    }

    @Override
    public String toString() {
        return descripcion + " (expira: " + fechaExpiracion + ")";
    }
}
