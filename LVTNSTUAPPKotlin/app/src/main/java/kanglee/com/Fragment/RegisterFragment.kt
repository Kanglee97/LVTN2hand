package kanglee.com.Fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kanglee.com.Activity.LoginandRegisterActivity
import kanglee.com.Model.SignupResponse

import kanglee.com.R
import kanglee.com.Remote.RetrofitClient
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_register.view.*
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
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_register, container, false)

        view.layouthaveanaccount.setOnClickListener {
            val SignInIntenthave= Intent(activity,LoginandRegisterActivity::class.java)
            //SignInIntenthave.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            startActivity(SignInIntenthave)
        }

        view.btnRegister.setOnClickListener {
            val sdt : String = (view.txtSDT_Register.text.toString().trim())
            val pass : String = (view.txtPass_Register.text.toString().trim())
            val passAgian : String = (view.txtPass_Again_Register.text.toString().trim())

            if (sdt.isEmpty())
            {
                view.txtSDT_Register.error = activity?.getText(R.string.error_SDT_isEmpty)
                view.txtSDT_Register.requestFocus()
                //Toast.makeText(activity,"Sai 1", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pass.isEmpty())
            {
                view.txtPass_Register.error = activity?.getText(R.string.error_pass_isEmpty)
                view.txtPass_Register.requestFocus()
                //Toast.makeText(activity,"Sai 2", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (passAgian.isEmpty())
            {
                view.txtPass_Again_Register.error = "mat khau"
                view.txtPass_Again_Register.requestFocus()
                //Toast.makeText(activity,"Sai 3", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if ( sdt.length >11 || sdt.length <10)
            {
                view.txtSDT_Register.error = activity?.getText(R.string.error_SDT_length)
                view.txtSDT_Register.requestFocus()
                //Toast.makeText(activity,"Sai 4", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pass.length < 6 || pass.length >20)
            {
                view.txtPass_Register.error = activity?.getText(R.string.error_pass_length)
                view.txtPass_Register.requestFocus()
                //Toast.makeText(activity,"Sai 5", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!pass.equals(passAgian))
            {
                view.txtPass_Register.error = activity?.getText(R.string.error_pass_pass)
                view.txtPass_Register.requestFocus()
                //Toast.makeText(activity,"Sai 6", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.userssignup(sdt, pass)
                .enqueue(object: Callback<SignupResponse> {
                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                        if (response.body()?.message.equals("User created"))
                        {
                            Toast.makeText(activity, response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                        else if (response.body()?.message.equals("Phone number exists"))
                        {
                            Toast.makeText(activity, response.body()?.message, Toast.LENGTH_LONG).show()
                        }
                        else
                        {
                            Toast.makeText(activity, response.body()?.message, Toast.LENGTH_LONG).show()

                        }
                    }

                })

        }



        return view
    }


}
