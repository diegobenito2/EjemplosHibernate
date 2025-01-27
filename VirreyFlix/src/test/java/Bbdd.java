import com.example.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class Bbdd {
    @Test
    public void baseDatos(){
        SessionFactory session = HibernateUtil.getSessionFactory();
    }
}
