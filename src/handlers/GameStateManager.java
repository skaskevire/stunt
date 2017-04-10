package handlers;

import java.util.Stack;

import states.GameState;
import states.Play;
import stunt.Game;
import stunt.Globals;

public class GameStateManager {
	private Game game;
	private Stack<GameState> gameStates;
	
	public GameStateManager(Game game)
	{
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(Globals.PLAY_GS);
		
	}
	
	public Game getGame() {
		return game;
	}

	public void update(float dt)
	{
		gameStates.peek().update(dt);
	}
	
	public void render()
	{
		gameStates.peek().render();
	}
	public GameState getState(int state)
	{
		if(state == Globals.PLAY_GS)
		{
			return new Play(this);
		}
		return null;
	}
	
	public void setState(int state)
	{
		popState();
		pushState(state);
	}
	
	public void pushState(int state)
	{
		gameStates.push(getState(state));
	}
	
	public void popState()
	{
		GameState g = gameStates.pop();
		g.dispose();
	}
}
