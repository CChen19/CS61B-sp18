public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) + (yyPos-p.yyPos)*(yyPos-p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return G * mass * p.mass / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos-xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos-yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double netForceExertedByX = 0;
        for (Planet p : ps) {
            if (equals(p)) {
                continue;
            }
            netForceExertedByX += calcForceExertedByX(p);
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double netForceExertedByY = 0;
        for (Planet p : ps) {
            if (equals(p)) {
                continue;
            }
            netForceExertedByY += calcForceExertedByY(p);
        }
        return netForceExertedByY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}