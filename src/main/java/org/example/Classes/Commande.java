package org.example.Classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commande_id;

    private Double total;
    @Temporal(TemporalType.DATE)
    private Date dateCommande;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "commande_produit", joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private List<Produit> produits = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    public Commande(Date dateCommande) {
        this.dateCommande = dateCommande;
        this.total = 0D;
    }

    public Commande() {
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public boolean addProduit(Produit produit, int quantity) {
        if (produit != null && quantity > 0 && quantity <= produit.getStock()) {
            this.produits.add(produit);
            this.total += quantity * produit.getPrix();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Commande{" +
                "commande_id=" + commande_id +
                ", total=" + total +
                ", dateCommande=" + dateCommande + "\n" +
                ", produits=" + produits + "\n" +
                ",adress = " + adresse +
                '}';
    }
}
