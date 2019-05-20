package kanglee.com.Utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v4.app.FragmentActivity

class SharedPreferences {

    companion object{

        fun saveSDT(sdt:String, context: FragmentActivity?): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsEditor = prefs.edit()
            prefsEditor.putString(Constants.KEY_SDT,sdt)
            prefsEditor.apply()
            return true
        }


        fun getEmail(context: FragmentActivity?): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
                return prefs.getString(Constants.KEY_SDT, null)

        }


        fun savePassword(password: String, context: FragmentActivity?): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsEditor = prefs.edit()
            prefsEditor.putString(Constants.KEY_PASSWORD, password)
            prefsEditor.apply()
            return true
        }

        fun getPassword(context: Context): String? {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getString(Constants.KEY_PASSWORD, null)

        }
        fun logout(context: FragmentActivity?): Boolean{
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val prefsEditor = prefs.edit()
            prefsEditor.clear()
            prefsEditor.apply()
            return true
        }



    }



}