package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {

    public DcMotor lfd = null;
    public DcMotor rfd = null;
    public DcMotor lbd = null;
    public DcMotor rbd = null;

    public DcMotor am = null;
    public DcMotor cm = null;

    public Servo as = null;


    HardwareMap hwMap = null;

    public RobotHardware(){

    }

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;

        lfd = hwMap.get(DcMotor.class,"lfd");
        rfd = hwMap.get(DcMotor.class,"rfd");
        lbd = hwMap.get(DcMotor.class,"lbd");
        rbd = hwMap.get(DcMotor.class,"rbd");

        am = hwMap.get(DcMotor.class,"am");
        cm = hwMap.get(DcMotor.class,"cm");

        as = hwMap.get(Servo.class,"as");

        lfd.setDirection(DcMotor.Direction.FORWARD);
        rfd.setDirection(DcMotor.Direction.FORWARD);
        lbd.setDirection(DcMotor.Direction.REVERSE);
        rbd.setDirection(DcMotor.Direction.REVERSE);

        am.setDirection(DcMotor.Direction.FORWARD);
        cm.setDirection(DcMotor.Direction.FORWARD);

        as.setDirection(Servo.Direction.REVERSE);

        lfd.setPower(0);
        rfd.setPower(0);
        lbd.setPower(0);
        rbd.setPower(0);

        am.setPower(0);
        cm.setPower(0);

        as.setPosition(0);

        /*double power = as.getPower();
        as.
        as.setPower(power);
*/


    }

}
