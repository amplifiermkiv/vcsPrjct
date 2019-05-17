package lt.vcs.managementprjct.controller;

import lt.vcs.managementprjct.ConnectionFactory;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class RemoveTripTest {
    Connection connection;
    RemoveTrip removeTrip;


    public void replay(Object obj) {
        EasyMock.replay(obj);
    }

    public void verify(Object obj) {
        EasyMock.verify(obj);
    }

    public void reset(Object obj) {
        EasyMock.reset(obj);
    }

    @Before
    public void setUp() {

        // mocks
        connection = EasyMock.createMock(Connection.class);
        //ConnectionFactory.getInstance(connection);

        // test subject
        removeTrip = new RemoveTrip();
    }

    @After
    public void tearDown() {
        EasyMock.reset(connection);
    }

    @Test
    public void test_removeConfirmationTrue() {
        // no setup

        // expectations
        //ConnectionFactory.getFactory()
        EasyMock.expectLastCall();

        // test
        replay(connection);
        removeTrip.removeConfirmation();
        //verify(printerService);
        //reset(printerService);

        // no assertions as only testing the behaviour
    }


}
