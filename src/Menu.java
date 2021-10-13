import java.util.Scanner;

public class Menu {
    private ProductHashSet productHashSet = new ProductHashSet();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("\n1 - Добавить продукт");
        System.out.println("2 - Изменить продукт");
        System.out.println("3 - Удалить продукт");
        System.out.println("4 - Показать список всех продуктов");
        System.out.println("5 - Сортировать продукты");
        System.out.println("6 - Выход");
        choise();
    }

    public void choise() {
        int i = scanner.nextInt();

        switch (i) {
            case 1: {
                Scanner sc = new Scanner(System.in);
                System.out.println("Добавление продукта :");
                System.out.println("Введи номер продукта ");
                int id = sc.nextInt();
                System.out.println("Введи наименование продукта ");
                String name = sc.next();
                System.out.println("Введи цену продукта ");
                double price = sc.nextDouble();
                productHashSet.add(new Product(id, name, price));
                start();
                break;
            }
            case 2: {
                System.out.println("Введи номер продукта который необходимо изменить");
                int num = scanner.nextInt();
                Product product = productHashSet.getById(num);
                System.out.println("Веди новое наименование продукта");
                String name = scanner.next();
                System.out.println("Введи новую цену продукта");
                double price = scanner.nextDouble();
                productHashSet.editProduct(product, name, price);
                start();
                break;
            }
            case 3: {
                System.out.println("Введи номер продукта который необходимо удалить");
                int id = scanner.nextInt();
                productHashSet.removeById(id);
                start();
                break;
            }
            case 4: {
                System.out.println("Весь список продуктов");
                for (Product list : productHashSet) {
                    System.out.println(list);
                }
                start();
                break;
            }
            case 5: {
                SortProduct sortProduct = new SortProduct();
                System.out.println("Выбери сортировку: ");
                System.out.println("1 - цена по возрастанию");
                System.out.println("2 - цена по убыванию");
                int a = scanner.nextInt();
                int b = 0;
                if (a == 1) {
                    b = 1;
                } else if (a == 2) {
                    b = -1;
                } else {
                    start();
                }
                sortProduct.sort(productHashSet, b);
                start();
                break;
            }
            case 6: {
                System.out.println("Работа завершена");
                break;
            }
        }
    }
}