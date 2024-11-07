package final_project_spa_shop.final_project_spa_shop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "feedback")
public class FeedbackEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@OneToOne
	@JoinColumn(name="customer_id")
	CustomerEntity customer;
	@Column(name="rate",nullable = false)
	@Min(value = 0)
	@Max(value = 5)
	int rate;
	@Column(name="description", columnDefinition = "TEXT")
	String description;
}
