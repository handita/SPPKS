package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table(name="m_nusrt")
public class MNuSrt extends Model {
	
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

	@Column(name="nusrt")
	public String nusrt;

    @Column(name = "nks")
    public String nks;
	
	@Column(name="nama_krt")
	public String nama_krt;
	
	@Column(name="tipe_kues")
	public String tipe_kues;
	
	@Column(name="is_cacah")
	public boolean is_cacah;

	@Column(name="kode_tanaman")
	public int kode_tanaman;
	
	public MNuSrt() {
		super();
	}

	@Override
	public String toString() {
		return String.format("[%s%s%s%s%s]-%s",kode_prov,kode_kab,kode_kec,kode_desa,nbs,nama_krt);
	}
}
