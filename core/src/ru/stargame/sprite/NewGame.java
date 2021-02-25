package ru.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.stargame.base.BaseButton;
import ru.stargame.math.Rect;
import ru.stargame.pool.BulletPool;
import ru.stargame.pool.EnemyPool;

public class NewGame extends BaseButton {

    private static final float PADDING = 0.03f;

    private MainShip mainShip;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;


    public NewGame(TextureRegion tr, MainShip mainShip, BulletPool bulletPool, EnemyPool enemyPool) {  super(new TextureRegion(tr));
    this.mainShip = mainShip;
    this.bulletPool = bulletPool;
    this.enemyPool = enemyPool;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);
        setHeightProportion(worldBounds.getHeight()*0.075f);
        setBottom(worldBounds.getBottom() + PADDING);
    }



    @Override
    public void action() {
        mainShip.reBuild();
        mainShip.setLeft(0-mainShip.getHalfWidth());
        bulletPool.destroyActiveSprites();
        enemyPool.destroyActiveSprites();
    }
}
