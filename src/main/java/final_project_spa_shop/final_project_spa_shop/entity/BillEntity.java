package final_project_spa_shop.final_project_spa_shop.entity;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill")
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@ManyToOne
	@JoinColumn(name="employee_id")
	EmployeeEntity employee;
	@ManyToOne
	@JoinColumn(name="customer_id")
	CustomerEntity customer;
	@OneToOne
	@JoinColumn(name="voucher_id")
	VoucherEntity voucher;
	@ManyToMany
	@JoinTable(name="bill_service",
	joinColumns =@JoinColumn(name= "bill_id"),
	inverseJoinColumns = @JoinColumn(name="service_id"))
	Set<ServiceEntity> services;
	@Column(name="status")
	boolean status = false;
}
