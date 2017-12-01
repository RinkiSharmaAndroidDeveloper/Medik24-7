package medik247.vs.com.medik;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class AppMapFragmentDoctor extends BaseFragment implements OnMapReadyCallback {
    LocationManager locationManager;
public static  double lat23;
    public static  double long23;

    Marker mark;
    List<LatLng> pointsdecode = new ArrayList<LatLng>();
    Polyline polyline23 = null;
    String url = "http://vertosys.com/docpat/GetLocation.php?user_id=";
    List<ModelMarker> listmodel = new ArrayList<ModelMarker>();

    private static final String KEY_PLACE = "KEY_PLACE";
    public double lat1, gpslatitude, gpslongitude;
    String APIID;

    public static Fragment getFragment(AppPlace appPlace) {
        Bundle bundle = new Bundle();
        AppMapFragmentDoctor receiversFragment = new AppMapFragmentDoctor();
        if (appPlace != null) {
            bundle.putSerializable(KEY_PLACE, appPlace);
        }
        receiversFragment.setArguments(bundle);
        return receiversFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screendoctor, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = new SupportMapFragment();
        getChildFragmentManager().beginTransaction().add(R.id.map_containers2, mapFragment, "MAPS").commit();
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);



    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        GPSTracker gpsTracker=new GPSTracker(getContext());

        googleMap.addMarker(new MarkerOptions().position(new LatLng(GlobalVariable.lattpat,GlobalVariable.longtpat)).title("Test").icon(BitmapDescriptorFactory.fromResource(R.drawable.blueicon)));

     mark=   googleMap.addMarker(new MarkerOptions().position(new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude())).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.doctoricon)));
        mark.setTag("1");
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude())));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

       googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        } );
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, new android.location.LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                String msg = "New Latitude: " + location.getLatitude()
                        + "New Longitude: " + location.getLongitude();

                lat23=location.getLatitude();
                long23=location.getLongitude();
                Log.e("datasdsdsd",""+msg);


                if( mark.getTag()=="1")
                {
                    mark.remove();
                }
                mark = googleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.doctoricon)));
                mark.setTag("1");
                mark.getTag();
                Log.e("datasdsdsd",""+mark.getTag());
                String link6="https://maps.googleapis.com/maps/api/directions/json?origin="+location.getLatitude()+","+location.getLongitude()+"&destination="+GlobalVariable.lattpat+","+GlobalVariable.longtpat+"&mode=car&key=AIzaSyCbkAKAJnt57am2mMVkHc5PrNZ1rzM_RfQ";
                Log.e("Link42",link6);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, link6, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Json Object","response:="+response);
                        try {
                            String routes=response.getString("routes");
                            JSONArray jsonArray=new JSONArray(routes);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String bounds=jsonObject.getString("bounds");
                                String overviewpoints=jsonObject.getString("overview_polyline");
                                Log.e("Overviewpoints",overviewpoints);
                                JSONObject jsonObjectpoints=new JSONObject(overviewpoints);
                                String points=jsonObjectpoints.getString("points");
                                Log.e("points",points);
                                pointsdecode = decodePolyLine(points);


                                JSONObject jsonObject1=new JSONObject(bounds);
                                String northeast=      jsonObject1.getString("northeast");
                                JSONObject jsonObject2=new JSONObject(northeast);
                                lat1= jsonObject2.getDouble("lat");
                                double  long1=jsonObject2.getDouble("lng");
                                LatLng sydney2 = new LatLng(lat1,long1);


                                String southwest= jsonObject1.getString("southwest");
                                JSONObject jsonObject3=new JSONObject(southwest);

                            }









                            Log.e("Pointdecode",pointsdecode.toString()+pointsdecode.size());
                            PolylineOptions polylineOptions = new PolylineOptions().
                                    geodesic(true).
                                    color(Color.BLUE).
                                    width(5);
                            for(int j=0;j<pointsdecode.size();j++){
//
////                        mMap.addMarker(new MarkerOptions().position(pointsdecode.get(j)).title("Place B"));
//
//
                                PolylineOptions polylineOptions2=   polylineOptions.add(pointsdecode.get(j));
                                polyline23=googleMap.addPolyline(polylineOptions);
//
                            }
//
//                    polylineOptions.visible(false);
//                    Polyline line = mMap.addPolyline(new PolylineOptions()
//                            .add(new LatLng(location.getLatitude(), location.getLongitude()),
//                                    new LatLng(this.destinationLatitude, this.destinationLongitude))
//                            .width(1)
//                            .color(Color.DKGRAY)
                            polyline23.remove();

                            Log.e("Pointdecode Clear",pointsdecode.toString()+pointsdecode.size());





                            googleMap.addPolyline(polylineOptions);

                            Log.e("routes",routes);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


// TODO Auto-generated method stub

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
// TODO Auto-generated method stub
                    }
                });
                queue.add(jsObjRequest);




            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });



    }








    private List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }

        return decoded;
    }


}
