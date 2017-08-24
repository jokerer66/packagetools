package test;

/**
 * Created by apple on 2017/7/14.
 */
public class B extends A {
    public B() {
        System.out.println("B gouzao");
    }

    @Override
    public void function1() {
        super.function1();
        System.out.println("B function1");
    }

    @Override
    public void function2(){
        System.out.println("B function2");
    }

    public void function3(){
        System.out.println("B function3");
    }

    public static void main(String[] aa){
        A a=new B();
        a.function1();

    }
}
