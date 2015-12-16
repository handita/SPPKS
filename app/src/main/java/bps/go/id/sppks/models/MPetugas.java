package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table(name="m_petugas")
public class MPetugas extends Model{
	@Column(name="kode_prov")
	public String kode_prov;
	
	@Column(name="kode_kab")
	public String kode_kab;

	@Column(name="kode_petugas")
	public String kode_petugas;

	@Column(name="nama_petugas")
	public String nama_petugas;
/*
 * {"kode_petugas":"340053000",
 * "kode_prov":"15",
 * "kode_kab":"01",
 * "nama_petugas":"KORTIM A",
 * "status":null,
 * "jabatan":"K","flag":null,"kode_kortim":null,"token":"XzcX4hRwB","email":"kortim_a@localhost.com","blank1":null
 */
	@Column(name="jabatan")
	public String jabatan;

	@Column(name="status")
	public String status;

	@Column(name="token")
	public String token;
	
	@Column(name="email")
	public String email;

	@Column(name="blank1")
	public String blank1;
	
	@Column(name="kode_kortim")
	public String kode_kortim;
	
	@Column(name="flag")
	public String flag;
	
	@Column(name="password")
	public String password;
	
	public MPetugas() {
		super();
	}
}
