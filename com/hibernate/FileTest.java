package com.hibernate;

import java.io.File;
import java.util.Scanner;

import org.hibernate.Session;

public class FileTest {
	static String fileUrl="C:\\Users\\Sushant\\workspace\\HibernateExample\\src\\main\\java\\gamefilesmall.csv";
	static GameDetails gameTable = null;
	static String vltId = " ";
	
	public static void FileInput(String fileUrl) throws Exception {
		File file = new File(fileUrl);
		Scanner scanner = new Scanner(file);
		int countLine = 0;
		String s;
		while (scanner.hasNext()) {
			s = scanner.nextLine();
		
			countLine ++;
			System.out.println("countLine:" + countLine);
			if (countLine >= 6 && s.trim().length() != 0) {
				String[] str = SingleLine(s);

				if (str[1].trim().length() != 0 && (!"2".equals(str[0]))) {
					vltId = str[1];
				}
				System.out.println("Line First Element:" + str[0]);
				System.out.println();
				if ("1".equals(str[0])) {
					System.out.println("Calling Table Column :1");
					gameTable = FileTest.getInstance();
					gameTable.setRecordType(str[0]);
					if (str[1].trim().length() == 0) {
						gameTable.setVLTID(vltId);
					} else {
						gameTable.setVLTID(str[1]);
					}
					gameTable.setCreationDateTime(str[2]);
					gameTable.setLogSequence(str[3]);
					gameTable.setDeviceID(str[4]);
					gameTable.setTransactionID(str[5]);
					gameTable.setGameTime(str[6]);
					gameTable.setPlayState(str[7]);
					gameTable.setPlayResult(str[8]);
					gameTable.setDenom(str[9]);
					gameTable.setInitialWager(str[10]);
					gameTable.setInitialWin(str[11]);
					gameTable.setSecondaryPlayed(str[12]);
					gameTable.setSecondaryWin(str[13]);
					gameTable.setFinalWin(str[14]);
					gameTable.setPaytableId(str[15]);
					gameTable.setThemeId(str[16]);
					gameTable.setInitialStartTime(str[17]);
					gameTable.setInitialPlayerCashableAmount(str[18]);
					gameTable.setInitialPlayerNonCashableAmount(str[19]);
					gameTable.setInitialPlayerPromoAmount(str[20]);
					gameTable.setPlayerCashableAmount(str[21]);
					gameTable.setPlayerNonCashableAmount(str[22]);
					gameTable.setPlayerPromoAmount(str[23]);
					gameTable.setPlayerSessionID(str[24]);
					gameTable.setPlayerID(str[25]);
					FileTest.saveUser(gameTable);
					System.out.println("Submitted to Database:");
					gameTable = null;
				} else if ("2".equals(str[0])) {
					System.out.println("Calling Table Column :2");
					gameTable = FileTest.getInstance();
					gameTable.setRecordType(str[0]);
					gameTable.setWinLevelIndex(str[1]);
					gameTable.setWinLevelCombo(str[2]);
					gameTable.setProgressiveAllowed(str[3]);
					FileTest.saveUser(gameTable);
					System.out.println("Submitted to Database:");
					gameTable = null;
				} else if ("3".equals(str[0])) {
					System.out.println("Calling Table Column :3");
					gameTable = FileTest.getInstance();
					gameTable.setRecordType(str[0]);
					gameTable.setDeviceClass(str[1]);
					gameTable.setTransactionID(str[2]);
					gameTable.setLogSequence(str[3]);
					gameTable.setCashableAmount(str[4]);
					gameTable.setPromoAmount(str[5]);
					gameTable.setNonCashableAmount(str[6]);
					FileTest.saveUser(gameTable);
					System.out.println("Submitted to Database:");
					gameTable = null;
				} else if ("4".equals(str[0])) {
					System.out.println("Calling Table Column :4");
					gameTable = FileTest.getInstance();
					gameTable.setRecordType(str[0]);
					if (str[1].trim().length() == 0) {
						gameTable.setVLTID(vltId);
					} else {
						gameTable.setVLTID(str[1]);
					}
					gameTable.setCreationDateTime(str[2]);
					gameTable.setDeviceID(str[3]);
					gameTable.setTransactionID(str[4]);
					gameTable.setCurrencyID(str[5]);
					gameTable.setDenomID(str[6]);
					gameTable.setBaseCashableAmt(str[7]);
					gameTable.setNoteDateTime(str[8]);
					FileTest.saveUser(gameTable);
					System.out.println("Submitted to Database:");
					gameTable = null;
				} else if ("5".equals(str[0])) {
					System.out.println("Calling Table Column :5");
					gameTable = FileTest.getInstance();
					gameTable.setRecordType(str[0]);
					if (str[1].trim().length() == 0) {
						gameTable.setVLTID(vltId);
					} else {
						gameTable.setVLTID(str[1]);
					}
					gameTable.setCreationDateTime(str[2]);
					gameTable.setDeviceID(str[3]);
					gameTable.setTransactionID(str[4]);
					gameTable.setValidationID(str[5]);
					gameTable.setVoucherSequence(str[6]);
					gameTable.setVoucherAmt(str[7]);
					gameTable.setTransferDateTime(str[8]);
					FileTest.saveUser(gameTable);

					System.out.println("Submitted to Database:");
					gameTable = null;
				} else if ("5".equals(str[0]) || str[0].trim().length() != 0) {
					break;
				}
			}else {
				System.out.println("Empty Line:");
			}
			
			s=null;
			
		}
		scanner.close();
		gameTable = null;

	}

	public static GameDetails getInstance() {
		if (gameTable == null) {
			return new GameDetails();
		}
		return null;
	}

	public static String[] SingleLine(String s) {

		String opLine = s;
		String[] resLine = opLine.split(" ");
		int i = 0;
		StringBuffer sb = new StringBuffer();
		for (i = 0; i < resLine.length; i++) {
			sb.append(resLine[i]);
		}

		String str = sb.toString();
		String[] newStr = str.split("\\|", 50);

		return newStr;

	}

	public static void saveUser(GameDetails gameTable) {
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			session.save(gameTable);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) throws Exception {
		FileTest.FileInput(fileUrl);
	}

}
