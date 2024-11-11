package final_project_spa_shop.final_project_spa_shop.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentEntity extends BillEntity{
	@ManyToOne
	@JoinColumn(name="customer_id")
	CustomerEntity customer;
	@Future
	@Column(name = "date")
	Date date;
	@OneToOne
	@JoinColumn(name="voucher_id")
	VoucherEntity voucher;
}
