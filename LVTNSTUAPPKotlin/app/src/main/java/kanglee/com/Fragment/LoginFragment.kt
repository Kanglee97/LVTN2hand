package kanglee.com.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kanglee.com.Activity.HomeActivity
import kanglee.com.Model.LoginResponse

import kanglee.com.R
import kanglee.com.Remote.RetrofitClient
import kanglee.com.Storage.SharedPrefManager
import kanglee.com.Utils.SharedPreferences
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        if (SharedPreferences.getEmail(activity)!= null){
            val HomeIntent = Intent(activity, HomeActivity::class.java)
            startActivity(HomeIntent)
        }

        view.btnLogin.setOnClickListener {

            val sdt : String = (view.txtSDT_Login.text.toString().trim())
            val pass : String = (view.txtPass_Login.text.toString().trim())

//
            if (sdt.isEmpty())
            {
                view.txtSDT_Login.error = activity?.getText(R.string.error_SDT_isEmpty)
                view.txtSDT_Login.requestFocus()
               // Toast.makeText(activity,"Sai 1", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pass.isEmpty())
            {
                view.txtPass_Login.error = activity?.getText(R.string.error_pass_isEmpty)
                view.txtPass_Login.requestFocus()
               // Toast.makeText(activity,"Sai 2", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if ( sdt.length >11 || sdt.length <10)
            {
                view.txtSDT_Login.error = activity?.getText(R.string.error_SDT_length)
                view.txtSDT_Login.requestFocus()
               // Toast.makeText(activity,"Sai 3 ", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pass.length < 6 || pass.length >20)
            {
                view.txtPass_Login.error = activity?.getText(R.string.error_pass_length)
                view.txtPass_Login.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userslogin(sdt,pass)
                .enqueue(object: Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                        if (response.body()?.message.equals("Auth successful"))
                        {
                            kanglee.com.Utils.SharedPreferences.saveSDT(sdt,activity)
                            SharedPreferences.savePassword(pass,activity)
                            val HomeIntent = Intent(activity, HomeActivity::class.java)
                            HomeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            HomeIntent.putExtra("sdt",sdt)
                            startActivity(HomeIntent)
                            Toast.makeText(activity, response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                        else
                        {
                            Toast.makeText(activity, "Sai roi nha Kung", Toast.LENGTH_LONG).show()
                        }

                    }
                })
        }

        return view
    }


    override fun onStart() {
        super.onStart()

        val sdt : String = (txtSDT_Login.text.toString().trim())

        if (SharedPrefManager.getInstance(activity!!).isLoggedIn)
        {
            var HomeIntent = Intent(activity, HomeActivity::class.java)
            HomeIntent.putExtra("sdt",sdt)
            HomeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(HomeIntent)
        }


    }


}
