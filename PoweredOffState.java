package Lab1;

import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class PoweredOffState implements VMState
{
    MyVM vMachine;
 
    public PoweredOffState( MyVM vMachine )
    {
        this.vMachine = vMachine;
    }
     
    public void on() {
        try{
            System.out.println("Powering on....");       
            vMachine.vm.powerOnVM_Task(null);
            vMachine.setState( vMachine.getPoweredOnState() );
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }

    
    public void off() {
        try{
            System.out.println("Already powered off....");
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }

    
    public void suspend() {
        try{
            System.out.println("Invalid state....");
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
    
    
   public void reset() {
        try{
            System.out.println("Invalid state....");
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
    
   public void forceShutDown() {
        try{
            System.out.println("Invalid state....");
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
}