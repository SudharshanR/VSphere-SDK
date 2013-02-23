package Lab1;

public class PoweredOnState implements VMState
{
   MyVM vMachine;
 
    public PoweredOnState( MyVM vMachine )
    {
        this.vMachine = vMachine;
    }
     
    public void on() {
        try{
            System.out.println("System already powered on....");
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }

    
    public void off() {
        try{
         System.out.println("Powering off....");            
         vMachine.vm.powerOffVM_Task();
         vMachine.setState( vMachine.getPoweredOffState() );
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }

    
    public void suspend() {
        try{
         System.out.println("Suspending....");
         vMachine.vm.suspendVM_Task();
         vMachine.setState( vMachine.getSuspendedState() );
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }    
    
   public void reset() {
        try{
            System.out.println("Restarting....");         
            vMachine.vm.resetVM_Task();
            vMachine.setState( vMachine.getPoweredOnState() );
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }   
    
   public void forceShutDown() {
        try{
             System.out.println("Powering off....");            
             vMachine.vm.powerOffVM_Task();
             vMachine.setState( vMachine.getPoweredOffState() );
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
}