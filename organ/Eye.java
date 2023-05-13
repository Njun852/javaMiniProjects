package organ;
class Eye extends Organ{
  
   
    Eye(String name, String condition, String colorname, String color){
        super(name, condition, colorname, color);
        
    }

    void details(){
        System.out.println("Name: "+this.getName());
        System.out.println("Medical Condition: "+this.getCondition());
        System.out.println(this.getDetailname()+": "+this.getDetail());
    }
    void showoptions(){
        System.out.println("t\1. Close the eye");
    }
}