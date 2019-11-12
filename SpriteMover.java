/*
Kyle Sadler
Programming Paradigms Assignment 5
October 18th, 2019
*/
public class SpriteMover implements Runnable{

    Model model;
    View view;

    public SpriteMover(Model m, View v){
        this.model = m;
        this.view = v;
    }

    public void run(){
        while(true){

            //model.updateScene(view.getWidth(), view.getHeight());
            view.repaint();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
            }
        }
    }
}
