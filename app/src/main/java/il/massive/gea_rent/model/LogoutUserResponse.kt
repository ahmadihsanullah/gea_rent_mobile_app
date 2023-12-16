package il.massive.gea_rent.model

import com.google.gson.annotations.SerializedName

data class LogoutUserResponse(

	@field:SerializedName("data")
	val data: String
)