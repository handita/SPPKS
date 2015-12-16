package bps.go.id.sppks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.activeandroid.query.Delete;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import bps.go.id.sppks.models.Constants;
import bps.go.id.sppks.models.MBs;
import bps.go.id.sppks.models.MDesa;
import bps.go.id.sppks.models.MInitialize;
import bps.go.id.sppks.models.MKab;
import bps.go.id.sppks.models.MKec;
import bps.go.id.sppks.models.MNuSrt;
import bps.go.id.sppks.models.MPetugas;
import bps.go.id.sppks.models.MProv;
import bps.go.id.sppks.models.MTanaman;

/**
 * Created by handi_000 on 6/27/2015.
 */
public class HomeActivity extends Activity {


    public MInitialize mInitialize;
    EditText textKodePetugas, textToken;
    ProgressBar progressBarToken;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button buttonSync = (Button) findViewById(R.id.button_sinkron);
        buttonSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();

                View dialogSync = inflater.inflate(R.layout.dialog_sync, null);
                textKodePetugas = (EditText) dialogSync.findViewById(R.id.text_kode_petugas);
                textToken = (EditText) dialogSync.findViewById(R.id.text_token);
                progressBarToken = (ProgressBar) dialogSync.findViewById(R.id.progressbar_sync);

                progressBarToken.setVisibility(View.INVISIBLE);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(dialogSync)
                        // Add action buttons
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface d) {
                        final Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                b.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        new InitializeTask().execute();
                                    }
                                });
                            }
                        });
                    }
                });

                dialog.show();


            }
        });


        Button buttonHortikultura= (Button) findViewById(R.id.button_hortikultura);
        buttonHortikultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences(Constants.SHARED_PREF,0);
                SharedPreferences.Editor editor= pref.edit();
                editor.putInt(Constants.SHARED_TIPE_KUESIONER,Constants.KUESIONER_HORTIKULTURA);
                editor.commit();

                Intent intent = new Intent(HomeActivity.this, DaftarRTActivity.class);
                startActivity(intent);
            }
        });

        Button buttonKebun= (Button) findViewById(R.id.button_kebun);
        buttonKebun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences(Constants.SHARED_PREF,0);
                SharedPreferences.Editor editor= pref.edit();
                editor.putInt(Constants.SHARED_TIPE_KUESIONER,Constants.KUESIONER_KEBUN);
                editor.commit();

                Intent intent = new Intent(HomeActivity.this, DaftarRTActivity.class);
                startActivity(intent);
            }
        });
        /*MrhandsViewPagerAdapter adapter=new MrhandsViewPagerAdapter();*/

    }

    public class InitializeTask extends AsyncTask<Void, Void, Void> {

        Exception ex;

        @Override
        protected void onPreExecute() {
            progressBarToken.setVisibility(View.VISIBLE);
            textKodePetugas.setEnabled(false);
            textToken.setEnabled(false);
        }

        MInitialize init = null;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<MInitialize> response = null;
                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.add("token", textToken.getText().toString());
                map.add("kode_petugas", textKodePetugas.getText().toString());
                restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
                init = restTemplate.postForObject(Constants.URL_API_GET_DATA, map, MInitialize.class);
            }catch (RestClientException ex){
                this.ex=ex;
            } catch (Exception ex) {
                this.ex = ex;
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void mInit) {
            try {
                progressBarToken.setVisibility(View.INVISIBLE);
                textKodePetugas.setEnabled(true);
                textToken.setEnabled(true);
                if (ex != null) {
                    ex.printStackTrace();
                    if (ex instanceof ResourceAccessException)
                        Toast.makeText(HomeActivity.this, "Koneksi tidak dapat dilakukan, periksa koneksi internet anda", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(HomeActivity.this,ex.getMessage(),Toast.LENGTH_LONG).show();
                    return;
                }
                mInitialize = init;
                if (mInitialize.error.equals("1")) {
                    Toast.makeText(HomeActivity.this, mInitialize.getResponse(), Toast.LENGTH_LONG).show();
                    return;
                }else{
                    new Delete().from(MBs.class).execute();
                    new Delete().from(MProv.class).execute();
                    new Delete().from(MKab.class).execute();
                    new Delete().from(MDesa.class).execute();
                    new Delete().from(MKec.class).execute();
                    new Delete().from(MNuSrt.class).execute();

                    List<MProv> listProv=mInitialize.getProv();
                    List<MKab> listKab=mInitialize.getKab();
                    List<MKec> listKec=mInitialize.getKec();
                    List<MDesa> listDesa=mInitialize.getDesa();
                    List<MBs> listBS=mInitialize.getBs();
                    List<MNuSrt> listMnusrt=mInitialize.getMnusrt();
                    List<MPetugas> listPetugas=mInitialize.getPetugas();
                    List<MTanaman> listTanaman=mInitialize.getTanaman();

                    for(MProv prov : listProv){
                        prov.save();
                    }
                    for(MKab kab:listKab){
                        kab.save();
                    }
                    for(MKec kec:listKec){
                        kec.save();
                    }
                    for(MDesa des:listDesa){
                        des.save();
                    }
                    for(MBs bs:listBS){
                        bs.save();
                    }
                    for(MNuSrt nusrt:listMnusrt){
                        nusrt.save();
                    }
                    for(MPetugas petugas:listPetugas){
                        petugas.save();
                    }
                    for(MTanaman tanaman:listTanaman){
                        tanaman.save();
                    }

                }
                Toast.makeText(HomeActivity.this, "Berhasil di sinkron", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(HomeActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }

}
