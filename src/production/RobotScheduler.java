package production;

import java.util.ArrayList;
public class RobotScheduler {
	//Arraylist of robots and boolean for charging station to see if it's open
	ArrayList<Robot> robots = new ArrayList();
	boolean chargingStationOpen = true;
	Floor F;
	
	//@author: Alex Wang
	//Add robots, to be changed later w/Floor
	public RobotScheduler(){
//		robots.add(new Robot(1,Constants.ROBOT_X[0],Constants.ROBOT_Y[0]));
//		robots.add(new Robot(2,Constants.ROBOT_X[1],Constants.ROBOT_Y[1]));
		this.F = Production.getMaster().getMasterFloor();
	}
	
	/**
	 * @author scott hoefer
	 * @param A shelf, s, which the robot will then retrieve
	 */
	
    public void requestShelf(Shelf s) { 
    	System.out.println("****Robot Scheduler Requesting Shelf");
		Cell target = s.getHome(); // where Shelf sits
		Robot robot = findRobot(); // get some idle robot
		robot.route = F.getPath(robot.currentLocation,target);
		robot.task = "toShelf";
		robot.carrying = s;
	    };
	
	/**
	 * @author scott hoefer
	 */
	public Robot findRobot() {
		for (int i=0; i < robots.size(); i++) {
			if (robots.get(i).task == "idle" ) {
				return robots.get(i);
			}
		}
		return null;
	}
	
	/**
	 * @author scott hoefer
	 * moveRobots just gets called every tick and then calls tick() for each non-idle robot
	 */
	public void moveRobots() {
		for (Robot r : this.robots) {
			r.tick();
		}
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
        
        
        public void setRobotPath(Path P) {
            Production.getMaster().getMasterFloor().getRobot().setNextPath(P);
        }
        
        public void setRobotPath(Cell C) {
            Path P = new Path(Production.getMaster().getMasterFloor().getRobot(),C);
            Production.getMaster().getMasterFloor().getRobot().setNextPath(P);
        }

}