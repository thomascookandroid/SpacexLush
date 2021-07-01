package com.lush.spacex.remote.models.launch

import com.squareup.moshi.Json

data class LaunchPadLinks(
    val patch: PatchLink,
    val reddit: RedditLink,
    val flickr: FlickrLink,
    val presskit: String?,
    val webcast: String?,
    @Json(name = "youtube_id")
    val youtubeId: String?,
    val article: String?,
    val wikipedia: String?
)