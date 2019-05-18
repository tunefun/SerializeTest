package hello;

import java.util.Random;

public class Task {
    private int id;
    private String name;
    private User createUser;
    private String[] jobs;
    private int innerToken;

    public Task(int id, String name, User createUser, String[] jobs) {
        this.id = id;
        this.name = name;
        this.createUser = createUser;
        this.jobs = jobs;
        innerToken = new Random().nextInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String[] getJobs() {
        return jobs;
    }

    public void setJobs(String[] jobs) {
        this.jobs = jobs;
    }

    public int getInnerToken() {
        return innerToken;
    }

    public void setInnerToken(int innerToken) {
        this.innerToken = innerToken;
    }


}
