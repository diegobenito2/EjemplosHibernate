import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class Consultas {
    @Test
    public void baseDatos(){
        SessionFactory session = HibernateUtil.getSessionFactory();
    }
}
