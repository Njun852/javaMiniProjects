package organ;
import java.util.ArrayList;


class Organ{

    private String name;
    private String condition;
    private String detail;
    private String detailname;

    private ArrayList<String> options = new ArrayList<>();
    private ArrayList<String> behaviour = new ArrayList<>();

    

    Organ(String name, String condition, String detailname, String detail){
        this.name = name;
        this.condition = condition;
        this.detail = detail;
        this.detailname = detailname;
    }
    Organ(){
        this.name = "Body";
    }

    void addbehaviour(String what){
        behaviour.add(what);
    }

    void showbehaviour(int input){
        for(int i = 0; i < options.size(); i++){
            if(input-1 == i){
                System.out.println(behaviour.get(i));
            }
        }
    }

   
    void details(){
        System.out.println("Name: "+this.getName());
        System.out.println("Medical Condition: "+this.getCondition());
        System.out.println(this.getDetailname()+": "+this.getDetail());
    }

    void addoption(String option){
        this.options.add(option);
    }

    void showoptions(){
        int num = 1;
        for(int i = 0; i < options.size(); i++){

            System.out.println("\t"+num+". "+options.get(i));
            num++;
        }
    }

    String getName(){
        return this.name;
    }
    String getCondition(){
        return this.condition;
    }
    String getDetail(){
        return this.detail;
    }
    void setDetail(String detail){
        this.detail = detail;
    }
    String getDetailname(){
        return this.detailname;
    }

    String getBehaviour(int n){
        return this.behaviour.get(n);
    }


}