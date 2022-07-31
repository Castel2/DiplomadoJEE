package co.com.claro.services;

import co.com.claro.entity.Equipment;
import co.com.claro.entity.Sale;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class SaleServiceImpl implements Service<Sale>{

    @PersistenceContext(name = "claro")
    private EntityManager em;

    @Override
    public Sale save(Sale sale) {
        em.persist(sale);
        return sale;
    }

    @Override
    public Sale findById(Long id) {
        Query q =  em.createQuery("SELECT * FROM sale WHERE sale.id = :id");
        q.setParameter("id", id);
        Sale sale = (Sale) q.getSingleResult();
        return sale;
    }

    @Override
    public List<Sale> findAll() {
        Query q =  em.createQuery("SELECT e.name, e.quantity, c.name, v.name, t.type, v.city FROM sale s, equipment e, client c, vendor v" );
        List<Sale> sales =  q.getResultList();
        return sales;
    }

    @Override
    public void delete(Sale sale) {
        em.remove(sale);
    }
}
