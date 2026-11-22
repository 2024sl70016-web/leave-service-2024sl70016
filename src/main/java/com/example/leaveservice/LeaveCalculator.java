package com.example.leaveservice;

public class LeaveCalculator {

    /**
     * Calculates the remaining leave balance.
     */
    public int calculateLeaveBalance(int totalAllocatedLeaves, int leavesTaken) {
        if (totalAllocatedLeaves < 0 || leavesTaken < 0) {
            throw new IllegalArgumentException("Leave values cannot be negative.");
        }
        if (leavesTaken > totalAllocatedLeaves) {
            throw new IllegalArgumentException("Leaves taken cannot exceed total allocated leaves.");
        }
        return totalAllocatedLeaves - leavesTaken;
    }

    /**
     * Calculates the leave encashment payout based on remaining leave days and daily rate.
     */
    public double calculateLeaveEncashment(int remainingLeaves, double dailyBasicPay) {
        if (remainingLeaves < 0 || dailyBasicPay < 0) {
            throw new IllegalArgumentException("Remaining leaves and daily pay cannot be negative.");
        }
        return remainingLeaves * dailyBasicPay;
    }
}
