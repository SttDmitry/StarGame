package ru.stargame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.base.Sprite;
import ru.stargame.math.Rect;

public class Logo extends Sprite {
    private Vector2 v = new Vector2();
    private Vector2 dest = new Vector2();
    private Vector2 destTemp = new Vector2();

    @Override
    public void update(float delta) {
        destTemp.set(dest);
        if  (destTemp.sub(this.pos).len() > v.len()) {
            this.pos.add(v);
        } else  {
            this.pos.set(dest);
        }
    }

    public Logo(Texture texture) {  super(new TextureRegion(texture)); }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);
        setHeightProportion(worldBounds.getHeight()*0.5f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        dest.set(touch);
        v.set(touch.cpy().sub(this.pos)).setLength(0.02f);
        return super.touchDown(touch, pointer, button);
    }
}
