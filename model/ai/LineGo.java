package model.ai;

import model.Line;

public class LineGo implements Comparable<LineGo>{
	private int utility;//chi so heuristic cua line hien tai
	private Line line;
	
	public LineGo(int utility, Line line) {
		super();
		this.utility = utility;
		this.line = line;
	}
	public LineGo() {
	}

	/**
	 * @return the utility
	 */
	public int getUtility() {
		return utility;
	}

	/**
	 * @param utility the utility to set
	 */
	public void setUtility(int utility) {
		this.utility = utility;
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public int compareTo(LineGo o) {
		return this.utility-o.utility;
	}
	@Override
	public String toString() {
		return this.utility+"\t";
	}
	
}
