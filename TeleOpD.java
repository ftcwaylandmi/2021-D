package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="TeleOpD", group="11846")
public class TeleOpD extends OpMode{
    Robot myrobot =  new Robot();
    double left = 0.00;
    double right = 0.00;
    double carouselleft = 0.00;
    double carouselright = 0.00;

    @Override
    public void init(){
        myrobot.initHW(hardwareMap);
    }

    @Override
    public void loop() {
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        carouselleft = gamepad1.left_trigger;
        carouselright = gamepad1.right_trigger;

        myrobot.moveArm(gamepad2.right_stick_y/2);

        if(gamepad2.a) {
            myrobot.openArmServo();
        }
        if(gamepad2.b) {
            myrobot.closeArmServo();
        }

        telemetry.addData("Hand position", myrobot.rHW.as.getPosition());
        telemetry.update();
        myrobot.leftDrive(left);
        myrobot.rightDrive(right);
        myrobot.spinCarouselLeft(carouselleft);
        myrobot.spinCarouselRight(carouselright);

    }
}
