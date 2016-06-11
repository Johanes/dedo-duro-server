package br.com.dedoduro.model;


import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by gasparbarancelli on 11/06/16.
 */
@Document(collection = "DENUNCIA")
public class Denuncia {

    @Id
    private String id;

    @NotNull(message = "Informe o latitude")
    private String latitude;

    @NotNull(message = "Informe a longitude")
    private String longitude;

    @NotNull(message = "Envie uma foto")
    private String foto;

    private String observacao;

    @NotNull(message = "Informe o numero de telefone")
    @Length(min = 10, message = "Número de telefone deve conter ao menos 10 digitos")
    private String numeroTelefone;

    @NotNull(message = "Defina o tipo da denuncia")
    private TipoDenuncia tipoDenuncia;

    private Boolean concluida;

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public TipoDenuncia getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(TipoDenuncia tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

}
