package ca.uoit.csci4100u.lab04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.filter;
import static android.R.attr.layout_above;

public class lab04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab04);

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryLevel inten = new batteryLevel();
        registerReceiver(inten,filter);
    }

    class batteryLevel extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String[] strings = new String[4];
            NotificationCompat.InboxStyle is = new NotificationCompat.InboxStyle();
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //current level
            TextView txtView = (TextView) ((Activity) context).findViewById(R.id.batLevel);
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float battery_Pct = level / (float) scale;
            String bat = String.valueOf((int) (battery_Pct * 100));

            strings[0] = "Battery %:" + bat;

            //charging/charged?
            String charge = "N/A";
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                strings[1] = "Battery: Charged";
            } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                strings[1] = "Battery: Charging";
            } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                strings[1] = "Battery: Discharging";
            } else {
                strings[1] = "Battery: Not Charging";
            }

            //How charging?
            String usb = "N/A";
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
            if (usbCharge == true) {
            } else if (usbCharge == false) {
                strings[2] = "Battery: AC Charging";
            } else {
                strings[2] = "Battery Not Charging";
            }
//            mBuilder.setSmallIcon(R.drawable.logo);
//            mBuilder.setContentTitle("Error");
//            mBuilder.setContentText(usb);
//
//            nm.notify(0,mBuilder.build());
            //health
            int stat = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            strings[3] = "Battery: N/A";
            switch (stat) {
                case BatteryManager.BATTERY_HEALTH_COLD:
                    strings[3] = "Battery: Cold";
                    break;
                case BatteryManager.BATTERY_HEALTH_DEAD:
                    strings[3] = "Battery: Dead";
                    break;
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    strings[3] = "Battery: Good";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                    strings[3] = "Battery: Over Voltage";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    strings[3] = "Battery: Over Heated";
                    break;
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    strings[3] = "Battery: Unknown";
                    break;
                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    strings[3] = "Battery: Unspecified Failure";
                    break;
            }

            is.setBigContentTitle("Battery");
            is.addLine(strings[0]);
            is.addLine(strings[1]);
            is.addLine(strings[2]);
            is.addLine(strings[3]);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext());
            mBuilder.setContentTitle("Battery");
            mBuilder.setTicker("Battery Alert!");
            mBuilder.setSmallIcon(R.drawable.logo);

            mBuilder.setStyle(is);
            nm.notify(0, mBuilder.build());

        }
    }}
