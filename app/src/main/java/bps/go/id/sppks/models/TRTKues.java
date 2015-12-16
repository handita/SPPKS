package bps.go.id.sppks.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "t_rt_kues")
public class TRTKues extends Model {
	@Column(name = "id_ruta")
	public long id_ruta;
	@Column(name = "variabel")
	public String variabel;
	@Column(name = "nilai")
	public String nilai;
	@Column(name = "is_used")
	public int isUsed;

	public TRTKues() {
		super();
	}

	@Override
	public String toString() {
		return  variabel + "" + nilai;
	}
}
