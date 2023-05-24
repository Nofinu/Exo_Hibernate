package org.example.Util;

import org.example.Classes.*;
import org.example.services.CommandeService;
import org.example.services.ProduitService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IHM {
    private ProduitService produitService;
    private CommandeService commandeService;
    private Scanner scanner;

    public IHM() {
        produitService = new ProduitService();
        produitService.begin();
        commandeService = new CommandeService();
        commandeService.begin();
        scanner = new Scanner(System.in);

    }

    public void start() {
        int entry;
        do {
            menu();
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    addProductAction();
                    break;
                case 2:
                    deleteProductAction();
                    break;
                case 3:
                    editProductAction();
                    break;
                case 4:
                    findAllAction();
                    break;
                case 5:
                    findByPriceAction();
                    break;
                case 6:
                    findByDate();
                    break;
                case 7:
                    findByQuantityAction();
                    break;
                case 8:
                    stockByBrandAction();
                    break;
                case 9:
                    meanPriceAction();
                    break;
                case 10:
                    findByBrandAction();
                    break;
                case 11:
                    deleteByBrandAction();
                    break;
                case 12:
                    findValueByBrandAction();
                    break;
                case 13:
                    addImageAction();
                    break;
                case 14:
                    addCommentaireAction();
                    break;
                case 15:
                    findByNoteAction();
                    break;
                case 16:
                    addCommandeAction();
                    break;
                case 17 :
                    addProduitToCommandeAction();
                    break;
                case 18:
                    showAllCommandeAction();
                    break;
                case 19:
                    showTodayCommandeAction();
                    break;
                case 20 :
                    addAdresseToCommande();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("entrer une valeur valide");
                    break;
            }
        } while (entry != 0);
        commandeService.end();
        produitService.end();
    }

    private void menu() {
        System.out.println("-------- menu --------");
        System.out.println("1-- ajouter un produit");
        System.out.println("2-- supprimer un produit");
        System.out.println("3-- editer un produit");
        System.out.println("---------------------------");
        System.out.println("4-- afficher tout les produits");
        System.out.println("5-- afficher les produit supperieur a une somme");
        System.out.println("6-- afficher les produit entre 2 dates");
        System.out.println("7-- afficher l'id et la reference des produit au dessous d'un seuille de stock");
        System.out.println("---------------------------");
        System.out.println("8-- stock par marque");
        System.out.println("9-- prix moyen des article");
        System.out.println("10-- liste d'article par marque");
        System.out.println("11-- suppresion par marque");
        System.out.println("12-- afficher le prix total du stock d'une marque");
        System.out.println("---------------------------");
        System.out.println("13-- ajouter une image a un produit");
        System.out.println("14-- ajouter un commentaire a un produit");
        System.out.println("15-- trouver des produit par rapport a leurs note");
        System.out.println("----------------------------");
        System.out.println("16-- cree une commande");
        System.out.println("17-- ajouter un produit a une commande");
        System.out.println("18-- afficher toute les commande");
        System.out.println("19-- afficher les commande du jour");
        System.out.println("20-- ajouter une adresse a une commande");
    }

    private void addProductAction (){
        System.out.println("-------- ajout d'un produit ---------");
        System.out.println("marque :");
        String brand = scanner.nextLine();
        System.out.println("reference :");
        String reference = scanner.nextLine();
        System.out.println("date d'achat (yyyy/MM/dd):");
        String date = scanner.nextLine();
        System.out.println("prix");
        Double price = scanner.nextDouble();
        System.out.println("stock");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Produit produit = new Produit(brand,reference,new Date(date),price,stock);
        if(produitService.create(produit)){
            System.out.println("produit cree");
        }else{
            System.out.println("erreure lors de la creation du produit");
        }
    }

    private void deleteProductAction (){
        System.out.println("-------- supresion d'un produit ---------");
        System.out.println("id du produit a suprimer :");
        int id = scanner.nextInt();
        scanner.nextLine();

        if(produitService.delete(id)){
            System.out.println("produit supprimer");
        }else{
            System.out.println("erreure lors de la suppresion");
        }
    }

    private void editProductAction (){
        System.out.println("-------- modification d'un produit ---------");
        System.out.println("marque :");
        String brand = scanner.nextLine();
        System.out.println("reference :");
        String reference = scanner.nextLine();
        System.out.println("date d'achat (yyyy/MM/dd):");
        String date = scanner.nextLine();
        System.out.println("prix");
        Double price = scanner.nextDouble();
        System.out.println("stock");
        int stock = scanner.nextInt();
        System.out.println("id du produit a moddifier :");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produit produit = new Produit(brand,reference,new Date(date),price,stock);
        produit.setProduit_id(id);

        if(produitService.update(produit)){
            System.out.println("produit modifié");
        }else{
            System.out.println("erreure lors de la modification");
        }
    }

    private void findAllAction() {
        System.out.println("-------- afficher tout les produit -------");
        produitService.findAll().forEach(System.out::println);
    }

    private void findByPriceAction() {
        System.out.println("-------- afficher par prix ---------");
        System.out.println("prix mini :");
        Double price = scanner.nextDouble();
        scanner.nextLine();
        produitService.findByPrice(price).forEach(System.out::println);
    }

    private void findByDate() {
        System.out.println("-------- afficher les produit entre 2 dates --------");
        System.out.println("date de debut (yyyy/MM/dd):");
        String dateStart = scanner.nextLine();
        System.out.println("date de fin (yyyy/MM/dd):");
        String dateEnd = scanner.nextLine();
        produitService.findBetweenDate(new Date(dateStart), new Date(dateEnd)).forEach(System.out::println);
    }

    private void findByQuantityAction (){
        System.out.println("------------ affichage par quantité ---------");
        System.out.println("quantité en dessous de la quelle on affichera les produits :");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        produitService.findByQuantity(quantity).forEach(System.out::println);
    }

    private void stockByBrandAction (){
        System.out.println("--------affichage du stock par marque----------");
        System.out.println("marque :");
        String brand = scanner.nextLine();
        System.out.println(produitService.stockValueByBrand(brand));
    }

    private void meanPriceAction (){
        System.out.println(produitService.meanPrice());
    }

    private void findByBrandAction (){
        System.out.println("--------affichage du stock par marque----------");
        System.out.println("marque :");
        String brand = scanner.nextLine();
        produitService.findByBrand(brand).forEach(System.out::println);
    }

    private void deleteByBrandAction (){
        System.out.println("--------affichage du stock par marque----------");
        System.out.println("marque :");
        String brand = scanner.nextLine();
        if(produitService.deleteByBrand(brand)){
            System.out.println("produits suprimé");
        }else{
            System.out.println("erreure lors de la suppresion");
        }
    }

    private void findValueByBrandAction (){
        System.out.println("--------affichage du stock par marque----------");
        System.out.println("marque :");
        String brand = scanner.nextLine();
        System.out.println(produitService.findValueByBrand(brand));
    }

    private void addImageAction (){
        System.out.println("---------- ajout d'une image a un produit ----------");
        System.out.println("url de l'image :");
        String url = scanner.nextLine();
        System.out.println("id du produit :");
        int id = scanner.nextInt();
        scanner.nextLine();

        Image image = new Image(url);
        if(produitService.createImage(image)){
            Produit produit = produitService.findById(id);
            if(produit != null){
                image.setProduit(produit);
                produit.addImage(image);
                if(produitService.update(produit)){
                    System.out.println("image ajouté");
                }else{
                    System.out.println("erreure lors de l'ajout de l'image");
                }
            }
        }
    }

    private void addCommentaireAction (){
        System.out.println("---------- ajout d'un commentaire a un produit ----------");
        System.out.println("contenu :");
        String contenu = scanner.nextLine();
        System.out.println("date (yyyy/MM/dd):");
        String dateStr= scanner.nextLine();
        System.out.println("note :");
        int note = scanner.nextInt();
        System.out.println("id du produit :");
        int id = scanner.nextInt();
        scanner.nextLine();

        Commentaire commentaire = new Commentaire(contenu,new Date(dateStr),note);
        if(produitService.createCommentaire(commentaire)){
            Produit produit = produitService.findById(id);
            if(produit != null){
                commentaire.setProduit(produit);
                produit.addCommentaire(commentaire);
                if(produitService.update(produit)){
                    System.out.println("commentaire ajouté");
                }else{
                    System.out.println("erreure lors de l'ajout du commentaire");
                }
            }
        }
    }

    private void findByNoteAction(){
        System.out.println("afficher les produit par rapport a leurs note");
        System.out.println("note mini des produit");
        int note = scanner.nextInt();
        scanner.nextLine();
        produitService.findBynote(note).forEach(System.out::println);
        System.out.println();
    }

    private void addCommandeAction (){
        System.out.println("---------- creation d'une commande -----------");
        System.out.println("date de la commande (yyyy/MM/dd):");
        String dateStr = scanner.nextLine();

        Commande commande = new Commande(new Date(dateStr));
        if(commandeService.create(commande)){
            System.out.println("commande cree");
        }else{
            System.out.println("erreure lors de la creation de la commande");
        }

    }

    private void addProduitToCommandeAction (){
        System.out.println("------ ajouter un produit a une commande -------");
        System.out.println("id de la commande :");
        int idCommande = scanner.nextInt();
        System.out.println("id du produit :");
        int idProduit = scanner.nextInt();
        System.out.println("quantité :");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Commande commande = commandeService.findById(idCommande);
        Produit produit = produitService.findById(idProduit);
        if(commande != null && produit != null){
            if(commande.addProduit(produit,quantity)){
                if(commandeService.update(commande)){
                    int stock = produit.getStock()-quantity;
                    produit.setStock(stock);
                    if(produitService.update(produit)){
                        System.out.println("produit ajouté");
                    }
                }
            }else{
                System.out.println("quantité invalide");
            }
        }else {
            System.out.println("erreure dans l'id produit ou l'id commande");
        }
    }

    private void showAllCommandeAction () {
        System.out.println("------ afficher toute les commande -------");
        commandeService.findAll().forEach(System.out::println);
    }

    private void showTodayCommandeAction (){
        System.out.println("------- afficher les commande du jour ------");
        commandeService.findTodayCommande().forEach(System.out::println);
    }

    private void addAdresseToCommande (){
        System.out.println("------- ajouter une adresse a une commande -------");
        System.out.println("rue :");
        String rue = scanner.nextLine();
        System.out.println("ville :");
        String ville = scanner.nextLine();
        System.out.println("code postal:");
        int codePostal = scanner.nextInt();
        System.out.println("id de la commande :");
        int id = scanner.nextInt();
        scanner.nextLine();

        Adresse adresse = new Adresse(rue, ville, codePostal);
        Commande commande = commandeService.findById(id);
        if(commande != null){
            if(commandeService.createAdresse(adresse)){
                commande.setAdresse(adresse);
                if(commandeService.update(commande)){
                    System.out.println("adresse ajouter");
                }else{
                    System.out.println("erreure lors de l'ajout");
                }
            }else{
                System.out.println("erreure lors de la creation de l'adresse");
            }

        }else{
            System.out.println("id de la commande incorecte");
        }
    }
}

