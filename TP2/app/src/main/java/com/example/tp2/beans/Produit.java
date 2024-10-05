package com.example.tp2.beans;


import java.util.ArrayList;
import java.util.List;

public class Produit {

    private static int comp;
    private int id;
    private String nom;
    private int nbrIndredients;
    private int photo;
    private String duree;
    private  String detailsIngred;
    private String description;
    private String preparation;
    public Produit() {
    }

    public Produit(String nom, int nbrIndredients, int photo, String duree, String detailsIngred, String description, String preparation) {
        this.id = ++comp;
        this.nom = nom;
        this.nbrIndredients = nbrIndredients;
        this.photo = photo;
        this.duree=duree;
        this.detailsIngred=detailsIngred;
        this.description=description;
        this.preparation=preparation;
    }

    public static int getComp() {
        return comp;
    }

    public static void setComp(int comp) {
        Produit.comp = comp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbrIndredients() {
        return nbrIndredients;
    }

    public void setNbrIndredients(int nbrIndredients) {
        this.nbrIndredients = nbrIndredients;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDetailsIngred() {
        return detailsIngred;
    }

    public void setDetailsIngred(String detailsIngred) {
        this.detailsIngred = detailsIngred;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}

