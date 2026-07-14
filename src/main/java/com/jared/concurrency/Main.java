package com.jared.concurrency;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        BookingService unsafeService = new UnsafeBookingService();
        BookingService synchronizedService = new SynchronizedBookingService();
        BookingService lockService = new LockBookingService();

        runSimulation(unsafeService);
        runSimulation(synchronizedService);
        runSimulation(lockService);
    }

    private static void runSimulation(BookingService bookingService) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger successfulBookings = new AtomicInteger(0);
        for(int i=1;i<=50;i++){
            String userName="User-"+ i;
            executor.submit(() -> {
               boolean booked= bookingService.bookSeat(userName);
                if(booked){
                    successfulBookings.incrementAndGet();
                }
            });



        }
        executor.shutdown();
        try{
            executor.awaitTermination(10,TimeUnit.SECONDS);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("\nResult for: " + bookingService.getServiceName());
        System.out.println("Successful bookings: " + successfulBookings.get());
        System.out.println("Available seats left: " + bookingService.getAvailableSeats());




    }

}