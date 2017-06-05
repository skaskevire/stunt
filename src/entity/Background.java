package entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import stunt.Game;
import stunt.Globals;

public class Background implements Entity {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed = 100;

    public Background() {
        textureRegion = new TextureRegion(Game.res.getTexture("background"));
        textureRegionBounds1 = new Rectangle(0 - Globals.V_WIDTH / 2, 0, Globals.V_WIDTH, Globals.V_HEIGHT);
        textureRegionBounds2 = new Rectangle(Globals.V_WIDTH / 2, 0, Globals.V_WIDTH, Globals.V_HEIGHT);
    }

    private void act(float delta) {
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.x - (delta * speed)) <= 0;
    }

    private void updateXBounds(float delta) {
        textureRegionBounds1.x += delta * speed;
        textureRegionBounds2.x += delta * speed;
    }

    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(Globals.V_WIDTH, 0, Globals.V_WIDTH, Globals.V_HEIGHT);
    }

	@Override
	public void update(float dt) {
		act(0.00001f);
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, Globals.V_WIDTH,
				Globals.V_HEIGHT);
		sb.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Globals.V_WIDTH,
				Globals.V_HEIGHT);
		sb.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}