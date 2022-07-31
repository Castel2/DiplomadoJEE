package co.com.claro.services;

import co.com.claro.entity.TypeVendor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class TypeVendorServiceImpl implements Service<TypeVendor>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public TypeVendor save(TypeVendor typeVendor) {
        em.persist(typeVendor);
        return typeVendor;
    }

    @Override
    public TypeVendor findById(Long id) {
        Query q =  em.createQuery("SELECT * FROM type_vendor WHERE type_vendor.id = :id");
        q.setParameter("id", id);
        TypeVendor typeVendor = (TypeVendor) q.getSingleResult();
        return typeVendor;
    }

    @Override
    public List<TypeVendor> findAll() {
        Query q =  em.createQuery("SELECT * FROM type_vendor");
        List<TypeVendor> typeVendors = q.getResultList();
        return typeVendors;
    }

    @Override
    public void delete(TypeVendor typeVendor) {
        em.remove(typeVendor);
    }
}
