package org.example.Classes;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int produit_id;

    private String marque;
    private String reference;
    @Temporal(TemporalType.DATE)
    private Date dateAchat;
    private Double prix;
    private int stock;

    @OneToMany(mappedBy = "produit",fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(mappedBy ="produit",fetch = FetchType.EAGER)
    private List<Commentaire> commentaires;

    public Produit(String marque, String reference, Date dateAchat, Double prix, int stock) {
        this.marque = marque;
        this.reference = reference;
        this.dateAchat = dateAchat;
        this.prix = prix;
        this.stock = stock;
    }

    public Produit() {
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public void addImage (Image image){
        if(image != null){
            this.images.add(image);
        }
    }
    public void removeImage(Image image){
        this.images.remove(image);
    }

    public void addCommentaire (Commentaire commentaire){
        if(commentaire != null){
            this.commentaires.add(commentaire);
        }
    }
    public void removeCommentaire (Commentaire commentaire){
        this.commentaires.remove(commentaire);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + produit_id +
                ", marque='" + marque + '\'' +
                ", reference='" + reference + '\'' +
                ", dateAchat=" + dateAchat +
                ", prix=" + prix +
                ", stock=" + stock +
                ", images=" + images +
                ", commentaires=" + commentaires +
                '}';
    }
}
