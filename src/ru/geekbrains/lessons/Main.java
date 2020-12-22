package ru.geekbrains.lessons;
import java.util.concurrent.*;

public class Main {

    public static final int CARS_COUNT = 4 ;
//    static CountDownLatch startLine = new CountDownLatch(CARS_COUNT);
    static CyclicBarrier roadStage = new CyclicBarrier(CARS_COUNT);
    static Semaphore tunnel = new Semaphore(CARS_COUNT/2, true);
//    static CountDownLatch finishLine = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {

        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!" );

        CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT + 1);

        ArrayBlockingQueue<Car> finished = new ArrayBlockingQueue<>(Main.CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barrier, finished);
            new Thread(cars[i]).start();
        }

//        while (startLine.getCount() > 0){
            try{
                barrier.await();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                barrier.await();
                System.out.println("Победитель: " + finished.take().getName());
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
//        }

//        while (finishLine.getCount() > 0){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!" );

    }
}

