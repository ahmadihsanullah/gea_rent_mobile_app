package il.massive.gea_rent.data.panduan

import il.massive.gea_rent.R
import il.massive.gea_rent.model.PanduanModel
import il.massive.gea_rent.model.TokoModel

object DataPanduan {
    val dummyDataPanduan = listOf<PanduanModel>(
        PanduanModel("Panduan memasang Kompor Portable",
            "Penggunaan kompor portable kini sangat luas untuk berbagai keperluan memasak, Berikut ini adalah Tutorial cara memasang kompor portable yang mudah, aman, praktis dibawa kemana saja",
            "https://youtu.be/FtlufGzJ53E?si=V8a3BSkhScSz2Lz7", R.drawable.img_alat1),
        PanduanModel("Panduan penggunaan tenda di outdoor",
            "Sebagai salah satu perlakuan & pemeliharaan agar umur penggunaan tenda anda menjadi lebih lama dan awet. Berikut tutorial cara pasang tenda camping kapasitas 4 orang ",
            "https://youtu.be/eR8EpwTOfNc?si=AfPqk7STXjdapU3A", R.drawable.img_alat2),
        PanduanModel("Panduan menggunakan Sleeping Bed",
            "Sebagai salah satu perlakuan & pemeliharaan agar umur penggunaan sleeping bed anda menjadi lebih lama dan awet. Bisa simak video berikut cara membuka dan penggunaan sleeping bed ",
            "https://youtu.be/M1Rnzu-4g64?si=7MfY-O77iyduq36T", R.drawable.img_alat3),
        PanduanModel("Panduan memakai water bladder",
            "Penggunaan water bladder dengan mudah dan bisa disimak video berikut",
            "https://youtu.be/zcQxoM3gRcw?si=4rcnhH39pQ7tYoW-", R.drawable.img_alat4),
    )
}