package exercise1_1;

/**
 * Class of a product
 * Stores name, price and stock count of the product
 */
public class Product {
    private String name;
    private int price;
    private int stockCount;

    public Product(String name, int price){
        this(name,price,0);
    }
    public Product(String name, int price,int stockCount){
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * print out product info
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %d", this.name, this.price,this.stockCount);
    }
    public static void main(String[] args){
        Product first = new Product("abc",1);
        Product second = new Product("deh",2,4);
        System.out.println("name | price | count");
        System.out.println(first);
        System.out.println(second);

    }
}
