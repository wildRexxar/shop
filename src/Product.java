import java.util.Objects;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Товар № " + id + " : " + name + ", стоимостью " + price + " $";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product product = (Product) obj;
            return (product.id == this.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Product o) {
        if(this.price < o.getPrice()){
            return -1;
        } else if(this.price > o.getPrice()){
            return 1;
        } else {
            return 0;
        }
    }
}
