import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
//class of client who connected with the server
public class SocketClientSide {
    public static void main(String[] args) {
        try {
            //make new socket and connect between it and the server with port number
            Socket clientSocket = new Socket("localhost", 9000);

            //  send and receive the output through the socket.
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

            // receive input from user
            Scanner input = new Scanner(System.in);

            System.out.println("********************************************************");
            System.out.println("*            Welcome to Civil Registry system          *");
            System.out.println("********************************************************");

            loop: while (true) {
                displayServices();      //method implemented at end to display system services

                int selectedService = input.nextInt();      //receive number of service from user
                outputStream.writeInt(selectedService);    //send number of service to server

                switch (selectedService) {
                    case 1:                /* display alive person's data */
                        System.out.println("Please enter ID number ");
                        String id = input.next();
                        outputStream.writeLong(checkId(id));
                        boolean checkID=inputStream.readBoolean();  //find out if this ID is correct or not
                        while(checkID)  //if this ID isn't found receive correct ID from user
                        {
                            System.out.println("This ID isn't found. Please try to enter correct ID");
                             id = input.next();        //receive new ID from user
                            outputStream.writeLong(checkId(id));    //send the new ID to server
                            checkID=inputStream.readBoolean(); //check again the new ID
                        }

                        String requiredPerson = inputStream.readUTF();
                        System.out.println(requiredPerson);
                        break;
                    case 2:                /* Display dead person's data */
                        System.out.println("Please enter ID number");
                        String ID = input.next();
                        outputStream.writeLong(checkId(ID));
                        boolean checkId=inputStream.readBoolean();  //find out if this ID is correct or not
                        while(checkId)  //if this ID isn't found receive correct ID from user
                        {
                            System.out.println("This ID isn't found. Please try to enter correct ID");
                            ID = input.next();        //receive new ID from user
                            outputStream.writeLong(checkId(ID));    //send the new ID to server
                            checkId=inputStream.readBoolean(); //check again the new ID
                        }

                        String deadPerson = inputStream.readUTF();
                        System.out.println(deadPerson);
                        break;
                    case 3:                /* Add new birth person */
                        System.out.println("Enter the name");
                        String name= input.next();
                        outputStream.writeUTF(name);
                        System.out.println("Enter the ID number");
                    /* check the ID before ( contain 14 digits) sending it to server */
                        String Id = input.next();
                        long newId =checkId(Id);                  //method implemented at end to return a correct id
                        outputStream.writeLong(newId);                 //send the correct id to server
                        boolean searchID =inputStream.readBoolean();    //return if ID is used before or not
                        while(!searchID) {
                            System.out.println("Please try to enter correct ID which didn't exist before ");
                            Id = input.next();
                            newId =checkId(Id);                     //check the new id
                            outputStream.writeLong(newId);         //send the new id to server
                            searchID =inputStream.readBoolean();   //find out if ID is used before or not
                        }
                        System.out.println("Please enter the birthdate :");
                        System.out.println("Enter the month");
                        int month=input.nextInt();
                        month=checkMonth(month);         //method to check and return the correct month
                        System.out.println("Enter the day");
                        int day=input.nextInt();
                        checkDay(month,day);       //method to check and return the correct day
                        System.out.println("Enter the year");
                        int year= input.nextInt();
                        System.out.println("Now Enter the address");
                        String address=input.next();
                        System.out.println("Enter the job");
                        String job=input.next();
                        System.out.println("Enter the status");
                        String status=input.next();
                        System.out.println("Enter the gender");
                        String gender=input.next();
                        System.out.println("Enter the nationality");
                        String nationality=input.next();
                        System.out.println("Enter the religion");
                        String religion=input.next();
                        System.out.println("Enter mother's name ");
                        String motherName=input.next();
                        System.out.println("Enter father's name ");
                        String fatherName=input.next();
                       //send the remainder data to server
                        outputStream.writeInt(day);
                        outputStream.writeInt(month);
                        outputStream.writeInt(year);
                        outputStream.writeUTF(address);
                        outputStream.writeUTF(job);
                        outputStream.writeUTF(status);
                        outputStream.writeUTF(gender);
                        outputStream.writeUTF(nationality);
                        outputStream.writeUTF(religion);
                        outputStream.writeUTF(motherName);
                        outputStream.writeUTF(fatherName);
                        System.out.println("This person was added successfully\n");
                        break;
                    case 4:                 /* Add new dead person */
                        System.out.println("Enter the name");
                        String deadName = input.next();
                        outputStream.writeUTF(deadName);
                        System.out.println("Enter the ID number");
                        /* check the ID before ( contain 14 digits) sending it to server */
                        String deadId = input.next();
                        long newDeadId =checkId(deadId);       //method implemented at end to return a correct id
                        outputStream.writeLong(newDeadId);               //send the correct id to server
                        boolean searchDeadID =inputStream.readBoolean();  //return if ID is used before or not
                        boolean searchBirthID=inputStream.readBoolean();
                        while(!searchDeadID) {
                            System.out.println("Please try to enter correct ID which didn't exist before ");
                            deadId = input.next();
                            newDeadId =checkId(deadId);                   //check the new id
                            outputStream.writeLong(newDeadId);           //send the new id to server
                            searchDeadID =inputStream.readBoolean();    //find out if ID is used before or not
                        }
                        while(searchBirthID) {
                            System.out.println("Please try to enter correct ID which stored before in births ");
                            deadId = input.next();
                            newDeadId =checkId(deadId);                   //check the new id
                            outputStream.writeLong(newDeadId);           //send the new id to server
                            searchBirthID =inputStream.readBoolean();    //find out if ID is stored before or not
                        }
                        System.out.println("Please enter the birthdate :");
                        System.out.println("Enter the month");
                        int deadMonth = input.nextInt();
                        deadMonth=checkMonth(deadMonth);                      //methods to check and return the correct month
                        System.out.println("Enter the day");
                        int deadDay = input.nextInt();
                        checkDay(deadMonth,deadDay);      //methods to check and return the correct day
                        System.out.println("Enter the year");
                        int deadYear = input.nextInt();
                        System.out.println("Now Enter the address");
                        String deadAddress = input.next();
                        System.out.println("Enter the job");
                        String deadJob = input.next();
                        System.out.println("Enter the status");
                        String deadStatus = input.next();
                        System.out.println("Enter the gender");
                        String deadGender = input.next();
                        System.out.println("Enter the nationality");
                        String deadNationality = input.next();
                        System.out.println("Enter the religion");
                        String deadReligion = input.next();
                        System.out.println("Enter mother's name");
                        String deadMotherName = input.next();
                        System.out.println("Enter father's name");
                        String deadFatherName = input.next();
                        //send the remainder data to server
                        outputStream.writeInt(deadDay);
                        outputStream.writeInt(deadMonth);
                        outputStream.writeInt(deadYear);
                        outputStream.writeUTF(deadAddress);
                        outputStream.writeUTF(deadJob);
                        outputStream.writeUTF(deadStatus);
                        outputStream.writeUTF(deadGender);
                        outputStream.writeUTF(deadNationality);
                        outputStream.writeUTF(deadReligion);
                        outputStream.writeUTF(deadMotherName);
                        outputStream.writeUTF(deadFatherName);
                        System.out.println("This person was added successfully\n");
                        break;
                    case 5:               /* Remove person from births */
                        System.out.println("Enter ID number of this person");
                        String idOfPerson = input.next();
                        outputStream.writeLong(checkId(idOfPerson));
                        boolean checkUpdateId=inputStream.readBoolean();  //find out if this ID is correct or not
                        while(checkUpdateId)  //if this ID isn't found receive correct ID from user
                        {
                            System.out.println("This ID isn't found. Please try to enter correct ID");
                            idOfPerson = input.next();        //receive new ID from user
                            outputStream.writeLong(checkId(idOfPerson));    //send the new ID to server
                            checkUpdateId=inputStream.readBoolean(); //check again the new ID
                        }

                        String deletedItem = inputStream.readUTF();
                        System.out.println(deletedItem);
                        System.out.println("This item has been deleted from births successfully \n ");
                        break;
                    case 6:               /* Remove person from deaths */
                        System.out.println("Enter ID number of this person");
                        String idOfDeadPerson = input.next();
                        outputStream.writeLong(checkId(idOfDeadPerson));
                        boolean checkRemovedID=inputStream.readBoolean();  //find out if this ID is correct or not
                        while(checkRemovedID)  //if this ID isn't found receive correct ID from user
                        {
                            System.out.println("This ID isn't found. Please try to enter correct ID");
                            idOfDeadPerson = input.next();        //receive new ID from user
                            outputStream.writeLong(checkId(idOfDeadPerson));    //send the new ID to server
                            checkRemovedID=inputStream.readBoolean(); //check again the new ID
                        }

                        String dItem = inputStream.readUTF();
                        System.out.println(dItem);
                        System.out.println("This item has been deleted from deaths successfully \n ");
                        break;
                    case 7:               /* Update alive person data */
                        System.out.println("Enter ID number of this person ");
                        String idPerson = input.next();
                        outputStream.writeLong(checkId(idPerson));  //send the ID of person we want to update his data to server
                        boolean checkUpdateID=inputStream.readBoolean();  //find out if this ID is correct or not
                        while(checkUpdateID)  //if this ID isn't found receive correct ID from user
                        {
                            System.out.println("This ID isn't found. Please try to enter correct ID");
                            idPerson = input.next();        //receive new ID from user
                            outputStream.writeLong(checkId(idPerson));    //send the new ID to server
                            checkUpdateID=inputStream.readBoolean(); //check again the new ID
                        }

                        System.out.println("Enter the new data : ");
                        System.out.println("Enter the name");
                        String updatedName = input.next();
                        outputStream.writeUTF(updatedName);
                        System.out.println("Enter The ID number");
                        String updatedId = input.next();
                        long newID =checkId(updatedId);             //check and return the correct ID
                        outputStream.writeLong(newID);             //send it to server
                        System.out.println("Enter the birthdate :");
                        System.out.println("Enter the month");
                        int updatedMonth = input.nextInt();
                        updatedMonth=checkMonth(updatedMonth);
                        System.out.println("Enter the day");
                        int updatedDay = input.nextInt();
                        checkDay(updatedMonth,updatedDay);
                        System.out.println("Enter the year");
                        int updatedYear = input.nextInt();
                        System.out.println("Now enter the address");
                        String updatedAddress = input.next();
                        System.out.println("Enter the job");
                        String updatedJob = input.next();
                        System.out.println("Enter the status");
                        String updatedStatus = input.next();
                        System.out.println("Enter the gender");
                        String updatedGender = input.next();
                        System.out.println("Enter the nationality");
                        String updatedNationality = input.next();
                        System.out.println("Enter the religion");
                        String updatedReligion = input.next();
                        System.out.println("Enter mother's name ");
                        String updatedMotherName = input.next();
                        System.out.println("Enter father's name ");
                        String updatedFatherName = input.next();
                        //send the remainder data to server
                        outputStream.writeInt(updatedDay);
                        outputStream.writeInt(updatedMonth);
                        outputStream.writeInt(updatedYear);
                        outputStream.writeUTF(updatedAddress);
                        outputStream.writeUTF(updatedJob);
                        outputStream.writeUTF(updatedStatus);
                        outputStream.writeUTF(updatedGender);
                        outputStream.writeUTF(updatedNationality);
                        outputStream.writeUTF(updatedReligion);
                        outputStream.writeUTF(updatedMotherName);
                        outputStream.writeUTF(updatedFatherName);
                        System.out.println("Data updated successfully\n");
                        break;
                    case 8:             /* Update dead person data */
                        System.out.println("Enter the ID number of this person ");
                        String idDeadPerson = input.next();
                        outputStream.writeLong(checkId(idDeadPerson));  //send ID of dead we want to update his data to server
                        boolean checkUpdatedId=inputStream.readBoolean();  //find out if this ID is correct or not
                        while(checkUpdatedId)  //if this ID isn't found receive correct ID from user
                        {
                            System.out.println("This ID isn't found. Please try to enter correct ID");
                            idDeadPerson = input.next();        //receive new ID from user
                            outputStream.writeLong(checkId(idDeadPerson));    //send the new ID to server
                            checkUpdatedId=inputStream.readBoolean(); //check again the new ID
                        }

                        System.out.println("Enter the new data ");
                        System.out.println("Enter the name");
                        String newName = input.next();
                        outputStream.writeUTF(newName);
                        System.out.println("Enter the ID number");
                        String updatedDeadId = input.next();
                        long newUpdatedDeadId =checkId(updatedDeadId);           //check and return the correct ID
                        outputStream.writeLong(newUpdatedDeadId);                //send the correct ID to server
                        System.out.println("Enter the birthdate :");
                        System.out.println("Enter the month");
                        int newMonth = input.nextInt();
                        newMonth=checkMonth(newMonth);
                        System.out.println("Enter the day");
                        int newDay = input.nextInt();
                        checkDay(newMonth,newDay);
                        System.out.println("Enter the year");
                        int newYear = input.nextInt();
                        System.out.println("Now enter the address");
                        String newAddress = input.next();
                        System.out.println("Enter the job");
                        String newJob = input.next();
                        System.out.println("Enter the status");
                        String newStatus = input.next();
                        System.out.println("Enter the gender");
                        String newGender = input.next();
                        System.out.println("Enter the nationality");
                        String newNationality = input.next();
                        System.out.println("Enter the religion");
                        String newReligion = input.next();
                        System.out.println("Enter mother's name ");
                        String newMotherName = input.next();
                        System.out.println("Enter father's name ");
                        String newFatherName = input.next();
                        //send the remainder data to server
                        outputStream.writeInt(newDay);
                        outputStream.writeInt(newMonth);
                        outputStream.writeInt(newYear);
                        outputStream.writeUTF(newAddress);
                        outputStream.writeUTF(newJob);
                        outputStream.writeUTF(newStatus);
                        outputStream.writeUTF(newGender);
                        outputStream.writeUTF(newNationality);
                        outputStream.writeUTF(newReligion);
                        outputStream.writeUTF(newMotherName);
                        outputStream.writeUTF(newFatherName);
                        System.out.println("Data updated successfully\n");
                        break;
                    case 9:          /* Display number of births */
                        int numberOfBirths = inputStream.readInt();
                        System.out.println("Number of births is: " + numberOfBirths);
                        break;
                    case 10:        /* Display number of deaths  */
                        int numberOfDeaths = inputStream.readInt();
                        System.out.println("Number of deaths is: " + numberOfDeaths);
                        break;
                    case 0:         /* Exit */
                        break loop;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//method to display system services
    public static void displayServices() {
        System.out.println("How can we help you!");
        System.out.println("1 - Display alive person's data ");
        System.out.println("2 - Display dead person's data ");
        System.out.println("3 - Add new birth person ");
        System.out.println("4 - Add new dead person ");
        System.out.println("5 - Remove person from births ");
        System.out.println("6 - Remove person from deaths ");
        System.out.println("7 - Update alive person data ");
        System.out.println("8 - Update dead person data ");
        System.out.println("9 - Display number of births ");
        System.out.println("10 - Display number of deaths ");
        System.out.println("0 - Exit ");
    }

//method to check if ID is string or long and return correct ID
    public static long checkId(String Id){
        Scanner input=new Scanner(System.in);
        while (true){
            if(Id.matches("^\\d+$"))   //if ID is string of long number
            {
                /* convert it to long number and check it with our method CheckId which implemented down */
                CheckId(Long.parseLong(Id));
                break ;
            }
            else     //if it string of characters !
            {
                System.out.println("please enter your Id again and it must be numbers not string");
                Id=input.next();        //receive another correct ID from user
            }
        }
        return Long.parseLong(Id);   //return the correct ID after checking process
    }

//method to check if ID is containing 14 digits and return correct ID
    public static long CheckId(long ID) {
        Scanner input = new Scanner(System.in);
        String newID;
        while (true) {
            if (ID > 0l && numOfDigits(ID) == 14)
                break;
            else {
                System.out.println("Please enter correct Id");
                 newID = input.next();   //receive new correct ID from user
                 checkId(newID);        //check the new ID if it string or long again
                 break;
            }
        }
        return ID;     //return the correct ID
    }

//method to count the number of digits in a long number
public static int numOfDigits(long number) {
        int count = 0;
        do {
            count++;
            number /= 10L;    // Remove last digit from number
        } while (number != 0L);

        return count;
    }

 // Method to check if the day is correct or not
    public static int checkDay(int month, int day) {
        while(true) {
            int flag =0;
            if (month == 1 && day <= 31) flag = 1;
            else if (month == 2 &&  day <= 29) flag = 1;
            else if (month == 3 &&  day <= 31) flag = 1;
            else if (month == 4 &&  day <= 30) flag = 1;
            else if (month == 5 &&  day <= 31) flag = 1;
            else if (month == 6 &&  day <= 30) flag = 1;
            else if (month == 7 &&  day <= 31) flag = 1;
            else if (month == 8 &&  day <= 31) flag = 1;
            else if (month == 9 &&  day <= 30) flag = 1;
            else if (month == 10 && day <= 31) flag = 1;
            else if (month == 11 && day <= 30) flag = 1;
            else if (month == 12 && day <= 31) flag = 1;
            if (flag ==1)
            break;
            else
            {
                System.out.println("Please enter correct day !");
                Scanner input = new Scanner(System.in);
                 day=input.nextInt();
            }
        }
        return day;
    }

//method to check if month is correct or not
    public static int checkMonth(int month) {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            if(month<=12) break;
            else {
                System.out.println("Please enter correct month !");
                month =input.nextInt();     //receive correct month from user
            }
        }
    return month;
    }

}    //End of the class