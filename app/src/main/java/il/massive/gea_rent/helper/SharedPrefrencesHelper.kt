package il.massive.gea_rent.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPrefrencesHelper(context: Context) {
    private val PREFS_NAME = "shared_prefs_gearent"
    val sharedPref : SharedPreferences
    val editor : SharedPreferences.Editor
    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }
    fun put(key: String, value: String){
        editor.putString(key,value)
            .apply()
    }
    fun getString(key:String):String? {
        return sharedPref.getString(key, null)
    }
    fun put(key: String, value: Boolean){
        editor.putBoolean(key,value)
            .apply()
    }
    fun getBoolean(key:String):Boolean{
        return sharedPref.getBoolean(key, false)
    }

    fun clear(){
        editor.clear()
            .apply()
    }
}