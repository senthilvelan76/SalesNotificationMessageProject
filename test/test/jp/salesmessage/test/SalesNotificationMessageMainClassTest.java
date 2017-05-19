package test.jp.salesmessage.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.jp.salesmessage.impl.MessageReadAndParserDetails;
import test.jp.salesmessage.main.SalesNotificationMessageMainClass;


public class SalesNotificationMessageMainClassTest {

	SalesNotificationMessageMainClass tp;
	MessageReadAndParserDetails messageParser;
	@Before
	public void setUp() throws Exception {
		 tp=new SalesNotificationMessageMainClass();
		 messageParser= new MessageReadAndParserDetails("");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void checkMainClassbject() {
		//fail("Not yet implemented");
		assertNotNull(tp);
		assertNotNull(messageParser);
	}
	
	//valid filename passing
	@Test
	public void validFileName() {
		String fileName="inputfile/salesmessagedetails.txt";
		String fileValidCheck= tp.fileNameValidate(fileName);
		String expectedResult="FileValid";
		assertSame(expectedResult, fileValidCheck);
	}
	
	//invalid filename passing
	@Test
	public void inValidFileName() {
		String fileName="inputfile/salesmessagedetails1.txt";
		String fileValidCheck= tp.fileNameValidate(fileName);
		String expectedResult="FileInValid";
		assertSame(expectedResult, fileValidCheck);
	}
	  //valid messagetype1 passing
		@Test
		public void validMessageType1() {
			String message="apple at 10p";
			boolean messageValue= messageParser.parseMessage(message);
			//System.out.println("validMessageType1-->"+messageValue);
			assertSame(true, messageValue);
		}
		
		//invalid messagetype1 passing
				@Test
				public void inValidMessageType1() {
					String message="apple at";
					boolean messageValue= messageParser.parseMessage(message);
					//System.out.println("inValidMessageType1-->"+messageValue);
					assertSame(false, messageValue);
				}
	
				  //valid messagetype2 passing
				@Test
				public void validMessageType2() {
					String message="20 sales of apples at 10p each";
					boolean messageValue= messageParser.parseMessage(message);
					//System.out.println("validMessageType2-->"+messageValue);
					assertSame(true, messageValue);
				}
				
				//invalid messagetype1 passing
						@Test
						public void inValidMessageType2() {
							String message="sales of apples at 10p each";
							boolean messageValue= messageParser.parseMessage(message);
							//System.out.println("inValidMessageType2-->"+messageValue);
							assertSame(false, messageValue);
						}
						  //valid messagetype3 passing
						@Test
						public void validMessageType3() {
							String message="Add 20p apples";
							boolean messageValue= messageParser.parseMessage(message);
							//System.out.println("validMessageType3-->"+messageValue);
							assertSame(true, messageValue);
						}
						
						//invalid messagetype3 passing
								@Test
								public void inValidMessageType3() {
									String message="ddd 20p apples";
									boolean messageValue= messageParser.parseMessage(message);
									//System.out.println("inValidMessageType3-->"+messageValue);
									assertSame(false, messageValue);
								}
						
				
	//valid filename passing and processing the sales message notificaiton successfuly
	//Process will terminate after 50th message and show result.so test will not return anything
	//It is used for full execution and verify the result only
	
	// invalid filename or path
	@Test
	public void showResultSalesMessage() {
		
		String fileName= "inputfile/salesmessagedetails1.txt";
		String actualResult= tp.processSalesMessage(fileName);
		System.out.println(actualResult);
		String expectedResult="ProcessFailure";
		assertSame(expectedResult, actualResult);		
	}
	
	//valid filename passing and processing the sales message notificaiton successfuly
	//Process will terminate after 50th message and show result.so test will not return anything
	//It is used for full execution and verify the result only
		
	// valid filename or path
	/*@Test
	public void showResultSalesMessage1() {
		
		String fileName= "inputfile/salesmessagedetails.txt";
		String actualResult= tp.processSalesMessage(fileName);
		System.out.println(actualResult);
		String expectedResult="ProcessFailure";
		assertSame(expectedResult, actualResult);		
	}*/
}
