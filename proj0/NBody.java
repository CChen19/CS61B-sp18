public class NBody {
    public static String imageToDraw = "images/starfield.jpg";
    public static int numOfPlants;
    public static double readRadius(String s) {
        In in = new In(s);
        in.readInt();
        double secondItemInFile = in.readDouble();

        return secondItemInFile;
    }

    public static Planet[] readPlanets(String s) {
        In in = new In(s);
        numOfPlants = in.readInt();
        in.readDouble();
        Planet[] planetsArray = new Planet[numOfPlants];
        for (int i = 0; i < numOfPlants; i ++) {
            planetsArray[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return planetsArray;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);

        for (double t = 0; t < T; t += dT) {
            // Calculate the net x and y forces for each planet, storing these in the xForces and yForces arrays respectively.
            double[] xForces = new double[numOfPlants];
            double[] yForces = new double[numOfPlants];
            for (int i = 0; i < numOfPlants; i ++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            // Call update on each of the planets. This will update each planet’s position, velocity, and acceleration.
            for (int i = 0; i < numOfPlants; i ++) {
                planets[i].update(dT, xForces[i], yForces[i]);
            }
            // Draw the background image.
            StdDraw.picture(0, 0, imageToDraw);
            // Draw all of the planets.
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
