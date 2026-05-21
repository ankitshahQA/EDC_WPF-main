package com.automation.PythonAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

@SuppressWarnings("serial")
public class PythonHelp extends TestBase {
	public static String msg = "";
	public static String s = "";
	public static String s1 = "";
	public static String output = "";
	
	public static String runExe(String exePath, String[] args) throws IOException {

		ProcessBuilder pb = new ProcessBuilder();
		java.util.List<String> command = new java.util.ArrayList<>();
		command.add(exePath);
		for (String arg : args) {
			command.add(arg);
		}
		pb.command(command);
		pb.redirectErrorStream(true); // merge error + output
		Process process = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		String output = "";
		while ((line = reader.readLine()) != null) {
			System.out.println("OUTPUT: " + line);
			output += line;
		}
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return output;

	}

	public static void CreateFolder() throws IOException {

		msg = "Create New Folder";
		childnode = logger.createNode(msg);
		String output = "";
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "CreateFolder.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "CreateFolder.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword,
				"--FolderName", FolderName };
		try {
			output = runExe(exePath, args);
		} catch (IOException e) {
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "Exception occurred while executing the EXE: " + e.getMessage());
			return;
		}

		if (output.contains("STATUS:PASS")) {
			foldercreateddone = true;
			CommonFunction.logStatus("PASS", "The folder  '" + FolderName + "' creation was completed successfully.");
		} else {
			foldercreateddone = false;
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "The system failed to create the folder. '" + FolderName + "' ");
		}
	}

	public static void DeleteAllFolder() throws IOException {
		msg = "Delete All the Existing Folders and Items created by Automation";
		childnode = logger.createNode(msg);
		String output = "";
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "DeleteAllFolder.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "DeleteAllFolder.exe";
		}

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword,
				"--FolderName", FolderName, "--BaseFolderName", BaseFolderName };

		File exeFile = new File(exePath);
		if (exeFile.exists()) {
			System.out.println("Using EXE Path: " + exePath);
			try {
				output = runExe(exePath, args);
			} catch (IOException e) {
				CommonFunction.logStatus("FAIL", "Exception occurred while executing the EXE: " + e.getMessage());
				return;
			}
		} else {
			// Fallback: no precompiled EXE on this machine. Run the .py directly
			// using a Python interpreter (mirrors DeleteSceneLayers below).
			String pyPath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "DeleteAllFolder.py";
			if (!new File(pyPath).exists()) {
				CommonFunction.logStatus("FAIL",
						"DeleteAllFolder cleanup skipped: neither DeleteAllFolder.exe nor DeleteAllFolder.py was found.");
				return;
			}
			System.out.println("EXE not found — falling back to Python script: " + pyPath);
			try {
				output = runPython(pyPath, args);
			} catch (IOException e) {
				CommonFunction.logStatus("FAIL",
						"Exception occurred while executing DeleteAllFolder.py: " + e.getMessage());
				return;
			}
		}

		if (output.contains("STATUS:PASS")) {
			CommonFunction.logStatus("PASS", "The folder deletion was completed successfully.");
		} else {
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "The system failed to delete the folder.");
		}
	}

	/**
	 * Run a Python script using the first available interpreter. Tries (in
	 * order): ArcGIS Pro's {@code arcgispro-py3} env (the only env with the
	 * {@code arcgis} package preinstalled), then {@code python} on PATH, then
	 * {@code py} (the Windows launcher). Returns the merged stdout/stderr.
	 */
	public static String runPython(String scriptPath, String[] args) throws IOException {
		String[] interpreters = new String[] {
			"C:\\Program Files\\ArcGIS\\Pro\\bin\\Python\\envs\\arcgispro-py3\\python.exe",
			"python",
			"py"
		};
		IOException lastError = null;
		for (String interpreter : interpreters) {
			if (interpreter.contains(File.separator) && !new File(interpreter).exists()) {
				continue;
			}
			java.util.List<String> command = new java.util.ArrayList<>();
			command.add(interpreter);
			command.add(scriptPath);
			for (String arg : args) {
				command.add(arg);
			}
			System.out.println("Executing: " + String.join(" ", command));
			ProcessBuilder pb = new ProcessBuilder(command);
			pb.redirectErrorStream(true);
			try {
				Process process = pb.start();
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				StringBuilder output = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					System.out.println("OUTPUT: " + line);
					output.append(line);
				}
				try {
					process.waitFor();
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
				return output.toString();
			} catch (IOException e) {
				lastError = e;
				// Try next interpreter.
			}
		}
		throw new IOException(
				"No usable Python interpreter found (tried ArcGIS Pro python, 'python', 'py')."
				+ (lastError != null ? " Last error: " + lastError.getMessage() : ""));
	}

	public static void DeleteAllCreatedtems() throws IOException {
		msg = "Delete All the Existing Folders and Items created by Automation";
		childnode = logger.createNode(msg);
		String output = "";
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "DeleteItemsAll.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "DeleteItemsAll.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword };
		try {
			output = runExe(exePath, args);
		} catch (IOException e) {
			CommonFunction.logStatus("FAIL", "Exception occurred while executing the EXE: " + e.getMessage());
			return;
		}

		if (output.contains("PASS")) {
			CommonFunction.logStatus("PASS",
					"Verify all existing items in the 'All My Content' folder, created by automation, have been successfully deleted.");
		} else {
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "The system failed to delete all existing items from 'All My Content' Folder created by Automation");
		}
	}

	public static void DeleteSceneLayers() throws IOException {
		// System.out.println("DeleteFolders method called"+BaseFolderName);

		String pathPython = System.getProperty("user.dir") + "\\src\\com\\automation\\PythonAPI\\DeleteSceneLayers.py";

		String cmd = "\"C:\\Program Files\\ArcGIS\\Pro\\bin\\Python\\envs\\arcgispro-py3\\python\" " + "\"" + pathPython
				+ "\"" + " --portalUrl \"" + Portalurl + "\"" + " --userName \"" + PortalUserName + "\""
				+ " --password \"" + PortalPassword + "\"";
		System.out.println("Executing: " + cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String line;
		String output = "";

		// ? Read normal output
		while ((line = input.readLine()) != null) {
			System.out.println("OUTPUT: " + line);
			output += line;
		}

		// ? Read error output (VERY IMPORTANT)
		while ((line = error.readLine()) != null) {
			System.out.println("ERROR: " + line);
		}

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ? Check STATUS from Python
		if (output.contains("PASS")) {
			// CommonFunction.logStatus("PASS", "All the existing Scene Layers have been
			// deleted successfully.");

		} else if (output.contains("SKIPPED")) {
			// CommonFunction.logStatus("Info", "All the existing Scene Layers have been
			// deleted successfully.");

		} else {
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "Failed to delete Scene Layers.");
		}

	}

	public static void MoveItemInfolder(String CreatedLayerName) throws IOException {
		String Item_LayerName = CreatedLayerName;
		String output = "";
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "MoveItem.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "MoveItem.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword,
				"--FolderName", FolderName, "--Item_LayerName", Item_LayerName };
		
		try {
			output = runExe(exePath, args);
		} catch (IOException e) {
			CommonFunction.logStatus("FAIL", "Exception occurred while executing the EXE: " + e.getMessage());
			return;
		}

		if (output.contains("STATUS:PASS")) {
			CommonFunction.logStatus("PASS", "Created item '" + Item_LayerName + "' moved to folder '" + FolderName + "' successfully.");
		}else if (output.contains("SKIPPED")) {
			CommonFunction.logStatus("PASS", "Created item '" + Item_LayerName + "' already exist in folder '" + FolderName + "'");
		}else {
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "The system failed to move item '" + Item_LayerName + "' to folder '" + FolderName + "'.");
		}
	}
	
	public static void validateDataStoresPython() throws IOException {
		msg = "Validate Data Stores";
		childnode = logger.createNode(msg);
		
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "ValidateDataStore.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "ValidateDataStore.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword };
		ProcessBuilder pb = new ProcessBuilder();
		java.util.List<String> command = new java.util.ArrayList<>();
		command.add(exePath);
		for (String arg : args) {
			command.add(arg);
		}
		pb.command(command);
		pb.redirectErrorStream(true); // merge error + output
		Process process = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		String finalStatus = "";
		String failedDatastores = "";

		while ((line = reader.readLine()) != null) {
		    System.out.println("OUTPUT: " + line);

		    if (line.contains("FINAL_STATUS:")) {
		        finalStatus = line.split(":", 2)[1].trim();
		    }

		    if (line.contains("FAILED_DATASTORES:")) {
		        failedDatastores = line.split(":", 2)[1].trim();
		    }
		}

		// ?? Reporting Logic
		if ("PASS".equalsIgnoreCase(finalStatus)) {
			CommonFunction.logStatus("PASS", "Data Store validation passed successfully. All data stores are in a healthy state and marked as green.");
		} else if ("FAIL".equalsIgnoreCase(finalStatus)) {
		    System.out.println("? REPORT: DataStore Validation FAILED");

		    if (!failedDatastores.isEmpty()) {
		        String[] dsList = failedDatastores.split(",");

		        System.out.println("Failed Datastores:");
		        for (String ds : dsList) {
		            System.out.println(" - " + ds.trim());
		        }
		    }CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "Data Store validation failed. The following data stores are not marked as green.   " + failedDatastores );
		}else if("WARNING".equalsIgnoreCase(finalStatus)) {
			CommonFunction.logStatus("WARNING", "The portal is not hosted on a server. No data stores were found, or validation is not applicable");
	}else {
		    System.out.println("?? REPORT: Unable to determine status");
		}
		
	}
		
	public static void CreateSceneLayer() throws IOException {
		msg = "Create New Scene Layer";
		childnode = logger.createNode(msg);
		
		String output = "";
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "SceneLayerCreation.exe";
		//String exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation" + File.separator + "PythonAPI" + File.separator + "SceneLayerCreation.py";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "SceneLayerCreation.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword,
				"--FolderName", FolderName, "--SCENE_LAYER", SceneLayerName, "--SCENE_TITLE", SceneTitle };
		try {
			output = runExe(exePath, args);
		} catch (IOException e) {
			CommonFunction.logStatus("FAIL", "Exception occurred while executing the EXE: " + e.getMessage());
			return;
		}

		if (output.contains("FINAL STATUS: PASS")) {
			scenecreated=true;
			CommonFunction.logStatus("PASS", "The Scene layer is Created  successfully.");
		} else {
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT", "The system failed to Create the Scene Layer.");
		}
	}

	public static void getProjectVersion() throws IOException {

		String output = "";
		String versionInfo = "";
		DeploymentType = "";
		Appversion = "";
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "Projectversion.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "Projectversion.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword };
		try {
			output = runExe(exePath, args);
			if (output.contains("FINALVERSION:")) {
		        versionInfo = output.split("FINALVERSION:")[1].trim();
		    }
		} catch (IOException e) {
			CommonFunction.logStatus("FAILWITHOUTSCREENSHOT","Exception occurred while executing the EXE: " + e.getMessage());
			return;
		}
		// ?? Process extracted value
		if (!versionInfo.isEmpty() && versionInfo.contains("_")) {
			String[] parts = versionInfo.split("_");
			DeploymentType = parts[0];
			Appversion = parts[1];
			System.out.println("Deployment: " + DeploymentType);
			System.out.println("Version: " + Appversion);
			System.out.println("Final Version: " + DeploymentType + "_" + Appversion);

		} else {
			System.out.println("Failed to extract version from output:");
			
		}
	}

	
	public static void ValidateObjectDataStore_K8S() throws IOException {
		msg = "Verify the Object Data Store in a Kubernetes Environment";
		//childnode = logger.createNode(msg);
		String output = "";
		ObjectStore = false;
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "ValidateObjectStorek8s.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "ValidateObjectStorek8s.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword };
		try {
			output = runExe(exePath, args);
		} catch (IOException e) {
			CommonFunction.logStatus("FAIL", "Exception occurred while executing the EXE: " + e.getMessage());
			return;
		}

		if (output.equalsIgnoreCase("PRECONDITION_STATUS:TRUE")) {
			ObjectStore = true;
			} else {
				ObjectStore = false;
			}
	}

	public static void ValidateObjectDataStore() throws IOException {
		msg = "Verify the Object Data Store ";
		//childnode = logger.createNode(msg);
		String output = "";
		ObjectStore = false;
		String basePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		String exePath = basePath + File.separator + "Input" + File.separator + "ValidateObjectStore.exe";

		File f = new File(exePath);
		if (!f.exists()) {
			exePath = basePath + File.separator + "src" + File.separator + "com" + File.separator + "automation"
					+ File.separator + "PythonAPI" + File.separator + "dist" + File.separator + "ValidateObjectStore.exe";
		}

		System.out.println("Using EXE Path: " + exePath);

		String[] args = { "--portalUrl", Portalurl, "--userName", PortalUserName, "--password", PortalPassword };
		try {
			output = runExe(exePath, args);
		} catch (IOException e) {
			CommonFunction.logStatus("FAIL", "Exception occurred while executing the EXE: " + e.getMessage());
			return;
		}

		if (output.equalsIgnoreCase("PRECONDITION_STATUS:TRUE")) {
			ObjectStore = true;
			} else {
				ObjectStore = false;
			}
	}

	
}
		
