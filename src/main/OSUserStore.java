package main;

import main.User;
import main.UserStore;

import java.util.ArrayList;
import java.util.List;

public class OSUserStore implements UserStore {
  private ArrayList<User> users = new ArrayList<User>();
  @Override
  public void store(User user) {
    users.add(user);
  }

  @Override
  public List<User> findAll() {
    return users;
  }
}
