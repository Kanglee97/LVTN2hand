package kanglee.com.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import kanglee.com.Fragment.*
import kanglee.com.R
import kanglee.com.Storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListenerr = BottomNavigationView.OnNavigationItemSelectedListener { item->
       when(item.itemId)
        {
            R.id.foryou -> {
                loadFragment(ForYouFragment())
            return@OnNavigationItemSelectedListener true
        }
            R.id.home -> {
                loadFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
           R.id.add-> {
               loadFragment(AddNewFragment())
               return@OnNavigationItemSelectedListener true
           }
            R.id.chat -> {
                loadFragment(ChatFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.list -> {
                loadFragment(GeneralFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation.setOnNavigationItemSelectedListener (onNavigationItemSelectedListenerr)
        loadFragment(ForYouFragment())
        //loadFragment(bottomNavigation.selectedItemId)
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framlayouthome,fragment)
        fragmentTransaction.commit()



    }

    override fun onStart() {
        super.onStart()

        if (SharedPrefManager.getInstance(this).isLoggedIn)
        {
            val HomeIntent = Intent(this, HomeActivity::class.java)
            HomeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(HomeIntent)
        }


    }
}
