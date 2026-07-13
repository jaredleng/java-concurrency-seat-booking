package com.jared.concurrency;

public class UnsafeBookingService implements BookingService {
    private int availableSeats = 10;



    public boolean bookSeat(String userName){
        if(availableSeats>0){
            simulateDelay();
            availableSeats --;
            System.out.println(userName + " booked a seat. Seats left: " + availableSeats);
            return true;

        }
        System.out.println(userName +"Failed.No seats left");
        return false;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getServiceName() {
        return "Unsafe Booking Service";
    }
    private void simulateDelay(){
        try{
            Thread.sleep(10);

        }catch (InterruptedException e ){
            Thread.currentThread().interrupt();
        }
    }


}
