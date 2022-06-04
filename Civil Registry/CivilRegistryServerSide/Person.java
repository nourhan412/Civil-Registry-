// A class to implement all setters and getters for our project
public class Person {

//attributes:-
    private String name;
    private  long ID;
    Date birthDate;      // Object of class Date which store day,month and year of birth date which implemented at end
    private String address;
    private String job;
    private String status;
    private String gender;
    private String nationality;
    private String religion;
    private String motherName;
    private String fatherName;

// Constructor:-
    public Person(String name,long ID, Date birthDate, String gender, String religion, String job, String Status,
                  String motherName, String fatherName,String address,String nationality)
    {
        setName(name);
        setID(ID);
        setBirthDate(birthDate);
        setFatherName(fatherName);
        setMotherName(motherName);
        setGender(gender);
        setStatus(Status);
        setReligion(religion);
        setJob(job);
        setNationality(nationality);
        setAddress(address);
    }

 // Setters :-
    public void setName(String name) {
        if(!name.isEmpty())
            this.name = name;
        else
            System.out.println("Name required !");
    }

    public void setID(long ID) {
        if(ID>0l)
            this.ID = ID;
        else
            System.out.println("ID required");
    }

    public void setGender(String gender) {
        if (!gender.isEmpty())
            this.gender = gender;
        else
            System.out.println("Gender required !");
    }

    public void setJob(String job) {
        if(!job.isEmpty())
            this.job = job;
        else
            System.out.println("Job required !");
    }

    public void setReligion(String religion) {
        if(!religion.isEmpty())
            this.religion = religion;
        else
            System.out.println("Religion required !");
    }

    public void setStatus(String status) {
        if(!status.isEmpty())
            this.status = status;
        else
            System.out.println("Status required !");
    }

    public void setMotherName(String motherName) {
        if(!motherName.isEmpty())
            this.motherName = motherName;
        else
            System.out.println("Mother name required!");
    }

    public void setFatherName(String fatherName) {
        if(!fatherName.isEmpty())
            this.fatherName =fatherName;
        else
            System.out.println("Father name required");
    }

    public void setBirthDate(Date birthDate)
    {
        if(birthDate.isEmpty())
            System.out.println("Birth date required !");
        else
            this.birthDate=birthDate;
    }

    public void setAddress(String address)
    {
        if(!address.isEmpty())
            this.address=address;
        else
            System.out.println("Address required !");
    }

    public void setNationality(String nationality)
    {
        if(!nationality.isEmpty())
            this.nationality=nationality;
        else
            System.out.println("Nationality required !");
    }

// getters:-
    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
    }

    public String getGender() {
        return gender;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getReligion() {
        return religion;
    }

    public String getStatus() {
        return status;
    }

    public String getJob() {
        return job;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public String getAddress()
    {
        return address;
    }

    public String getNationality()
    {
        return nationality;
    }


}   //End of Person class

// Class Date which store day,month,year of person birth date
class Date
{
    public  int day;
    public int month;
    public int year;
    public Date(int day,int month,int year)          //constructor
    {
        this.day=day;
        this.month=month;
        this.year=year;
    }
    public boolean isEmpty()
    {
        if((year!=0)&&(day!=0)&&(month!=0)) return false;
        else
            return true;
    }
}   //End of Date class
