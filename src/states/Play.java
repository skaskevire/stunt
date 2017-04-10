package states;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.particles.values.LineSpawnShapeValue;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import handlers.GameStateManager;
import stunt.Globals;

public class Play extends GameState{
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	//private BitmapFont font = new BitmapFont();
	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0,-1.8f), true);
		b2dr = new Box2DDebugRenderer();
		
		//create ground
		BodyDef bdef = new BodyDef();
		bdef.position.set(120 / Globals.PPM,120 / Globals.PPM);
		bdef.type = BodyType.StaticBody;
		
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(100 / Globals.PPM, 1 / Globals.PPM);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.density = 1;
		body.createFixture(fdef);
		
		bdef.position.set(60 / Globals.PPM,175 / Globals.PPM);
		body = world.createBody(bdef);		
		shape.setAsBox(1 / Globals.PPM, 50 / Globals.PPM);
		fdef.shape = shape;
		fdef.density = 1;
		body.createFixture(fdef);
		
		bdef.position.set(180 / Globals.PPM,175 / Globals.PPM);
		body = world.createBody(bdef);		
		shape.setAsBox(1 / Globals.PPM, 50 / Globals.PPM);
		fdef.shape = shape;
		fdef.density = 1;
		body.createFixture(fdef);
		
		
		bdef.position.set(120 / Globals.PPM,120 / Globals.PPM);
		
		//create car
		bdef.position.set(120 / Globals.PPM, 220 / Globals.PPM);
		bdef.type  = BodyType.DynamicBody;
		body = world.createBody(bdef);		
		shape.setAsBox(20 / Globals.PPM, 3 / Globals.PPM);
		fdef.shape = shape;	
		fdef.restitution = 0.4f;
		fdef.density = 1;
		body.createFixture(fdef);
		
		bdef.position.set(95 / Globals.PPM, 240 / Globals.PPM);
		bdef.fixedRotation = false;

		bdef.type  = BodyType.DynamicBody;
		body = world.createBody(bdef);
		shape.setAsBox(20 / Globals.PPM, 3 / Globals.PPM);
		fdef.shape = shape;	
		fdef.density = 1;

		fdef.restitution = 0.4f;
		body.createFixture(fdef);
		
		
		
		
		
		
		CircleShape cshape = new CircleShape();		
		cshape.setRadius(5 / Globals.PPM);
		bdef.position.set(116 / Globals.PPM, 180 / Globals.PPM);		
		bdef.type  = BodyType.DynamicBody;
		body = world.createBody(bdef);
		fdef.shape = cshape;	
		fdef.restitution = 0.95f;
		fdef.density = 1;
		body.createFixture(fdef);
		

		cshape.setRadius(5 / Globals.PPM);
		bdef.position.set(116 / Globals.PPM, 181 / Globals.PPM);		
		bdef.type  = BodyType.DynamicBody;
		body = world.createBody(bdef);
		fdef.shape = cshape;	
		fdef.restitution = 0.95f;
		fdef.density = 1;
		body.createFixture(fdef);
		

		cshape.setRadius(5 / Globals.PPM);
		bdef.position.set(116 / Globals.PPM, 182 / Globals.PPM);		
		bdef.type  = BodyType.DynamicBody;
		body = world.createBody(bdef);
		fdef.shape = cshape;	
		fdef.restitution = 0.95f;
		fdef.density = 1;
		body.createFixture(fdef);
		
		
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Globals.V_WIDTH / Globals.PPM, Globals.V_HEIGHT / Globals.PPM );
		//static body - don't move, unaffected by forces - ground
		
		//kinematic - don't get affected by forces ( moving platform )
		
		//dynamic body - always ged affected by forces ( player )
		
		
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		
	}

	@Override
	public void render() {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
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
