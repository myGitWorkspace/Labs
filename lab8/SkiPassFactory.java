package labs.lab8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/** Factory class to create and block different kinds of SkiPass cards
 * Class generates statistics for all types of SkiPass
 * 
 *
 */
public class SkiPassFactory {

	private static final String SKI_PASS_ID_FILE_NAME = "skiPassIdFile.txt";
	private static final String SKI_PASS_ACCESS_LOG_PATH = "SkiPassAccessLog\\";
	private static final String SKI_PASS_ACCESS_LOG_FOLDER_PREFIX = "TYPE-SkiPass-";
	private static final String SKI_PASS_ACCESS_LOG_ID_FILE_PREFIX = "SkiPass-id#";
	private static final SkiPassFactory instance = new SkiPassFactory();
	
	private SkiPassFactory() {

	}
	
	public static SkiPassFactory getInstance() {
		return instance;
	}
	
	public SkiPass produceSkiPass(int cardTypeId, int numberDaysOrTripsId) {
		
		if ( !cardTypeSignatureCheck(cardTypeId, numberDaysOrTripsId) )
			return null;

		int id = getIdForNewSkiPass();
		
		Calendar expireDate = getSkiPassExpireDate();			
		
		saveNewSkiPassIdToFile(id);
		
		switch (cardTypeId) {
			case 1:
				return new SkiPassByDaysOnWeekdays(id, expireDate, numberDaysOrTripsId);
			case 2:
				return new SkiPassByTripsOnWeekdays(id, expireDate, numberDaysOrTripsId);
			case 3:
				return new SkiPassByDaysOnWeekend(id, expireDate, numberDaysOrTripsId);
			case 4:
				return new SkiPassByTripsOnWeekdays(id, expireDate, numberDaysOrTripsId);
			case 5:
				return new SkiPassOnSeason(id, expireDate);
		}
				
		return null;
	}
	
	private boolean cardTypeSignatureCheck(int cardTypeId, int numberDaysOrTripsId) {
		
		if (cardTypeId <= 0 && cardTypeId >= 6) {
			System.out.println("Wrong card type!!! Must be 1 or 2 or 3 or 4 or 5");
			return false;
		}
				
		if (cardTypeId == 1 || cardTypeId == 2 || cardTypeId == 4) {
			if ( numberDaysOrTripsId < 0 || numberDaysOrTripsId > 3 ) {
				System.out.println("Wrong numberDaysOrTripsId!!! Must be 0-3 ");
				return false;
			}
		}
		
		if (cardTypeId == 3) {
			if ( numberDaysOrTripsId < 0 || numberDaysOrTripsId > 2 ) {
				System.out.println("Wrong numberDaysOrTripsId!!! Must be 0-2 ");
				return false;
			}
		}
		
		return true;
	}
	
	private int getIdForNewSkiPass() {
		
		File file = new File(SKI_PASS_ID_FILE_NAME);
		
		if (file.exists()) {
		
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
			String data = "";
			String lastRecord = "";
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				while ( (data = bufferedReader.readLine()) != null )
					lastRecord = data;
			} catch (IOException e) {
				System.err.println("File error"+e);
			} finally {
				try {
					if (bufferedReader != null)
						bufferedReader.close();
				} catch (IOException e) {
					System.err.println("Closing file error"+e);
				}
			}
			
			if ( lastRecord != "" ) {
				String[] idString = lastRecord.split("\\s");				
				return Integer.parseInt(idString[0])+1;
			}
		}
		
		return 1;
	}
	
	private Calendar getSkiPassExpireDate() {
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR)+1;
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
	    calendar.clear();
	    calendar.set(year,month,date);
	    
	    return calendar;
	}
	
	private void saveNewSkiPassIdToFile(int id) {
		
		if ( !isIdValid(id) )
			return;
		
		File file = new File(SKI_PASS_ID_FILE_NAME);
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);			
			printWriter.printf("%d %n", id);
		} catch (IOException e) {
			System.err.println("File error"+e);
		} finally {
			printWriter.close();
		}
		
	}
	
	public void blockSkiPass(int id) {
		
		if ( !isIdValid(id) )
			return;
		
		String firstPart = "";
		String secondPart = "";
		String insertLine = String.valueOf(id)+" blocked";
		
		File file = new File(SKI_PASS_ID_FILE_NAME);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String data = "";
		boolean found = false;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			while ( (data = bufferedReader.readLine()) != null ) {
				if (found == false) {
					if (data != "") {						
						String[] tempStr = data.split("\\s");
						if (tempStr[0] != "") {
							int idFind = Integer.parseInt(tempStr[0]);
							if (idFind == id) {
								found = true;	
								continue;
							}
						}
						firstPart += data+"\r\n";
					}
				} else if (found == true)
					secondPart += data+"\r\n";
			}

		} catch (IOException e) {
			System.err.println("File error"+e);
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				System.err.println("Closing file error"+e);
			}
		}
		
		if ( !found )
			return;
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter(file, false);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
			printWriter.printf("");
			printWriter.close();
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);			
			if (firstPart != "")
				printWriter.printf("%s", firstPart);
			printWriter.printf("%s%n", insertLine);
			if (secondPart != "")
				printWriter.printf("%s", secondPart);
		} catch (IOException e) {
			System.err.println("File error"+e);
		} finally {
			printWriter.close();
		}
		
	}
	
	public Calendar getCurrentDateTime() {
		
		Calendar calendar = Calendar.getInstance();	    
	  
	    return calendar;
	}
	
	public void writeSkiPassAccessLog(int id, int typeId, boolean access) {
		
		if ( !isIdValid(id) )
			return;
		
		if ( !isIdTypeValid(typeId) )
			return;
		
		String pathStr = SKI_PASS_ACCESS_LOG_PATH + File.separator + SKI_PASS_ACCESS_LOG_FOLDER_PREFIX + String.valueOf(typeId);		
		File accessDir = new File(pathStr);
		if ( !accessDir.exists() )
			accessDir.mkdirs();
		String fileName = pathStr + File.separator + SKI_PASS_ACCESS_LOG_ID_FILE_PREFIX + String.valueOf(id) + ".txt";
		
		String writeToFileStr = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(getCurrentDateTime().getTime()) )+((access)?" success":" failure");
		
		File file = new File(fileName);
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);			
			printWriter.printf("%s %n", writeToFileStr);
		} catch (IOException e) {
			System.err.println("File error"+e);
		} finally {
			printWriter.close();
		}
		
	}
	
	public boolean isSkiPassBlocked(int id) {
		
		if ( !isIdValid(id) )
			return false;		
		
		File file = new File(SKI_PASS_ID_FILE_NAME);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String data = "";
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			while ( (data = bufferedReader.readLine()) != null ) {
				if (data != "") {						
					String[] tempStr = data.split("\\s");
					if (tempStr[0] != "") {
						int idFind = Integer.parseInt(tempStr[0]);
						if (idFind == id) {
							if (tempStr.length > 1 && tempStr[1] != "")								
								if (tempStr[1].compareTo("blocked") == 0)
									return true;
								else if (tempStr[1] == "")
									return false;
						}
					}
					
				}
				
			}

		} catch (IOException e) {
			System.err.println("File error"+e);
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				System.err.println("Closing file error"+e);
			}
		}
		return false;
	}
	
	public void getStatistics(int ... typeId) {
		
		if (typeId.length == 0) {
			String pathStr = SKI_PASS_ACCESS_LOG_PATH;
			File logFolder = new File(pathStr);
			File[] listOfFiles = logFolder.listFiles();
			if (listOfFiles == null) {
				System.out.println("Error : Directory " + pathStr + " doesn't exist!!!");
				return;
			}
			
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isDirectory()) {
					
					String[] parseFileName = (listOfFiles[i].getName()).split("-");
					if (parseFileName != null && parseFileName.length == 3) {
						int currTypeId = Integer.parseInt(parseFileName[2]);						
						getStatisticsByIdType(currTypeId);
					}
				}
			}

			
		} else if (typeId.length > 0) {
			for (int id : typeId)
				getStatisticsByIdType(id);
		}
		
	}
	
	private void getStatisticsByIdType(int typeId) {
		
		if ( !isIdTypeValid(typeId) )
			return;
		
		String pathStr = SKI_PASS_ACCESS_LOG_PATH + File.separator + SKI_PASS_ACCESS_LOG_FOLDER_PREFIX + String.valueOf(typeId);
		File currTypeIdFolder = new File(pathStr);
		File[] listOfLogFiles = currTypeIdFolder.listFiles();
		if (listOfLogFiles == null) {
			System.out.println("Error : Directory " + pathStr + " doesn't exist!!!");
			return;
		}
		
		for (int j = 0; j < listOfLogFiles.length; j++) {
			if (listOfLogFiles[j].isFile()) {
				
				String[] parseUserCardId = (listOfLogFiles[j].getName()).split("#");
				int currUserCardId = 0;
				if (parseUserCardId != null && parseUserCardId.length == 2) {
					String[] fileExtension = parseUserCardId[1].split(".txt");
					if (fileExtension != null && fileExtension.length > 0)
						currUserCardId = Integer.parseInt(fileExtension[0]);
				}
				
				pathStr = SKI_PASS_ACCESS_LOG_PATH + File.separator + SKI_PASS_ACCESS_LOG_FOLDER_PREFIX + String.valueOf(typeId) + File.separator + listOfLogFiles[j].getName();
				File file = new File(pathStr);
				FileReader fileReader = null;
				BufferedReader bufferedReader = null;
				String data = "";
				String lastRecord = "";
				try {
					fileReader = new FileReader(file);
					bufferedReader = new BufferedReader(fileReader);
					while ( (data = bufferedReader.readLine()) != null ) {
						lastRecord = data;
						String outPutStatistics = "SkiPass Type = "+String.valueOf(typeId)+", user card id = "+currUserCardId+" : "+lastRecord;
						System.out.println(outPutStatistics);
					}
				} catch (IOException e) {
					System.err.println("File error"+e);
				} finally {
					try {
						if (bufferedReader != null)
							bufferedReader.close();
					} catch (IOException e) {
						System.err.println("Closing file error"+e);
					}
				}
			}

		}
		
	}
	
	public int getSkiPassTypeId(SkiPass skiPass) {
		
		if (skiPass == null) {
			System.out.println("SkiPass parameter in function is null!!!");
			return 0;
		}
		
		if (skiPass instanceof SkiPassByDaysOnWeekdays)
			return 1;
		if (skiPass instanceof SkiPassByTripsOnWeekdays)
			return 2;
		if (skiPass instanceof SkiPassByDaysOnWeekend)
			return 3;
		if (skiPass instanceof SkiPassByTripsOnWeekdays)
			return 4;
		if (skiPass instanceof SkiPassOnSeason)
			return 5;
		return 0;
	}
	
	private boolean isIdValid(int id) {
		if ( id < 1 ) {
			System.out.println("Id parameter in function is invalid!!!");
			return false;
		}
		return true;
	}
	
	private boolean isIdTypeValid(int idType) {		
		if ( idType < 1 || idType > 5 ) {
			System.out.println("Id type of SkiPass as parameter in function is invalid!!!");
			return false;
		}
		return true;
	}
	
}
