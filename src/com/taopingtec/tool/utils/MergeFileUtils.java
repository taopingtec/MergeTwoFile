package com.taopingtec.tool.utils;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MergeFileUtils {
	public static ArrayList<String> getMergedLineTxts(ArrayList<String> lineTxts1,
			ArrayList<String> lineTxts2) {
		ArrayList<String> mergedLineTxtList = new ArrayList<String>();

		if (null != lineTxts1 && lineTxts1.size() > 0) {
			for (String strLineTxt : lineTxts1) {
				if (!containsTrimedTxt(mergedLineTxtList, strLineTxt))
					mergedLineTxtList.add(strLineTxt);
			}
		}

		if (null != lineTxts2 && lineTxts2.size() > 0) {
			for (String strLineTxt : lineTxts2) {
				if (!containsTrimedTxt(mergedLineTxtList, strLineTxt))
					mergedLineTxtList.add(strLineTxt);
			}
		}

		return mergedLineTxtList;
	}

	public static ArrayList<String> getLineTxts(String strFilePath) throws IOException {
		ArrayList<String> strLineTxtList = new ArrayList<String>();

		InputStreamReader read = new InputStreamReader(new FileInputStream(new File(strFilePath)));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			strLineTxtList.add(lineTxt);
		}
		read.close();

		return strLineTxtList;
	}

	public static void writeLineTxtsToFile(ArrayList<String> strLineTxtList, File file)
			throws IOException {
		if (null == strLineTxtList || strLineTxtList.size() <= 0 || null == file)
			return;

		InputStreamReader read = new InputStreamReader(new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(read);
		CharArrayWriter tempStream = new CharArrayWriter();
		// String lineTxt = null;
		for (String strLineTxt : strLineTxtList) {
			tempStream.append(strLineTxt);
			tempStream.append(System.getProperty("line.separator"));
		}

		bufferedReader.close();
		FileWriter out = new FileWriter(file);
		tempStream.writeTo(out);
		out.close();
	}

	private static boolean containsTrimedTxt(ArrayList<String> strTxtList, String strTxt) {
		if (null == strTxtList || null == strTxt)
			return false;

		String strTrimedTxt = strTxt.trim();
		if (null == strTrimedTxt || strTrimedTxt.length() <= 0)
			return false;

		for (String strTxtItem : strTxtList) {
			if (strTxtItem.contains(strTrimedTxt))
				return true;
		}

		return false;
	}
}
