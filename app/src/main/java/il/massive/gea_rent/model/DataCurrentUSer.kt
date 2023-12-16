package il.massive.gea_rent.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataCurrentUSer(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("username")
	val username: String
) : Parcelable