package com.lkb.baseandroidproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.launch

class MainViewModel(private val dataBase: DatabaseReference) : ViewModel() {
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
                dataBase.child(Constants.DB_NAME).get().addOnSuccessListener { it ->
                    val iterator = it.children
                    iterator.forEach {
                        it.getValue(User::class.java)?.let { it1 -> list.add(it1) }
                    }
                    userList.addAll(list)
                    userData.setValue(list)
                }.addOnFailureListener {
                    Log.e("firebase", "Error getting data", it)
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
        dataBase.child(Constants.DB_NAME).child(uuid)
            .setValue(user)
            .addOnSuccessListener {
                result.setValue("Success")

            }
            .addOnFailureListener {
                result.setValue("Failed")
            }
        return result
    }

    fun updateUser(user: User, transaction: Transaction): LiveData<String> {
        user.uuid?.let {
            dataBase.child(Constants.DB_NAME).child(it).child("transactions")
                .child("${Util.getCurrentTimeEpoch()}").setValue(transaction)
                .addOnSuccessListener { result.setValue("Success") }
                .addOnFailureListener { result.setValue("Failed") }
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