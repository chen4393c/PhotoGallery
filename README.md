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
</ul>
