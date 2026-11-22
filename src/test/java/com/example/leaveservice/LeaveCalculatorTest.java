package com.example.leaveservice;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LeaveCalculatorTest {

    private LeaveCalculator calculator;

    @Before
    public void setUp() {
        calculator = new LeaveCalculator();
    }

    @Test
    public void testCalculateLeaveBalance_ValidInputs() {
        int remaining = calculator.calculateLeaveBalance(20, 7);
        assertEquals(13, remaining);
    }

    @Test
    public void testCalculateLeaveEncashment_ValidInputs() {
        double payout = calculator.calculateLeaveEncashment(10, 1500.0);
        assertEquals(15000.0, payout, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateLeaveBalance_ExceedsAllocated_ThrowsException() {
        calculator.calculateLeaveBalance(15, 18);
    }
}
