package com.stjean.operationalpha;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Utilisateur {
    private int id;
    private String nom;
    private int age;
    private String email;
    private String telephone;
    private String ville;
    private double soldePersonnel;

    public static ArrayList<Utilisateur> users = new ArrayList<>();

 
    public Utilisateur(int id, String nom, int age, String email, String telephone, String ville, double soldePersonnel) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.soldePersonnel = soldePersonnel;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getVille() { return ville; }
    public double getSoldePersonnel() { return soldePersonnel; }

    public void setNom(String nom) { this.nom = nom; }
    public void setAge(int age) { this.age = age; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setVille(String ville) { this.ville = ville; }
    public void setSoldePersonnel(double soldePersonnel) { this.soldePersonnel = soldePersonnel; }

    public static void ajouter(Utilisateur user) throws EmailInvalidException {
        
        users.add(user);
        System.out.println("✅ Utilisateur ajouté : " + user.getNom());
    }

    public static void supprimer(int id) throws SuppressionInvalidException {
        Utilisateur u = null;
        for (Utilisateur user : users) {
            if (user.getId() == id) {
                u = user;
                break;
            }
        }
        if (u == null) {
            throw new SuppressionInvalidException("❌ Utilisateur avec ID " + id + " introuvable.");
        }
        users.remove(u);
        System.out.println("🗑️ Utilisateur supprimé : " + u.getNom());
    }

    public static void lister() {
        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            System.out.println("📋 Liste des utilisateurs :");
            for (Utilisateur u : users) {
                System.out.println(u);
            }
        }
    }

    public static void afficher(int id) {
        for (Utilisateur u : users) {
            if (u.getId() == id) {
                System.out.println("👤 Détails utilisateur : " + u);
                return;
            }
        }
        System.out.println("⚠️ Aucun utilisateur trouvé avec l'ID " + id);
    }

    private static boolean validerEmail(String email) {
        String regex = "^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nom: %s | Âge: %d | Email: %s | Téléphone: %s | Ville: %s | Solde: %.2f",
                id, nom, age, email, telephone, ville, soldePersonnel);
    }
}
