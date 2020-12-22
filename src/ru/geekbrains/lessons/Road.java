package ru.geekbrains.lessons;

import java.util.concurrent.BrokenBarrierException;

public class Road extends Stage {
    public Road (int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров" ;
    }

    @Override
    public void go (Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000 );
//            Main.roadStage.await();
            System.out.println(c.getName() + " закончил этап: " + description);
            /*интуитивная попытка выполнения проверки окончания проезда финиша всеми участниками*/
//            if (this.length == 40){
//                Main.finishLine.countDown();
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }/*catch (BrokenBarrierException e){
            e.printStackTrace();
        }*/
    }
}
