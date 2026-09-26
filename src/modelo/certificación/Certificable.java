package modelo.certificación;

import modelo.Estudiante;
import org.w3c.dom.ls.LSOutput;

public interface Certificable {
    String ENTIDAD_EMISORA="UTN-FRM";

    public String generarCertificado(Estudiante estudiante);

}
