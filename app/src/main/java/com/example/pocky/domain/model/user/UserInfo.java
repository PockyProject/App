package com.example.pocky.domain.model.user;

public class UserInfo {
    private String userId;
    private String nickname;
    private String profileURl;
    private int userAge;

    private UserInfo(){};
    public void init(String userId,String nickname, String profileURl, int userAge){
                this.userId = userId;
                this.nickname = nickname;
                this.profileURl = profileURl;
                this.userAge = userAge;
    }

    private static class LazyHolder{
        private static final UserInfo INSTANCE = new UserInfo();
    }

    public static UserInfo getInstance(){
        return LazyHolder.INSTANCE;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileURl() {
        return profileURl;
    }

    public String getUserId() { return userId; }
    public int getUserAge() { return userAge; }

}
