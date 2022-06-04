import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
public class ServerSide {
    public static void main(String[] args) {
        try{
            CivilRegistryBrain systemBrain=new CivilRegistryBrain(); //make object from out class who has the methods
            //  we use the same computer as the client so started the server on port 9000 as the client
            int port = 9000;
            ServerSocket serverSocket=new ServerSocket(port);
            System.out.println("Waiting for client connection");

            // The server waits and listening to the socket for a client to make a connection request
            Socket connectionSocket= serverSocket.accept();
            System.out.println("Client connected");

            // to write and receive messages to and from the client
            DataInputStream inputStream= new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(connectionSocket.getOutputStream());

            loop:while(true) {
                int selectedService = inputStream.readInt();
                switch (selectedService) {
                    case 1:
                        System.out.println("Display alive person's data");
                        long id=inputStream.readLong();                //receive id from client
                       boolean searchDisplayedID = systemBrain.searchAliveID(id); //check if this ID is found
                        while (searchDisplayedID){     //if ID isn't found receive another ID from user
                            outputStream.writeBoolean(searchDisplayedID);
                            id=inputStream.readLong();
                            searchDisplayedID = systemBrain.searchAliveID(id);
                        }
                       outputStream.writeBoolean(searchDisplayedID);

                        String requiredPerson= systemBrain.displayBirthPersonWithID(id);  //use it as a parameter to display method
                        outputStream.writeUTF(requiredPerson);        //send the output of display method to client
                        break ;
                    case 2:
                        System.out.println("Display dead person's data");
                        long ID=inputStream.readLong();              //receive ID from client to display this dead data
                        boolean searchDead = systemBrain.searchDeadID(ID);        //check if this ID is found
                        while (searchDead){               //if ID isn't found receive another ID from user
                            outputStream.writeBoolean(searchDead);
                            ID=inputStream.readLong();
                            searchDead = systemBrain.searchDeadID(ID);
                        }
                        outputStream.writeBoolean(searchDead);

                        String requiredDead= systemBrain.displayDeathPersonWithID(ID);
                        outputStream.writeUTF(requiredDead);
                        break ;
                    case 3:
                        System.out.println("Add new alive person");
                        String name=inputStream.readUTF();
                        long Id= inputStream.readLong();
                       boolean searchID = systemBrain.searchAliveID(Id);  //check if ID is stored before
                        while(!searchID){                        //if ID is stored before receive another ID from user
                            outputStream.writeBoolean(searchID);        //send the result of searchAliveID method  to client
                            Id= inputStream.readLong();                //receive new ID if the ID is stored before
                            searchID=systemBrain.searchAliveID(Id);
                        }
                        outputStream.writeBoolean(searchID); //return the final result of the method to client after store the ID
                        //receive the remainder data from client
                        int day=inputStream.readInt();
                        int month=inputStream.readInt();
                        int year=inputStream.readInt();
                        String address=inputStream.readUTF();
                        String job=inputStream.readUTF();
                        String status=inputStream.readUTF();
                        String gender=inputStream.readUTF();
                        String nationality=inputStream.readUTF();
                        String religion=inputStream.readUTF();
                        String motherName=inputStream.readUTF();
                        String fatherName=inputStream.readUTF();
                        Date date=new Date(day,month,year);
                        //use all this data as parameters to new object of class Person
                        Person newPerson=new Person(name,Id,date,gender,religion,job,status,motherName,fatherName,address,nationality);
                        systemBrain.addAlivePerson(newPerson);   //finally new alive person was added
                        System.out.println("This person was added successfully");
                        break;
                    case 4:     //The same as case 3
                        System.out.println("Add new dead person");
                        String nameOfDead=inputStream.readUTF();
                        long IdOfDead= inputStream.readLong();
                        boolean searchDeadID = systemBrain.searchDeadID(IdOfDead);
                        boolean searchBirthID = systemBrain.searchAliveID(IdOfDead);
                        while(!searchDeadID){   //if ID is stored before receive another correct ID from user
                            outputStream.writeBoolean(searchDeadID);
                            IdOfDead= inputStream.readLong();
                            searchDeadID=systemBrain.searchDeadID(IdOfDead);
                        }
                        outputStream.writeBoolean(searchDeadID);

                        while(searchBirthID){   //if ID is not stored before receive another correct ID from user
                            outputStream.writeBoolean(searchBirthID);
                            IdOfDead= inputStream.readLong();
                            searchBirthID=systemBrain.searchAliveID(IdOfDead);
                        }
                        outputStream.writeBoolean(searchBirthID);
                        //receive the remainder data from client
                        int dayOfDeath=inputStream.readInt();
                        int monthOfDeath=inputStream.readInt();
                        int yearOfDeath=inputStream.readInt();
                        String addressOfDeath=inputStream.readUTF();
                        String jobOfDeath=inputStream.readUTF();
                        String statusOfDeath=inputStream.readUTF();
                        String genderOfDeath=inputStream.readUTF();
                        String nationalityOfDeath=inputStream.readUTF();
                        String religionOfDeath=inputStream.readUTF();
                        String motherNameOfDeath=inputStream.readUTF();
                        String fatherNameOfDeath=inputStream.readUTF();
                        Date dateOfDeath=new Date(dayOfDeath,monthOfDeath,yearOfDeath);
                        Person Death=new Person(nameOfDead,IdOfDead,dateOfDeath,genderOfDeath,religionOfDeath,jobOfDeath
                                ,statusOfDeath,motherNameOfDeath,fatherNameOfDeath,addressOfDeath,nationalityOfDeath);
                        systemBrain.addDeadPerson(Death);         //finally new dead person was added
                        System.out.println("This person was added successfully");
                        break;
                    case 5:
                        System.out.println("Remove person from births");
                        long idOfPerson=inputStream.readLong();   //receive ID of this person from client
                        boolean searchRemovedID = systemBrain.searchAliveID(idOfPerson);     //check if this ID is found
                        while (searchRemovedID){               //if ID isn't found receive another ID from user
                            outputStream.writeBoolean(searchRemovedID);
                            idOfPerson=inputStream.readLong();
                            searchRemovedID = systemBrain.searchAliveID(idOfPerson);
                        }
                        outputStream.writeBoolean(searchRemovedID);

                        String deletedItem =systemBrain.displayBirthPersonWithID(idOfPerson);
                        outputStream.writeUTF(deletedItem);
                        systemBrain.removePersonFromBirths(idOfPerson);  //this person is deleted successfully
                        System.out.println("This item has been deleted from births successfully \n ");
                        break;
                    case 6:
                        System.out.println("Remove person from deaths");
                        long idOfDeadPerson=inputStream.readLong();  //receive ID of this person from client
                        boolean searchRemovedId = systemBrain.searchDeadID(idOfDeadPerson);  //check if this ID is found
                        while (searchRemovedId){               //if ID isn't found receive another ID from user
                            outputStream.writeBoolean(searchRemovedId);
                            idOfDeadPerson=inputStream.readLong();
                            searchRemovedId = systemBrain.searchDeadID(idOfDeadPerson);
                        }
                        outputStream.writeBoolean(searchRemovedId);

                        String deletedPerson =systemBrain.displayDeathPersonWithID(idOfDeadPerson);
                        outputStream.writeUTF(deletedPerson);
                        systemBrain.removePersonFromDeaths(idOfDeadPerson); //this dead is deleted successfully
                        System.out.println("This item has been deleted from deaths successfully \n ");
                        break ;
                    case 7:
                        System.out.println("Update alive person data ");
                        long idPerson=inputStream.readLong();    //receive his ID from client
                        boolean checkRemovedId = systemBrain.searchAliveID(idPerson);  //check if this ID is found
                        while (checkRemovedId){               //if ID isn't found receive another ID from user
                            outputStream.writeBoolean(checkRemovedId);
                            idPerson=inputStream.readLong();
                            checkRemovedId = systemBrain.searchAliveID(idPerson);
                        }
                        outputStream.writeBoolean(checkRemovedId);

                        //Start receive the new data from client as follows
                        String namePerson=inputStream.readUTF();
                        long newId= inputStream.readLong();
                        int dayPerson=inputStream.readInt();
                        int monthPerson=inputStream.readInt();
                        int yearPerson=inputStream.readInt();
                        String addressPerson=inputStream.readUTF();
                        String jobPerson=inputStream.readUTF();
                        String statusPerson=inputStream.readUTF();
                        String genderPerson=inputStream.readUTF();
                        String nationalityPerson=inputStream.readUTF();
                        String religionPerson=inputStream.readUTF();
                        String motherNamePerson=inputStream.readUTF();
                        String fatherNamePerson=inputStream.readUTF();
                        Date datePerson=new Date(dayPerson,monthPerson,yearPerson);
                        Person newData=new Person(namePerson,newId,datePerson,genderPerson,religionPerson
                                ,jobPerson,statusPerson,motherNamePerson,fatherNamePerson,addressPerson,nationalityPerson);
                        systemBrain.updateBirthPerson(idPerson,newData);
                        System.out.println("Data updated successfully");
                        break;
                    case 8:
                        System.out.println("Update dead person data");
                        long deadOldID=inputStream.readLong();    //Receive this person's ID from client
                        boolean checkId = systemBrain.searchDeadID(deadOldID);  //check if this ID is found
                        while (checkId){               //if ID isn't found receive another ID from user
                            outputStream.writeBoolean(checkId);
                            deadOldID=inputStream.readLong();
                            checkId = systemBrain.searchDeadID(deadOldID);
                        }
                        outputStream.writeBoolean(checkId);

                        //Start receive the new data from client as follows
                        String nameOfPerson=inputStream.readUTF();
                        long deadId= inputStream.readLong();
                        int dayOfPerson=inputStream.readInt();
                        int monthOfPerson=inputStream.readInt();
                        int yearOfPerson=inputStream.readInt();
                        String addressOfPerson=inputStream.readUTF();
                        String jobOfPerson=inputStream.readUTF();
                        String statusOfPerson=inputStream.readUTF();
                        String genderOfPerson=inputStream.readUTF();
                        String nationalityOfPerson=inputStream.readUTF();
                        String religionOfPerson=inputStream.readUTF();
                        String motherNameOfPerson=inputStream.readUTF();
                        String fatherNameOfPerson=inputStream.readUTF();
                        Date dateOfPerson=new Date(dayOfPerson,monthOfPerson,yearOfPerson);
                        Person newOfData=new Person(nameOfPerson,deadId,dateOfPerson,genderOfPerson,religionOfPerson
                                ,jobOfPerson,statusOfPerson,motherNameOfPerson,fatherNameOfPerson,addressOfPerson,nationalityOfPerson);
                        systemBrain.updateDeathPerson(deadOldID,newOfData);
                        System.out.println("Data updated successfully");
                        break;
                    case 9:
                        System.out.println("Display number of births");
                        int numberOfBirths= systemBrain.displayNumberOfBirths();
                        outputStream.writeInt(numberOfBirths);
                        break;
                    case 10:
                        System.out.println("Display number of deaths");
                        int numberOfDeaths= systemBrain.displayNumberOfDeaths();
                        outputStream.writeInt(numberOfDeaths);
                        break;
                    case 0:
                        System.out.println(" Exit ");
                        break loop;
                }
            }
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }        //End of main method
}   //End of the ServerSide class
