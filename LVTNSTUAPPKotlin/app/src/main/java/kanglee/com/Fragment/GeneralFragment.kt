package kanglee.com.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kanglee.com.Activity.LoginandRegisterActivity

import kanglee.com.R
import kanglee.com.Utils.SharedPreferences
import kotlinx.android.synthetic.main.fragment_general.view.*


class GeneralFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "Information"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val view =  inflater.inflate(R.layout.fragment_general, container, false)

        view.layoutlogout.setOnClickListener {
            SharedPreferences.logout(activity)
            val LoginandRegisterIntent = Intent(activity,LoginandRegisterActivity::class.java)
            startActivity(LoginandRegisterIntent)
        }









        return  view
    }


}
