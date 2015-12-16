package bps.go.id.sppks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bps.go.id.mrhandsdroid.adapter.MrhandsListViewSelectedColorAdapter;
import bps.go.id.mrhandsdroid.models.Filter;
import bps.go.id.mrhandsdroid.models.FilterList;
import bps.go.id.mrhandsdroid.models.Konsistensi;
import bps.go.id.mrhandsdroid.models.Metadata;
import bps.go.id.mrhandsdroid.tools.ExcelHelper;
import bps.go.id.mrhandsdroid.tools.ViewTools;
import bps.go.id.sppks.models.Constants;
import bps.go.id.sppks.models.MDesa;
import bps.go.id.sppks.models.MKab;
import bps.go.id.sppks.models.MKec;
import bps.go.id.sppks.models.MProv;
import bps.go.id.sppks.models.TBulanKues;
import bps.go.id.sppks.models.TRTKues;
import bps.go.id.sppks.models.TRt;
import jxl.read.biff.BiffException;

/**
 * Created by handi_000 on 6/29/2015.
 */
public class KuesionerActivity extends AbstractKuesionerActivity {
    final int HAL1 = 0;
    final int HAL2 = 1;
    final int HAL3 = 2;

    TextView textBulan;

    TRt kuesioner;

    public void initRT() {
        kuesioner = new Select()
                .from(TRt.class)
                .where("kode_prov=? AND kode_kab=? AND nusrt=?",
                        "33", "09", "1").executeSingle();
        if (kuesioner == null) {
            kuesioner = new TRt();
            kuesioner.kode_prov = "33";
            kuesioner.kode_kab = "09";
            kuesioner.kode_kec = "05";
            kuesioner.kode_desa = "010";
            kuesioner.kode_petugas = "330905110";
            kuesioner.nbs = "005B";
            kuesioner.nusrt = "1";
            kuesioner.save();
        }


    }

    int currentPage;

    List<Metadata> listMetadata;


    @Override
    public int[] getViews() {
        int[] result;
        if (tipeKuesioner == Constants.KUESIONER_HORTIKULTURA)
            result = new int[]{R.layout.informasi_wilayah, R.layout.horti_hal_1, R.layout.horti_hal_2};
        else
            result = new int[]{R.layout.informasi_wilayah, R.layout.kebun_hal_1, R.layout.kebun_hal_2};
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_kuesioner, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void saveKuesionerBulan(final int position, int bulan) {
        Filter<Metadata, Integer> filterPage = new Filter<Metadata, Integer>() {
            @Override
            public boolean isMatched(Metadata object, Integer text) {
                return object.page == text;
            }
        };

        List<Metadata> listCurrentPage = new FilterList().filterList(listMetadata, filterPage, position);
        for (Metadata m : listCurrentPage) {
            String value = "";
            Object object = controls.get(m.field);
            boolean isFound = true;
            if (object instanceof RadioGroup) {
                RadioGroup radio = (RadioGroup) object;
                int data = ViewTools.indexOfChild(radio
                        , adapter.getViews().get(position).
                        findViewById(radio.getCheckedRadioButtonId()));
                value = String.valueOf(data);

            } else if (object instanceof EditText) {
                EditText edit = (EditText) object;
                value = edit.getText().toString();

            } else {
                isFound = false;
            }
            if (isFound) {
                if (!value.isEmpty()) {
                    TBulanKues tbulan = new Select().from(TBulanKues.class).
                            where("id_ruta=? AND variabel=? AND bulan=?", kuesioner.getId(), m.field, bulan).
                            executeSingle();
                    if (tbulan == null)
                        tbulan = new TBulanKues();
                    tbulan.bulan = bulan;
                    tbulan.id_ruta = kuesioner.getId();//ini harus diganti
                    tbulan.nilai = String.valueOf(value);
                    tbulan.variabel = m.field;
                    tbulan.isUsed = 1;
                    tbulan.save();
                    Log.i("Save", "Save " + m.field + " dengan nilai " + value);
                }
            }

        }


    }

    private void setValueBulan(int position, int bulan) {
        Filter<Metadata, Integer> filterPage = new Filter<Metadata, Integer>() {
            @Override
            public boolean isMatched(Metadata object, Integer text) {
                return object.page == text;
            }
        };

        List<Metadata> listCurrentPage = new FilterList().filterList(listMetadata, filterPage, position);
        for (Metadata m : listCurrentPage) {
            String value = "";
            Object object = controls.get(m.field);
            TBulanKues tbulan = new Select().from(TBulanKues.class).where("id_ruta=? AND variabel=? AND bulan=?", kuesioner.getId(), m.field, bulan).executeSingle();
            if (object instanceof EditText) {
                EditText currentEditText = (EditText) object;
                if (tbulan == null) {
                    currentEditText.setText("");
                } else {
                    currentEditText.setText(tbulan.nilai);
                }
            } else if (object instanceof RadioGroup) {
                RadioGroup group = (RadioGroup) object;
                if (tbulan != null) {
                    int nilai = Integer.parseInt(tbulan.nilai);
                    ((RadioButton) group.getChildAt(nilai)).setChecked(true);
                } else {
                    group.clearCheck();
                }
            }


        }
    }


    private void saveKuesioner(final int position) {
        Filter<Metadata, Integer> filterPage = new Filter<Metadata, Integer>() {
            @Override
            public boolean isMatched(Metadata object, Integer text) {
                return object.page == text;
            }
        };

        List<Metadata> listCurrentPage = new FilterList().filterList(listMetadata, filterPage, position);
        for (Metadata m : listCurrentPage) {
            String value = "";
            Object object = controls.get(m.field);
            boolean isFound = true;
            if (object instanceof RadioGroup) {
                RadioGroup radio = (RadioGroup) object;
                int data = ViewTools.indexOfChild(radio
                        , adapter.getViews().get(position).
                        findViewById(radio.getCheckedRadioButtonId()));
                value = String.valueOf(data);

            } else if (object instanceof EditText) {
                EditText edit = (EditText) object;
                value = edit.getText().toString();

            } else {
                isFound = false;
            }
            if (isFound) {
                TRTKues rtKues = new Select().from(TRTKues.class).
                        where("id_ruta=? AND variabel=?", kuesioner.getId(), m.field).
                        executeSingle();
                if (rtKues == null)
                    rtKues = new TRTKues();
                rtKues.id_ruta = kuesioner.getId();//ini harus diganti
                rtKues.nilai = String.valueOf(value);
                rtKues.variabel = m.field;
                rtKues.isUsed = 1;
                rtKues.save();
                Log.i("Save", "Save " + m.field + " dengan nilai " + value);
            }

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveKuesioner(currentPage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    HashMap<String, Object> controls;


    public void addToHash(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                addToHash((ViewGroup) v);
            }
            try {
                if (v.getTag() != null) {
                    controls.put(v.getTag().toString(), v);
                }
            } catch (Exception ex) {
                Log.e("eror", "pesan :" + ex.getMessage());
            }
        }
    }

    Filter<Metadata, String> filterName = new Filter<Metadata, String>() {
        @Override
        public boolean isMatched(Metadata object, String text) {
            return object.field.equals(text);
        }
    };


    public void setDataExisting() {
        for (Map.Entry<String, Object> entry : controls.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            TRTKues kues = new Select().from(TRTKues.class).where("id_ruta=? AND variabel=?", kuesioner.getId(), key).executeSingle();
            if (kues != null) {
                if (value instanceof EditText) {
                    ((EditText) value).setText(kues.nilai);
                } else if (value instanceof RadioGroup) {
                    int index = Integer.parseInt(kues.nilai);
                    ((RadioButton) ((RadioGroup) value).getChildAt(index)).setChecked(true);
                }
            }

        }

    }


    public void settingUpControlType() {
        for (Map.Entry<String, Object> entry : controls.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof EditText) {
                List<Metadata> listFoundMetadata = new FilterList().filterList(listMetadata, filterName, key);
                if (listFoundMetadata.size() > 0) {
                    Metadata metadata = listFoundMetadata.get(0);
                    EditText currentEditText = (EditText) value;
                    if (metadata.type.equals("decimal")) {
                        currentEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    } else if (metadata.type.equals("numeric")) {
                        currentEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    }

                    InputFilter[] FilterArray = new InputFilter[1];
                    FilterArray[0] = new InputFilter.LengthFilter(metadata.maxLength);
                    currentEditText.setFilters(FilterArray);

                    if (metadata.type.equals("alphanumeric")) {
                        currentEditText.setEms(10);
                    }
                }
            }


        }

    }

    int indexBulan;

    List<Konsistensi> listKonsistensi;

    int tipeKuesioner;


    public void text(String test) {
        System.out.println(test);
    }

    private void executeRule() {
        //sample expression
//        String sample = "R307a == 1 ? Jump(R307b) :1 ";
        String sample = "test('hello')";
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        try {
            context.registerFunction("test", this.getClass().getDeclaredMethod("text", new Class[]{String.class}));
            String helloWorldReversed = parser.parseExpression(
                    "#reverseString('hello')").getValue(context, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getSharedPreferences(Constants.SHARED_PREF, 0);
        tipeKuesioner = pref.getInt(Constants.SHARED_TIPE_KUESIONER, 0);
        super.onCreate(savedInstanceState);
        currentPage = 1;
        indexBulan = 1;
        View halaman2 = adapter.getViews().get(HAL3);

        //<editor-fold desc="Dapatkan Data di SharedPref">
        String prop = pref.getString(Constants.SHARED_PROP, "");
        String kab = pref.getString(Constants.SHARED_KAB, "");
        String kec = pref.getString(Constants.SHARED_KEC, "");
        String desa = pref.getString(Constants.SHARED_DESA, "");
        String nbs = pref.getString(Constants.SHARED_NBS, "");
        String nusrt = pref.getString(Constants.SHARED_NUSRT, "");
        String namakrt = pref.getString(Constants.SHARED_NAMA_KRT, "");
        String nks = pref.getString(Constants.SHARED_NKS, "");
        int kodeTanaman = pref.getInt(Constants.SHARED_JENIS_TANAMAN, 0);
        //</editor-fold>

        //<editor-fold desc="Load Object">
        MProv mprov = new Select().from(MProv.class).where("kode_prov=?", prop).executeSingle();
        MKab mkab = new Select().from(MKab.class).where("kode_prov=? AND kode_kab=?", prop, kab).executeSingle();
        MKec mkec = new Select().from(MKec.class).where("kode_prov=? AND kode_kab=? AND kode_kec=?", prop, kab, kec).executeSingle();
        MDesa mdesa = new Select().from(MDesa.class).where("kode_prov=? AND kode_kab=? AND kode_kec=? AND kode_desa=?", prop, kab, kec, desa).executeSingle();

        View halaman1 = adapter.getViews().get(HAL1);
        //</editor-fold>

        //<editor-fold desc="Set Text wilayah ke label">
        TextView textPropinsi = (TextView) halaman1.findViewById(R.id.label_propinsi);
        TextView textKabupaten = (TextView) halaman1.findViewById(R.id.label_kabupaten);
        TextView textKecamatan = (TextView) halaman1.findViewById(R.id.label_kecamatan);
        TextView textDesa = (TextView) halaman1.findViewById(R.id.label_desa);
        TextView textNbs = (TextView) halaman1.findViewById(R.id.label_nbs);
        TextView textNks = (TextView) halaman1.findViewById(R.id.label_nks);
        TextView textNamaKrt = (TextView) halaman1.findViewById(R.id.label_nama_krt);
        TextView textKodeTanaman = (TextView) halaman1.findViewById(R.id.label_tanaman_terpilih);

        textPropinsi.setText(mprov.nama_prov);
        textKabupaten.setText(mkab.nama_kab);
        textKecamatan.setText(mkec.nama_kec);
        textDesa.setText(mdesa.nama_desa);
        textNbs.setText(nbs);
        textNks.setText(nks);
        textNamaKrt.setText(namakrt);
        textKodeTanaman.setText(String.valueOf(kodeTanaman));
        //</editor-fold>

        try {
            InputStream stream = getApplicationContext().getAssets().open("meta.xls", Context.MODE_WORLD_READABLE);
            ExcelHelper helper = new ExcelHelper(stream);
            String sheetName="";
            if (tipeKuesioner == Constants.KUESIONER_KEBUN)
                sheetName="metadata_kebun";
            else
                sheetName="metadata_horti";
            listMetadata = helper.getMetadata(sheetName);

            InputStream stream2 = getApplicationContext().getAssets().open("meta.xls", Context.MODE_WORLD_READABLE);
//            ExcelHelper helperKonsistensi = helper.getKonsistensi("konsistensi");
            listKonsistensi = new ExcelHelper(stream2).getKonsistensi("konsistensi");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        //ambil semua kontrol jadikan ke hashmap
        controls = new HashMap<String, Object>();

        for (View v : adapter.getViews()) {
            addToHash((ViewGroup) v);
        }

        //tambahkan validasi dan range
        settingUpControlType();

        //ini harus diganti nanti langsung dari daftar DSRT
        //<editor-fold desc="Init RT">
        kuesioner = new Select()
                .from(TRt.class)
                .where("kode_prov=? AND kode_kab=? AND kode_kec=? AND kode_desa=? AND nbs=? AND nusrt=?  AND nks=? AND kode_tanaman=?",
                        mprov.kode_prov, mkab.kode_kab, mkec.kode_kec, mdesa.kode_desa, nbs, nusrt, nks, kodeTanaman).executeSingle();

        if (kuesioner == null) {
            kuesioner = new TRt();
            kuesioner.kode_prov = mprov.kode_prov;
            kuesioner.kode_kab = mkab.kode_kab;
            kuesioner.kode_kec = mkec.kode_kec;
            kuesioner.kode_desa = mdesa.kode_desa;
            kuesioner.nbs = nbs;
            kuesioner.nusrt = nusrt;
            kuesioner.nks = nks;
            kuesioner.kode_tanaman = kodeTanaman;
            kuesioner.kode_petugas = pref.getString(Constants.SHARED_KODE_PETUGAS, "");
            kuesioner.save();
        } else {
            setDataExisting();
        }
        //</editor-fold>

        ListView listView = (ListView) halaman2.findViewById(R.id.listview_bulan);

        textBulan = (TextView) halaman2.findViewById(R.id.textBulan);

        int color = getResources().getColor(R.color.biru);
        final MrhandsListViewSelectedColorAdapter listViewAdapter = new MrhandsListViewSelectedColorAdapter(this, Arrays.asList(bulanArray), color);

        listView.setAdapter(listViewAdapter);

        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //ingat position disini dimulai dari index 0
                saveKuesioner(position - 1);
                currentPage = position + 1;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewAdapter.setSelectedIndex(position);
                //ganti highlight color
                textBulan.setText(bulanArray[position]);
                saveKuesionerBulan(currentPage - 1, indexBulan);
                indexBulan = position + 1;
                setValueBulan(currentPage - 1, indexBulan);

            }
        });


    }
}




