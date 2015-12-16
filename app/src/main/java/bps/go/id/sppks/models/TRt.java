package bps.go.id.sppks.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "t_rt",id="id_ruta")
public class TRt extends Model {
	/*@Column(name = "id_ruta")
	public long idRuta;*/
	//id_ruta sudah dibuat di id di model ini
	@Column(name = "kode_kab")
	public String kode_kab;
	
	@Column(name = "kode_prov")
	public String kode_prov;
	
	@Column(name = "kode_kec")
	public String kode_kec;
	
	@Column(name = "kode_desa")
	public String kode_desa;
	
	@Column(name = "nbs")
	public String nbs;

	@Column(name="nks")
	public String nks;

    @Column(name="kode_tanaman")
    public int kode_tanaman;
	
	@Column(name = "nusrt")
	public String nusrt;
	
	@Column(name = "kode_petugas")
	public String kode_petugas;
	
	@Column(name = "date_create")
	public long date_create;
	
	@Column(name = "date_update")
	public long date_update;
	
	@Column(name = "date_sync")
	public long date_sync;
	
	@Column(name = "status_dok")
	public String status_dok;
	
	@Override
	public String toString() {
		return kode_prov+kode_kec+kode_desa+nbs;
	}
	
	@Column(name="is_uploaded")
	public boolean is_uploaded;
	
	@Column(name="is_locked")
	public boolean is_locked;
	
	public TRt(){
		super();
	}
}
