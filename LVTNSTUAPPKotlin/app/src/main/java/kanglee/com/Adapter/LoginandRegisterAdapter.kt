package kanglee.com.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kanglee.com.Fragment.LoginFragment
import kanglee.com.Fragment.RegisterFragment

class LoginandRegisterAdapter(private val myContext: Context ,fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(p: Int): Fragment? {
       //To change body of created functions use File | Settings | File Templates.
        when (p) {
            0 -> {
                return LoginFragment()
            }
            1 -> {
                return RegisterFragment()
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
       //To change body of created functions use File | Settings | File Templates.
        return totalTabs
    }
}