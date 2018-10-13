/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CH07_Object_Oriented_Design.CallCentre;

/**
 * <b>Call Center:</b> Imagine you have a call center with three levels of
 * employees: respondent, manager, and director. An incoming telephone call must
 * be first allocated to a respondent who is free. If the respondent can't
 * handle the call, he or she must escalate the call to a manager. If the
 * manager is not free or not able to handle it, then the call should be
 * escalated to a director. Design the classes and data structures for this
 * problem. Implement a method dispatchCall() which assigns a call to the first
 * available employee.
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class Test {
    
    /**
     * Main method...uncomment to run
     * @param args command line argument
     *//*
    public static void main(String[] args) {
        CallCentre callCentre = new CallCentre();
        World theWorld = new World();
        theWorld.register(callCentre);
        callCentre.makeEmployee(Level.Respondent, "James");
        callCentre.makeEmployee(Level.Respondent, "Bond");
        CallCentre.Employee christiano = callCentre.makeEmployee(Level.Respondent, "Christiano");
        callCentre.makeEmployee(Level.Respondent, "Ronaldo");

        CallCentre.Employee Luis = callCentre.makeEmployee(Level.Manager, "Luis");
        callCentre.makeEmployee(Level.Manager, "Figo");

        CallCentre.Employee maradona = callCentre.makeEmployee(Level.Director, "Maradona");

        theWorld.dialIn();
        theWorld.dialIn();
        theWorld.dialIn();
        theWorld.dialIn();
        theWorld.dialIn();
        theWorld.dialIn();
        theWorld.dialIn();
        theWorld.dialIn();
        theWorld.dialIn();

        christiano.drop();
        theWorld.dialIn();

        maradona.drop();
        theWorld.dialIn();

        Luis.drop();
        theWorld.dialIn();
    }
*/
}
