package generated;


// Generated 15 oct. 2017 04:58:57 by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Team generated by hbm2java
 */
@Entity
@Table(name = "team", catalog = "sanglier")
public class Team implements java.io.Serializable {

	private Integer idTeam;
	private Sport sport;
	private String nameTeam;
	@JsonIgnore
	private Set encountersForIdTeam1 = new HashSet(0);
	@JsonIgnore
	private Set encountersForIdTeam2 = new HashSet(0);

	public Team() {
	}

	public Team(Sport sport) {
		this.sport = sport;
	}

	public Team(Sport sport, String nameTeam, Set encountersForIdTeam1, Set encountersForIdTeam2) {
		this.sport = sport;
		this.nameTeam = nameTeam;
		this.encountersForIdTeam1 = encountersForIdTeam1;
		this.encountersForIdTeam2 = encountersForIdTeam2;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_team", unique = true, nullable = false)
	public Integer getIdTeam() {
		return this.idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sport", nullable = false)
	public Sport getSport() {
		return this.sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	@Column(name = "name_team", length = 20)
	public String getNameTeam() {
		return this.nameTeam;
	}

	public void setNameTeam(String nameTeam) {
		this.nameTeam = nameTeam;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teamByIdTeam1")
	public Set<Encounter> getEncountersForIdTeam1() {
		return this.encountersForIdTeam1;
	}

	public void setEncountersForIdTeam1(Set encountersForIdTeam1) {
		this.encountersForIdTeam1 = encountersForIdTeam1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teamByIdTeam2")
	public Set<Encounter> getEncountersForIdTeam2() {
		return this.encountersForIdTeam2;
	}

	public void setEncountersForIdTeam2(Set encountersForIdTeam2) {
		this.encountersForIdTeam2 = encountersForIdTeam2;
	}

}
