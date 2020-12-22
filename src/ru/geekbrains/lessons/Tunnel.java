package ru.geekbrains.lessons;

public class Tunnel extends Stage {

    public Tunnel () {
        this.length = 80 ;
        this.description = "Тоннель " + length + " метров" ;
    }

    @Override
    public void go (Car c) {
        try {
            try {
                if (!Main.tunnel.tryAcquire()){
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    Main.tunnel.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Main.tunnel.release();
                System.out.println(c.getName() + " закончил этап: " +
                        description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
