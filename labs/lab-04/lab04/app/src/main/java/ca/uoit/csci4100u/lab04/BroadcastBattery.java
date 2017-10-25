package ca.uoit.csci4100u.lab04;

/**
 * Created by brad on 10/25/17.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

/**
 * Created by shayne on 2017-10-20.
 */

public class BroadcastBattery extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intert){
        IntentFilter ifilter = new IntentFilter (Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null,ifilter);

        //charging/charged?
        String charge = "N/A";
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCharged = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status== BatteryManager.BATTERY_STATUS_FULL;
        if (isCharged == true){
            charge = "Charged";
        }else {
            charge = "Charging";
        }
        Toast.makeText(context, charge, Toast.LENGTH_LONG);

        //How charging?
        String usb = "N/A";
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        if (usbCharge == true){
            usb = "USB Charging";
        }else{
            usb = "AC Charging";
        }
        Toast.makeText(context, usb, Toast.LENGTH_LONG);
        //current level
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1);

        float battery_Pct = level/(float)scale;
        int bat = (int)battery_Pct;

        //health
        int stat = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
        String health = "N/A";
        switch(stat){
            case BatteryManager.BATTERY_HEALTH_COLD:
                health = "Cold";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                health = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                health = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                health  = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                health = "Over Heated";
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                health = "Unknown";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                health = "Unspecified Failure";
                break;
        }
    }
}

