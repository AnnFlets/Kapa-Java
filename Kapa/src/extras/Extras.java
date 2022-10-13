package extras;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Extras {

    private String fechaCambiar;

    public String cambiarFormatoAFechaVista(String fecha) {
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formato.parse(fecha);
            String fechaFormato = new SimpleDateFormat("dd/MM/yyyy").format(date);
            fechaCambiar = fechaFormato;
        } catch (Exception e) {
            System.err.println("[Error-Cambio-Fecha]: " + e.getMessage());
        }
        return fechaCambiar;
    }

    public String cambiarFormatoAFechaBD(String fecha) {
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formato.parse(fecha);
            String fechaFormato = new SimpleDateFormat("yyyy-MM-dd").format(date);
            fechaCambiar = fechaFormato;
        } catch (Exception e) {
            System.err.println("[Error-Cambio-Fecha]: " + e.getMessage());
        }
        return fechaCambiar;
    }

    public String devolverFechaActual() {
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return fecha;
    }
}
