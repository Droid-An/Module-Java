package com.prep._2_1;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Car car = new Car();
        Engine engine = new Engine();
        Set<Cog> cogs = Set.of(new Cog(), new Cog(), new Cog());
        Gears gears = new Gears();
        Cambelt cambelt = new Cambelt();
        car.start(engine, cogs, gears, cambelt);
    }

    static class Car {
        void start(Engine engine, Set<Cog> cogs, Gears gears, Cambelt cambelt) {
            engine.rev(cogs, gears, cambelt);
            System.out.println("Car Started");
        }

    }

    static class Engine {
        void rev(Set<Cog> cogs, Gears gears, Cambelt cambelt) {
            gears.initialise(cogs);
            cambelt.initialise();
        }
    }


    static class Gears {
        void initialise(Set<Cog> cogs) {
            cogs.forEach(cog -> cog.spin());
            // Initialisation logic
        }
    }

    static class Cog {
        void spin() {
            // Spin logic
        }
    }


    static class Cambelt {
        void initialise() {
            // Initialisation logic
        }
    }
}