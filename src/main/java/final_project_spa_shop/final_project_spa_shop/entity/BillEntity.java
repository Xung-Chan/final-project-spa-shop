package final_project_spa_shop.final_project_spa_shop.entity;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill")
@FieldDefaults(level = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@ManyToMany
	@JoinTable(name="bill_service",
	joinColumns =@JoinColumn(name= "bill_id"),
	inverseJoinColumns = @JoinColumn(name="service_id"))
	Set<ServiceEntity> services;
	@Column(name="status")
	boolean status = false;
	@Column(name="cost")
	double cost;
}
