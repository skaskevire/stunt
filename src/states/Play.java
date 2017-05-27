package states;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.values.LineSpawnShapeValue;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import handlers.GameStateManager;
import handlers.MyInput;
import stunt.Game;
import stunt.Globals;

public class Play extends GameState{
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	private float tileSize;
	
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	
	private Body playerBody;
	private Body playerBody2;
	private Body playerBody3;
	private Body playerBody4;
	private Body playerBody5;
	private Body playerBody6;
	private BitmapFont font = new BitmapFont();
	
	
	

	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0,-1.8f), true);
		b2dr = new Box2DDebugRenderer();		
		
		
		Texture box25tex = Game.res.getTexture("box25");

		createTerrainCurve();
		
		createTruck();
		
		
		
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Globals.V_WIDTH / Globals.PPM, Globals.V_HEIGHT / Globals.PPM );
		
		createObstacles();
		//static body - don't move, unaffected by forces - ground		
		//kinematic - don't get affected by forces ( moving platform )		
		//dynamic body - always ged affected by forces ( player )	
		
		
		
		
		//////////// < tiled staff >
		
		tileMap = new TmxMapLoader().load("res/maps/test.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("main");
		tileSize = layer.getTileWidth();
		
		///////////  </ tiled staff >
		

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
			Body tr= rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
		
		
		
		 tLength = 1f;
		 twidth = 15f;
		 track = new Body[228];
		 length = (float) (634  / Globals.PPM);
		 delta = 8 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
		
		
		
		
		 tLength = 15f;
		 twidth = 15f;
		 track = new Body[28];
		 length = (float) (1234  / Globals.PPM);
		 delta = 20 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
		
		
		
		
		
		 tLength = 25f;
		 twidth = 25f;
		 track = new Body[28];
		 length = (float) (1434  / Globals.PPM);
		 delta = 27 / Globals.PPM ;
		
		
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= rectangularBody(80f,world, length,146 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}
	}
	
	
	private void createTruck()
	{
		Body head = rectangularBody(10f,world, 165 / Globals.PPM,218 / Globals.PPM,20 / Globals.PPM, 10 / Globals.PPM);	
		
		Body body = truckBody(10f,world, 160 / Globals.PPM,120 / Globals.PPM,33 / Globals.PPM, 3 / Globals.PPM);	

		float tLength = 4f;
		float twidth = 1f;
		Body[] track = new Body[11];
		float length = (float) (101  / Globals.PPM);
		float delta = (float) (tLength/Globals.PPM)*2;	
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= rectangularBody(10f,world, length,103 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}	
		for (int i = 0; i < track.length; i++) {
			if(i!= track.length-1)
			{
				revoluteJoint(world, track[i], track[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		
		
		
		 tLength = 4f;
		 twidth = 1f;
		Body[] trackttt = new Body[4];
		 length = (float) (190  / Globals.PPM);
		 delta = (float) (tLength/Globals.PPM)*2;	
		for (int i = 0; i < trackttt.length; i++) {
			length = length + delta;
			Body tr= rectangularBody(10f,world, length,114 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			trackttt[i] = tr;
		}	
		for (int i = 0; i < trackttt.length; i++) {
			if(i!= trackttt.length-1)
			{
				revoluteJoint(world, trackttt[i], trackttt[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		revoluteJoint(world, track[track.length-1], trackttt[0], tLength/Globals.PPM, -twidth/Globals.PPM, -tLength/Globals.PPM, -twidth/Globals.PPM);

		
		
	
		
		
		 tLength = 4f;
		 twidth = 1f;
		Body[] trackl = new Body[14];
		 length = (float) (101  / Globals.PPM);
		 delta = (float) (tLength/Globals.PPM)*2;
		
		
		for (int i = 0; i < trackl.length; i++) {
			length = length + delta;
			Body tr= rectangularBody(10f,world, length,89 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			trackl[i] = tr;
		}
		
		for (int i = 0; i < trackl.length; i++) {
			if(i!= trackl.length-1)
			{
				revoluteJoint(world, trackl[i], trackl[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		revoluteJoint(world, trackl[0], track[0], -tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, -twidth/Globals.PPM);
		revoluteJoint(world, trackttt[trackttt.length-1], trackl[trackl.length-1], tLength/Globals.PPM, -twidth/Globals.PPM, tLength/Globals.PPM, twidth/Globals.PPM);
			

		
		
		
		
		
		Body d1 = rectangularBody(30f,world, 110 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d2 = rectangularBody(30f,world, 130 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d3 = rectangularBody(30f,world, 150 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d4 = rectangularBody(30f,world, 170 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d5 = rectangularBody(40f,world, 190 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d6 = rectangularBody(30f, world, 210 / Globals.PPM,120 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		
		playerBody = circularBody(world, 110 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM);
		playerBody2 = circularBody(world, 130 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM);
		playerBody3 = circularBody(world, 150 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM);
		playerBody4 = circularBody(world, 170 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM);
		playerBody5 = circularBody(world, 190 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM);
		playerBody6 = circularBody(world, 210 / Globals.PPM, 110 / Globals.PPM, 5 / Globals.PPM);
		
		vzhJoint(world, body, d1, -50/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		vzhJoint(world, body, d2, -30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		vzhJoint(world, body, d3, -10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		vzhJoint(world, body, d4, 10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		vzhJoint(world, body, d5, 30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		vzhJoint(world, body, d6, 50/Globals.PPM,10/Globals.PPM, 0,12/Globals.PPM);	
		
		distanceJoint(0.6f, world, body, d1, -50/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		distanceJoint(0.6f,world, body, d2, -30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);		
		distanceJoint(0.6f,world, body, d3, -10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		distanceJoint(0.6f,world, body, d4, 10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		distanceJoint(1f,world, body, d5, 30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		distanceJoint(0.9f,world, body, d6, 50/Globals.PPM,10/Globals.PPM, 0,12/Globals.PPM);
		
		revoluteJoint(world, playerBody, d1, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		revoluteJoint(world, playerBody2, d2, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		revoluteJoint(world, playerBody3, d3, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		revoluteJoint(world, playerBody4, d4, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		revoluteJoint(world, playerBody5, d5, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		revoluteJoint(world, playerBody6, d6, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
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
	private Body rectangularBody(float density, World world, float x, float y, float width, float height)
	{
		PolygonShape psh = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		BodyDef bdef = new BodyDef();		
		bdef.position.set(x, y);
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);		
		psh.setAsBox(width, height);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = density;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		return body;
	}
	
	//	Body head = rectangularBody(1f,world, 195 / Globals.PPM,138 / Globals.PPM,20 / Globals.PPM, 15 / Globals.PPM);	

	private Body truckBody(float density, World world, float x, float y, float width, float height)
	{
		PolygonShape psh = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		BodyDef bdef = new BodyDef();		
		bdef.position.set(x, y);
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);		
		psh.setAsBox(width, height);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = density;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		
		//kabina
		fdef = new FixtureDef();
		psh = new PolygonShape();		
		psh.setAsBox(20 / Globals.PPM, 15 / Globals.PPM,new Vector2(33 / Globals.PPM,18 / Globals.PPM),0f);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 0.001f;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		
		
		fdef = new FixtureDef();
		psh = new PolygonShape();		
		psh.setAsBox(8 / Globals.PPM, 6 / Globals.PPM,new Vector2(42 / Globals.PPM,24 / Globals.PPM),0f);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 0.001f;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		fdef = new FixtureDef();
		psh = new PolygonShape();		
		psh.setAsBox(2 / Globals.PPM, 6 / Globals.PPM,new Vector2(51 / Globals.PPM,24 / Globals.PPM),0f);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 0.001f;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		fdef = new FixtureDef();
		psh = new PolygonShape();		
		psh.setAsBox(2 / Globals.PPM, 2 / Globals.PPM,new Vector2(54 / Globals.PPM,6 / Globals.PPM),0f);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 0.001f;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		
		
		fdef = new FixtureDef();
		psh = new PolygonShape();		
		psh.setAsBox(34 / Globals.PPM, 2 / Globals.PPM,new Vector2(-20 / Globals.PPM,6 / Globals.PPM),0f);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 0.001f;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		

		
		
		
		
		
		
		
		

		
		
		fdef = new FixtureDef();
		psh = new PolygonShape();		
		psh.setAsBox(1 / Globals.PPM, 10 / Globals.PPM,new Vector2(-55 / Globals.PPM,3 / Globals.PPM),3.14f);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 0.001f;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		
		
		fdef = new FixtureDef();
		psh = new PolygonShape();		
		psh.setAsBox(1 / Globals.PPM, 5 / Globals.PPM,new Vector2(57 / Globals.PPM,-2 / Globals.PPM),0.6f);
		fdef.shape = psh;	
		fdef.restitution = 0.4f;
		fdef.density = 0.001f;
		fdef.friction = 0.8f;
		body.createFixture(fdef);
		

		
		
		return body;
	}
	
	
	
	private PrismaticJoint vzhJoint(World world, Body a, Body b, float lax, float lay, float lbx, float lby)
	{
		
		com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef revoluteJointDef2 = new PrismaticJointDef();
		revoluteJointDef2.lowerTranslation = 0f/Globals.PPM;
		revoluteJointDef2.upperTranslation = 8f/Globals.PPM;
		//revoluteJointDef2.referenceAngle = 0f;
		revoluteJointDef2.enableLimit=true;
		revoluteJointDef2.collideConnected = false;
	//	revoluteJointDef2.enableMotor=true;
		//revoluteJointDef2.maxMotorForce=-3;
		//revoluteJointDef2.motorSpeed = -0.1f;
		revoluteJointDef2.localAxisA.add(0, 12);
		  revoluteJointDef2.bodyA = a;
		  revoluteJointDef2.bodyB = b;
		  revoluteJointDef2.localAnchorA.set(lax, lay);//the top right corner of the box
		  revoluteJointDef2.localAnchorB.set(lbx, lby);//center of the circle


		  return (PrismaticJoint)world.createJoint( revoluteJointDef2);
	}
	
	
	private DistanceJoint distanceJoint(float dr, World world, Body a, Body b, float lax, float lay, float lbx, float lby)
	{
		
		com.badlogic.gdx.physics.box2d.joints.DistanceJointDef revoluteJointDef2 = new DistanceJointDef();
		revoluteJointDef2.length = 0/ Globals.PPM;
		revoluteJointDef2.dampingRatio = dr;
		revoluteJointDef2.frequencyHz = 4f;
		  revoluteJointDef2.bodyA = a;
		  revoluteJointDef2.bodyB = b;
		  revoluteJointDef2.collideConnected = false;
		  revoluteJointDef2.localAnchorA.set(lax, lay);//the top right corner of the box
		  revoluteJointDef2.localAnchorB.set(lbx, lby);//center of the circle


		  return (DistanceJoint)world.createJoint( revoluteJointDef2);
	}
	
	
	
	
	private RevoluteJoint revoluteJoint(World world, Body a, Body b, float lax, float lay, float lbx, float lby)
	{
		RevoluteJointDef revoluteJointDef2 = new RevoluteJointDef();
		  revoluteJointDef2.bodyA = a;
		  revoluteJointDef2.bodyB = b;
		  revoluteJointDef2.collideConnected = false;
		  revoluteJointDef2.localAnchorA.set(lax, lay);//the top right corner of the box
		  revoluteJointDef2.localAnchorB.set(lbx, lby);//center of the circle
		  return (RevoluteJoint)world.createJoint( revoluteJointDef2);
	}
	
	private Body circularBody(World world, float x, float y, float radius)
	{
		CircleShape cshape = new CircleShape();	
		cshape.setRadius(radius);
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y);		
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = cshape;	
		fdef.restitution = 0.35f;
		fdef.density = 10f;
		fdef.friction = 10;
		body.createFixture(fdef);
		
		return body;
	}

	@Override
	public void handleInput() {
		float force = 0.0007f;
		if(MyInput.isDown(MyInput.BUTTON1))
		{
			playerBody.applyAngularImpulse(force, true);
			playerBody2.applyAngularImpulse(force, true);
			playerBody3.applyAngularImpulse(force, true);
			playerBody4.applyAngularImpulse(force, true);
			playerBody5.applyAngularImpulse(force, true);
			playerBody6.applyAngularImpulse(force, true);
		//	playerBody.applyForce(new Vector2(0,force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody2.applyForce(new Vector2(0,force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody3.applyForce(new Vector2(0,force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody4.applyForce(new Vector2(0,force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody5.applyForce(new Vector2(0,force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody6.applyForce(new Vector2(0,force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);

		
		}
		if(MyInput.isDown(MyInput.BUTTON2))
		{
			playerBody.applyAngularImpulse(-force, true);
			playerBody2.applyAngularImpulse(-force, true);
			playerBody3.applyAngularImpulse(-force, true);
			playerBody4.applyAngularImpulse(-force, true);
			playerBody5.applyAngularImpulse(-force, true);
			playerBody6.applyAngularImpulse(-force, true);
		//	playerBody.applyForce(new Vector2(0,-force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody2.applyForce(new Vector2(0,-force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody3.applyForce(new Vector2(0,-force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody4.applyForce(new Vector2(0,-force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody5.applyForce(new Vector2(0,-force / Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);
		//	playerBody6.applyForce(new Vector2(0,-force/ Globals.PPM), new Vector2(2.5f / Globals.PPM,2.5f / Globals.PPM), true);

		}
		System.out.println(playerBody.getAngularVelocity());
	}

	@Override
	public void update(float dt) {
		

		handleInput();
		world.step(dt, 6, 2);
		
		
		sb.setProjectionMatrix(b2dCam.combined);
	}

	@Override
	public void render() {
		b2dCam.position.set(new Vector3(playerBody3.getPosition().x, playerBody3.getPosition().y, 0));
		b2dCam.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tmr.setView(b2dCam);
		tmr.render();
		
		
		
		
		sb.begin();
		//sb.draw(Game.res.getTexture("wheel10"), playerBody.getPosition().x - 5/Globals.PPM, playerBody.getPosition().y- 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM);
		sb.draw(new TextureRegion(Game.res.getTexture("wheel10")), playerBody.getPosition().x- 5/Globals.PPM, playerBody.getPosition().y- 5/Globals.PPM, 5/Globals.PPM, 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM, 1, 1 , playerBody.getAngle() * MathUtils.radiansToDegrees, false);
		sb.draw(new TextureRegion(Game.res.getTexture("wheel10")), playerBody2.getPosition().x- 5/Globals.PPM, playerBody2.getPosition().y- 5/Globals.PPM, 5/Globals.PPM, 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM, 1, 1 , playerBody2.getAngle() * MathUtils.radiansToDegrees, false);

		sb.draw(new TextureRegion(Game.res.getTexture("wheel10")), playerBody3.getPosition().x- 5/Globals.PPM, playerBody3.getPosition().y- 5/Globals.PPM, 5/Globals.PPM, 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM, 1, 1 , playerBody3.getAngle() * MathUtils.radiansToDegrees, false);

		sb.draw(new TextureRegion(Game.res.getTexture("wheel10")), playerBody4.getPosition().x- 5/Globals.PPM, playerBody4.getPosition().y- 5/Globals.PPM, 5/Globals.PPM, 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM, 1, 1 , playerBody4.getAngle() * MathUtils.radiansToDegrees, false);
		sb.draw(new TextureRegion(Game.res.getTexture("wheel10")), playerBody5.getPosition().x- 5/Globals.PPM, playerBody5.getPosition().y- 5/Globals.PPM, 5/Globals.PPM, 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM, 1, 1 , playerBody5.getAngle() * MathUtils.radiansToDegrees, false);
		sb.draw(new TextureRegion(Game.res.getTexture("wheel10")), playerBody6.getPosition().x- 5/Globals.PPM, playerBody6.getPosition().y- 5/Globals.PPM, 5/Globals.PPM, 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM, 1, 1 , playerBody6.getAngle() * MathUtils.radiansToDegrees, false);

		sb.end();
		
		
		
		
		
		b2dr.render(world,b2dCam.combined);
		//sb.setProjectionMatrix(cam.combined);
		//sb.begin();
		//font.draw(sb, "play state", 100, 100);
		//sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
