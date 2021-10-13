import java.util.Iterator;

public class ProductHashSet implements ProductSet {

    private int size = 0;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    @Override
    public boolean add(Product product) {
        if (size >= (array.length * LOAD_FACTOR)) {
            increaseArray();
        }
        boolean added = add(product, array);
        if (added) {
            size++;
        }
        return added;
    }

    private boolean add(Product product, Entry[] newArray) {
        int position = getProductPosition(product, newArray.length);
        if (newArray[position] == null) {
            Entry entry = new Entry(product, null);
            newArray[position] = entry;
            return true;
        } else {
            Entry existedElement = newArray[position];
            while (true) {
                if (existedElement.element.equals(product)) {
                    return false;
                } else if (existedElement.next == null) {
                    existedElement.next = new Entry(product, null);
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }

    @Override
    public boolean remove(Product product) {
        int position = getProductPosition(product, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = array[position];
        Entry last = secondLast.next;
        if (secondLast.element.equals(product)) {
            array[position] = last;
            size--;
            return true;
        }
        while (last != null) {
            if (last.element.equals(product)) {
                secondLast.next = last.next;
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }
        return false;
    }

    public Product getById(int id) {
        int arrayIndex = 0;
        Entry entry = null;
        int index = 0;

        while (index < size) {
            while (array[arrayIndex] == null) {
                arrayIndex++;
            }
            if (entry == null) {
                entry = array[arrayIndex];
            }
            Product product = entry.element;
            if (entry.element.getId() == id) {
                return product;

            }
            entry = entry.next;
            if (entry == null) {
                arrayIndex++;
            }
            index++;
        }
        return null;
    }

    @Override
    public boolean removeById(int id) {
        if(getById(id) != null){
            Product product = getById(id);
            remove(product);
            return true;
        } else {
            System.out.println("Продукт с таким id не найден");
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean editProduct(Product product, String name, double price) {
        int position = getProductPosition(product, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = array[position];
        Entry last = secondLast.next;
        if (secondLast.element.equals(product)) {
            Product newProduct = secondLast.element;
            newProduct.setName(name);
            newProduct.setPrice(price);
            return true;
        }
        while (last != null) {
            if (last.element.equals(product)) {
                return true;
            } else {
                last = last.next;
            }
        }
        return false;
    }

    private int getProductPosition(Product product, int arrayLength) {
        int variable = (product.hashCode() % arrayLength);
        return (variable < 0) ? -variable : variable;
    }

    @Override
    public Iterator<Product> iterator() {
        return new Iterator<Product>() {

            int index = 0;
            int arrayIndex = 0;
            Entry entry;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Product next() {
                while (array[arrayIndex] == null) {
                    arrayIndex++;
                }
                if (entry == null) {
                    entry = array[arrayIndex];
                }
                Product product = entry.element;
                entry = entry.next;
                if (entry == null) {
                    arrayIndex++;
                }
                index++;
                return product;
            }
        };
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry existesElement = entry;
            while (existesElement != null) {
                add(existesElement.element, newArray);
                existesElement = existesElement.next;
            }
        }
        array = newArray;
    }

    private static class Entry {
        Product element;
        Entry next;

        public Entry(Product element, Entry next) {
            this.element = element;
            this.next = next;
        }
    }
}