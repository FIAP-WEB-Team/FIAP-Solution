package model;

import java.util.Calendar;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_BOOKING_FLIGHT")
public class BookingFlight {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	@SequenceGenerator(name = "book_seq", sequenceName = "TB_BOOKING_FLIGHT_SEQ", allocationSize = 1)
	@Column(name = "cod_booking")
	private Integer id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "booking_date")
	private Calendar date;

	@Column(name = "origin_flight")
	private String origin;

	@Column(name = "destiny_flight")
	private String destiny;

	@Column(name = "seat_number")
	private Integer seat;

	@Column(name = "operator_flight")
	private String operator;

	public BookingFlight() {

	}

	public BookingFlight(String origin, String destiny, Integer seat, String operator) {
		this.origin = origin;
		this.destiny = destiny;
		this.seat = seat;
		this.operator = operator;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return id + " - " + date + " - " + origin + " - " + destiny + " - " + seat + " - " + operator;
	}

}
