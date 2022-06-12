package com.project.discofferytemp.repository


import com.project.discofferytemp.model.ArticlesDetail

class DetailKopiRepository  {

    suspend fun getDetailKopi():ArrayList<ArticlesDetail>{
        var data = ArrayList<ArticlesDetail>()
         data.add(ArticlesDetail("Aceh Gayo","Aceh, Indonesia","Arabika","1500 MPDL","Aceh gayo coffee is one of the most popular coffee in the world. The name Aceh Gayo is taken from the location where this coffee grows, namely in the Gayo Highlands with an altitude of 1200 - 1700 m. The unique thing about this coffee is that it has a distinct aroma as well as a distinct taste that allows it to be recognized around the world. Even Aceh Gayo coffee is one of the world's most costly coffees.\n"))
         data.add(ArticlesDetail("Dampit","Malang,Indonesia","Robusta","800 - 1000 MDPL","Robusta Dampit Coffee Plantation is located at the foot of Mount Semeru which is included in the Tengger plateau area. With an altitude of 900 meters above sea level is one of the requirements for planting superior coffee. Abroad, especially in Europe,Robusta Dampit coffee is very well known. Robusta coffee from Dampit is considered to have a special taste. When drunk, the coffee will have a thick taste plus a low acidity with a final sensation of caramel taste and also a slight earthy aroma that feels, and smells for quite a long time"))
         data.add(ArticlesDetail("Toraja","Sulawesi, Indonesia","Arabika","1400 MDPL","Toraja Coffee Plantation is located on the slopes of Mount Sesean, North Toraja Regency, with an altitude of 1.400–2.100 MDPL. Toraja Arabica coffee has been exported to many countries, especially Japan and North America. The taste is a combination of strong classic coffee with modern coffee that tends to be fruity. The sour taste is balanced enough to make it even more delicious to enjoy in the morning or evening.\n"))

        return data
    }

    companion object {
        @Volatile
        private var instance: DetailKopiRepository? = null
        fun getInstance(): DetailKopiRepository =
            instance ?: synchronized(this) {
                instance ?: DetailKopiRepository()
            }.also { instance = it }
    }

}