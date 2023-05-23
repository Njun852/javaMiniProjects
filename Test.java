import java.util.Scanner;
class Test extends Thread implements Runnable {

    int time = 0;
    boolean isFinish = false;

    public void run(){
       while(!isFinish){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        time+=1;
       }
    }

    public static void main(String args[]){

        Test t1 = new Test();
        t1.start();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 'ok' to exit");
        String a = scan.nextLine();
        if(a.equals("ok")){
            t1.isFinish = true;
            scan.close();
        }
        System.out.println(t1.time);


       }
    }

