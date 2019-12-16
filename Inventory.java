import java.util.ArrayList;
import java.io.*;
public class Inventory
{

   //keeps track of inventory during program
   public static ArrayList<Gift> inStock = new ArrayList<Gift>();
   
   //will keep track of invetory from .txt file
   public static ArrayList<Gift> inStock2 = new ArrayList<Gift>();
   private Gift temp;
   private double totalValue = 0;
   
   //will keep track of gifts added to basket
   public static ArrayList<Gift> inBasket = new ArrayList<Gift>();

   public Inventory()
   {
      inStock = new ArrayList<Gift>(1000);
      //initializes a default inventory, overwrites whatever is in the current inventory file
      DataOutputStream q = readWriteFile("Inventory.txt"); 
      for (int i = 0; i < 200 ; i++)
      {
         inStock.add(new Gift("Key Chain", 4.99, 1));
         temp = inStock.get(i);
         setTotalValue(temp);
         
      }
      for (int i = 200; i <401; i++)
      {
         inStock.add(new Gift("Coffee Mug", 14.99, 2));
         temp = inStock.get(i);
         setTotalValue(temp);
      }
      for (int i = 400 ; i < 601; i++)
      {
         inStock.add(new Gift("Zoo Shirt", 19.99, 3));
         temp = inStock.get(i);
         setTotalValue(temp);
      }
      for (int i = 600; i <801; i++)
      {
         inStock.add(new Gift("Stuffed Animal Small", 24.99, 4));
         temp = inStock.get(i);
         setTotalValue(temp);
      }
      
      for (int i = 800; i <1001; i++)
      {
         inStock.add(new Gift("Stuffed Animal Large", 49.99, 5));
         temp = inStock.get(i);
         setTotalValue(temp);
      }
     for (int i = 0; i < inStock.size(); i++)
     {
      writeToInventory(inStock.get(i), q);
     }
   }
   
   //will fill out inventory array from .txt file called Inventory.txt
   //parameter a is arbitrary
   public Inventory(int a)
   {
      int i = 0;
      Gift temp2;
      //Opens up the file
      File inventoryFile = new File("Inventory.txt");
      
      boolean endOfFile = false;
      
      //this try-catch block reads the contents in the file, which then creates new gift Object and adds it to the working array.
      try
      {
         DataInputStream getInventory = new DataInputStream(new BufferedInputStream(new FileInputStream(inventoryFile)));
         
         while(!endOfFile)
         {
            String giftName = getInventory.readUTF();
            int giftCode = getInventory.readInt();
            double giftPrice = getInventory.readDouble();
            
            //this is what will update the inventory.
            temp2 = new Gift(giftName,giftPrice,giftCode);
            this.inStock.add(temp2);
            
         }
      }
      catch(EOFException e)
      {
         System.out.println("End of file has been reached unexpectedly");
         endOfFile = true;
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
         endOfFile = true;
      }
      catch (IOException e)
      {
         System.out.println("Error printing/reading from file");
         endOfFile = true;
      }
      
   }

   public int size()
   {
      return inStock.size();
   }

   public void setGift(Gift gift)
   {
      this.temp = gift;
   }
   public Gift getGift()
   {
      return this.temp;
   }
   public Gift get(int i)
   {
      return inStock.get(i);
   }
   public void setTotalValue(Gift gift)
   {
      this.totalValue = totalValue + gift.getGiftPrice();
      this.totalValue = Math.floor(totalValue * 100)/100;
   }
   public double getTotalValue()
   {
      return this.totalValue;
   }
   
   //Removes gift from Inventory   
   public boolean removeFromInventory(Gift gift)
   {
      int giftCode = gift.getGiftCode();
      int giftCode2;
      Gift g;
      boolean confirmed = false;
      for (int i = 0; i < inStock.size(); i++)
      {
         g = inStock.get(i);
         giftCode2 = g.getGiftCode();
         if (giftCode == giftCode2)
         {
            inStock.remove(i);
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

   //Displays a simplified inventory(won't display all 1000 elements)
   public void displayInventory()
   {

      Gift temp2 = inStock.get(0);
      Gift temp3;
      int count = 0;
      for (int i = 0; i < inStock.size(); i++)
      {
         temp3 = inStock.get(i);
         if (temp2.getGiftCode() == temp3.getGiftCode())
         {
            count++;
         }
         else
         {
            System.out.println(temp2.getGiftName() + " x" + count + " at $" + temp2.getGiftPrice() + " each");
            temp2 = inStock.get(i);
            count = 0;
         }
         
      }
      System.out.println(temp2.getGiftName() + " x" + count + " at $" + temp2.getGiftPrice() + " each");
         
      
   }
   
   //will open the link to the file
   public static DataOutputStream readWriteFile(String fileName)
   {
      try
      {
         File q = new File(fileName);
         
         DataOutputStream overWriteInventory = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(q)));
         return overWriteInventory;
      }
      
      catch(IOException e)
      {
         System.out.println("Error, invalid Input/Output");
         System.exit(0);
      }
      return null;
   }
   //this next method writes to the inventory.txt file
   public static void writeToInventory(Gift gift, DataOutputStream stream)
   {
      try
      {
         stream.writeUTF(gift.getGiftName());
         stream.writeInt(gift.getGiftCode());
         stream.writeDouble(gift.getGiftPrice());
      }
      catch(IOException e)
      {
         System.out.println("Error writing to file");
         System.exit(0);
         
      }
   }
   
      
   //takes care of saving the total revenues to a txt file. It overwrites whatever is already there.
   public static void writeToGiftShopRevenueReport(Double revenue, DataOutputStream stream)
   {
      try
      {
         stream.writeDouble(revenue);
      }
      catch(IOException e)
      {
         System.out.println("Error writing to file");
         System.exit(0);
         
      }
   }
   public static void getGiftShopReport()
   {
      //Opens up the file
      File inventoryFile = new File("GiftShopReport.txt");
      
      boolean endOfFile = false;
      
      //reads numbers from report.
      try
      {
         DataInputStream getReport = new DataInputStream(new BufferedInputStream(new FileInputStream(inventoryFile)));
         
         while(!endOfFile)
         {
            double revenueGift = getReport.readDouble();
         }
      }
      catch(EOFException e)
      {
         System.out.println("End of file has been reached unexpectedly");
         endOfFile = true;
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
         endOfFile = true;
      }
      catch (IOException e)
      {
         System.out.println("Error printing/reading from file");
         endOfFile = true;
      }
      
   }
}