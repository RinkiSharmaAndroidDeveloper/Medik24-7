package medik247.vs.com.medik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import medik247.vs.com.medik.Adapters.HistoryDoctorAdapter;

/**
 * Created by Indosurplus on 5/18/2017.
 */

public class HistoryDoctorActivity extends Activity implements View.OnClickListener {
    RecyclerView recyclerView;
    HistoryDoctorAdapter mAdapter;
    SessionManager sessionManager;
    String doctorId;
    ImageView imageView;
    HistoryModel historyModel;
    ArrayList<HistoryModel> historyModels =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        recyclerView = (RecyclerView) findViewById(R.id.recyclyerview);
        imageView = (ImageView) findViewById(R.id.backup);
        sessionManager =new SessionManager(this);
        HashMap<String,String> map=sessionManager.getUserDetails();
        doctorId=  map.get(SessionManager.UID);
        getDoctorHistory();
        setAdapter();
        imageView.setOnClickListener(this);
    }

    public void setAdapter()
    {
        mAdapter = new HistoryDoctorAdapter(historyModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    public void getDoctorHistory(){
        RequestQueue queue2 = Volley.newRequestQueue(this);
        String url2="http://vertosys.com/docpat/GetHistory.php?doctor_id="+doctorId;
        Log.e("usere",url2);
        JsonObjectRequest jsObjRequest2 = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>()  {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                String responsemessage= null;
                try {

                     response.getString("Data");
                    Log.e("Data23", response.getString("Data"));
                    JSONArray jsonObj = new JSONArray(response.getString("Data"));
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = jsonObj.getJSONObject(i);
                        String id = c.getString("patient_id");
                        String name = c.getString("first_name");
                        String lastName = c.getString("last_name");
                        String phoneNumber = c.getString("phone_number");
                        String image = c.getString("profile_pic");
                      //  String address = c.getString("address");
                        String date = c.getString("date_added");
                        String status = c.getString("status");
                        String requestId = c.getString("request_id");
                        Log.d("name",name);
                        //public HistoryModel(String patentName, String patentPhone, String patentAddress, String patentdate,String image,String status,String requestId,String patientId)
                        historyModel =new HistoryModel(name,lastName,phoneNumber,date,image,status,requestId,id);
                        historyModels.add(historyModel);
                    }
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            //    queue2.add(jsObjRequest2);

            }
            // TODO Auto-generated method stub


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
//                Toast.makeText(Main2Activity.this,"Error"+error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        queue2.add(jsObjRequest2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.backup):
                finish();
                break;
        }
    }
}
