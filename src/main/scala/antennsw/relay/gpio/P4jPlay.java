package antennsw.relay.gpio;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.platform.Platform;
import com.pi4j.platform.Platforms;

public class P4jPlay {
    private static final int PIN_LED = 3;
//    private static final int PIN_LED = 22; // PIN 15 = BCM 22
    private static int pressCount = 0;
    private static final int PIN_BUTTON = 24; // PIN 18 = BCM 24

    public static void main(String[] args) {
        Context pi4j = Pi4J.newAutoContextAllowMocks();
        Platforms platforms = pi4j.platforms();
        Platform platform = platforms.defaultPlatform();

        platforms.describe().print(System.out);


        var led = pi4j.digitalOutput().create(PIN_LED);

        led.high()
        Thread.sleep(250);
        led.low()
        Thread.sleep(1000);






        if (led.state() == DigitalState.HIGH) {
            led.low();
        } else {
            led.high();
        }
//        Thread.sleep(500 / (pressCount + 1));
    }

}
