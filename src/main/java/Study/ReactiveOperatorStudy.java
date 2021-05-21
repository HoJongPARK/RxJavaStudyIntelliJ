package Study;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import model.USER;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class ReactiveOperatorStudy {
    public static void main(String[] args) throws IOException {
        ArrayList<USER> users = new ArrayList<>();
        for(int i=0;i<5;i++)
            users.add(new USER("박종호",i+26));

        Observable<USER> userObservable = Observable.fromIterable(users).map(
                v-> {
                    v.setFavoriteSong("spiral");
                    return v;
                }
        );

        Disposable disposable = userObservable.subscribe(v->v.printUserInfo(),err-> System.out.println(err),()-> System.out.println("onComplete"));
        System.out.println(disposable.isDisposed());
        //map 함수는 Function 함수를 인자로 받으므로, 별도로 정의해서 넘겨줄 수 있음
        //Function 함수의 제네릭 타입은 <T,R> 인자로 T를 받고 R을 리턴
        Function<USER,String> function = user -> user.getUserName();

        Observable<String> userObservable2 = Observable.fromIterable(users).map(function);

        userObservable2.subscribe(v-> System.out.println(v));

        Function<String, Observable<String>> getDoubleDiamonds = ball -> Observable.just(ball+"ball",ball+"ball");

        String[] balls = {"1","3","5"};
        //flatMap 함수는 Observable을 반환하고, 이로 인해 인자를 넣으면 여러 개의 데이터를 발행하는 Observable 객체를 반환받을 수 있다.
        Observable<String> source = Observable.fromArray(balls).flatMap(getDoubleDiamonds);
        source.subscribe(v-> System.out.println(v));


        Observable.just("String","hi I am repeatale study").subscribe( v -> System.out.println(v));

    }
}
