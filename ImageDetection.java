package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.tensorflow.lite.task.vision.detector.Detection;

@TeleOp( name = "ImageDetection" , group = "11846")
public class ImageDetection extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker",
    };
    private static final String VUFORIA_KEY =
            "AXQRyrb/////AAABmf7j40owPER9tF+D89vaCrQEm6Lo7uQ0lShlb45tqPAEEz7bn4Mlp23OJ+26k7d5W6RTH6HUwtnhv+aLeg4tpL8ExoI9hSbnb0CylUIvSeFeC20q/igxMkiSFg1EX+qxLQeQCAvqQf4fUrXMYK6xNnjNiPeBtT11bQqeWqg9YYgXv6RdOmmIs6OsOnKCp0e5HTqJOX1d364dwwUBaxxm9AxoMnqF6rp0XpknpxO8zff/PRdO0iCzCGh8fEJxT2keJEgvg+8HVK8liNhh10hVtR9O+AsQJsUhbO3REnn0Z/QbCUD6iGoKqrNNd8NV92ZSmw786RWACszpPi6Cf1CUfMHIltcQ0nhK7K/awL8C3ieq";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        initVufoira();
        initTfod();

        if(tfod != null){
            tfod.activate();
            tfod.setZoom(2.5,16.0/9.0);
        }

        telemetry.addData(">","Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()){
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format(" Label (%d)", i), recognition.getLabel())
                            ;
                            telemetry.addData(String.format(" left,top(%d", i), "%.03f , %.03f", recognition.getLeft(), recognition.getTop());
                            i++;

                            telemetry.update();
                        }
                    }
                }
            }
        }
    }

    private void initVufoira(){
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class,"Webcam 1");
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    private void initTfod() {
    int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    tfodParameters.minResultConfidence = 0.8f;
    tfodParameters.isModelTensorFlow2 = true;
    tfodParameters.inputSize = 320;
    tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
}
}

