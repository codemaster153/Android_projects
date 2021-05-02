package com.lkb.prinstarr.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.lkb.prinstarr.R
import com.lkb.prinstarr.User
import com.lkb.prinstarr.view.detail.UserDetailsFragment
import com.lkb.prinstarr.view.user.CreateUserFragment
import com.lkb.prinstarr.view.user.UserInteractionFragment
import kotlinx.android.synthetic.main.activity_main.*

const val YOUR_FRAGMENT_STRING_TAG = "UserForm"

class MainActivity : BaseActivity(),
    MyDialogFragment.DialogClickListener {
    var dialogFragment: MyDialogFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.setTitle(R.string.app_name)
        dialogFragment = MyDialogFragment()
        dialogFragment!!.setOnDialogClickListener(this)

        user_registration.setOnClickListener {
            startFragment(CreateUserFragment.getInstance())
        }
        view_user.setOnClickListener {
            startFragment(UserDetailsFragment.getInstance())
        }
        logout.setOnClickListener {
            val fm = supportFragmentManager
            dialogFragment!!.show(fm, getString(R.string.dialog_tag))
        }
    }

    override fun onSuccess() {
        signOut()
    }

    override fun onFailure() {
        //do nothing
    }

    override fun onStop() {
        super.onStop()
        dialogFragment?.removeListener()
    }

    private fun startFragment(fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, YOUR_FRAGMENT_STRING_TAG)
        transaction.commit()
    }
    fun startUserTransactionFragment(user: User){
        val bundle = Bundle()
        bundle.putParcelable("user", user)
        val userInteractionFragment = UserInteractionFragment.getInstance()
        userInteractionFragment.arguments = bundle
        startFragment(userInteractionFragment)
    }
    fun startViewAllTransactionFragment(fragment:Fragment){
        startFragment(fragment)
    }
}
