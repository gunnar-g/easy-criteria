package easycriteria;

import static org.junit.Assert.assertEquals;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.JoinType;

import org.junit.Before;
import org.junit.Test;

public class EasyCriteriaQueryTestInheritance {

	private EntityManager entityManager;
	private JPAQuery query;

	@Before
	public void setup() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("easycriteria");

		entityManager = entityManagerFactory.createEntityManager();

		query = new JPAQuery(entityManager);
	}

	@Test
	public void testNestedPropertiesCondition() {
		setupAnimalsTestData();
		
		QDog_ dog = new QDog_();
		
//		List<Dog> dogs = query.select(Dog.class).where(dog.name.eq("dog2")).getResultList();		
//		assertEquals(2, dogs.size());
		
//		List<Animal> animals = query.select(Animal.class).getResultList();
//		System.out.println("============================");
//		System.out.println(animals.size() + " | " + animals);
//		System.out.println("============================");
		
		List<Dog> dogs1 = query.select(Dog.class).where(dog.petAddress.zip.eq("zip1")).getResultList();
		System.out.println("============================");
		System.out.println(dogs1);
		System.out.println("============================");
		assertEquals(1, dogs1.size());
		
		QAnimal_ animal = new QAnimal_();
		QLargeProject_ largeProject = new QLargeProject_();
		QHomeAnimal_ homeAnimal = new QHomeAnimal_();
		
//		List<LargeProject> projects = query.select(LargeProject.class)
//				.where(largeProject.type.eq(ProjectType.DEVELOPMENT))
//				.getResultList();
//		System.out.println("============================");
//		System.out.println(projects.size() + " | " + projects);
//		System.out.println("============================");
		
		List<LargeProject> projects1 = query.select(LargeProject.class)
				.join(largeProject.homeAnimal, JoinType.INNER, homeAnimal)
					.on(homeAnimal.owner.eq("owner1"))
				.endJoin()
				.getResultList();
		System.out.println("============================");
		System.out.println(projects1.size() + " | " + projects1);
		System.out.println("============================");
		
	}
	
	
	private void setupAnimalsTestData() {
		beginTx();

		Animal a1 = new Animal();
		a1.setName("animal1");
		entityManager.persist(a1);
		
		Animal a2 = new Animal();
		a1.setName("animal2");
		entityManager.persist(a2);
		
		Animal a3 = new Animal();
		a1.setName("animal3");
		entityManager.persist(a3);
		
		//-------------------
		
		HomeAnimal ha1 = new HomeAnimal();
		ha1.setName("home_animal1");
		ha1.setOwner("owner1");
		PetAddress haPa1 = new PetAddress();
		haPa1.setState("st1");
		haPa1.setZip("zip1");
		ha1.setPetAddress(haPa1);
		entityManager.persist(ha1);
		
		HomeAnimal ha2 = new HomeAnimal();
		ha2.setName("home_animal2");
		ha2.setOwner("owner2");
		PetAddress haPa2 = new PetAddress();
		haPa2.setState("st2");
		haPa2.setZip("zip2");
		ha2.setPetAddress(haPa2);
		entityManager.persist(ha2);
		
		HomeAnimal ha3 = new HomeAnimal();
		ha3.setName("home_animal3");
		ha3.setOwner("owner2");
		PetAddress haPa3 = new PetAddress();
		haPa3.setState("st1");
		haPa3.setZip("zip1");
		ha3.setPetAddress(haPa3);
		entityManager.persist(ha3);
		
		//-------------------
		
		Dog d1 = new Dog();
		d1.setName("dog1");
		d1.setOwner("owner1");
		d1.setDogBreed("db1");
		d1.setAge(2);
		PetAddress dPa1 = new PetAddress();
		dPa1.setState("st1");
		dPa1.setZip("zip1");
		d1.setPetAddress(dPa1);
		entityManager.persist(d1);
		
		Dog d2 = new Dog();
		d2.setName("dog2");
		d2.setOwner("owner2");
		d2.setDogBreed("db2");
		d2.setAge(3);
		PetAddress dPa2 = new PetAddress();
		dPa2.setState("st2");
		dPa2.setZip("zip2");
		d2.setPetAddress(dPa2);
		entityManager.persist(d2);
		
		Dog d3 = new Dog();
		d3.setName("dog2");
		d3.setOwner("owner2");
		d3.setDogBreed("db2");
		d3.setAge(3);
		PetAddress dPa3 = new PetAddress();
		dPa3.setState("st2");
		dPa3.setZip("zip2");
		d3.setPetAddress(dPa3);
		entityManager.persist(d3);
		
		//-------------------
		Cat c1 = new Cat();
		c1.setCatBreed("b1");
		c1.setName("cat1");
		c1.setOwner("cat_owner1");
		PetAddress cPa1 = new PetAddress();
		cPa1.setState("st1");
		cPa1.setZip("zip4");
		c1.setPetAddress(cPa1);
		entityManager.persist(c1);
		
		Cat c2 = new Cat();
		c2.setCatBreed("b2");
		c2.setName("cat2");
		c2.setOwner("cat_owner1");
		PetAddress cPa2 = new PetAddress();
		cPa2.setState("st1");
		cPa2.setZip("zip5");
		c2.setPetAddress(cPa2);
		entityManager.persist(c2);

		//-------------------
		LargeProject p1 = new LargeProject();
		p1.setName("project1");
		p1.setType(ProjectType.DEVELOPMENT);
		p1.setTheAnimal(d1);
		p1.setHomeAnimal(ha1);
		p1.setDog(d2);
		entityManager.persist(p1);
		
		
		LargeProject p2 = new LargeProject();
		p2.setName("project2");
		p2.setType(ProjectType.RESEARCH);
		p2.setTheAnimal(d2);
		p2.setHomeAnimal(ha2);
		entityManager.persist(p2);
		
		commitTx();
	}

	private void beginTx() {
		entityManager.getTransaction().begin();
	}

	private void commitTx() {
		entityManager.getTransaction().commit();
	}
}