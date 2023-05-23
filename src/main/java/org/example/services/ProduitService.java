package org.example.services;

import org.example.Classes.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;


public class ProduitService extends BaseService implements Repository<Produit> {

    public ProduitService(){
        super();
    }

    @Override
    public boolean create(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Produit findById(int id) {
        Produit produit = null;
        session = sessionFactory.openSession();
        produit = (Produit) session.get(Produit.class, id);
        session.close();
        return produit;
    }

    @Override
    public List<Produit> findAll() {
        List<Produit> produits = null;
        session = sessionFactory.openSession();
        produits = session.createQuery("from Produit", Produit.class).list();
        session.close();
        return produits;

    }

    public List<Produit> findByPrice (Double price){
        List<Produit> produits = null;
        session= sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where prix>:price", Produit.class);
        produitQuery.setParameter("price",price);
        produits = produitQuery.list();
        session.close();
        return produits;
    }

    public List<Produit> findBetweenDate (Date dateStart,Date dateEnd){
        List<Produit> produits = null;
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where dateAchat between :dateStart and :dateEnd", Produit.class);
        produitQuery.setParameter("dateStart",dateStart);
        produitQuery.setParameter("dateEnd",dateEnd);
        produits = produitQuery.list();
        session.close();
        return produits;
    }
}