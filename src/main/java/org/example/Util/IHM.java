package org.example.Util;

import org.example.Classes.Produit;
import org.example.services.ProduitService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IHM {
    private ProduitService produitService;
    private Scanner scanner;

    public IHM() {
        produitService = new ProduitService();
        produitService.begin();
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
          /*      case 8:
                    deletetest();
                    break;*/
                case 0:
                    break;
                default:
                    System.out.println("entrer une valeur valide");
                    break;
            }
        } while (entry != 0);
        produitService.end();
    }

    private void menu() {
        System.out.println("-------- menu --------");
        System.out.println("1-- ajouter un produit");
        System.out.println("2-- supprimer un produit");
        System.out.println("3-- editer un produit");
        System.out.println("4-- afficher tout les produits");
        System.out.println("5-- afficher les produit supperieur a une somme");
        System.out.println("6-- afficher les produit entre 2 dates");
        System.out.println("7-- afficher l'id et la reference des produit au dessous d'un seuille de stock");
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
        produit.setId(id);

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

        List<Produit> produitList = produitService.findByQuantity(quantity);
        for (Produit p: produitList) {
            System.out.println("id : "+p.getId()+"/ reference : "+p.getReference());
        }
    }
/*
    private void deletetest (){
        System.out.println("id :");
        int id =  scanner.nextInt();
        Produit produit = produitService.findById(id);
        produitService.delete2(produit);
    }*/
}
