package com.drake.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LightPolarizationApp extends ApplicationAdapter {
	SpriteBatch batch;
	private Stage stage;
	Button button;
	ShapeRenderer renderer;
	BitmapFont font;
	LPCore core;

	Double AofL = 0.0;
	
	@Override
	public void create () {
		core = new LPCore();
		batch = new SpriteBatch();
		font = new BitmapFont();
		renderer = new ShapeRenderer();

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
//		button = new Button();
//		button.setSize(70, 50);
//		button.setPosition(100, 50);
//		button.addListener(new InputListener(){
//			@Override
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				super.touchUp(event, x, y, pointer, button);
//			}
//
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				return super.touchDown(event, x, y, pointer, button);
//			}
//		});
//		stage.addActor(button);
//		stage.addListener(new InputListener(){
//			@Override
//			public boolean keyDown(InputEvent event, int keycode) {
//				if(keycode == Input.Keys.A) {
//					if (AofL <= 315.0) {
//						AofL += 45.0;
//					} else {
//						AofL = 0.0;
//					}
//				}else if(keycode == Input.Keys.D){
//					if (AofL >= 0.0) {
//						AofL -= 45.0;
//					} else {
//						AofL = 315.0;
//					}
//				}
//				return super.keyDown(event, keycode);
//			}
//		});

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		float delta = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

		if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
			if (AofL <= 315.0) {
				AofL += 45.0;
			} else {
				AofL = 0.0;
			}
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
			if (AofL > 0.0) {
				AofL -= 45.0;
			} else {
				AofL = 315.0;
			}
		}

		String b = core.intensitySolver(AofL, 5.0) + ": output intensity";
		String a = AofL.toString();

		batch.begin();
		font.draw(batch, b, 100, 50);
		font.draw(batch, a, 100, 70);
		batch.end();

		renderer.begin(ShapeType.Filled);
		renderer.rect(0,0, 50, 50);
		renderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		renderer.dispose();
		font.dispose();
	}
}
