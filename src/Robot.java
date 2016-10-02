/*
 * Ќа игровом поле находитс€ робот. ѕозици€ робота на поле описываетс€ двум€ 
 * целочисленным координатами: X и Y. ќсь X смотрит слева направо, ось Y Ч снизу вверх.
 * ¬ начальный момент робот находитс€ в некоторой позиции на поле. 
 * “акже известно, куда робот смотрит: вверх, вниз, направо или налево. 
 * ¬аша задача Ч привести робота в заданную точку игрового пол€.
 * 
 * ѕример
 * ¬ метод передано: toX == 3, toY == 0; начальное состо€ние робота такое: 
 * robot.getX() == 0, robot.getY() == 0, robot.getDirection() == Direction.UP
 * „тобы привести робота в указанную точку (3, 0), метод должен вызвать у 
 * робота следующие методы:
 * robot.turnRight();
 * robot.stepForward();
 * robot.stepForward();
 * robot.stepForward();
 */
public class Robot {

	private int x;
	private int y;
	private Direction direction;
	
	public enum Direction {
	    UP,
	    DOWN,
	    LEFT,
	    RIGHT
	}
 
	public Robot(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
 
	public Direction getDirection() {
		return direction;
	}
 
	public int getX() {
		return x;
	}
 
	public int getY() {
		return y;
	}
 
	public void turnLeft() {
		switch (direction) {
		case DOWN:
			System.out.println("turning left");
			direction = Direction.RIGHT;
			break;
		case LEFT:
			System.out.println("turning left");
			direction = Direction.DOWN;
			break;
		case RIGHT:
			System.out.println("turning left");
			direction = Direction.UP;
			break;
		case UP:
			System.out.println("turning left");
			direction = Direction.LEFT;
			break;
		default:
			break;
		}
	}
 
	public void turnRight() {
		switch (direction) {
		case DOWN:
			System.out.println("turning right");
			direction = Direction.LEFT;
			break;
		case LEFT:
			System.out.println("turning right");
			direction = Direction.UP;
			break;
		case RIGHT:
			System.out.println("turning right");
			direction = Direction.DOWN;
			break;
		case UP:
			System.out.println("turning right");
			direction = Direction.RIGHT;
			break;
		default:
			break;
		}
	}
 
	public void stepForward() {
		switch (direction) {
		case DOWN:
			--y;
			System.out.println("moving down");
			break;
		case LEFT:
			--x;
			System.out.println("moving left");
			break;
		case RIGHT:
			++x;
			System.out.println("moving right");
			break;
		case UP:
			System.out.println("moving up");
			++y;
			break;
		default:
			break;
		}
	}
    
	public static void moveRobot(Robot robot, int toX, int toY) {
		
		Direction directionX, directionY;
	    int currentPosX = robot.getX();
	    int currentPosY = robot.getY();

	    if ((toX - currentPosX) >= 0) directionX = Direction.RIGHT;
	    else                          directionX = Direction.LEFT;
	    
	    if ((toX - currentPosX) != 0) {
		    while (robot.getDirection() != directionX) {
		        robot.turnRight();
		    }
		    
		    while ((toX - robot.getX()) != 0) {
		    	robot.stepForward();
		    }
	    }
	    
	    if ((toY - currentPosY) >= 0) directionY = Direction.UP;
	    else                          directionY = Direction.DOWN;
	    
	    if ((toY - currentPosY) != 0) {
		    if (robot.getDirection() != directionY) {
		        robot.turnRight();
		    }
		    
		    if ((toY - robot.getY()) != 0) {
		    	robot.stepForward();
		    }
	    }
	}
	
	public static void main(String[] args) {
		Robot robot = new Robot(0, 0, Direction.UP);
		moveRobot(robot, 3, 0);

	}

}
