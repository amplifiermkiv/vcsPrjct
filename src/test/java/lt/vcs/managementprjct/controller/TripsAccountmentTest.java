package lt.vcs.managementprjct.controller;

import lt.vcs.managementprjct.ConnectionFactory;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.easymock.EasyMock.*;

public class TripsAccountmentTest {
    Connection connection;
    TripsAccountment tripsAccountment;

    @Before
    public void setUp() {
        connection = EasyMock.createMock(Connection.class);
        ConnectionFactory.setConnection(connection);

        tripsAccountment = new TripsAccountment();
    }

    @Test
    public void test_selectedManagerProfit() {
        // no setup


        // expectations
        //ConnectionFactory.getFactory()
        EasyMock.expectLastCall();

        // test
        //replay(connection);
        //removeTrip.removeConfirmation();
        //verify(printerService);
        //reset(printerService);

        // no assertions as only testing the behaviour
    }

    @Test
    public void test_selectedManagerTurnover() {

    }

    @Test
    public void test_totalProfit() {

    }

    @Test
    public void test_totalTurnover() {

    }

    @Test
    public void test_loadData() {

    }

    @Test
    public void test_loadManagerList() {

    }

}
