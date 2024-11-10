package final_project_spa_shop.final_project_spa_shop.entity;


import java.sql.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "profile")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProfileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	long id;
	@Pattern(regexp = "^0[1-9][0-9]{8}")
	@Column(name="phoneNumber",nullable = false,length = 10)
	String phoneNumber;
	@Email
	@Column(name="email",nullable = false)
	String email;
	@Past
	@Column(name="birth",nullable = false)
	Date birth;
	@Column(name="fullName",nullable = false)
	String fullName;
	@UpdateTimestamp
	Date updated_at;
	@Lob
	@Column(name="imagePath")
	String imagePath;
	@OneToOne
	@JoinColumn(name="username",nullable = false)
	AccountEntity account;
	
}
