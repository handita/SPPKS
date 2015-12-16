package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by handi_000 on 7/13/2015.
 */
@Table(name = "m_tanaman")
public class MTanaman extends Model{
    @Column(name = "kode_tanaman")
    public int kode_tanaman;
    @Column(name = "nama_tanaman")
    public String nama_tanaman;

    public  MTanaman (){super();}

}
