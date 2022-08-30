package model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_TICKET")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_ticket")
	private int id;

	@Column(name = "price")
	private double price;

	@Column(name = "ticket_date")
	private Date date;

	@JoinColumn(name = "cod_payment")
	@ManyToOne
	private Payment payment;

	public Ticket() {
	}

	public Ticket(double price, Date ticketDate, Payment payment) {
		this.price = price;
		this.date = ticketDate;
		this.payment = payment;
	}

	public int getID() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "(" + id + ": " + price + " [" + date + "] - " + payment.getName() + ")";
	}
}
