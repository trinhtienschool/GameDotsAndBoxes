package model.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Board;
import model.Cell;
import model.Line;
import model.player.Player;

public class Alpha_beta_pruning {
	private Board board;
	private final int W_SCORE = 20;
	private final int W_THREE_LINE_FILL = 15;
	private final int W_TWO_LINE_FILL = 1;
	private final long MOVE_TIME = 1000;
	private final int MIN = Integer.MIN_VALUE;
	private final int MAX = Integer.MAX_VALUE;
	private long startTime;
	private int maxLevel;

	public Alpha_beta_pruning(Board board) {
		super();
		this.board = board;
	}
	/**
	 *	lay he so heuristic cua player ao dang choi
	 *	heuristic = W_SCORE * Score(AI) - W_SCORE * Score(OTHER)
	 *	neu nguoi dang choi la ai: 
	 *		heuristis += W_THREE_LINE_FILL * S(3LineFilledCell) -W_TWO_LINE_FILL * S(2LineFilledCell)
	 *	neu nguoi dang choi khong phai ai:
	 *		 heuristis -= W_THREE_LINE_FILL * S(3LineFilledCell) -W_TWO_LINE_FILL * S(2LineFilledCell)
	 *
	 *Trong do: W_SCORE = 20: he so diem cua nguoi choi dang co
	 *			W_THREE_LINE_FILL: he so diem cua cell co 3 line da ve
	 *			W_TWO_LINE_FILL: he so diem cua cell co 2 line da ve
	 *			Score(AI), Score(OTHER): tong so diem cua nguoi choi
	 *			S(3LineFilledCell): tong so cell co 3 line da ve
	 *			S(2LineFilledCell): tong so cell co 2 line da ve 
	 *	
	 *@param player: nguoi choi hien tai		 
	 */
	public int heuristic(Player player) {
		int value = W_SCORE * board.getScore(true) - W_SCORE * board.getScore(false);
		if(player == null) return value;
		if (player.getName().equalsIgnoreCase("ai")) {
			value += W_THREE_LINE_FILL * board.countCellByNumOfLineFill(3)
					- W_TWO_LINE_FILL * board.countCellByNumOfLineFill(2);
		} else {
			value -= W_THREE_LINE_FILL * board.countCellByNumOfLineFill(3)
					- W_TWO_LINE_FILL * board.countCellByNumOfLineFill(2);
		}
		return value;
	}
	/**
	 * Lay nuoc di tot nhat
	 * 
	 * startTime: thoi gian hien tai khi vo phuong thuc
	 * maxLevel: do sau gioi han cua alpha_beta search, do sau se tang dan cho den khi bang so line co the ve hoac het thoi gian
	 * 
	 * chuong trinh se chay maxLevel den size sau do chay vao maxValue de lay best line o do sau maxValue hien tai, 
	 * tiep tuc thuc hien cho den khi het thoi gian MOVE_TIME = 1000ms = 1s 
	 * @param playerGo: nguoi choi dang choi
	 * @param opp: doi thu
	 * @return line toi uu nhat
	 */
	public Line getNextMove(Player playerGo, Player opp) {
		startTime = System.currentTimeMillis();

		maxLevel = 1;
		Line best = null;

		int size = board.getAvailabelLines().size();
		while (maxLevel <= size) {
			LineGo lineGo = maxValue(MIN, MAX, 0, playerGo, opp);

			if (System.currentTimeMillis() - startTime < MOVE_TIME)
				best = lineGo.getLine();
			else {

				break;
			}
			maxLevel++;
		}
		return best;
	}

	/**
	 * maxValue
	 * @param alpha_value: gia tri alpha
	 * @param beta_value: gia tri beta
	 * @param level: do sau hien tai
	 * @param playerGo: nguoi choi dang choi
	 * @param opp: doi thu
	 * @return line thoa dieu kien
	 */
	public LineGo maxValue(int alpha_value, int beta_value, int level, Player playerGo, Player opp) {
		
		//do sau hien tai < do sau lon nhat & thoi gian da chay hien tai < move_time
		if (level < maxLevel && System.currentTimeMillis() - startTime < MOVE_TIME) {

			//lay ra available lineGo: line va trong so heuristic
			//lineGos: da duoc sap xep tang dan theo trong so heuristic 
			List<LineGo> lineGos = getAvailableLineGo(playerGo, opp);

			int size = lineGos.size();

			//neu khong con available lineGo: tra ve lineGo: line=null va heuristic cua state hien tai
			if (size == 0)
				return new LineGo(heuristic(playerGo), null);
			List<Line> moves = new ArrayList<Line>();
			
			//lay ra line trong lineGos them vao moves
			//Sap xep moves theo he so heuristic giam dan
			for (int i = size - 1; i >= 0; i--) {
				moves.add(lineGos.get(i).getLine());
			}
			
			//lineGo hien tai se duoc tra ve
			LineGo lineGo = new LineGo(MIN, null);
			for (int i = 0; i < size; i++) {
				
				//tao ra state moi bang cach ve line = moves.get(i), gan player cho line duoc ve la player dang choi
				boolean isDrawCell = aiDrawLine(moves.get(i), playerGo);
				
				//lineGo child
				LineGo childLineGo;

				//neu trang thai hien tai cua line da ve co 1 o moi duoc ve: player dang choi se di tiep maxValue
				//nguoc lai player doi thu se di minValue
				if (isDrawCell) {
					childLineGo = maxValue(alpha_value, beta_value, level + 1, playerGo, opp);
				} else {
					childLineGo = minValue(alpha_value, beta_value, level + 1, opp, playerGo);
				}
				
				//lay he so heuristic cua lineGo child
				int childUtility = childLineGo.getUtility();
				
				//neu he so lineGo hien tai < he so lineGo child : gan lineGo hien tai = lineGo child
				if (lineGo.getUtility() < childUtility) {
					lineGo.setUtility(childUtility);
					lineGo.setLine(moves.get(i));
				}
				
				//xoa line da ve
				aiUnDrawLine(moves.get(i));
				
				//neu luot di tiep theo la cua doi thu va he so heuristic cua lineGo child > = beta_value ---> cat tia
				if (!isDrawCell && childUtility >= beta_value)
					return lineGo;
				
				// gan lai gia tri moi cho alpha_value
				alpha_value = Math.max(alpha_value, lineGo.getUtility());
			}
			return lineGo;
		}
		// nguoi lai tra ve lineGo cua trang thai hien tai
		else
			return new LineGo(heuristic(playerGo), null);

	}

	public LineGo minValue(int alpha_value, int beta_value, int level, Player playerGo, Player opp) {
		if (level < maxLevel && System.currentTimeMillis() - startTime < MOVE_TIME) {
			List<LineGo> lineGos = getAvailableLineGo(playerGo, opp);
			int size = lineGos.size();

			if (size == 0)
				return new LineGo(heuristic(playerGo), null);

			List<Line> moves = new ArrayList<Line>();
			
			//lay ra line trong lineGos them vao moves
			//Sap xep moves theo he so heuristic tang dan
			for (int i = 0; i < size; i++)
				moves.add(lineGos.get(i).getLine());

			LineGo lineGo = new LineGo(MAX, null);
			for (int i = 0; i < size; i++) {
				boolean isDrawCell = aiDrawLine(moves.get(i), playerGo);
				LineGo childLineGo;

				if (isDrawCell) {
					childLineGo = minValue(alpha_value, beta_value, level + 1, playerGo, opp);
				} else {
					childLineGo = maxValue(alpha_value, beta_value, level + 1, opp, playerGo);
				}
				int childUtility = childLineGo.getUtility();
				if (lineGo.getUtility() > childUtility) {
					lineGo.setUtility(childUtility);
					lineGo.setLine(moves.get(i));
				}
				aiUnDrawLine(moves.get(i));
				
				//neu luot di tiep theo la cua doi thu va he so heuristic cua lineGo child < = alpha_value ---> cat tia
				if (!isDrawCell && childUtility <= alpha_value)
					return lineGo;
				beta_value = Math.min(beta_value, lineGo.getUtility());

			}
			return lineGo;
		} else
			return new LineGo(heuristic(playerGo), null);
	}

	private List<LineGo> getAvailableLineGo(Player playerGo, Player opp) {
		List<Line> moves = board.getAvailabelLines();
		List<LineGo> lineGos = new ArrayList<LineGo>();
		for (int i = 0; i < moves.size(); i++) {
			
			//tao ra state bang cach ve them line= moves.get(i)
			boolean isDrawCell = aiDrawLine(moves.get(i), playerGo);
			
			//neu trang thai hien tai co 1 cell duoc ve, luot choi thuoc nguoi choi hien tai
			//nguoc lai luot choi thuoc doi thu
			LineGo line = new LineGo(heuristic(isDrawCell ? playerGo : opp), moves.get(i));
			lineGos.add(line);
			
			//xoa line da ve de tra ve state ban dau
			aiUnDrawLine(moves.get(i));
		}
		
		//sap xep lineGos theo thu tu tang dan he so heuristic
		Collections.sort(lineGos);
		return lineGos;
	}

	private boolean aiDrawLine(Line line, Player playerGo) {
		
		//lay ra row, col cua cell chua line
		int rowLine = line.getRow();
		int colLine = line.getCol();
		
		//ve line
		line.draw(playerGo);
		boolean isDrawCell = false;
		
		//duyet cach cell xung quanh cell chua line hien tai, xem con cell nao chua line khong
		for (int row = rowLine - 1; row <= rowLine + 1; row++) {
			for (int col = colLine - 1; col <= colLine + 1; col++) {
				if (row < 0 || row >= board.getRows())
					continue;
				if (col < 0 || col >= board.getCols())
					continue;
				Cell cell = board.getCell(row, col);
				
				//co cell nao duoc ve sau het cac line sau khi ve line hien tai khong 
				if (cell.draw(playerGo) == Cell.CELLDREW) {
					isDrawCell = true;
				}
			}
		}
		return isDrawCell;
	}

	private void aiUnDrawLine(Line line) {
		int rowLine = line.getRow();
		int colLine = line.getCol();
		
		//xoa da ve trong line
		line.unDraw();
		for (int row = rowLine - 1; row <= rowLine + 1; row++) {
			for (int col = colLine - 1; col <= colLine + 1; col++) {
				if (row < 0 || row >= board.getRows())
					continue;
				if (col < 0 || col >= board.getCols())
					continue;
				Cell cell = board.getCell(row, col);
				
				//neu line da xoa ve thuoc ve cell thi xoa da ve trong cell
				cell.unDraw();
			}
		}

	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

}
