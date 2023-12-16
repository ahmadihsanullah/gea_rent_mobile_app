package il.massive.gea_rent.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLoginResponse(

	@field:SerializedName("token")
	val token: String
) : Parcelable