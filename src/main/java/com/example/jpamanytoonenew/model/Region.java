package com.example.jpamanytoonenew.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Region
{
    @Id
    @Column(length=4)
    private String kode;
    private String href;
    private String navn;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();

    public String getKode()
    {
        return kode;
    }

    public void setKode(String kode)
    {
        this.kode = kode;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    public Set<Kommune> getKommuner()
    {
        return kommuner;
    }

    public void setKommuner(Set<Kommune> kommuner)
    {
        this.kommuner = kommuner;
    }

    public List<String> getKommuneNavne()
    {
        List<String> kommuneNavne = new ArrayList<>();
        for (Kommune kommune : this.kommuner) {
            kommuneNavne.add(kommune.getNavn()); // Assuming the Kommune class has a getNavn() method
        }
        return kommuneNavne;
    }

    /* Hvis jeg vil have en liste af kommuneopbjekter istedet for bare navne.
    public List<Kommune> getKommuneNavne()
    {
        List<Kommune> kommuneNavne = new ArrayList<>();
        for (Kommune kommune : this.kommuner) {
            kommuneNavne.add(kommune); // Assuming the Kommune class has a getNavn() method
        }
        return kommuneNavne;
    }
    */
}
