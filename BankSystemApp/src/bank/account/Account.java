
package bank.account;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.Random;

public class Account {
    
    private String userName;
    private String password;
    private String identityNO;
    private String accountNo;
    private char gender;
    private double startUpBal;
    private double withdrawLimit;
    private double depLimit;

    public Account(){
        
        startUpBal = 0.0;
        withdrawLimit = 5000;
        depLimit = 5000;
        generateAccountNo();
    }

    public Account(String userName, String password, String identityNO, char gender,
           double withdrawLimit,double depositLimit ){
        
        this.userName = userName;
        this.password = password;
        this.identityNO = identityNO;
        this.gender = gender;
        this.withdrawLimit = withdrawLimit;
        this.depLimit = depositLimit;
        generateAccountNo();
    }

    public void setAccountHolder(String accountHolder) {
        this.userName = accountHolder;
    }

    public void setContact(String contact) {
        this.password = contact;
    }

    public void setIdentityNO(String identityNO) {
        this.identityNO = identityNO;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setStartUpBal(double startUpBal) {
        this.startUpBal = startUpBal;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setBalance(double balance) {
        this.depLimit = balance;
    }
    

    public String getAccountHolder() {
        return userName;
    }

    public String getContact() {
        return password;
    }

    public String getIdentityNO() {
        return identityNO;
    }

    public char getGender() {
        return gender;
    }

    public double getStartUpBal() {
        return startUpBal;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return depLimit;
    }
    
    
    public boolean deposit(double amount){
        
        boolean isDeposited = false;
        
        if(amount <= depLimit){
            
            depLimit  = depLimit + amount;
            isDeposited = true;
        }
       
       //write the transaction 
       writeTransaction("deposit transaction", amount);
       
       return isDeposited;
        
    }
    
    public boolean withdraw(double amount){
        
        boolean isWithdrawed = false;
        
        if(amount < withdrawLimit){
            
            depLimit = depLimit - amount;
            isWithdrawed = true;
            writeTransaction("withdraw transaction", amount);
        }
        
        return isWithdrawed;
    }
     
    public void writeTransaction(String transaction,double amount){
        
        //instance object of the decimal format class
        DecimalFormat dc = new DecimalFormat("R###,###.00");
        
        //instatiating the file object
        File myFile = new File("src/datafiles//statements.txt");
        
        
        try{
            
            FileWriter write = new FileWriter(myFile);
            BufferedWriter writeData = new BufferedWriter(write);
            
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter objct = DateTimeFormatter.ofPattern("dd/month/yyyy HH:mm:ss");
            
            String dateTime = objct.format(date);
            String sign = "";
            
            if(transaction.equalsIgnoreCase("deposit")){
                
                sign = "+";
            }
            else{
                
                sign = "-";
            }
            
            String statement  = "";
            statement = dateTime  + "\t" + transaction + "\t" + sign + " " +
                    dc.format(amount);
            
            writeData.write(statement);
            writeData.newLine();
            writeData.close();
                        
        }
        catch(IOException e){
            
            System.out.println(e.getMessage());
        }
                
    }
    
    public void generateAccountNo(){
        
        String accNum = "";
        Random randomizer = new Random();
        
        int number = randomizer.nextInt(10);
        while(number == 0){
            
            number = randomizer.nextInt(10);
        }
        
        accNum += number;
        
        for(int i = 1; i <= 9; i++){
            
            number = randomizer.nextInt(10);
            accNum += number;
        }
        
        setAccountNo(accNum);
    }

    @Override
    public String toString() {
        return  userName + "," + password + "," + identityNO + "," + accountNo + "," + 
                gender + "," + startUpBal + "," + withdrawLimit + "," + depLimit ;
        
    }
    
}
