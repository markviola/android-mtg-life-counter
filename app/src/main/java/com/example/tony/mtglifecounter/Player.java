package com.example.tony.mtglifecounter;

public class Player {

    private int _id;
    private String _playerName;
    private String _life, _poison;

    public Player(){

    }

    public Player(String playerName){
        this._playerName = playerName;
        this._life = "20";
        this._poison = "0";
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_playerName(String _playerName) {
        this._playerName = _playerName;
    }

    public void set_life(String _life) {
        this._life = _life;
    }

    public void set_poison(String _poison) {
        this._poison = _poison;
    }

    public int get_id() {
        return _id;
    }

    public String get_playerName() {
        return _playerName;
    }

    public String get_life() {
        return _life;
    }

    public String get_poison() {
        return _poison;
    }
}
