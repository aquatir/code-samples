package patterns.mediator;

class RunMediatorExample {

    public static void main(String[] args) {

        Mediator mediator = new ConcreteMediator();

        Colleague colleagueOne = new ConcreteColleagueOne(mediator);
        Colleague colleagueTwo = new ConcreteColleagueTwo(mediator);

        mediator.addColleague(colleagueOne);
        mediator.addColleague(colleagueTwo);

        colleagueOne.send("How are you?");
        colleagueTwo.send("I'm fine");
    }

}
