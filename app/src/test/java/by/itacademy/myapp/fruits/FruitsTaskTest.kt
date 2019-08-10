package by.itacademy.myapp.fruits

import org.junit.Assert.assertEquals
import org.junit.Test

class FruitsTaskTest {

    private val testCode0 = listOf(listOf("apple"), listOf("banana"))
    private val testCode1 = listOf(listOf("orange"), listOf("apple", "apple"), listOf("banana", "orange", "apple"))
    private val testCode2 = listOf(listOf("apple", "apple"), listOf("banana", "anything", "banana"))

    private val testShop0 = listOf("orange")
    private val testShop1 = listOf("orange", "apple", "apple", "banana", "orange", "banana")
    private val testShop2 = listOf("orange", "banana", "apple", "apple", "orange", "banana")
    private val testShop3 = listOf("orange", "apple", "apple", "banana", "orange", "apple", "banana")

    private val testClass = FruitsTask()

    @Test
    fun correctTask() {
        assertEquals(1, testClass.checkWin(testCode2, testShop1))
        assertEquals(0, testClass.checkWin(testCode2, testShop2))

        assertEquals(1, testClass.checkWin(testCode1, testShop3))
        assertEquals(0, testClass.checkWin(testCode1, testShop0))

        assertEquals(1, testClass.checkWin(testCode0, testShop2))
        assertEquals(0, testClass.checkWin(testCode0, testShop0))
    }
}