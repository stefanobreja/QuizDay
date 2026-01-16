package com.obi.quizday.data.quizzes.model

import com.google.gson.annotations.SerializedName

data class QuizResponseDto(
    @SerializedName("response_code") val responseCode: Int?,
    val results: List<QuizDto>?
)

//    Code 0: Success Returned results successfully.
//    Code 1: No Results Could not return results. The API doesn't have enough questions for your query. (Ex. Asking for 50 Questions in a Category that only has 20.)
//    Code 2: Invalid Parameter Contains an invalid parameter. Arguments passed in aren't valid. (Ex. Amount = Five)
//    Code 3: Token Not Found Session Token does not exist.
//    Code 4: Token Empty Session Token has returned all possible questions for the specified query. Resetting the Token is necessary.
//    Code 5: Rate Limit Too many requests have occurred. Each IP can only access the API once every 5 seconds.
enum class QuizResultCode {
    SUCCESS,
    NO_RESULTS,
    INVALID_PARAMETER,
    TOKEN_NOT_FOUND,
    TOKEN_EMPTY,
    RATE_LIMIT_TOO_MANY_REQUESTS,
    UNKNOWN;

    companion object {
        fun getByCode(code: Int) = when (code) {
            0 -> SUCCESS
            1 -> NO_RESULTS
            2 -> INVALID_PARAMETER
            3 -> TOKEN_NOT_FOUND
            4 -> TOKEN_EMPTY
            5 -> RATE_LIMIT_TOO_MANY_REQUESTS
            else -> UNKNOWN
        }
    }
}