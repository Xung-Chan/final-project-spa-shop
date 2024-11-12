package final_project_spa_shop.final_project_spa_shop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service")
public class ServiceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	@NotNull(message = "NULL_VALUE")
	@Column(name="name",unique = true)
	String name;
	@Column(name="description",nullable = false)
	String description;
	@Column(name="price",nullable = false)
	long price;
	@Column(name="imagePath")
	String imagePath=null;
	@Column(name="status")
	boolean status;
	
}
