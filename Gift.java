/*
This initializes a gift. This can be added through the GiftShop class
This will also include appropriate get and setter methods
As well as a toString that will be used by another class to display all elements in stock
  */
public class Gift
{

   private String giftName;
   private double giftPrice;
   private int giftCode;
   
   public Gift()
   {
      this.giftName = " ";
      this.giftPrice = 0.0;
      this.giftCode = 0;
   }
   public Gift(String name, double price, int code)
   {
      setGiftName(name);
      setGiftPrice(price);
      setGiftCode(code);
   }
   
   public void setGiftName(String name)
   {
      this.giftName = name;
   }
   
   public String getGiftName()
   {
      return giftName;
   }
   
   public void setGiftPrice(double price)
   {
      this.giftPrice = price;
   }
   public double getGiftPrice()
   {
      return giftPrice;
   
   }
   
   public void setGiftCode(int code)
   {
      this.giftCode = code;
   }
   
   public int getGiftCode()
   {
      return giftCode;
   }
   public String toString()
   {
      String output="Gift: ";
      output = output + getGiftName() + ", $" + getGiftPrice();
      return output;
       
   }   
}