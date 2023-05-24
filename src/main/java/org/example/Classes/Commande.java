package org.example.Classes;

import javax.persistence.*;
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
    @JoinTable(name = "commande_produit",joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private List<Produit> produits;

    public Commande(Date dateCommande) {
        this.dateCommande = dateCommande;
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

    @Override
    public String toString() {
        return "Commande{" +
                "commande_id=" + commande_id +
                ", total=" + total +
                ", dateCommande=" + dateCommande +
                ", produits=" + produits +
                '}';
    }
}
