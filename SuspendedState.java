package Lab1;

public class SuspendedState implements VMState
{
    MyVM vMachine;
 
    public SuspendedState( MyVM vMachine )
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
            System.out.println("Invalid State....");
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }

    
    public void suspend() {
        try{
            System.out.println("Already Suspended....");
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
            System.out.println("Shutting down....");       
            vMachine.vm.powerOnVM_Task(null);           
            vMachine.vm.powerOffVM_Task();
            vMachine.setState( vMachine.getPoweredOffState() );
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
}