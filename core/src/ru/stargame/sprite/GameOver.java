package ru.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.stargame.base.Sprite;
import ru.stargame.math.Rect;

public class GameOver extends Sprite {
    private static final float SIZE = 0.065f;
    public GameOver(TextureRegion tr) {  super(new TextureRegion(tr)); }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);
        setHeightProportion(worldBounds.getHeight()*SIZE);
    }

}
