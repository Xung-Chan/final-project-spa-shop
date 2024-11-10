package final_project_spa_shop.final_project_spa_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class AccountEntity {
	@Id
	@Column(name = "username",nullable = false)
	String username;
	@Column(name="password",nullable = false)
	String password;
	@ManyToOne
	@JoinColumn(name="role_id",nullable = false)
	RoleEntity role;
	@OneToOne
	@JoinColumn(name="profile_id")
	ProfileEntity profile;
}
