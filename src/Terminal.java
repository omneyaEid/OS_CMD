import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
public class Terminal {
	String hostname = "Unknown";
	public String currentDirectory ="C:\\Users\\"+System.getProperty("user.name")+"\\";
	public void cp(String sourcePath, String destinationPath) {
		if(!sourcePath.contains(":")&&!sourcePath.equals("")) {
			sourcePath=currentDirectory+sourcePath+"\\";
		}
		if(!destinationPath.contains(":")&&!destinationPath.equals("")) {
			destinationPath=currentDirectory+destinationPath+"\\";
		}
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			File source = new File(sourcePath);
			File dest = new File(destinationPath);
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void mkdir(String direction) {
		if(!direction.contains(":")&&!direction.equals("")) {
			direction=currentDirectory+direction+"\\";
		}
		boolean success = (new File(direction)).mkdirs();
	}
	public void clear() {
		for (int clear = 0; clear < 1000; clear++) {
			System.out.println();
		}
	}
	public void pwd(String path, int flag) throws IOException {
		if((!path.contains(":"))&&!path.equals("")){
			path=currentDirectory+path+"\\";
		}
		if (flag == 0) {
			System.out.println(currentDirectory);
		} else if (flag == 1) {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(currentDirectory);
			writer.newLine();
			writer.close();
		} else if (flag == 2) {
			String temp = currentDirectory + System.lineSeparator();
			Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
		}
	}
	public void cd(String path) {	
		currentDirectory = path;
	}
	public void rmdir(String direction) throws IOException {
		if(!direction.contains(":")&&!direction.equals("")) {
			direction=currentDirectory+direction+"\\";
		}
		File file = new File(direction);
		for (File childFile : file.listFiles()) {
			childFile.delete();
		}
		file.delete();
	}
	public void ls(String Spath, String filePath, int flag) {
		if(!filePath.contains(":")&&!filePath.equals("")) {
			filePath=currentDirectory+filePath+"\\";
		}
		File f = null;
		String[] paths;
		try {
			// create new file
			f = new File(Spath);
			// array of files and directory
			paths = f.list();
			// for each name in the path array
			if (flag == 0) {
				for (String path : paths) {
					// prints filename and directory name
					System.out.println(path);
				}
			}
			/// truncate the file then write
			else if (flag == 1) {
				BufferedWriter writer;
				writer = new BufferedWriter(new FileWriter(filePath));
				for (String path : paths) {
					writer.write(path);
					writer.newLine();
				}
				writer.close();
			}
			/// append on file
			else if (flag == 2) {
				for (String path : paths) {
					String temp = path + System.lineSeparator();
					Files.write(Paths.get(filePath), temp.getBytes(), StandardOpenOption.APPEND);
				}
			}
		} catch (Exception e) {
			// if any error occurs
			// e.printStackTrace();
			System.out.println("Error");
		}
	}
public void mv(String sourcePath, String destinationPath) {
	if(!sourcePath.contains(":")&&!sourcePath.equals("")) {
		sourcePath=currentDirectory+sourcePath+"\\";
	}
	if(!destinationPath.contains(":")&&!destinationPath.equals("")) {
		destinationPath=currentDirectory+destinationPath+"\\";
	}
		InputStream inStream = null;
		OutputStream outStream = null;
	    	try{
	    	    File afile =new File(sourcePath);
	    	    File bfile =new File(destinationPath);	
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	    	    byte[] buffer = new byte[1024];
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	    	    	outStream.write(buffer, 0, length);
	    	    }
	    	    inStream.close();
	    	    outStream.close();
	    	    //delete the original file
	    	    afile.delete();
	    	    System.out.println("File is copied successful!");
	    	}catch(IOException e){
	    	    e.printStackTrace();
	    	}
	}
public void date(String path, int flag) throws IOException {
	if(!path.contains(":")&&!path.equals("")) {
		path=currentDirectory+path+"\\";
	}
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // date
	LocalDateTime now = LocalDateTime.now();
	if (flag == 0) {
		System.out.println(dtf.format(now)); // 2016/11/16 12:08:43
	} else if (flag == 1) {
		BufferedWriter writer;
		writer = new BufferedWriter(new FileWriter(path));
		writer.write(dtf.format(now));
		writer.newLine();
		writer.close();
	} else if (flag == 2) {
		String temp = dtf.format(now) + System.lineSeparator();
		Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
	}
}
	public void rm(String path) {
		if(!path.contains(":")&&!path.equals("")) {
			path=currentDirectory+path+"\\";
		}
		try {
			File file = new File(path); // delete file
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void cat(String path1, String path2, String newPath, int flag) throws IOException {
		if(!path1.contains(":")&&!path1.equals("")) {
			path1=currentDirectory+path1+"\\";
		}
		if(!path2.contains(":")&&!path2.equals("")) {
			path2=currentDirectory+path2+"\\";
		}
		if(!newPath.contains(":")&&!newPath.equals("")) {
			newPath=currentDirectory+newPath+"\\";
		}
		BufferedReader in = new BufferedReader(new FileReader(path1));
		String line;
		if (flag == 0) {
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} else if (flag == 1) {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(newPath));
			while ((line = in.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		} else if (flag == 2) {
			while ((line = in.readLine()) != null) {
				String temp = line + System.lineSeparator();
				Files.write(Paths.get(newPath), temp.getBytes(), StandardOpenOption.APPEND);
			}
		}
		in.close();
		BufferedReader in1 = new BufferedReader(new FileReader(path2));
		String linee;
		if (flag == 0) {
			while ((linee = in1.readLine()) != null) {
				System.out.println(linee);
			}
		} else if (flag == 1 || flag == 2) { // not to truncate the first file
			while ((linee = in1.readLine()) != null) {
				String temp = linee + System.lineSeparator();
				Files.write(Paths.get(newPath), temp.getBytes(), StandardOpenOption.APPEND);
			}
		}
		in1.close();
	}
	public  void more (String pathFile ){
		if(!pathFile.contains(":")&&!pathFile.equals("")) {
			pathFile=currentDirectory+pathFile+"\\";
		}
	File file = new File(pathFile);
	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader in = new BufferedReader(fileReader);
            String line, userInputString;            
            int i = 0;
            while((line = in.readLine())!= null)
            {
                System.out.println(line);
                i++;
                if (i == 10) 
                {
                    System.out.println("read more ? (yes -> press enter | no-> press q)");
                    Scanner UIS = new Scanner(System.in);
                    userInputString = UIS.nextLine();
                    if (UIS.hasNextLine()) 
                    {
			i=0;
                    }
                    if (userInputString.equals("q")) 
                    {
			break;
                    }
		}
            }
            fileReader.close();
        }catch (FileNotFoundException ex) {
		System.out.println(" file not found.");
        }catch (IOException ex) {
        	   System.out.println("input/output error.");
        }
    }
	public void args(String cmd, Parser as, String path, int flag) throws IOException {
		if(!path.contains(":")&&!path.equals("")) {
			path=currentDirectory+path+"\\";
		}
		if (flag == 0)
			System.out.println(cmd);
		else if (flag == 1) {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(cmd);
			writer.newLine();
			writer.close();
		} else if (flag == 2) {
			String temp = cmd + System.lineSeparator();
			Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
		}
		if (as.listOfCmd.contains(cmd)) {
			if (as.num.get(as.listOfCmd.indexOf(cmd)) == 0) {
				if (flag == 0)
					System.out.println(" Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd)) + " args ");
				else if (flag == 1 || flag == 2) {
					String temp = " Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd)) + " args "
							+ System.lineSeparator();
					Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
				}
			} else if (as.num.get(as.listOfCmd.indexOf(cmd)) == 1) {
				if (flag == 0)
					System.out.println(
							" Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd)) + " args : Destination Path");
				else if (flag == 1 || flag == 2) {
					String temp = " Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd))
							+ " args : Destination Path" + System.lineSeparator();
					Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
				}
			}
			else if (as.num.get(as.listOfCmd.indexOf(cmd)) == 2) {
				if (flag == 0)
					System.out.println(" Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd))
							+ " args : Source Path, Destination Path");
				else if (flag == 1 || flag == 2) {
					String temp = " Number of args is " + as.num.get(as.listOfCmd.indexOf(cmd))
							+ " args : Source Path, Destination Path" + System.lineSeparator();
					Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
				}
			}
		} else {
			System.out.println("Wrong Command!");
		}
	}
	public void help(String path, int flag) throws IOException {
		if(!path.contains(":")&&!path.equals("")) {
			path=currentDirectory+path+"\\";
		}
		ArrayList<String> cmd = new ArrayList<String>();
		cmd.add("help ---> This command show to user what every command do.");
		cmd.add("clear ---> removes current terminal screen.");
		cmd.add("cp ---> copy content of a file to another one.");
		cmd.add("ls ---> show the folders and files in the current directory.");
		cmd.add("pwd ---> show the current directory.");
		cmd.add("cd ---> change the current directory.");
		cmd.add("mv ---> cut and move content of a file to another one.");
		cmd.add("rm ---> delete file  you entered its path.");
		cmd.add("mkdir ---> create new folder on entered path.");
		cmd.add("rmdir ---> remove folder from entered path.");
		cmd.add("cat ---> read the content of file.");
		cmd.add("date ---> get current time and date.");
		cmd.add("more  --> This command Let us display and scroll down the output in one directiononly.You can scroll page by page or line by line.");
		cmd.add("args  --> This command List all command argument ^_^");
		cmd.add("exit  --> This command Stop all ^_^");
		cmd.add("> ---> This command Redirect the output to be written to afile using the redirect > create/replace file operator.");
		cmd.add(">>    --> This command Redirect the output to be written to afile using the redirect > create/appand to file operator.");
		cmd.add(" args ---> List all command arguments.");
                cmd.add("exit ---> Exit the Shell.");
		if (flag == 0) {
			for (int i = 0; i < cmd.size(); i++) {
				System.out.println(cmd.get(i));
			}
		} else if (flag == 1) {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < cmd.size(); i++) {
				writer.write(cmd.get(i));
				writer.newLine();
			}
			writer.close();
		} else if (flag == 2) {
			for (int i = 0; i < cmd.size(); i++) {
				String temp = cmd.get(i) + System.lineSeparator();
				Files.write(Paths.get(path), temp.getBytes(), StandardOpenOption.APPEND);
			}
		}
	}
}
