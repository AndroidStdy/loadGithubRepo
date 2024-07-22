package fastcampus.part2.loadgithubrepo.model

import com.google.gson.annotations.SerializedName

data class UserDto( //Dto -> data transfer object(주로 서버에서 사용하는 용어)
    @SerializedName("total_count")
    val totalCount : Int,
    @SerializedName("items")
    val items: List<User>,
)