package com.remoteapi.nikhilkumar.remoteapi.responsePOJO

import com.google.gson.annotations.SerializedName

data class ZomatoAPIResponse(@SerializedName("results_found") var resultsFound: Int? = null,
                               @SerializedName("results_start") var resultStart: Int? = null,
                               @SerializedName("results_shown") var resultShown: Int? = null,
                               @SerializedName("restaurants") var restaurants: List<Restaurants>? = null)

data class Restaurants(@SerializedName("restaurant") var restraunt: Restaurant? = null)


data class Restaurant(@SerializedName("thumb") var logoUrl: String? = null,
                      @SerializedName("average_cost_for_two") var avgCost: String? = null,
                      @SerializedName("name") var resName: String? = null,
                      @SerializedName("id") var id: String? = null,
                      @SerializedName("user_rating") var rating: UserRating? = null)


data class UserRating (@SerializedName("aggregate_rating") var avgRating: String? = null,
                       @SerializedName("rating_text") var ratingText: String? = null)

