package com.jared.concurrency;
import java.util.concurrent.locks.ReentrantLock;
public class LockBookingService implements BookingService {
    private final ReentrantLock lock = new ReentrantLock();
    private int availableSeats = 10;

    public boolean bookSeat(String userName) {
        lock.lock();
        try {
            if(availableSeats>0){
                simulateDelay();
                availableSeats --;
                System.out.println(userName + " booked a seat. Seats left: " + availableSeats);
                return true;
            }
            System.out.println(userName + " failed. No seats left.");
            return false;
        } finally {
            lock.unlock();
        }
    }
    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getServiceName() {
        return "Lock Booking Service";
    }
    private void simulateDelay(){
        try{
            Thread.sleep(10);

        }catch (InterruptedException e ){
            Thread.currentThread().interrupt();
        }
    }
}
