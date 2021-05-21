package Study;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import model.USER;
import model.USERLIST;

import java.io.IOException;

public class ObservableStudyPractice {
    public static USERLIST userlist;
    public static void main(String[] args) throws IOException {
        userlist = USERLIST.getInstance();

    }
}
