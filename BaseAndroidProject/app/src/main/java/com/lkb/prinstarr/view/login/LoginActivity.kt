package com.lkb.prinstarr.view.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.lkb.prinstarr.R
import com.lkb.prinstarr.view.BaseActivity
import com.lkb.prinstarr.view.BottomDialog
import com.lkb.prinstarr.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    private val viewModel by viewModel<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //Remove notification bar
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)
        mAuthListener = AuthStateListener { firebaseAuth: FirebaseAuth ->
            updateUI(firebaseAuth.currentUser)
        }
        btnSetting.setOnClickListener {
            showBottomSheet()
        }
        btnSingIn.setOnClickListener {
            //check for config file

            if (!viewModel.isStringPrefsPresent("config")!!) {
                showBottomSheet()
            } else if (viewModel.isValidConfig() && viewModel.hasRunPermission()) {
                viewModel.getConfiguration().observe(this, {
                    if (it) signIn() else Toast.makeText(
                        this,
                        "You are not Allowed to Log In",
                        Toast.LENGTH_SHORT
                    ).show()
                })
            } else {
                Toast.makeText(this, "you do not have valid config", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                if (result!!.isSuccess) {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = result.signInAccount
                    firebaseAuthWithGoogle(account!!)
                } else {
                    // Google Sign In failed, update UI appropriately
                }
            }
        }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    companion object {
        const val RC_SIGN_IN = 12011
        const val TAG = "LoginActivity"
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id)
        showProgressDialog()
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                Log.d(
                    TAG,
                    "signInWithCredential:onComplete:" + task.isSuccessful
                )
                if (!task.isSuccessful) {
                } else {
                }
                hideProgressDialog()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            //Toast.makeText(this, "User Logged out", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener)
        }
    }

    private fun showBottomSheet() {
        val addBottomDialogFragment: BottomDialog = BottomDialog.getInstance(viewModel)
        addBottomDialogFragment.show(
            supportFragmentManager,
            "BottomDialog"
        )
    }

//    fun createPreference(value: String) {
//        viewModel.createPrefrence("config", value)
//    }


}
