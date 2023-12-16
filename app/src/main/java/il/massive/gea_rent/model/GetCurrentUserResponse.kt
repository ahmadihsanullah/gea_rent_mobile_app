package il.massive.gea_rent.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GetCurrentUserResponse(

	@field:SerializedName("data")
	val data: DataCurrentUSer
) : Parcelable