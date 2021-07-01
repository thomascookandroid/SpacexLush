# SpacexLush
Lush - Android Task

General Information:

Implemented the task as asked + using rockets end point too.

Showing off use of nav graph/nav components + nav drawer - in the app, you can switch between 2 lists; launch and rockets.

Used SoC (rendering + VM(s), persistance layer (i.e. caching in RoomDB - no TTL/ETags though - not enough time), remote layer (i.e. hitting the API), seperation of remote models and app models, mappers, and interactor layer for mediating between cache and remote).

Using Dagger for DI.

Tests with Mockito (Instrumentation tests - didn't want to faff around with Robolectric).

Known issues:

UI is a bit bare bones

Drawer navigation looks garbage (styling mainly)

No TTL on cache, so, although data *is* cached (and render will show from cache first, before fetching network - if cache exists) the network will always be hit

Clicking cells on the list views doesn't actually do anything, although my plan was to open dialog fragments showing the "full details" of the clicked item (maybe with recycler for images)

Some assumptions made about the remote API (i.e. what is optional vs what is not) and also when mapping to perstistance layer I chose to use all non-optional fields (to make my life easier) so app models might not properly reflect semantics of the problem domain
