package com.example.locationpractice

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.locationpractice.ui.theme.LocationPracticeTheme
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Configuration.getInstance().load(this, getSharedPreferences("osm_prefs", MODE_PRIVATE))
        setContent {

            LocationPracticeTheme {
                Surface(
                    // on below line we are specifying modifier and color for our app
                    modifier = Modifier.fillMaxSize(), color = Color.White
                ) {
                    OsmdroidMapView(this, LocalContext.current)
                }
            }
        }
    }
}

@Composable
fun OsmdroidMapView(activity: Activity,context: Context) {
    var geoPoint by remember {
        mutableStateOf(GeoPoint(23.7104, 90.40744))
    }
    RequestLocationPermission(
        onPermissionGranted = {
            Toast.makeText(context,"Permission Granted!!",Toast.LENGTH_SHORT).show()
            GetLocation.getLastUserLocation(
                onGetLastLocationSuccess = {
                    geoPoint = GeoPoint(it.first,it.second)
                    println("from last")
                },
                onGetLastLocationFailed = { exception ->
                },
                onGetLastLocationIsNull = {
                    // Attempt to get the current user location
                    GetLocation.getCurrentLocation(
                        onGetCurrentLocationSuccess = {
                            geoPoint = GeoPoint(it.first,it.second)
                            println("from current")
                        },
                        onGetCurrentLocationFailed = {
                        },
                        context = context
                    )
                },
                context = context
            )
        },
        onPermissionDenied = {
            Toast.makeText(context,"Permission Denied!!",Toast.LENGTH_SHORT).show()
        },
        onPermissionsRevoked = {
            Toast.makeText(context,"Permission Revoked!!",Toast.LENGTH_SHORT).show()
        }
    )
    println(geoPoint)
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setUseDataConnection(true)
                setMultiTouchControls(true)

                val marker = Marker(this)
                marker.position = geoPoint
                overlays.add(marker)

                val mapController: IMapController = this.controller
                mapController.setCenter(geoPoint)
                mapController.setZoom(12.0)
                val mapEventsReceiver = object : MapEventsReceiver {
                    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                        p?.let {
                            // Update GeoPoint and move the map center
                            geoPoint = it
                            getLocationDetails(activity, it.latitude, it.longitude)
                            println(geoPoint)
                            marker.position = it
                            mapController.setCenter(it)
                        }
                        return true
                    }

                    override fun longPressHelper(p: GeoPoint?): Boolean {
                        // Handle long presses if needed
                        return false
                    }
                }
                this.overlays.add(MapEventsOverlay(mapEventsReceiver))
            }
        },
        update = { view ->
            view.controller.setCenter(geoPoint)
        }
    )
}

@Composable
fun MapUI(context: Context) {

    var permissionResultText by remember { mutableStateOf("Permission Granted...") }

    var showSourcePermission by remember { mutableStateOf(false) }
    var showSourcePermission2 by remember { mutableStateOf(false) }
    var showDestinationPermission by remember { mutableStateOf(false) }

    val srcUrl = "https://maps.app.goo.gl/7HVVxqz6JKNhV3xo6"

    val destUrl = "https://maps.app.goo.gl/JTPWUMtoGv2PyiLP7"

    var sourceLocation by remember {
        mutableStateOf("")
    }
    var destinationLocation by remember {
        mutableStateOf("")
    }

    var sourceLat by remember {
        mutableDoubleStateOf(0.0)
    }
    var sourceLan by remember {
        mutableDoubleStateOf(0.0)
    }
    var destinationLat by remember {
        mutableDoubleStateOf(0.0)
    }
    var destinationLan by remember {
        mutableDoubleStateOf(0.0)
    }

    if (showDestinationPermission) {
        ExtractInfoFromUrl.resolveShortenedUrl(destUrl) { resolvedUrl ->
            val latLng = ExtractInfoFromUrl.extractLatLngFromUrl(resolvedUrl)
            if (latLng != null) {
                destinationLocation = "Latitude: ${latLng.first}, Longitude: ${latLng.second}"
                destinationLat = 21.4137782
                destinationLan = 91.9830086
            } else {
                destinationLocation = "No coordinates found in the URL."
            }
        }
    }
    if (showSourcePermission2) {
        ExtractInfoFromUrl.resolveShortenedUrl(srcUrl) { resolvedUrl ->
            val latLng = ExtractInfoFromUrl.extractLatLngFromUrl(resolvedUrl)
            if (latLng != null) {
                sourceLocation = "Latitude: ${latLng.first}, Longitude: ${latLng.second}"
                sourceLat = latLng.first
                sourceLan = latLng.second
            } else {
                sourceLocation = "No coordinates found in the URL."
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .background(color = Color.Cyan, shape = RoundedCornerShape(8.dp))
                .clickable {
                    showSourcePermission = true
                },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Get Your Location",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Text(
            text = "Source: $sourceLocation",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .background(color = Color.Cyan, shape = RoundedCornerShape(8.dp))
                .clickable {
                    showDestinationPermission = true
                },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Get Destination Location",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Text(
            text = "Destination: $destinationLocation",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            textAlign = TextAlign.Center,
        )


        // on below line we are adding a spacer.
        Spacer(modifier = Modifier.height(20.dp))

        // on below line adding a button.
        Button(
            onClick = {
                drawTrack(sourceLat, sourceLan, destinationLat, destinationLan, context)
            },
            // on below line adding a modifier for our button.
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            // on below line we are adding a text
            Text(
                text = "Draw Route on Google Maps",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
    if (showSourcePermission) {
        RequestLocationPermission(
            onPermissionGranted = {
                GetLocation.getLastUserLocation(
                    onGetLastLocationSuccess = {
                        sourceLocation =
                            "Location using LAST-LOCATION: LATITUDE: ${it.first}, LONGITUDE: ${it.second}"
                        sourceLat = it.first
                        sourceLan = it.second
                    },
                    onGetLastLocationFailed = { exception ->
                        sourceLocation =
                            exception.localizedMessage ?: "Error Getting Last Location"
                    },
                    onGetLastLocationIsNull = {
                        // Attempt to get the current user location
                        GetLocation.getCurrentLocation(
                            onGetCurrentLocationSuccess = {
                                sourceLocation =
                                    "Location using CURRENT-LOCATION: LATITUDE: ${it.first}, LONGITUDE: ${it.second}"
                                sourceLat = it.first
                                sourceLan = it.second
                            },
                            onGetCurrentLocationFailed = {
                                sourceLocation =
                                    it.localizedMessage
                                        ?: "Error Getting Current Location"
                            },
                            context = context
                        )
                    },
                    context = context
                )
            },
            onPermissionDenied = {
                permissionResultText = "Permission Denied :("
            },
            onPermissionsRevoked = {
                permissionResultText = "Permission Revoked :("
            }
        )
    }
}

private fun drawTrack(
    sourceLat: Double,
    sourceLng: Double,
    destinationLat: Double,
    destinationLng: Double,
    context: Context
) {
    try {
        // create a uri
        val uri: Uri =
            Uri.parse("https://www.google.co.in/maps/dir/?api=1&origin=$sourceLat,$sourceLng&destination=$destinationLat,$destinationLng")
        // initializing a intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // below line is to set maps package name
        i.setPackage("com.google.android.apps.maps")
        // below line is to set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // start activity
        context.startActivity(i)
    } catch (e: ActivityNotFoundException) {
        // when the google maps is not installed on users device
        // we will redirect our user to google play to download google maps.
        val uri: Uri =
            Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
        // initializing intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // to start activity
        context.startActivity(i)
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    MapUI(context = LocalContext.current)
}