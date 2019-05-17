package lt.vcs.managementprjct.controller;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TripsAccountmentTest {
    Connection connection;
    TripsAccountment tripsAccountment;
    private ArrayList<Double> list;
    private ArrayList<Double> listT;


    @Before
    public void setUp() {
        /*connection = EasyMock.createMock(Connection.class);
        ConnectionFactory.setConnection(connection);*/
        list = new ArrayList<>();
        listT = new ArrayList<>();
        tripsAccountment = new TripsAccountment();
    }

    //TODO kodėl klasės TripsAccountmenst metodus teko daryti public??????

    @Test
    public void test_totalProfit() {
        list.add((double) 0);
        list.add(2.52);
        list.add(1000000.21);
        double expect = 1000002.73;
        double result = tripsAccountment.totalProfit(list);

        assertEquals(expect, result, 0.01);
    }

    @Test
    public void test_totalTurnover() {
        listT.add(10.12);
        listT.add(2.52);
        listT.add(1000000.21);
        double expect = 0;
        double result = tripsAccountment.totalTurnover(listT);

        assertEquals(expect, result, 0.01);

    }

    @Test
    public void test_selectedManagerProfit() {
        // no setup

        // expectations
        //ConnectionFactory.getFactory()
        //EasyMock.expectLastCall();

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
    public void test_loadData() {

    }

    @Test
    public void test_loadManagerList() {

    }

}
