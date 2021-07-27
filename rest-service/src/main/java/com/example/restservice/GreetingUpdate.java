package com.example.restservice;

public class GreetingUpdate {
    private long id;
    private String content;

    public long getID(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    @Override
    public String toString(){
        return "Greeting{" +
                "id= '" +id +'\'' + ",content= '" + content + '\'' + '}';
    }
}
