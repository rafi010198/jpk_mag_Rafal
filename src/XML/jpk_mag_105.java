
package XML;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import XML.Parameters;

public class jpk_mag_105 extends Parameters{
	static Connection connection= WB.Connection2DB.dbConnector();

public static void main() throws SQLException, ParseException {
	
		        try {
		        	
		        	System.out.println(datastart);
		        	System.out.println(datastop);
		        	System.out.println(directoryname);
		        	
		    		// configure directory and files
		        	Parameters.createDirectory();
		        	File f = Parameters.createFile("jpk_MAG_105.xml");

					// create the xml data
		        	
System.out.println("create the xml data");
setinfo("create the xml for magazine 105");

		            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		            Document document = documentBuilder.newDocument();

		            // root element
	 
		            Element root = document.createElementNS("http://jpk.mf.gov.pl/wzor/2016/03/09/03093/","JPK");
		            root.setAttribute("xmlns:etd", "http://crd.gov.pl/xml/schematy/dziedzinowe/mf/2016/01/25/eD/DefinicjeTypy/");
		            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		            root.setAttribute("xsi:schemaLocation", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/ Schemat_JPK_MAG(1)_v1-0.xsd");
		            root.setAttribute("xmlns:tns", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/");
	 	            document.appendChild(root);
	  

	 
		           // document = naglowek(document,root,datastart,datastop,"2018-12-17T14:13:00");
		            document = naglowek(document,root,datastart,datastop,today()+"T"+time()+"Z");
		            document = podmiot(document, root);
		            document = magazyn(document, root);
		            document = PZ(document,root,datastart,datastop);
		            document = RW(document,root,datastart,datastop);

			       	 
				
	
		            //transform the DOM Object to an XML File
						
	 
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = transformerFactory.newTransformer();
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // to create everywhere a new line in the output
		            DOMSource domSource = new DOMSource(document);
		            
		           // StreamResult streamResult = new StreamResult(new File(xmlFilePath));
	
		            StreamResult streamResult = new StreamResult(f);

		            // If you use

		            // StreamResult result = new StreamResult(System.out);
	
		            // the output will be pushed to the standard output ...
	
		            // You can use that for debugging
	
		 
		            transformer.setOutputProperty("http://www.oracle.com/xml/is-standalone", "yes");
		            transformer.transform(domSource, streamResult);
	
		            System.out.println("Done creating XML File");
	
		        }
		        catch (ParserConfigurationException pce) {
		        	 seterror(pce.toString());
		            pce.printStackTrace();
	
		        } catch (TransformerException tfe) {
			        	if(tfe.toString().equals("javax.xml.transform.TransformerException: java.lang.NullPointerException"))
			        	{seterror(tfe.toString()+"\nValue has occurred null ");}
			        	else
			        	{seterror(tfe.toString());}
			        	
			            tfe.printStackTrace();
			        }
		        
		        setinfo("Done JPK for Magazine 105");	
		    }
		    
		    
private static Document naglowek(Document doc, Element root, String from, String till, String timestamp){
		    	   // Naglowek element
		   	 
	            Element Naglowek = doc.createElement("tns:Naglowek");
	            root.appendChild(Naglowek);
	 
			            // NAGLOWEK
	            		Element KodFormularza = doc.createElement("tns:KodFormularza");
	            		KodFormularza.setAttribute("kodSystemowy", "JPK_MAG (1)");
	            		KodFormularza.setAttribute("wersjaSchemy", "1-0");
	            		KodFormularza.appendChild(doc.createTextNode("JPK_MAG"));
		 	            Naglowek.appendChild(KodFormularza);
		 
			            Element WariantFormularza = doc.createElement("tns:WariantFormularza");
			            WariantFormularza.appendChild(doc.createTextNode("1"));
			            Naglowek.appendChild(WariantFormularza);
		 
			            Element CelZlozenia = doc.createElement("tns:CelZlozenia");
			            CelZlozenia.appendChild(doc.createTextNode("1"));
			            Naglowek.appendChild(CelZlozenia);
		 	            
			            Element DataWytworzeniaJPK = doc.createElement("tns:DataWytworzeniaJPK");
			            DataWytworzeniaJPK.appendChild(doc.createTextNode(timestamp));
			            Naglowek.appendChild(DataWytworzeniaJPK);
			            
			            Element DataOd = doc.createElement("tns:DataOd");
			            DataOd.appendChild(doc.createTextNode(from));
			            Naglowek.appendChild(DataOd);
			            
			            Element DataDo = doc.createElement("tns:DataDo");
			            DataDo.appendChild(doc.createTextNode(till));
			            Naglowek.appendChild(DataDo);
			            
			            Element DomyslnyKodWaluty = doc.createElement("tns:DomyslnyKodWaluty");
			            DomyslnyKodWaluty.appendChild(doc.createTextNode("PLN"));
			            Naglowek.appendChild(DomyslnyKodWaluty);
			            
			            Element KodUrzedu = doc.createElement("tns:KodUrzedu");
			            KodUrzedu.appendChild(doc.createTextNode("3023"));
			            Naglowek.appendChild(KodUrzedu);
			            
		      // Naglowek element
		    	
		    	return doc;
		    	
		    }
		   
		    
private static Document podmiot(Document doc, Element root ){
		    	Element Podmiot1 = doc.createElement("tns:Podmiot1");
			       root.appendChild(Podmiot1);	           
			            
			       		//  Podmiot1

			       		Element IdentyfikatorPodmiotu = doc.createElement("tns:IdentyfikatorPodmiotu");
			       		Podmiot1.appendChild(IdentyfikatorPodmiotu);
			       		
					       		Element etdNIP = doc.createElement("etd:NIP");
					       		etdNIP.appendChild(doc.createTextNode("8960000138"));
					       		IdentyfikatorPodmiotu.appendChild(etdNIP);
					       		
				       		
					       		Element etdPelnaNazwa = doc.createElement("etd:PelnaNazwa");
					       		etdPelnaNazwa.appendChild(doc.createTextNode("Fabryka Automatow Tokarskich"));
					       		IdentyfikatorPodmiotu.appendChild(etdPelnaNazwa);
					       		
					       		Element etdREGON = doc.createElement("etd:REGON");
					       		etdREGON.appendChild(doc.createTextNode("222222222"));
					       		IdentyfikatorPodmiotu.appendChild(etdREGON);
					       		
			       		
			       		Element AdresPodmiotu = doc.createElement("tns:AdresPodmiotu");
			       		Podmiot1.appendChild(AdresPodmiotu);
			       		
			       
					       		Element etdKodKraju = doc.createElement("etd:KodKraju");
					       		etdKodKraju.appendChild(doc.createTextNode("PL"));
					       		AdresPodmiotu.appendChild(etdKodKraju);
					       
					       		Element etdWojewodztwo = doc.createElement("etd:Wojewodztwo");
					       		etdWojewodztwo.appendChild(doc.createTextNode("DOLNASLASK"));
					       		AdresPodmiotu.appendChild(etdWojewodztwo);
					       
					       		Element etdPowiat = doc.createElement("etd:KodKraju");
					       		etdPowiat.appendChild(doc.createTextNode("WROCLAW"));
					       		AdresPodmiotu.appendChild(etdPowiat);
					       
					       		Element etdGmina = doc.createElement("etd:Gmina");
					       		etdGmina.appendChild(doc.createTextNode("WROCLAW"));
					       		AdresPodmiotu.appendChild(etdGmina);
					       					       		
					       		Element etdUlica = doc.createElement("etd:Ulica");
					       		etdUlica.appendChild(doc.createTextNode("GRABISZYNSKA"));
					       		AdresPodmiotu.appendChild(etdUlica);
					       		
					       		Element etdNrDomu = doc.createElement("etd:NrDomu");
					       		etdNrDomu.appendChild(doc.createTextNode("281"));
					       		AdresPodmiotu.appendChild(etdNrDomu);
					       		
//					       		Element etdNrLokalu = document.createElement("etd:NrLokalu");
//					       		etdNrLokalu.appendChild(document.createTextNode(""));
//					       		AdresPodmiotu.appendChild(etdNrLokalu);
					       		
					       		Element etdMiejscowosc = doc.createElement("etd:Miejscowosc");
					       		etdMiejscowosc.appendChild(doc.createTextNode("WROCLAW"));
					       		AdresPodmiotu.appendChild(etdMiejscowosc);
					       		
					       		Element etdKodPocztowy = doc.createElement("etd:KodPocztowy");
					       		etdKodPocztowy.appendChild(doc.createTextNode("53-234"));
					       		AdresPodmiotu.appendChild(etdKodPocztowy);
					       		
					       		Element etdPoczta = doc.createElement("etd:Poczta");
					       		etdPoczta.appendChild(doc.createTextNode("WROCLAW"));
					       		AdresPodmiotu.appendChild(etdPoczta);
		    	return doc;
		    	
		    }
		   
		    
private static Document magazyn(Document doc, Element root){
			   Element Magazyn = doc.createElement("tns:Magazyn");
				Magazyn.appendChild(doc.createTextNode("MAGAZYN MATERIAL")); //name magazin ????????????
				root.appendChild(Magazyn);	
			   return doc;
			   
		   }
	



/**
 * Generate the RW section for JPK_MAG
 * @author CUB4U
 *
 * @param doc 		Document, main doc name file
 * @param root  	Element the root element of the section
 * @param start 	start date in string version yyyy-mm-dd
 * @param stop 		stop date in string version yyyy-mm-dd
 * 
 * @return Document  RW section of the document
 */		    
private static Document RW(Document doc, Element root, String start , String stop) throws SQLException, ParseException{
		    	
		    	BigDecimal totalamountRW = BigDecimal.ZERO;
		    	int countRW = 0;   //counting the numbers of RW`s
		    	String rwNumber = null;
		    	int rememberRwNr = 0;
		    	Element RW = doc.createElement("tns:RW");
			       root.appendChild(RW);	           
			            
			       		//  Podmiot1

setinfo("create sql to RW from magazin 105");

			       		
	String sql1 = "select ORDERNUMMER as NR, SEQUENTIE, ARTIKELCODE,ARTIKELOMSCHRIJVING,BESTELD, LEVERINGSDATUMEFFECTIEF, LEVERINGSDATUMINGAVERECEPTIE, "
					+ "BESTELEENHEID,CFLEVNAAM,GELEVERD,AFDELING,MUNT,TOTAAL,EENHEIDSPRIJS, (select sum(TOTAAL) from storenotesdetail where s.ORDERNUMMER=ORDERNUMMER) as summ "
					+ "from storenotesdetail s "
					+ "where Leverancier = '105' "
					+ "and LEVERDATUM between '"+start+"' and '"+stop+"' "
					+ "and MONTAGE is not null  "
					+ "and MONTAGEOMSCHRIJVING is not null "
					+ "and MONTAGE = '500' "
					+ "and (AFDELING  = '500' or AFDELING='2' or AFDELING='14' or AFDELING='5' or AFDELING='3')";
			       		
			       		
 		System.out.println(sql1);
			    		Statement st1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    		ResultSet rs1 = st1.executeQuery(sql1);
			    	
setinfo("create sql2 to RW from magazin 105");	
			    		//sql to return from assembly
			    		String sql2="select r.BONNR as NR, r.VOLGNUMMER, r.SEQUENTIE,r.ARTIKELCODE, r.ARTIKELOMSCHRIJVING,r.CFRECEPTIEDATUM,r.CFEFFLEVERINGSDATUM, "
			    					+ "aa.VERSCHAFFINGSCODE,r.BESTELEENHEID,r.BESTELD,r.GELEVERD,ak.CFFIRMAMUNT,ak.CFKOSTPRIJS,ak.MATERIAAL,ak.SOORT,r.LEVERANCIER, "
			    					+ "(select sum(ak.MATERIAAL * r.GELEVERD) from receptiedetail r left join artikel_kostprijs ak on r.ARTIKELCODE = ak.ARTIKELCODE where r.BONNR=NR and "
			    					+ "(r.LEVERANCIER = '500' or r.LEVERANCIER = '9999999') and aa.VERSCHAFFINGSCODE='Y' and ak.SOORT='4' and r.CFRECEPTIEDATUM between '"+start+"' and '"+stop+"') as summ "
			    					+ "from receptiedetail r "
			    					+ "left join artikel_algemeen aa on r.ARTIKELCODE = aa.ARTIKELCODE "
			    					+ "left join artikel_kostprijs ak on r.ARTIKELCODE = ak.ARTIKELCODE "
			    					+ "where (r.LEVERANCIER = '500' or r.LEVERANCIER = '9999999') and aa.VERSCHAFFINGSCODE='Y' and ak.SOORT='4' and  r.CFRECEPTIEDATUM between '"+start+"' and '"+stop+"' "
			    					+ "order by r.BONNR,r.VOLGNUMMER";
			    		
			    			System.out.println(sql2);
			    			Statement st2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    			ResultSet rs2 = st2.executeQuery(sql2);
			    			
setinfo("create RWWartosc to xml");	
System.out.println("create RWWartosc to xml");
			    		while(rs1.next()){
			    			
			    			//this if return nr bonnr and articlecode when is null value
			    			if( rs1.getString("SEQUENTIE")==null || rs1.getString("ARTIKELCODE")==null ||  rs1.getString("ARTIKELOMSCHRIJVING")==null || 
			    					rs1.getString("BESTELD")==null || rs1.getString("BESTELEENHEID")==null ||
			    					rs1.getString("CFLEVNAAM")==null||rs1.getString("GELEVERD")==null || rs1.getString("EENHEIDSPRIJS")==null ||
			    					rs1.getString("MUNT")==null || rs1.getString("TOTAAL")==null )
			    			{seterror_nr("\n Error is in WZ in Magazine 102 in WZnr: "+rs1.getString("NR")+" , Artiklcode: "+rs1.getString("Artikelcode")+"    You can select:\n"
			    					+"select ORDERNUMMER as NR, SEQUENTIE, ARTIKELCODE,ARTIKELOMSCHRIJVING,BESTELD, LEVERINGSDATUMEFFECTIEF, LEVERINGSDATUMINGAVERECEPTIE, "
			    					+ "BESTELEENHEID,CFLEVNAAM,GELEVERD,AFDELING,MUNT,TOTAAL,EENHEIDSPRIJS, (select sum(TOTAAL) from storenotesdetail where s.ORDERNUMMER=ORDERNUMMER) as summ "
			    					+ "from storenotesdetail s "
			    					+ "where Leverancier = '105' "
			    					+ "and LEVERDATUM between '"+start+"' and '"+stop+"' "
			    					+ "and MONTAGE is not null  "
			    					+ "and MONTAGEOMSCHRIJVING is not null "
			    					+ "and MONTAGE = '500' "
			    					+ "and (AFDELING  = '500' or AFDELING='2' or AFDELING='14' or AFDELING='5' or AFDELING='3') and ORDERNUMMER='"+rs1.getInt("NR")+"' and ARTIKELCODE='"+rs1.getString("Artikelcode")+"' ");}
			    			
			    			
			    			if (rs1.getInt("NR") != rememberRwNr && rs1.getString("LEVERINGSDATUMEFFECTIEF")!=null){
			    					countRW++;
			    					rememberRwNr = rs1.getInt("NR");
			    					String datumRW = rs1.getString("LEVERINGSDATUMEFFECTIEF").substring(0,10); 
			    					Element rwWartosc = doc.createElement("RWWartosc");
			    					rwWartosc.setAttribute("xmlns", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/");
			    					RW.appendChild(rwWartosc);
					       		
			    							rwNumber = "RW " +rs1.getString("NR");
			    							System.out.println("Detected RW with Number: "+ rwNumber);
setinfo("create RWWartosc to xml      "+rwNumber);		
					    				Element NumerRW = doc.createElement("NumerRW");
							       		NumerRW.appendChild(doc.createTextNode(rwNumber));
					    				rwWartosc.appendChild(NumerRW);
					    										       							    						
							       		Element DataRW = doc.createElement("DataRW");
							       		DataRW.appendChild(doc.createTextNode(rs1.getString("LEVERINGSDATUMINGAVERECEPTIE").substring(0,4) +"-"+rs1.getString("LEVERINGSDATUMINGAVERECEPTIE").substring(5,7)+"-"+rs1.getString("LEVERINGSDATUMINGAVERECEPTIE").substring(8,10)));
							       		rwWartosc.appendChild(DataRW);
						       		
						       		
								       		// if munt <> PLN  then search currency exchange that day and convert the total
								       		String strCena = null;
								       	
						       		
								       		if(rs1.getString("MUNT").equals("PLN")){  
								       			strCena = rs1.getString("summ");
								       			
								       		}else{strCena = ConvertValutaToPLN(rs1.getString("MUNT"), rs1.getString("summ"), datumRW);
								       		
									       				
								       		}// end else if
								       		
								       		BigDecimal bdAmount = new BigDecimal(strCena);
								       		bdAmount = bdAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
								       		totalamountRW = totalamountRW.add(bdAmount) ;
								       		
							       		Element WartoscRW = doc.createElement("WartoscRW");
							       		WartoscRW.appendChild(doc.createTextNode(bdAmount.toString()));
							       		rwWartosc.appendChild(WartoscRW);
						       		
							       			//add totaal to final sumation of all RW
						       		
							       		Element DataWydaniaRW = doc.createElement("DataWydaniaRW");
							       		DataWydaniaRW.appendChild(doc.createTextNode(datumRW));
							       		rwWartosc.appendChild(DataWydaniaRW);
						       		
							       		Element SkadRW = doc.createElement("SkadRW");
							       		SkadRW.appendChild(doc.createTextNode(rs1.getString("CFLEVNAAM")));
							       		rwWartosc.appendChild(SkadRW);
							       		
							       		String recipient = rs1.getString("AFDELING");
							       		String dokad = null;
							       		if(recipient.equals("500"))
							       		{
							       			dokad="FAT Produkcja";
							       		}
							       		else if(recipient.equals("2") || recipient.equals("14") || recipient.equals("5") || recipient.equals("3"))
							       		{
							       			dokad="Monta¿";
							       		}
							       		Element DokadRW = doc.createElement("DokadRW");
							       		DokadRW.appendChild(doc.createTextNode(dokad));
							       		rwWartosc.appendChild(DokadRW);
						       		
							       		//optional childs if needed to be add
							       		
//							       		Element NumerFaRW = doc.createElement("NumerFaRW");
//							       		NumerFaRW.appendChild(doc.createTextNode("8960000138"));
//							       		rwWartosc.appendChild(NumerFaRW);
//						       		
//							       		Element DataFaRW = doc.createElement("DataFaRW");
//							       		DataFaRW.appendChild(doc.createTextNode("8960000138"));
//							       		rwWartosc.appendChild(DataFaRW);
							       		
				    			
			    			} //ENDIF for RWWartosz
			    		} //END WHILE
			    		
			    		rs1.beforeFirst();
			    		
while(rs2.next()){		//while to RWWartosc from sql2
			    			
			    			//this if return nr bonnr and articlecode when is null value
			    			if( rs2.getString("VOLGNUMMER")==null || rs2.getString("SEQUENTIE")==null ||  rs2.getString("ARTIKELCODE")==null || 
			    					rs2.getString("ARTIKELOMSCHRIJVING")==null || rs2.getString("CFRECEPTIEDATUM")==null || rs2.getString("CFEFFLEVERINGSDATUM")==null ||
			    					rs2.getString("VERSCHAFFINGSCODE")==null||rs2.getString("BESTELEENHEID")==null || rs2.getString("BESTELD")==null || 
			    					rs2.getString("GELEVERD")==null || rs2.getString("CFFIRMAMUNT")==null ||  rs2.getString("MATERIAAL")==null ||  rs2.getString("summ")==null)			    					
			    			{seterror_nr("\n Error is in RW in Magazine 102 in RWnr: "+rs2.getString("NR")+" , Artiklcode: "+rs2.getString("Artikelcode")+"    You can select:\n"
			    							+"select r.BONNR as NR, r.VOLGNUMMER, r.SEQUENTIE,r.ARTIKELCODE, r.ARTIKELOMSCHRIJVING,r.CFRECEPTIEDATUM,r.CFEFFLEVERINGSDATUM, "
					    					+ "aa.VERSCHAFFINGSCODE,r.BESTELEENHEID,r.BESTELD,r.GELEVERD,ak.CFFIRMAMUNT,ak.CFKOSTPRIJS,ak.MATERIAAL,ak.SOORT,r.LEVERANCIER, "
					    					+ "(select sum(ak.MATERIAAL * r.GELEVERD) from receptiedetail r left join artikel_kostprijs ak on r.ARTIKELCODE = ak.ARTIKELCODE where r.BONNR=NR and "
					    					+ "(r.LEVERANCIER = '500' or r.LEVERANCIER = '9999999') and aa.VERSCHAFFINGSCODE='Y' and ak.SOORT='4' and r.CFRECEPTIEDATUM between '"+start+"' and '"+stop+"') as summ "
					    					+ "from receptiedetail r "
					    					+ "left join artikel_algemeen aa on r.ARTIKELCODE = aa.ARTIKELCODE "
					    					+ "left join artikel_kostprijs ak on r.ARTIKELCODE = ak.ARTIKELCODE "
					    					+ "where (r.LEVERANCIER = '500' or r.LEVERANCIER = '9999999') and aa.VERSCHAFFINGSCODE='Y' and ak.SOORT='4' and  r.CFRECEPTIEDATUM between '"+start+"' and '"+stop+"' "
			    							+ "order by r.BONNR,r.VOLGNUMMER");}
			    			
			    			
			    			if (rs2.getInt("NR") != rememberRwNr){
			    					countRW++;
			    					rememberRwNr = rs2.getInt("NR");
			    					String datumRW = rs2.getString("CFRECEPTIEDATUM").substring(0,10); // data fakturyzacji 
			    					Element rwWartosc = doc.createElement("RWWartosc");
			    					rwWartosc.setAttribute("xmlns", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/");
			    					RW.appendChild(rwWartosc);
					       		
			    							rwNumber = "RW " +rs2.getString("NR");
			    							System.out.println("Detected RW with Number: "+ rwNumber);
setinfo("create RWWartosc to xml      "+rwNumber);		
					    				Element NumerRW = doc.createElement("NumerRW");
							       		NumerRW.appendChild(doc.createTextNode(rwNumber));
					    				rwWartosc.appendChild(NumerRW);
					    										       							    						
							       		Element DataRW = doc.createElement("DataRW");		//data dostarczenia
							       		DataRW.appendChild(doc.createTextNode(rs2.getString("CFEFFLEVERINGSDATUM").substring(0,4) +"-"+rs2.getString("CFEFFLEVERINGSDATUM").substring(5,7)+"-"+rs2.getString("CFEFFLEVERINGSDATUM").substring(8,10)));
							       		rwWartosc.appendChild(DataRW);
						       		
								       		// if munt <> PLN  then search currency exchange that day and convert the total
								       		String strCena = null;
								       	
								       		if(rs2.getString("CFFIRMAMUNT").equals("PLN")){  
								       			strCena = rs2.getString("summ");
								       			
								       		}else{strCena = ConvertValutaToPLN(rs2.getString("CFFIRMAMUNT"), rs2.getString("summ"), datumRW);
								       					
								       		}// end else if
								       		
								       		BigDecimal bdAmount = new BigDecimal(strCena);
								       		bdAmount = bdAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
								       		totalamountRW = totalamountRW.add(bdAmount) ;
								       		
							       		Element WartoscRW = doc.createElement("WartoscRW");
							       		WartoscRW.appendChild(doc.createTextNode(new BigDecimal(strCena).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
							       		rwWartosc.appendChild(WartoscRW);
						       		
							       			//add totaal to final sumation of all RW
						       		
							       		Element DataWydaniaRW = doc.createElement("DataWydaniaRW");
							       		DataWydaniaRW.appendChild(doc.createTextNode(datumRW));
							       		rwWartosc.appendChild(DataWydaniaRW);
						       		
							       		String levnam = rs2.getString("LEVERANCIER");
							       		String dokad = null;
							       		if(levnam.equals("500"))  // LEVERANCIER = '500' - "FAT Produkcja"
							       		{
							       			dokad="FAT Produkcja";
							       		}
							       		else if(levnam.equals("9999999")) // LEVERANCIER = '9999999' - "Monta¿"
							       		{
							       			dokad="Monta¿";
							       		}
							       		
							       		Element SkadRW = doc.createElement("SkadRW");
							       		SkadRW.appendChild(doc.createTextNode(dokad)); 
							       		rwWartosc.appendChild(SkadRW);
							       		
							       		Element DokadRW = doc.createElement("DokadRW");
							       		DokadRW.appendChild(doc.createTextNode(/*rs2.getString()*/"MAGAZYN MATERIAL"));  //VERSCHAFFINGSCODE='Y' - mag 105
							       		rwWartosc.appendChild(DokadRW);
						       		
							       		//optional childs if needed to be add
							       		
//							       		Element NumerFaRW = doc.createElement("NumerFaRW");
//							       		NumerFaRW.appendChild(doc.createTextNode("8960000138"));
//							       		rwWartosc.appendChild(NumerFaRW);
//						       		
//							       		Element DataFaRW = doc.createElement("DataFaRW");
//							       		DataFaRW.appendChild(doc.createTextNode("8960000138"));
//							       		rwWartosc.appendChild(DataFaRW);
							       			
			    			} //ENDIF for RWWartosz
			    		} //END WHILE to RWWartosc from sql2
			    		
			    		rs2.beforeFirst();
			    		
			    		// RWWIERSZ
setinfo("create RWWiersz to xml");	
			    		while(rs1.next()){
			    			if(rs1.getString("LEVERINGSDATUMEFFECTIEF")!=null)
			    			{
			    			
			    			String datumRW = rs1.getString("LEVERINGSDATUMEFFECTIEF");
			    			
			    			Element RWWiersz = doc.createElement("RWWiersz");
			    			RWWiersz.setAttribute("xmlns", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/");
				       		RW.appendChild(RWWiersz);
				       				
				       				rwNumber = "RW " +rs1.getString("NR")+"/"+rs1.getString("SEQUENTIE");
				       				
					       		Element Numer2RW = doc.createElement("Numer2RW");
					       		Numer2RW.appendChild(doc.createTextNode(rwNumber));
					       		RWWiersz.appendChild(Numer2RW);
					       		
					       		Element KodTowaruRW = doc.createElement("KodTowaruRW");
					       		KodTowaruRW.appendChild(doc.createTextNode(rs1.getString("ARTIKELCODE")));
					       		RWWiersz.appendChild(KodTowaruRW);
					       		
					       		
					       		
					       		String StrNazwaTowaruRW = rs1.getString("ARTIKELOMSCHRIJVING"); //String StrNazwaTowaruRW = rs1.getString("Artikelomschrijving");
				//					if (rs1.getString("Artikelcode").equals("M") )	{
				//						String StrTekst = rs1.getString("Tekst");
				//						System.out.println("for bonnummer "+ rwNumber + " we have following articledescription: "+ StrTekst);
				//						StrNazwaTowaruRW=StrTekst;
				//					}
											
					       		
					       		Element NazwaTowaruRW = doc.createElement("NazwaTowaruRW");
					       		NazwaTowaruRW.appendChild(doc.createTextNode(StrNazwaTowaruRW));
					       		RWWiersz.appendChild(NazwaTowaruRW);
					       		
					       		Element IloscWydanaRW = doc.createElement("IloscWydanaRW");
					       		IloscWydanaRW.appendChild(doc.createTextNode(rs1.getString("GELEVERD"))); //IloscWydanaRW.appendChild(doc.createTextNode(rs1.getString("Geleverd")));
					       		RWWiersz.appendChild(IloscWydanaRW);
					       		
					       		Element JednostkaMiaryRW = doc.createElement("JednostkaMiaryRW");
					       		JednostkaMiaryRW.appendChild(doc.createTextNode(rs1.getString("BESTELEENHEID"))); //JednostkaMiaryRW.appendChild(doc.createTextNode(rs1.getString("besteleenheid")));
					       		RWWiersz.appendChild(JednostkaMiaryRW);
					       		
						       		// if munt <> PLN  then search currency exchange that day and convert the total
						 		String strCena = null;
										       		
						       		if(rs1.getString("MUNT").equals("PLN")){
						       			strCena = rs1.getString("EENHEIDSPRIJS");
						       		}else{
						       			strCena = ConvertValutaToPLN(rs1.getString("MUNT"), rs1.getString("EENHEIDSPRIJS"), datumRW);
						       		}// end else if
					       		
					       		Element CenaJednRW = doc.createElement("CenaJednRW");
					       		CenaJednRW.appendChild(doc.createTextNode(strCena));
					       		RWWiersz.appendChild(CenaJednRW);
					       		
						       		BigDecimal bdprice = new BigDecimal(strCena);
						       				bdprice = bdprice.setScale(2,BigDecimal.ROUND_UP);
						       		BigDecimal bdqty = new BigDecimal(rs1.getString("GELEVERD"));
						       		BigDecimal total = bdprice.multiply(bdqty);
						       				total = total.setScale(2,BigDecimal.ROUND_UP);
					       		
					       		Element WartoscPozycjiRW = doc.createElement("WartoscPozycjiRW");
					       		WartoscPozycjiRW.appendChild(doc.createTextNode(total.toString()));
					       		RWWiersz.appendChild(WartoscPozycjiRW);
					       		
				    			
			    			
			    			
			    			
			    			
			    		}
			    			
			    			
			    		} //END WHILE
			    		
while(rs2.next()){			//WHILE to RWWIERSZ from sql2
			    			
			    			
			    			
			    			String datumRW = rs2.getString("CFRECEPTIEDATUM");
			    			
			    			Element RWWiersz = doc.createElement("RWWiersz");
			    			RWWiersz.setAttribute("xmlns", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/");
				       		RW.appendChild(RWWiersz);
				       				
				       				rwNumber = "RW " +rs2.getString("NR")+"/"+rs2.getString("VOLGNUMMER");
				       				
					       		Element Numer2RW = doc.createElement("Numer2RW");
					       		Numer2RW.appendChild(doc.createTextNode(rwNumber));
					       		RWWiersz.appendChild(Numer2RW);
					       		
					       		Element KodTowaruRW = doc.createElement("KodTowaruRW");
					       		KodTowaruRW.appendChild(doc.createTextNode(rs2.getString("ARTIKELCODE"))); 
					       		RWWiersz.appendChild(KodTowaruRW);
					       		
					       		
					       		
					       		String StrNazwaTowaruRW = rs2.getString("ARTIKELOMSCHRIJVING"); 
				//					if (rs2.getString("Artikelcode").equals("M") )	{
				//						String StrTekst = rs2.getString("Tekst");
				//						System.out.println("for bonnummer "+ rwNumber + " we have following articledescription: "+ StrTekst);
				//						StrNazwaTowaruRW=StrTekst;
				//					}
											
					       		
					       		Element NazwaTowaruRW = doc.createElement("NazwaTowaruRW");
					       		NazwaTowaruRW.appendChild(doc.createTextNode(StrNazwaTowaruRW));
					       		RWWiersz.appendChild(NazwaTowaruRW);
					       		
					       		Element IloscWydanaRW = doc.createElement("IloscWydanaRW");
					       		IloscWydanaRW.appendChild(doc.createTextNode(rs2.getString("GELEVERD"))); 
					       		RWWiersz.appendChild(IloscWydanaRW);
					       		
					       		Element JednostkaMiaryRW = doc.createElement("JednostkaMiaryRW");
					       		JednostkaMiaryRW.appendChild(doc.createTextNode(rs2.getString("BESTELEENHEID"))); 
					       		RWWiersz.appendChild(JednostkaMiaryRW);
					       		
						       		// if munt <> PLN  then search currency exchange that day and convert the total
						 		String strCena = null;
										       		
						       		if(rs2.getString("CFFIRMAMUNT").equals("PLN")){
						       			strCena = rs2.getString("MATERIAAL");
						       		}else{
						       			strCena = ConvertValutaToPLN(rs2.getString("CFFIRMAMUNT"), rs2.getString("MATERIAAL"), datumRW);
						       		}// end else if
					       		
					       		Element CenaJednRW = doc.createElement("CenaJednRW");
					       		CenaJednRW.appendChild(doc.createTextNode(new BigDecimal(strCena).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
					       		RWWiersz.appendChild(CenaJednRW);
					       		
						       		BigDecimal bdprice = new BigDecimal(strCena);
						       				bdprice = bdprice.setScale(2,BigDecimal.ROUND_UP);
						       		BigDecimal bdqty = new BigDecimal(rs2.getString("GELEVERD"));
						       		BigDecimal total = bdprice.multiply(bdqty);
						       				total = total.setScale(2,BigDecimal.ROUND_UP);
					       		
					       		Element WartoscPozycjiRW = doc.createElement("WartoscPozycjiRW");
					       		WartoscPozycjiRW.appendChild(doc.createTextNode(total.toString()));
					       		RWWiersz.appendChild(WartoscPozycjiRW);
						}//END WHILE to RWWIERSZ from sql2
			    		
			    		Element RWCtrl = doc.createElement("RWCtrl");
			       		RW.appendChild(RWCtrl);
			    		
			       		Element LiczbaRW = doc.createElement("LiczbaRW");
			       		LiczbaRW.appendChild(doc.createTextNode(String.valueOf(countRW)));
			       		RWCtrl.appendChild(LiczbaRW);
			       		
			       		
			       		totalamountRW = totalamountRW.setScale(2,BigDecimal.ROUND_UP);
			       		Element SumaRW = doc.createElement("SumaRW");
			       		SumaRW.appendChild(doc.createTextNode(totalamountRW.toString()));
			       		RWCtrl.appendChild(SumaRW);

			    		st1.close();
			    		rs1.close();
			       		
			    		st2.close();
			    		rs2.close();	
	
					       		return doc;
			    		 				       		
		    }



/**
 * Generate the PZ section for JPK_MAG
 * @author CUB4U
 *
 * @param doc 		Document, main doc name file
 * @param root  	Element the root element of the section
 * @param start 	start date in string version yyyy-mm-dd
 * @param stop 		stop date in string version yyyy-mm-dd
 * 
 * @return Document  PZ section of the document + main doc 
 */		    
private static Document PZ(Document doc, Element root, String start , String stop) throws SQLException, ParseException{
		    	
		    	BigDecimal totalamountPZ = BigDecimal.ZERO;
		    	int countPZ = 0;   //counting the numbers of WZ`s
		    	
		    	int oldPzNr = 0;
		    	Element PZ = doc.createElement("tns:PZ");
			    	root.appendChild(PZ);	           
			            
			       		//  Podmiot1
setinfo("create sql to PZ from magazin 105");	
System.out.println("create sql to PZ from magazin 105");	
      		
						String sql1 ="select  re.bonnr,re.SEQUENTIE, re.volgnummer, b.leverancier,aa.LEVNAAM,b.ORDERNUMMER,b.ARTIKELCODE,b.ARTIKELOMSCHRIJVING, "
								+"b.BESTELEENHEID,b.GELEVERD, b.MUNT,b.CFSTOCK,b.LEVERINGSDATUMINGAVERECEPTIE,b.FACTURATIEDATUM, b.eenheidsprijs, "
								+"a.VERSCHAFFINGSCODE "
								+ "from bestellingdetail b "
								+ "left join artikel_algemeen a "
								+ "on b.ARTIKELCODE = a.ARTIKELCODE "
								+ "left join artikel_aankoop aa "
								+ "on b.ARTIKELCODE = aa.ARTIKELCODE "
								+ "left join receptiedetail re "
								+ "on b.ARTIKELCODE = re.artikelcode and b.ORDERNUMMER=re.ORDERNUMMER "
								+ "where "
								+ "b.LEVERINGSDATUMINGAVERECEPTIE between '"+start+"' and '"+stop+"' "
								+ "and aa.LEVMANPLANNING = '1' # dostawca glowny -  tymczaosowo bo to zalezne jest od daty "
								+ "order by re.BONNR, re.VOLGNUMMER ";

			    		Statement st1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			    		ResultSet rs1 = st1.executeQuery(sql1);
setinfo("Create PZWartosc to xml");	
			    		while(rs1.next()){
			    			
			    			//this if return nr bonnr and articlecode when is null value
			    			if(rs1.getString("volgnummer")==null || rs1.getString("leverancier")==null || rs1.getString("LEVNAAM")==null  || rs1.getString("ordernummer")==null ||
			    					rs1.getString("LEVERINGSDATUMINGAVERECEPTIE")==null ||
			    					rs1.getString("ARTIKELCODE")==null || rs1.getString("artikelomschrijving")==null || rs1.getString("geleverd")==null || 
			    					rs1.getString("besteleenheid")==null )   		// 		
			    			{seterror_nr("\n Error is in PZ in Magazine MAIN in Bonnr: "+rs1.getString("bonnr")+" , Artiklcode: "+rs1.getString("ARTIKELCODE")+"   You can select:\n"
			    				+"select  re.bonnr, re.volgnummer, b.leverancier,aa.LEVNAAM,b.ORDERNUMMER,b.ARTIKELCODE,b.ARTIKELOMSCHRIJVING, "
								+"b.BESTELEENHEID,b.GELEVERD, b.MUNT,b.CFSTOCK,b.LEVERINGSDATUMINGAVERECEPTIE,b.FACTURATIEDATUM, b.eenheidsprijs, "
								+"a.VERSCHAFFINGSCODE "
								+ "from bestellingdetail b "
								+ "left join artikel_algemeen a "
								+ "on b.ARTIKELCODE = a.ARTIKELCODE "
								+ "left join artikel_aankoop aa "
								+ "on b.ARTIKELCODE = aa.ARTIKELCODE "
								+ "left join receptiedetail re "
								+ "on b.ARTIKELCODE = re.artikelcode and b.ORDERNUMMER=re.ORDERNUMMER "
								+ "where re.bonnr='"+rs1.getString("bonnr")+"' and b.ARTIKELCODE='"+rs1.getString("ARTIKELCODE")+"'");}
			    	
			    			int bonnr = rs1.getInt("bonnr");
			    			String code = rs1.getString("VERSCHAFFINGSCODE");
			    			if(rs1.getString("bonnr")!=null && code!=null && rs1.getString("LEVNAAM")!=null && rs1.getString("leverancier").length()==6 && 
			    					rs1.getString("leverancier").equals("119003")==false && rs1.getString("LEVNAAM").equals("FAT produkcja")==false)
			    			{
setinfo("Create PZWartosc to xml      "+bonnr);	
			    			if (bonnr != oldPzNr && countPZ > 0 && code.equals("Y") && rs1.getString("FACTURATIEDATUM")!=null){
			    				 
			    				    System.out.println("ADD 1 PZWartosc: "+pzNumber+" | " + pzDatum+" | "+pzAmount+" | "+pzLeveringsdatum + " | " + pzLeverancier  );
			    					doc = PZWartosc(doc, PZ, pzNumber, pzDatum, pzAmount, pzLeveringsdatum, pzLeverancier);
			    				
			    				
			    			} //ENDIF writing a PZ WARTOSC BLOCK
			    			
			    			if (bonnr == oldPzNr    && code.equals("Y") && rs1.getString("FACTURATIEDATUM")!=null){
			    				
			    				// use this block to cummulate the prices of every single items of the PZ
			    				
			    				pzDatum = rs1.getString("FACTURATIEDATUM");
			    				String unitprice = rs1.getString("eenheidsprijs");
			    				String valuta = rs1.getString("munt");
			    				String quantity = rs1.getString("GELEVERD");
			    				
			    				pzAmount = cumulInitPlusPriceTimeQty(pzAmount, unitprice, quantity, valuta, pzDatum);
			    				String rowtotal = cumulInitPlusPriceTimeQty("0", unitprice, quantity, valuta, pzDatum);
			    				
			    				totalamountPZ = totalamountPZ.add(new BigDecimal(rowtotal)); //cumul all PZ values
			    				
			    			} //ENDIF add amount of next item of PZ
			    			
			    			if (bonnr != oldPzNr && code.equals("Y") && rs1.getString("FACTURATIEDATUM")!=null){
			    				
			    				//new block pz Wartosc found	
			    				//collect data and put it in the right parameters
			    							    				
			    				countPZ++;
			    				System.out.println(countPZ);
			    				oldPzNr = rs1.getInt("bonnr");
			    				pzNumber = rs1.getString("bonnr");
			    				pzDatum = rs1.getString("FACTURATIEDATUM");
			    				String unitprice = rs1.getString("eenheidsprijs");
			    				String valuta = rs1.getString("munt");
			    				String quantity = rs1.getString("geleverd");
			    				
			    				
			    				
			    				System.out.println("cumul "+ unitprice +" | "+ quantity +" | "+  valuta  +" | "+ pzDatum);
			    				pzAmount = cumulInitPlusPriceTimeQty("0", unitprice, quantity, valuta, pzDatum);
			    				pzLeveringsdatum = rs1.getString("LEVERINGSDATUMINGAVERECEPTIE");
			    				pzLeverancier = rs1.getString("LEVNAAM");
			    				
			    				totalamountPZ = totalamountPZ.add(new BigDecimal(pzAmount));  //cumul all PZ values
			    				
			    				
			    			} //ENDIF preparing data
			   
			    		}
			    			
			    		} //END WHILE
			    		
			    		//write down the last section of PZ
		    			System.out.println("ADD 2 PZWartosc: "+pzNumber+" | " + pzDatum+" | "+pzAmount+" | "+pzLeveringsdatum + " | " + pzLeverancier  );
		    			doc = PZWartosc(doc, PZ, pzNumber, pzDatum, pzAmount, pzLeveringsdatum, pzLeverancier);
		    			totalamountPZ = totalamountPZ.setScale(2,BigDecimal.ROUND_UP);
			    		rs1.beforeFirst();
			    		
			    		// WZWIERSZ
			    		while(rs1.next()){
			    			if(rs1.getString("bonnr")!=null && rs1.getString("VERSCHAFFINGSCODE")!=null && rs1.getString("LEVNAAM")!=null && rs1.getString("leverancier").length()==6)
			    			{
			    			if(rs1.getString("VERSCHAFFINGSCODE").equals("Y") && rs1.getString("leverancier").equals("119003")==false &&  rs1.getString("FACTURATIEDATUM")!=null && rs1.getString("LEVNAAM").equals("FAT produkcja")==false)
			    			{
					    			String articlecode = rs1.getString("ARTIKELCODE");
					    			String description = rs1.getString("artikelomschrijving");
					    			String quantity = rs1.getString("geleverd");
					    			String unit = rs1.getString("besteleenheid");
					    			String unitprice = rs1.getString("eenheidsprijs");
					    			String total = null;
					    			String valuta = rs1.getString("munt");
					    			pzDatum = rs1.getString("FACTURATIEDATUM");
					    			pzNumber = rs1.getString("bonnr");
					    			String volgnummer = rs1.getString("SEQUENTIE");		//choice  SEQUENTIE, because SEQUENTIE is number facture 
					    				
					    			System.out.println("cumul proc: " + pzNumber + " |  " + unitprice + " |  " +valuta + " | " + pzDatum + " | " );
					    			if (unitprice == null){
					    				unitprice="0";
					    			 	System.out.println("ERROR proc: " + pzNumber + " |  " + unitprice + " |  " +valuta + " | " + pzDatum + " | " );
					    			}
					    			if (valuta == null){
					    				valuta="PLN";
					    			 	System.out.println("ERROR proc: " + pzNumber + " |  " + unitprice + " |  " +valuta + " | " + pzDatum + " | " );
					    			}
					    			unitprice = cumulInitPlusPriceTimeQty("0", unitprice, "1", valuta, pzDatum);
					    			total = cumulInitPlusPriceTimeQty("0", unitprice, quantity, valuta, pzDatum);
					    			
					    			System.out.println("ADD PZWIERZ: "+pzNumber+" | " + articlecode+" | "+description+" | "+quantity + " | " + unit + " | " +unitprice + " | " + total);
setinfo("Create PZWiersz to xml       "+pzNumber);						    			 
					    			doc = PZWiersz(doc, PZ, pzNumber+"/"+volgnummer, articlecode, description, quantity, unit, unitprice, total);
			    			}
			    			}
			    		
			    		} //END WHILE
			    		
			    		
			    		Element PZCtrl = doc.createElement("PZCtrl");
			       		PZ.appendChild(PZCtrl);
			    		
			       		Element LiczbaPZ = doc.createElement("LiczbaPZ");
			       		LiczbaPZ.appendChild(doc.createTextNode( String.valueOf(countPZ)));
			       		PZCtrl.appendChild(LiczbaPZ);
			       		
			       		
			       		Element SumaPZ = doc.createElement("SumaWZ");
			       		SumaPZ.appendChild(doc.createTextNode(totalamountPZ.toString()));
			       		PZCtrl.appendChild(SumaPZ);
			    		
			    		
			    		
			    		
			    		st1.close();
			    		rs1.close();
			       		
					       		
					       		
					       		
					    return doc;
					       		
		    }



/**
 * add to the document the section of PZWartosc
 * @author CUB4U
 *
 * @param doc 		Document where it need to be added
 * @param root  	Element to add the element as child to
 * @param pzNumber 	String   with Pz number
 * @param pzdatum 	String   when Pz dokument was made yyyy-mm-dd
 * @param amount 	String   total amount of Pz dokument
 * @param leveringsdatum 	String  date when good arrived yyyy-mm-dd
 * @param leverancier 	String  name of supplier
 *
 * @return part of document
 */
private static Document PZWartosc(Document doc, Element root, String pzNumber , String pzdatum, String amount, String leveringsdatum , String leverancier){
	
	Element pzWartosc = doc.createElement("PZWartosc");
	pzWartosc.setAttribute("xmlns", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/");
	root.appendChild(pzWartosc);
	
		Element NumerPZ = doc.createElement("NumerPZ");
		NumerPZ.appendChild(doc.createTextNode(pzNumber));
		pzWartosc.appendChild(NumerPZ);
		
		Element DataPZ = doc.createElement("DataPZ");
   		DataPZ.appendChild(doc.createTextNode(pzdatum));
   		pzWartosc.appendChild(DataPZ);
   		
   		Element WartoscPZ = doc.createElement("WartoscPZ");
   		WartoscPZ.appendChild(doc.createTextNode(amount));
   		pzWartosc.appendChild(WartoscPZ);
		
   		Element DataOtrzymaniaPZ = doc.createElement("DataOtrzymaniaPZ");
   		DataOtrzymaniaPZ.appendChild(doc.createTextNode(leveringsdatum));
   		pzWartosc.appendChild(DataOtrzymaniaPZ);
		
   		Element Dostawca = doc.createElement("Dostawca");
   		Dostawca.appendChild(doc.createTextNode(leverancier));
   		pzWartosc.appendChild(Dostawca);
		
   		//optional childs if needed to be add
   		
//   		Element NumerFaWZ = doc.createElement("NumerFaWZ");
//   		NumerFaWZ.appendChild(doc.createTextNode("8960000138"));
//   		wzWartosc.appendChild(NumerFaWZ);
//		
//   		Element DataFaWZ = doc.createElement("DataFaWZ");
//   		DataFaWZ.appendChild(doc.createTextNode("8960000138"));
//   		wzWartosc.appendChild(DataFaWZ);
	
	
	return doc;
	
}


/**
 * add to the document the section of PZWiersz
 * @author CUB4U
 *
 * @param doc 			Document where it need to be added
 * @param root  		Element to add the element as child to
 * @param pzNumber 		String   with Pz number
 * @param articlecode 	String   with articlecode
 * @param description 	String   description of article
 * @param qty 			String  qty
 * @param unit 			String  unit used szt kg etc...
 * @param unitprice 	String  unitprice
 * @param total 		String  total amount of this line
 *
 * @return part of document
 */
private static Document PZWiersz(Document doc, Element root, String PZnumber , String articlecode, String description, String qty , String unit ,String unitprice , String total){
setinfo("Create PZWiersz to xml");	
	Element PZWiersz = doc.createElement("PZWiersz");
	PZWiersz.setAttribute("xmlns", "http://jpk.mf.gov.pl/wzor/2016/03/09/03093/");
	root.appendChild(PZWiersz);
	
		Element Numer2PZ = doc.createElement("Numer2PZ");
		Numer2PZ.appendChild(doc.createTextNode(PZnumber));
		PZWiersz.appendChild(Numer2PZ);
		
		Element KodTowaruPZ = doc.createElement("KodTowaruPZ");
		KodTowaruPZ.appendChild(doc.createTextNode(articlecode));
		PZWiersz.appendChild(KodTowaruPZ);
   		
   		Element NazwaTowaruPZ = doc.createElement("NazwaTowaruPZ");
   		NazwaTowaruPZ.appendChild(doc.createTextNode(description));
   		PZWiersz.appendChild(NazwaTowaruPZ);
		
   		Element IloscPrzyjetaPZ = doc.createElement("IloscPrzyjetaPZ");
   		IloscPrzyjetaPZ.appendChild(doc.createTextNode(qty));
   		PZWiersz.appendChild(IloscPrzyjetaPZ);
		
   		Element JednostkaMiaryPZ = doc.createElement("JednostkaMiaryPZ");
   		JednostkaMiaryPZ.appendChild(doc.createTextNode(unit));
   		PZWiersz.appendChild(JednostkaMiaryPZ);
		
   		Element CenaJednPZ = doc.createElement("CenaJednPZ");
   		CenaJednPZ.appendChild(doc.createTextNode(unitprice));
   		PZWiersz.appendChild(CenaJednPZ);
   		
   		Element WartoscPozycjiPZ = doc.createElement("WartoscPozycjiPZ");
   		WartoscPozycjiPZ.appendChild(doc.createTextNode(total));
   		PZWiersz.appendChild(WartoscPozycjiPZ);
   		

	
	return doc;}
	

/**
 * Convert other Valuta than PLN to PLN, if no exchange data available, procedure check previous day till valid data is found
 * @author CUB4U
 *
 * @param valuta 	String of other than PLN valuta
 * @param amount  	String the value to be converted
 * @param datum 	String day of convertion
 
 * 
 * @return string converted value 
 */
private static String ConvertValutaToPLN(String valuta, String amount, String datum) throws SQLException, ParseException{
	
	BigDecimal 	bdAmount = new BigDecimal(amount);
	String 		exchange = null;
	String 		result = null;
	
		
		do{
				String sql1 = "select tarief from dagkoersen where munt = '"+ valuta +"' and MUNT_STANDAARD = 'pln' and DATUM = '"+ datum +"'";
				Statement st1 = connection.createStatement();
				ResultSet rs1 = st1.executeQuery(sql1);
			
				while(rs1.next()){	exchange = rs1.getString("tarief");	}
				
				//System.out.println("searching on date: " + datum);
				Date date = yyyymmdd. parse(datum);
				//System.out.println("searching on date: " + date.toString());
				Calendar cal = Calendar. getInstance();
				cal.setTime(date);
				//System.out.println("On: "+datum+" value: "+amount+"  exchange rate:  " + exchange + " " + cal.getTime());
				cal.add(Calendar.DATE, -1);
				datum = yyyymmdd.format(cal.getTime());
				
				
				
		}while (exchange == null);  //end do
	
	
		BigDecimal bdexchange =  new BigDecimal(exchange);
		bdAmount = bdAmount.multiply(bdexchange);
		bdAmount = bdAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		
		
		return bdAmount.toString();
	
}


/**
* FORMULA   TOTAL = INIT + UNITPRICE * QTY
* and convert if necessary to PLN
* @author CUB4U
*
* @param init 		String value of initial cumulation
* @param unitprice  String value for unitprice
* @param qty 		String value for the quantity
* @param valuta 	String with actual currency
* @param Datum 		String date used for convertion
* 
* @return string converted value 
*/
private static String cumulInitPlusPriceTimeQty(String init, String unitprice, String qty, String valuta, String Datum) throws SQLException, ParseException{
	
			//formula TOTAL = INIT + UNITPRICE * QTY
			//convert Unitprice to PLN according acutaul exchange rate
		// if unitprice is null, return zero
		if (unitprice == null){
			System.out.println("ERROR proc change unitprice = null" );
			return "0";
		}
		if (valuta == null){
			valuta="PLN";
		 	System.out.println("ERROR proc change valuta = null to: pln" );
		}
		
	
		// if unitprice is equal zero, skip the procedure and return zero
		if(unitprice.equals("0")){return "0";}
	
	
		//System.out.println("cumul proc: " + init + " | " + unitprice + " | " +qty + " | " +valuta + " | " +Datum + " | " );
	
		// if munt <> PLN  then search currency exchange that day and convert the total
		if(!valuta.equals("PLN")){	unitprice = ConvertValutaToPLN(valuta, unitprice, Datum); }// end else if
	
		
		
			BigDecimal bdinit = new BigDecimal(init);
			bdinit = bdinit.setScale(2,BigDecimal.ROUND_UP);
			
			
			BigDecimal bdUnitPrice = new BigDecimal(unitprice);
			bdUnitPrice = bdUnitPrice.setScale(2,BigDecimal.ROUND_UP);
			
			BigDecimal bdqty = new BigDecimal(qty);
			BigDecimal total = bdUnitPrice.multiply(bdqty);
			
			total = total.add(bdinit);
			total = total.setScale(2,BigDecimal.ROUND_UP);
	
	
	return total.toString();
	
}


}