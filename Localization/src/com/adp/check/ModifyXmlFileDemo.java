package com.adp.check;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.TestNG;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.adp.main.config.Constants;
import com.google.common.collect.Lists;

public class ModifyXmlFileDemo {

public static void main(String argv[]) {
	try{
/*	TestNG testng = new TestNG();
//	testng.setVerbose();
	ArrayList<String> suites = Lists.newArrayList();
	suites.add("C:\\ProjectWorkspace\\Localization\\config\\parellelTest.xml");
	testng.setTestSuites(suites);
	testng.run();*/
//	Config.tearDown();
		//{"testSubscribe","testBlogs"};
		String [][] mainExcel = new String[3][3] ;
		mainExcel[0][0]=("testSubscribe");
		mainExcel[1][0]=("testBlogs");
		updateXML(mainExcel);
	}catch(Exception e)
	{
		e.printStackTrace();
	}

   
}


public static void updateXML(String[][] mainExcel)
{
	   try {
		   
		   ArrayList<String> testCaseForExecution = new ArrayList<>();
		   for(int i=0;i<mainExcel.length;i++)
		   {
			    testCaseForExecution.add(mainExcel[i][2]);
		   }
		 //  System.out.println(testCaseForExecution.size());
		   
		   
		      File inputFile = new File(Constants.testngXML); 	    	
		      DocumentBuilderFactory docFactory =DocumentBuilderFactory.newInstance();
		      DocumentBuilder docBuilder =  docFactory.newDocumentBuilder();
		      Document doc = docBuilder.parse(inputFile);
		     
		     /* Node methodsNode = doc.getElementsByTagName("methods").item(1);
		      NodeList list = ((Node) methodsNode).getChildNodes();
		      System.out.println("Length="+list.getLength());*/
		      
		      NodeList nodeList=doc.getElementsByTagName("suite");
		     
		      /*for (int i=0; i<nodeList.getLength(); i++) 
		      {
		      */    // Get element
		          Element element = (Element)nodeList.item(0);
		          System.out.println(element.getAttribute("parallel"));
		          element.setAttribute("parallel","no");
		          //NodeList list1= element.getChildNodes();
		          
		          //Node node1 = list1.item(1);
		         // System.out.println("NOde Name="+element.geta);
		          
		         /*if (node1.getNodeType() == Node.ELEMENT_NODE) {
		             Element eElement = (Element) node1;
		             
		             if(testCaseForExecution.contains(eElement.getAttribute("name")))
		             {
		            	 doc.renameNode(list1.item(1), "", "include");
		            	
		             }else{
		            	 doc.renameNode(list1.item(1), "", "exclude");
		             }*/
		          //System.out.println("Name="+eElement.getAttribute("name"));
		       //   }
		         /* for(int y=0;y<list1.getLength();y++)
		          {
		        	  System.out.println("Child no proper="+list1.item(0).getNodeName());
		        	  System.out.println("Child no name="+list1.item(0).getTextContent());
		          }*/
		     //     System.out.println("Sibling="+element.getNextSibling().getNodeName());
		          /*if(element.getNodeName().equalsIgnoreCase("methods"))
		          {
		        	  
		          }*/
		      //}
		      
		      
		      /*for (int temp = 0; temp < list.getLength(); temp++) {
		          Node node = list.item(temp);
		          System.out.println("NOde Name="+node.getNodeName());
		          if (node.getNodeType() == Node.ELEMENT_NODE) {
		             Element eElement = (Element) node;
		          System.out.println("Name="+eElement.getAttribute("name"));
		             if(testCaseForExecution.contains(eElement.getAttribute("name")))
		             {
		            	 doc.renameNode(list.item(temp), "", "include");
		            	
		             }else{
		            	 doc.renameNode(list.item(temp), "", "exclude");
		             }
		             
		           //  System.out.println(eElement.getTextContent());
		         //    System.out.println(node.getNodeName());
		          }*/
		    //  }
		         TransformerFactory transformerFactory =  TransformerFactory.newInstance();
		         Transformer transformer = transformerFactory.newTransformer();
		         DOMSource source = new DOMSource(doc);
		       //  System.out.println("-----------Modified File-----------");
		         StreamResult consoleResult = new StreamResult(source.toString());
		         //StreamResult consoleResult = new StreamResult(new File(Constants.testngXML));
		         transformer.transform(source, consoleResult);
		  //       logger.info("Execution XML file updated successfully...");
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }

}