public class TextRunner extends LifeGrid{

    private TextRunner(int w, int h) {
        super(w, h);
    }

    @Override
    protected void next() {
        super.next();
        System.out.println(this);
    }

    public static void main(String[] args) {
        LifeGrid lg = new TextRunner(15,10);

        lg.change(2,1);
        lg.change(2,2);
        lg.change(2,3);


        lg.change(0,5);
        lg.change(0,6);
        lg.change(0,7);
        lg.change(0,8);
    }


}
