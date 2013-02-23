package Lab1;

import java.net.URL;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

/**
 * Write a description of class MyVM here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyVM
{
    // instance variables 
    private VMState cState;
    private VMState poweredOnState;
    private VMState poweredOffState;
    private VMState suspendedState;
    
    private ServiceInstance si ;
    private Folder rootFolder;
    private ManagedEntity mes;
    public static VirtualMachine vm ;
    
    public MyVM() throws Exception
    {
        // initialise instance variables
        poweredOnState = new PoweredOnState(this);
        poweredOffState = new PoweredOffState(this);
        suspendedState = new SuspendedState(this);
        
        si = new ServiceInstance(
            new URL(Config.getVmwareHostURL()), Config.getVmwareLogin(), Config.getVmwarePassword(), true);
        
        rootFolder = si.getRootFolder();
        String name = rootFolder.getName();
        System.out.println("root:" + name);
        
        mes = new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine","sudharshan.ramakumar@sjsu.edu");
        if(mes==null)
        {
            return;
        }
        vm = (VirtualMachine) mes; 
        
        System.out.println("\n============ Virtual Machine Info ============");
        if ( vm != null )
        {
            System.out.println("STATE........"+vm.getRuntime().getPowerState());
        
        
            if(vm.getRuntime().getPowerState().toString().equalsIgnoreCase("poweredon"))
                cState = poweredOnState ;
            else if(vm.getRuntime().getPowerState().toString().equalsIgnoreCase("poweredoff"))
                cState = poweredOffState ;
            else if(vm.getRuntime().getPowerState().toString().equalsIgnoreCase("suspended"))
                cState = suspendedState ;
            
            VirtualMachineConfigInfo vminfo = vm.getConfig();
            VirtualMachineCapability vmc = vm.getCapability();
            System.out.println("Hello " + vm.getName());
            System.out.println("GuestOS: " + vminfo.getGuestFullName());
            System.out.println("Multiple snapshot supported: " + vmc.isMultipleSnapshotsSupported());
            VirtualMachineSummary vmsum = vm.getSummary() ;
            VirtualMachineQuickStats vmstats = vmsum.getQuickStats() ;
            System.out.println("Status "+vmstats.getGuestHeartbeatStatus().toString());
            System.out.printf( "Guest Memory Usage: %d MB\n", vmstats.getGuestMemoryUsage() ) ;
            System.out.printf( "Host Memory Usage: %d MB\n", vmstats.getHostMemoryUsage() ) ;
            System.out.printf( "Overall CPU Usage: %d MHz\n", vmstats.getOverallCpuUsage() ) ;
        }
    }
    
    public void powerOn() 
    {
        try {
              cState.on();
        } 
        catch ( Exception e ){ 
            System.out.println( e.toString() ) ; 
        }
    }

    /**
     * Power Off the Virtual Machine
     */
    public void powerOff() 
    {
        try {
              cState.off();
        } 
        catch ( Exception e ) {
            System.out.println( e.toString() ) ; 
        }
    }

     /**
     * Reset the Virtual Machine
     */

    public void reset() 
    {
        try {
              cState.reset();
        }
        catch ( Exception e ) {
            System.out.println( e.toString() ) ; 
        }
    }


     /**
     * Suspend the Virtual Machine
     */
 
    public void suspend() 
    {
        try {
              cState.suspend();
        } 
        catch ( Exception e ) {
            System.out.println( e.toString() ) ; 
        }
    }


     /**
     * Shutdown the Virtual Machine
     */
 
    public void forceShutDown() 
    {
        try {
                 
              cState.off();
        } 
        catch ( Exception e ) {
            System.out.println( e.toString() ) ; 
        }
    }  


     /**
     * Shutdown all Virtual Machines
     */
 
    public void shutDownAll() 
    {
        try {
            VirtualMachine allVM;
            ManagedEntity[] allMES = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
            String currentState;
            
            if(allMES==null || allMES.length==0)
            {
                return;
            }
            
            for(int i=0;i<allMES.length;i++){
                allVM = (VirtualMachine) allMES[i];
                currentState = allVM.getRuntime().getPowerState().toString();
                if(currentState.equalsIgnoreCase("poweredon")){ 
                    System.out.println("Shutting down "+allVM.getName());     
                    allVM.powerOffVM_Task();
                }
                else if(currentState.equalsIgnoreCase("suspended")){
                    System.out.println("Shutting down "+allVM.getName());     
                    allVM.powerOnVM_Task(null);
                    allVM.powerOffVM_Task();
                }
            }
        } 
        catch ( Exception e ) {
            System.out.println( e.toString() ) ; 
        }
    }   
    
    void setState(VMState state) {
        cState = state;
    }
    
    public VMState getPoweredOnState(){
        return this.poweredOnState;
    }
    
    public VMState getPoweredOffState(){
        return this.poweredOffState;
    }
    
    public VMState getSuspendedState(){
        return this.suspendedState;
    }

}
