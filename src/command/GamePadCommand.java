package command;

import net.java.games.input.*;

public class GamePadCommand extends Command {
    public void gamePadInit() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        Controller gamepad = null;
        Controller stick = null;
        for (int i = 0; i < controllers.length; i++) {

            if (controllers[i].getType() == Controller.Type.GAMEPAD) {
                gamepad = controllers[i];
                System.out.println(gamepad);
            }

            if (controllers[i].getType() == Controller.Type.STICK) {
                stick = controllers[i];
                System.out.println(stick);
            }


/*            System.out.println(controllers[i]);
            System.out.println(controllers[i].getType());*/
        }

//        Component[] gamepadComponents = gamepad.getComponents();
//        Component[] stickComponents = stick.getComponents();
//
//        for (int i =0; i < gamepadComponents.length; i++){
//            System.out.println(gamepadComponents[i].getName());
//        }
////        for (int i =0; i < stickComponents.length; i++){
////            System.out.println(stickComponents[i].getName());
////        }

        EventQueue eventQueue = gamepad.getEventQueue();
        Event event = new Event();
        Boolean stopped = false;
        while (!stopped) {
            gamepad.poll();
            eventQueue.getNextEvent(event);

            Component component = event.getComponent();

            if (component != null) {
                Component.Identifier identifier = component.getIdentifier();
                float data = component.getPollData();
                if (identifier == Component.Identifier.Button._0) {
                    System.out.println("0");
                    btn_0 = true;
                }
                if (identifier == Component.Identifier.Button._1) {
                    System.out.println("1");
                    btn_1 = true;
                }
                if (identifier == Component.Identifier.Button._2) {
                    System.out.println("2");
                    btn_2 = true;
                }
                if (identifier == Component.Identifier.Button._3) {
                    System.out.println("3");
                    btn_3 = true;
                }
                if (identifier == Component.Identifier.Axis.X) {
                    if (data < 0)
                        System.out.println("down" + data);
                    if (data > 0)
                        System.out.println("up" + data);
                }
                if (identifier == Component.Identifier.Axis.Y) {
                    if (data < 0)
                        System.out.println("left" + data);
                    if (data > 0)
                        System.out.println("right" + data);
                }
                if (identifier == Component.Identifier.Button.LEFT_THUMB || identifier == Component.Identifier.Button.LEFT_THUMB2 || identifier == Component.Identifier.Button.LEFT_THUMB3) {
                    System.out.println("left thumb" + data);
                }
                if (identifier == Component.Identifier.Button.RIGHT_THUMB) {
                    System.out.println("right thumb" + data);
                }
                if (identifier == Component.Identifier.Button.THUMB) {
                    System.out.println("thumb 1" + data);
                }
                if (identifier == Component.Identifier.Button.THUMB2) {
                    System.out.println("thumb 2" + data);
                }
            }
        }
    }
}