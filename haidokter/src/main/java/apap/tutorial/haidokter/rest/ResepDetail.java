package apap.tutorial.haidokter.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResepDetail {
    private String status;

    @JsonProperty("resep-license")
    private Integer resepLicense;

    @JsonProperty("valid-until")
    private Date validUntil;

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public void setResepLicense(Integer resepLicense) {
        this.resepLicense = resepLicense;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public Integer getResepLicense() {
        return resepLicense;
    }

    public String getStatus() {
        return status;
    }
}
