package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table(name="m_desa")
public class MDesa extends Model{
	@Column(name="kode_prov")
	public String kode_prov;
	
	@Column(name="kode_kab")
	public String kode_kab;

	@Column(name="kode_kec")
	public String kode_kec;
	
	@Column(name="kode_desa")
	public String kode_desa;

	@Column(name="nama_desa")
	public String nama_desa;

	@Column(name="klasifikasi")
	public int klasifikasi;
	
	public MDesa() {
		super();
	}

	@Override
	public String toString() {
		return String.format("[%s%s%s]-%s",kode_prov,kode_kab,kode_kec,nama_desa);
	}
}
