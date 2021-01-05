public class Planet {
    private static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet planet) {
        xxPos = planet.xxPos;
        yyPos = planet.yyPos;
        xxVel = planet.xxVel;
        yyVel = planet.yyVel;
        mass = planet.mass;
        imgFileName = planet.imgFileName;
    }

    public double calcDistance(Planet planet) {
        double dx = xxPos - planet.xxPos;
        double dy = yyPos - planet.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy(Planet planet) {
        double r = calcDistance(planet);
        double F = G * mass * planet.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet planet) {
        double r = calcDistance(planet);
        double F = calcForceExertedBy(planet);
        double dx = planet.xxPos - xxPos;
        double Fx = F * dx / r;
        return Fx;
    }

    public double calcForceExertedByY(Planet planet) {
        double r = calcDistance(planet);
        double F = calcForceExertedBy(planet);
        double dy = planet.yyPos - yyPos;
        double Fy = F * dy / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double FnetX = 0;
        for (Planet planet : planets) {
            if (this.equals(planet))
                continue;
            FnetX += calcForceExertedByX(planet);
        }
        return FnetX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double FnetY = 0;
        for (Planet planet : planets) {
            if (this.equals(planet))
                continue;
            FnetY += calcForceExertedByY(planet);
        }
        return FnetY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        xxVel += dt * aX;
        xxPos += dt * xxVel;

        double aY = fY / mass;
        yyVel += dt * aY;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}