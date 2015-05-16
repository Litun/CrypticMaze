package com.litun.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by user on 22.03.2015.
 */
public class Labyrinth implements Json.Serializable {

    // 1 up, 2 right, 4 down, 8 left
    private static byte UP = 1,
            RIGHT = 2,
            DOWN = 4,
            LEFT = 8;

    private byte storage[][][];
    private final int size,
            dimension;
    private int currentDimension = 0;

    public Labyrinth(int size, int dimension) {
        this.size = size;
        this.dimension = dimension;
        storage = new byte[dimension][size][size];

        readFile();
    }

    private  void readFile(){
        FileHandle file = Gdx.files.internal("maze/demo_level.maze");
        InputStream inputStream = file.read();
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            storage=(byte[][][])objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hardCode() {
        for (int i = 0; i < dimension; i++) {
            storage[i] = new byte[size][];
            for (int j = 0; j < size; j++) {
                storage[i][j] = new byte[size];
                for (int k = 0; k < size; k++) {
                    storage[i][j][k] = 15;
                }
            }

            for (int j = 0; j < size; j++) {
                storage[i][0][j] &= ~LEFT;
                storage[i][j][0] &= ~DOWN;
                storage[i][size - 1][j] &= ~RIGHT;
                storage[i][j][size - 1] &= ~UP;
            }
        }

        storage[0][1][1]&= ~RIGHT;
        storage[0][2][1]&= ~LEFT;

        storage[0][2][2]&= ~RIGHT;
        storage[0][2][2]&= ~UP;
        storage[0][2][3]&= ~DOWN;
        storage[0][3][2]&= ~LEFT;
    }

    public boolean toRight(int i, int j) {
        return (storage[currentDimension][i][j] & RIGHT) == RIGHT;
    }

    public boolean toLeft(int i, int j) {
        return (storage[currentDimension][i][j] & LEFT) == LEFT;
    }

    public boolean toUp(int i, int j) {
        return (storage[currentDimension][i][j] & UP) == UP;
    }

    public boolean toDown(int i, int j) {
        return (storage[currentDimension][i][j] & DOWN) == DOWN;
    }

    public boolean toRightNextDimention(int i, int j) {
        return (storage[(currentDimension + 1) % dimension][i][j] & RIGHT) == RIGHT;
    }

    public boolean toLeftNextDimention(int i, int j) {
        return (storage[(currentDimension + 1) % dimension][i][j] & LEFT) == LEFT;
    }

    public boolean toUpNextDimention(int i, int j) {
        return (storage[(currentDimension + 1) % dimension][i][j] & UP) == UP;
    }

    public boolean toDownNextDimention(int i, int j) {
        return (storage[(currentDimension + 1) % dimension][i][j] & DOWN) == DOWN;
    }

    public void switchDimension() {
        currentDimension = (currentDimension + 1) % dimension;
    }

    public int getSize() {
        return size;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public void write(Json json) {
        json.writeValue(storage);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        //storage = jsonData.child().asString();
    }
}
