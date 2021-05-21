package Study;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import model.USER;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class Boksoop {

    public static void main(String[] args) throws IOException {

        Observable<String> observable = Observable.just("Hello","RxJava");

        Disposable disposable = observable.subscribe(v-> System.out.println(v),err-> System.out.println(err),() -> System.out.println("complete"));
        if(disposable.isDisposed())
            System.out.println(true);

        ArrayList<USER> userArrayList = new ArrayList<>();

        userArrayList.add(new USER("안녕하세요",26));
        for(int i=0; i<10;i++){
            userArrayList.add(new USER("나는 박종호",30+i));
        }

        Observable<USER> observable1 = Observable.fromIterable(userArrayList);


        Disposable disposable1 = observable1.subscribe(v -> v.printUserInfo());
    }
}
