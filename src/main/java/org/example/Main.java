import java.sql.SQLException;
import java.util.List;
import com.tiendaropa.model.Categoria;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import com.tiendaropa.util.DatabaseConnector;


public class Main {
    public static void main(String[] args) {
        try {
            // Usar getJdbi() de DatabaseConnector en lugar de la conexión directa
            Jdbi jdbi = DatabaseConnector.getJdbi();

            // Prueba si funciona
            boolean conexionExitosa = DatabaseConnector.testConnection();
            System.out.println("Conexión exitosa: " + conexionExitosa);

            // Aquí puedes usar jdbi para ejecutar consultas
            // Por ejemplo:
            // List<Categoria> categorias = jdbi.withHandle(handle ->
            //    handle.createQuery("SELECT * FROM categorias")
            //          .mapToBean(Categoria.class)
            //          .list());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}