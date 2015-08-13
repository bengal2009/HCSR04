#include <stdio.h>
#include <stdlib.h>
#include <wiringPi.h>

#define TRUE 1

#define TRIG 2
#define ECHO 0

void setup() {
        wiringPiSetup();
        pinMode(TRIG, OUTPUT);
        pinMode(ECHO, INPUT);

        //TRIG pin must start LOW
        digitalWrite(TRIG, LOW);
        delay(30);
}

double getCM() {
        //Send trig pulse
        digitalWrite(TRIG, HIGH);
        delayMicroseconds(20);
        digitalWrite(TRIG, LOW);

        //Wait for echo start
        while(digitalRead(ECHO) == LOW);

        //Wait for echo end
       double startTime = micros();
        while(digitalRead(ECHO) == HIGH);
		double travelTime = micros() - startTime;

        //Get distance in cm
        double distance = travelTime / 58;
	        return distance;
}

int main(void) {
        setup();

        //printf("%ldcm\n", getCM());
		printf("%9.1f\n", getCM());
		
        return 0;
}



