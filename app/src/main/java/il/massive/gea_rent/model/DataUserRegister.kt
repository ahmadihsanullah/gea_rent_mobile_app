package il.massive.gea_rent.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataUserRegister(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable