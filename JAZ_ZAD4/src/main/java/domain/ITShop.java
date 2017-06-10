package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name = "all.things", query="SELECT t FROM ITShop t"),
	@NamedQuery(name = "id.thing", query="FROM ITShop t WHERE t.id=:ITShopId"),
	@NamedQuery(name = "name.thing", query="FROM ITShop t WHERE t.name like :ITShopName"),
	@NamedQuery(name = "lower.price", query = "FROM ITShop t WHERE t.price >= :LowerPrice"),
	@NamedQuery(name = "higher.price", query = "FROM ITShop t WHERE t.price <= :HigherPrice"),
	//----
	@NamedQuery(name = "ram.type", query = "FROM ITShop t WHERE t.type like '%RAM%'"),
	@NamedQuery(name = "motherboard.type", query = "FROM ITShop t WHERE t.type like '%Motherboard%'"),
	@NamedQuery(name = "gpu.type", query = "FROM ITShop t WHERE t.type like '%GPU%'"),
	@NamedQuery(name = "hdd.type", query = "FROM ITShop t WHERE t.type like '%HDD%'")
	
})
public class ITShop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String name;
	public String type;
	public int price;

	
	private List<Comments> comments = new ArrayList<Comments>();
	
		
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlTransient
	@OneToMany(mappedBy="ITShop")
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

}
