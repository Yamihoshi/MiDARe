package generated;

// default package
// Generated 15 oct. 2017 04:58:57 by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Sport generated by hbm2java
 */
@Entity
@Table(name = "sport", catalog = "sanglier")
public class Sport implements java.io.Serializable {

	private Integer idSport;
	private String nameSport;
	@JsonIgnore
	private Set<Encounter> encounters = new HashSet(0);
	@JsonIgnore
	private Set<Team> teams = new HashSet(0);

	public Sport() {
	}

	public Sport(String nameSport, Set encounters, Set teams) {
		this.nameSport = nameSport;
		this.encounters = encounters;
		this.teams = teams;
	}

	public Sport(String nom) {
		this.nameSport = nom;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_sport", unique = true, nullable = false)
	public Integer getIdSport() {
		return this.idSport;
	}

	public void setIdSport(Integer idSport) {
		this.idSport = idSport;
	}

	@Column(name = "name_sport", length = 20)
	public String getNameSport() {
		return this.nameSport;
	}

	public void setNameSport(String nameSport) {
		this.nameSport = nameSport;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sport")
	public Set<Encounter> getEncounters() {
		return this.encounters;
	}

	public void setEncounters(Set encounters) {
		this.encounters = encounters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sport")
	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Sport [idSport=" + idSport + ", nameSport=" + nameSport + ", encounters=" + encounters + ", teams="
				+ teams + "]";
	}

}
