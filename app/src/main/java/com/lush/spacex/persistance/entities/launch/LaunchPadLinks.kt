package com.lush.spacex.persistance.entities.launch

import androidx.room.Embedded

data class LaunchPadLinks(
    @Embedded(prefix = "_patch")
    val patch: PatchLink,
    @Embedded(prefix = "_reddit")
    val reddit: RedditLink,
    @Embedded(prefix = "_flickr")
    val flickr: FlickrLink,
    val presskit: String,
    val webcast: String,
    val youtubeId: String,
    val article: String,
    val wikipedia: String
)