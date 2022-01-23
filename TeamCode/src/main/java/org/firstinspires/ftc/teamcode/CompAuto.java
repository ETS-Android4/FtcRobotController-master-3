package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class CompAuto extends LinearOpMode{
    // initialize motors :(
    DcMotor r1 = null;
    DcMotor r2 = null;
    DcMotor l1 = null;
    DcMotor l2 = null;
    DcMotor carousel = null;
    DcMotor intake = null;
    Servo drop = null;
    DcMotor linearSlides = null;
    HardwareMap hwMap;

    // declare variables (¡Lo siento!)
    double startToFreight = 20;
    double freightToWall = 59;
    double wallToHub = 56;
    double wallToCarousel = 118;
    double wallToEnd;

    double robotLength;
    double robotWidth = 11.25;

    double diameterCarouselWheel = 3;

    int slideLevel = 0;
    double level0Distance = 3;
    double level1Distance = 8.5;
    double level2Distance = 14.75;
    double diameterLinearWheel = 1.49606;

    double dropStart = 0.0;
    double dropSpeed = 0.1;

    // init code
    public void init(HardwareMap Map) {
        r1 = hwMap.get(DcMotor.class, "r1");
        r2 = hwMap.get(DcMotor.class, "r2");
        l1 = hwMap.get(DcMotor.class, "l1");
        l2 = hwMap.get(DcMotor.class, "l2");
        carousel = hwMap.get(DcMotor.class, "Carousel");
        intake = hwMap.get(DcMotor.class, "Intake");
        drop = hwMap.get(Servo.class, "Drop");
        linearSlides = hwMap.get(DcMotor.class, "Linear Slides");

        r1.setDirection(DcMotor.Direction.REVERSE);
        r2.setDirection(DcMotor.Direction.REVERSE);
        l1.setDirection(DcMotor.Direction.FORWARD);
        l2.setDirection(DcMotor.Direction.FORWARD);
        carousel.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.REVERSE);
        drop.setDirection(Servo.Direction.FORWARD);
        linearSlides.setDirection(DcMotor.Direction.REVERSE);

        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        carousel.setPower(0);
        intake.setPower(0);
        drop.setPosition(dropStart);
        linearSlides.setPower(0);

        r1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        r1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        carousel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drop.resetDeviceConfigurationForOpMode();
        linearSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    // elapsed time function
    private ElapsedTime runtime = new ElapsedTime();
    //VuforiaObjectDetection webcam = new VuforiaObjectDetection();

    // code to run while play
    @Override
    public void runOpMode() {
        init(hwMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        //webcam.runOpMode();
        runtime.reset();
        startToFreight();
        toHub();

        while (opModeIsActive()) {
            runtime.reset();
            //webcam.runOpMode();
            while (runtime.milliseconds() < 20000) {
                backToFreight();
                toHub();
            }
        }

        toCarousel();
        spinCarousel();
        toLandingZone();
    }
    // resets encoders
    public void resetEncoder() {
        r1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    // run to position code
    public void runToPosition() {
        r1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    // calculates distance in ticks
    public void getTarget(double distanceInInches) {
        // distances --> startToFreight: 20, freightToWall: tomeasure, wallToHub: 56, wallToCarousel: tomeasure, wallToEnd: tomeasure
        double ticks = 537.7;
        double circumference = 11.87;
        double ticksPerInch = ticks/circumference;
        double distanceInTicks = distanceInInches * ticksPerInch;

        r1.setTargetPosition((int) distanceInTicks);
        r2.setTargetPosition((int) distanceInTicks);
        l1.setTargetPosition((int) distanceInTicks);
        l2.setTargetPosition((int) distanceInTicks);
    }
    // calculates angle in ticks
    public double getAngleDistance(double angle) {
        double ticks = 537.7;
        double circumferenceInInches = robotWidth * 3.14;
        double angleDistanceInInches = (angle/360) * circumferenceInInches;

        return angleDistanceInInches;
    }
    // moves forward
    public void moveForward(double power) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(power);
    }
    // moves right
    public void moveRight(double power) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(power);
        l2.setPower(-power);
    }
    // move lefts
    public void moveLeft(double power) {
        r1.setPower(-power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(power);
    }
    // moves backward
    public void moveBackward(double power) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(-power);
    }
    // spins rights
    public void spinRight(double power, double distance) {
        resetEncoder();
        getTarget(distance);
        runToPosition();

        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(power);
        l2.setPower(power);
    }
    // spins left
    public void spinLeft(double power, double distance) {
        resetEncoder();
        getTarget(distance);
        runToPosition();

        r1.setPower(-power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(-power);
    }
    // stop moving
    public void stopMoving() {
        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        carousel.setPower(0);
        intake.setPower(0);
        linearSlides.setPower(0);
    }
    // spin carousel
    public void spinCarousel() {
        double ticks = 537.7;
        double circumference = diameterCarouselWheel * 3.14;
        double ticksPerInch = ticks/circumference;
        double distanceInTicks = circumference * ticksPerInch * 1.59;

        carousel.setTargetPosition((int) distanceInTicks);
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        carousel.setPower(0.5);
    }
    // start position to freight
    public void startToFreight() {
        resetEncoder();
        getTarget(startToFreight);
        runToPosition();
        moveForward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();
    }
    // freight to hub
    public void toHub() {
        resetEncoder();
        getTarget(freightToWall);
        runToPosition();
        moveBackward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

        spinLeft(0.5, getAngleDistance(90));

        resetEncoder();
        getTarget(wallToHub);
        runToPosition();
        moveLeft(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();
    }
    // hub back to freight
    public void backToFreight() {
        resetEncoder();
        getTarget(wallToHub);
        runToPosition();
        moveForward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

        spinRight(0.5, getAngleDistance(90));

        resetEncoder();
        getTarget(freightToWall);
        runToPosition();
        moveBackward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

    }
    // hub to carousel
    public void toCarousel() {
        resetEncoder();
        getTarget(wallToHub);
        runToPosition();
        moveForward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

        spinRight(0.5, getAngleDistance(90));

        resetEncoder();
        getTarget(wallToCarousel);
        runToPosition();
        moveBackward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();

        spinCarousel();
    }
    // carousel to landing zone
    public void toLandingZone() {
        resetEncoder();
        getTarget(wallToEnd);
        runToPosition();
        moveLeft(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();
    }
    // sets linear slide distance :o
    public void setSlideDistance(int levelToGo) {
        resetEncoder();
        if (levelToGo == 0) {
            moveSlides(level0Distance);
        }
        else if (levelToGo == 1) {
            moveSlides(level1Distance);
        }
        else if (levelToGo == 2){
            moveSlides(level2Distance);
        }
    }
    // moves slides :V
    public void moveSlides(double slideDistance) {
        double ticks = 537.7;
        double circumference = diameterLinearWheel * 3.14;
        double ticksPerInch = ticks/circumference;
        double distanceInTicks = circumference * ticksPerInch * slideDistance;

        linearSlides.setTargetPosition((int) distanceInTicks);
        linearSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlides.setPower(1);
    }
    // drop freight ;)
    public void dropFreight() {

    }

    public void getIntake(double power) {
        resetEncoder();
        intake.setPower(power);
    }
}
