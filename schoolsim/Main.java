package schoolsim;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;


public class Main {
    private static String name;
    private static String schoolName;
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    private static int id = 11;
    private static void BorderSpace(){
        Space();
        border();
        Space();
    }
    private static void Space() {
        System.out.println();
    }
    private static void border(){
        System.out.println("********************************************");
    }
    private static void noOption(){
        System.out.println("1. Go back");
        int choice = scan.nextInt();
        switch(choice){
            default:
            MainMenu();
            break;
        }
    }
    public static void main(String[] args) {
        

        /*
         * Great
         * show options
         * See all students
         * Add a new student
         * Remove a student
         * Quit
         */
    
            System.out.println("Hello! Welcome to School Simulator!"+""+
            "\nWhere You are the Principal and you have to manage your students.");
            BorderSpace();
            System.out.println("Enter any key to continue...");
            scan.next();
            System.out.println();
            System.out.println("Lets start by introducing yourself!"+""+
            "\nWhat is your name?");
            name = scan.next();
            name += scan.nextLine();
            Space();
            System.out.println("Good day "+name+"!"+
            "\nNow what about the name for your school?");
            schoolName = scan.nextLine();
            Space();
            System.out.println("Hello "+name+"! and welcome to "+schoolName);
            MainMenu();

    }

    private static void MainMenu(){
        BorderSpace();
        System.out.println("Principal: "+name+
        "\nSchool: "+schoolName);
        Space();
        System.out.println("Main Menu:"+
        "\n\t1. Student List"+
        "\n\t2. Add a Student"+
        "\n\t3. Remove a student"+
        "\n\t4. Quit");
      
        int choice = scan.nextInt();
        
        switch(choice){
            case 1:
            allStudent();
            break;
            case 2:
            addStudent();
            case 3:
           removeStudent();
            break;
            case 4:
            System.out.println("Closing game...");
            System.out.println("Game closed.");
            break;
            default:
            MainMenu();
            break;
        }
        scan.close();
    }

    private static void removeStudent(){
        String name;
        Space();
        System.out.println("Please enter the name of the student:");
        name = scan.next();
        name += scan.nextLine();

        boolean hasFound = false;
        
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (name.equals(s.getName())) {
                iterator.remove();
                hasFound = true;
            }
        }
        

        if(!hasFound){
            Space();
            System.out.println("No student with name "+name+" has been found.");
            noOption();
        }else{
            System.out.println(name+" has been removed to the school!");
            noOption();
        }

    }
    private static void allStudent(){

        if(students.size() < 1) {
            Space();
            System.out.println("There are no students.");
            Space();
            noOption();
        }else{
            Space();
            System.out.println("List of Students:");
            int n = 1;
            System.out.println();
            for(Student s: students){
                System.out.println(n+". "+s.getName());
                n++;
            }
            //noOption();
        }

       
            int choice = scan.nextInt();
       

       
            students.get(choice-1).getStudentDetails().getDetails();
            noOption();
     
    }

    private static void addStudent(){
        String name;
        String gender;
        String talent;
        String fColor;
        String apperance;
        String personality;
        String strand;
        int height = 0;
        int age = 0;
        Space();
        scan.nextLine();
        System.out.println("What is the name of the student?");
        name = scan.next();
        name += scan.nextLine();
        Space();
        System.out.println("Ok the name of the student is "+name+
        ".\nNow what is their gender?\n\n\t1. Male\n\t2. Female\n\t3. Not to say");
        int choice = 0;
        try {
            choice = scan.nextInt();
        } catch (Exception e) {
            Space();
            System.out.println("Choose a number!");
            addStudent();
        }
        
        switch(choice){
            case 1:
            gender = "Male";
            break;
            case 2:
            gender = "Female";
            break;
            default:
            gender = "Not to say";
            break;
        }
        String pronouns[] = new String[3];
        if(choice == 1){
            pronouns[0] = "He";
            pronouns[1] = "His";
            pronouns[2] = "Him";

        }else if(choice == 2){
            pronouns[0] = "She";
            pronouns[1] = "Her";
            pronouns[2] = "Her";
        }else{
            pronouns[0] = "They";
            pronouns[1] = "Their";
            pronouns[2] = "Them";
        }
        
        Space();
         System.out.println("Great! Now we need to know "+name+"'s personalities and the more things about "+pronouns[1].toLowerCase()+"!"+
        "\nWhat is "+pronouns[1].toLowerCase()+" age?");
            try {
                age = scan.nextInt();
            } catch (Exception e) {
                Space();
                System.out.println("Only number is allowed as his age!");
                addStudent();
            }
        
        if(age < 1 || age > 70){
            Space();
            System.out.println(name+" is not at the right age!");
            addStudent();
        }
        Space();
        System.out.println("Oh... so "+name+" is "+age+" years old great!"+
        "\nWhat is "+pronouns[1].toLowerCase()+" favourite color?");
        fColor = scan.next();
        fColor += scan.nextLine();

        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'j', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
    'u', 'v', 'w', 'x', 'y', 'z'};
        boolean isAColor = false;

        for(int i = 0; i < fColor.length(); i++){

            for(int j = 0; j < letters.length; j++){
                if(Character.toLowerCase(fColor.charAt(i)) == letters[j]){
                    isAColor = true;
                }
            }
        }
        
        Space();
        if(isAColor){
            System.out.println("Oh so "+name+" likes the color "+fColor.toLowerCase()+" very cool!");
        }else{
            System.out.println(fColor+" is not a color!");
            addStudent();
        }

        Space();
        System.out.println("What is "+pronouns[1].toLowerCase()+" talent?");
        talent = scan.next();
        talent += scan.nextLine();

        if(talent.equals("")){
            Space();
            System.out.println("Please enter a talent");
            addStudent();
        }
        Space();
        System.out.println("Wow! "+name+" sure is talented.");
        

        Space();
        System.out.println("Can you describe how "+pronouns[1].toLowerCase()+" looks?");
        apperance = scan.next();
        apperance += scan.nextLine();

        if(apperance.equals("")){
            Space();
            System.out.println("Please enter some details!");
            addStudent();
        }
        Space();
        System.out.println("Sounds nice!");
        

        Space();
        System.out.println("Describe "+pronouns[1].toLowerCase()+" personality");
        personality = scan.next();
        personality += scan.nextLine();

        if(personality.equals("")){
            Space();
            System.out.println("Please provide some information");
            addStudent();
        }
        Space();
        System.out.println("Sounds nice!"+
        "\nHow tall is "+name+"?");
        try {
            height = scan.nextInt();
        } catch (Exception e) {
            Space();
            System.out.println("Not a valid input!");
            addStudent();
        }
        Space();
        System.out.println("Very nice! What strand does "+name+" want to take?");
        strand = scan.next();
        strand += scan.nextLine();
        BorderSpace();
        System.out.println("Congratulations! "+name+" is now enrolled to "+schoolName+"."+
        "\nWe wish "+pronouns[2].toLowerCase()+" the best in "+pronouns[1].toLowerCase()+" journey in taking the strand "+strand+" in our school "+schoolName+"!");
        BorderSpace();
        Student newStudent = new Student();
        id+=1;
        newStudent.setDetails(name,strand, fColor, talent, apperance, personality, age, height, gender, id);
        students.add(newStudent);
        noOption();
        
    }
}
