package schoolsim;
public class Details {

    private String favouriteColor;
    private String strand;
    private String personality;
    private String apperance;
    private String talent;
    private String gender;
    private String name;
    private int age;
    private int id;
    private float height;

    public void getDetails(){
        System.out.println("**************************");
        System.out.println("Name: "+this.name+
        "\nStrand: "+this.strand+""+
        "\nAge: "+this.age+""+
        "\nGender: "+this.gender+""+
        "\nId Number: "+this.id+""+
        "\nHeight: "+this.height+"ft"+
        "\nTalent: "+this.talent+""+
        "\nApperance: "+this.apperance+""+
        "\nFavourite Color: "+this.favouriteColor+""+
        "\nPersonality: "+this.personality);
        System.out.println("**************************");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFavouriteColor() {
        return favouriteColor;
    }
    public void setFavouriteColor(String favouriteColor) {
        this.favouriteColor = favouriteColor;
    }
    public String getStrand() {
        return strand;
    }
    public void setStrand(String strand) {
        this.strand = strand;
    }
    public String getPersonality() {
        return personality;
    }
    public void setPersonality(String personality) {
        this.personality = personality;
    }
    public String getApperance() {
        return apperance;
    }
    public void setApperance(String apperance) {
        this.apperance = apperance;
    }
    public String getTalent() {
        return talent;
    }
    public void setTalent(String talent) {
        this.talent = talent;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public float getHeight() {
        return height;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    
}
