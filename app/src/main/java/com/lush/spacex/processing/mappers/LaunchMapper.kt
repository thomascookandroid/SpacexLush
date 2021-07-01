package com.lush.spacex.processing.mappers

import com.lush.spacex.persistance.entities.launch.*
import com.lush.spacex.remote.models.launch.Launch
import java.util.*

interface LaunchMapper {
    fun mapRemoteModelToPersistenceModel(launch: Launch) : LaunchEntity
}

class LaunchMapperImpl : LaunchMapper {
    override fun mapRemoteModelToPersistenceModel(launch: Launch) : LaunchEntity {
        return LaunchEntity(
            flightNumber = launch.flightNumber,
            name = launch.name,
            date = launch.date,
            staticFireDate = launch.staticFireDate ?: Date(),
            tbd = launch.tbd,
            net = launch.net,
            window = launch.window ?: 0,
            rocketId = launch.rocketId ?: "",
            success = launch.success ?: false,
            failures = launch.failures.map { toMapLaunchFailure ->
                LaunchFailure(
                    time = toMapLaunchFailure.time,
                    altitude = toMapLaunchFailure.altitude ?: 0,
                    reason = toMapLaunchFailure.reason
                )
            },
            upcoming = launch.upcoming,
            details = launch.details ?: "",
            fairings = launch.fairings?.let { toMapFairings ->
                LaunchFairing(
                    reused = toMapFairings.reused ?: false,
                    recoveryAttempt = toMapFairings.recoveryAttempt ?: false,
                    recovered = toMapFairings.recovered ?: false,
                    shipIds = toMapFairings.shipIds
                )
            } ?: LaunchFairing(
                reused = false,
                recoveryAttempt = false,
                recovered = false,
                shipIds = emptyList()
            ),
            crewIds = launch.crewIds,
            shipIds = launch.shipIds,
            capsuleIds = launch.capsuleIds,
            payloadIds = launch.payloadIds,
            launchPadId = launch.launchPadId ?: "",
            cores = launch.cores.map { toMapCore ->
                LaunchPadCore(
                    coreId = toMapCore.coreId ?: "",
                    flight = toMapCore.flight ?: 0,
                    gridFins = toMapCore.gridFins ?: false,
                    legs = toMapCore.legs ?: false,
                    reused = toMapCore.reused ?: false,
                    landingAttempt = toMapCore.landingAttempt ?: false,
                    landingSuccess = toMapCore.landingSuccess ?: false,
                    landingType = toMapCore.landingType ?: "",
                    landingPadId = toMapCore.landingPadId ?: ""
                )
            },
            links = launch.links.let { toMapLinks ->
                LaunchPadLinks(
                    patch = toMapLinks.patch.let { toMapPatch ->
                        PatchLink(
                            small = toMapPatch.small ?: "",
                            large = toMapPatch.large
                        )
                    },
                    reddit = toMapLinks.reddit.let { toMapReddit ->
                        RedditLink(
                            campaign = toMapReddit.campaign ?: "",
                            launch = toMapReddit.launch ?: "",
                            media = toMapReddit.media ?: "",
                            recovery = toMapReddit.recovery ?: ""
                        )
                    },
                    flickr = toMapLinks.flickr.let { toMapFlickr ->
                        FlickrLink(
                            small = toMapFlickr.small,
                            original = toMapFlickr.original
                        )
                    },
                    presskit = toMapLinks.presskit ?: "",
                    webcast = toMapLinks.webcast ?: "",
                    youtubeId = toMapLinks.youtubeId ?: "",
                    article = toMapLinks.article ?: "",
                    wikipedia = toMapLinks.wikipedia ?: ""
                )
            },
            autoUpdate = launch.autoUpdate
        )
    }
}