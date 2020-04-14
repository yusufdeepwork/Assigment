/**
 * @author Yusuf
 * @since 13.04.2020
 */

import java.util.ArrayList;

public class Assigment02_20190808001 {
}
class Product{

    private String name;
    private double price;
    private  int count;

    public Product(String name, double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }


    public double purchase(int amount){
        if(amount<=count && amount >= 0) {
            count = count-amount;
            return (double)(amount*price);
        }else{
            throw new InvalidPurchaseException(amount,count);
        }

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(int amount) {
        if(amount >=0)
        this.price=(double)amount;
        else
        throw new InvalidPriceException(price);
    }
    public int getCount() {
        return count;
    }
    public void addToInventory(int amount) {
        if(amount>=0)
            this.count=this.count+amount;
        else
            throw new InvalidAmountException(amount);
    }
    @Override
    public String toString() {
        return "Product " + name + " has " + count + " remaining";
    }

}
class FoodProduct extends Product{

    private  int calories;
    private  boolean gluten;
    private  boolean dairy;
    private  boolean meat;

    public FoodProduct(String name, double price, int count, int calories, boolean gluten, boolean dairy,boolean meat) {
        super(name, price, count);
        this.calories = calories;
        this.gluten = gluten;
        this.dairy = dairy;
        this.meat = meat;
    }

    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        if(calories < 0)
            throw new InvalidAmountException(calories);
        else
            this.calories = calories;
    }
    public boolean containsGluten() {
        return gluten;
    }
    public boolean containsDairy() {
        return dairy;
    }
    public  boolean containsMeat() {
        return meat;
    }
}
class ClothingProduct extends Product{

    private String size;

    public ClothingProduct(String name, double price, int count, String size) {
        super(name, price, count);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
}

class Customer {

    private String name;
    private String variableReceipt;
    private double variableTotalDue;


    public Customer(String name) {
        this.name = name;
    }

    public void addToCard(Product product,int count){

        try {
            product.purchase(count);
            variableReceipt= "Product Name : " +product.getName()+
                    "Product Price : " + product.getPrice() + "Count : "+count+
                    "Total for : " + count * product.getPrice();
            variableTotalDue=product.getPrice()*count;
        }catch(Exception e){
            System.out.println("ERROR");
        }
    }

    public String receipt(){
        return variableReceipt;
    }
    public double getTotalDue(){
        return variableTotalDue;
    }

    public double pay(double amount){
    if (amount >= variableTotalDue){
        System.out.println("Thank You");
        return variableTotalDue;
    }else{
        throw new NotEnoughPaymentException(amount,variableTotalDue);
    }

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
class ClubCustomer extends Customer {

    private String phone;

    public ClubCustomer(String name, String phone) {
        super(name);
        this.phone = phone;
    }



    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


}
class Store {

    private String name;
    private String webSite;
    private int count;
    private ArrayList<Product> products;
    private ArrayList<ClubCustomer> clubCustomers;


    public Store(String name, String webSite) {
        this.name = name;
        this.webSite = webSite;

        products = new ArrayList<Product>();
        clubCustomers = new ArrayList<ClubCustomer>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getInventorySize() {
        return products.size();
    }

    public int getCount() {
        return count;
    }

    public void addProduct(Product product) {
        products.add(product);
        count++;
    }

    public void addCostumer(ClubCustomer costumer) {
        clubCustomers.add(costumer);
    }

    public Product getProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name))
                return products.get(i);
        }
        throw new ProductNotFoundException(name);
    }

    public ClubCustomer getCostumer(String phone) {
        for (int i = 0; i < clubCustomers.size(); i++) {
            if (clubCustomers.get(i).getPhone().equals(phone))
                return clubCustomers.get(i);
        }
        throw new CostumerNotFoundException(name);
    }

    public void removeProduct(String name) {
        boolean isProductName = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                products.remove(i);
                isProductName = true;
                break;
            }
        }

        if (!isProductName)
            throw new ProductNotFoundException(name);
    }

    public void removeCostumer(String phone) {
        boolean isCostumerPhone = false;
        for (int i = 0; i < clubCustomers.size(); i++) {
            if (clubCustomers.get(i).getPhone().equals(phone)) {
                clubCustomers.remove(i);
                isCostumerPhone = true;
                break;
            }
        }
        if (!isCostumerPhone)
            throw new CostumerNotFoundException(name);
    }

}





class InvalidPriceException extends RuntimeException{

  private double price;

    public InvalidPriceException(double price) {
        this.price=price;
        throw new InvalidPriceException(price);
    }

    @Override
    public String toString() {
        return "InvalidPriceException{}" + price;
    }
}
class InvalidAmountException extends RuntimeException{

    private  int amount;
    public InvalidAmountException(int amount) {
      this.amount=amount;
        throw new InvalidAmountException(amount);
    }

    @Override
    public String toString() {
        return "InvalidAmountException{}" + amount;
    }
}
class InvalidPurchaseException extends RuntimeException{
    private  int amount;
    private  int remaining;

    public InvalidPurchaseException(int amount, int remaining) {
        this.amount = amount;
        this.remaining = remaining;
        throw new InvalidPurchaseException(amount, remaining);
    }

    @Override
    public String toString() {
        return "InvalidPurchaseException{" +
                "amount=" + amount + "requested"+
                ", remaining=" + remaining +
                '}';
    }
}
class NotEnoughPaymentException extends RuntimeException{

    private  double amount;
    private  double due;

    public NotEnoughPaymentException(double amount, double due) {
        this.amount = amount;
        this.due = due;
    throw new NotEnoughPaymentException(amount, due);
    }

    @Override
    public String toString() {
        return "NotEnoughPaymentException{" +due +
                ", due but only" + amount+ "given";
    }
}
class ProductNotFoundException extends RuntimeException{
    private  String name;

    public ProductNotFoundException(String name) {
        this.name = name;
        throw new ProductNotFoundException(name);
    }

    @Override
    public String toString() {
        return "ProductNotFoundException{" +
                "name='" + name + '\'' +
                '}';
    }
}
class CostumerNotFoundException extends RuntimeException {
    private  String phone;

    public CostumerNotFoundException(String phone) {
        this.phone = phone;
        throw new CostumerNotFoundException(phone);
    }

    @Override
    public String toString() {
        return "CostumerNotFoundException{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
