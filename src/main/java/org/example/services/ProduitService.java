package org.example.services;

import org.example.Classes.Commentaire;
import org.example.Classes.Image;
import org.example.Classes.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.*;


public class ProduitService extends BaseService implements Repository<Produit> {

    public ProduitService() {
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
    public boolean createImage(Image o) {
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }
    public boolean createCommentaire(Commentaire o) {
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
        Produit p = session.get(Produit.class, id);
        if (p != null) {
            session.delete(p);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }
    @Override
    public Produit findById(int id) {
        return session.get(Produit.class, id);
    }

    @Override
    public List<Produit> findAll() {
        return session.createQuery("from Produit", Produit.class).list();
    }

    public List<Produit> findByPrice(Double price) {
        Query<Produit> produitQuery = session.createQuery("from Produit where prix>:price", Produit.class);
        produitQuery.setParameter("price", price);
        return  produitQuery.list();
    }

    public List<Produit> findBetweenDate(Date dateStart, Date dateEnd) {
        Query<Produit> produitQuery = session.createQuery("from Produit where dateAchat between :dateStart and :dateEnd", Produit.class);
        produitQuery.setParameter("dateStart", dateStart);
        produitQuery.setParameter("dateEnd", dateEnd);
        return produitQuery.list();
    }

    public List<String> findByQuantity(int quantity) {
        Query query = session.createQuery("select id,reference from Produit where stock < :quantity");
        query.setParameter("quantity", quantity);
        List produits = query.list();
        List<String> result = new ArrayList<>();
        for (Object o: produits) {
            Object[] p= (Object[]) o;
            result.add("id : "+p[0]+" / reference : "+p[1]);
        }
        return result;
    }

    public Long stockValueByBrand(String brand) {
        Query queryStock = session.createQuery("select sum (stock) from Produit as p where p.marque = :brand");
        queryStock.setParameter("brand", brand);
        return (Long)  queryStock.uniqueResult();
    }

    public Double meanPrice() {
        return (Double) session.createQuery("select avg(prix) from Produit as p ").uniqueResult();
    }

    public List<Produit> findByBrand(String brand) {
        Query<Produit> queryProduits = session.createQuery("from Produit as p where p.marque = :brand",Produit.class);
        queryProduits.setParameter("brand", brand);
        return queryProduits.list();
    }

    public boolean deleteByBrand (String brand){
        session.beginTransaction();
        Query deleteQuery = session.createQuery("delete from Produit as p where p.marque = :brand");
        deleteQuery.setParameter("brand",brand);
        int result = deleteQuery.executeUpdate();
        session.getTransaction().commit();
        return result >= 1;
    }
    public Double findValueByBrand (String brand){
        Query<Double> queryStock = session.createQuery("select sum (stock*prix) from Produit as p where p.marque = :brand");
        queryStock.setParameter("brand", brand);
        return (Double) queryStock.uniqueResult();
    }

    public List<Produit> findBynote (int note){
        Query<Integer> idProduit = session.createQuery("select c.produit.id from Commentaire as c where c.note >= :note");
        idProduit.setParameter("note",note);
        List<Integer> id = idProduit.list();
        Query<Produit> produitQuery = session.createQuery("from Produit where id in :ids");
        produitQuery.setParameter("ids",id);
        return produitQuery.list();
    }
}