package com.enoteshare;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Language;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.accenture.fsadd.sonar.business.entity.Sonardashboard;
import com.accenture.fsadd.sonar.business.service.SonarDashboardService;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMongoDBJunit {

	@Autowired
	private PersonService personService;
	
	private Logger log = Logger.getLogger(TestMongoDBJunit.class);

	public static class Sample {
		Contact value;
	}

	public static abstract class Contact {
	};

	public static class Person extends Contact {

		private String id;
		private String name;
		private int age;
		private String author;

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
		}

	}

	@Test
	public void testMongoDB() {
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "test"));
		mongoOps.insert(new Person("Joe", 34));
		log.info(mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class));
		// While print the following
		// Person [id=584feb1a89c6cd3774d176c8, name=Joe, age=34]
		mongoOps.dropCollection("person");

		// XML Configuration
		// <context:property-placeholder location=
		// "classpath:/com/myapp/mongodb/config/mongo.properties"/>
		// <mongo:mongo host="${mongo.host}" port="${mongo.port}">
		//   <mongo:options
		//   connections-per-host="${mongo.connectionsPerHost}"
		//   threads-allowed-to-block-for-connection-multiplier=
		// "${mongo.threadsAllowedToBlockForConnectionMultiplier}"
		//   connect-timeout="${mongo.connectTimeout}"
		//   max-wait-time="${mongo.maxWaitTime}"
		//   auto-connect-retry="${mongo.autoConnectRetry}"
		//   socket-keep-alive="${mongo.socketKeepAlive}"
		//   socket-timeout="${mongo.socketTimeout}"
		//   slave-ok="${mongo.slaveOk}"
		//   write-number="1"
		//   write-timeout="0"
		//   write-fsync="true"/>
		// </mongo:mongo>
		// <mongo:db-factory dbname="database" mongo-ref="mongo"/>
		// <bean id="anotherMongoTemplate" class=
		// "org.springframework.data.mongodb.core.MongoTemplate">
		//   <constructor-arg name="mongoDbFactory" ref="mongoDbFactory"/>
		// </bean>

	}

	// Template:MongoTemplate => MongoOperations --> MongoDB API ("find",
	// "findAndModify", "findOne", "insert", "remove",
	// "save", "update" and "updateMulti".)
	//
	// Domain Class <-> MongoDB DBObject: MongoConverter
	// <--MongoMappingConverter
	// Exception Translation
	// Callback --> MongoDB API
	@Test
	public void testMongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "test"));
		// Throw a exception when error occurred
		mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		// * <p>Controls the acknowledgment of write operations with various
		// options.</p>
		// mongoTemplate.setWriteConcern(writeConcern);

		// set write concern according to the mongodb action(update, insert) or
		// collection or domain class or mongo DBObject
		// mongoTemplate.setWriteConcernResolver(writeConcernResolver);

		// Test
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "test"));
		Person p = new Person("Joe", 34);
		// Insert is used to initially store the object into the database.
		mongoOps.insert(p);
		log.info("Insert: " + p);
		// Find
		p = mongoOps.findById(p.getId(), Person.class);
		log.info("Found: " + p);
		// Update
		mongoOps.updateFirst(new Query(Criteria.where("name").is("Joe")), Update.update("age", 35), Person.class);
		p = mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class);
		log.info("Updated: " + p);
		// Delete
		mongoOps.remove(p);
		// Check that deletion worked
		List<Person> people = mongoOps.findAll(Person.class);
		log.info("Number of people = : " + people.size());
		mongoOps.dropCollection(Person.class);

		// Collection Name
		// 1. By class simple name
		// 2. By @Document
		// 3. By specifying in the insert(save) operation

	}

	// MongoMappingConverter
	// MongoDB _id
	// @id -> _id
	// class{xxx id} -> _id If String Converter<String, ObjectId> or BigInteger
	// Converter<BigInteger, ObjectId>.

	// Test the type Mapping
	@Test
	public void testTypeMapping() {

		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "test"));
		Sample sample = new Sample();
		sample.value = new Person("Joe", 34);
		mongoOps.save(sample);
		log.info("SAVE: " + sample);

		// MappingMongoConverter --> MongoTypeMapper <-DefaultMongoTypeMapper
		// --> TypeInformationMapper
		// Converter<Person, DBObject>?
		// NOTE: _class
		// > show collections
		// sample
		// testShell
		// > db.sample.find()
		/// { "_id" : ObjectId("584ff94d89c6cd0d8474c1f1"), "_class" :
		// "com.enoteshare.TestMongoDBJunit$Sample", "value" : { "_class" :
		// "com.enoteshare.TestMongoDBJunit$Person", "_id" : null, "name" :
		// "Joe", "age" : 34 } }
		// >

	}

	// Test findAndModify
	@Test
	public void testFindAndModify() {
		MongoOperations mongoTemplate = new MongoTemplate(
				new SimpleMongoDbFactory(new MongoClient("localhost"), "test"));
		mongoTemplate.dropCollection(Person.class);
		mongoTemplate.insert(new Person("Tom", 21));
		mongoTemplate.insert(new Person("Dick", 22));
		mongoTemplate.insert(new Person("Harry", 23));
		Query query = new Query(Criteria.where("name").is("Harry"));
		Update update = new Update().inc("age", 1);
		Person p = mongoTemplate.findAndModify(query, update, Person.class); // return's
																				// old
																				// person
																				// object
		assertThat(p.getName(), is("Harry"));
		assertThat(p.getAge(), is(23));
		p = mongoTemplate.findOne(query, Person.class);
		assertThat(p.getAge(), is(24));
		// Now return the newly updated document when updating
		p = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Person.class);
		assertThat(p.getAge(), is(25));

		// Update when exists or insert when not exists
		Query query2 = new Query(Criteria.where("name").is("Mary"));
		p = mongoTemplate.findAndModify(query2, update, new FindAndModifyOptions().returnNew(true).upsert(true),
				Person.class);
		assertThat(p.getName(), is("Mary"));
		assertThat(p.getAge(), is(1));
	}

	@Document
	public static class PersonVersion {
		@Id
		String id;
		String name;
		@Version
		Long version;

		public PersonVersion(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getVersion() {
			return version;
		}

		public void setVersion(Long version) {
			this.version = version;
		}

	}

	// Test Optimistic Lock
	@Test
	public void testOptimisticLock() {
		MongoOperations template = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "test"));
		PersonVersion daenerys = new PersonVersion("Daenerys");
		template.insert(daenerys);
		PersonVersion tmp = template.findOne(new Query(Criteria.where("id").is(daenerys.getId())), PersonVersion.class);
		daenerys.setName("Targaryen");
		template.save(daenerys);
		try {
			template.save(tmp); // throws OptimisticLockingFailureException}
			fail("Not Trhow");
		} catch (OptimisticLockingFailureException e) {

			log.info(e);

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			// template.dropCollection(PersonVersion.class);
		}

	}

	// Test Full Text Query

	@Document(language = "none")
	public static class SomeEntity {
		@TextIndexed
		private String foo;
		@Language
		private String lang;
		private Nested nested;

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}

		public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}

		public Nested getNested() {
			return nested;
		}

		public void setNested(Nested nested) {
			this.nested = nested;
		}

		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	public static class Nested {
		@TextIndexed(weight = 5)
		private String bar;
		private String roo;

		public String getBar() {
			return bar;
		}

		public void setBar(String bar) {
			this.bar = bar;
		}

		public String getRoo() {
			return roo;
		}

		public void setRoo(String roo) {
			this.roo = roo;
		}

		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	@Test
	public void testFullTextQuery() {

		MongoOperations template = new MongoTemplate(
				new SimpleMongoDbFactory(new MongoClient("localhost"), "fulltext"));
		Nested nested = new Nested();
		// nested.setBar("我们还传递了一个数据库连接到该解析器，以便它能够针对任何JDBC连接而工作");
		nested.setBar("I like JDBC");
		// nested.setRoo("输出电信号与转子转角成某种函数关系的电感式角度传感元件。微特电机的一种。旋转变压器的结构与自整角电机相似,区别在于旋转...");

		SomeEntity entity = new SomeEntity();
		// entity.setFoo("简称旋变是一种输出电压随转子转角变化的信号元件JDBC");
		entity.setNested(nested);

		template.save(entity);

		Query query = TextQuery.queryText(new TextCriteria().matchingAny("JDBC")).sortByScore();
		List<SomeEntity> page = template.find(query, SomeEntity.class);
		page.stream().forEach(p -> log.info("Some:entity" + p));

	}

	// Test Query By example

	@Test
	public void testQueryByExample() {
		MongoOperations template = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "test"));

		PersonExample person = new PersonExample();
		person.setFirstname("Dave");
		template.save(person);

		PersonExample probe = new PersonExample();
		probe.setFirstname("Dave");
		personService.findPeople(probe).forEach(p -> log.error(p));

	}

	@Test
	public void testQueryOrderby() {
		MongoOperations mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "fsadd"));
//		PersonExample person3 = new PersonExample();
//		person3.setFirstname("1");
//		person3.setLastname("1");
//		template.insert(person3);
//		PersonExample person1 = new PersonExample();
//		person1.setFirstname("2");
//		person1.setLastname("2");
//		template.insert(person1);
//		PersonExample person2 = new PersonExample();
//		person2.setFirstname("3");
//		person2.setLastname("3");
//		template.insert(person2);
//		PersonExample person4 = new PersonExample();
//		person4.setFirstname("4");
//		person4.setLastname("4");
//		template.insert(person4);
//		List<PersonExample> personList = personService.findAllPeople();
//		Query query = new Query(Criteria.where("projectKey").is("inventory-aid")).with(new Sort(Direction.DESC,"createDate"));
//		Sonardashboard sonarDashboard = template.findOne(query, Sonardashboard.class);
		
//		assertEquals(3, personList.size());
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9000/sonar/api/measures/component?componentKey=inventory-aid&metricKeys=vulnerabilities,bugs,code_smells,ncloc,lines,statements,files,tests,test_failures,test_success_density,coverage,duplicated_lines,duplicated_blocks,duplicated_files,duplicated_lines_density";
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		Object parseResult = JSON.parse(result.getBody());
		DBObject dbObject = (DBObject)parseResult;
		dbObject.put("getAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		mongoTemplate.insert(dbObject.get("component"),"sonarqube_issues");
		assertNotNull(parseResult);
	}
	
	public static class MapReduceResult {
		private String id;
		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		private String author;

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		private int count;

		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	// Test Map-Reduce
	@Test
	public void testMapReduce() throws IOException {
		MongoOperations template = new MongoTemplate(
				new SimpleMongoDbFactory(new MongoClient("localhost"), "mapreduce"));

		List<Note> noteList = new ArrayList<>();
		Note note1 = new Note();
		note1.setTitle("ABC");
		note1.setAuthor("Bill");
		noteList.add(note1);

		Note note2 = new Note();
		note2.setTitle("ABC2");
		note2.setAuthor("Bill");
		noteList.add(note2);

		Note note3 = new Note();
		note3.setTitle("ABC");
		note3.setAuthor("QiQi");
		noteList.add(note3);

		template.insertAll(noteList);

		File mpFile = ResourceUtils.getFile("classpath:mongo/map.js");
		String mapFileString = FileCopyUtils.copyToString(new FileReader(mpFile));

		File reduceFile = ResourceUtils.getFile("classpath:mongo/reduce.js");
		String reduceString = FileCopyUtils.copyToString(new FileReader(reduceFile));

		MapReduceResults<MapReduceResult> results = template.mapReduce("note", mapFileString, reduceString,
				MapReduceResult.class);
		results.forEach(p -> log.error(p));

	}

	// Test Aggregation
	public static class ZipInfo {
		String id;
		String city;
		String state;
		@Field("pop")
		int population;
		@Field("loc")
		double[] location;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public int getPopulation() {
			return population;
		}

		public void setPopulation(int population) {
			this.population = population;
		}

		public double[] getLocation() {
			return location;
		}

		public void setLocation(double[] location) {
			this.location = location;
		}

		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	public static class City {
		String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getPopulation() {
			return population;
		}
		public void setPopulation(int population) {
			this.population = population;
		}
		int population;
		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	public static  class ZipInfoStats {
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public City getBiggestCity() {
			return biggestCity;
		}
		public void setBiggestCity(City biggestCity) {
			this.biggestCity = biggestCity;
		}
		public City getSmallestCity() {
			return smallestCity;
		}
		public void setSmallestCity(City smallestCity) {
			this.smallestCity = smallestCity;
		}
		String id;
		String state;
		City biggestCity;
		City smallestCity;
		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

	@Test
	public void testAggregation() {

		MongoOperations template = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("localhost"), "Agg"));
		// ZipInfo zipInfo = new ZipInfo();
		//
		// zipInfo.city ="Dalian";
		// zipInfo.state ="LiaoNing";
		// zipInfo.id="GanJingZi";
		// zipInfo.population=30000;
		// template.insert(zipInfo);
		//
		// zipInfo.city ="Dalian";
		// zipInfo.state ="LiaoNing";
		// zipInfo.id="ZhongShanQu";
		// zipInfo.population=70000;
		// template.insert(zipInfo);
		//
		//
		// zipInfo.city ="ShangYang";
		// zipInfo.state ="LiaoNing";
		// zipInfo.id="SengYang";
		// zipInfo.population=700000;
		// template.insert(zipInfo);
		//
		// zipInfo.city ="Chendu";
		// zipInfo.state ="SiChuan";
		// zipInfo.id="SiChuan";
		// zipInfo.population=7000000;
		// template.insert(zipInfo);

		Aggregation agg = Aggregation.newAggregation(ZipInfo.class,
				Aggregation.group("state", "city").sum("pop").as("pop"),
				Aggregation.sort(Direction.ASC, "pop", "state", "city"),
				Aggregation.group("state")
				.last("city").as("biggestCity")
				.last("pop").as("biggestPop")
				.first("city").as("smallestCity")
				.first("pop").as("smallestPop"),
				Aggregation.project()
				.and("state").previousOperation()
				.and("biggestCity")
				.nested(Aggregation.bind("name", "biggestCity").and("population", "biggestPop"))
				.and("smallestCity")
				.nested(Aggregation.bind("name", "smallestCity").and("population", "smallestPop")),
				Aggregation.sort(Direction.ASC, "state"));
		AggregationResults<ZipInfoStats> result = template.aggregate(agg, "zipInfo", ZipInfoStats.class);
		result.getMappedResults().forEach(p -> log.error(p));

	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

}
