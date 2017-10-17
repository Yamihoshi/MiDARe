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

/**
 * Service generated by hbm2java
 */
@Entity
@Table(name = "service", catalog = "sanglier")
public class Service implements java.io.Serializable {

	private Integer idService;
	private String nameService;
	private Set betsForIdService2 = new HashSet(0);
	private Set betsForIdService1 = new HashSet(0);

	public Service() {
	}

	public Service(String nameService, Set betsForIdService2, Set betsForIdService1) {
		this.nameService = nameService;
		this.betsForIdService2 = betsForIdService2;
		this.betsForIdService1 = betsForIdService1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_service", unique = true, nullable = false)
	public Integer getIdService() {
		return this.idService;
	}

	public void setIdService(Integer idService) {
		this.idService = idService;
	}

	@Column(name = "name_service", length = 20)
	public String getNameService() {
		return this.nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceByIdService2")
	public Set getBetsForIdService2() {
		return this.betsForIdService2;
	}

	public void setBetsForIdService2(Set betsForIdService2) {
		this.betsForIdService2 = betsForIdService2;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceByIdService1")
	public Set getBetsForIdService1() {
		return this.betsForIdService1;
	}

	public void setBetsForIdService1(Set betsForIdService1) {
		this.betsForIdService1 = betsForIdService1;
	}

}