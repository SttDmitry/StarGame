package ru.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 touch;
    private Vector2 v;
    private float pX;
    private float pY;




    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        touch = new Vector2(6,6);
        v = new Vector2();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.6f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, touch.x, touch.y);
        batch.end();
        touch.add(v);
        if (Math.abs(touch.x % pX) < 5f && Math.abs(touch.y % pY) < 5f) {
            v.set(0,0);
        }
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        float subX = (screenX - touch.x) / 200.0f;
        float subY = (Gdx.graphics.getHeight() - screenY - touch.y) / 200.0f;
        System.out.println("sX="+subX+"; sY="+subY);
        pX = screenX;
        pY = Gdx.graphics.getHeight()-screenY;
        v.set(subX,subY);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                touch.y += 10;
                break;
            case Input.Keys.DOWN:
                touch.y -= 10;
                break;
            case Input.Keys.RIGHT:
                touch.x += 10;
                break;
            case Input.Keys.LEFT:
                touch.x -= 10;
                break;
        }
        return false;
    }

}
