/*
 * Copyright (c) 2009-2012 Matthew R. Harrah
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.mattharrah.gedcom4j.relationship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.mattharrah.gedcom4j.model.Gedcom;
import com.mattharrah.gedcom4j.model.Individual;
import com.mattharrah.gedcom4j.parser.GedcomParser;
import com.mattharrah.gedcom4j.parser.GedcomParserException;
import com.mattharrah.gedcom4j.query.Finder;

/**
 * @author frizbog1
 * 
 */
public class RelationshipCalculatorTest {

    /**
     * The gedcom to work with for testing
     */
    private Gedcom g;

    /**
     * A finder test fixture for the test
     */
    private Finder finder;

    /**
     * {@link RelationshipCalculator} test fixture
     */
    private RelationshipCalculator rc = new RelationshipCalculator();

    /**
     * Set up test fixtures
     * 
     * @throws IOException
     *             if the gedcom file can't be read
     * @throws GedcomParserException
     *             if the gedcom can't be parsed
     */
    @Before
    public void setUp() throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load("sample/RelationshipTest.ged");
        g = gp.gedcom;
        assertNotNull(g);
        assertEquals("There are 27 people in the gedcom", 27,
                g.individuals.size());
        assertEquals("There are 12 families in the gedcom", 12,
                g.families.size());
        finder = new Finder(g);

    }

    /**
     * Test method for
     * {@link RelationshipCalculator#calculateRelationships(com.mattharrah.gedcom4j.model.Individual, com.mattharrah.gedcom4j.model.Individual)}
     * .
     */
    @Test
    public void testCalculateRelationshipGrandparents() {
        Individual robert = getPerson("Andrews", "Robert");
        Individual steven = getPerson("Struthers", "Steven");

        rc.calculateRelationships(robert, steven);
        assertNotNull(rc.relationshipsFound);
        System.out
                .println("Relationships between " + robert + " and " + steven);
        for (Relationship r : rc.relationshipsFound) {
            System.out.println("   " + r);
        }
        assertEquals(
                "Steven is Robert's grandfather - there should be one relationship",
                1, rc.relationshipsFound.size());
        Relationship r = rc.relationshipsFound.get(0);
        assertEquals(robert, r.individual1);
        assertEquals(steven, r.individual2);
        assertNotNull(r.chain);
        assertEquals(
                "The relationship length should be still be two hops long after collapsing",
                2, r.chain.size());
    }

    /**
     * Test method for
     * {@link RelationshipCalculator#calculateRelationships(com.mattharrah.gedcom4j.model.Individual, com.mattharrah.gedcom4j.model.Individual)}
     * .
     */
    @Test
    public void testCalculateRelationshipGreatGrandparents() {
        Individual nancy = getPerson("Andrews", "Nancy");
        Individual steven = getPerson("Struthers", "Steven");

        rc.calculateRelationships(nancy, steven);
        assertNotNull(rc.relationshipsFound);
        System.out.println("Relationships between " + nancy + " and " + steven);
        for (Relationship r : rc.relationshipsFound) {
            System.out.println("   " + r);
        }
        assertEquals(
                "Steven is Nancy's great-grandfather - there should be one relationship",
                1, rc.relationshipsFound.size());
        Relationship r = rc.relationshipsFound.get(0);
        assertEquals(nancy, r.individual1);
        assertEquals(steven, r.individual2);
        assertNotNull(r.chain);
        assertEquals(
                "The relationship length should be two hops long after collapsing",
                3, r.chain.size());
    }

    /**
     * Test method for
     * {@link RelationshipCalculator#calculateRelationships(com.mattharrah.gedcom4j.model.Individual, com.mattharrah.gedcom4j.model.Individual)}
     * .
     */
    @Test
    public void testCalculateRelationshipManyGenerations() {
        Individual alex = getPerson("Zucco", "Alex");
        Individual abigail = getPerson("Wood", "Abigail");

        rc.calculateRelationships(alex, abigail);
        assertNotNull(rc.relationshipsFound);
        System.out.println("Relationships between " + alex + " and " + abigail);
        for (Relationship r : rc.relationshipsFound) {
            System.out.println("   " + r);
        }
        assertEquals(
                "Abigail is Alex's great-great-grandmother - there should be one relationship",
                1, rc.relationshipsFound.size());
        Relationship r = rc.relationshipsFound.get(0);
        assertEquals(alex, r.individual1);
        assertEquals(abigail, r.individual2);
        assertNotNull(r.chain);
        assertEquals(
                "The relationship length should be four hops long (after collapsing)",
                4, r.chain.size());
    }

    /**
     * Test method for
     * {@link RelationshipCalculator#calculateRelationships(com.mattharrah.gedcom4j.model.Individual, com.mattharrah.gedcom4j.model.Individual)}
     * .
     */
    @Test
    public void testCalculateRelationshipSelf() {
        Individual alex = getPerson("Zucco", "Alex");

        rc.calculateRelationships(alex, alex);
        assertNotNull(rc.relationshipsFound);
        System.out.println("Relationships between " + alex + " and " + alex);
        for (Relationship r : rc.relationshipsFound) {
            System.out.println("   " + r);
        }
        assertEquals("There are no relationships to oneself", 0,
                rc.relationshipsFound.size());

    }

    /**
     * Test method for
     * {@link RelationshipCalculator#calculateRelationships(com.mattharrah.gedcom4j.model.Individual, com.mattharrah.gedcom4j.model.Individual)}
     * .
     */
    @Test
    public void testCalculateRelationshipSiblings() {
        Individual alex = getPerson("Zucco", "Alex");
        Individual betsy = getPerson("Zucco", "Betsy");

        rc.calculateRelationships(alex, betsy);
        assertNotNull(rc.relationshipsFound);
        System.out.println("Relationships between " + alex + " and " + betsy);
        for (Relationship r : rc.relationshipsFound) {
            System.out.println("   " + r);
        }
        assertEquals(
                "Betsy is Alex's great-great-grandmother - there should be one relationship after simplifying -"
                        + " the one through mom Nancy, one through dad Michael should have been treated as duplicates",
                1, rc.relationshipsFound.size());
        for (Relationship r : rc.relationshipsFound) {
            assertEquals(alex, r.individual1);
            assertEquals(betsy, r.individual2);
            assertNotNull(r.chain);
            assertEquals(
                    "The relationship length should be one hops long after collapsing",
                    1, r.chain.size());
        }
    }

    /**
     * Test method for
     * {@link RelationshipCalculator#calculateRelationships(com.mattharrah.gedcom4j.model.Individual, com.mattharrah.gedcom4j.model.Individual)}
     * .
     */
    @Test
    public void testCalculateRelationshipSonFather() {
        Individual james = getPerson("Andrews", "James");
        Individual robert = getPerson("Andrews", "Robert");

        rc.calculateRelationships(robert, james);
        assertNotNull(rc.relationshipsFound);
        System.out.println("Relationships between " + robert + " and " + james);
        for (Relationship r : rc.relationshipsFound) {
            System.out.println("   " + r);
        }
        assertEquals(
                "James is robert's father - there should be one relationship",
                1, rc.relationshipsFound.size());
        Relationship r = rc.relationshipsFound.get(0);
        assertEquals(robert, r.individual1);
        assertEquals(james, r.individual2);
        assertNotNull(r.chain);
        assertEquals("The relationship length should be one hop long", 1,
                r.chain.size());
    }

    /**
     * Helper method to get a person and assert they exist
     * 
     * @param surname
     *            the surname of the person we want
     * @param givenName
     *            the given name of the person we want
     * @return the person
     */
    private Individual getPerson(String surname, String givenName) {
        Individual result = finder.findByName(surname, givenName).get(0);
        assertNotNull("Couldn't find " + givenName + " " + surname
                + " by name in the gedcom", result);
        return result;
    }
}