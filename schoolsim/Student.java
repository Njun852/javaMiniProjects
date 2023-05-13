package schoolsim;
public class Student {

    private Details StudentDetails;
    private String dialogue;
    private String name;


    public void saySomething(){
        System.out.println(this.dialogue);
    }

    public void setDetails(String name, String strand, String fColor, String talent,
    String apperance, String personality, int age, int height, String gender, int id){

        this.name = name;
        this.StudentDetails.setName(this.name);
        this.StudentDetails.setAge(age);
        this.StudentDetails.setPersonality(personality);
        this.StudentDetails.setApperance(apperance);
        this.StudentDetails.setStrand(strand);
        this.StudentDetails.setFavouriteColor(fColor);
        this.StudentDetails.setTalent(talent);
        this.StudentDetails.setHeight(height);
        this.StudentDetails.setId(id);
        this.StudentDetails.setGender(gender);
        
    }

    public Details getStudentDetails() {
        return StudentDetails;
    }
    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Student(){
        this.StudentDetails = new Details();
    }
}
