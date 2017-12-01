package medik247.vs.com.medik;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Android on 28-04-2017.
 */

public class SignUp extends Activity {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String link=" http://vertosys.com/docpat/register.php?";
    String paraFname="first_name";
    String paralname="last_name";
    String paraemail="email";
    String parapassword="password";
    String paraphonenumber="phone_number";
    String paraofficeaddreess="office_address";
    String parahomeaddress="home_address";
  String doccertipara=  "doc_certificate";
    String profilepicencodedString;
            String doclicensepara="doc_license";
    String profile_picpara="profile_pic";
    EditText edFname,edLname,edemail,edphonenumber,epassword,edconfirmpassword,edofficeaddress,edhomeaddress;
     String url;
    ProgressDialog progressDialog;
    private static final int IMG_RESULT3 =25 ;
    private static final int IMG_RESULT2 =222 ;
    private static int IMG_RESULT = 3;
    String ImageDecode;
    ImageView imageViewLoad1,imageViewLoad2,imageViewLoad3;
    Button LoadImage1,LoadImage2,LoadImage3;
    TextView textViewspeacial;
    Intent intent;
    String[] FILE;
    List<SpecialistMdel>objlist=new ArrayList<SpecialistMdel>();
     String fname2,lname,password,phonenumber,email,officeaddreess,homeaddress,confirmpassword;
    private int REQUESTFINELOCAION=111;
     String specialistid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
//        requestPermission();
        objlist.clear();
        apshitsad();


        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pattern = Pattern.compile(EMAIL_PATTERN);

        edFname= (EditText) findViewById(R.id.edfname);
        edLname= (EditText) findViewById(R.id.edlname);
        edemail= (EditText) findViewById(R.id.editTextemail);
        edconfirmpassword= (EditText) findViewById(R.id.edconfirmpassword);
        epassword= (EditText) findViewById(R.id.editTextpassword);
        edphonenumber= (EditText) findViewById(R.id.phonenumber);
        edofficeaddress= (EditText) findViewById(R.id.editTextaddressoffice);
        edhomeaddress= (EditText) findViewById(R.id.homeaddress);
//        http://vertosys.com/docpat/register.php?first_name=varinder&last_name=singh&email=varinder@gmail.com&password=123456&phone_number=0987654321&profile_pic=pic.jpeg&user_type=doctor&office_address=address%20home&home_address=office%20address&doc_certificate=certificate&doc_license=license

        imageViewLoad1 = (ImageView) findViewById(R.id.imageView_1);
        imageViewLoad2= (ImageView) findViewById(R.id.imageView_2);
        imageViewLoad3 = (ImageView) findViewById(R.id.imageView_3);
        LoadImage1 = (Button)findViewById(R.id.buttonpick_1);
        LoadImage2 = (Button)findViewById(R.id.buttonpick_2);
        LoadImage3 = (Button)findViewById(R.id.buttonpick_3);
        Button button= (Button) findViewById(R.id.sumbit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fname2=edFname.getText().toString();
                 lname=edLname.getText().toString();
                 email=edemail.getText().toString();
                 officeaddreess=edofficeaddress.getText().toString();
                 homeaddress=edhomeaddress.getText().toString();
                 phonenumber=edphonenumber.getText().toString();
                 password=epassword.getText().toString();
                 confirmpassword=edconfirmpassword.getText().toString();
               Log.e("Data",fname2+lname+password+phonenumber+email+officeaddreess+homeaddress+confirmpassword);
             if(password.equals(confirmpassword)) {
if(validate(email)==true) {
//    apihit();
}
                 if(fname2.equals("")){
                     edFname.setError("Field not entered");
                     edFname.requestFocus();
                 }
                 if(lname.equals("")){
                     edLname.setError("Field not entered");
                     edLname.requestFocus();
                 }
                 if(email.equals("")){
                     edemail.setError("Field not entered");
                     edemail.requestFocus();
                 }
                 if(phonenumber.equals("")){
                     edphonenumber.setError("Field not entered");
                     edphonenumber.requestFocus();
                 }
                 if(password.equals("")){

                     epassword.setError("Field  not entered");
                     epassword.requestFocus();

                 }
                 if(officeaddreess.equals("")){
                     edofficeaddress.setError("Field  not entered");
                     edofficeaddress.requestFocus();
                 }

                 if(homeaddress.equals("")){
                     edhomeaddress.setError("Field  not entered");
                     edhomeaddress.requestFocus();
                 }

                 if (password.equals(confirmpassword)==false) {
                     Toast.makeText(SignUp
                     .this, "Password Not Match", Toast.LENGTH_SHORT).show();

                 }
                 else {
                     url = link + "first_name=" + fname2 + "&last_name=" + lname + "&email=" +
                             email + "&password=" + password + "&phone_number=" + phonenumber + "&profile_pic=pic.jpeg&user_type=patient"+"&DeviceID=";
                     //http://vertosys.com/docpat/register.php?first_name=varinder&last_name=singh&email=varinder2@gmail.com&password=123456&phone_number=0987654321&profile_pic=pic.jpeg&user_type=patient
                     Log.e("URL", url);
                     progressDialog.show();

                     apihit();
                 }
             }
                else {
                 Toast.makeText(SignUp.this,"Password Not Match",Toast.LENGTH_SHORT).show();
             }

            }
        });
        ImageView imageView= (ImageView) findViewById(R.id.backsignup);
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(SignUp.this,Sigin.class);
            startActivity(intent);
        }
    });

        LoadImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT);

            }
        });

        LoadImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT2);

            }
        });

        LoadImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT3);

            }
        });

    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTFINELOCAION);

        } else {

        }
    }
    public void getSpeciality(){
        String link23="http://vertosys.com/docpat/GetSpeciality.php";

    }
    private String generateID() {
        String deviceId = android.provider.Settings.Secure.getString(this.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        if ("9774d56d682e549c".equals(deviceId) || deviceId == null) {
            deviceId = ((TelephonyManager) this
                    .getSystemService(Context.TELEPHONY_SERVICE))
                    .getDeviceId();
            if (deviceId == null) {
                Random tmpRand = new Random();
                deviceId = String.valueOf(tmpRand.nextLong());
            }
        }
        return getHash(deviceId);
    }

    public String getHash(String stringToHash) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] result = null;
        try {
            result = digest.digest(stringToHash.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (byte b : result) {
            sb.append(String.format("%02X", b));
        }

        String messageDigest = sb.toString();
        return messageDigest;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUESTFINELOCAION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        }
    }

    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && null != data) {


                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };


                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();
                imageViewLoad1.setImageBitmap(BitmapFactory
                        .decodeFile(ImageDecode));
                Bitmap bm = BitmapFactory.decodeFile(ImageDecode);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                 profilepicencodedString = Base64.encodeToString(b, Base64.DEFAULT);
                Log.e("base64",profilepicencodedString);
                url = link+"first_name="+fname2+"&last_name="+lname+"&email="+
                        email+"&password="+password+"&phone_number="+phonenumber+"&profile_pic="+profilepicencodedString+"&user_type=doctor&office_address="+
                        officeaddreess+"&home_address="+homeaddress+"&doc_certificate=certificate&doc_license=license";
                Log.e("URL",url);


            }

            if (requestCode == IMG_RESULT2 && resultCode == RESULT_OK
                    && null != data) {


                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };


                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();
                imageViewLoad2.setImageBitmap(BitmapFactory
                        .decodeFile(ImageDecode));

            }
            if (requestCode == IMG_RESULT3 && resultCode == RESULT_OK
                    && null != data) {


                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };


                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();
                imageViewLoad3.setImageBitmap(BitmapFactory
                        .decodeFile(ImageDecode));
//                Toast.makeText(this, "PATH"+imageViewLoad1, Toast.LENGTH_LONG)
//                        .show();
            }
        } catch (Exception e) {
//            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();
        }
    }



public void apshitsad(){
String apidoc="http://vertosys.com/docpat/GetSpeciality.php";
    RequestQueue queue = Volley.newRequestQueue(SignUp.this);
    JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, apidoc, null, new Response.Listener<JSONObject>()  {

        @Override
        public void onResponse(JSONObject response) {
            String status = null;
            Log.e("yuipk",response.toString());

            String messagedata= null;
            try {
                messagedata = response.getString("message");
                JSONArray joarray=new JSONArray(messagedata);
                for(int k=0;k<joarray.length();k++){
                    JSONObject jsonobj=joarray.getJSONObject(k);
               String id=     jsonobj.getString("speciality_id");
                    String name=jsonobj.getString("speciality_name");
                    Log.e("yuipk",id+name);

                    objlist.add(new SpecialistMdel(id,name));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            // TODO Auto-generated method stub

        }
    }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            // TODO Auto-generated method stub
            progressDialog.dismiss();


            Toast.makeText(SignUp.this,"Error"+error.getMessage(),Toast.LENGTH_LONG).show();

        }
    });

    queue.add(jsObjRequest);
    textViewspeacial= (TextView) findViewById(R.id.speacialtext);

    textViewspeacial.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog   dialog = new Dialog(SignUp.this);
            dialog.setContentView(R.layout.dialoglayout);
            dialog.setTitle("Specialist");

            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            Customadapter customadapter=new Customadapter(SignUp.this,objlist);
            ListView listView= (ListView) dialog.findViewById(R.id.listviewsa);
            listView.setAdapter(customadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    textViewspeacial.setText(objlist.get(position).getSpeciality_name());

                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    });


}
    public void apihit()
    {
        String textSpe=textViewspeacial.getText().toString();
        for(int k=0;k<objlist.size();k++){
            if(objlist.get(k).getSpeciality_name().equals(textSpe))
            {
                specialistid=objlist.get(k).getSpeciality_id();
                Log.e("DataLoops ID",objlist.get(k).getSpeciality_id()+".."+k);

            }
            Log.e("DataLoops",objlist.get(k).getSpeciality_name()+".."+k);

        }
        url = link+"first_name="+fname2+"&last_name="+lname+"&email="+
                email+"&password="+password+"&phone_number="+phonenumber+"&profile_pic="+"filename.png"+"&user_type=doctor&office_address="+
                officeaddreess+"&home_address="+homeaddress+"&doc_certificate=certificate&doc_license=license"+"&DeviceID="+"&speciality_id="+specialistid;
        Log.e("URL Doctor",url);
        RequestQueue queue = Volley.newRequestQueue(SignUp.this);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()  {

            @Override
            public void onResponse(JSONObject response) {
                String status = null;
                Log.e("Response Base64",response.toString());
                try {
                    status=response.getString("success");
                    Log.e("Status",status);
                    if(status.equals("true")){
                        progressDialog.dismiss();

                        String message=response.getString("message");
                  Toast.makeText(SignUp.this,message,Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(SignUp.this,ScreenSignIn.class);
                        startActivity(intent);
                    }
                    else {
                        progressDialog.dismiss();

                        String message=response.getString("message");

                        Toast.makeText(SignUp.this,message,Toast.LENGTH_SHORT).show();


                    }
                } catch (Exception e) {
                    progressDialog.dismiss();

//                    Toast.makeText(SignUp.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

                // TODO Auto-generated method stub

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                progressDialog.dismiss();


//                 Toast.makeText(SignUp.this,"Error"+error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        queue.add(jsObjRequest);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {

      Intent in=new Intent(this,Sigin.class);
        startActivity(in);

    }


}
