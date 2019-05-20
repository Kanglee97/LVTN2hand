package kanglee.com.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import kanglee.com.Adapter.LoginandRegisterAdapter
import kanglee.com.R
import kanglee.com.Storage.SharedPrefManager

class LoginandRegisterActivity : AppCompatActivity() {

    var tabLayoutLoginandRegister: TabLayout?= null
    var viewPager : ViewPager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginand_register)

        tabLayoutLoginandRegister = findViewById(R.id.tabLayoutLoginandRegister)
        viewPager = findViewById(R.id.viewPagerLoginandRegister)

        tabLayoutLoginandRegister!!.addTab(tabLayoutLoginandRegister!!.newTab().setText(getText(R.string.Name_Button_SignIn)))
        tabLayoutLoginandRegister!!.addTab(tabLayoutLoginandRegister!!.newTab().setText(R.string.Name_Button_SignUp))

        val adapter = LoginandRegisterAdapter(this, supportFragmentManager, tabLayoutLoginandRegister!!.tabCount)
        viewPager!!.adapter = adapter

        tabLayoutLoginandRegister!!.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab) {


                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabUnselected(p0: TabLayout.Tab) {
               //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
                viewPager!!.currentItem  = p0.position
                //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this).isLoggedIn)
        {
            var HomeIntent = Intent(this, HomeActivity::class.java)
            HomeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(HomeIntent)
        }


    }
}
