package com.example.jpamanytoonenew.model;

import jakarta.persistence.*;

@Entity
public class Kommune
{
    @Id
    @Column(length=4, unique = true)
    private String kode;
    private String navn;
    private String href;

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    /* @JsonBackReference er nødvendigt hvis man vil returnere kommune objekter til regionen istedet for navne.
    Hvis ikke man har dette så sidder man fast i et uendeligt loop pga. "cyclic dependency"*/
    Region region;

    public String getKode()
    {
        return kode;
    }

    public void setKode(String kode)
    {
        this.kode = kode;
    }

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public Region getRegion()
    {
        return region;
    }

    public void setRegion(Region region)
    {
        this.region = region;
    }
}
