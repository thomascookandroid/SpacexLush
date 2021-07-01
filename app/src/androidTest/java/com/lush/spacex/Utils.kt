package com.lush.spacex

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import org.mockito.stubbing.Stubber

/**
 * Helper method so we can avoid using `when` in our tests and use whenever instead
 */
fun<T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)