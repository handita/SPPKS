package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table(name="m_kab")
public class MKab extends Model {
	@Column(name="kode_prov")
	public String kode_prov;
	
	@Column(name="kode_kab")
	public String kode_kab;

	@Column(name="nama_kab")
	public String nama_kab;
	
	public MKab() {
		super();
	}
}
