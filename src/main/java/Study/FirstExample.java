package Study;

import io.reactivex.Observable;

import java.io.IOException;

public class FirstExample {
    public void emit(){
        Observable.just("hi!! hello world","RxJava").subscribe(System.out::println);
    }
    public static void main(String[] args) throws IOException {
        FirstExample firstExample = new FirstExample();
        firstExample.emit();
    }
}
