# PhotoGallery
An Android client for the photo-sharing site <a href="https://www.flickr.com/" target="_blank">Flickr</a>.
It will fetch and display the most recent public photos uploaded to Flickr.

<ul>
  <li>Implemented fetching JSON data from Flickr with HTTPURLConnection, InputStream, ByteArrayStream, Uri and AsyncTask.</li>
  <li>Parsed JSON text with Gson.</li>
  <li>Solved the screen rotation issue by setting the fragment retained.</li>
  <li>Implemented downloading on an as-needed basis by creating a dedicated background thread with HandlerThread.</li>
  <li>Implemented thread communication by creating a message queue with Handler, Message and Looper.</li>
  <li>Displayed a list of images with RecyclerView, Picasso/Glide library.</li>
  <li>Integrated search into the app using SearchView for users searching related images.</li>
  <li>Saved user query and implemented the query persistance between restarts of the app (even after the user turns off the device) with SharedPreferences.</li>
  <li>Allowed users to poll for new search results in the background with AlarmManager and PendingIntent.</li>
  <li>Displayed notifications appearing in the notifications drawer when users getting a new search result with Notification, PendingIntent and NotificationManager.</li>
  <li>Implemented notifying the user if new results are found even when the user has not opened the application with BroadcastReceiver and intent filter.</li>
  <li>Filtered foreground notifications with Fragment, BroadcastReceiver, intent filter, ordered broadcasts and priority.</li>
  <li>Limited broadcasts to your app using private permissions and protection level.</li>
</ul>
