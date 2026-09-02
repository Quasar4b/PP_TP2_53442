public class Taller extends Actividad{

    private boolean requiereNotebook;

    //--------------------CONSTRUCTORES--------------------

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    //--------------------METODOS--------------------

    @Override
    public double calcularCostoMateriales(){
        if(requiereNotebook) {
            return 5000;
        } else {
            return 2000;
        }
    }
    @Override
    public String getTipo(){
        return "Taller";
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }
}
