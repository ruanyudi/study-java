package cn.ruanyudi.run;
public class RunTest{
    public static void main(String [] args){
        new ThreadOwn().start();
        new ThreadOwn().start();
    }
}

class ThreadOwn extends Thread{
    @Override
    public void run() {
        super.run();
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+" Current step : "+i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}