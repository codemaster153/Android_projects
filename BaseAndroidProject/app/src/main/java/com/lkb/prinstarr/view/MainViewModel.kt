package com.lkb.prinstarr.view

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.lkb.prinstarr.Transaction
import com.lkb.prinstarr.User
import com.lkb.prinstarr.Util
import kotlinx.coroutines.launch

class MainViewModel(private val pref:SharedPreferences,private val dataBase: DatabaseReference) : ViewModel() {
    private var userData: MutableLiveData<List<User>> = MutableLiveData()
    private var result: MutableLiveData<String> = MutableLiveData()
    private var userList: ArrayList<User> = mutableListOf<User>() as ArrayList<User>
    private var allTransactionData = MutableLiveData<List<Transaction>>()
    var selectedUserUUID = ""
    var selectedUser: User? = null
    fun getUserData(): LiveData<List<User>> {
        viewModelScope.launch {
            var list = mutableListOf<User>()
            if (userList.isEmpty()) {
                Util.getDBPath(pref)?.let {
                    dataBase.child(it).get().addOnSuccessListener { it ->
                        val iterator = it.children
                        iterator.forEach {
                            it.getValue(User::class.java)?.let { it1 -> list.add(it1) }
                        }
                        userList.addAll(list)
                        userData.setValue(list)
                    }.addOnFailureListener {
                        Log.e("firebase", "Error getting data", it)
                    }
                }
            } else {
                userData.setValue(userList)
            }
        }
        return userData
    }

    fun createUser(name: String, address: String, phone: String, limit: Double): LiveData<String> {
        val user = User(name, address, phone, limit, null)
        val uuid = Util.generateUserId(
            phone.toLong(),
            user.name!!
        )
        user.uuid = uuid
        Util.getDBPath(pref)?.let {
            dataBase.child(it).child(uuid)
                .setValue(user)
                .addOnSuccessListener {
                    result.setValue("Success")

                }
                .addOnFailureListener {
                    result.setValue("Failed")
                }
        }
        return result
    }

    fun updateUser(user: User, transaction: Transaction): LiveData<String> {
        user.uuid?.let {
            Util.getDBPath(pref)?.let { it1 ->
                dataBase.child(it1).child(it).child("transactions")
                    .child("${System.currentTimeMillis()}").setValue(transaction)
                    .addOnSuccessListener { result.setValue("Success") }
                    .addOnFailureListener { result.setValue("Failed") }
            }
        }
        return result
    }

    fun inValidateData() {
        userList.clear()
        selectedUser = null
    }

    fun getAllTransactionData(): LiveData<List<Transaction>> {
        val list = mutableListOf<Transaction>()
        if (selectedUser == null && selectedUserUUID.isNotEmpty() && userList.isNotEmpty()) {
            userList.forEach {
                if (it.uuid == selectedUserUUID) {
                    selectedUser = it
                }
            }
        }
        selectedUser?.transactions?.forEach { (_, v) -> list.add(v) }
        allTransactionData.value = list
        return allTransactionData
    }
}