package tests;

import main.*;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


import main.SimpleUserService;
import main.User;
import main.UserService;
import org.mockito.Mockito;

public class TestUserServices {
  private UserService userService;
  private OSAuditLog osAuditLog;
  private OSUserStore osUserStore;
  private OSTimeSource osTimeSource;


  @Test
  public void testRegisterTwoUsers() {
    osAuditLog = new OSAuditLog();
    osUserStore = new OSUserStore();
    osTimeSource = new OSTimeSource();
    userService = new SimpleUserService(osAuditLog, osUserStore, osTimeSource);

    DateTime time = osTimeSource.currentTime();

    userService.register("bob");
    assertEquals(1, userService.users().size());

    userService.register("alice");
    assertEquals(2, userService.users().size());

    User user = userService.find("alice");
    assertEquals("alice", user.getUsername());
    assertEquals(time, user.getCreationTime());
  }

  @Test
  public void testRegisterTwoUsersMockitoSpy() {

    osAuditLog = new OSAuditLog();
    osUserStore = new OSUserStore();
    osTimeSource = new OSTimeSource();
    OSTimeSource osTimeSourceSpy = Mockito.spy(osTimeSource);
    Mockito.doReturn(new DateTime(2020, 2, 29, 0, 0,01)).when(osTimeSourceSpy).currentTime();

    userService = new SimpleUserService(osAuditLog, osUserStore, osTimeSourceSpy);

    DateTime time = osTimeSourceSpy.currentTime();
    System.out.println(time);

    userService.register("bob");
    assertEquals(1, userService.users().size());

    userService.register("alice");
    assertEquals(2, userService.users().size());

    User user = userService.find("alice");
    assertEquals("alice", user.getUsername());
    assertEquals(time, user.getCreationTime());
  }

  @Test
  public void testRegisterTwoUsersMockitoVerify()
  {
    osUserStore = new OSUserStore();
    osTimeSource = new OSTimeSource();
    osAuditLog = new OSAuditLog();
    OSAuditLog mockedOSAuditLog = mock(OSAuditLog.class);


    userService = new SimpleUserService(mockedOSAuditLog, osUserStore, osTimeSource);

    DateTime time = osTimeSource.currentTime();
    System.out.println(time);

    //userService.register("bob");
    //assertEquals(1, userService.users().size());
    verify(mockedOSAuditLog).log("user", "register", "bobby");

    userService.register("alice");
    assertEquals(2, userService.users().size());

    User user = userService.find("alice");
    assertEquals("alice", user.getUsername());
    //assertEquals(time, user.getCreationTime());
  }
}
