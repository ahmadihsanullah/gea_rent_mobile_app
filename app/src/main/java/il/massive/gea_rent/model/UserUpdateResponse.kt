package il.massive.gea_rent.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUpdateResponse(
	val data: DataUserUpdate? = null,
	val message: String? = null
) : Parcelable