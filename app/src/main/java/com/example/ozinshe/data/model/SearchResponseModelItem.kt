package com.example.ozinshe.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponseModelItem(
    @SerializedName("id")
    val id: Int, // 121
    @SerializedName("movieType")
    val movieType: String, // SERIAL
    @SerializedName("name")
    val name: String, // Дала ойындары
    @SerializedName("keyWords")
    val keyWords: String, //  супер қаһарман робот ойын
    @SerializedName("description")
    val description: String, // «Рухани жаңғыру» секілді мемлекеттік бағдарламалардың негізінде қазір балалар арасында супер қаһарман бейнесін қалыптастыру қолға алынып, қарқынды жүзеге асырылып жатыр. Мұның алғышарты –  Әмір сынды анимациялық кейіпкеріміз. Анимациялық телехикаяның желісі болашақ әлемнен сыр шертеді. Ол әлемде роботтық техникалар даму шегіне жетіп, адамдарға қызмет етеді. Десе де, бұл болашақ ғаламда табиғи ресурстар жоқтың қасы еді. Балалар қауымы далаға шықпай, бөлмелерінде "VR" көзілдірік арқылы кибер ойындарды ойнауды жөн көреді. Дегенмен елден ерек Әмір атты 9 жасар бала  кибер ойындарға мойынсұнбай, далада, таза ауада физикалық ойындарды ойнауды жөн көретін. Ол балаларға  виртуал әлемнің қауіптерін ашып көрсетеді. Сол арқылы цифрлы  технологиялардың қаупіне назар аударуға шақырады. 
    @SerializedName("year")
    val year: Int, // 2018
    @SerializedName("trend")
    val trend: Boolean, // true
    @SerializedName("timing")
    val timing: Int, // 8
    @SerializedName("director")
    val director: String, // Қасиет Сақиолла
    @SerializedName("producer")
    val producer: String, // Қуанышбек Аралбайұлы
    @SerializedName("poster")
    val poster: PosterXXXX,
    @SerializedName("video")
    val video: VideoXXXX,
    @SerializedName("watchCount")
    val watchCount: Int, // 4371
    @SerializedName("seasonCount")
    val seasonCount: Int, // 1
    @SerializedName("seriesCount")
    val seriesCount: Int, // 10
    @SerializedName("createdDate")
    val createdDate: String, // 2022-06-16T13:27:01.756+00:00
    @SerializedName("lastModifiedDate")
    val lastModifiedDate: String, // 2022-07-14T07:00:42.865+00:00
    @SerializedName("screenshots")
    val screenshots: List<ScreenshotXXXX>,
    @SerializedName("categoryAges")
    val categoryAges: List<CategoryAgeXXXX>,
    @SerializedName("genres")
    val genres: List<GenreXXXX>,
    @SerializedName("categories")
    val categories: List<CategoryXXXX>,
    @SerializedName("favorite")
    val favorite: Boolean // false
)