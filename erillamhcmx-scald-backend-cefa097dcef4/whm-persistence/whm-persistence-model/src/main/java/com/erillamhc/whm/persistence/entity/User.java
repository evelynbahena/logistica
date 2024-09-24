package com.erillamhc.whm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")

@NamedQueries({
	@NamedQuery(name="User.findAll", 
				query="SELECT u FROM User u")
	, @NamedQuery(name="User.findByEmailAndPassword", 
				query="SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private Integer idUser;

	private Boolean available;

	private String email;

	private String lastname;

	private String name;

	private String password;

	//bi-directional many-to-one association to Movement
	@OneToMany(mappedBy="user")
	private List<Movement> movements;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_role")
	private Role role;

	public User() {
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Movement> getMovements() {
		return this.movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public Movement addMovement(Movement movement) {
		getMovements().add(movement);
		movement.setUser(this);

		return movement;
	}

	public Movement removeMovement(Movement movement) {
		getMovements().remove(movement);
		movement.setUser(null);

		return movement;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}