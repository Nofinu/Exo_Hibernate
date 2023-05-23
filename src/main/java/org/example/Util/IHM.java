package org.example.Util;

import org.example.services.ProduitService;

import java.util.Date;
import java.util.Scanner;

public class IHM {
    private ProduitService produitService;
    private Scanner scanner;

    public IHM() {
        produitService = new ProduitService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        int entry;
        do {
            menu();
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 4:
                    findAllAction();
                    break;
                case 5:
                    findByPriceAction();
                    break;
                case 6:
                    findByDate();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("entrer une valeur valide");
                    break;
            }
        } while (entry != 0);
    }

    private void menu() {
        System.out.println("-------- menu --------");
        System.out.println("4-- afficher tout les produits");
        System.out.println("5-- afficher les produit supperieur a une somme");
        System.out.println("6-- afficher les produit entre 2 dates");
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
}
