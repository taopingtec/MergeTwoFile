package com.taopingtec.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.taopingtec.tool.utils.MergeFileUtils;

public class MergeTwoFiles {

	public static void main(String[] args) throws IOException {
		if (null == args || 3 != args.length) {
			System.out
					.println("Please use like this: java -jar MergeTwoFiles.jar file1 file2 merged-file");
			return;
		}

		mergeTwoFiles(args[0], args[1], args[2]);

		System.out.println("Finished");

	}

	public static void mergeTwoFiles(String strFile1Path, String strFile2Path, String strMergedFilePath)
			throws IOException {
		File mergedFile = new File(strMergedFilePath);
		if (!mergedFile.createNewFile()) {
			System.out.println("Failed to Create " + strMergedFilePath);
			return;
		}

		ArrayList<String> lineTxtList1 = MergeFileUtils.getLineTxts(strFile1Path);
		ArrayList<String> lineTxtList2 = MergeFileUtils.getLineTxts(strFile2Path);
		ArrayList<String> mergedLineTxts = MergeFileUtils.getMergedLineTxts(lineTxtList1, lineTxtList2);

		MergeFileUtils.writeLineTxtsToFile(mergedLineTxts, mergedFile);
	}
}
