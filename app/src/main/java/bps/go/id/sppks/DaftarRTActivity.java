package bps.go.id.sppks;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;

import bps.go.id.mrhandsdroid.adapter.MrhandsListViewSelectedColorAdapter;
import bps.go.id.mrhandsdroid.models.Filter;
import bps.go.id.mrhandsdroid.models.FilterList;
import bps.go.id.sppks.models.Constants;
import bps.go.id.sppks.models.MBs;
import bps.go.id.sppks.models.MDesa;
import bps.go.id.sppks.models.MInitialize;
import bps.go.id.sppks.models.MKab;
import bps.go.id.sppks.models.MKec;
import bps.go.id.sppks.models.MNuSrt;
import bps.go.id.sppks.models.MProv;

/**
 * Created by handi_000 on 7/2/2015.
 */
public class DaftarRTActivity extends Activity {


    List<MKec> listKecamatan;
    List<MDesa> listDesa;
    List<MBs> listBs;
    List<MNuSrt> listMnuSrt;
    MInitialize mInitialize;
    TextView labelPropinsi, labelKabupaten;

    int tipeKuesioner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            SharedPreferences pref = getSharedPreferences(Constants.SHARED_PREF, 0);
            tipeKuesioner = pref.getInt(Constants.SHARED_TIPE_KUESIONER, 0);

            if (tipeKuesioner == Constants.KUESIONER_HORTIKULTURA)
                setContentView(R.layout.daftar_rt_horti);
            else
                setContentView(R.layout.daftar_rt_kebun);


            labelPropinsi = (TextView) findViewById(R.id.label_propinsi);
            labelKabupaten = (TextView) findViewById(R.id.label_kabupaten);

            MProv prov = new Select().from(MProv.class).executeSingle();
            if (prov == null)
                throw new Exception("Data belum ditemukan silahkan sinkronisasi terlebih dahulu");


            labelPropinsi.setText(prov.nama_prov);

            MKab kab = new Select().from(MKab.class).executeSingle();
            labelKabupaten.setText(kab.nama_kab);

            listKecamatan = new Select().from(MKec.class).execute();
            listDesa = new Select().from(MDesa.class).execute();
            listBs = new Select().from(MBs.class).execute();
            listMnuSrt = new Select().from(MNuSrt.class).where("tipe_kues=?",tipeKuesioner).execute();
            final ListView listViewKec = (ListView) findViewById(R.id.list_kecamatan);
            final ListView listViewDesa = (ListView) findViewById(R.id.list_desa);
            final SwipeMenuListView listViewBs = (SwipeMenuListView) findViewById(R.id.list_rt);

            int color = getResources().getColor(R.color.biru);
            final MrhandsListViewSelectedColorAdapter adapterKec = new MrhandsListViewSelectedColorAdapter(this, listKecamatan, color);
            listViewKec.setAdapter(adapterKec);

            /*
            final MrhandsListViewSelectedColorAdapter adapterDesa = new MrhandsListViewSelectedColorAdapter(this, listDesa,color);
            final MrhandsListViewSelectedColorAdapter adapterNusrt = new MrhandsListViewSelectedColorAdapter(this, listMnuSrt,color);
            listViewDesa.setAdapter(adapterDesa);
            listViewBs.setAdapter(adapterNusrt);
            */

            registerForContextMenu(listViewBs);


            listViewKec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapterKec.setSelectedIndex(position);
                    Filter<MDesa, String> filterDesa = new Filter<MDesa, String>() {
                        @Override
                        public boolean isMatched(MDesa object, String text) {
                            return (object.kode_prov + object.kode_kab + object.kode_kec).equals(text);
                        }
                    };
                    MKec kec = listKecamatan.get(position);
                    final List<MDesa> listDes = new FilterList().filterList(listDesa, filterDesa, kec.kode_prov + kec.kode_kab + kec.kode_kec);
                    int color = getResources().getColor(R.color.biru);
                    final MrhandsListViewSelectedColorAdapter adapterDesa = new MrhandsListViewSelectedColorAdapter(DaftarRTActivity.this, listDes, color);
                    listViewDesa.setAdapter(adapterDesa);
                    listViewDesa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            adapterDesa.setSelectedIndex(position);

                            Filter<MNuSrt, String> filterMnusrt = new Filter<MNuSrt, String>() {
                                @Override
                                public boolean isMatched(MNuSrt object, String text) {
                                    return (object.kode_prov + object.kode_kab + object.kode_kec + object.kode_desa).equals(text);
                                }
                            };
                            MDesa kec = listDes.get(position);
                            final List<MNuSrt> listMnusrt = new FilterList().filterList(listMnuSrt, filterMnusrt, kec.kode_prov + kec.kode_kab + kec.kode_kec + kec.kode_desa);
                            int color = getResources().getColor(R.color.biru);
                            final MrhandsListViewSelectedColorAdapter adapterMnusrt = new MrhandsListViewSelectedColorAdapter(DaftarRTActivity.this, listMnusrt, color);
                            listViewBs.setAdapter(adapterMnusrt);
                            registerForContextMenu(listViewBs);

                            listViewBs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    adapterMnusrt.setSelectedIndex(position);
                                }
                            });
                            SwipeMenuCreator creator = new SwipeMenuCreator() {

                                @Override
                                public void create(SwipeMenu menu) {
                                    // create "open" item
                                    SwipeMenuItem openItem = new SwipeMenuItem(
                                            getApplicationContext());
                                    // set item background
                                    openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                                            0xCE)));
                                    // set item width
                                    openItem.setWidth(90);
                                    // set item title
                                    openItem.setIcon(android.R.drawable.ic_input_add);
                                    // set item title fontsize
                                    openItem.setTitleSize(18);
                                    // set item title font color
                                    openItem.setTitleColor(Color.WHITE);
                                    // add to menu
                                    menu.addMenuItem(openItem);

                                    // create "delete" item
                                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                                            getApplicationContext());
                                    // set item background
                                    deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                                            0x3F, 0x25)));
                                    // set item width
                                    deleteItem.setWidth(90);
                                    // set a icon
                                    deleteItem.setIcon(android.R.drawable.ic_delete);
                                    // add to menu
                                    menu.addMenuItem(deleteItem);
                                }
                            };
                            listViewBs.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(int position, SwipeMenu swipeMenu, int index) {

                                    switch (index) {
                                        case 0:
                                            Intent i = new Intent(DaftarRTActivity.this, KuesionerActivity.class);
                                            SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF, 0);
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putString(Constants.SHARED_PROP, listMnusrt.get(position).kode_prov);
                                            editor.putString(Constants.SHARED_KAB, listMnusrt.get(position).kode_kab);
                                            editor.putString(Constants.SHARED_KEC, listMnusrt.get(position).kode_kec);
                                            editor.putString(Constants.SHARED_DESA, listMnusrt.get(position).kode_desa);
                                            editor.putString(Constants.SHARED_NBS, listMnusrt.get(position).nbs);
                                            editor.putString(Constants.SHARED_NKS, listMnusrt.get(position).nbs);
                                            editor.putString(Constants.SHARED_NAMA_KRT, listMnusrt.get(position).nama_krt);
                                            editor.putString(Constants.SHARED_NAMA_KLASIFIKASI, listMnusrt.get(position).nbs);
                                            editor.putString(Constants.SHARED_NUSRT, listMnusrt.get(position).nusrt);
                                            editor.putInt(Constants.SHARED_JENIS_TANAMAN, listMnusrt.get(position).kode_tanaman);
//                                        editor.putString(Constants.SHARED_NKS,listMnusrt.get(position).);

                                            editor.commit();
                                            startActivity(i);
                                            break;
                                        case 1:
                                            break;
                                    }

                                    return false;
                                }
                            });
                            listViewBs.setMenuCreator(creator);

                        }
                    });

                }
            });
        } catch (Exception ex) {
            Toast.makeText(DaftarRTActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Kuesioner Menu");
        menu.add(0, v.getId(), 0, "Entri");
        menu.add(0, v.getId(), 0, "Hapus");
        menu.add(0, v.getId(), 0, "Upload");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Entri") {
            Toast.makeText(this, "Action 1 invoked", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Hapus") {
            Toast.makeText(this, "Action 2 invoked", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Upload") {
            Toast.makeText(this, "Action 3 invoked", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
    }
}
