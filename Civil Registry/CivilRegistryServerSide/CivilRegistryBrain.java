import java.util.ArrayList;
// This class has all methods of our project

public class CivilRegistryBrain {
    // Arraylist to store all alive people
    private ArrayList <Person> alivePersonsList = new ArrayList<Person>();

    // Arraylist  to store all deaths
    private ArrayList <Person> deadPeople  = new ArrayList<Person>();

// Methods:-

//add alive person
    public void addAlivePerson (Person person) {
        alivePersonsList.add(person);
    }

//add dead person
    public void addDeadPerson (Person person){
        // add person to deadPeople and remove him from alive people
       removePersonFromBirths(person.getID());
        deadPeople.add(person);
    }

//display alive person's data
    public String displayBirthPersonWithID(long ID){
        String Person = "";     // A string to store all data of this person and return it

        for (int i = 0; i < alivePersonsList.size(); i++) {
            // If statement to find the person who has this ID and store his data to display it
            if (alivePersonsList.get(i).getID() == ID) {
                Person += "Name: "+alivePersonsList.get(i).getName() + "\n"+"ID: "+alivePersonsList.get(i).getID()+"\n"+"Address: "
                        + alivePersonsList.get(i).getAddress() + "\n"+"Birth date: "+alivePersonsList.get(i).getBirthDate().day+
                        " - "+alivePersonsList.get(i).getBirthDate().month+" - " +alivePersonsList.get(i).getBirthDate().year+
                        "\n"+"Gender: "+ alivePersonsList.get(i).getGender() + "\n" +"Status: "+ alivePersonsList.get(i).getStatus()
                        + "\n" +"Nationality: "+ alivePersonsList.get(i).getNationality() + "\n" +"Religion: "+
                        alivePersonsList.get(i).getReligion() + "\n" +"Job: "+ alivePersonsList.get(i).getJob()+ "\n"+"Mother name: "+
                        alivePersonsList.get(i).getMotherName()+"\n"+"Father name: "+alivePersonsList.get(i).getFatherName() + "\n" ;

                break;   //End this loop when find him and store his data
            }
        }
        return Person ;
    }

//display a dead person's data
    public String displayDeathPersonWithID(long ID){
        String Person = "";    //A string to store all this peron's data and return it
        for (int i = 0; i < deadPeople.size(); i++) {
            // If statement to find the person who has this ID
            if (deadPeople.get(i).getID() == ID) {
                Person += "Name: "+deadPeople.get(i).getName() + "\n"+"ID: "+deadPeople.get(i).getID()+"\n"+"Address: "
                        + deadPeople.get(i).getAddress() + "\n"+"Birth date: "+ deadPeople.get(i).getBirthDate().day+
                        " - "+deadPeople.get(i).getBirthDate().month+" - " +deadPeople.get(i).getBirthDate().year+
                        "\n"+"Gender: "+deadPeople.get(i).getGender() + "\n" +"Status: "+ deadPeople.get(i).getStatus()
                        + "\n" +"Nationality: "+ deadPeople.get(i).getNationality() + "\n" +"Religion: "+
                        deadPeople.get(i).getReligion() + "\n" +"Job: "+ deadPeople.get(i).getJob()+ "\n"+"Mother name: "+
                        deadPeople.get(i).getMotherName()+"\n"+"Father name: "+ deadPeople.get(i).getFatherName() + "\n" ;

                break;    // End the loop when find him and store his data
            }
        }
        return Person ;
    }

// Remove person from births
    public void removePersonFromBirths (long ID){
        for (int i = 0; i < alivePersonsList.size(); i++) {
            // If statement to find this person who has this ID
            if (alivePersonsList.get(i).getID() == ID) {
                   alivePersonsList.remove(i);

                break;    // End the loop when find him
            }
        }
    }

// remove person from deaths
    public void removePersonFromDeaths (long ID){
        for (int i = 0; i < deadPeople.size(); i++) {
            // If statement to find the person who has this ID
            if (deadPeople.get(i).getID() == ID) {
                deadPeople.remove(i);

                break;  //End the loop when find and remove it
            }
        }
    }

// Method to check if ID of alive person is stored before
    public boolean searchAliveID(long Id){
        boolean test=true;
        for (int i=0;i<alivePersonsList.size();i++){
            if (Id==alivePersonsList.get(i).getID()){
                test=false;   //if ID is used before end the loop return false
                break;
            }
        }
        return test;
    }

// Method to check if ID of dead person is stored before
    public boolean searchDeadID(long Id){
        boolean test=true;
        for (int i=0;i<deadPeople.size();i++){
            if (Id==deadPeople.get(i).getID()){
                test=false;  //if ID is used before end the loop return false
                break;
            }
        }
        return test;
    }

//update birth person data
    public void updateBirthPerson(long ID,Person newPerson) {
        for (int i = 0; i < alivePersonsList.size(); i++) {
            // If statement to find the person we want to update his data
            if (alivePersonsList.get(i).getID() == ID) {
                alivePersonsList.set(i, newPerson);   // Update the data
                break;    //End the loop when update it
            }
        }
    }

//update death person data
    public void updateDeathPerson(long ID,Person newPerson) {
        for (int i = 0; i < deadPeople.size(); i++) {
            // If statement to find the person we want to update his data
            if (deadPeople.get(i).getID() == ID) {
                deadPeople.set(i,newPerson);   // Update the data
                break;    //End the loop when update it
            }
        }
    }
    //display Number Of Deaths
    public int displayNumberOfDeaths()
    {
        return deadPeople.size();
    }

    //display Number Of Births
    public int displayNumberOfBirths()
    {
        return alivePersonsList.size();
    }

}   // End of the class