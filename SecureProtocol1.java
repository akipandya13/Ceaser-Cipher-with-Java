//Akshay Pandya Secure Protocol Assignment 1

import java.math.BigInteger;
import java.util.Scanner;

public class SecureProtocol1 {
    
    //Right shift function used for Encryption and decryption
    String shiftright(String msg, int key){
         String out = "";
         int len = msg.length();
         int i=0;
         while(i<len)
         {
             int letter = msg.charAt(i);
             if(Character.isUpperCase(letter))
             {
                 letter = letter+(key%26);
                 if(letter>'Z')
                 letter=letter-26;
             }
             else if(Character.isLowerCase(letter))
             {
                 letter = letter+(key%26);
                 if(letter>'z')
                 letter=letter-26;
             }
            out = out + (char)letter; 
            i++;
         }
         return out;
     }
    
    //Left shift function used for Encryption and decryption
    String shiftleft(String msg, int key){
         String out = "";
         int len = msg.length();
         int i=0;
         while(i<len)
         {
             int letter = msg.charAt(i);
             if(Character.isUpperCase(letter))
             {
                 letter = letter-(key%26);
                 if(letter<'A')
                 letter=letter+26;
             }
             else if(Character.isLowerCase(letter))
             {
                 letter = letter-(key%26);
                 if(letter<'a')
                 letter=letter+26;
             }
            out = out + (char)letter; 
            i++;
         }
         return out;
     }
    
    //Encryption with one key and one direction
    String encrypt(String M, int key, char direction){
        String encrypted_M = new String();
        if(direction == 'r' || direction == 'R')
            encrypted_M = shiftright(M,key);
        else if(direction == 'l' || direction == 'L')
            encrypted_M = shiftleft(M,key);
        return encrypted_M;
    }
    
    //Decryption with one key and direction
    String decrypt(String ciphertext, int key, char direction){
        String decrypted_M = new String();
        if(direction == 'r' || direction == 'R')
            decrypted_M = shiftleft(ciphertext,key);
        else if(direction == 'l' || direction == 'L')
            decrypted_M = shiftright(ciphertext,key);
        return decrypted_M;
    }
    
    //Encryption with two keys and two directions
    String encryptmultiple(String M, int key1,int key2,char direction1, char direction2){
        String encrypted_M = new String();
        if(direction1 == 'r' || direction1 == 'R')
            encrypted_M = shiftright(M,key1);
        else if(direction1 == 'l' || direction1 == 'L')
            encrypted_M = shiftleft(M,key1);
        M = encrypted_M;
        if(direction2 == 'r' || direction2 == 'R')
            encrypted_M = shiftright(M,key2);
        else if(direction2 == 'l' || direction2 == 'L')
            encrypted_M = shiftleft(M,key2);
        return encrypted_M;
    }
    
    //Decryption with two keys and two directions
    String decryptmultiple(String ciphertext, int key1,int key2,char direction1, char direction2){
        String encrypted_M = new String();
        if(direction1 == 'r' || direction1 == 'R')
            encrypted_M = shiftleft(ciphertext,key1);
        else if(direction1 == 'l' || direction1 == 'L')
            encrypted_M = shiftright(ciphertext,key1);
        ciphertext = encrypted_M;
        if(direction2 == 'r' || direction2 == 'R')
            encrypted_M = shiftleft(ciphertext,key2);
        else if(direction2 == 'l' || direction2 == 'L')
            encrypted_M = shiftright(ciphertext,key2);
        return encrypted_M;
    }
 
    //Temper function which charlie works with
    char[] temper(char[] ciphertext){
        String encrypted_text = new String(ciphertext);
        String binary = new BigInteger(encrypted_text.getBytes()).toString(2);
        System.out.println("The bit string before flipping\n"+binary);
        char[] binary_array = binary.toCharArray();
        if(binary.length()>255)
        {
            int j=0;
            while(j<256)
            {
                if(binary_array[j]== '0')
                    binary_array[j] = '1';
                else if(binary_array[j] == '1')
                    binary_array[j] = '0';
                j++;
            }
        }
        else if(binary.length()==255)
        {
            int j=0;
            while(j<255)
            {
                if(binary_array[j]== '0')
                    binary_array[j] = '1';
                else if(binary_array[j] == '1')
                    binary_array[j] = '0';
                j++;
            }
        }
        String binary_flip = new String(binary_array);
        System.out.println("The bit String after flipping\n"+binary_flip);
        String tempered = new String(new BigInteger(binary_flip, 2).toByteArray());
        return tempered.toCharArray();
    }   

    //Main Function
    public static void main(String[] args) {
        //Declaration of all the variables
        String M;
        int key,key1,key2;
        char direction,direction1,direction2;
        //Creating the class object to call methods
        SecureProtocol1 xyz = new SecureProtocol1();
        
        //Taking all the user inputs
        Scanner string = new Scanner(System.in);
        Scanner keys = new Scanner(System.in);
        Scanner directions = new Scanner(System.in);
        System.out.println("Enter the input message string : \t");
        M = string.nextLine();
        while(M.length()<32)
        {
            System.out.println("Please enter string atleast with 32 characters.");
            System.out.println("Enter the input message string : \t");
            M = string.nextLine();
        }
        System.out.println("Enter integer key for single round: \t");
        while(!keys.hasNextInt())
        {
            System.out.println("Enter an integer for key");
            keys.next();
        }
        key = keys.nextInt();
        System.out.println("Enter the direction for single round encryption: \t");
        direction = directions.next().charAt(0);
        boolean ans = false;
        while(ans != true)
        {
            if(direction == 'r' || direction == 'R' || direction == 'L' || direction == 'l')
            {
                ans = true;
                break;
            }
            System.out.println("Enter l or L for leftshift and R or r for right shift.");
            direction = directions.next().charAt(0);
        }
        System.out.println("Enter key1 for double round : \t");
        while(!keys.hasNextInt())
        {
            System.out.println("Enter an integer for key");
            keys.next();
        }
        key1 = keys.nextInt();
        System.out.println("Enter key2 for double round: \t");
        while(!keys.hasNextInt())
        {
            System.out.println("Enter an integer for key");
            keys.next();
        }
        key2 = keys.nextInt();
        System.out.println("Enter the direction 1 for double round: \t");
        direction1 = directions.next().charAt(0);
        ans = false;
        while(ans != true)
        {
            if(direction1 == 'r' || direction1 == 'R' || direction1 == 'L' || direction1 == 'l')
            {
                ans = true;
                break;
            }
            System.out.println("Enter l or L for leftshift and R or r for right shift.");
            direction1 = directions.next().charAt(0);
        }
        System.out.println("Enter the direction 2 for double round: \t");
        direction2 = directions.next().charAt(0);
        ans = false;
        while(ans != true)
        {
            if(direction2 == 'r' || direction2 == 'R' || direction2 == 'L' || direction2 == 'l')
            {
                ans = true;
                break;
            }
            System.out.println("Enter l or L for leftshift and R or r for right shift.");
            direction2 = directions.next().charAt(0);
        }
        
        //Problem 2
        String problem2 = xyz.encrypt(M, key, direction);
        System.out.println("Problem 2:\n\nThe message is \n"+M+"\nand the encryption is\n"+problem2);
        
        //Problem 3
        String problem3 = xyz.decrypt(problem2, key, direction);
        System.out.println("\nProblem 3:\nThe ciphertext is \n"+problem2+"\nand the decryption is\n"+problem3);
        
        //Problem 4
        String problem4 = xyz.encryptmultiple(M,key1,key2,direction1,direction2);
        System.out.println("\nProblem 4:\nThe message is \n"+M+"\nand the encryption after double round is\n"+problem4);
        
        //Problem 5
        String problem5 = xyz.decryptmultiple(problem4, key1, key2, direction1, direction2);
        System.out.println("\nProblem 5:\nThe ciphertext of double round is \n"+problem4+"\nand the decryption is\n"+problem5);
        
        //Problem 6
        System.out.println("\nProblem 6:");
        String problem6 = new String(xyz.temper(problem2.toCharArray()));
        System.out.println("\nThe ciphertext after single round encryption is\n"+problem2+"\nand tempered text is\n"+problem6);
        
        //Problem 7
        String problem7 = xyz.decrypt(problem6, key, direction);
        System.out.println("\nProblem 7:\n\nThe tempered ciphertext is \n"+problem6+"\nand the decryption of tempered ciphertext is\n"+problem7);       
    }
}