package Study;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import model.USER;

import java.io.IOException;

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

        userObservable.subscribe(v-> System.out.println(v.userAge+" "+v.userName));
        Disposable disposable2 = userObservable.subscribe(v -> {
            v.userAge+=5;
            System.out.println(v.userAge);
        });

    }
}
