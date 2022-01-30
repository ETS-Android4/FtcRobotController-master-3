package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class FinalCompAuto extends LinearOpMode {
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

    double dropStart = 0.6;
    double dropRiseFall = dropStart + 0.1;
    double dropEnd = 0;

    // init code
    public void init(HardwareMap Map) {
        r1 = hardwareMap.get(DcMotor.class, "r1");
        r2 = hardwareMap.get(DcMotor.class, "r2");
        l1 = hardwareMap.get(DcMotor.class, "l1");
        l2 = hardwareMap.get(DcMotor.class, "l2");
        carousel = hardwareMap.get(DcMotor.class, "Carousel");
        intake = hardwareMap.get(DcMotor.class, "Intake");
        drop = hardwareMap.get(Servo.class, "Drop");
        linearSlides = hardwareMap.get(DcMotor.class, "Linear Slides");

        r1.setDirection(DcMotor.Direction.REVERSE);
        r2.setDirection(DcMotor.Direction.REVERSE);
        l1.setDirection(DcMotor.Direction.FORWARD);
        l2.setDirection(DcMotor.Direction.FORWARD);
        carousel.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.REVERSE);
        drop.setDirection(Servo.Direction.FORWARD);
        linearSlides.setDirection(DcMotor.Direction.FORWARD);

        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        carousel.setPower(0);
        intake.setPower(0);
        dropStart = drop.getPosition();
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
        //drop.resetDeviceConfigurationForOpMode();
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
        runtime.reset();
        waitForStart();
        /*startToHub();
        telemetry.addData("Status", "To Hub");
        telemetry.update();
        //linearSlides();
        toFreight();
        telemetry.addData("Status", "To Freight");
        telemetry.update();
        toHub();
        telemetry.addData("Status", "To Hub");
        telemetry.update();
        //linearSlides();
        toCarousel();
        telemetry.addData("Status", "To Carousel");
        telemetry.update();
        //spinCarousel();
        toLandingZone();
        telemetry.addData("Status", "To Landing Zone");
        telemetry.update();*/

        /*moveRight(0.2, 500);
        telemetry.addData("Status", "Move Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveForward(0.5, 2200);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinCarousel(3000);
        telemetry.addData("Status", "Spin Carousel");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveBackward(0.5, 2100);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveRight(0.2, 700);
        telemetry.addData("Status", "Move Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();*/

        spinLeft(0.6, 500);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinRight(0.6, 500);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinLeft(0.6, 1000);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinRight(0.6, 1000);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinLeft(0.6, 1500);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinRight(0.6, 1500);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinLeft(0.6, 2000);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinRight(0.6, 2000);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        /*moveBackward(0.2, 800);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        servoTest();
        telemetry.addData("Status", "Linear Slides and Servo");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveForward(0.2, 900);
        telemetry.addData("Status", "Move Forward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        spinLeft(0.6, 1780);
        telemetry.addData("Status", "Spin Left");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveLeft(0.2, 600);
        telemetry.addData("Status", "Move Right");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveForward(0.2, 2250);
        telemetry.addData("Status", "Move Forward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveRight(0.2, 1500);
        telemetry.addData("Status", "Move Right");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveForward(0.2, 500);
        telemetry.addData("Status", "Move Forward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();*/

        while (opModeIsActive()) {
            telemetry.addData("Status", runtime.milliseconds());
        }
    }
    /*
            toCarousel();
            spinCarousel();
            toLandingZone();
        }
        // resets encoders
        // calculates distance in ticks
    */
    // moves forward

    public void servoTest() {
        drop.setPosition(0.8);
        sleep(500);
        linearSlides.setPower(0.1);
        sleep(3000);
        linearSlides.setPower(0);
        sleep(1000);
        drop.setPosition(0.2);
        sleep(1000);
        drop.setPosition(0.8);
        sleep(1000);
        linearSlides.setPower(-0.05);
        sleep(3000);
        linearSlides.setPower(0);
        drop.setPosition(0.9);
        sleep(2000);
    }

    public void spinRight(double power, int time) {
        r1.setPower(-power);
        r2.setPower(-power);
        l1.setPower(power);
        l2.setPower(power);
        sleep(time);
    }

    // moves right
    public void moveRight(double power, int time) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(power);
        l2.setPower(-power);
        sleep(time);
    }

    // move lefts
    public void moveLeft(double power, int time) {
        r1.setPower(-power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(power);
        sleep(time);
    }

    // moves backward
    public void spinLeft(double power, int time) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(-power);
        sleep(time);
    }

    // spins rights
    public void moveForward(double power, int time) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(power);
        l2.setPower(power);
        sleep(time);
    }

    // spins left
    public void moveBackward(double power, int time) {
        r1.setPower(-power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(-power);
        sleep(time);
    }

    // stop moving
    public void stopMoving(int time) {
        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        carousel.setPower(0);
        intake.setPower(0);
        linearSlides.setPower(0);
        sleep(time);
    }

    // spin carousel
    //prob 3
    public void spinCarousel(int time) {
        carousel.setPower(-0.1);
        sleep(time);
    }

    public void spinIntake() {
        // dropStart is the initial position
        // dropRiseFall is dropStart + 0.1
        // dropEnd is dropStart + 0.5
        drop.setPosition(dropStart);
        sleep(500);
        intake.setPower(0.1);
        sleep(500);
        drop.setPosition(dropRiseFall);
        sleep(1000);
    }
    //8 seconds
    public void linearSlides(double power1, double power2) {
        linearSlides.setPower(power1);
        sleep(3000);
        linearSlides.setPower(0);
        sleep(500);
        /*drop.setPosition(dropEnd);
        sleep(500);
        drop.setPosition(dropRiseFall);
        sleep(500);*/
        linearSlides.setPower(power2);
        sleep(3500);
        linearSlides.setPower(0);
        /*drop.setPosition(dropStart);
        sleep(500);*/
    }

    //0.5 seconds
    public void startToHub() {
        moveBackward(0.75, 1000);
        stopMoving(1);
        spinRight(0.5, 300);
        stopMoving(1);
        moveBackward(0.75, 1000);
        stopMoving(1);
        linearSlides(0.25, -0.1);
    }

    // start position to freight
    //5.3
    public void toFreight() {
        moveForward(0.75, 1000);
        stopMoving(1);
        spinLeft(0.5, 300);
        stopMoving(1);
        spinIntake();
        moveForward(0.75, 2000);
        stopMoving(1);
        spinRight(1, 2000);
        stopMoving(100);
    }

    // freight to hub
    //4s
    public void toHub() {
        moveBackward(0.5, 2000);
        stopMoving(1);
        spinRight(0.5, 300);
        stopMoving(1);
        moveBackward(0.75, 1000);
        stopMoving(1);
        linearSlides(0.25, -0.1);
        stopMoving(100);
    }

    // hub to carousel
    //3.5
    public void toCarousel() {
        spinRight(0.5, 500);
        stopMoving(1);
        moveForward(0.75, 3000);
        stopMoving(1);
        spinCarousel(3000);
        stopMoving(1);
    }

    // carousel to landing zone
    //1.5s
    public void toLandingZone() {
        moveRight(0.5, 1500);
        stopMoving(1);
    }
}