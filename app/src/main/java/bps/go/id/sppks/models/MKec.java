package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table(name="m_kec")
public class MKec extends Model {
	@Column(name="kode_prov")
	public String kode_prov;
	
	@Column(name="kode_kab")
	public String kode_kab;

	@Column(name="kode_kec")
	public String kode_kec;
	
	@Column(name="nama_kec")
	public String nama_kec;

	@Override
	public String toString() {
		return String.format("[%s] - %s ",kode_prov+kode_kab+kode_kec,nama_kec);
	}

	public MKec() {
		super();
	}
}
