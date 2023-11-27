package com.api.sweetshop;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.api.sweetshop.exceptions.UserExistsException;
import com.api.sweetshop.model.Gender;
import com.api.sweetshop.model.Sweet;
import com.api.sweetshop.model.UserProfile;
import com.api.sweetshop.service.BakingImpl;
import com.api.sweetshop.service.BakingStreams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BakingStreamsTest {
  private final BakingImpl baking = new BakingImpl();
  private BakingStreams bakingStreams;

  UserProfile user1;
  UserProfile user2;
  UserProfile user3;

  @BeforeEach
  public void setUp() throws UserExistsException {
    user1 = new UserProfile("Anna", "anna@yahoo.com", Gender.FEMALE);
    user1.addFavoriteSweet(Sweet.CHOCOLATE_TRUFFLES);
    user1.addFavoriteSweet(Sweet.HOMEMADE_CHOCOLATE);

    user2 = new UserProfile("Alex", "alex@yahoo.com", Gender.MALE);
    user2.addFavoriteSweet(Sweet.MARQUISE);

    user3 = new UserProfile("Emma", "emma@yahoo.com", Gender.FEMALE);
    user3.addFavoriteSweet(Sweet.DUMP_CAKE);
    user3.addFavoriteSweet(Sweet.FONDANT_CANDIES);
    user3.addFavoriteSweet(Sweet.HOMEMADE_CHOCOLATE);

    baking.addUser(user1);
    baking.addUser(user2);
    baking.addUser(user3);
    bakingStreams = new BakingStreams();
  }

  public void testStream() {

  }

  @Test
  public void testNumberOfUsers() {
    assertEquals(3, bakingStreams.getNumberOfUsers(baking));
  }

  @Test
  public void testNumberOfFavoriteSweets() {
    assertEquals(6, bakingStreams.getNumberOfFavoriteSweets(baking));
  }

  @Test
  public void testUsersSorted() {
    SortedSet<UserProfile> users = bakingStreams.getUsersSorted(baking);

    assertEquals(3, users.size());
    assertEquals(users.first(), user2);
    assertEquals(users.last(), user3);
  }

  @Test
  public void testClientsByGender() {
    Map<Gender, List<UserProfile>> usersByGender = bakingStreams.getUsersByGender(baking);
    System.out.println(usersByGender);
    assertEquals(2, usersByGender.get(Gender.FEMALE).size());
    assertEquals(1, usersByGender.get(Gender.MALE).size());
  }
}
