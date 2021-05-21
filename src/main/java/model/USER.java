package model;

public class USER {
    private String userName;
    private int userAge;
    private String favoriteSong;
    public USER(String userName,int userAge0){
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getFavoriteSong() {
        return favoriteSong;
    }

    public void setFavoriteSong(String favoriteSong) {
        this.favoriteSong = favoriteSong;
    }

    public void printUserInfo(){
        System.out.println("유저 이름: "+userName);
        System.out.println("유저 나이: "+userAge);
        System.out.println("좋아하는 노래: "+favoriteSong);
    }
}
