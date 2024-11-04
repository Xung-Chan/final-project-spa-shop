package final_project_spa_shop.final_project_spa_shop.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name ="post")
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@NotNull(message = "NULL_VALUE")
	CustomerEntity customer;
	String content;
	@CreationTimestamp
	Date createdAt;
}
