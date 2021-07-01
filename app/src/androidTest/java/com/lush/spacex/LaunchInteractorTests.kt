package com.lush.spacex

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lush.spacex.persistance.dao.LaunchDao
import com.lush.spacex.persistance.database.SpacexDatabase
import com.lush.spacex.persistance.entities.launch.*
import com.lush.spacex.processing.interactors.LaunchInteractorImpl
import com.lush.spacex.processing.interfaces.LaunchMapper
import com.lush.spacex.processing.models.LoadingState
import com.lush.spacex.remote.interfaces.SpacexRemote
import com.lush.spacex.remote.models.launch.Launch
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response
import java.util.*

@RunWith(AndroidJUnit4::class)
class LaunchInteractorTests {
    @Mock
    lateinit var spacexDatabase: SpacexDatabase

    @Mock
    lateinit var launchDao: LaunchDao

    @Mock
    lateinit var spacexRemote: SpacexRemote

    @Mock
    lateinit var launchMapper: LaunchMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    /**
     * This runs after every test
     * The purpose of verifyNoMoreInteractions on each mock
     * Is to make sure the test writer has correctly and completely covered a single
     * execution path through the code under test
     */
    @After
    fun teardown() {
        verifyNoMoreInteractions(spacexDatabase)
        verifyNoMoreInteractions(launchDao)
        verifyNoMoreInteractions(spacexRemote)
        verifyNoMoreInteractions(launchMapper)
    }

    /**
     * This test case covers the case where there *was* cached data and the remote call succeeds
     * Under this case, we expect the sequence to be:
     * Loading
     * Cached Data
     * Fresh data
     */
    @Test
    fun assertLoadFlowEmitsLoadingThenCachedDataThenFreshDataFromRemote() = runBlocking {
        // ARRANGE
        val now = Date()
        whenever(launchDao.getLaunches())
            .thenReturn(listOf(dummyLaunchEntity(now)))
        doNothing().`when`(launchDao)
            .insertLaunches(listOf(dummyLaunchEntity(now)))
        whenever(spacexDatabase.launchDao())
            .thenReturn(launchDao)
        whenever(spacexRemote.fetchPastLaunches())
            .thenReturn(Result.success(listOf(dummyLaunch(now))))
        whenever(launchMapper.mapRemoteModelToPersistenceModel(dummyLaunch(now)))
            .thenReturn(dummyLaunchEntity(now))

        // ACT
        val launchEntityLoadFlowEmissions = LaunchInteractorImpl(
            spacexDatabase,
            spacexRemote,
            launchMapper
        ).launchEntityLoad().toList()

        // ASSERT
        verify(launchDao).getLaunches()
        verify(launchDao).insertLaunches(listOf(dummyLaunchEntity(now)))
        verify(spacexDatabase, times(2)).launchDao()
        verify(spacexRemote).fetchPastLaunches()
        verify(launchMapper).mapRemoteModelToPersistenceModel(dummyLaunch(now))

        assert(launchEntityLoadFlowEmissions[0] == LoadingState.Loading)
        assert(launchEntityLoadFlowEmissions[1] == LoadingState.Loaded(listOf(dummyLaunchEntity(now))))
        assert(launchEntityLoadFlowEmissions[2] == LoadingState.Loaded(listOf(dummyLaunchEntity(now))))
    }

    /**
     * This test case covers the case where there *was* cached data and the remote call fails
     * Under this case, we expect the sequence to be:
     * Loading
     * Cached Data
     * Error
     */
    @Test
    fun assertLoadFlowEmitsLoadingThenCachedDataThenError() = runBlocking {
        // ARRANGE
        val now = Date()
        whenever(launchDao.getLaunches())
            .thenReturn(listOf(dummyLaunchEntity(now)))
        whenever(spacexDatabase.launchDao())
            .thenReturn(launchDao)
        whenever(spacexRemote.fetchPastLaunches())
            .thenReturn(Result.failure(
                HttpException(
                    Response.error<List<LaunchEntity>>(
                        500,
                        ResponseBody.create(null, "")
                    )
                )
            ))

        // ACT
        val launchEntityLoadFlowEmissions = LaunchInteractorImpl(
            spacexDatabase,
            spacexRemote,
            launchMapper
        ).launchEntityLoad().toList()

        // ASSERT
        verify(launchDao).getLaunches()
        verify(spacexDatabase).launchDao()
        verify(spacexRemote).fetchPastLaunches()

        assert(launchEntityLoadFlowEmissions[0] == LoadingState.Loading)
        assert(launchEntityLoadFlowEmissions[1] == LoadingState.Loaded(listOf(dummyLaunchEntity(now))))
        assert(launchEntityLoadFlowEmissions[2] == LoadingState.Error)
    }

    /**
     * Dummy launch entity (i.e. the model that is persisted to DB)
     */
    private fun dummyLaunchEntity(now: Date) = LaunchEntity(
        flightNumber = 1,
        name = "Test",
        date = now,
        staticFireDate = now,
        tbd = false,
        net = false,
        window = 0,
        rocketId = "id",
        success = true,
        failures = emptyList(),
        upcoming = true,
        details = "details",
        fairings = LaunchFairing(
            reused = true,
            recoveryAttempt = true,
            recovered = true,
            shipIds = emptyList()
        ),
        crewIds = emptyList(),
        shipIds = emptyList(),
        capsuleIds = emptyList(),
        payloadIds = emptyList(),
        launchPadId = "launchPadId",
        cores = emptyList(),
        links = LaunchPadLinks(
            patch = PatchLink(
                small = "small",
                large = "large"
            ),
            reddit = RedditLink(
                campaign = "campaign",
                launch = "launch",
                media = "media",
                recovery = "recovery",
            ),
            flickr = FlickrLink(
                small = emptyList(),
                original = emptyList()
            ),
            presskit = "presskit",
            webcast = "webcast",
            youtubeId = "youtubeId",
            article = "article",
            wikipedia = "wikipedia"
        ),
        autoUpdate = true
    )

    /**
     * Dummy launch entity (i.e. the model that is fetched from remote)
     */
    private fun dummyLaunch(now: Date) = Launch(
        flightNumber = 1,
        name = "Test",
        date = now,
        staticFireDate = now,
        tbd = false,
        net = false,
        window = 0,
        rocketId = "id",
        success = true,
        failures = emptyList(),
        upcoming = true,
        details = "details",
        fairings = com.lush.spacex.remote.models.launch.LaunchFairing(
            reused = true,
            recoveryAttempt = true,
            recovered = true,
            shipIds = emptyList()
        ),
        crewIds = emptyList(),
        shipIds = emptyList(),
        capsuleIds = emptyList(),
        payloadIds = emptyList(),
        launchPadId = "launchPadId",
        cores = emptyList(),
        links = com.lush.spacex.remote.models.launch.LaunchPadLinks(
            patch = com.lush.spacex.remote.models.launch.PatchLink(
                small = "small",
                large = "large"
            ),
            reddit = com.lush.spacex.remote.models.launch.RedditLink(
                campaign = "campaign",
                launch = "launch",
                media = "media",
                recovery = "recovery",
            ),
            flickr = com.lush.spacex.remote.models.launch.FlickrLink(
                small = emptyList(),
                original = emptyList()
            ),
            presskit = "presskit",
            webcast = "webcast",
            youtubeId = "youtubeId",
            article = "article",
            wikipedia = "wikipedia"
        ),
        autoUpdate = true
    )
}