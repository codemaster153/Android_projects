package com.lkb.prinstarr

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UtilTest {

    @Before
    fun setUp() {

    }

    @Test
    fun `test the absAmount`() {
        assertEquals("1.0", Util.absAmount(-1.0))
        assertEquals("1.0", Util.absAmount(1.0))
    }

    @Test
    fun `test for fromEpochToDate`() {
        val expected = "2021-05-03"
        assertEquals(expected, Util.fromEpochToDate(1620050270))
    }

    @Test
    fun `test for convertStrToEpoch`() {
        val expected = 1609612500000L
        assertEquals(expected, Util.convertStrToEpoch("03-05-2021"))
    }

    @Test
    fun `test for countDaysLeft`() {
        val startDate = 1620820782000L //12-05-2021
        val currentDate = 1620993582000L // 14-05-2021
        val expected = 28 // 28 days left
        assertEquals(expected,Util.countDaysLeft(30,startDate,currentDate))
    }
}