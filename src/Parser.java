import java.util.*;
public class Parser {
	public String[] args;
	public String cmd;
	ArrayList<String> listOfCmd;
	ArrayList<Integer> num;
	public int flag; 
	Parser(){
		flag=0;
		listOfCmd=new ArrayList<String>();
		num =new ArrayList<Integer>();
		listOfCmd.add("clear");num.add(0);
		listOfCmd.add("cd");num.add(1);
		listOfCmd.add("ls");num.add(0);
		listOfCmd.add("cp");num.add(2);
		listOfCmd.add("mv");num.add(2);
		listOfCmd.add("rm");num.add(1);
		listOfCmd.add("mkdir");num.add(1);
		listOfCmd.add("rmdir");num.add(1);
		listOfCmd.add("cat");num.add(2);/**num of args***/
		listOfCmd.add("more");num.add(1);
		listOfCmd.add("pwd");num.add(0);
		listOfCmd.add("help");num.add(0);
		listOfCmd.add("args");num.add(1);
		listOfCmd.add("date");num.add(0);
		listOfCmd.add("exit");num.add(0);
		args=new String[4];
		args[0]="";
		args[1]="";
		args[2]="";
		args[3]="";
	}
	public boolean parse(String input) {/**we implement | in the main*/
		String[] all=input.split(" ");
		if(all.length==1) {cmd=all[0];}
		else if(all.length==2) {cmd=all[0];args[0]=all[1];}
		else if(all.length==3) {
			cmd=all[0];
			args[0]=all[1];
			args[1]=all[2];
		}
		else if(all.length==4) {
			cmd=all[0];
			args[0]=all[1];
			args[1]=all[2];
			args[2]=all[3];
		}/**for more if it was needed*///for multi args as > 
		else if(all.length==5) {/**for cat*/
			cmd=all[0];
			args[0]=all[1];
			args[1]=all[2];
			args[2]=all[3];
			args[3]=all[4];
		}
		else {
			System.out.println("wrong in length");
			return false;
		}
	
		if(listOfCmd.contains(cmd)) {
			if(all.length-1==num.get(listOfCmd.indexOf(cmd))&&(args[0]!=">"||args[0]!=">>")&&(args[2]!=">>"||args[2]!=">>")) {
				flag=0;return true;
				}
			else if((cmd.equals("ls")||cmd.equals("pwd")||cmd.equals("date")||cmd.equals("help"))&&args[0].equals(">")&&(all.length==3)) {
				flag=1;
				return true;
			}
			else if(cmd.equals("args")&&args[1].equals(">")&&(all.length==4)) {
				flag=1;
				return true;
			}
			else if(cmd.equals("cat")&&args[2].equals(">")&&(all.length==5)) {
				flag=1;
				return true;
			}
			else if((cmd.equals("ls")||cmd.equals("pwd")||cmd.equals("date")||cmd.equals("help"))&&args[0].equals(">>")&&(all.length==3)) {
				flag=2;
				return true;
			}
			else if(cmd.equals("args")&&args[1].equals(">>")&&(all.length==4)) {
				flag=2;
				return true;
			}
			else if(cmd.equals("cat")&&args[2].equals(">>")&&(all.length==5)) {
				flag=2;
				return true;
			}
			else {System.out.println("wrong in >>");return false;}
		}
		else {System.out.println("wrong in contain");return false;}
	}
	
}
