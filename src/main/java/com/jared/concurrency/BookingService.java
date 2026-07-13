package com.jared.concurrency;

public interface BookingService {
    boolean bookSeat(String userName);
    int getAvailableSeats();
    String getServiceName();
}
