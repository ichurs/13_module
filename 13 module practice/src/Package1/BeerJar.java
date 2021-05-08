package Package1;

import java.util.Random;

public class BeerJar {

    private int beer = 1000;
    private Object lock = new Object();

    int getBeer(){
        return beer;
    }

    void gulp(int beer){
        synchronized (lock){
            this.beer -= beer;
        }
    }

    void refill(int beer){
        synchronized (lock) {
            this.beer = beer;
        }
    }

    class Student extends Thread {

        private String name;

        Student(String name){
            this.name = name;
        }

        @Override
        public void run(){
            while (true){
                Random random = new Random();
                int gulpSize = random.nextInt(30)+20;
                if(getBeer() >= gulpSize){
                    System.out.println(name + " сделал глоток величиной " + gulpSize + " мл");
                    gulp(gulpSize);
                    System.out.println("В кружке осталось: " + getBeer() + " мл");

                } else {
                    System.out.println("Кружка пуста! " +  name + " идет за Клинским!");
                    refill(1000);
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public BeerJar(){
        new Student("Иванов").start();
        new Student("Петров").start();
    }

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        BeerJar beerjar = new BeerJar();
        while (true){
            Thread.sleep(1000);
        }
    }
}
