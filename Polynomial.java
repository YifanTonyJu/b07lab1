import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Polynomial {
    double[] co;
    int[] co_i;

    public Polynomial() {
        this.co = new double[1];
        this.co_i = new int[1];
        return;
    }

    public Polynomial(double[] coe, int[] coe_i) {
        this.co = coe;
        this.co_i = coe_i;
        return;
    }

    public Polynomial(File f){
        Scanner scanner = null;
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String pol = scanner.nextLine();
        String modPol = pol.replaceAll("-", "\\+-");
        String[] terms = modPol.split("\\+");
        int termNum = terms.length;
        double[] coe = new double[termNum];
        int[] fr = new int[termNum];
        for (int i = 0; i < termNum; ++i) {
            String ter = terms[i];
            if(ter.contains("x")){
                String[] cf = ter.split("x");
                if (cf.length == 2){
                    coe[i] = Double.parseDouble(cf[0]);
                    fr[i] = Integer.parseInt(cf[1]);
                }
                else{
                    coe[i] = Double.parseDouble(cf[0]);
                    fr[i] = 1;
                }
            }
            else{
                coe[i] = Double.parseDouble(ter);
                fr[i] = 0;
            }
        }
        this.co = coe;
        this.co_i = fr;
        return;
    }

    public void saveToFile(String fn){
        File file = new File(fn);
        try {
            if (file.createNewFile()){
                String pol ="";
                for(int i = 0; i < this.co.length - 1; ++i){
                    if(co_i[i] == 0){
                        pol = pol + co[i] + "+";
                    }
                    else if (co_i[i] == 1){
                        pol = pol + co[i] + "x" + "+";
                    }
                    else{
                        pol = pol + co[i] + "x" + co_i[i] + "+";
                    }
                }
                pol = pol + co[this.co.length - 1] + "x" + co_i[this.co_i.length - 1];
                pol = pol.replaceAll("\\+-", "-");
                FileWriter fwr = new FileWriter(file);
                BufferedWriter bwr = new BufferedWriter(fwr);
                bwr.write(pol);
                bwr.close();
                return;
            }
            else{
                String pol ="";
                for(int i = 0; i < this.co.length - 1; ++i){
                    if(co_i[i] == 0){
                        pol = pol + co[i] + "+";
                    }
                    else if (co_i[i] == 1){
                        pol = pol + co[i] + "x" + "+";
                    }
                    else{
                        pol = pol + co[i] + "x" + co_i[i] + "+";
                    }
                }
                pol = pol + co[this.co.length - 1] + "x" + co_i[this.co_i.length - 1];
                pol = pol.replaceAll("\\+-", "-");
                FileWriter fwr = new FileWriter(file);
                BufferedWriter bwr = new BufferedWriter(fwr);
                bwr.write(pol);
                bwr.close();
                return;
            }
        } catch(IOException e){
            System.out.println(" Something Wrong Happend");
            e.printStackTrace();
        }
        return;
    }

    public Polynomial add(Polynomial a) {
        int thisLen = this.co_i[this.co_i.length - 1];
        int aLen = a.co_i[a.co_i.length - 1];
        int newLen = Math.max(thisLen, aLen) + 1;

        double[] add_co = new double[newLen];

        for (int i = 0; i < this.co_i.length; ++i){
            add_co[this.co_i[i]] = this.co[i];
        }

        for (int j = 0; j < a.co_i.length; ++j){
            add_co[a.co_i[j]] = add_co[a.co_i[j]] + a.co[j];
        }

        int no_zero = 0;
        for (int p = 0; p < newLen; ++p){
            if (add_co[p] != 0){
                no_zero += 1;
            }
        }

        double[] true_add_co = new double[no_zero];
        int[] true_add_co_i = new int[no_zero];
        int t = 0;
        for(int k = 0; k < newLen; ++k){
            if(add_co[k] != 0){
                true_add_co[t] = add_co[k];
                true_add_co_i[t] = k;
                t += 1;
            }
        }

        Polynomial newp = new Polynomial(true_add_co, true_add_co_i);
        return newp;
    }

    public double evaluate(double x) {
       int len = this.co.length;
       double result = 0.0d;

       for (int i = 0; i < len; ++i){
        result = result + this.co[i] * (Math.pow(x, this.co_i[i]));
       }

       return result;
    }

    public boolean hasRoot(double x) {
        double e = evaluate(x);
        return e == 0.0d;
    }

    public Polynomial multiply(Polynomial jyf) {
        int thisLen = this.co_i[this.co_i.length - 1];
        int jLen = jyf.co_i[jyf.co_i.length - 1];
        int newLen = thisLen + jLen + 1;
        double[] new_mul_co = new double[newLen];

        for (int i = 0; i < this.co.length; ++i){
            double ic = this.co[i];
            for (int j = 0; j < jyf.co.length; ++j) {
                new_mul_co[this.co_i[i] + jyf.co_i[j]] = new_mul_co[this.co_i[i] + jyf.co_i[j]] + ic * jyf.co[j];
            }
        }

        int no_zero = 0;
        for (int p = 0; p < newLen; ++p){
            if (new_mul_co[p] != 0){
                no_zero += 1;
            }
        }

        double[] true_mul_co = new double[no_zero];
        int[] true_mul_co_i = new int[no_zero];
        int t = 0;
        for(int k = 0; k < newLen; ++k){
            if(new_mul_co[k] != 0){
                true_mul_co[t] = new_mul_co[k];
                true_mul_co_i[t] = k;
                t += 1;
            }
        }
        Polynomial nmp = new Polynomial(true_mul_co, true_mul_co_i);
        return nmp;
    }
}