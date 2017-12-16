
package bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_annotation")
public class Product_annotation {
	int id;
	String name;
	float price;
	Category_annotation category;
	Set<User_annotation> users;

	@ManyToOne
	@JoinColumn(name = "cid")
	public Category_annotation getCategory() {
		return category;
	}

	@Id // 表示这个是主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增方法采用MySQL自带
	@Column(name = "id") // 映射到字段id
	public int getId() {
		return id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "price")
	public float getPrice() {
		return price;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_product_annotation", joinColumns = @JoinColumn(name = "pid") , inverseJoinColumns = @JoinColumn(name = "uid") )
	public Set<User_annotation> getUsers() {
		return users;
	}

	public void setCategory(Category_annotation category) {
		this.category = category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setUsers(Set<User_annotation> users) {
		this.users = users;
	}

}
