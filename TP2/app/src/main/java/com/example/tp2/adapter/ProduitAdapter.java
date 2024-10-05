package com.example.tp2.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.tp2.R;
import com.example.tp2.beans.Produit;

public class ProduitAdapter extends BaseAdapter {
    private List<Produit> produits;
    private LayoutInflater inflater;

    public ProduitAdapter(List<Produit> produits, Activity activity) {
        this.produits = produits;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return produits.size();
    }

    @Override
    public Object getItem(int position) {
        return produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Réutilisation des vues existantes pour des performances optimales
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_produit, null);
        }

        // Récupération des vues du fichier XML item_produit.xml
        ImageView photo = convertView.findViewById(R.id.photoProduit);
        TextView nom = convertView.findViewById(R.id.nomProduit);
        TextView nbrIngredients = convertView.findViewById(R.id.nbrIngredientsProduit);
        TextView duree = convertView.findViewById(R.id.dureeProduit);

        // Remplissage des données pour chaque produit
        Produit produit = produits.get(position);

        // Définir les données sur chaque vue
        nom.setText(produit.getNom());
        nbrIngredients.setText("Ingrédients: " + produit.getNbrIndredients());
        duree.setText("Durée: " + produit.getDuree());
        photo.setImageResource(produit.getPhoto());  // Assurez-vous que photo est un ID de ressource valide

        return convertView;
    }
}
