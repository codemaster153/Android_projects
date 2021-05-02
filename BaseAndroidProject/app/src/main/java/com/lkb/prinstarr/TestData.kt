package com.lkb.prinstarr

import java.util.*

class TestData {
    companion object{
        fun getUserData(): MutableList<User> {
            val userList = mutableListOf<User>()
            val transaction = Transaction(50.00, Date().time)
            val transaction2 = Transaction(-40.00, Date().time)
            val transctionList = mutableListOf<Transaction>()
            transctionList.add(transaction)
            transctionList.add(transaction2)

            val tl = mutableListOf<Transaction>()
            val t1 = Transaction(1000.00, Date().time)
            val t2 = Transaction(-400.00, Date().time)
            tl.add(t1)
            tl.add(t2)

//            val user = User("Jhon Doe","Bangalore",1000.00,transctionList)
//            val user2 = User("Jane Doe","Baripada",10000.00,tl)
//            userList.add(user)
//            userList.add(user2)
            return userList
        }

    }
}