package entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import stunt.Globals;
import util.BodyCreationUtils;

public class Ground implements Entity {
	private World world;
	
	public Ground(World world)
	{
		this.world = world;
		createTerrainCurve();
		createObstacles();
	}
	
	private Vector2[] createTerrainCurve()
	{
		Vector2[] vectorArray = new Vector2[12];
		vectorArray[0] = new Vector2(0,30);
		vectorArray[1] = new Vector2(100/ Globals.PPM,60/ Globals.PPM);
		vectorArray[2] = new Vector2(110/ Globals.PPM,70/ Globals.PPM);
		vectorArray[3] = new Vector2(200/ Globals.PPM,40/ Globals.PPM);
		vectorArray[4] = new Vector2(220/ Globals.PPM,50/ Globals.PPM);
		vectorArray[5] = new Vector2(620/ Globals.PPM,50/ Globals.PPM);
		vectorArray[6] = new Vector2(720/ Globals.PPM,100/ Globals.PPM);
		vectorArray[7] = new Vector2(720/ Globals.PPM,50/ Globals.PPM);
		vectorArray[8] = new Vector2(900/ Globals.PPM,40/ Globals.PPM);
		vectorArray[9] = new Vector2(1200/ Globals.PPM,0/ Globals.PPM);
		vectorArray[10] = new Vector2(5000/Globals.PPM,0/ Globals.PPM);
		vectorArray[11] = new Vector2(5000/Globals.PPM,1000/ Globals.PPM);	

		
		createTerrain(world, 0,-40 / Globals.PPM, vectorArray);
		
		return vectorArray;
	}
	
	
	private Body createTerrain(World world, float x, float y, Vector2[] vectorArray)
	{
		ChainShape shape = new ChainShape();
		shape.createChain(vectorArray);
		FixtureDef fdef = new FixtureDef();
		BodyDef bdef = new BodyDef();
		bdef.position.set(x,y);
		bdef.type = BodyType.StaticBody;
		
		Body body = world.createBody(bdef);
		fdef.shape = shape;
		fdef.density = 1;
		fdef.friction = 1.9f;
		body.createFixture(fdef);
		
		return body;
	}
	
	private void createObstacles()
	{
		
		float tLength = 4f;
		float twidth = 1f;
		Body[] track = new Body[11];
		float length = (float) (101  / Globals.PPM);
		float delta = (float) (tLength/Globals.PPM)*2;	
		
		
		
		
		 tLength = 5f;
		 twidth = 5f;
		 track = new Body[128];
		 length = (float) (634  / Globals.PPM);
		 delta = 8 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
		
		
		
		 tLength = 1f;
		 twidth = 15f;
		 track = new Body[228];
		 length = (float) (634  / Globals.PPM);
		 delta = 8 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
		
		
		
		
		 tLength = 15f;
		 twidth = 15f;
		 track = new Body[28];
		 length = (float) (1234  / Globals.PPM);
		 delta = 20 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
		
		
		
		
		
		 tLength = 25f;
		 twidth = 25f;
		 track = new Body[28];
		 length = (float) (1434  / Globals.PPM);
		 delta = 27 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
	}
	
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
