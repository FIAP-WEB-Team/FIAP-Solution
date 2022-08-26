package model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PAYMENT")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_payment")
	private int id = -1293019203;
	
	@Column(name = "payment")
	private String name;
	
	public Payment() {
	}
	
	public Payment(String name) {
		this.name = name;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
