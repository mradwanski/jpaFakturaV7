/*
 * Copyright (C) 2014 mradwanski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pl.mirco.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mradwanski
 */
@Entity
public class Dokument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @ManyToOne
    private TypDokumentu typDokumentu;
    @NotNull
    @Size(min = 2, max = 24)
    private String nazwa;
    @NotNull
    @ManyToOne
    private Kontrahent kontrahent;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataWystawienia;
    @NotNull
    @Size(min = 2, max = 128)
    private String miejcsceWystawienia;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataSprzedazy;
    @Temporal(TemporalType.DATE)
    private Date terminPlatnosci;
    private String formaPlatnosci;
    @OneToMany(mappedBy = "dokument", fetch=FetchType.LAZY)
    private Set<PozycjaDokumentu> pozycjaDokumentus = new HashSet<>(0);
    private boolean wydrukowany;
    @Size(max = 1024)
    private String opis;
    @NotNull
    @ManyToOne
    private Uzytkownik uzytkownik;


    public Dokument() {
            dataWystawienia = new Date();
            dataSprzedazy = new Date();
            terminPlatnosci = new Date();
            wydrukowany = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dokument)) {
            return false;
        }
        Dokument other = (Dokument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazwa;
    }

    public TypDokumentu getTypDokumentu() {
        return typDokumentu;
    }

    public void setTypDokumentu(TypDokumentu typDokumentu) {
        this.typDokumentu = typDokumentu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Kontrahent getKontrahent() {
        return kontrahent;
    }

    public void setKontrahent(Kontrahent kontrahent) {
        this.kontrahent = kontrahent;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public String getMiejcsceWystawienia() {
        return miejcsceWystawienia;
    }

    public void setMiejcsceWystawienia(String miejcsceWystawienia) {
        this.miejcsceWystawienia = miejcsceWystawienia;
    }

    public Date getDataSprzedazy() {
        return dataSprzedazy;
    }

    public void setDataSprzedazy(Date dataSprzedazy) {
        this.dataSprzedazy = dataSprzedazy;
    }

    public Date getTerminPlatnosci() {
        return terminPlatnosci;
    }

    public void setTerminPlatnosci(Date terminPlatnosci) {
        this.terminPlatnosci = terminPlatnosci;
    }

    public String getFormaPlatnosci() {
        return formaPlatnosci;
    }

    public void setFormaPlatnosci(String formaPlatnosci) {
        this.formaPlatnosci = formaPlatnosci;
    }

    public Set<PozycjaDokumentu> getPozycjaDokumentus() {
        return pozycjaDokumentus;
    }

    public void setPozycjaDokumentus(Set<PozycjaDokumentu> pozycjaDokumentus) {
        this.pozycjaDokumentus = pozycjaDokumentus;
    }

    public boolean isWydrukowany() {
        return wydrukowany;
    }

    public void setWydrukowany(boolean wydrukowany) {
        this.wydrukowany = wydrukowany;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }
    
}
