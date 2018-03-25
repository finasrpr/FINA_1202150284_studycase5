package com.example.fina.fina_1202150284_studycase5;


public class itemTodo {
    //mendeklarasikan variabel yang dibutuhkan
    String todo, desc, prior;
    //konstruktor
    public itemTodo(String todo, String desc, String prior) {
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }
    //method setter dan getter nya

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
