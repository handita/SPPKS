package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "t_bulan_kues")
public class TBulanKues extends Model {
    @Column(name = "id_ruta")
    public long id_ruta;
    @Column(name = "bulan")
    public int bulan;
    @Column(name = "variabel")
    public String variabel;
    @Column(name = "nilai")
    public String nilai;
    @Column(name = "is_used")
    public int isUsed;

    public TBulanKues() {
        super();
    }

//    public TRt getTrt() {
//
//        return new Select().from(TRt.class).where("id=?", idRuta).executeSingle();
//    }

    @Override
    public String toString() {
        return  bulan + " " + variabel + " " + nilai;
    }

}
