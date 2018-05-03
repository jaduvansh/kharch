
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DateTest {
	
	static DBCollection  collection;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	static String array_names[] = { "John", "Tim", "Brit", "Robin", "Smith", "Lora", "Jennifer", "Lyla", "Victor",
			"Adam" };

	static String array_address[][] = { { "US", "FL", " Miami" }, { "US", "FL", " Orlando" },
			{ "US", "CA", "San Diego" }, { "US", "FL", " Orlando" }, { "US", "FL", " Orlando" },
			{ "US", "NY", "New York" }, { "US", "NY", "Buffalo" }, { "US", "TX", " Houston" },
			{ "US", "CA", "San Diego" }, { "US", "TX", " Houston" } };

	public static void main(String[] args) {

		try {

		    dbSetup();

			insert();
//
//			findAll();
//
//			BasicDBObject query = findByQuery();
//
//			update(query);
//
//			findAll();
		    
		    findByDate();

		} catch (MongoException e) {
			e.printStackTrace();
		} finally {
		}

	}

	private static void findByDate() {
		System.out.println("Today's date is: "+dateFormat.format(new Date()));
		try {
		BasicDBObject query;
		
			query = new BasicDBObject("date", new BasicDBObject("$lt", dateFormat.parse("01-05-2018")).append("$gte", dateFormat.parse("01-04-2018")));
		

		DBCursor cursor;
		cursor = collection.find(query);
		System.out.println("Person joined on feb month --> " + cursor.count());
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static BasicDBObject findByQuery() {
		BasicDBObject query = new BasicDBObject("age", new BasicDBObject("$gt", 40));

		DBCursor cursor;
		cursor = collection.find(query);
		System.out.println("Person with age > 40 --> " + cursor.count());
		return query;
	}

	private static void update(BasicDBObject query) {
		// update documents found by query "age > 30" with udpateObj "age = 20"
		DBCursor cursor;
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("age", 20);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);

		collection.update(query, updateObj, false, true);

		/**** Find and display ****/
		cursor = collection.find(query);
		System.out.println("Person with age > 40 after update --> " + cursor.count());
	}

	private static void findAll() {
		System.out.println("All Persons: " + collection.getCount());
		DBCursor cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	private static void insert() {
		/**** Insert ****/
		// create a document to store key and value

		BasicDBObject document;
		String address[];
		//for (int i = 0; i < array_names.length; i++) {
			document = new BasicDBObject();
			// value -> String
			document.append("name", array_names[3]);
			// value -> int
			document.append("age", (int) (Math.random() * 60));
			// value -> date
			try {
				document.append("join", dateFormat.parse("02-05-2018"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// value -> array
			document.append("friends", pickFriends());

			address = pickAddress();
			// value --> document
			document.append("address",
					new BasicDBObject("country", address[0]).append("state", address[1]).append("city", address[2]));

			collection.insert(document);

		//}
	}

	private static void dbSetup() {
		// Connect to mongodb
		MongoClient mongo = new MongoClient("localhost", 27017);

		// get database
		// if database doesn't exists, mongodb will create it for you
		//DB db = mongo.getDB("test");
		DB db = mongo.getDB("kharch");

		
		// get collection
		// if collection doesn't exists, mongodb will create it for you
		//collection = db.getCollection("person");
		collection = db.getCollection("expenditure");
	}

	// ----------------------------------------------------
	// These methods are just jused to build random data
	private static String[] pickFriends() {
		int numberOfFriends = (int) (Math.random() * 10);
		LinkedList<String> friends = new LinkedList<String>();
		int random = 0;
		while (friends.size() < numberOfFriends) {
			random = (int) (Math.random() * 10);
			if (!friends.contains(array_names[random]))
				friends.add(array_names[random]);

		}
		String a[] = {};
		return friends.toArray(a);
	}

	private static String[] pickAddress() {
		int random = (int) (Math.random() * 10);
		return array_address[random];
	}
}
