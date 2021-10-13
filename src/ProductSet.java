public interface ProductSet extends Iterable<Product> {
    boolean add(Product product);

    boolean remove(Product product);

    boolean removeById(int id);

    int size();

    void clear();

    public boolean editProduct(Product product, String name, double price);
}