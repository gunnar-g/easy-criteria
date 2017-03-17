package easycriteria;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@MappedSuperclass
//@Entity
public class Project {
	
	@Id
    @GeneratedValue
    private int id;
	
	@Column(name="NAME")
	private String name;
	
	private ProjectType type;
	
	@OneToMany()
	private List<Animal> animals;
	
	@OneToOne
	private Animal theAnimal;
	
	@OneToOne
	private HomeAnimal homeAnimal;
	
	@OneToOne
	private Dog dog;

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

	public ProjectType getType() {
		return type;
	}

	public void setType(ProjectType type) {
		this.type = type;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public Animal getTheAnimal() {
		return theAnimal;
	}

	public void setTheAnimal(Animal theAnimal) {
		this.theAnimal = theAnimal;
	}

	public HomeAnimal getHomeAnimal() {
		return homeAnimal;
	}

	public void setHomeAnimal(HomeAnimal homeAnimal) {
		this.homeAnimal = homeAnimal;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
}
