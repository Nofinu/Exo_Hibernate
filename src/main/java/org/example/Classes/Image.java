package org.example.Classes;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_image;
    private String url;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public Image(String url) {
        this.url = url;
    }

    public Image() {
    }

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id_image=" + id_image +
                ", url='" + url + '\'' +
                '}';
    }
}
