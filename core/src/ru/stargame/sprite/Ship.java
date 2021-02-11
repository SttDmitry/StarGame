package ru.stargame.sprite;



import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.base.Sprite;
import ru.stargame.math.Rect;

public class Ship extends Sprite {

    private Vector2 v = new Vector2();
    private Vector2 dest = new Vector2();
    private Vector2 destTemp = new Vector2();

    private static final float HEIGHT = 0.2f;
    private static final float PADDING = 0.05f;

    public Ship (TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship").getTexture(), 916, 95, 187, 287 ));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void update(float delta) {
        destTemp.set(dest);
        if  (destTemp.sub(this.pos).len() > v.len()) {
            this.pos.add(v);
        } else  {
            this.pos.set(dest);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        dest.set(touch);
        v.set(touch.cpy().sub(this.pos)).setLength(0.02f);
        return super.touchDown(touch, pointer, button);
    }
}
