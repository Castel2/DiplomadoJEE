package co.com.claro.services;

import co.com.claro.entity.Vendor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class VendorServiceImpl implements Service<Vendor> {

    @PersistenceContext(name = "claro")
    private EntityManager em;

    @Override
    public Vendor save(Vendor vendor) {
        em.persist(vendor);
        return vendor;
    }

    @Override
    public Vendor findById(Long id) {
        Query q =  em.createQuery("SELECT * FROM vendor WHERE vendor.id = :id");
        q.setParameter("id", id);
        Vendor vendor = (Vendor) q.getSingleResult();
        return vendor;
    }

    @Override
    public List<Vendor> findAll() {
        Query q =  em.createQuery("SELECT * FROM vendor WHERE");
        List<Vendor> vendors = q.getResultList();
        return vendors;
    }

    @Override
    public void delete(Vendor vendor) {
        em.remove(vendor);
    }
}
