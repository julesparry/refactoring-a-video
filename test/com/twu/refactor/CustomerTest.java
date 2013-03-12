package com.twu.refactor;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    private static final String GOLD_PATH = "test/data";

    private Customer customer = new Customer("Jonny Customer");

    private Movie python = new Movie("Monty Python and the Holy Grail", Movie.REGULAR);
    private Movie ran = new Movie("Ran", Movie.REGULAR);
    private Movie la = new Movie("LA Confidential", Movie.NEW_RELEASE);
    private Movie trek = new Movie("Star Trek 13.2", Movie.NEW_RELEASE);
    private Movie wallace = new Movie("Wallace and Gromit", Movie.CHILDRENS);

    @Before
    public void setUpData() {
        customer.addRental(new Rental(python, 3));
        customer.addRental(new Rental(ran, 1));
        customer.addRental(new Rental(la, 2));
        customer.addRental(new Rental(trek, 1));
        customer.addRental(new Rental(wallace, 6));
    }

    @Test
    public void shouldOutputEmptyStatement() throws Exception {
        Customer customer = new Customer("Golden Shark");
        verifyOutput(customer.statement(), "outputEmpty");
    }

    @Test
    public void shouldOutputCustomerStatement() throws Exception {
        verifyOutput(customer.statement(), "output1");
    }

    @Test
    public void shouldOutputChangedStatement() throws Exception {
        la.setPriceCode(Movie.REGULAR);
        verifyOutput(customer.statement(), "outputChange");
    }

    /*
    public void testHtml() throws Exception {
        verifyOutput("1st Output", "outputHtml", customer.htmlStatement());
    }
    */

    protected void verifyOutput(String actualValue, String fileName) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(GOLD_PATH + '/' + fileName));
        BufferedReader actualStream = new BufferedReader(new StringReader(actualValue));
        String thisFileLine;
        while ((thisFileLine = file.readLine()) != null) {
            assertThat("in file: " + fileName, actualStream.readLine(), equalTo(thisFileLine));
        }
    }

}
