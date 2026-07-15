# Java Concurrency Seat Booking Demo

A simple Java project that demonstrates **race conditions** and how to fix them using **`synchronized`** and **`ReentrantLock`**.

## Overview

This project simulates a seat booking system where:

- **50 users** try to book seats at the same time
- Only **10 seats** are available
- **`ExecutorService`** is used to run multiple booking tasks concurrently

The project compares three implementations:

1. **UnsafeBookingService** — no synchronization
2. **SynchronizedBookingService** — uses **`synchronized`**
3. **LockBookingService** — uses **`ReentrantLock`**

## Problem

In the unsafe version, multiple threads can check and update the same seat count at the same time.

Example:

```java
if (availableSeats > 0) {
    availableSeats--;
}
```

This can cause a **race condition**, where more users successfully book seats than the number of seats available.

## Solution

The project fixes the issue using two thread-safe approaches:

### 1. Using `synchronized`

```java
public synchronized boolean bookSeat(String userName)
```

This allows only **one thread** to run the booking method at a time.

### 2. Using `ReentrantLock`

```java
lock.lock();

try {
    // booking logic
} finally {
    lock.unlock();
}
```

This manually locks and unlocks the critical section.

The **`finally`** block ensures that the lock is released after the booking logic finishes.

## Sample Result

Unsafe version:

```text
Result for: Unsafe Booking Service
Successful bookings: 20
Available seats left: -2
```

This is incorrect because the system only started with **10 seats**, but **20 users** were able to book successfully.

Thread-safe versions:

```text
Result for: Synchronized Booking Service
Successful bookings: 10
Available seats left: 0

Result for: Lock Booking Service
Successful bookings: 10
Available seats left: 0
```

The unsafe result may change on each run because thread scheduling is unpredictable.

## Concepts Demonstrated

- **Java multithreading**
- **Race condition**
- **Thread safety**
- **ExecutorService**
- **synchronized**
- **ReentrantLock**
- **AtomicInteger**

## What I Learned

This project helped me understand how concurrency issues can happen when multiple threads access shared data at the same time.

It also demonstrates how **`synchronized`** and **`ReentrantLock`** can be used to prevent inconsistent results in backend systems such as seat booking, order processing, and transaction handling.
