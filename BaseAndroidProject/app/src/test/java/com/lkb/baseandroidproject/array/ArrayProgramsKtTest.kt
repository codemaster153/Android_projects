package com.lkb.baseandroidproject.array

import org.junit.Assert.*
import org.junit.Test
import quickSortLocal
import rotateArray
import rotateArray1

class ArrayProgramsKtTest {
    @Test
    fun `test_rotation_array_both_array_should_be_equal`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val d = 3
        val expected = arrayOf(4, 5, 6, 1, 2, 3)
        assertArrayEquals(expected, rotateArray(array, d, array.size))
    }

    @Test
    fun `test_rotation_array_both_array_should_be_equal_`() {
        val array = arrayOf(1, 2, 3, 4, 5, 6)
        val d = 3
        val expected = arrayOf(4, 5, 6, 1, 2, 3)
        assertArrayEquals(expected, rotateArray1(array, d, array.size))
    }


    @Test
    fun `test_quick_sort`(){
        //val inputArray = arrayOf(2,1,6,7,4,3)
        val inputArray = arrayOf(7,6,4,3,2,1)
        val expectedOutput = arrayOf(1,2,3,4,6,7)
        assertArrayEquals(expectedOutput, quickSortLocal(inputArray,0,inputArray.size-1))
    }
}