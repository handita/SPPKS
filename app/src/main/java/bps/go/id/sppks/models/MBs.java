package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="m_bs")
public class MBs extends Model{
	@Column(name="kode_prov")
	public String kode_prov;
	
	@Column(name="kode_kab")
	public String kode_kab;

	@Column(name="kode_kec")
	public String kode_kec;
	
	@Column(name="kode_desa")
	public String kode_desa;

	@Column(name="nbs")
	public String nbs;
	
	public MBs() {
		super();
	}
	
}
