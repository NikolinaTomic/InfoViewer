package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.JSON_Parser;
import model.Paket;
import model.Tabela;

public class JSONParserTest {
	
	private static Tabela[] tabele;
	private static Paket[] paketi;
	private static String uspesan = "\r\n" + 
			"[\r\n" + 
			"\r\n" + 
			"  \r\n" + 
			"{\r\n" + 
			" \r\n" + 
			"	\"title\": \"Drzava\",\r\n" + 
			"        \"paket\":\"p2\",\r\n" + 
			"        \"parent\": [],\r\n" + 
			" \r\n" + 
			" 	\"attributes\": [\r\n" + 
			" \r\n" + 
			"     {\r\n" + 
			"\r\n" + 
			"      	\"name\": \"Oznaka\",\r\n" + 
			"        \"code\": \"DRZ_ID\",\r\n" + 
			"        \"type\": \"string\",\r\n" + 
			"        \"maxString\": 3,\r\n" + 
			"        \"is_mandatory\":true\r\n" + 
			"      },\r\n" + 
			"{\r\n" + 
			"\r\n" + 
			"      	\"name\": \"Naziv\",\r\n" + 
			"        \"code\": \"DRZ_NAZIV\", \r\n" + 
			"        \"type\": \"string\",\r\n" + 
			"        \"maxString\": 128,\r\n" + 
			"        \"is_mandatory\":true\r\n" + 
			"      },\r\n" + 
			"\r\n" + 
			"      {\r\n" + 
			"\r\n" + 
			"      	\"name\": \"Glavni grad\",\r\n" + 
			"        \"code\": \"NM_PB\",\r\n" + 
			"        \"type\": \"string\",\r\n" + 
			"        \"maxString\": 5,  \r\n" + 
			"       \"is_mandatory\":false\r\n" + 
			"\r\n" + 
			"      }\r\n" + 
			" \r\n" + 
			"   ],\r\n" + 
			"\r\n" + 
			"  	\"key\": [\"DRZ_ID\"],\r\n" + 
			"  	\"relations\": [\r\n" + 
			"  	    {\r\n" + 
			"     \"relation_title\": \"Pripada drzavi\",\r\n" + 
			"      \"sourceKey\": [\"DRZ_ID\"],\r\n" + 
			"      \"destinationTable\": \"Naseljeno mesto\",\r\n" + 
			"      \"destinationKey\": [\"DRZ_ID\"]\r\n" + 
			"    }\r\n" + 
			"]\r\n" + 
			"},\r\n" + 
			"\r\n" + 
			"{\r\n" + 
			"\r\n" + 
			"  \"title\": \"Naseljeno mesto\",\r\n" + 
			"    \"paket\":\"\",\r\n" + 
			"  \"parent\": [\"Drzava\"],\r\n" + 
			" \r\n" + 
			"\r\n" + 
			"  \"attributes\": [\r\n" + 
			"    {\r\n" + 
			"      \"code\": \"DRZ_ID\",\r\n" + 
			"      \"name\": \"Drzava\",\r\n" + 
			"      \"maxString\": 3,\r\n" + 
			"      \"is_mandatory\": true,\r\n" + 
			"      \"type\": \"string\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"code\": \"NM_PB\", \r\n" + 
			"      \"name\": \"Post. broj\",\r\n" + 
			"      \"maxString\": 5,\r\n" + 
			"      \"is_mandatory\": true,\r\n" + 
			"      \"type\": \"string\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"code\": \"NM_NAZIV\",\r\n" + 
			"      \"name\": \"Naziv\",\r\n" + 
			"      \"maxString\": 128,\r\n" + 
			"      \"is_mandatory\": true,\r\n" + 
			"      \"type\": \"string\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"code\": \"NM_STAN\",\r\n" + 
			"      \"name\": \"Broj stanovnika\",\r\n" + 
			"      \"maxString\": 10,\r\n" + 
			"      \"is_mandatory\": false,\r\n" + 
			"      \"type\": \"number\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"code\": \"NM_GRAD\",\r\n" + 
			"      \"name\": \"Grad\",\r\n" + 
			"      \"is_mandatory\": true,\r\n" + 
			"      \"type\": \"boolean\"\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"  \"relations\": [\r\n" + 
			"  	        {\r\n" + 
			"          \"relation_title\": \"Mesto stanovanja\",\r\n" + 
			"            \"sourceKey\": [\"NM_PB\",\"DRZ_ID\"],\r\n" + 
			"            \"destinationTable\": \"Student\",\r\n" + 
			"            \"destinationKey\": [\"STU_MESTOSTAN\",\"STU_DRZSTAN\"]\r\n" + 
			"       },\r\n" + 
			"\r\n" + 
			"        {\r\n" + 
			"          \"relation_title\": \"Mesto rodjenja\",\r\n" + 
			"            \"sourceKey\": [\"NM_PB\",\"DRZ_ID\"],\r\n" + 
			"            \"destinationTable\": \"Student\",\r\n" + 
			"            \"destinationKey\": [\"STU_MESTORODJ\",\"STU_DRZRODJ\"]\r\n" + 
			"       },\r\n" + 
			"    {\r\n" + 
			"     \"relation_title\": \"Glavni grad\",\r\n" + 
			"      \"sourceKey\": [\"NM_PB\",\"DRZ_ID\"],\r\n" + 
			"      \"destinationTable\": \"Drzava\",\r\n" + 
			"      \"destinationKey\": [\"NM_PB\",\"DRZ_ID\"]\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"  ],\r\n" + 
			"  \"key\": [\r\n" + 
			"    \"DRZ_ID\",\r\n" + 
			"    \"NM_PB\"\r\n" + 
			"  ]\r\n" + 
			"},{\r\n" + 
			"  \"title\": \"Student\",\r\n" + 
			"        \"paket\":\"\",\r\n" + 
			"        \"parent\": [],\r\n" + 
			"        \r\n" + 
			"  \"attributes\": [\r\n" + 
			"\r\n" + 
			"       {\r\n" + 
			"            \"name\": \"Indeks\",\r\n" + 
			"            \"code\": \"STU_INDEKS\",\r\n" + 
			"            \"type\": \"string\",\r\n" + 
			"            \"maxString\": 15,\r\n" + 
			"            \"is_mandatory\":true\r\n" + 
			"  },\r\n" + 
			" \r\n" + 
			"       {\r\n" + 
			"            \"name\": \"Ime\",\r\n" + 
			"            \"code\": \"STU_IME\",\r\n" + 
			"            \"type\": \"string\",\r\n" + 
			"            \"maxString\": 64,\r\n" + 
			"            \"is_mandatory\":true\r\n" + 
			"  },\r\n" + 
			" \r\n" + 
			"       {\r\n" + 
			"            \"name\": \"Prezime\",\r\n" + 
			"            \"code\": \"STU_PREZIME\",\r\n" + 
			"            \"type\": \"string\",\r\n" + 
			"            \"maxString\": 64,\r\n" + 
			"            \"is_mandatory\":true\r\n" + 
			"  },\r\n" + 
			" \r\n" + 
			"       {\r\n" + 
			"            \"name\": \"Drzava rodjenja\",\r\n" + 
			"            \"code\": \"STU_DRZRODJ\",\r\n" + 
			"            \"type\": \"string\",\r\n" + 
			"            \"maxString\": 3,\r\n" + 
			"            \"is_mandatory\":true\r\n" + 
			"  },\r\n" + 
			" \r\n" + 
			"       {\r\n" + 
			"            \"name\": \"Mesto rodjenja\",\r\n" + 
			"            \"code\": \"STU_MESTORODJ\",\r\n" + 
			"            \"type\": \"string\",\r\n" + 
			"            \"maxString\": 5,\r\n" + 
			"            \"is_mandatory\":true\r\n" + 
			"  },\r\n" + 
			" \r\n" + 
			"       {\r\n" + 
			"            \"name\": \"Drzava stan.\",\r\n" + 
			"            \"code\": \"STU_DRZSTAN\",\r\n" + 
			"            \"type\": \"string\",\r\n" + 
			"            \"maxString\": 3,\r\n" + 
			"            \"is_mandatory\":false\r\n" + 
			"  },\r\n" + 
			" \r\n" + 
			"       {\r\n" + 
			"            \"name\": \"Mesto stan.\",\r\n" + 
			"            \"code\": \"STU_MESTOSTAN\",\r\n" + 
			"            \"type\": \"string\",\r\n" + 
			"            \"maxString\": 5,\r\n" + 
			"            \"is_mandatory\":false\r\n" + 
			"  }\r\n" + 
			"\r\n" + 
			"],\r\n" + 
			"   \"key\": [ \"STU_INDEKS\"],\r\n" + 
			"    \"relations\": [\r\n" + 
			"\r\n" + 
			"  ]\r\n" + 
			"  \r\n" + 
			" } \r\n" + 
			"]"; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		JSON_Parser parser = new JSON_Parser();
		parser.parse(uspesan);
		
		tabele=parser.getTabeleNiz();
		paketi=parser.getPaketiNiz();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testiraj() {
		
		assertEquals(3, tabele.length); // tacno
		
		assertEquals(3, paketi.length); // pogresno

		
		Tabela tabela = tabele[0];
		assertEquals("Drzava", tabela.getTitle());//tacno
		
		Tabela tabela1 = tabele[tabele.length-1];
		assertEquals("Drzava", tabela1.getTitle());//netacno
		
	}


}
