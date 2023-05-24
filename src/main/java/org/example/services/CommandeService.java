package org.example.services;

import org.example.Classes.Adresse;
import org.example.Classes.Commande;
import org.example.Classes.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class CommandeService extends BaseService implements Repository<Commande> {
    @Override
    public void begin() {
        session = sessionFactory.openSession();
    }

    @Override
    public void end() {
        session.close();
    }

    @Override
    public boolean create(Commande o) {
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }
    public boolean createAdresse(Adresse o) {
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Commande o) {
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
    public Commande findById(int id) {
        return session.get(Commande.class, id);
    }

    @Override
    public List<Commande> findAll() {
        return session.createQuery("from Commande ", Commande.class).list();
    }

    public List<Commande> findTodayCommande (){
        Date today = new Date(System.currentTimeMillis());
        Query<Commande> commandeQuery = session.createQuery("from Commande where dateCommande = :date");
        commandeQuery.setParameter("date",today);
        return commandeQuery.list();
    }

}
