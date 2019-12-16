//GiftShopEmployee can add items to basket, show total, remove from basket, add items to inventory.
public class GiftShopEmp
{
   private String empName;
   private int empID;
   
   public GiftShopEmp(String name, int id)
   {
      setEmpName(name);
      setEmpID(id);
   }
   public void setEmpName(String name)
   {
      this.empName = name;
   }
   public String getName()
   {
      return empName;
   }
   
   public void setEmpID(int iD)
   {
      this.empID = iD;
   }
   public int getID()
   {
      return empID;
   }
   
   //Employee can add gifts to the giftShop Inventory.
   public void addNewGiftToInventory(Gift gift)
   {
      Inventory.inStock.add(gift);
   }  
   
   
   //displays prices of a Specific gift
   public void prices(Gift gift)
   {
      gift.getGiftPrice();
   }
   
   //adds item to the purchase basket, throws exception if item out of stock
   public void addToBasket(Gift gift)
   {
      Inventory.inBasket.add(gift);
   }
   
   //removes item from basket
   public boolean removeFromBasket(Gift gift)
   {
      String name1 = gift.getGiftName();
      String name2;
      Gift g;
      boolean confirmed = false;
      for (int i = 0; i < Inventory.inBasket.size(); i++)
      {
         g = Inventory.inBasket.get(i);
         name2 = g.getGiftName();
         if (name1.equals(name2))
         {
            Inventory.inBasket.remove(i);
            confirmed = true;
            return confirmed;
         }
         else
         {
            confirmed = false;
         }
      }
      return confirmed;
   }
   
   //displays total in basket
   public void displayTotal()
   {
      Gift g;
      String display;
      double total = 0.0;
      System.out.println("Current items in Basket");
      for (int i = 0; i <Inventory.inBasket.size(); i++)
      {
         g = Inventory.inBasket.get(i);
         display = g.toString();
         System.out.println(display);
         total = total + g.getGiftPrice();
      }
      System.out.println("Balance due: $" + total);
   }
   
   public static void main(String[] args)
   {

   GiftShop blaze = new GiftShop(1, "Grace", 2, 1, 2, 7.25, 9.00, 14.00);
   String p = blaze.toString();
   
   System.out.println(blaze.getGiftRevenue());
   
   
   blaze.displayInventory();
   }
}