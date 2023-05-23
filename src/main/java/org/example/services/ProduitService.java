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
    public void begin() {
        session = sessionFactory.openSession();
    }

    @Override
    public void end() {
        session.close();
        sessionFactory.close();
    }

    @Override
    public boolean create(Produit o) {
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(int id) {
        session.beginTransaction();
        Produit p = session.get(Produit.class,id);
        if(p != null){
            session.delete(p);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }
/*
    public boolean delete2(Produit o) {
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }*/

    @Override
    public Produit findById(int id) {
        Produit produit = null;
        produit = (Produit) session.get(Produit.class, id);
        return produit;
    }

    @Override
    public List<Produit> findAll() {
        List<Produit> produits = null;
        produits = session.createQuery("from Produit", Produit.class).list();
        return produits;

    }

    public List<Produit> findByPrice (Double price){
        List<Produit> produits = null;
        Query<Produit> produitQuery = session.createQuery("from Produit where prix>:price", Produit.class);
        produitQuery.setParameter("price",price);
        produits = produitQuery.list();
        return produits;
    }

    public List<Produit> findBetweenDate (Date dateStart,Date dateEnd){
        List<Produit> produits = null;
        Query<Produit> produitQuery = session.createQuery("from Produit where dateAchat between :dateStart and :dateEnd", Produit.class);
        produitQuery.setParameter("dateStart",dateStart);
        produitQuery.setParameter("dateEnd",dateEnd);
        produits = produitQuery.list();
        return produits;
    }

    public List<Produit> findByQuantity (int quantity){
        List<Produit> produits = null;
        Query<Produit> queryProduits= session.createQuery("from Produit where stock < :quantity",Produit.class);
        queryProduits.setParameter("quantity",quantity);
        return produits = queryProduits.list();
    }
}