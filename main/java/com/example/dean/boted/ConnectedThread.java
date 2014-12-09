package com.example.dean.boted;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Dean on 9/14/2014.
 */
public class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void stop_it() {
        write(new byte[]{49, 50, 0x20, 0, 0, 0});
        write(new byte[]{49, 50, 0x20, 0, 0, 0});
        write(new byte[]{49, 50, 0x20, 0, 0, 0});
    }

    public void run(ArrayList<String> program) {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        for (String s : program) {
            if (s.equals("LED on")) {
                write(new byte[]{49, 50, 0x24, 1, 0, 0});
                Log.v("yo", s);
            }
            else if (s.equals("LED off")) {
                write(new byte[]{49, 50, 0x24, 0, 0, 0});
            }
            else if (s.equals("Drive forward")) {
                write(new byte[]{49, 50, 0x20, 100, 100, 0});
            }
            else if (s.equals("Drive backward")) {
                write(new byte[]{49, 50, 0x20, -100, -100, 0});
            }
            else if (s.equals("Stop")) {
                write(new byte[]{49, 50, 0x20, 0, 0, 0});
            }
            else if (s.equals("Turn Right")) {
                write(new byte[]{49, 50, 0x20, 50, -50, 0});
            }
            else if (s.equals("Turn Left")) {
                write(new byte[]{49, 50, 0x20, -50, 50, 0});
            }
            else if (s.equals("Turn Right 90 degrees")) {
                write(new byte[]{49, 50, 0x23, -18, 0, 0});
            }
            else if (s.equals("Turn Left 90 degrees")) {
                write(new byte[]{49, 50, 0x23, 18, 0, 0});
            }
            else if (s.equals("Play Beep")) {
                write(new byte[]{49, 50, 0x26, 0, 0, 0});
            }
            else if (s.equals("Wait 1 second")) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (s.contains("Wait X milliseconds")) {
                int x = Integer.parseInt(s.substring(s.indexOf("(")+1,s.lastIndexOf(")")));
                try {
                    sleep(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (s.contains("Drive forward X wheel revolutions")) {
                int x = Integer.parseInt(s.substring(s.lastIndexOf("(")+1,s.lastIndexOf(")")));
                x = x*5;
                write(new byte[]{49, 50, 0x21, (byte)x, (byte)x, 0});
            }
            else if (s.contains("Drive forward at X% speed")) {
                int x = Integer.parseInt(s.substring(s.lastIndexOf("(")+1,s.lastIndexOf(")")));
                write(new byte[]{49, 50, 0x20, (byte)x, (byte)x, 0});
            }
            else if (s.contains("Turn X Degrees Right")) {
                int x = Integer.parseInt(s.substring(s.lastIndexOf("(")+1,s.lastIndexOf(")")));
                x = (int)(x/5.0);
                write(new byte[]{49, 50, 0x23, (byte)-x, 0, 0});
            }
            else if (s.contains("Turn X Degrees Left")) {
                int x = Integer.parseInt(s.substring(s.lastIndexOf("(")+1,s.lastIndexOf(")")));
                x = (int)(x/5.0);
                write(new byte[]{49, 50, 0x23, (byte)x, 0, 0});
            }
            else if (s.contains("Drive, X% left motor, Y% right motor")) {
                int x = Integer.parseInt(s.substring(s.indexOf("(")+1,s.indexOf(")")));
                int y = Integer.parseInt(s.substring(s.lastIndexOf("(")+1,s.lastIndexOf(")")));
                write(new byte[]{49, 50, 0x20, (byte)x, (byte)y, 0});
            }
            else if (s.contains("Play Tone at X Hz")) {
                int x = Integer.parseInt(s.substring(s.indexOf("(")+1,s.indexOf(")")));
                x = (int)(x/20.0)+1;
                write(new byte[]{49, 50, 0x25, (byte)x, 5, 0});
            }
        }

        // Keep listening to the InputStream until an exception occurs
            //               try {

//                try {
//                    write(new byte[]{49, 50, 54});
//                    sleep(1000);
//                    write(new byte[]{49, 50, 55});
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            // Read from the InputStream
            //bytes = mmInStream.read(buffer);
            // Send the obtained bytes to the UI activity
            // mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
            //         .sendToTarget();

//                }
//                catch (IOException e) {
//                    break;
//                }
    }

    /* Call this from the main activity to send data to the remote device */
    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) { }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}