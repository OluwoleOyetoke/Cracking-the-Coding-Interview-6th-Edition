/*
 * Copyright (C) 2018 Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Others;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke at gmail.com
 */
public class SampleTest {

@BeforeClass
public static void setUpClass(){
    
}

@AfterClass
public static void tearDownClass(){
    
}

@Before
public void setUpMethod(){
    
}

@After
public void tearDownMethod(){
    
}

@Test
public void testMethodA(){
    System.out.println("Testing methodA");
    int expected = 99;
    assertEquals(expected, 99);
}

@Test
public void testMethodB(){  
    System.out.println("Testing methodB");
    int expected = 99;
    assertEquals(expected, 65);
}

}
