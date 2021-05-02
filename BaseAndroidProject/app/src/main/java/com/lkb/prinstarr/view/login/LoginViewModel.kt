package com.lkb.prinstarr.view.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkb.prinstarr.Util
import com.lkb.prinstarr.domain.LocalConfig
import com.lkb.prinstarr.usecase.ConfigUseCase
import com.lkb.prinstarr.usecase.base.UseCaseResponse

class LoginViewModel(
    private val pref: SharedPreferences,
    private val configUseCase: ConfigUseCase
) :
    ViewModel() {
    private var shouldRun = MutableLiveData<Boolean>()
    fun getConfiguration(): LiveData<Boolean> {
        val url = pref.getString("config", "http://localhost/")
        configUseCase.invoke(viewModelScope, url, object : UseCaseResponse<LocalConfig> {
            override fun onSuccess(result: LocalConfig) {
                shouldRun.value = result.shouldRun
                if (result.dbPath != null) {
                    Util.updatePref(pref, "dbPath", result.dbPath)
                }
            }

            override fun onError(apiError: String?) {
                Util.dLog(apiError.toString())
                shouldRun.value = false
            }

        })
        return shouldRun
    }

    fun isStringPrefsPresent(key: String): Boolean {
        return pref.getString(key, "").isNotEmpty()
    }

    fun getPrefValue(key: String): String? {
        return pref.getString(key, "")
    }

    fun createPreference(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun isValidConfig(): Boolean {
        return true
    }

    fun hasRunPermission(): Boolean {
        return true
    }

}