import java.util.Arrays;
import java.util.Comparator;

public class SortProduct {
    private int i = 0;

    private Product[] transformation(ProductHashSet list) {
        Product[] arr = new Product[list.size()];
        for (Product product : list) {
            arr[i++] = product;

        }
        return arr;
    }

    public void sort(ProductHashSet list, int a) {
        Product[] ar = transformation(list);
        Arrays.sort(ar, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (a == 1) {
                    return Double.compare(o1.getPrice(), o2.getPrice());
                } else {
                    return Double.compare(o2.getPrice(), o1.getPrice());
                }
            }
        });
        System.out.println(Arrays.toString(ar));
    }
}