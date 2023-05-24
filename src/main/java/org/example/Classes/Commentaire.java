package org.example.Classes;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_com;
    private String contenu;
    @Temporal(TemporalType.DATE)
    private Date date;
    private int note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public Commentaire(String contenu, Date date, int note) {
        this.contenu = contenu;
        this.date = date;
        this.note = note;
    }

    public Commentaire() {
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id_com=" + id_com +
                ", contenu='" + contenu + '\'' +
                ", date=" + date +
                ", note=" + note +
                '}';
    }
}
