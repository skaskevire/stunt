package states;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import entity.Entity;
import entity.Ground;
import entity.Truck;

import handlers.GameStateManager;
import stunt.Globals;

public class Play extends GameState{
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	private float tileSize;
	
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private Map<String, Entity> entities;


	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0,-1.8f), true);
		b2dr = new Box2DDebugRenderer();

		
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Globals.V_WIDTH / Globals.PPM, Globals.V_HEIGHT / Globals.PPM );
		
		entities.put("ground", new Ground(world));
		entities.put("truck", new Truck(world, b2dCam));		
		
		//////////// < tiled staff >
		
		tileMap = new TmxMapLoader().load("res/maps/test.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("main");
		tileSize = layer.getTileWidth();
		
		///////////  </ tiled staff >
	}	

	@Override
	public void handleInput() {
		for(Entity entity: entities.values())
		{
			entity.update(0f);
		}
	}

	@Override
	public void update(float dt) {
		

		handleInput();
		world.step(dt, 6, 2);
		
		
		sb.setProjectionMatrix(b2dCam.combined);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tmr.setView(b2dCam);
		tmr.render();		
		
		for(Entity entity: entities.values())
		{
			entity.render(sb);
		}
		
		b2dr.render(world,b2dCam.combined);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
