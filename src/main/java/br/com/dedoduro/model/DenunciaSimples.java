package br.com.dedoduro.model;

/**
 * Created by gasparbarancelli on 11/06/16.
 */
public class DenunciaSimples {

    private String id;
    private String latitude;
    private String longitude;
    private TipoDenuncia tipoDenuncia;

    public DenunciaSimples() {
    }

    public DenunciaSimples(Denuncia denuncia) {
        id = denuncia.getId();
        latitude = denuncia.getLatitude();
        longitude = denuncia.getLongitude();
        tipoDenuncia = denuncia.getTipoDenuncia();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public TipoDenuncia getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(TipoDenuncia tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }
}
