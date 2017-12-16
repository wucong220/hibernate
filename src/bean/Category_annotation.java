package bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category_annotation")
public class Category_annotation {
    int id;
    String name;
    Set<Product_annotation> products;
    
    @OneToMany(fetch=FetchType.EAGER)//表示不进行延迟加载(FetchType.LAZY表示要进行延迟加载)
    @JoinColumn(name="cid")
    public Set<Product_annotation> getProducts() {
		return products;
	}
	public void setProducts(Set<Product_annotation> paset) {
		this.products = paset;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}

