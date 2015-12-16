package bps.go.id.sppks.models;

import java.util.List;

/**
 * Created by handi_000 on 7/4/2015.
 */
public class MInitialize {
    public String error;
    public List<MProv> prov;
    public List<MKab> kab;
    public List<MKec> kec;
    public List<MDesa> desa;
    public List<MBs> bs;
    public List<MNuSrt> mnusrt;
    public List<MPetugas> petugas;
    public List<MTanaman> tanaman;

    public List<MTanaman> getTanaman() {
        return tanaman;
    }

    public void setTanaman(List<MTanaman> tanaman) {
        this.tanaman = tanaman;
    }

    public List<MPetugas> getPetugas() {
        return petugas;
    }

    public void setPetugas(List<MPetugas> petugas) {
        this.petugas = petugas;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String response;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<MProv> getProv() {
        return prov;
    }

    public void setProv(List<MProv> prov) {
        this.prov = prov;
    }

    public List<MKab> getKab() {
        return kab;
    }

    public void setKab(List<MKab> kab) {
        this.kab = kab;
    }

    public List<MKec> getKec() {
        return kec;
    }

    public void setKec(List<MKec> kec) {
        this.kec = kec;
    }

    public List<MDesa> getDesa() {
        return desa;
    }

    public void setDesa(List<MDesa> desa) {
        this.desa = desa;
    }

    public List<MBs> getBs() {
        return bs;
    }

    public void setBs(List<MBs> bs) {
        this.bs = bs;
    }

    public List<MNuSrt> getMnusrt() {
        return mnusrt;
    }

    public void setMnusrt(List<MNuSrt> mnusrt) {
        this.mnusrt = mnusrt;
    }
}
