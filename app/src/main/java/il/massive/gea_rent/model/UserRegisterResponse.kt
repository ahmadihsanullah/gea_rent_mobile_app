package il.massive.gea_rent.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRegisterResponse(

	@field:SerializedName("data")
	val data: DataUserRegister? = null
) : Parcelable