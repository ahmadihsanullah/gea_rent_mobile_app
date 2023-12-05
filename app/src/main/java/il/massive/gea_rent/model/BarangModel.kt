package il.massive.gea_rent.model

data class BarangModel(
    val toko: TokoModel,
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    val stok: Int,
    val gambar: Int

)
