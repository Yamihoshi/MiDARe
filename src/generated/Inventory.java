package generated;
// Generated 11 nov. 2017 17:04:26 by Hibernate Tools 5.2.6.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Inventory generated by hbm2java
 */
@Entity
@Table(name = "inventory", catalog = "sanglier")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inventory implements java.io.Serializable {

	private InventoryId id;
	private Account account;
	private Card card;
	private Integer quantity;

	public Inventory() {
	}

	public Inventory(InventoryId id, Account account, Card card) {
		this.id = id;
		this.account = account;
		this.card = card;
	}

	public Inventory(InventoryId id, Account account, Card card, Integer quantity) {
		this.id = id;
		this.account = account;
		this.card = card;
		this.quantity = quantity;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "idUser", column = @Column(name = "id_user", nullable = false)),
			@AttributeOverride(name = "idCard", column = @Column(name = "id_card", nullable = false)) })
	public InventoryId getId() {
		return this.id;
	}

	public void setId(InventoryId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_card", nullable = false, insertable = false, updatable = false)
	public Card getCard() {
		return this.card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
