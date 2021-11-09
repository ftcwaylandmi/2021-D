package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    RobotHardware rHW = new RobotHardware();
    double limitopen = 0.55;
    double limitclosed = 0;
    double servopos = 0;
    double incr = 0.01;
    int modulater = 0;
    int modratio = 3;
    public void initHW(HardwareMap ahwMap){
        rHW.init(ahwMap);
    }

    public void drive(double power){
        rHW.lfd.setPower(power);
        rHW.rfd.setPower(power);
        rHW.lfd.setPower(power);
        rHW.rbd.setPower(power);
    }

    public void leftDrive(double power){
        rHW.rfd.setPower(-power);
        rHW.rbd.setPower(power);
    }

    public void rightDrive(double power){
        rHW.lfd.setPower(power);
        rHW.lbd.setPower(-power);
    }

    public void spinCarouselLeft(double spin){
        rHW.cm.setPower(spin);
    }

    public void spinCarouselRight(double spin){
        rHW.cm.setPower(-spin);
    }

    public void moveArm(double power){
        rHW.am.setPower(power);
    }

    public void openArmServo(){
       modulater++;
       if((modulater % modratio) == 0) {
           servopos += incr;
           if (servopos > limitopen) {
               servopos = 0.55;
           }
           rHW.as.setPosition(servopos);
       }
    }
    public void closeArmServo(){
        modulater++;
        if((modulater % modratio) == 0) {
            servopos -= incr;
            if (servopos < limitclosed) {
                servopos = 0;
            }
            rHW.as.setPosition(servopos);
        }

    }

}
