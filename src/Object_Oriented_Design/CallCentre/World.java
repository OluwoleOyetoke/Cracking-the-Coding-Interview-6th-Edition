/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object_Oriented_Design.CallCentre;

import java.util.ArrayList;

/**
 * Using the observer Pattern
 *
 *  * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
public class World {

    ArrayList<CallListener> callCentres;

    World() {
        callCentres = new ArrayList();
    }

    public void register(CallListener newCentre) {
        callCentres.add(newCentre);
    }

    public void dialIn() {
        for (CallListener x : callCentres) {
            System.out.println("\nCALL INCOMING");
            x.dispatchCall();
        }
    }
}
