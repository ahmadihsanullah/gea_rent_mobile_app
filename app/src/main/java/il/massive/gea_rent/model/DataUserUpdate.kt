package il.massive.gea_rent.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataUserUpdate(
	val profile: String? = null,
	val name: String? = null,
	var statusToko: Boolean = false,
	var password: String? = null,
) : Parcelable