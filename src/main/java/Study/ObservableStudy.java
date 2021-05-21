package Study;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import model.USER;

import javax.security.auth.Subject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class ObservableStudy {
    public static void main(String[] args) throws IOException {
        //객체의 상태 변화를 관찰하는 관찰자 목록
        //인자로 넣은 데이터를 차례로 발행하는 just 함수
        Observable<Integer> source = Observable.just(1,2,3,4,5,6);

        //subscribe 를 해야 발행한 데이터에 관한 처리를 할 수 있다.
        //subscribe 함수는 disposable 객체를 반환하고, 파라미터에 따라 onNext,onError,onComplete등을 처리할 수 있음)
        //onComplete 시 자동으로 dispose 실행, 구독관계를 종료한다.
        Disposable disposable = source.subscribe(v-> System.out.println(v),err-> System.out.println(err),()-> System.out.println("onComplete"));

        System.out.println(disposable.isDisposed());

        //create함수 -> onNext, onComplete 등 프로그래머가 직접적으로 해줘야되는 Observable 객체 생성 함수

        Observable<String> source1 = Observable.create(
                (ObservableEmitter<String> emitter) -> {
                    emitter.onNext("하이");
                    emitter.onNext("월드");
                    emitter.onComplete();
                }
        );
        Disposable disposable1 = source1.subscribe(v-> System.out.println(v));

        System.out.println(disposable1.isDisposed());

        USER[] users = new USER[4];
        for(int i=0;i<4;i++){
            users[i] = new USER("박종호",i+26);
        }
        //Array에 있는 데이터를 발행하는 Observable 객체를 생성하는 fromArray함수
        Observable<USER> userObservable = Observable.fromArray(users);

        userObservable.subscribe(v-> System.out.println(v.getUserAge()+" "+v.getUserName()));
        Disposable disposable2 = userObservable.subscribe(v -> {
            System.out.println(v.getUserAge());
        });

        //Single Class 는 데이터를 한 개만 발행하는 Observable 클래스로 , 발행과 동시에 종료된다. onSuccess 와 onError 함수로만 구성된다.
        Single<String> single = Single.just("Hello RxJava");
        single.subscribe(v-> System.out.println(v));

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "hello RxJava";
            }
        };
        Observable.fromCallable(callable).subscribe(v-> System.out.println(v));

        // 이 때까지 다룬 Observable 객체는 전부 차가운 Observable 이다. -> just, fromCallable, fromArray등 Observable 객체를 생성해도
        // 구독자가 존재하지 않는다면 데이터를 발행하지 않는다.
        // 뜨거운 Observable 은 객체를 생성할 때부터 데이터를 발행한다. 이 경우 배압(발행 속도와 그 데이터를 처리하는 속도가 너무 차이날 때) 을 별도로 처리해줘야한다.

        //Subject 클래스는 발행자의 속성과 구독자의 속성이 모두 존재한다.
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);
        asyncSubject.subscribe(v-> System.out.println(v));

        asyncSubject.onNext(5);
        asyncSubject.subscribe(v-> System.out.println(v));

        asyncSubject.onComplete();
        //AsyncSubject 클래스는 onComplete 가 불릴 시 마지막으로 발행된 데이터만 처리하는 클래스이다.

        AsyncSubject<String> asyncSubject1 = AsyncSubject.create();

        //Subject 클래스는 Observable 클래스를 상속함과 동시에 인터페이스를 구현하기 때문에, 구독자 , 발행자로서의 역할을 동시에 수행 가능하다.
        asyncSubject1.subscribe(v-> System.out.println(v));
        Observable.fromCallable(callable).subscribe(asyncSubject1);
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.createDefault("Hello RxJava");
        behaviorSubject.subscribe(v-> System.out.println(v));

        behaviorSubject.onNext("Build a Bitch");
        behaviorSubject.subscribe(v->System.out.println(v));
        behaviorSubject.onNext("Spiral");
        behaviorSubject.subscribe(v->System.out.println(v));
        //BeHaviorSubject 클래스는 가장 최근에 발행된 혹은 기본 데이터를 구독자에게 발행한다.
        behaviorSubject.onComplete();
        //map 함수는 해당 mapping 함수를 각 데이터에 적용해 발행한다는 의미다.
        Observable<USER> mapObservable = Observable.fromArray(users)
                .map(v-> { v.setFavoriteSong("spiral");
                return v; });
        mapObservable.subscribe(v->v.printUserInfo());
    }
}
