package View;

import Controller.Driver;

public class GameTypeButtonHandler{
	private String gameType;
	
	public GameTypeButtonHandler(String gameType)
	{
		this.setGameType(gameType);
	}
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	public boolean handle() {
		boolean bResult = 
				Driver.getInstance().processByUserInput(OzlympicGameView.SELECT_GAME, 
														gameType);
		//test
		System.out.println("result :" + bResult);
		return bResult;
	}
}