package org.example;

import Classes.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();

        Produit produit1 = new Produit("marque1","reference1", LocalDate.now(),01.05D,200);
        Produit produit2 = new Produit("marque2","reference2", LocalDate.now(),21.05D,20);
        Produit produit3 = new Produit("marque3","reference3", LocalDate.now(),200.05D,10);
        Produit produit4 = new Produit("marque4","reference4", LocalDate.now(),10D,50);
        Produit produit5 = new Produit("marque5","reference5", LocalDate.now(),55.25D,150);

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(produit1);
        session.save(produit2);
        session.save(produit3);
        session.save(produit4);
        session.save(produit5);
        session.getTransaction().commit();

        System.out.println(session.load(Produit.class,2));

        session.getTransaction().begin();
        Produit productFind = session.load(Produit.class,3);
        session.delete(productFind);
        session.getTransaction().commit();

        session.getTransaction().begin();
        Produit productEdit = session.load(Produit.class,1);
        productEdit.setMarque("marquemodif");
        session.update(productEdit);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}