public class Polynomial {
    double[] co;

    public Polynomial() {
        this.co = new double[1];
        return;
    }

    public Polynomial(double[] coe) {
        this.co = coe;
        return;
    }

    public Polynomial add(Polynomial a) {
        int thisTermNum = this.co.length;
        int aTermNum = a.co.length;

        if (thisTermNum < aTermNum) {
            double[] nco = new double[aTermNum];

            for (int i = 0; i < thisTermNum; i++) {
                nco[i] = a.co[i] + this.co[i];
            }

            for (int j = thisTermNum; j < aTermNum; j++) {
                nco[j] = a.co[j];
            }

            Polynomial np = new Polynomial(nco);
            return np;
        }

        if (thisTermNum == aTermNum) {
            double[] nco = new double[thisTermNum];

            for (int i = 0; i < thisTermNum; i++) {
                nco[i] = a.co[i] + this.co[i];
            }

            Polynomial np = new Polynomial(nco);
            return np;
        }

        double[] nco = new double[aTermNum];

        for (int i = 0; i < aTermNum; i++) {
            nco[i] = a.co[i] + this.co[i];
        }

        for (int j = aTermNum; j < thisTermNum; j++) {
            nco[j] = this.co[j];
        }

        Polynomial np = new Polynomial(nco);
        return np;
    }

    public double evaluate(double x) {
        int thisTermNum = this.co.length;
        double result = 0.0d;

        for (int i = 0; i < thisTermNum; i++) {
            result = result + this.co[i] * (Math.pow(x, i));
        }

        return result;
    }

    public boolean hasRoot(double x) {
        double e = evaluate(x);
        return e == 0.0d;
    }
}