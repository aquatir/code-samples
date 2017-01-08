package learntocode.patterns.MVCgui;

/**
 * Controller is used to modify state of model.
 * NOTE: Controller only allows you to MODIFY state of model but not ACQUIRE it.
 * <br>
 * Controller gets model as argument in constructor.
 */
public class Controller implements  ControllerInterface {

    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void setDisplayedNumber(int number) {
        model.setDisplayedNumber(number);
    }

    /* Controller is used to call for model method */
    @Override
    public void incrementDisplayedNumber() {
        model.setDisplayedNumber(model.getDisplayedNumber() + 1);
    }
}
