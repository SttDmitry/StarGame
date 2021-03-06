package ru.stargame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.math.Rect;
import ru.stargame.pool.BulletPool;
import ru.stargame.pool.ExplosionPool;
import ru.stargame.sprite.Bullet;
import ru.stargame.sprite.Explosion;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected Vector2 v0;
    protected Vector2 v;

    protected Rect worldBounds;
    protected ExplosionPool explosionPool;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletV2;
    protected Vector2 bulletPos;
    protected Vector2 bulletPos2;
    protected float bulletHeight;
    protected int damage;
    protected boolean upgrade = false;

    protected float reloadInterval;
    protected float reloadTimer;

    protected int hp;

    protected Sound sound;

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        frame = 1;
        damageAnimateTimer = 0f;
    }

    public int getDamage() {
        return damage;
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
        if (upgrade) {
            Bullet bullet2 = bulletPool.obtain();
            bullet2.set(this, bulletRegion, bulletPos2, bulletV2, bulletHeight, worldBounds, damage);
        }
        sound.play();
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }

    public int getHp() {
        return hp;
    }

    public void restoreHp() {
        this.hp = 100;
    }

    public void healHp(int hp) {
        this.hp += hp;
    }

    public void setUpgrade(boolean upgrade) {
        this.upgrade = upgrade;
    }
}
