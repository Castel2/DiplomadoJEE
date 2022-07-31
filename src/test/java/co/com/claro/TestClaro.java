package co.com.claro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestClaro {

    @Test
    public void testCreateDB(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("claro");
        EntityManager em = emf.createEntityManager();
        assertNotNull(em);
        em.close();

    }
}
