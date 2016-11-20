package production;

import java.util.ArrayList;
public class RobotScheduler {
	//Arraylist of robots and boolean for charging station to see if it's open open, coordinates to be determined.
	ArrayList<Robot> robots = new ArrayList();
	boolean chargingstationopen = true;
	//Add another array list of arrays to contain the points for the line to the charging station
	
	//@author: Alex Wang
	//Add robots, to be changed later w/Floor
	public RobotScheduler(){
		robots.add(new Robot(1,5,5));
		robots.add(new Robot(2,8,8));
		robots.add(new Robot(3,11,11));
	}
	
	//@author: Alex Wang
	//Select the first idle robot to carry out the order. 
	public void carryOrder(int x, int y){
		int selectrobot = 0;
		for(int i=0; i<robots.size(); i++){
			if(robots.get(i).getStatus() != "none"){
				selectrobot=i;
			}
		}
		int[] destination = new int[2];
		destination[0]=x;
		destination[0]=y;
		//this.requestRoute(robots.get(i), destination);
	}
	
	//@author: Alex Wang
	//Checks the status of all robots to see if they're done with their tasks. If they are, assign the next task in logical order
	public void checkRobots(){
		for(int i=0; i<robots.size(); i++){
			String status = robots.get(i).getStatus();
			if(status=="wait-toStation"){
				robots.get(i).setTask("toStation");
				//Use requestRoute to get a route from shelf area to picker
			} else if (status=="wait-returnShelf"){
				robots.get(i).setTask("returnShelf");
				//Use requestRoute to get a route from picker to shelf area
			} else if (status=="wait-toCharge"){
				robots.get(i).setTask("toCharge");
				//Use requestRoute to get a route from shelf area to charging area
			} else if (status=="wait-toHome"){
				robots.get(i).setTask("toHome");
				//Use requestRoute to get route from current area to home
			}
		}
	}
	
	//@author: Alex Wang
	//Sends a route request to the Floor in the form of [x start, y start, x destination, y destination].
	public int[] requestRoute(Robot r, int[] destination){
		int[] pathinfo = new int[4];
		pathinfo[0]=r.getX();
		pathinfo[1]=r.getY();
		pathinfo[2]=destination[0];
		pathinfo[3]=destination[3];
		return pathinfo;
	}
	
	//@author: Alex Wang
	public void tick(){
		this.checkRobots();
	}

}

