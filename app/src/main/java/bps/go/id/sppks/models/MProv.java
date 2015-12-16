package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;



@Table(name = "m_prop")
public class MProv extends Model {
	@Column(name = "kode_prov")
	public String kode_prov;

	@Column(name = "nama_prov")
	public String nama_prov;

	public MProv() {
		super();
	}
}
