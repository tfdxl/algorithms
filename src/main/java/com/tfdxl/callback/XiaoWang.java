package com.tfdxl.callback;

/**
 * Created by tianfeng on 2017/7/4.
 */
public class XiaoWang implements CallBack {

    private Xiaoli xiaoli;

    public XiaoWang(Xiaoli xiaoli) {
        this.xiaoli = xiaoli;
    }

    public synchronized void askQuestion(final String question) {

        //we start a new thread
        new Thread(() -> {
            try {
                xiaoli.executeMessage(XiaoWang.this, question);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        doOtherThings();
    }

    @Override
    public synchronized void solve(String result) {
        System.out.println("I get the answer is " + result);
    }

    private void doOtherThings() {
        System.out.println("We have other things to do ");
    }

    public static void main(String[] args) {
        Xiaoli xiaoli = new Xiaoli();
        XiaoWang xiaoWang = new XiaoWang(xiaoli);
        xiaoWang.askQuestion("1+1 =?");
    }
}
