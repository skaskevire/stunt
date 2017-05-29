package entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import handlers.MyInput;
import stunt.Game;
import stunt.Globals;
import util.BodyCreationUtils;

public class Truck implements Entity{

	private World world;
	

	private List<Body> rollers;
	private Body cargo;
	private Body truckBody;
	
	
	public Truck(World world)
	{
		this.world = world;
		rollers = new ArrayList<Body>();
		
		cargo = BodyCreationUtils.rectangularBody(10f,world, 165 / Globals.PPM,218 / Globals.PPM,20 / Globals.PPM, 10 / Globals.PPM);	
		truckBody = createTruckBody(10f, world, 160 / Globals.PPM,120 / Globals.PPM,33 / Globals.PPM, 3 / Globals.PPM);
	
		createTracks();
		createRollers();
		attachWheels(truckBody);
		
	}
	
	private void attachWheels(Body body)
	{
		Body d1 = BodyCreationUtils.rectangularBody(30f,world, 110 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d2 = BodyCreationUtils.rectangularBody(30f,world, 130 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d3 = BodyCreationUtils.rectangularBody(30f,world, 150 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d4 = BodyCreationUtils.rectangularBody(30f,world, 170 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d5 = BodyCreationUtils.rectangularBody(40f,world, 190 / Globals.PPM,110 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);	
		Body d6 = BodyCreationUtils.rectangularBody(30f, world, 210 / Globals.PPM,120 / Globals.PPM,2 / Globals.PPM, 4 / Globals.PPM);
		
		BodyCreationUtils.vzhJoint(world, body, d1, -50/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.vzhJoint(world, body, d2, -30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		BodyCreationUtils.vzhJoint(world, body, d3, -10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.vzhJoint(world, body, d4, 10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);	
		BodyCreationUtils.vzhJoint(world, body, d5, 30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.vzhJoint(world, body, d6, 50/Globals.PPM,10/Globals.PPM, 0,12/Globals.PPM);	
		
		BodyCreationUtils.distanceJoint(0.6f, world, body, d1, -50/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(0.6f,world, body, d2, -30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);		
		BodyCreationUtils.distanceJoint(0.6f,world, body, d3, -10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(0.6f,world, body, d4, 10/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(1f,world, body, d5, 30/Globals.PPM,-0/Globals.PPM, 0,12/Globals.PPM);
		BodyCreationUtils.distanceJoint(0.9f,world, body, d6, 50/Globals.PPM,10/Globals.PPM, 0,12/Globals.PPM);
		
		BodyCreationUtils.revoluteJoint(world, rollers.get(0), d1, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(1), d2, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(2), d3, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(3), d4, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(4), d5, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, rollers.get(5), d6, -0/Globals.PPM,-0/Globals.PPM, 0,-14/Globals.PPM);
	}	
	
	
	private void createRollers()
	{
		rollers.add(BodyCreationUtils.circularBody(world, 110 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM));		
		rollers.add(BodyCreationUtils.circularBody(world, 130 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world, 150 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world, 170 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world, 190 / Globals.PPM, 100 / Globals.PPM, 5 / Globals.PPM));
		rollers.add(BodyCreationUtils.circularBody(world, 210 / Globals.PPM, 110 / Globals.PPM, 5 / Globals.PPM));
	}
	
	
	
	private Body createTruckBody(float density, World world, float x, float y, float width, float height)
	{
		BodyDef bdef = new BodyDef();		
		bdef.position.set(x, y);
		bdef.type  = BodyType.DynamicBody;
		Body body = world.createBody(bdef);		
		body.createFixture(BodyCreationUtils.createPolygonFixture(width, height, 0.4f, density, 0.8f, null, 0f));

		//kabina + body
		body.createFixture(BodyCreationUtils.createPolygonFixture(20 / Globals.PPM, 15 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(33 / Globals.PPM,18 / Globals.PPM), 0f));		
		body.createFixture(BodyCreationUtils.createPolygonFixture(8 / Globals.PPM, 6 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(42 / Globals.PPM,24 / Globals.PPM), 0f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(2 / Globals.PPM, 6 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(51 / Globals.PPM,24 / Globals.PPM), 0f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(2 / Globals.PPM, 2 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(54 / Globals.PPM,6 / Globals.PPM), 0f));		
		body.createFixture(BodyCreationUtils.createPolygonFixture(34 / Globals.PPM, 2 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(-20 / Globals.PPM,6 / Globals.PPM), 0f));		
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 10 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(-55 / Globals.PPM,3 / Globals.PPM), 3.14f));
		body.createFixture(BodyCreationUtils.createPolygonFixture(1 / Globals.PPM, 5 / Globals.PPM, 0.4f, 0.001f, 0.8f, new Vector2(57 / Globals.PPM,-2 / Globals.PPM), 0.6f));
		
		return body;
	}
	
	private void createTracks()
	{
		float tLength = 4f;
		float twidth = 1f;
		Body[] track = new Body[11];
		float length = (float) (101  / Globals.PPM);
		float delta = (float) (tLength/Globals.PPM)*2;	
		for (int i = 0; i < track.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(10f,world, length,103 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			track[i] = tr;
		}	
		for (int i = 0; i < track.length; i++) {
			if(i!= track.length-1)
			{
				BodyCreationUtils.revoluteJoint(world, track[i], track[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		
		
		
		 tLength = 4f;
		 twidth = 1f;
		Body[] trackttt = new Body[4];
		 length = (float) (190  / Globals.PPM);
		 delta = (float) (tLength/Globals.PPM)*2;	
		for (int i = 0; i < trackttt.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(10f,world, length,114 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			trackttt[i] = tr;
		}	
		for (int i = 0; i < trackttt.length; i++) {
			if(i!= trackttt.length-1)
			{
				BodyCreationUtils.revoluteJoint(world, trackttt[i], trackttt[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		BodyCreationUtils.revoluteJoint(world, track[track.length-1], trackttt[0], tLength/Globals.PPM, -twidth/Globals.PPM, -tLength/Globals.PPM, -twidth/Globals.PPM);
	
		
		
		
		
		
		
		 tLength = 4f;
		 twidth = 1f;
		Body[] trackl = new Body[14];
		 length = (float) (101  / Globals.PPM);
		 delta = (float) (tLength/Globals.PPM)*2;
		
		
		for (int i = 0; i < trackl.length; i++) {
			length = length + delta;
			Body tr= BodyCreationUtils.rectangularBody(10f,world, length,89 / Globals.PPM,tLength / Globals.PPM, twidth / Globals.PPM);
			trackl[i] = tr;
		}
		
		for (int i = 0; i < trackl.length; i++) {
			if(i!= trackl.length-1)
			{
				BodyCreationUtils.revoluteJoint(world, trackl[i], trackl[i+1], tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, twidth/Globals.PPM);
			}
		}
		
		BodyCreationUtils.revoluteJoint(world, trackl[0], track[0], -tLength/Globals.PPM, twidth/Globals.PPM, -tLength/Globals.PPM, -twidth/Globals.PPM);
		BodyCreationUtils.revoluteJoint(world, trackttt[trackttt.length-1], trackl[trackl.length-1], tLength/Globals.PPM, -twidth/Globals.PPM, tLength/Globals.PPM, twidth/Globals.PPM);

	}
	@Override
	public void update(float dt) {
		float force = 0.0007f;
		if(MyInput.isDown(MyInput.BUTTON1))
		{
			for(Body roller : rollers)
			{
				roller.applyAngularImpulse(force, true);
			}		
		}
		if(MyInput.isDown(MyInput.BUTTON2))
		{
			for(Body roller : rollers)
			{
				roller.applyAngularImpulse(-force, true);
			}
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();		
		for(Body roller : rollers)
		{
			sb.draw(new TextureRegion(Game.res.getTexture("wheel10")), roller.getPosition().x- 5/Globals.PPM, roller.getPosition().y- 5/Globals.PPM, 5/Globals.PPM, 5/Globals.PPM, 10/Globals.PPM, 10/Globals.PPM, 1, 1 , roller.getAngle() * MathUtils.radiansToDegrees, false);
		}
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public List<Body> getRollers() {
		return rollers;
	}

	public void setRollers(List<Body> rollers) {
		this.rollers = rollers;
	}

	public Body getCargo() {
		return cargo;
	}

	public void setCargo(Body cargo) {
		this.cargo = cargo;
	}

	public Body getTruckBody() {
		return truckBody;
	}

	public void setTruckBody(Body truckBody) {
		this.truckBody = truckBody;
	}


}
