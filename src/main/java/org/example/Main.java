package org.example;

import org.example.Classes.Produit;
import org.example.services.ProduitService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();

       /* ProduitService ps = new ProduitService();
        ps.create(new Produit("produit1","zzaa123",new Date("2016/01/08"),250D,6000));
        ps.create(new Produit("produit2","EER678",new Date("2016/02/09"),250D,2000));
        ps.create(new Produit("produit3","AQWZSX",new Date("2016/09/23"),250D,6000));
        ps.create(new Produit("produit4","AZERTY",new Date("2016/02/12"),250D,6000));
        ps.create(new Produit("produit5","qsdERT",new Date("2016/02/02"),250D,6000));

        // Informations produit id = 2

        Produit p = ps.findById(2);
        System.out.println(p);

        // Supprimer le produit id = 3
        ps.delete(ps.findById(3));

        // Modifier produit id = 1

        p = ps.findById(1);
        if(p != null){
            p.setMarque("HP");
            p.setReference("MMMMPPP");
            p.setDateAchat(new Date("2015/09/08"));
            p.setPrix(5000D);
            ps.update(p);
        }*/

        ProduitService produitService = new ProduitService();
        produitService.findAll().forEach(System.out::println);

        produitService.findByPrice(100D).forEach(System.out::println);

        produitService.findBetweenDate(new Date("2016/01/08"),new Date("2023/01/08")).forEach(System.out::println);
    }
}