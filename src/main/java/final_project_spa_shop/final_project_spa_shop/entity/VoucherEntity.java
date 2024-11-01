package final_project_spa_shop.final_project_spa_shop.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "voucher")
public class VoucherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@Column(name ="percent")
	double percent;
	@CreationTimestamp
	Date created_at;
	@Column(name="expired_at")
	Date expired_at;
	@OneToOne
	@JoinColumn(name="bill_id")
	BillEntity bill;
}
